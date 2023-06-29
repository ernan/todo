package com.fodala.controller;

import com.fodala.pojo.Setting;
import com.fodala.service.SettingsService;
import com.fodala.service.ToDoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class SettingsController {
    private static final Logger logger = LoggerFactory.getLogger(SettingsController.class);

    @Autowired
    private SettingsService settingsService;
    @Autowired
    private ToDoService toDoService;

    @GetMapping("/setting")
    public String setting(@RequestParam(value = "id", required = false) Integer id, Model model) {
        Setting setting;
        if (id != null) {
            setting = settingsService.findById(id);
            model.addAttribute("history", settingsService.history(id));
        } else {
            setting = settingsService.createEmpty();
        }
        model.addAttribute("setting", setting);
        return "settingsedit";
    }

    @GetMapping("/setting/delete")
    public ModelAndView delete(@RequestParam(value = "id", required = false) Integer id) {
        if (id != null) {
            settingsService.delete(id);
        }
        return new ModelAndView("redirect:/settings");
    }

    @PostMapping("/setting")
    public ModelAndView save(Setting setting, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            if (setting.getId() == null) {
                settingsService.insert(setting);
            } else {
                if (settingsService.findById(setting.getId()) != null) {
                    settingsService.update(setting);
                }
            }
        }
        ModelAndView modelAndView = new ModelAndView("settingsedit");
        modelAndView.addObject("setting", setting);
        modelAndView.addObject("currentTab", "settings");
        modelAndView.addObject("history", settingsService.history(setting.getId()));
        return modelAndView;
    }


    @GetMapping("/settings")
    public String settings(Model model) {
        logger.info("Getting all Settings");
        List<Setting> settingList = settingsService.all();
        logger.info("Found {} Settings", settingList.size());
        model.addAttribute("settings", settingList);
        model.addAttribute("currentTab", "settings");
        model.addAttribute("count", toDoService.count());
        return "settings";
    }
}
