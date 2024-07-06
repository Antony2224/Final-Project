package superheroes.and.villains.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import superheroes.and.villains.controller.model.CitiesData;
import superheroes.and.villains.controller.model.SuperHeroData;
import superheroes.and.villains.service.SuperheroesAndVillainsService;

@RestController
@RequestMapping("/superheroes_and_villains")
@Slf4j
public class HeroController {
	@Autowired
	private SuperheroesAndVillainsService superheroesAndVillainsService;

	@PostMapping("/city")
	@ResponseStatus(code = HttpStatus.CREATED)
	public CitiesData insertCity(@RequestBody CitiesData cityData) {
		log.info("Creating city {}", cityData);
		return superheroesAndVillainsService.saveCity(cityData);
	}

	@PutMapping("/cities/{cityId}")
	public CitiesData updateCity(@PathVariable Long cityId, @RequestBody CitiesData cityData) {
		cityData.setCityId(cityId);
		log.info("Updating city {}", cityData);
		return superheroesAndVillainsService.saveCity(cityData);
	}

	@GetMapping("/cities")
	public List<CitiesData> retrieveAllCities() {
		log.info("Retrieve all cities called.");
		return superheroesAndVillainsService.retrieveAllCities();
	}

	@GetMapping("/city/{cityId}")
	public CitiesData retrieveCityById(@PathVariable Long cityId) {
		log.info("Retreiving city with ID={}", cityId);
		return superheroesAndVillainsService.retrieveCityById(cityId);
	}

	@DeleteMapping("/cities")
	public void deletaAllCities() {
		log.info("Attempting to delete all cities");
		throw new UnsupportedOperationException("Deleting all Cities is not allowed");
	}

	@DeleteMapping("/cities/{cityId}")
	public Map<String, String> deleteCityById(@PathVariable Long cityId) {
		log.info("Deleting city with ID = {}", cityId);

		superheroesAndVillainsService.deleteCityById(cityId);

		return Map.of("message", "Deletion of city with ID =" + cityId + " was successful");
	}
	@PostMapping("/cities/{cityId}/Superhero")
	@ResponseStatus(code = HttpStatus.CREATED)
	public SuperHeroData insertSuperHero(@PathVariable Long cityId, @RequestBody SuperHeroData superHeroData) {
		
		log.info("Creating Superhero {} for city with ID={}", cityId, superHeroData);
		return superheroesAndVillainsService.saveSuperhero(cityId, superHeroData);
	}
	
	@PutMapping("/cities/{cityId}/Superhero/{superheroId}")
	public SuperHeroData insertSuperHero(@PathVariable Long cityId, @PathVariable Long superheroId, @RequestBody SuperHeroData superHeroData) {
		superHeroData.setSuperheroId(superheroId);
		
		log.info("Creating Superhero {} for city with ID={}", cityId, superHeroData);
		return superheroesAndVillainsService.saveSuperhero(cityId, superHeroData);
	}
	@GetMapping("/cities/{cityId}/Superhero/{superheroId}")
	public SuperHeroData retrieveSuperheroById(@PathVariable Long cityId,
			@PathVariable Long superheroId) {
		log.info("Retrieving supehero with ID={} for city with ID={}" ,superheroId, cityId );
		
		return superheroesAndVillainsService.retrieveSuperheroById(cityId, superheroId);
	}
}
