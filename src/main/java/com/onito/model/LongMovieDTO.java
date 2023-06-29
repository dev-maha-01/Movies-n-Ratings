package com.onito.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class LongMovieDTO {
	
	private String tconst;
	private String primaryTitle;
	private Integer runtimeMinutes;
	private String genres;
	
	
	//manually writing the all args contructor to know the order of fields
	public LongMovieDTO(String tconst, String primaryTitle, Integer runtimeMinutes, String genres) {
		super();
		this.tconst = tconst;
		this.primaryTitle = primaryTitle;
		this.runtimeMinutes = runtimeMinutes;
		this.genres = genres;
	}
}
