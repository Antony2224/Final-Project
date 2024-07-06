package superheroes.and.villains.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import superheroes.and.villains.entity.SuperHeroes;

public interface SuperheroDao extends JpaRepository<SuperHeroes, Long> {

}
