package com.example.globaldorm.service;

import com.example.globaldorm.model.Room;
import com.example.globaldorm.repository.RoomRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService {

    private RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Cacheable("rooms")
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    @Cacheable(value="roomsByCity", key="#city")
    public List<Room> searchRoomsByCity(String city) {
        return roomRepository.findByAddressCity(city);
    }

    @Cacheable(value="roomById", key="#id")
    public Optional<Room> findRoomsById(String id) {
        return roomRepository.findById(id);
    }
}
