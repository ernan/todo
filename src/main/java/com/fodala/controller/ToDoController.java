package com.fodala.controller;

import com.fodala.pojo.ToDo;
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
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class ToDoController {
    private static final Logger logger = LoggerFactory.getLogger(ToDoController.class);

    @Autowired
    private ToDoService toDoService;

    Optional<String> getPreviousPageByRequest(HttpServletRequest request) {
        return Optional.ofNullable(request.getHeader("Referer")).map(requestUrl -> "redirect:" + requestUrl);
    }

    @PostMapping("/delete")
    public String delete(@RequestParam(value = "id") Integer id,
                         @RequestParam(value = "currentTab") String currentTab,
                         @RequestParam(value = "list_id", required = false) Integer listId) {
        logger.info("Deleting todo: {}", id);
        toDoService.delete(id);
        String target = listId == null ? currentTab : currentTab + "?id=" + listId;
        return "redirect:/" + target;
    }

    @PostMapping("/importantStatus")
    public String importantStatus(@RequestParam(value = "id") Integer id,
                                  @RequestParam(value = "currentTab") String currentTab,
                                  @RequestParam(value = "list_id", required = false) Integer list_id) {
        logger.info("Marking todo important: {}", id);
        toDoService.important(id);
        String target = list_id == null ? currentTab : currentTab + "?id=" + list_id;
        return "redirect:/" + target;
    }

    @PostMapping("/completedStatus")
    public String completedStatus(@RequestParam(value = "id") Integer id,
                                  @RequestParam(value = "currentTab") String currentTab,
                                  @RequestParam(value = "list_id", required = false) Integer list_id) {
        logger.info("Completed todo: {}", id);
        toDoService.completed(id);
        String target = list_id == null ? currentTab : currentTab + "?id=" + list_id;
        return "redirect:/" + target;
    }

    @GetMapping("/back")
    public ModelAndView back(HttpServletRequest request) {
        String referer = getPreviousPageByRequest(request).orElse("/");
        return new ModelAndView("redirect:" + referer);
    }

    @GetMapping("/active")
    public String indexActive(Model model) {
        addAttributes(model, Filter.ACTIVE, Tab.TASKS,
                toDoService.filter(Collections.singletonMap("completed", 0)));
        return "index";
    }

    @GetMapping("/calendar")
    public String calendar(Model model) {
        addAttributes(model, Filter.COMPLETED, Tab.CALENDAR, toDoService.all());
        return "calendar";
    }

    @GetMapping("/completed")
    public String indexCompleted(Model model) {
        addAttributes(model, Filter.COMPLETED, Tab.TASKS,
                toDoService.filter(Collections.singletonMap("completed", 1)));
        return "index";
    }

    void addAttributes(Model model, Filter listFilter, Tab tab, List<ToDo> todos) {
        model.addAttribute("todo", toDoService.createEmpty());
        model.addAttribute("filter", listFilter);
        model.addAttribute("todos", todos);
        model.addAttribute("title", tab.title);
        model.addAttribute("currentTab", tab.value);
        model.addAttribute("totalNumberOfItems", todos.size());
        model.addAttribute("count", toDoService.count());
        model.addAttribute("listNames", toDoService.listNames());
    }

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ToDoValidationError handleException(Exception exception) {
        return new ToDoValidationError(exception.getMessage());
    }

    @GetMapping("/")
    public String home(Model model) {
        logger.info("Getting all todos");
        addAttributes(model, Filter.ALL, Tab.TASKS, toDoService.all());
        return "index";
    }

    @GetMapping("/tasks")
    public String tasks(Model model) {
        logger.info("Getting all tasks");
        addAttributes(model, Filter.ALL, Tab.TASKS, toDoService.all());
        return "index";
    }

    @GetMapping("/planned")
    public String planned(Model model) {
        logger.info("Getting all planned todos");
        model.addAttribute("title", "Planned");
        LocalDateTime start = LocalDateTime.now();
        LocalDateTime end = LocalDateTime.now().plusYears(100L);
        addAttributes(model, Filter.ALL, Tab.PLANNED, toDoService.findByDate(start, end));
        return "index";
    }

    @GetMapping("/important")
    public String important(Model model) {
        logger.info("Getting all important todos");
        addAttributes(model, Filter.ALL, Tab.IMPORTANT, toDoService.filter(Collections.singletonMap("important", 1)));
        return "index";
    }

    @GetMapping("/myday")
    public String myDay(Model model) {
        logger.info("Getting My Day todos");
        LocalDateTime start = LocalDateTime.now();
        LocalDateTime end = LocalDateTime.now().plusDays(1L);
        addAttributes(model, Filter.ALL, Tab.MY_DAY, toDoService.findByDate(start, end));
        return "index";
    }

    @RequestMapping(value = "/todo", method = RequestMethod.GET)
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
                       @RequestParam(value = "list_id", required = false) Integer list_id,
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
                    toDoService.insertListItem(list_id, id);
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
        model.addAttribute("list_id", list_id);
        model.addAttribute("todo", new ToDo());
        String target = list_id == null ? currentTab : currentTab + "?id=" + list_id;
        return "redirect:/" + target;
    }

    @PostMapping("/search")
    public String search(@RequestParam(value = "search", required = false) String search, Model model, HttpSession session) {
        logger.info("Finding tasks containing text");
        model.addAttribute("search", search);
        session.setAttribute("search", search);
        List<ToDo> toDos = toDoService.search("%" + search + "%");
        model.addAttribute("title", "Find: " + search);
        addAttributes(model, Filter.ALL, Tab.SEARCH, toDos);
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
        addAttributes(model, Filter.ALL, Tab.SEARCH, toDos);
        model.addAttribute("title", "Searching for " + search);
        return "index";
    }

    @GetMapping("/list")
    public String list(@RequestParam(value = "id") Integer id, Model model) {
        logger.info("Finding tasks for list {}", id);
        List<ToDo> toDos = toDoService.listItems(id);
        addAttributes(model, Filter.ALL, Tab.LIST, toDos);
        model.addAttribute("list_id", id);
        model.addAttribute("title", getListName(id));
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
        addAttributes(model, Filter.ALL, Tab.LIST, Collections.emptyList());
        return "index";
    }

    @GetMapping("/notifications")
    public String notifications(Model model) {
        logger.info("Getting notifications");
        addAttributes(model, Filter.ALL, Tab.NOTIFICATIONS, Collections.emptyList());
        return "notifications";
    }


    enum Filter {
        ALL("all"),
        ACTIVE("active"),
        COMPLETED("completed");

        final String value;

        Filter(String value) {
            this.value = value;
        }

        public String toString() {
            return value;
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

        public String toString() {
            return value;
        }
    }

}
