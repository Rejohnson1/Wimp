package com.lmig.gfc.wimp.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.lmig.gfc.wimp.models.Movie;
import com.lmig.gfc.wimp.repositories.MovieRepository;

public class MovieApiControllerTests {

	private MovieApiController controller;
	@Mock
	private MovieRepository repo;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		controller = new MovieApiController(repo);
	}

	@Test // get "" // confirm that the get all is running successfully
	public void getAll_returns_list_of_movies() {
		// Arrange
		ArrayList<Movie> movies = new ArrayList<Movie>();
		when(repo.findAll()).thenReturn(movies);
		// Act
		List<MovieView> actual = controller.getAll();
		// Asset
		assertThat(actual).hasSize(movies.size());
		verify(repo).findAll();
		// trying to test the loop
		// for (int i = 0; i < movies.size(); i = i + 1) {
		// assertThat(movies.get(i)).isSameAs(actual.get(i).nnnnn);
		// }
	}

	@Test // get one for a valid id
	public void get_one_returns_a_movie_for_a_valid_id() {
		Movie movie = new Movie();
		when(repo.findOne(12L)).thenReturn(movie);
		// act - call the method to create the movie
		Movie actual = controller.getOne(12L);
		// assert
		assertThat(actual).isSameAs(movie);
		verify(repo).findOne(12L);
	}

	@Test // get one for an invalid id
	public void get_one_for_an_invalid_id() {
		// arrange
		// act - call the method to create the dog
		Movie actual = controller.getOne(98L);
		// assert
		assertThat(actual).isNull();
		verify(repo).findOne(98L);
	}

	@Test // test post // test the create method under the @PostMapping("")
	// using the repository object
	public void create_saves_the_movie_and_returns_it() {
		// arrange
		Movie movie = new Movie();
		when(repo.save(movie)).thenReturn(movie);
		// act - call the method to create the movie
		Movie actual = controller.create(movie);
		// assert
		assertThat(actual).isSameAs(movie);
		verify(repo).save(movie);
	}

	@Test // test the update mapping in the @PutMapping("{id}")
	public void update_sets_id_of_actor_and_calls_save_and_return_actor() {
		// Arrange
		Movie movie = new Movie();
		when(repo.save(movie)).thenReturn(movie);
		// act
		Movie actual = controller.update(movie, 44L); // update is the method we are testing
		// assert
		assertThat(actual).isSameAs(movie);
		verify(repo).save(movie);
		assertThat(movie.getId()).isEqualTo(44L); // code was changing the id
	}

	@Test // delete // test the movie delete
	public void delete_gets_the_movie_and_deletes_it_from_the_repo_and_returns_it() {
		// arrange
		Movie movie = new Movie();
		when(repo.findOne(2L)).thenReturn(movie);
		// act
		Movie actual = controller.delete(2L);
		// assert
		assertThat(actual).isSameAs(movie);
		verify(repo).findOne(2L);
		verify(repo).delete(2L);
	}

}
