package com.onito.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.onito.model.GenresDTO;
import com.onito.model.LongMovieDTO;
import com.onito.model.Movies;
import com.onito.model.TopRatedMovieDTO;
import com.onito.model.GenreWithSubtotalDTO;

@Repository
public interface MoviesRepo extends JpaRepository<Movies, String>{
	
	@Query("select new com.onito.model.LongMovieDTO(mov.tconst,mov.primaryTitle,mov.runtimeMinutes,mov.genres) from Movies mov where mov.titleType='movie' order by mov.runtimeMinutes desc limit 10")
	public List<LongMovieDTO> getLongMovies();
	
	@Query("select new com.onito.model.TopRatedMovieDTO(mov.tconst,mov.primaryTitle,mov.genres,rat.averageRating) from Movies mov join mov.ratings rat where mov.tconst=rat.tconst and rat.averageRating>6.0 order by rat.averageRating")
	public List<TopRatedMovieDTO> getTopRatedMovies();
	
	@org.springframework.data.jpa.repository.Modifying
	@Query("update Movies mov set mov.runtimeMinutes=mov.runtimeMinutes +(case when mov.genres='Documentary' then 15 when mov.genres='Animation' then 30 else 45 end)")
	public int updateRuntime();
	
	@Query("select new com.onito.model.GenresDTO(mov.genres,mov.primaryTitle,rat.numVotes) from Movies mov join mov.ratings rat where mov.tconst=rat.tconst ")
	public List<GenresDTO> getAllMoviesByGenre();
	
	@Query("select new com.onito.model.GenreWithSubtotalDTO(mov.genres,sum(rat.numVotes)) from Movies mov join Ratings rat on mov.tconst=rat.tconst group by mov.genres")
	public List<GenreWithSubtotalDTO> getGenreSubTotal();
	
	@org.springframework.data.jpa.repository.Modifying
	@Query(value="insert into movies (tconst,title_type,primary_title,runtime_minutes,genres) values (?1,?2,?3,?4,?5)",nativeQuery = true)
	public int saveMovieByNativeQuery(String tconst,String titleType,String primaryTitle,Integer runtimeMinutes,String genres);
}
