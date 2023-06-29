package com.onito.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.onito.exception.MoviesException;
import com.onito.model.GenreWithSubtotalDTO;
import com.onito.model.LongMovieDTO;
import com.onito.model.Movies;
import com.onito.model.TopRatedMovieDTO;

@Service
public interface MoviesService {
	public List<LongMovieDTO> getLongestDurationMovies() throws MoviesException;
	public String registerMovie(Movies mov) throws MoviesException;
	public List<TopRatedMovieDTO> getTopRatedMovies() throws MoviesException;
	public String updateRuntime() throws MoviesException;
	public List<GenreWithSubtotalDTO> getAllMoviesByGenres() throws MoviesException;
	public String saveListOfMovies(List<Movies> movList);
	public int registerMovieWithNativeQuery(String tconst,String titleType,String primaryTitle,Integer runtimeMinutes,String genres);
}
