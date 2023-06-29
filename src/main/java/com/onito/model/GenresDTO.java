package com.onito.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GenresDTO {
	private String genres;
	private String primaryTitle;
	private Integer numVotes;
	public GenresDTO(String genres, String primaryTitle, Integer numVotes) {
		super();
		this.genres = genres;
		this.primaryTitle = primaryTitle;
		this.numVotes = numVotes;
	}
	
	
}
