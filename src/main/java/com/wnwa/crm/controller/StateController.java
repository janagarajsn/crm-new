package com.wnwa.crm.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.wnwa.crm.entity.State;
import com.wnwa.crm.service.StateService;

@Controller
public class StateController {
    @Autowired
    private StateService stateService;

    @GetMapping("/addState")
    public String addNewState(Model model) {
        State state = new State();
        model.addAttribute("pageTitle", "Add State");
        model.addAttribute("allStateList", stateService.getStates());
        model.addAttribute("state", state);
        return "newState";
    }

    @PostMapping("/addState")
    public String addState(@ModelAttribute("state") State state, Model model, RedirectAttributes redirectAttributes) {
        stateService.createState(state);
        model.addAttribute("allStateList", stateService.getStates());
        redirectAttributes.addFlashAttribute("successMessage", "State Added/Updated!");
        return "redirect:/addState";
    }

    @GetMapping("/updateState/{id}")
    public String updateForm(@PathVariable(value = "id") Integer id, Model model) {
        State state = stateService.getById(id);       
        model.addAttribute("pageTitle", "Update State");
        model.addAttribute("state", state);
        model.addAttribute("allStateList", stateService.getStates());
        return "updateState";
    }

    @GetMapping("/deleteState/{id}")
    public String deleteThroughId(@PathVariable(value = "id") Integer id, Model model, RedirectAttributes redirectAttributes) {
        State state = new State();
        stateService.deleteStateByID(id);
        model.addAttribute("allStateList", stateService.getStates());
        model.addAttribute("state", state);
        redirectAttributes.addFlashAttribute("successMessage", "State Deleted!");
        return "redirect:/addState";

    }

}
