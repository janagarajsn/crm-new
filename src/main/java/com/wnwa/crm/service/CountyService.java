package com.wnwa.crm.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wnwa.crm.dao.CountyRepository;
import com.wnwa.crm.entity.County;

@Service
public class CountyService {
    @Autowired
   private CountyRepository countyRepository;

   public County createCounty(County county) {
     if(county != null)
      return countyRepository.save(county);
      else
      return null;
   
   }

   public List<County> getCounties() {
      return countyRepository.findAll();
   } 

  public String deleteCountyByID(Integer id) {
      countyRepository.deleteById(id);
      return "County Deleted";
   }

   public County getById (Integer id)
    {
        Optional<County> optional = countyRepository.findById(id);
        County county = null;
        if (optional.isPresent())
            county = optional.get();
        else
            throw new RuntimeException(
                "County not found for id : " + id);
        return county;
    }

   public List<County> getCountiesByStateId(Integer stateId) {
      return countyRepository.findByState_StateId(stateId);
   }
}
