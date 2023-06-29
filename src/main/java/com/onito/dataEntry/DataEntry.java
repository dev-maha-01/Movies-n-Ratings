package com.onito.dataEntry;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.onito.exception.MoviesException;
import com.onito.exception.RatingException;
import com.onito.model.Movies;
import com.onito.model.Ratings;
import com.onito.service.MoviesService;
import com.onito.service.RatingsService;

@Service
public class DataEntry implements DataEntryService {
	@Autowired
	private MoviesService mServ;
	@Autowired
	private RatingsService rServ;
	
	@Override
	public String moviesDataEntry(String path) throws FileNotFoundException,IOException, MoviesException{
		System.out.println("inside the ratings data entry");
		List<String[]>allLines=readEntireFile(path);
		
		List<Movies>movList=new ArrayList<>();
		for(int i=1;i<allLines.size();i++) {
			String[] rows=allLines.get(i);
			Movies mov=new Movies(rows[0],rows[1],rows[2],Integer.valueOf(rows[3]),rows[4]);
			movList.add(mov);
		}
		
		
		mServ.saveListOfMovies(movList);
		
		return "Movies Data entered successully..";
	}
	
	@Override
	public String ratingsDataEntry(String path) throws FileNotFoundException,IOException,  RatingException{
		System.out.println("inside the ratings data entry");
		List<String[]>allLines=readEntireFile(path);
		
		List<Ratings>ratList=new ArrayList<>();
		for(int i=1;i<allLines.size();i++) {
			String[] rows=allLines.get(i);
			Ratings rat=new Ratings(rows[0],Double.valueOf(rows[1]),Integer.valueOf(rows[2]));
			System.out.println("creating rating entity=="+rows[0]+" "+rows[1]+" "+rows[2]);
			ratList.add(rat);
		}
		
		rServ.registerListOfRatings(ratList);
		
		return "Ratings Data entered successully..";
	}
	
	@Override
	public List<String[]> readEntireFile(String path) throws FileNotFoundException,IOException{
		//uses buffered reader
		FileReader fr=new FileReader(path);
		BufferedReader br=new BufferedReader(fr);
		
		List<String[]> allLines=new ArrayList<>();
		
		//reades all the lines
		String line=br.readLine();
		while(line!=null) {
			System.out.println("reading line=="+line);
			allLines.add(line.split(","));
			line=br.readLine();
		}
		br.close();
		return allLines;
	}
}
