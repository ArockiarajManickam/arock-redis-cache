package com.arock.core.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.Cache;
import org.springframework.cache.Cache.ValueWrapper;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

/**
 * Car Service Implementation.<BR>
 * .
 * 
 * @author Arockiaraj Manickam
 */
@Service

public class CarServiceImpl {

	@Autowired
	private ObjectMapper objmapper;

	/**
	 * get Car details from AWS REDIS cache.
	 */

	@Cacheable(value = "cars")
	public String getCarsFromCache(String carName) {
		
		System.out.println("no cache");
		try {

			carName = objmapper.writeValueAsString(getCars());

		}

		catch (Exception e) {

			e.printStackTrace();

		}

		return carName;
	}

	public List<Car> getCars() {

		List<Car> cars = new ArrayList();

		for (int i = 1; i <= 3000; i++) {
			Car car = new Car();
			car.setName("car" + i);
			car.setDescription("This is" + i + "car");
			cars.add(car);
		}
		return cars;

	}

}