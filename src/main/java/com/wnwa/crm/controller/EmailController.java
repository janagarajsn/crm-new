package com.wnwa.crm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.wnwa.crm.service.EmailService;
import com.wnwa.crm.service.UserService;

@Controller
public class EmailController {
    @Autowired
    private EmailService emailService;
    @Autowired
    private UserService userService;

    @PostMapping("/sendEmail")
    public String sendEmail(@RequestParam String stateName,
            @RequestParam String countyName,
            @RequestParam String departmentName,
            @RequestParam String status,
            @RequestParam String comments,
            @RequestParam String reminderDate,
            @RequestParam Integer departmentId,
            @RequestParam Integer stateId,
            @RequestParam Integer countyId,
            Model model) {
        try {
            System.out.println("countyId### " + countyId + "" + "stateId### " + stateId + "departmentId### "
                    + departmentId);
            String emailId = userService.findEmailAddress(departmentId,countyId,stateId);
            System.out.println(emailId);
            String emailResult =  emailService.sendEmail(emailId, "subject",
                    "this is sample test msg");
            model.addAttribute("msg", emailResult);
        } catch (Exception e) {
            // Log the exception for debugging
            e.printStackTrace();
            // Add an error message to the model
            model.addAttribute("msg", "Failed to send email. Please try again later.");
        }

        // Return a fragment or partial view to update the specific part of the page
        return "message :: message-content";
    }
}
