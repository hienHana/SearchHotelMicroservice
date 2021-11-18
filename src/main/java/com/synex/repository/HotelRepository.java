package com.synex.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.synex.domain.Hotel;


public interface HotelRepository extends JpaRepository<Hotel, Integer> {

//	@Query(value = "select * from Hotels where hotelName=:#{#name} or city=:#{#name} or state=:#{#name}", nativeQuery = true)
//or
	@Query(value = "select * from hotels where hotelName=?1 or city=?1 or state=?1", nativeQuery = true)
	public List<Hotel> findByHotelNameCityState(String name);

	public Hotel findByHotelName(String name);

	public List<Hotel> findByCity(String name);

	public List<Hotel> findByState(String name);
	
	@Query(value = "select hotelId from hotels where hotelId = (select hotel_hotelId " + 
			"    from hotels_rooms where hotelrooms_roomId = ?1)", nativeQuery = true)
	public int findHotelIdByRoomId(int roomId);
	

}
