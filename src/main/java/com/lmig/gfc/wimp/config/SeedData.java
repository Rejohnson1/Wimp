package com.lmig.gfc.wimp.config;

import java.util.Date;

import org.springframework.context.annotation.Configuration;

import com.lmig.gfc.wimp.models.Actor;
import com.lmig.gfc.wimp.models.Award;
import com.lmig.gfc.wimp.models.Movie;
import com.lmig.gfc.wimp.repositories.ActorRepository;
import com.lmig.gfc.wimp.repositories.AwardRepository;
import com.lmig.gfc.wimp.repositories.MovieRepository;

@Configuration
public class SeedData {

	public SeedData(MovieRepository movieRepository, ActorRepository actorRepository, AwardRepository awardRepository) {

		movieRepository.save(new Movie("Movie 1", new Date(2012, 01, 01), 11111111L, "Distributor One"));
		movieRepository.save(new Movie("Movie 2", new Date(2012, 02, 02), 22222222L, "Distributor Two"));
		movieRepository.save(new Movie("Movie 3", new Date(2012, 03, 03), 33333333L, "Distributor Three"));
		movieRepository.save(new Movie("Movie 4", new Date(2012, 04, 04), 44444444L, "Distributor Four"));
		movieRepository.save(new Movie("Movie 5", new Date(2012, 05, 05), 55555555L, "Distributor Five"));
		movieRepository.save(new Movie("Movie 6", new Date(2012, 06, 06), 66666666L, "Distributor Six"));

		actorRepository.save(new Actor("Actor One", "One", 1971L, new Date(1980, 01, 01)));
		actorRepository.save(new Actor("Actor Two", "Two", 1972L, new Date(1980, 02, 02)));
		actorRepository.save(new Actor("Actor Three", "Three", 1973L, new Date(1980, 03, 03)));
		actorRepository.save(new Actor("Actor Four", "Four", 1974L, new Date(1980, 04, 04)));
		actorRepository.save(new Actor("Actor Five", "Five", 1975L, new Date(1980, 05, 05)));
		actorRepository.save(new Actor("Actor Six", "Six", 1976L, new Date(1980, 06, 06)));

		awardRepository.save(new Award("Grammy", "The Grammy Company", 1990));
		awardRepository.save(new Award("Emmy", "Emmy Company", 1991));
		awardRepository.save(new Award("Tony", "Tony Company", 1992));
	}
}
