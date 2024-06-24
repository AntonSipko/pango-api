package pango.parking.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pango.parking.service.PangoParkingService;

@RestController
@RequestMapping("/pango-api")
public class PangoParkingController {
	@Autowired
	private PangoParkingService pangoService;
	@GetMapping("/check-car/{carNumber}")
	public Map<Long,String>checkParking(@PathVariable Long carNumber){
		return pangoService.checkParking(carNumber);
	}
	

}
