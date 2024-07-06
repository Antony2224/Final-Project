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
public class CitiesData {

	private Long cityId;
	private String cityName;
	private Set<SuperHeroResponse> heroes = new HashSet<>();

	public CitiesData(Cities city) {
		// TODO Auto-generated constructor stub
		cityId = city.getCityId();
		cityName = city.getCityName();
		
		for(SuperHeroes hero : city.getHeroes()) {
			heroes.add(new SuperHeroResponse(hero));
		}
	}

	@Data
	@NoArgsConstructor
	static class SuperHeroResponse {
		
		private Long superheroId;
		private String name;
		private String secretIdentity;
		private int numSidekicks;
		private Set<String> powers = new HashSet<>();

		public SuperHeroResponse(SuperHeroes hero) {
			// TODO Auto-generated constructor stub
			superheroId = hero.getSuperheroId();
			name = hero.getName();
			secretIdentity = hero.getSecretIdentity();
			numSidekicks = hero.getNumSidekicks();
			
			for(Powers power : hero.getPowers()) {
				powers.add(power.getPowerName());
			}
		}
	}
}
