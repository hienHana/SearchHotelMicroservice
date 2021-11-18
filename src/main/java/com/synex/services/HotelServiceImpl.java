package com.synex.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synex.domain.Hotel;
import com.synex.repository.HotelRepository;

@Service
public class HotelServiceImpl implements  HotelService {
	@Autowired HotelRepository hotelRepo;
	
	@Override
	public Hotel findByHotelName(String name) { 
		return hotelRepo.findByHotelName(name);
	}

	@Override
	public Hotel saveHotel(Hotel hotel) {		
		return hotelRepo.save(hotel);
	}

	@Override
	public List<Hotel> findByCity(String name) {
		return  hotelRepo.findByCity(name);
	}

	@Override
	public List<Hotel> findByState(String name) {
		return hotelRepo.findByState(name);
	}

	@Override
	public List<Hotel> findAllHotels() {
		return hotelRepo.findAll();
	}

	@Override
	public List<Hotel> findByHotelNameCityState(String name) {
		return hotelRepo.findByHotelNameCityState(name);
	}


	@Override
	public String findHotelNameByHotelId(int hotelId) {
		return hotelRepo.findById(hotelId).get().getHotelName();
	}

	@Override
	public Hotel findByHotelId(int hotelId) {
		return hotelRepo.findById(hotelId).get();
	}

	@Override
	public int findHotelIdByRoomId(int roomId) {
		return hotelRepo.findHotelIdByRoomId(roomId);
	}

	@Override
	public void updateHotelRate(int hotelId, int avg) {
		Hotel hotel = hotelRepo.findById(hotelId).get();
		hotel.setCustomerRating(avg);
		hotelRepo.save(hotel);
		
	}

	
}
