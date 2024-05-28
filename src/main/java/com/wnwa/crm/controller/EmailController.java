package com.wnwa.crm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wnwa.crm.service.EmailService;
import com.wnwa.crm.service.UserService;

@Controller
public class EmailController {
    @Autowired
    private EmailService emailService;
    @Autowired
    private UserService userService;

    @PostMapping("/getEmailAddress")
    public @ResponseBody String getEmailAddress(@RequestParam String stateName,
            @RequestParam String countyName,
            @RequestParam String departmentName,
            @RequestParam String status,
            @RequestParam String comments,
            @RequestParam String reminderDate,
            @RequestParam Integer departmentId,
            @RequestParam Integer stateId, 
            @RequestParam Integer countyId) {
                List<String> emailIds  = userService.findEmailAddress(departmentId, countyId, stateId);
                return String.join(",", emailIds);
    }

    @PostMapping("/sendEmail")
    public String sendEmail(@RequestParam String emailId,@RequestParam String cc,@RequestParam String bcc,
            @RequestParam String subject,
            @RequestParam String content,
            Model model) {
                try{
            String emailResult = emailService.sendEmail(emailId,cc,bcc, subject,
                    content);
            model.addAttribute("msg", emailResult);
                }
                catch (Exception e) {
                    // Log the exception for debugging
                    e.printStackTrace();
                    // Add an error message to the model
                    model.addAttribute("msg", "Failed to send email. Please try again later.");
                }
        
                // Return a fragment or partial view to update the specific part of the page
                return "message :: message-content";
       
    }
}
