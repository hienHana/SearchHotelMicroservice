package com.synex.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.synex.domain.Hotel;
import com.synex.domain.Room;
import com.synex.domain.SearchDetails;
import com.synex.restclients.HotelRestClient;
import com.synex.services.HotelService;
import com.synex.services.RoomService;

@CrossOrigin
@RestController
public class SearchHotelController {
	@Autowired HotelService hotelService;
	@Autowired RoomService roomService;
	@Autowired HotelRestClient hotelRestClient;
	
	@RequestMapping(value = "/getAllHotels", method = RequestMethod.GET)
	public ResponseEntity<List<Hotel>> getResources(){
		List<Hotel> listHotel = hotelService.findAllHotels();
		return new  ResponseEntity<List<Hotel>>(listHotel, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/getHotels", method = RequestMethod.POST)
	public ResponseEntity<List<Hotel>> getResource(@RequestBody SearchDetails searchDetails) {
		List<Hotel> listHotel= hotelService.findByHotelNameCityState(searchDetails.getSearchHotel());	
		return new  ResponseEntity<List<Hotel>>(listHotel, HttpStatus.OK);		
	}
	
	
	
	@RequestMapping(value = "/selectHotelByName/{name}", method = RequestMethod.GET)
	public ResponseEntity<Hotel> getHotelListByName(@PathVariable String name){
		Hotel hotel = hotelService.findByHotelName(name);
		return new  ResponseEntity<Hotel>(hotel, HttpStatus.OK);
	}
	
	////
	@RequestMapping(value = "/getRoomsByHotelId/{hotelId}", method = RequestMethod.GET)
	public ResponseEntity<List<Room>> getRoomsByHotelId(@PathVariable int hotelId){
		List<Room> rooms = roomService.getRoomsByHotelId(hotelId);
		return new  ResponseEntity<List<Room>>(rooms, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/getRoomsByHotelIdWithDateSearch/{hotelIdSel}/{checkInDate}", method = RequestMethod.GET)
	public ResponseEntity<List<Room>> getRoomsByHotelIdWithDateSearch(@PathVariable int hotelIdSel, @PathVariable String checkInDate){
		List<Room> rooms = hotelRestClient.getRoomsByHotelSelectionWithDateSearch(hotelIdSel, checkInDate );
		return new  ResponseEntity<List<Room>>(rooms, HttpStatus.OK);
	}
	
	///
	
	@RequestMapping(value = "/getRoomTypeByRoomId/{roomId}", method = RequestMethod.GET)
	public ResponseEntity<String> getRoomTypeByRoomId(@PathVariable int roomId){
		String roomType = roomService.findRoomTypeByRoomId(roomId);
		return new  ResponseEntity<String>(roomType, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/getHotelNameByHotelId/{hotelId}", method = RequestMethod.GET)
	public ResponseEntity<String> getHotelNameByHotelId(@PathVariable int hotelId){
		String hotelName = hotelService.findHotelNameByHotelId(hotelId);
		return new  ResponseEntity<String>(hotelName, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getHotelsBySearchButton", method = RequestMethod.POST)
	public ResponseEntity<List<Hotel>> getHotelsBySearch(@RequestBody Map<String, List<Integer>> searchDetailMap) {
		
		String name = ""; 
		System.out.println("Inside SearchHotelMicroservice Controller "+ searchDetailMap);
		List<Integer> hotelIdsAvail = new ArrayList<>();
		for(Map.Entry<String, List<Integer>> entry: searchDetailMap.entrySet()) {
			name = entry.getKey(); //hotelName, state, or city
			hotelIdsAvail = entry.getValue();
		}
		
		System.out.println("Inside SearchHotelMicroservice Controller key: "+name+ ", values: " + hotelIdsAvail);		
		
		List<Hotel> listHotel = new ArrayList<>();
		if(hotelIdsAvail.isEmpty() ) {
			if(name != null && !name.isEmpty()) { //if only search by name
				listHotel= hotelService.findByHotelNameCityState(name);	
			}else {
				listHotel = hotelService.findAllHotels();
			}
		}else { //if search by checkin/out date and/or name
			
			for(Integer h:hotelIdsAvail) {				
				if(name != null && !name.isEmpty()) {
					if(name.equals(hotelService.findByHotelId(h).getHotelName())){
						listHotel= hotelService.findByHotelNameCityState(name);	
						break;
					}			
				}else {
					listHotel.add(hotelService.findByHotelId(h));
				}
			}
		}	
		return new  ResponseEntity<List<Hotel>>(listHotel, HttpStatus.OK);		
	}
	
	@RequestMapping(value = "/getRoomsBySearch", method = RequestMethod.POST)
	public ResponseEntity<List<Hotel>> getRoomsBySearch(@RequestBody Map<String, List<Object[]>> searchDetailMap) {
		
		String name = ""; 
		System.out.println("Inside SearchHotelMicroservice Controller "+ searchDetailMap);
		List<Object[]> roomIdsAvail = new ArrayList<>();
		for(Map.Entry<String, List<Object[]>> entry: searchDetailMap.entrySet()) {
			name = entry.getKey(); //hotelName, state, or city
			roomIdsAvail = entry.getValue();
		}
		
		//print out to console for troubleshoot
		for(Object[] r: roomIdsAvail) {
				int a = (int) r[0];
				int b = (int) r[1];
			System.out.println("Inside SearchHotelMicroservice Controller key: "+name+ ", values: " + a+", "+b);
		}
		
		List<Hotel> listHotel = new ArrayList<>();
		
		if(roomIdsAvail.isEmpty() && name != null && !name.isEmpty()) { //if only search by name
			listHotel= hotelService.findByHotelNameCityState(name);	
		
		}else { //if search by checkin/out date and/or name
			
			Map<String, List<Integer>> hotelIdRoomIdMap = new HashMap<>();
			for(Object[] obj:roomIdsAvail) {
				String hotelName = hotelService.findByHotelId((int)obj[0]).getHotelName();
				if(!hotelIdRoomIdMap.containsKey(hotelName)) {
					hotelIdRoomIdMap.put(hotelName, new ArrayList<Integer>((int)obj[1]));
				}else {
					hotelIdRoomIdMap.get(hotelName).add((int)obj[1]);
				}
			}
			
			for(String key:hotelIdRoomIdMap.keySet()) {				
				if(name != null && !name.isEmpty()) {
					if(name.equals(key)){
						listHotel= hotelService.findByHotelNameCityState(name);	
						break;
					}			
				}else {
					listHotel.add(hotelService.findByHotelName(key));
				}
			}
		}
		
		return new  ResponseEntity<List<Hotel>>(listHotel, HttpStatus.OK);		
	}
	
}

