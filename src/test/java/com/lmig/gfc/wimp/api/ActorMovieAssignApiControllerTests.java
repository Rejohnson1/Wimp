package com.lmig.gfc.wimp.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.lmig.gfc.wimp.models.Actor;
import com.lmig.gfc.wimp.models.Movie;
import com.lmig.gfc.wimp.repositories.ActorRepository;
import com.lmig.gfc.wimp.repositories.MovieRepository;

public class ActorMovieAssignApiControllerTests {

	private ActorMovieAssignApiController controller;

	@Mock
	private ActorRepository actorRepo;
	@Mock
	private MovieRepository movieRepo;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		controller = new ActorMovieAssignApiController(movieRepo, actorRepo);
	}

	@Test // testing the create method and assign an actor to a movie
	public void create_saves_actor_when_person_is_not_listed_with_movie() {
		Movie movie = new Movie();
		Actor actor = new Actor();
		actor.setMovies(new ArrayList<Movie>());
		when(movieRepo.findOne(111L)).thenReturn(movie);
		when(actorRepo.findOne(222L)).thenReturn(actor);
		// Act
		Movie actual = controller.create(111L, 222L);
		// Assert
		assertThat(actor.getMovies()).contains(movie);
		assertThat(actual).isSameAs(movie);
		verify(movieRepo).findOne(111L);
		verify(actorRepo).findOne(222L);
		verify(actorRepo).save(actor);
	}

	// public void
	// method_does_not_create_saves_when_person_is_already_listed_with_movie() {
	// // Arrange
	// Actor actor = new Actor();
	// ArrayList<Actor> actors = new ArrayList<Actor>();
	// actors.add(actor);
	// Movie movie = new Movie();
	// movie.setActors(actors);
	// when(movieRepo.findOne(2L)).thenReturn(movie);
	// when(actorRepo.findOne(3L)).thenReturn(actor);
	// // Act
	// Movie actual = controller.create(2L, 3L);
	// // Assert
	// verify(movieRepo).findOne(2L);
	// verify(actorRepo).findOne(3L);
	// verify(actorRepo, never()).save(actor);
	// assertThat(actual).isSameAs(movie);
	// assertThat(movie.getActors()).hasSize(1);
	// }
}
