package com.wnwa.crm.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.wnwa.crm.dao.UserRepository;
import com.wnwa.crm.entity.County;
import com.wnwa.crm.entity.State;
import com.wnwa.crm.entity.User;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private User user;
    private State state;
    private County county;

    @BeforeEach
    public void setUp() {
        state.setStateId(1);
        county.setCountyId(1);
        user = new User();
        user.setUserId(1);
        user.setEmailId("test@gmail.com");
        user.setCounty(county);
        user.setState(state);
    }

    @Test
    public void testCreateUser() {
        // Given
        when(userRepository.save(any(User.class))).thenReturn(user);

        // When
        User result = userService.createUser(user);

        // Then
        assertNotNull(result);
        assertEquals(user, result);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void testCreateUserNull() {
        // When
        User result = userService.createUser(null);

        // Then
        assertNull(result);
        verify(userRepository, times(0)).save(any(User.class));
    }

    @Test
    public void testGetUsers() {
        // Given
        List<User> users = Arrays.asList(user);
        when(userRepository.findAll()).thenReturn(users);

        // When
        List<User> result = userService.getUsers();

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(user, result.get(0));
        verify(userRepository, times(1)).findAll();
    }

    @Test
    public void testDeleteUserByID() {
        // When
        String result = userService.deleteUserByID(user.getUserId());

        // Then
        assertEquals("User Deleted", result);
        verify(userRepository, times(1)).deleteById(user.getUserId());
    }

    @Test
    public void testGetById() {
        // Given
        when(userRepository.findById(user.getUserId())).thenReturn(Optional.of(user));

        // When
        User result = userService.getById(user.getUserId());

        // Then
        assertNotNull(result);
        assertEquals(user, result);
        verify(userRepository, times(1)).findById(user.getUserId());
    }

    @Test
    public void testGetByIdNotFound() {
        // Given
        int nonExistentId = 2;
        when(userRepository.findById(nonExistentId)).thenReturn(Optional.empty());

        // When & Then
        Exception exception = assertThrows(RuntimeException.class, () -> {
            userService.getById(nonExistentId);
        });
        assertEquals("User not found for id : " + nonExistentId, exception.getMessage());
        verify(userRepository, times(1)).findById(nonExistentId);
    }

    @Test
    public void testFindEmailAddress() {
        // Given
        Integer departmentId = 1;
        Integer countyId = 1;
        Integer stateId = 1;
        List<String> emails = Arrays.asList("test@example.com");
        when(userRepository.findEmailByStateCountyDepartment(stateId, countyId, departmentId)).thenReturn(emails);

        // When
        List<String> result = userService.findEmailAddress(departmentId, countyId, stateId);

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("test@example.com", result.get(0));
        verify(userRepository, times(1)).findEmailByStateCountyDepartment(stateId, countyId, departmentId);
    }
}
