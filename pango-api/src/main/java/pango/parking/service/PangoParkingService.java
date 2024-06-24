package pango.parking.service;

import java.time.LocalDateTime;
import java.util.Map;

public interface PangoParkingService {
	Map<Long,String>checkParking(Long carNumber);

}
