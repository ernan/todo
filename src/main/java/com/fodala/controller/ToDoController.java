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

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String delete(@RequestParam(value = "id") Integer id, @RequestParam(value = "currentTab") String currentTab) {
        logger.info("Deleting todo: {}", id);
        toDoService.delete(id);
        return "redirect:/" + currentTab;
    }

    @RequestMapping(value = "/importantStatus", method = RequestMethod.POST)
    public String importantStatus(@RequestParam(value = "id") Integer id,
                                  @RequestParam(value = "currentTab") String currentTab,
                                  @RequestParam(value = "list_id", required = false) Integer list_id) {
        logger.info("Marking todo important: {}", id);
        toDoService.important(id);
        String target = list_id == null ? currentTab : currentTab + "?id=" + list_id;
        return "redirect:/" + target;
    }

    @RequestMapping(value = "/completedStatus", method = RequestMethod.POST)
    public String completedStatus(@RequestParam(value = "id") Integer id,
                                  @RequestParam(value = "currentTab") String currentTab,
                                  @RequestParam(value = "list_id", required = false) Integer list_id) {
        logger.info("Completed todo: {}", id);
        toDoService.completed(id);
        String target = list_id == null ? currentTab : currentTab + "?id=" + list_id;
        return "redirect:/" + target;
    }


    @RequestMapping(value = "/back", method = RequestMethod.GET)
    public ModelAndView back(HttpServletRequest request) {
        String referer = getPreviousPageByRequest(request).orElse("/");
        return new ModelAndView("redirect:" + referer);
    }

    @GetMapping("/active")
    public String indexActive(Model model) {
        addAttributes(model, ListFilter.ACTIVE, Tab.Tasks,
                toDoService.filter(Collections.singletonMap("completed", 0)));
        return "index";
    }

    @GetMapping("/completed")
    public String indexCompleted(Model model) {
        addAttributes(model, ListFilter.COMPLETED, Tab.Tasks,
                toDoService.filter(Collections.singletonMap("completed", 1)));
        return "index";
    }

    void addAttributes(Model model, ListFilter listFilter, Tab tab, List<ToDo> todos) {
        model.addAttribute("todo", toDoService.createEmpty());
        model.addAttribute("filter", listFilter);
        model.addAttribute("todos", todos);
        model.addAttribute("currentTab", tab.toString());
        model.addAttribute("totalNumberOfItems", todos.size());
        model.addAttribute("count", toDoService.count());
        model.addAttribute("listNames", toDoService.listNames());
    }

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ToDoValidationError handleException(Exception exception) {
        return new ToDoValidationError(exception.getMessage());
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Model model) {
        logger.info("Getting all todos");
        model.addAttribute("title", "Tasks");
        addAttributes(model, ListFilter.ALL, Tab.Tasks, toDoService.all());
        return "index";
    }

    @RequestMapping(value = "/tasks", method = RequestMethod.GET)
    public String tasks(Model model) {
        logger.info("Getting all tasks");
        model.addAttribute("title", "Tasks");
        addAttributes(model, ListFilter.ALL, Tab.Tasks, toDoService.all());
        return "index";
    }

    @RequestMapping(value = "/planned", method = RequestMethod.GET)
    public String planned(Model model) {
        logger.info("Getting all planned todos");
        model.addAttribute("title", "Planned");
        LocalDateTime start = LocalDateTime.now();
        LocalDateTime end = LocalDateTime.now().plusYears(100L);
        addAttributes(model, ListFilter.ALL, Tab.Planned, toDoService.findByDate(start, end));
        return "index";
    }

    @RequestMapping(value = "/important", method = RequestMethod.GET)
    public String important(Model model) {
        logger.info("Getting all important todos");
        model.addAttribute("title", "Important ToDos");
        addAttributes(model, ListFilter.ALL, Tab.Important, toDoService.filter(Collections.singletonMap("important", 1)));
        return "index";
    }

    @RequestMapping(value = "/myday", method = RequestMethod.GET)
    public String myDay(Model model) {
        logger.info("Getting My Day todos");
        model.addAttribute("title", "My Day: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("EEE d MMM uuuu")));
        LocalDateTime start = LocalDateTime.now();
        LocalDateTime end = LocalDateTime.now().plusDays(1L);
        addAttributes(model, ListFilter.ALL, Tab.MyDay, toDoService.findByDate(start, end));
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
        return "todo/edit";
    }

    @RequestMapping(value = "/todo", method = RequestMethod.POST)
    public String save(ToDo toDo,
                       @RequestParam(value = "currentTab", required = false) String currentTab,
                       @RequestParam(value = "list_id", required = false) Integer list_id,
                       BindingResult bindingResult, Model model) {
        if (!bindingResult.hasErrors()) {
            if (toDo.getId() == null) {
                toDo.setCreated(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                toDo.setImportant(currentTab.equals(Tab.Important.value) ? 1 : 0);
                toDo.setCompleted(0);
                if (currentTab.equals(Tab.MyDay.value)) {
                    toDo.setStart(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                }
                toDo.setStatus("New");
                toDoService.insert(toDo);
                int id = toDoService.lastInsert();
                if (currentTab.equals(Tab.List.value)) {
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

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String search(@RequestParam(value = "search", required = false) String search, Model model, HttpSession session) {
        logger.info("Finding tasks containing text");
        model.addAttribute("search", search);
        session.setAttribute("search", search);
        List<ToDo> toDos = toDoService.search("%" + search + "%");
        model.addAttribute("title", "Find: " + search);
        addAttributes(model, ListFilter.ALL, Tab.Search, toDos);
        return "index";
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String searchGet(@RequestParam(value = "search", required = false) String search, Model model, HttpSession session) {
        logger.info("Finding tasks containing text");
        if (search == null) {
            search = (String) session.getAttribute("search");
        }
        model.addAttribute("search", search);
        List<ToDo> toDos = toDoService.search("%" + search + "%");
        addAttributes(model, ListFilter.ALL, Tab.Search, toDos);
        return "index";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(@RequestParam(value = "id") Integer id, Model model) {
        logger.info("Finding tasks containing text");
        List<ToDo> toDos = toDoService.listItems(id);

        model.addAttribute("list_id", id);
        model.addAttribute("title", getListName(id));
        addAttributes(model, ListFilter.ALL, Tab.List, toDos);
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

    @RequestMapping(value = "/addList", method = RequestMethod.POST)
    public String addList(@RequestParam(value = "list") String list, Model model) {
        logger.info("Adding list {}", list);
        toDoService.createList(list);
        model.addAttribute("list", list);
        model.addAttribute("title", list);
        addAttributes(model, ListFilter.ALL, Tab.List, Collections.emptyList());
        return "index";
    }

    enum ListFilter {
        ALL,
        ACTIVE,
        COMPLETED
    }

    enum Tab {
        MyDay("myday"),
        Planned("planned"),
        Important("important"),
        Tasks("tasks"),
        List("list"),
        Search("search");
        final String value;

        Tab(String value) {
            this.value = value;
        }

        public String toString() {
            return value;
        }
    }

}
