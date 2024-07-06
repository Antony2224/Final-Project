package superheroes.and.villains.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Powers {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long powerId;
	
	private String powerName;
	private String powerRating;
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToMany(mappedBy = "powers")
	private Set<SuperHeroes> heroes = new HashSet<>();	
}
