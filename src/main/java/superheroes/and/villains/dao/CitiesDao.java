package superheroes.and.villains.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import superheroes.and.villains.entity.Cities;

public interface CitiesDao extends JpaRepository<Cities, Long> {

}
