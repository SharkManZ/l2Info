package ru.shark.home.l2info.datamanager;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.shark.home.l2info.dao.dto.RaceDto;
import ru.shark.home.l2info.dao.entity.RaceEntity;
import ru.shark.home.l2info.util.DaoServiceTest;

import static org.junit.jupiter.api.Assertions.*;

public class RaceDataManagerTest extends DaoServiceTest {
    @Autowired
    private RaceDataManager raceDataManager;

    @BeforeAll
    public void init() {
        loadRace("RaceDataManagerTest/race.json");
    }

    @Test
    public void findById() {
        // GIVEN
        RaceEntity entity = entityFinder.findRace("human");

        // WHEN
        RaceDto dto = raceDataManager.findById(entity.getId());

        // THEN
        assertNotNull(dto);
        assertEquals(entity.getId(), dto.getId());
        assertEquals(entity.getName(), dto.getName());
    }

    @Test
    public void save() {
        // GIVEN
        RaceDto dto = prepareDto(entityFinder.findRaceId("human"), "human");

        // WHEN
        RaceDto saved = raceDataManager.save(dto);

        // THEN
        assertNotNull(dto);
        assertEquals(dto.getId(), saved.getId());
        assertEquals(dto.getName(), saved.getName());
    }

    @Test
    public void deleteById() {
        // GIVEN
        Long id = entityFinder.findRaceId("human");

        // WHEN
        raceDataManager.deleteById(id);

        // THEN
        assertTrue(isDeleted(id, RaceEntity.class));
    }

    private RaceDto prepareDto(Long id, String name) {
        RaceDto dto = new RaceDto();
        dto.setId(id);
        dto.setName(name);

        return dto;
    }
}
