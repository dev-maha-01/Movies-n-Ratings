package com.onito.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class TopRatedMovieDTO {
	private String tconst;
	private String primaryTitle;
	private String genres;
	private Double averageRating;
	
	
	public TopRatedMovieDTO(String tconst, String primaryTitle, String genres, Double averageRating) {
		super();
		this.tconst = tconst;
		this.primaryTitle = primaryTitle;
		this.genres = genres;
		this.averageRating = averageRating;
	}
	
	
}
