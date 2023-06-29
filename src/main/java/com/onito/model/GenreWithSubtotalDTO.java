package com.onito.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GenreWithSubtotalDTO {
	private String genres;
	private List<GenresDTO>dtoList=new ArrayList<>();
	private Long total;
	public GenreWithSubtotalDTO(String genres, Long total) {
		super();
		this.genres = genres;
		this.total = total;
	}
	
	
}
