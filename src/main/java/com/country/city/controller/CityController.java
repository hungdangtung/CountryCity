package com.country.city.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.country.city.entity.City;
import com.country.city.intface.CityRepository;

@RestController
@RequestMapping("/api")
public class CityController {
	private CityRepository cityRepository;
	
	@GetMapping("/cities")
	public List<City> getAllCities() {
		return cityRepository.findAll();
	}
	
	@PostMapping("/cities")
	public City createCity(@Valid @RequestBody City city) {
		return cityRepository.save(city);
	}
	
	@GetMapping("/cities/{id}")
	public ResponseEntity<City> getCityById(@PathVariable(value = "id") Long cityID) {
		City city = cityRepository.findOne(cityID);
		
		if (city == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok().body(city);
	}
	
	@PutMapping("cities/{id}")
	public ResponseEntity<City> updateCity(@PathVariable(value = "id") Long cityId, @Valid @RequestBody City cityDetails) {
		City city = cityRepository.findOne(cityId);
		
		if (city == null) {
			return ResponseEntity.notFound().build();
		}
		
		city.setName(cityDetails.getName());
		
		City updateCity = cityRepository.save(city);
		
		return ResponseEntity.ok(updateCity);
	}
	
	@DeleteMapping("/cities/{id}")
	public ResponseEntity<City> deleteCity(@PathVariable(value = "id") Long cityId) {
		City city = cityRepository.findOne(cityId);
		if (city == null) {
			return ResponseEntity.notFound().build();
		}
		
		cityRepository.delete(city);
		
		return ResponseEntity.ok().build();
	}
}
