package ru.shark.home.l2info.dao.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.springframework.beans.factory.annotation.Autowired;
import ru.shark.home.l2info.dao.entity.ClassEntity;
import ru.shark.home.l2info.dao.entity.RaceEntity;
import ru.shark.home.l2info.util.DaoServiceTest;

public class ClassRepositoryTest extends DaoServiceTest {
    @Autowired
    private ClassRepository classRepository;

    @BeforeAll
    public void init() {
        loadRace("ClassRepositoryTest/race.json");
        loadClass("ClassRepositoryTest/class.json");
    }

    @Test
    public void findRaceByName() {
        // GIVEN
        ClassEntity entity = entityFinder.findClass("Warrior");

        // WHEN
        ClassEntity classByName = classRepository.findClassByName(entity.getName());

        // THEN
        Assertions.assertTrue(new ReflectionEquals(entity).matches(classByName));
    }
}
