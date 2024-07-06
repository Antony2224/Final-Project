package superheroes.and.villains.dao;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import superheroes.and.villains.entity.Powers;

public interface PowersDao extends JpaRepository<Powers, Long> {

	Set<Powers> findAllByPowerNameIn(Set<String> powers);
	
}
