package com.wnwa.crm.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.wnwa.crm.entity.County;
import com.wnwa.crm.entity.State;
import com.wnwa.crm.service.CountyService;
import com.wnwa.crm.service.StateService;

@Controller
public class CountyController {
    @Autowired
    private CountyService countyService;
    @Autowired
    private StateService stateService;

    @GetMapping("/addCounty")
    public String addNewCounty(Model model) {
        County county = new County();
        model.addAttribute("allCountyList", countyService.getCounties());
        model.addAttribute("county", county);
        return "newCounty";
    }

    @PostMapping("/addCounty")
    public String addCounty(@ModelAttribute("county") County county, Model model) {
        countyService.createCounty(county);
        model.addAttribute("allCountyList", countyService.getCounties());
        return "newCounty";
    }

    @GetMapping("/updateCounty/{id}")
    public String updateForm(@PathVariable(value = "id") Integer id, Model model) {
        County county = countyService.getById(id);
        model.addAttribute("county", county);
        return "updateCounty";
    }

    @GetMapping("/deleteCounty/{id}")
    public String deleteThroughId(@PathVariable(value = "id") Integer id, Model model) {
        County county = new County();
        countyService.deleteCountyByID(id);
        model.addAttribute("allCountyList", countyService.getCounties());
        model.addAttribute("county", county);
        return "newCounty";
    }

    @ModelAttribute("states")
    public List<State> populateList(Model model) {
        List<State> state = new ArrayList<State>();
        state = stateService.getStates();
        model.addAttribute("states", state);
        return state;
    }

}
