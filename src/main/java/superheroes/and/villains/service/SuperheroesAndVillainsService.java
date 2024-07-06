package superheroes.and.villains.service;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import superheroes.and.villains.controller.model.CitiesData;
import superheroes.and.villains.controller.model.SuperHeroData;
import superheroes.and.villains.dao.CitiesDao;
import superheroes.and.villains.dao.PowersDao;
import superheroes.and.villains.dao.SuperheroDao;
import superheroes.and.villains.entity.Cities;
import superheroes.and.villains.entity.Powers;
import superheroes.and.villains.entity.SuperHeroes;

@Service
public class SuperheroesAndVillainsService {

	@Autowired
	private CitiesDao cityDao;

	@Autowired
	private PowersDao powerDao;

	@Autowired
	private SuperheroDao heroDao;

	@Transactional(readOnly = false)
	public CitiesData saveCity(CitiesData cityData) {
		// TODO Auto-generated method stub
		Long cityId = cityData.getCityId();
		Cities city = findOrCreateCity(cityId);

		setFieldsInCity(city, cityData);
		return new CitiesData(cityDao.save(city));
	}

	private void setFieldsInCity(Cities city, CitiesData cityData) {
		// TODO Auto-generated method stub
		city.setCityName(cityData.getCityName());
	}

	private Cities findOrCreateCity(Long cityId) {
		Cities city;

		if (Objects.isNull(cityId)) {
			city = new Cities();
		} else {
			city = findCityById(cityId);
		}
		return city;
	}

	private Cities findCityById(Long cityId) {
		// TODO Auto-generated method stub
		return cityDao.findById(cityId)
				.orElseThrow(() -> new NoSuchElementException("City with ID=" + cityId + " was not found."));
	}

	@Transactional(readOnly = true)
	public List<CitiesData> retrieveAllCities() {
		// TODO Auto-generated method stub
		List<Cities> cities = cityDao.findAll();
		List<CitiesData> response = new LinkedList<>();

		for (Cities city : cities) {
			response.add(new CitiesData(city));
		}

		return response;
	}

	@Transactional(readOnly = true)
	public CitiesData retrieveCityById(Long cityId) {
		// TODO Auto-generated method stub
		Cities city = findCityById(cityId);
		return new CitiesData(city);
	}

	@Transactional(readOnly = false)
	public void deleteCityById(Long cityId) {
		// TODO Auto-generated method stub
		Cities city = findCityById(cityId);
		cityDao.delete(city);
	}

	@Transactional(readOnly = false)
	public SuperHeroData saveSuperhero(Long cityId, SuperHeroData superHeroData) {
		// TODO Auto-generated method stub
		Cities city = findCityById(cityId);

		Set<Powers> powers = powerDao.findAllByPowerNameIn(superHeroData.getPowers());

		SuperHeroes hero = findOrCreateSuperHero(superHeroData.getSuperheroId());
		setSuperheroFields(hero, superHeroData);
		
		hero.setCity(city);
		city.getHeroes().add(hero);
		
		for(Powers power : powers) {
			power.getHeroes().add(hero);
			hero.getPowers().add(power);
		}
		SuperHeroes dbHero = heroDao.save(hero);
		return new SuperHeroData(dbHero);
	}

	private void setSuperheroFields(SuperHeroes hero, SuperHeroData superHeroData) {
		// TODO Auto-generated method stub
		hero.setName(superHeroData.getName());
		hero.setSecretIdentity(superHeroData.getSecretIdentity());
		hero.setNumSidekicks(superHeroData.getNumSidekicks());
		hero.setSuperheroId(superHeroData.getSuperheroId());
	}

	private SuperHeroes findOrCreateSuperHero(Long superheroId) {
		// TODO Auto-generated method stub
		SuperHeroes hero;

		if (Objects.isNull(superheroId)) {
			hero = new SuperHeroes();
		} else {
			hero = findSuperheroById(superheroId);
		}

		return hero;
	}

	private SuperHeroes findSuperheroById(Long superheroId) {
		// TODO Auto-generated method stub
		return heroDao.findById(superheroId).orElseThrow(() -> new NoSuchElementException("Superhero with ID=" + superheroId + " does not exist"));
	}
	@Transactional(readOnly = true)
	public SuperHeroData retrieveSuperheroById(Long cityId, Long superheroId) {
		// TODO Auto-generated method stub
		findCityById(cityId);
		SuperHeroes superhero = findSuperheroById(superheroId);
		
		if(superhero.getCity().getCityId() != cityId) {
			throw new IllegalStateException("Superhero with ID=" + superheroId +"does not opperate in city with ID=" + cityId);
		}
		
		return new SuperHeroData(superhero);
	}

}
