package com.example.train_ticket_api.controller;

import com.example.train_ticket_api.model.Passenger;
import com.example.train_ticket_api.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserControllerTest {
    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Test
    public void testPurchaseTicket_Success() throws Exception {
        Passenger passenger = new Passenger("John", "Doe", "john.doe@example.com", "London", "France", 20.0, "A", 1);
        when(userService.saveUser(any(Passenger.class))).thenReturn(passenger);

        Passenger request = new Passenger("John", "Doe", "john.doe@example.com", "London", "France", 0, null, 0);
        ResponseEntity<Passenger> responseEntity = userController.purchaseTicket(request);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        verify(userService, times(1)).saveUser(any(Passenger.class));
    }
}



