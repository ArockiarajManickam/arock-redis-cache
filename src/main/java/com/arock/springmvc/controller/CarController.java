package com.arock.springmvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.arock.core.service.impl.CarServiceImpl;

/**
 * Controller to get the Car List.
 * 
 * @author Arockiaraj Manickam
 *
 */

@RestController

public class CarController {

	@Autowired

	private CarServiceImpl service;

	@Autowired
	CacheManager cacheManager;

	@GetMapping( "/carlist/{cardetails}"
			// produces = { "application/json;charset=UTF-8" },
			// consumes = { "application/json;charset=UTF-8" },
			)

	public ResponseEntity<String> getcardetails(@PathVariable("cardetails") String cardetails) {
		long s = System.currentTimeMillis();
		String response = service.getCarsFromCache(cardetails);

		long s1 = System.currentTimeMillis() - s;

		String responsewithtime = "Response time in millisecs : " + s1 + "   " + response;

		System.out.println(s1);

		// final String response= "great";
		return new ResponseEntity<String>(responsewithtime, HttpStatus.OK);
	}

	// @Scheduled(fixedRate = 5000)
	// public void evictAllcachesAtIntervals() {
	// evictAllCaches();
	// System.out.print("cache");
	// }
	//

	@RequestMapping(value = "/evictcache",
			// produces = { "application/json;charset=UTF-8" },
			// consumes = { "application/json;charset=UTF-8" },
			method = RequestMethod.GET)
	public ResponseEntity<String> evictAllcachesAtIntervals() {
		evictAllCaches();

		return new ResponseEntity<String>("cache cleared", HttpStatus.OK);

	}

	public void evictAllCaches() {
		cacheManager.getCacheNames().stream().forEach(cacheName -> cacheManager.getCache(cacheName).clear());

		cacheManager.getCacheNames().stream().forEach(cacheName -> System.out.println("cachename" + cacheName));
	}
}
