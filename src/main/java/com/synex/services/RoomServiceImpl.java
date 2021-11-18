package com.synex.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synex.domain.Room;
import com.synex.repository.RoomRepository;

@Service
public class RoomServiceImpl implements  RoomService {
	@Autowired RoomRepository roomRepo;

	@Override
	public List<Room> getRoomsByHotelId(int hotelId) {
		return roomRepo.getRoomsByHotelId(hotelId);
	}

	@Override
	public List<Room> findAllRoom() {
		return roomRepo.findAll();
	}

	@Override
	public Room saveRoom(Room room) {
		return roomRepo.save(room);
	}

	@Override
	public String findRoomTypeByRoomId(int roomId) {
		return roomRepo.findById(roomId).get().getRoomType().getTypeRoom();
	}
	
//	@Override
//	public List<Object[]> findTotalRoomsPerHotel() {
//		return roomRepo.findTotalRoomsPerHotel();
//	}

	
}
