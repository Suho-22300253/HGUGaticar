package org.example.hgugaticar.service;

import lombok.RequiredArgsConstructor;
import org.example.hgugaticar.dto.RoomRequest;
import org.example.hgugaticar.model.Room;
import org.example.hgugaticar.model.RoomStatus;
import org.example.hgugaticar.model.User;
import org.example.hgugaticar.repository.RoomRepository;
import org.example.hgugaticar.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;
    private final UserRepository userRepository;

    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    public Room getRoomById(Long id) {
        return roomRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 방을 찾을 수 없습니다."));
    }

    public Room createRoom(RoomRequest request) {
        User leader = userRepository.findById(request.getLeaderId())
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자를 찾을 수 없습니다."));

        Room room = Room.builder()
                .departure(request.getDeparture())
                .arrival(request.getArrival())
                .departureDate(request.getDepartureDate())
                .time(request.getTime())
                .maxCapacity(request.getMaxCapacity())
                .currentCapacity(1)
                .routeType(request.getRouteType())
                .status(RoomStatus.OPEN)
                .leader(leader)
                .build();

        return roomRepository.save(room);
    }

    public Room updateRoom(Long id, RoomRequest request) {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 방을 찾을 수 없습니다."));

        User leader = userRepository.findById(request.getLeaderId())
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자를 찾을 수 없습니다."));

        room.setDeparture(request.getDeparture());
        room.setArrival(request.getArrival());
        room.setDepartureDate(request.getDepartureDate());
        room.setTime(request.getTime());
        room.setMaxCapacity(request.getMaxCapacity());
        room.setRouteType(request.getRouteType());
        room.setLeader(leader);

        if (room.getCurrentCapacity() >= room.getMaxCapacity()) {
            room.setStatus(RoomStatus.FULL);
        } else {
            room.setStatus(RoomStatus.OPEN);
        }

        return roomRepository.save(room);
    }

    public void deleteRoom(Long id) {
        if (!roomRepository.existsById(id)) {
            throw new IllegalArgumentException("해당 방을 찾을 수 없습니다.");
        }

        roomRepository.deleteById(id);
    }
}