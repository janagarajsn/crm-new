package com.wnwa.crm.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.wnwa.crm.dao.CountyRepository;
import com.wnwa.crm.entity.County;
import com.wnwa.crm.entity.State;

@ExtendWith(MockitoExtension.class)
public class CountyServiceTest {

    @Mock
    private CountyRepository countyRepositoryMock;

    @InjectMocks
    private CountyService countyService;
    private County county;
    private State state;

    @BeforeEach
    public void init() {
        state = new State();
        state.setStateId(1);
        state.setStateName("California");

        county = new County();
        county.setCountyId(1);
        county.setCountyName("Cook");
    }

    @Test
    public void createCountyTest() {

        when(countyRepositoryMock.save(any(County.class))).thenReturn(county);

        County createdCounty = countyService.createCounty(county);

        assertNotNull(createdCounty);
        assertEquals(county.getCountyId(), createdCounty.getCountyId());
        verify(countyRepositoryMock, times(1)).save(county);
    }

    @Test
    void getCountiesListTest() {
        List<County> countyList = new ArrayList<>();
        state = new State();
        state.setStateId(1);
        state.setStateCode("IL");
        state.setStateName("Illinois");
        County county1 = new County();
        county1.setCountyId(1);
        county1.setCountyName("Will");
        county1.setState(state);
        countyList.add(county);

        when(countyRepositoryMock.findAll()).thenReturn(countyList);

        List<County> retrievedCounties = countyService.getCounties();
        assertEquals(countyList, retrievedCounties);
    }

    @Test
    void testDeleteStateByID() {
        Integer countyId = 1;
        countyService.deleteCountyByID(countyId);
        verify(countyRepositoryMock, times(1)).deleteById(countyId);
    }

    @Test
    void testGetById() {

        when(countyRepositoryMock.findById(1)).thenReturn(Optional.of(county));

        County retrievedCounty = countyService.getById(1);
        assertEquals(county, retrievedCounty);
    }

    @Test
    public void testGetCountiesByStateId() {

        state = new State();
        state.setStateId(1);
        state.setStateCode("OH");
        state.setStateName("Ohio");

        County county1 = new County();
        county1.setCountyId(1);
        county1.setCountyName("Dupage");
        county1.setState(state);

        County county2 = new County();
        county2.setCountyId(1);
        county2.setCountyName("Will");
        county2.setState(state);
        List<County> counties = Arrays.asList(county1, county2);


        // Mock behavior of countyRepository.findByState_StateId(stateId)
        when(countyRepositoryMock.findByState_StateId(1)).thenReturn(counties);

        // When
        List<County> result = countyService.getCountiesByStateId(1);

        // Then
        assertEquals(counties, result, "Counties should match");
    }

}
