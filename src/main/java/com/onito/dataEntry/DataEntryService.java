package com.onito.dataEntry;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import com.onito.exception.MoviesException;
import com.onito.exception.RatingException;

public interface DataEntryService {
	public String moviesDataEntry(String path) throws FileNotFoundException,IOException, MoviesException;
	public String ratingsDataEntry(String path) throws FileNotFoundException,IOException,  RatingException;
	public List<String[]> readEntireFile(String path) throws FileNotFoundException,IOException;
}
