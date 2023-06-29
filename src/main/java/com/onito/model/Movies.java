package com.onito.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Movies {
	@Id
	@NotEmpty(message="tconst cannot be empty.")
	@Column(name="tconst")
	private String tconst;
	
	@NotEmpty(message="titleType cannot be empty")
	private String titleType;
	
	@NotEmpty(message="primaryTitle cannot be empty")
	private String primaryTitle;
	
	@NotNull(message="runtimeMinute cannot be null")
	@Min(value=0,message="Runtime must be greater than 0")
	private Integer runtimeMinutes;
	
	@NotEmpty(message="genre cannot be empty")
	private String genres;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="tconst")
	private Ratings ratings;

	public Movies(@NotEmpty(message = "tconst cannot be empty.") String tconst,
			@NotEmpty(message = "titleType cannot be empty") String titleType,
			@NotEmpty(message = "primaryTitle cannot be empty") String primaryTitle,
			@NotNull(message = "runtimeMinute cannot be null") @Min(value = 1, message = "Runtime must be greater than 0") Integer runtimeMinutes,
			@NotEmpty(message = "genre cannot be empty") String genres) {
		super();
		this.tconst = tconst;
		this.titleType = titleType;
		this.primaryTitle = primaryTitle;
		this.runtimeMinutes = runtimeMinutes;
		this.genres = genres;
	}
	
	
}
