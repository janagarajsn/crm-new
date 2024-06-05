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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.wnwa.crm.entity.Department;
import com.wnwa.crm.entity.State;
import com.wnwa.crm.entity.User;
import com.wnwa.crm.service.DepartmentService;
import com.wnwa.crm.service.StateService;
import com.wnwa.crm.service.UserService;

@Controller
public class UserController {

   
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private StateService stateService;
    @Autowired
    private UserService userService;
    

    @GetMapping("/addUser")
    public String addNewUser(Model model) {
        User user = new User();
        model.addAttribute("allUserList", userService.getUsers());
        model.addAttribute("user", user);
        return "addNewUser";
    }

    @PostMapping("/addUser")
    public String addNewUser(@ModelAttribute("user") User user, Model model, RedirectAttributes redirectAttributes) {
        userService.createUser(user);
        model.addAttribute("allUserList", userService.getUsers());
        redirectAttributes.addFlashAttribute("successMessage", "User Added/Updated!");
        return "redirect:/addUser";
    }

    @GetMapping("/updateUser/{id}")
    public String updateForm(@PathVariable(value = "id") Integer id, Model model) {
        User user = userService.getById(id);
        model.addAttribute("user", user);
        return "updateUser";
    }

    @GetMapping("/deleteUser/{id}")
    public String deleteThroughId(@PathVariable(value = "id") Integer id, Model model, RedirectAttributes redirectAttributes) {
        User user = new User();
        userService.deleteUserByID(id);
        model.addAttribute("allUserList", userService.getUsers());
        model.addAttribute("user", user);
        redirectAttributes.addFlashAttribute("successMessage", "User Deleted!");
        return "redirect:/addUser";
    }
  @ModelAttribute("states")
    public List<State> populateStates(Model model) {
        List<State> state = new ArrayList<State>();
        state = stateService.getStates();
        model.addAttribute("states", state);
        return state;
    }
    @GetMapping("/loaddept")
    @ResponseBody
    public List<Department> populateList(@RequestParam("stateId") Integer stateId,@RequestParam("countyId") Integer countyId) {
        return  departmentService.getDeptListOnCounty(stateId,countyId);
    }

}
