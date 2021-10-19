package ru.shark.home.l2info.dao.service;

import com.google.common.collect.Ordering;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.shark.home.l2info.dao.common.PageableList;
import ru.shark.home.l2info.dao.common.RequestCriteria;
import ru.shark.home.l2info.dao.entity.RaceEntity;
import ru.shark.home.l2info.dao.entity.WeaponEntity;
import ru.shark.home.l2info.util.DaoServiceTest;

import java.text.MessageFormat;

import static org.junit.jupiter.api.Assertions.*;
import static ru.shark.home.l2info.common.ErrorConstants.ENTITY_ALREADY_EXISTS;

public class RaceDaoTest extends DaoServiceTest {

    @Autowired
    private RaceDao raceDao;

    @BeforeAll
    public void init() {
        loadRace("RaceDaoTest/race.json");
    }

    @Test
    public void getWithPagination() {
        // GIVEN
        Ordering<RaceEntity> ordering = new Ordering<RaceEntity>() {
            @Override
            public int compare(@Nullable RaceEntity raceEntity, @Nullable RaceEntity t1) {
                return raceEntity.getName().compareTo(t1.getName());
            }
        };

        // WHEN
        PageableList<RaceEntity> list = raceDao.getWithPagination(new RequestCriteria(0, 10));

        // THEN
        checkPagingList(list, 2, 2L);
        assertTrue(ordering.isOrdered(list.getData()));
    }

    @Test
    public void getWithPaginationWithSearch() {
        // GIVEN
        RequestCriteria requestCriteria = new RequestCriteria(0, 10);
        requestCriteria.setSearch("human");

        // WHEN
        PageableList<RaceEntity> list = raceDao.getWithPagination(requestCriteria);

        // THEN
        checkPagingList(list, 1, 1L);
    }


    @Test
    public void saveWithCreate() {
        // GIVEN
        RaceEntity entity = prepareNewEntity();

        // WHEN
        RaceEntity saved = raceDao.save(entity);

        // THEN
        assertNotNull(saved);
        assertNotNull(saved.getId());
        assertEquals(entity.getName(), saved.getName());
    }

    @Test
    public void saveWithUpdate() {
        // GIVEN
        RaceEntity entity = prepareNewEntity();
        entity.setId(entityFinder.findRace("Human").getId());

        // WHEN
        RaceEntity saved = raceDao.save(entity);

        // THEN
        assertNotNull(saved);
        assertEquals(entity.getId(), saved.getId());
        assertEquals(entity.getName(), saved.getName());
    }

    @Test()
    public void saveWithAlreadyExistsByName() {
        // GIVEN
        RaceEntity entity = prepareNewEntity();
        entity.setId(entityFinder.findRace("Human").getId());
        entity.setName("Elf");
        String expectedMsg = MessageFormat.format(ENTITY_ALREADY_EXISTS,
                RaceEntity.getDescription(), entity.getName());

        // WHEN
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> raceDao.save(entity));

        // THEN
        Assertions.assertEquals(expectedMsg, exception.getMessage());
    }

    @Test
    public void delete() {
        // GIVEN
        RaceEntity entity = entityFinder.findRace("Human");

        // WHEN
        raceDao.delete(entity);

        // THEN
        assertTrue(isDeleted(entity));
    }

    private RaceEntity prepareNewEntity() {
        RaceEntity entity = new RaceEntity();
        entity.setName("Orc");

        return entity;
    }
}
