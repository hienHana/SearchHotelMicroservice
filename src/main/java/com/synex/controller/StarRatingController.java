package com.synex.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.synex.services.HotelService;


@CrossOrigin
@RestController
public class StarRatingController {
	@Autowired HotelService hotelService;
	
	@RequestMapping(value = "/updateHotelRating", method = RequestMethod.POST)
	public ResponseEntity<String> updateHotelRating(@RequestBody Map<Integer, Integer> avgStarsPerRoom){
		Map<Integer, Integer[]> avgStarsPerHotel = new HashMap<>(); //key: hotelId, value:[starRating, how many same hotel count]
		System.out.println("avgStarsPerRoom: "+avgStarsPerRoom);
		for(Integer key:avgStarsPerRoom.keySet()) { //key:roomId, value:starAvg per roomId
			int hotelId = hotelService.findHotelIdByRoomId(key);
			if(!avgStarsPerHotel.containsKey(hotelId)) {
				avgStarsPerHotel.put(hotelId,  new Integer[] {avgStarsPerRoom.get(key), 1});
			}else {
				Integer[] arr = avgStarsPerHotel.get(hotelId);
				arr[0] = arr[0] + avgStarsPerRoom.get(key);
				arr[1] += 1;				
			}
		}
			
		System.out.println("avgStarsPerHotel: "+avgStarsPerHotel);
		for(Integer key:avgStarsPerHotel.keySet()) {//key:hotelId
			Integer[] arr = avgStarsPerHotel.get(key);
			int avg = 0;
			if(arr[1] != 0) {
				avg = arr[0]/arr[1];
			}
			if(key != null) {
				hotelService.updateHotelRate(key, avg);
			}
		}
		
		return new  ResponseEntity<String>("Updated", HttpStatus.OK);
	}
}