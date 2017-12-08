package com.lmig.gfc.wimp.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.lmig.gfc.wimp.models.Movie;
import com.lmig.gfc.wimp.repositories.MovieRepository;

@RestController
@RequestMapping("/api/movies")
public class MovieApiController {

	private MovieRepository movieRepository;

	public MovieApiController(MovieRepository movieRepository) {
		this.movieRepository = movieRepository;
	}

	// @GetMapping("")
	// public List<Movie> getAll() {
	// return movieRepository.findAll();
	// }

	@GetMapping("")
	public List<MovieView> getAll() {
		List<Movie> movies = movieRepository.findAll();
		ArrayList<MovieView> movieViews = new ArrayList<MovieView>();
		for (Movie movie : movies) {
			movieViews.add(new MovieView(movie));
		}
		return movieViews;
	}

	@GetMapping("{id}")
	public Movie getOne(@PathVariable Long id) {
		return movieRepository.findOne(id);
	}

	@PostMapping("")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Movie create(@RequestBody Movie movie) {
		return movieRepository.save(movie);
	}

	@PutMapping("{id}")
	public Movie update(@RequestBody Movie movie, @PathVariable Long id) {
		movie.setId(id);
		return movieRepository.save(movie);
	}

	@DeleteMapping("{id}")
	public Movie delete(@PathVariable Long id) {
		Movie movie = movieRepository.findOne(id);
		movieRepository.delete(id);
		return movie;
	}
}