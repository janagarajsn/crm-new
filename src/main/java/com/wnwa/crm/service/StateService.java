package com.wnwa.crm.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wnwa.crm.dao.StateRepository;
import com.wnwa.crm.entity.State;

import jakarta.annotation.Nonnull;


@Service
public class StateService {
    @Autowired
   private StateRepository stateRepository;

   public State createState(@Nonnull State state) {
      if (state != null)
    return stateRepository.save(state);
    else
    return null;

   }

   public List<State> getStates() {
      return stateRepository.findAll();
   }

   public State updateState(State state) {
      State existingState = null;
      Optional<State> optionalDept = stateRepository.findById(state.getStateId());
      if (optionalDept.isPresent()) {
         existingState = optionalDept.get();
         existingState.setStateCode(state.getStateCode());
         existingState.setStateName(state.getStateName());
         stateRepository.save(existingState);
      } else {
         return new State();
      }
      return existingState;
   }

   public String deleteStateByID(Integer id) {
      stateRepository.deleteById(id);
      return "State got deleted";
   }


   public State getById (Integer id)
    {
        Optional<State> optional = stateRepository.findById(id);
        State state = null;
        if (optional.isPresent())
            state = optional.get();
        else
            throw new RuntimeException(
                "State not found for id : " + id);
        return state;
    }
}
