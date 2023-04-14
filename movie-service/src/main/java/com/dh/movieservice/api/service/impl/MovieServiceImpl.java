package com.dh.movieservice.api.service.impl;

import com.dh.movieservice.api.service.MovieService;
import com.dh.movieservice.domain.model.Movie;
import com.dh.movieservice.domain.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class MovieServiceImpl implements MovieService {
	private MovieRepository movieRepository;

	@Autowired
	public MovieServiceImpl(MovieRepository movieRepository) {
		this.movieRepository = movieRepository;
	}

	@Override
	public List<Movie> getListByGenre(String genre, Integer faultPercentage) {
//		return movieRepository.findAllByGenre(genre);
		List<Movie> movieList = movieRepository.findAllByGenre(genre);
		return throwErrorIfBadLuck(movieList, faultPercentage);
	}

	@Override
	public Movie save(Movie movie) {
		return movieRepository.save(movie);
	}

	private List<Movie> throwErrorIfBadLuck(
			List<Movie> entity, int faultPercent) {

		if (faultPercent == 0) {
			return entity;
		}

		Integer randomThreshold = getRandomNumber(1, 100);

		if (faultPercent < randomThreshold) {
			System.out.format("We got lucky, no error occurred, %d < %d",faultPercent, randomThreshold);

		} else {
			System.out.format("Bad luck, an error occurred, %d >= %d",
					faultPercent, randomThreshold);

			throw new RuntimeException("Something went wrong...");
		}

		return entity;
	}

	private Integer getRandomNumber(Integer min, Integer max) {

		if (max < min) {
			throw new IllegalArgumentException("Max must be greater than min");
		}


		return (int) (Math.random() * ((max - min) + 1)) + min;
	}

}
