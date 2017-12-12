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

import com.lmig.gfc.wimp.models.Actor;
import com.lmig.gfc.wimp.repositories.ActorRepository;

public class ActorApiControllerTests {

	private ActorApiController controller;
	@Mock
	private ActorRepository repo;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		controller = new ActorApiController(repo);
	}

	@Test // get "" // confirm that the get all is running successfully
	public void getAll_returns_list_of_actors() {
		// Arrange
		ArrayList<Actor> actors = new ArrayList<Actor>();
		when(repo.findAll()).thenReturn(actors);
		// Act
		List<Actor> actual = controller.getAll();
		// Asset
		assertThat(actual).isSameAs(actors);
		verify(repo).findAll();
	}

	@Test // get one for a valid id
	public void get_one_returns_an_actor_for_a_valid_id() {
		Actor actor = new Actor();
		when(repo.findOne(12L)).thenReturn(actor);
		// act - call the method to create the actor
		Actor actual = controller.getOne(12L);
		// assert
		assertThat(actual).isSameAs(actor);
		verify(repo).findOne(12L);
	}

	@Test // get one for an invalid id
	public void get_one_for_an_invalid_id() {
		// arrange
		// act - call the method to create the dog
		Actor actual = controller.getOne(98L);
		// assert
		assertThat(actual).isNull();
		verify(repo).findOne(98L);
	}

	@Test // test post // test the create method under the @PostMapping("")
	// using the repository object
	public void create_saves_the_actor_and_returns_it() {
		// arrange
		Actor actor = new Actor();
		when(repo.save(actor)).thenReturn(actor);
		// act - call the method to create the actor
		Actor actual = controller.create(actor);
		// assert
		assertThat(actual).isSameAs(actor);
		verify(repo).save(actor);
	}

	@Test // test the update mapping in the @PutMapping("{id}")
	public void update_sets_id_of_actor_and_calls_save_and_return_actor() {
		// Arrange
		Actor actor = new Actor();
		when(repo.save(actor)).thenReturn(actor);
		// act
		Actor actual = controller.update(actor, 44L); // update is the method we are testing
		// assert
		assertThat(actual).isSameAs(actor);
		verify(repo).save(actor);
		assertThat(actor.getId()).isEqualTo(44L); // code was changing the id
	}

	@Test // delete // test the actor delete
	public void delete_gets_the_actor_and_deletes_it_from_the_repo_and_returns_it() {
		// arrange
		Actor actor = new Actor();
		when(repo.findOne(2L)).thenReturn(actor);
		// act
		Actor actual = controller.delete(2L);
		// assert
		assertThat(actual).isSameAs(actor);
		verify(repo).findOne(2L);
		verify(repo).delete(2L);
	}
}