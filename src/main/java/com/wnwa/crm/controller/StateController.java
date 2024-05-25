package com.wnwa.crm.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.wnwa.crm.entity.State;
import com.wnwa.crm.service.StateService;

@Controller
public class StateController {
    @Autowired
    private StateService stateService;

    @GetMapping("/addState")
    public String addNewState(Model model) {
        State state = new State();
        model.addAttribute("allStateList", stateService.getStates());
        model.addAttribute("state", state);
        return "newState";
    }

    @PostMapping("/addState")
    public String addState(@ModelAttribute("state") State state, Model model) {
        stateService.createState(state);
        model.addAttribute("allStateList", stateService.getStates());
        return "newState";
    }

    @GetMapping("/updateState/{id}")
    public String updateForm(@PathVariable(value = "id") Integer id, Model model) {
        State state = stateService.getById(id);
        model.addAttribute("state", state);
        model.addAttribute("allStateList", stateService.getStates());
        return "updateState";
    }

    @GetMapping("/deleteState/{id}")
    public String deleteThroughId(@PathVariable(value = "id") Integer id, Model model) {
        State state = new State();
        stateService.deleteStateByID(id);
        model.addAttribute("allStateList", stateService.getStates());
        model.addAttribute("state", state);
        return "newState";

    }

}
