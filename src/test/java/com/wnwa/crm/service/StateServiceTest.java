package com.wnwa.crm.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.wnwa.crm.dao.StateRepository;
import com.wnwa.crm.entity.State;
@ExtendWith(MockitoExtension.class)
class StateServiceTest {

    @Mock
    private StateRepository stateRepositoryMock;

    @InjectMocks
    private StateService stateService;
    private State state;

    @BeforeEach
   public void init() {
    state = new State();
        state.setStateId(1);
        state.setStateCode("CA");
        state.setStateName("California");
  }

    @Test
    void testCreateState() {
   when(stateRepositoryMock.save(any(State.class))).thenReturn(state);

        State createdState = stateService.createState(state);

        assertNotNull(createdState);
        assertEquals(state.getStateId(), createdState.getStateId());
        verify(stateRepositoryMock, times(1)).save(state);
    }

    @Test
    void testGetStates() {
        List<State> stateList = new ArrayList<>();
        State state1 = new State();
        state1.setStateId(1);
        state1.setStateCode("NY");
        state1.setStateName("New York");
        stateList.add(state1);

        when(stateRepositoryMock.findAll()).thenReturn(stateList);

        List<State> retrievedStates = stateService.getStates();
        assertEquals(stateList, retrievedStates);
    }

    @Test
    void testDeleteStateByID() {
        Integer stateId = 1;
        stateService.deleteStateByID(stateId);
        verify(stateRepositoryMock, times(1)).deleteById(stateId);
    }

    @Test
    void testGetById() {
        //State state = new State();
        //state.setStateId(1);
       // state.setStateCode("NY");
        //state.setStateName("New York");

        when(stateRepositoryMock.findById(1)).thenReturn(Optional.of(state));

        State retrievedState = stateService.getById(1);
        assertEquals(state, retrievedState);
    }
}
