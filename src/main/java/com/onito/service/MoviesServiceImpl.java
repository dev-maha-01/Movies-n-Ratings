package com.onito.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.onito.exception.MoviesException;
import com.onito.model.GenreWithSubtotalDTO;
import com.onito.model.GenresDTO;
import com.onito.model.LongMovieDTO;
import com.onito.model.Movies;
import com.onito.model.Ratings;
import com.onito.model.TopRatedMovieDTO;
import com.onito.repository.MoviesRepo;

@Service
public class MoviesServiceImpl implements MoviesService{

	@Autowired
	private MoviesRepo mRepo;
	
	@Override
	public List<LongMovieDTO> getLongestDurationMovies() throws MoviesException {
		List<LongMovieDTO> dotList=mRepo.getLongMovies();
		
		if(dotList.isEmpty()) throw new MoviesException("No Movies Exists !!!");
		
		return dotList;
	}

	@Override
	public String registerMovie(Movies mov) throws MoviesException {
		
		//checks for duplicate entry
		Optional<Movies> opt=mRepo.findById(mov.getTconst());
		if(opt.isPresent()) throw new MoviesException("Movie with tconst="+mov.getTconst()+" already exists !!!");
		
		//as the rating is a field add a new rating obj
		Ratings rating=new Ratings();
		rating.setTconst(mov.getTconst());
		
		//add rating to movie
		mov.setRatings(rating);
		
		mRepo.save(mov);
		
		return "success";
	}

	@Override
	public List<TopRatedMovieDTO> getTopRatedMovies() throws MoviesException {
		List<TopRatedMovieDTO> topList=mRepo.getTopRatedMovies();
		return topList;
	}

	@Override
	public String updateRuntime() throws MoviesException {
		mRepo.updateRuntime();
		return "runtime updated Successfully.";
	}

	@Override
	public List<GenreWithSubtotalDTO> getAllMoviesByGenres() throws MoviesException {
		
		//get the subtotal with jpql qquery
		List<GenreWithSubtotalDTO> genresWithSubtotal=mRepo.getGenreSubTotal();
		
		if(genresWithSubtotal.isEmpty()) throw new MoviesException("No Genre Exists !!!");
		
		//takes the list of all movies
		List<GenresDTO> dtoList=mRepo.getAllMoviesByGenre();
		
		if(dtoList.isEmpty()) throw new MoviesException("No Movies Exists !!!");
		
		//maps them in hashmap
		Map<String,GenreWithSubtotalDTO> map=new HashMap<>();
		for(GenreWithSubtotalDTO gs : genresWithSubtotal) {
			map.put(gs.getGenres(), gs);
		}
		
		//adds the respective genre movie in its object
		for(GenresDTO dto : dtoList) {
			if(map.containsKey(dto.getGenres())) {
			map.get(dto.getGenres()).getDtoList().add(dto);
			}
		}
		
		return genresWithSubtotal;
	}
	
	@Override
	public String saveListOfMovies(List<Movies> movList) {
		mRepo.saveAll(movList);
		//mRepo.saveAllAndFlush(movList);
		return "saved Successfully";
	}

	@Override
	public int registerMovieWithNativeQuery(String tconst, String titleType, String primaryTitle,
			Integer runtimeMinutes, String genres) {
		int val=mRepo.saveMovieByNativeQuery(tconst, titleType, primaryTitle, runtimeMinutes, genres);
		return val;
	}
	

}
