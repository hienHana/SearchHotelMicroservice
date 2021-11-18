package com.synex.services;

import java.util.List;

import com.synex.domain.Room;


public interface RoomService {
	public List<Room> findAllRoom();
	public Room saveRoom(Room room);
	
	public List<Room> getRoomsByHotelId(int hotelId);
	
	public String findRoomTypeByRoomId(int roomId);
	
}
