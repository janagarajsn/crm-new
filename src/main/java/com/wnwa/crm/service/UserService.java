package com.wnwa.crm.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wnwa.crm.dao.UserRepository;
import com.wnwa.crm.entity.User;



@Service
public class UserService {
    @Autowired
   private UserRepository userRepository;

   public User createUser(User user) {
     if(user != null)
      return userRepository.save(user);
      else
      return null;
   
   }

   public List<User> getUsers() {
      return userRepository.findAll();
   } 


   public User updateCounty(User user) {
      User existingUser = null;
      Optional<User> optionalDept = userRepository.findById(user.getUserId());
      if (optionalDept.isPresent()) {
         existingUser = optionalDept.get();
         existingUser.setUserName(user.getUserName());
         existingUser.setEmailId(user.getEmailId());
         userRepository.save(existingUser);
      } else {
         return new User();
      }
      return existingUser;
   }

   public String deleteUserByID(Integer id) {
      userRepository.deleteById(id);
      return "User Deleted";
   }

   public User getById (Integer id)
    {
        Optional<User> optional = userRepository.findById(id);
        User user = null;
        if (optional.isPresent())
            user = optional.get();
        else
            throw new RuntimeException(
                "County not found for id : " + id);
        return user;
    }

public String findEmailAddress(Integer departmentId, Integer countyId, Integer stateId) {
    return userRepository.findEmailByStateCountyDepartment(stateId, countyId, departmentId);

}

}
