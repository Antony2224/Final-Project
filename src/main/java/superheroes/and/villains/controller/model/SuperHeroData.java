package superheroes.and.villains.controller.model;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import lombok.NoArgsConstructor;
import superheroes.and.villains.entity.Cities;
import superheroes.and.villains.entity.Powers;
import superheroes.and.villains.entity.SuperHeroes;
@Data
@NoArgsConstructor
public class SuperHeroData {
	private Long superheroId;
	private String name;
	private String secretIdentity;
	private int numSidekicks;
	private SuperCities city;
	private Set<String> powers = new HashSet<>();
	
	public SuperHeroData(SuperHeroes hero) {
		superheroId = hero.getSuperheroId();
		name = hero.getName();
		secretIdentity = hero.getSecretIdentity();
		numSidekicks = hero.getNumSidekicks();
		city = new SuperCities(hero.getCity());
		
		for(Powers power: hero.getPowers()) {
			powers.add(power.getPowerName());
		}
	}
	
	@Data
	@NoArgsConstructor
	public static class SuperCities {
		private Long cityId;
		private String cityName;
		
		public SuperCities(Cities city) {
			cityId = city.getCityId();
			cityName = city.getCityName();
		}
	}
}
