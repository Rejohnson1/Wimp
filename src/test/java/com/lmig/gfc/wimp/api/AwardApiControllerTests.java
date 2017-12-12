package com.lmig.gfc.wimp.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.lmig.gfc.wimp.models.Actor;
import com.lmig.gfc.wimp.models.Award;
import com.lmig.gfc.wimp.repositories.ActorRepository;
import com.lmig.gfc.wimp.repositories.AwardRepository;

public class AwardApiControllerTests {

	private AwardApiController controller;

	@Mock
	private AwardRepository awardRepo;
	@Mock
	private ActorRepository actorRepo;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		controller = new AwardApiController(actorRepo, awardRepo);
	}

	@Test
	public void create_award_and_actor_relationship() {
		// Arrange
		Actor actor = new Actor();
		Award award = new Award();
		when(actorRepo.findOne(111L)).thenReturn(actor);
		// Act
		Award actual = controller.create(111L, award);
		// Assert
		verify(actorRepo).findOne(111L);
		verify(awardRepo).save(award);
		assertThat(actual).isSameAs(award);
		assertThat(award.getActor()).isEqualTo(actor);
	}

	// @Test no alternative since award needs to be passed in as a full entity
	// if no award passed in, no creation
	// public void method_does_not_create_award_and_actor_relationship() {
	// Actor actor = new Actor();
	// Award award = new Award();
	// when(actorRepo.findOne(111L)).thenReturn(actor);
	// // Act
	// Award actual = controller.create(111L, award);
	// // Assert
	// verify(actorRepo).findOne(111L);
	// verify(actorRepo, never()).save(actor);
	// }
}