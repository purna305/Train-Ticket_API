package com.example.train_ticket_api.service;

import com.example.train_ticket_api.model.Passenger;
import com.example.train_ticket_api.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserServiceTest {

  @InjectMocks
  UserService userService;

  @Mock
  UserRepository userRepository;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this); // Initialize mocks
  }

  @Test
  public void testSaveUser() {
    Passenger passenger = new Passenger("purna", "sai", "purnasai305@gmail.com", "london", "france", 20, "A", 11);
    when(userRepository.save(any(Passenger.class))).thenReturn(passenger);

    Passenger savedPassenger = userService.saveUser(passenger);

    assertEquals(passenger, savedPassenger);
    verify(userRepository, times(1)).save(passenger);
  }

  @Test
  public void testGetUserById(){
    Passenger passenger = new Passenger("purna", "sai", "purnasai305@gmail.com", "london", "france", 20, "A", 11);
    when(userRepository.findById(anyLong())).thenReturn(Optional.of(passenger));

    Optional<Passenger> resultPassenger=userService.getUserById(16L);

    assertEquals(Optional.of(passenger), resultPassenger);
    verify(userRepository, times(1)).findById(16L);
  }

  @Test
  public void testGetUserBySection(){
    List<Passenger> passengerList = Arrays.asList(
            new Passenger("Naveen", "sai", "Naveen305@gmail.com", "london", "france", 20, "A", 11),
            new Passenger("purna", "sai", "purnasai305@gmail.com", "london", "france", 20, "A", 11)
    );

    when(userRepository.findBySeatSection(anyString())).thenReturn(passengerList);

    List<Passenger> passengerListResult=userService.getUsersBySection("A");
    assertEquals(passengerListResult,passengerListResult);
  }

  @Test
  public void testDeleteUser(){
    doNothing().when(userRepository).deleteById(anyLong());
    userService.deleteUser(12L);
    verify(userRepository,times(1)).deleteById(12L);
  }

}
