package com.synex.controller;


import java.util.List;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.synex.domain.AmenitiesHotel;
import com.synex.domain.AmenitiesRoom;
import com.synex.domain.Hotel;
import com.synex.domain.Room;
import com.synex.domain.RoomType;
import com.synex.repository.AmenitiesHotelRepo;
import com.synex.repository.AmenitiesRoomRepo;
import com.synex.repository.RoomTypeRepo;
import com.synex.services.HotelService;
import com.synex.services.RoomService;

@CrossOrigin
@RestController
public class CreateResourcesController {
	@Autowired HotelService hotelService;
	@Autowired RoomService roomService;
	@Autowired AmenitiesHotelRepo amenHotelRepo;
	@Autowired AmenitiesRoomRepo amenRoomRepo;
	@Autowired RoomTypeRepo roomTypeRepo;
	
//	@RequestMapping(value="hotelForm")
//	public String createHotelForm( Model model) {
//		List<AmenitiesHotel> amenHotelList = new ArrayList<>();
//		amenHotelList = amenHotelRepo.findAll();
//		Map<Integer, String> amenHotelMap = new LinkedHashMap<>();
//		for(AmenitiesHotel a:amenHotelList) {
//			amenHotelMap.put(a.getAmenityHotelId(), a.getAmenityHotel());
//		}
//		model.addAttribute("amenitiesHotel", amenHotelMap);
//		return "hotelForm";
//	}
//	
//	@RequestMapping(value = "/createHotel", method = RequestMethod.POST)
//	public String createHotel(@Valid @ModelAttribute Hotel hotel, BindingResult br, Model model) {
//		if(!br.hasErrors()) {
//			hotelService.saveHotel(hotel);
//			return "hotelForm";
//		}
//		return "hotelForm";
//	}
	
/////////////////////////////////////////////////////////////////////////////////////	
	
	  @RequestMapping(value = "/createHotel", method = RequestMethod.POST) public
	  ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel) {
	  hotelService.saveHotel(hotel); return new ResponseEntity<Hotel>(hotel,
	  HttpStatus.OK); }
	  
	  @RequestMapping(value = "/getHotelAmenities", method = RequestMethod.GET)
	  public ResponseEntity<List<AmenitiesHotel>> getHotelAmenities() {
	  List<AmenitiesHotel> amenHotelList = amenHotelRepo.findAll(); return new
	  ResponseEntity<List<AmenitiesHotel>>(amenHotelList , HttpStatus.OK); }
	  
	  @RequestMapping(value = "/createRoom", method = RequestMethod.POST) public
	  ResponseEntity<Room> createRoom(@RequestBody Room room) {
	  roomService.saveRoom(room); return new ResponseEntity<Room>(room,
	  HttpStatus.OK); }
	  
	  @RequestMapping(value = "/getRoomType", method = RequestMethod.GET) public
	  ResponseEntity<List<RoomType>> getRoomType() { List<RoomType> roomTypes =
	  roomTypeRepo.findAll(); return new ResponseEntity<List<RoomType>>(roomTypes,
	  HttpStatus.OK); }
	  
	  @RequestMapping(value = "/getRoomAmenities", method = RequestMethod.GET)
	  public ResponseEntity<List<AmenitiesRoom>> getRoomAmenities() {
	  List<AmenitiesRoom> amenRoomList = amenRoomRepo.findAll(); return new
	  ResponseEntity<List<AmenitiesRoom>>(amenRoomList , HttpStatus.OK); }
	 
////////////////////////////////////////////////////////////////////////////////////////////////	
	
	
//	@RequestMapping(value = "/getRoomType", method = RequestMethod.GET)
//	public ResponseEntity<List<RoomType>> getRoomType() {
//		List<RoomType> roomTypes = roomTypeRepo.findAll();
//		System.out.println("&&&&&&&"+ roomTypes);
//		return  new  ResponseEntity<List<RoomType>>(roomTypes, HttpStatus.OK);
//	}
	
	
//	@RequestMapping(value="roomForm")
//	public String createRoomForm( Model model) {
//		List<AmenitiesRoom> amenRoomList = new ArrayList<>();
//		amenRoomList = amenRoomRepo.findAll();
//		Map<Integer, String> amenRoomMap = new LinkedHashMap<>();
//		for(AmenitiesRoom a:amenRoomList) {
//			amenRoomMap.put(a.getAmenityRoomId(), a.getAmenityRoom());
//		}
//		model.addAttribute("amenitiesRoom", amenRoomMap);
//		
//		List<RoomType> roomTypeList = new ArrayList<>();
//		roomTypeList = roomTypeRepo.findAll();
//		Map<Integer, String> roomTypeMap = new LinkedHashMap<>();
//		for(RoomType a:roomTypeList) {
//			roomTypeMap.put(a.getRoomTypeId(), a.getTypeRoom());
//		}
//		model.addAttribute("roomTypes", roomTypeMap);
//		return "roomForm";
//	}
//	
//	@RequestMapping(value = "/createRoom", method = RequestMethod.POST)
//	public String createRoom(@Valid @ModelAttribute Room room, BindingResult br, Model model) {
//		if(!br.hasErrors()) {
//			roomService.saveRoom(room);
//			return "roomForm";
//		}
//		return "roomForm";
//	}
	
}
