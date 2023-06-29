package com.onito.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.onito.dataEntry.DataEntryService;
import com.onito.exception.MoviesException;
import com.onito.model.GenreWithSubtotalDTO;
import com.onito.model.LongMovieDTO;
import com.onito.model.Movies;
import com.onito.model.TopRatedMovieDTO;
import com.onito.service.MoviesService;

import jakarta.validation.Valid;

@RestController
public class MoviesController {
	
	@Autowired
	private MoviesService mServ;
	
	@Autowired
	private DataEntryService dServ;
	
	@GetMapping("/api/v1/longest-duration-movies")
	public ResponseEntity<List<LongMovieDTO>> getLongestMovies() throws MoviesException{
		List<LongMovieDTO> dtoList=mServ.getLongestDurationMovies();
		
		return new ResponseEntity<>(dtoList,HttpStatus.OK);
	}
	
	@PostMapping("/api/v1/new-movie")
	public ResponseEntity<String> registerMovie(@Valid @RequestBody Movies mov) throws MoviesException{
		String message=mServ.registerMovie(mov);
		return new ResponseEntity<String>(message,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/api/v1/top-rated-movies")
	public ResponseEntity<List<TopRatedMovieDTO>> getTopRatedMovies() throws MoviesException{
		List<TopRatedMovieDTO> topList=mServ.getTopRatedMovies();
		return new ResponseEntity<>(topList,HttpStatus.OK);
	}
	
	@PostMapping("/api/v1/update-runtime-minutes")
	public ResponseEntity<String> updatRuntime() throws MoviesException{
		String message=mServ.updateRuntime();
		return new ResponseEntity<>(message,HttpStatus.OK);
	}
	
	@GetMapping("/api/v1/genre-movies-with-subtotals")
	public ResponseEntity<List<GenreWithSubtotalDTO>> getAllMoviesByGenre() throws MoviesException{
		List<GenreWithSubtotalDTO> dtoList=mServ.getAllMoviesByGenres();
		return new ResponseEntity<>(dtoList, HttpStatus.OK);
	}
	
	@PostMapping("/api/v1/initiate-data-entry/movies")
	public ResponseEntity<String> initiateMoviesDataEntry(@RequestParam("path") String path) throws Exception{
		String message=dServ.moviesDataEntry(path);
		return new ResponseEntity<String>(message, HttpStatus.CREATED);
	}
	
	@PostMapping("/api/v1/initiate-data-entry/ratings")
	public ResponseEntity<String> initiateRatingsDataEntry(@RequestParam("path") String path) throws Exception{
		String message=dServ.ratingsDataEntry(path);
		return new ResponseEntity<String>(message, HttpStatus.CREATED);
	}
}
