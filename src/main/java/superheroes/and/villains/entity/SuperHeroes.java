package superheroes.and.villains.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
public class SuperHeroes {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long superheroId;
	private String name;
	private String secretIdentity;
	private int numSidekicks;
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "city_id")
	private Cities city;
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "superheroes_powers",
			joinColumns = @JoinColumn(name = "superhero_id"),
			inverseJoinColumns = @JoinColumn(name = "power_id"))
	private Set<Powers> powers = new HashSet<>();
	
	
}
