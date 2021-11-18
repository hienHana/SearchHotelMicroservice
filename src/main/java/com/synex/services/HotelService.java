package com.synex.services;

import java.util.List;


import com.synex.domain.Hotel;


public interface HotelService {
	
	public List<Hotel> findByCity(String name);
	
	public List<Hotel> findByState(String name);
	
	public List<Hotel> findAllHotels();
	
	public Hotel saveHotel(Hotel hotel);
	
	public List<Hotel> findByHotelNameCityState(String name);
	
	public String findHotelNameByHotelId(int hotelId);
	
	public Hotel findByHotelId(int hotelId);
	
	public Hotel findByHotelName(String name);
	
	public int findHotelIdByRoomId(int roomId);
	
	public void updateHotelRate(int hotelId, int avg);
}
