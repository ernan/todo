package com.fodala.controller;

import com.fodala.pojo.ToDo;
import com.fodala.service.ToDoService;
import com.fodala.validation.ToDoValidationError;
import jakarta.servlet.http.HttpServletRequest;
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
    public String importantStatus(@RequestParam(value = "id") Integer id, @RequestParam(value = "currentTab") String currentTab) {
        logger.info("Marking todo important: {}", id);
        toDoService.important(id);
        return "redirect:/" + currentTab;
    }

    @RequestMapping(value = "/completedStatus", method = RequestMethod.POST)
    public String completedStatus(@RequestParam(value = "id") Integer id, @RequestParam(value = "currentTab") String currentTab) {
        logger.info("Completed todo: {}", id);
        toDoService.completed(id);
        return "redirect:/" + currentTab;
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
        return "/index";
    }

    @GetMapping("/completed")
    public String indexCompleted(Model model) {
        addAttributes(model, ListFilter.COMPLETED, Tab.Tasks,
                toDoService.filter(Collections.singletonMap("completed", 1)));
        return "/index";
    }

    void addAttributes(Model model, ListFilter listFilter, Tab tab, List<ToDo> todos) {
        logger.info("{}", todos.get(0));
        model.addAttribute("todo", new ToDo());
        model.addAttribute("filter", listFilter);
        model.addAttribute("todos", todos);
        model.addAttribute("currentTab", tab.toString());
        model.addAttribute("totalNumberOfItems", todos.size());
        model.addAttribute("count", toDoService.count());
    }

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ToDoValidationError handleException(Exception exception) {
        return new ToDoValidationError(exception.getMessage());
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Model model) {
        logger.info("Getting all todos");
        addAttributes(model, ListFilter.ALL, Tab.Tasks, toDoService.all());
        return "/index";
    }

    @RequestMapping(value = "/tasks", method = RequestMethod.GET)
    public String tasks(Model model) {
        logger.info("Getting all tasks");
        model.addAttribute("todos", toDoService.all());
        addAttributes(model, ListFilter.ALL, Tab.Tasks, toDoService.all());
        return "/index";
    }

    @RequestMapping(value = "/planned", method = RequestMethod.GET)
    public String planned(Model model) {
        logger.info("Getting all planned todos");
        LocalDateTime start = LocalDateTime.now().minusDays(10);
        LocalDateTime end = LocalDateTime.now().plusYears(100L);
        addAttributes(model, ListFilter.ALL, Tab.Planned, toDoService.findByDate(start, end));
        return "/index";
    }

    @RequestMapping(value = "/important", method = RequestMethod.GET)
    public String important(Model model) {
        logger.info("Getting all important todos");
        addAttributes(model, ListFilter.ALL, Tab.Important, toDoService.filter(Collections.singletonMap("important", 1)));
        return "/index";
    }

    @RequestMapping(value = "/myday", method = RequestMethod.GET)
    public String myDay(Model model) {
        logger.info("Getting My Day todos");
        LocalDateTime start = LocalDateTime.now();
        LocalDateTime end = LocalDateTime.now().plusDays(1L);
        addAttributes(model, ListFilter.ALL, Tab.MyDay, toDoService.findByDate(start, end));
        return "/index";
    }

    @RequestMapping(value = "/todo", method = RequestMethod.GET)
    public String todo(@RequestParam(value = "id", required = false) Integer id, Model model) {
        ToDo todo;
        if (id != null) {
            todo = toDoService.findById(id);
            model.addAttribute("history", toDoService.history(id));
        } else {
            todo = toDoService.createEmpty();
        }
        model.addAttribute("currentTab", "tasks");
        model.addAttribute("todo", todo);
        return "todo/edit";
    }

    @RequestMapping(value = "/todo", method = RequestMethod.POST)
    public ModelAndView save(ToDo toDo, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            if (toDo.getId() == null) {
                toDo.setCreated(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                toDo.setCompleted(0);
                toDo.setImportant(0);
                toDo.setStatus("New");
                toDoService.insert(toDo);
                logger.info("Inserted todo {}", toDo);
            } else {
                if (toDoService.findById(toDo.getId()) != null) {
                    toDoService.update(toDo);
                    logger.info("Updated todo {}", toDo);
                }
            }
        }
        ModelAndView modelAndView = new ModelAndView("todo/edit");
        modelAndView.addObject("todo", toDo);
        modelAndView.addObject("currentTab", "home");
        modelAndView.addObject("history", toDoService.history(toDo.getId()));
        return modelAndView;
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
        Tasks("tasks");
        final String value;

        Tab(String value) {
            this.value = value;
        }

        public String toString() {
            return value;
        }
    }

}
