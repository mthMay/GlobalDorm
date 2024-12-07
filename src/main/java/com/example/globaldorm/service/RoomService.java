package com.example.globaldorm.service;

import com.example.globaldorm.model.Room;
import com.example.globaldorm.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    public List<Room> searchAvailableRooms(String city) {
        return roomRepository.findByAddressCity(city);
    }
}
