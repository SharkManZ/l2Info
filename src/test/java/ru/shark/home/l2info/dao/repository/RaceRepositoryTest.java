package ru.shark.home.l2info.dao.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.springframework.beans.factory.annotation.Autowired;
import ru.shark.home.l2info.dao.entity.RaceEntity;
import ru.shark.home.l2info.util.DaoServiceTest;

public class RaceRepositoryTest extends DaoServiceTest {
    @Autowired
    private RaceRepository raceRepository;

    @BeforeAll
    public void init() {
        loadRace("RaceRepositoryTest/race.json");
    }

    @Test
    public void findRaceByName() {
        // GIVEN
        RaceEntity entity = entityFinder.findRace("Human");

        // WHEN
        RaceEntity raceByName = raceRepository.findRaceByName(entity.getName());
        Assertions.assertTrue(new ReflectionEquals(entity).matches(raceByName));
    }
}
