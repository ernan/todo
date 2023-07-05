package com.fodala.controller;

import com.fodala.pojo.Setting;
import com.fodala.pojo.ToDo;
import com.fodala.service.SettingsService;
import com.fodala.service.ToDoService;
import com.fodala.validation.ToDoValidationError;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Controller
public class ToDoController {
    private static final Logger logger = LoggerFactory.getLogger(ToDoController.class);

    @Autowired
    private ToDoService toDoService;

    @Autowired
    private SettingsService settingsService;

    Optional<String> getPreviousPageByRequest(HttpServletRequest request) {
        return Optional.ofNullable(request.getHeader("Referer")).map(requestUrl -> "redirect:" + requestUrl);
    }

    String buildTarget(String currentTab, String filter, Integer listId, Integer page) {
        // default to myday
        String useTab = currentTab == null ? "myday" : currentTab;
        String target = useTab;
        // lists handle null or empty
        if (useTab.equals("list")) {
            if (listId == null)
                target = useTab + "/1";
            else {
                target = useTab + "/" + listId;
            }
        }
        String useFilter = filter == null ? "active" : filter;
        target += "/" + useFilter;
        target += "/" + page;
        logger.info("Built target: {} {} {} {}", currentTab, filter, listId, target);
        return target;
    }

    @PostMapping("/delete")
    public String delete(@RequestParam(value = "id") Integer id,
                         @RequestParam(value = "currentTab") String currentTab,
                         @RequestParam(value = "filter") String filter,
                         @RequestParam(value = "page", defaultValue = "1") Integer page,
                         @RequestParam(value = "listId", required = false) Integer listId) {
        logger.info("Deleting todo: {}", id);
        toDoService.delete(id);
        return "redirect:/" + buildTarget(currentTab, filter, listId, page);
    }

    @PostMapping("/importantStatus")
    public String importantStatus(@RequestParam(value = "id") Integer id,
                                  @RequestParam(value = "currentTab") String currentTab,
                                  @RequestParam(value = "filter") String filter,
                                  @RequestParam(value = "page", defaultValue = "1") Integer page,
                                  @RequestParam(value = "listId", required = false) Integer listId) {
        logger.info("Marking todo important: {}", id);
        toDoService.important(id);
        return "redirect:/" + buildTarget(currentTab, filter, listId, page);
    }

    @PostMapping("/completedStatus")
    public String completedStatus(@RequestParam(value = "id") Integer id,
                                  @RequestParam(value = "currentTab") String currentTab,
                                  @RequestParam(value = "filter") String filter,
                                  @RequestParam(value = "page", defaultValue = "1") Integer page,
                                  @RequestParam(value = "listId", required = false) Integer listId) {
        logger.info("Completed todo: {}", id);
        toDoService.completed(id);
        return "redirect:/" + buildTarget(currentTab, filter, listId, page);
    }

    @GetMapping("/back")
    public ModelAndView back(HttpServletRequest request) {
        String referer = getPreviousPageByRequest(request).orElse("/");
        return new ModelAndView("redirect:" + referer);
    }

    @GetMapping("/calendar")
    public String calendar(Model model) {
        addAttributes(model, Filter.COMPLETED, Tab.CALENDAR, toDoService.all(), null);
        return "calendar";
    }

    @GetMapping("/notifications")
    public String notifications(Model model) {
        addAttributes(model, Filter.ALL, Tab.NOTIFICATIONS, toDoService.all(), null);
        model.addAttribute("notifications", toDoService.notifications());
        return "notifications";
    }

    void addAttributes(Model model, Filter listFilter, Tab tab, List<ToDo> toDos, Integer page) {
        model.addAttribute("todo", toDoService.createEmpty());
        model.addAttribute("filter", listFilter.name);
        model.addAttribute("title", tab.title);
        model.addAttribute("currentTab", tab.value);
        model.addAttribute("totalNumberOfToDos", toDos.size());
        List<Setting> list = settingsService.all();
        Map<String, String> settings = list.stream()
                .collect(Collectors.toMap(Setting::getName, Setting::getValue));
        double maxItems = Double.parseDouble(settings.get("Max ToDo Items"));
        model.addAttribute("maxToDoItems", (int) maxItems);
        model.addAttribute("count", toDoService.count());
        model.addAttribute("listNames", toDoService.listNames());
        model.addAttribute("settings", settings);
        int pages = (int) Math.ceil((double) toDos.size() / maxItems);
        model.addAttribute("pages", pages);
        if (page == null || page < 1) {
            page = 1;
        } else if (page > pages) {
            page = pages;
        }
        model.addAttribute("page", page);
        if (pages > 0) {
            int maxItemCount = (int) maxItems;
            int from = ((page - 1) * maxItemCount);
            int to = from + maxItemCount;
            if (to > toDos.size()) {
                to = toDos.size();
            }
            toDos = toDos.subList(from, to);
        }
        model.addAttribute("todos", toDos);
    }

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ToDoValidationError handleException(Exception exception) {
        return new ToDoValidationError(exception.getMessage());
    }

    @GetMapping("/")
    public String home(Model model) {
        logger.info("Getting all todos");
        addAttributes(model, Filter.ACTIVE, Tab.TASKS, toDoService.all(), 1);
        return "index";
    }

    @GetMapping("/todo")
    public String todo(@RequestParam(value = "id", required = false) Integer id,
                       Model model) {
        ToDo toDo;
        if (id != null) {
            toDo = toDoService.findById(id);
            model.addAttribute("history", toDoService.history(id));
        } else {
            toDo = toDoService.createEmpty();
        }
        model.addAttribute("currentTab", "tasks");
        model.addAttribute("title", toDo.getTitle());
        model.addAttribute("todo", toDo);
        return "edit";
    }

    @PostMapping("/todo")
    public String save(ToDo toDo,
                       @RequestParam(value = "currentTab", required = false) String currentTab,
                       @RequestParam(value = "listId", required = false) Integer listId,
                       @RequestParam(value = "filter", required = false) String filter,
                       @RequestParam(value = "page", defaultValue = "1") Integer page,
                       BindingResult bindingResult, Model model) {
        if (!bindingResult.hasErrors()) {
            if (toDo.getId() == null) {
                toDo.setCreated(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                toDo.setImportant(currentTab.equals(Tab.IMPORTANT.value) ? 1 : 0);
                toDo.setCompleted(0);
                if (currentTab.equals(Tab.MY_DAY.value)) {
                    toDo.setStart(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                }
                toDo.setStatus("New");
                int id = toDoService.insert(toDo);
                if (currentTab.equals(Tab.LIST.value)) {
                    toDoService.insertListItem(listId, id);
                }
                logger.info("Inserted todo {}", toDo);
            } else {
                if (toDoService.findById(toDo.getId()) != null) {
                    toDoService.update(toDo);
                    logger.info("Updated todo {}", toDo);
                }
            }
        }
        model.addAttribute("currentTab", currentTab);
        model.addAttribute("title", currentTab);
        model.addAttribute("listId", listId);
        model.addAttribute("filter", filter);
        model.addAttribute("todo", new ToDo());
        return "redirect:/" + buildTarget(currentTab, filter, listId, page);
    }

    @PostMapping("/search")
    public String search(@RequestParam(value = "search", required = false) String search, Model model, HttpSession session) {
        logger.info("Finding tasks containing text: {}", search);
        model.addAttribute("search", search);
        session.setAttribute("search", search);
        List<ToDo> toDos = toDoService.search("%" + search + "%");
        logger.info("Found {} todos matching text: {}", toDos.size(), search);
        model.addAttribute("title", "Find: " + search);
        addAttributes(model, Filter.ALL, Tab.SEARCH, toDos, 1);
        return "index";
    }

    @GetMapping("/search")
    public String searchGet(@RequestParam(value = "search", required = false) String search, Model model, HttpSession session) {
        logger.info("Getting tasks containing text {}", search);
        if (search == null) {
            search = (String) session.getAttribute("search");
        }
        model.addAttribute("search", search);
        List<ToDo> toDos = toDoService.search("%" + search + "%");
        addAttributes(model, Filter.ALL, Tab.SEARCH, toDos, 1);
        model.addAttribute("title", "Searching for " + search);
        return "index";
    }

    String getListName(Integer id) {
        List<Map<String, Object>> list = toDoService.listNames();
        for (Map<String, Object> e : list) {
            if (e.get("id").equals(id)) {
                return (String) e.get("name");
            }
        }
        return "missing...";
    }

    @PostMapping("/addList")
    public String addList(@RequestParam(value = "list") String list, Model model) {
        logger.info("Adding list {}", list);
        toDoService.createList(list);
        model.addAttribute("list", list);
        model.addAttribute("title", list);
        addAttributes(model, Filter.ALL, Tab.LIST, Collections.emptyList(), 1);
        return "index";
    }

    @GetMapping(value = {"/{tab}/", "/{tab}/{filter}", "/{tab}/{filter}/{page}"})
    public String getTodos(Model model, @PathVariable(value = "tab") String tab,
                           @PathVariable(value = "filter") String filter,
                           @PathVariable(value = "page", required = false) Integer page) {
        logger.info("Getting toDos: ");
        Tab tab1 = Tab.parse(tab);
        List<ToDo> toDos = getToDos(tab);
        Filter filter1 = Filter.parse(filter);
        toDos = filter(filter1, toDos);
        addAttributes(model, filter1, tab1, toDos, page);
        return "index";
    }

    @GetMapping(value = {"/list/{id}", "/list/{id}/{filter}", "/list/{id}/{filter}/{page}"})
    public String list(Model model,
                       @PathVariable(value = "id") Integer id,
                       @PathVariable(value = "filter") String filter,
                       @PathVariable(value = "page", required = false) Integer page) {
        logger.info("Finding tasks for list {}", id);
        List<ToDo> toDos = toDoService.listItems(id);
        Filter filter1 = Filter.parse(filter);
        toDos = filter(filter1, toDos);
        addAttributes(model, filter1, Tab.LIST, toDos, page);
        model.addAttribute("listId", id);
        model.addAttribute("title", getListName(id));
        return "index";
    }

    private List<ToDo> filter(Filter filter, List<ToDo> toDos) {
        if (Filter.ALL == filter) {
            return toDos;
        } else {
            return toDos.stream()
                    .filter(toDo -> Objects.equals(filter.completed, toDo.getCompleted()))
                    .collect(Collectors.toList());
        }
    }

    List<ToDo> getToDos(String tab) {
        switch (tab) {
            case "planned" -> {
                LocalDateTime start = LocalDateTime.now();
                LocalDateTime end = LocalDateTime.now().plusYears(100L);
                return toDoService.findByDate(start, end);
            }
            case "myday" -> {
                LocalDateTime start = LocalDateTime.now();
                LocalDateTime end = LocalDateTime.now().plusDays(1L);
                return toDoService.findByDate(start, end);
            }
            case "important" -> {
                return toDoService.filter(Collections.singletonMap("important", 1));
            }
            default -> {
                return toDoService.all();
            }
        }
    }


    enum Filter {
        ALL("all", -1, " 1 = 1 "),
        ACTIVE("active", 0, " completed = 0 "),
        COMPLETED("completed", 1, " completed = 1 ");

        final String name;
        final Integer completed;

        final String where;

        Filter(String name, Integer completed, String where) {
            this.name = name;
            this.completed = completed;
            this.where = where;
        }

        static Filter parse(String name) {
            for (Filter t : Filter.values()) {
                if (t.name.equals(name)) {
                    return t;
                }
            }
            return Filter.ACTIVE;
        }
    }

    enum Tab {
        CALENDAR("calendar", "Calendar"),
        IMPORTANT("important", "Important"),
        LIST("list", "List"),
        MY_DAY("myday", "My Day " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("EEE d MMM uuuu"))),
        NOTIFICATIONS("notifications", "Notifications"),
        PLANNED("planned", "Planned"),
        SEARCH("search", "Search"),
        TASKS("tasks", "Tasks");
        final String value;

        final String title;

        Tab(String value, String title) {
            this.value = value;
            this.title = title;
        }

        static Tab parse(String tabVal) {
            for (Tab t : Tab.values()) {
                if (t.value.equals(tabVal)) {
                    return t;
                }
            }
            return Tab.TASKS;
        }
    }

}
