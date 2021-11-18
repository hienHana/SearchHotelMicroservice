package com.synex.restclients;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.synex.domain.Room;
import com.synex.repository.RoomRepository;

@Component
public class HotelRestClient {
	@Autowired
	private RestTemplate restTemplate;
	@Autowired RoomRepository roomRepo;
	
	public List<Room> getRoomsByHotelSelectionWithDateSearch(int hotelIdSel, String checkInDate ) {		
		final String uri = "http://localhost:8484/getRoomsIdByHotelIdWithDateSearch/{hotelIdSel}/{checkInDate}";
		Integer[] result = restTemplate.getForObject(uri, Integer[].class, hotelIdSel, checkInDate);
		List<Room> listRooms = new ArrayList<>();
		for(Integer r:result) {
			listRooms.add(roomRepo.findById(r).get());
		}
		return listRooms;		
	}
}

	