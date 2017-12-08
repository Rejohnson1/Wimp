package com.lmig.gfc.wimp.api;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lmig.gfc.wimp.models.Actor;
import com.lmig.gfc.wimp.models.Movie;
import com.lmig.gfc.wimp.repositories.ActorRepository;
import com.lmig.gfc.wimp.repositories.MovieRepository;

@RestController
@RequestMapping("/api/movies/{movieId}/actors")
public class ActorMovieAssignApiController {

	private MovieRepository movieRepository;
	private ActorRepository actorRepository;

	public ActorMovieAssignApiController(MovieRepository movieRepository, ActorRepository actorRepository) {
		this.movieRepository = movieRepository;
		this.actorRepository = actorRepository;
	}

	@PostMapping("")
	public Movie create(@PathVariable Long movieId, @RequestBody Long actorId) {
		Movie movie = movieRepository.findOne(movieId); // get a movie
		Actor actor = actorRepository.findOne(actorId); // get an actor
		actor.getMovies().add(movie);
		actorRepository.save(actor);
		return movie;
	}
}