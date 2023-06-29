package com.onito.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Ratings {
	@Id
	@NotEmpty(message="titleType cannot be empty")
	private String tconst;
	
	//reason not to add validation is because movies can have no votes and no rating
	private Double averageRating=0.0;
	private Integer numVotes=0;
	public Ratings(@NotEmpty(message = "titleType cannot be empty") String tconst, Double averageRating,
			Integer numVotes) {
		super();
		this.tconst = tconst;
		this.averageRating = averageRating;
		this.numVotes = numVotes;
	}
	
	
}
