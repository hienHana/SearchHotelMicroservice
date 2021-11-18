package com.synex.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.synex.domain.Room;

public interface RoomRepository extends JpaRepository<Room, Integer> {
	
	@Query(value = "select * from rooms where roomId in " + 
			"(select hotelrooms_roomId from hotels_rooms where hotel_hotelId=?1)", nativeQuery=true)
	public List<Room> getRoomsByHotelId(int hotelId);
	
//	@Query(value = "select hotelId, count(*) from Room group by hotelId", nativeQuery = true)
//	public List<Object[]> findTotalRoomsPerHotel();
}
