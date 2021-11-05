package ru.shark.home.l2info.dao.service;

import com.google.common.collect.Ordering;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import ru.shark.home.l2info.dao.common.PageableList;
import ru.shark.home.l2info.dao.common.RequestCriteria;
import ru.shark.home.l2info.dao.entity.ClassEntity;
import ru.shark.home.l2info.dao.entity.RaceEntity;
import ru.shark.home.l2info.enums.ClassType;
import ru.shark.home.l2info.util.DaoServiceTest;

import java.text.MessageFormat;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;
import static ru.shark.home.l2info.common.ErrorConstants.*;

public class ClassDaoTest extends DaoServiceTest {

    @Autowired
    private ClassDao classDao;

    @BeforeAll
    public void init() {
        loadRace("ClassDaoTest/race.json");
        loadClass("ClassDaoTest/class.json");
    }

    @Test
    public void getWithPagination() {
        // GIVEN
        Ordering<ClassEntity> ordering = new Ordering<ClassEntity>() {
            @Override
            public int compare(@Nullable ClassEntity classEntity, @Nullable ClassEntity t1) {
                return Comparator.comparing(ClassEntity::getLevel)
                        .thenComparing(ClassEntity::getName)
                        .compare(classEntity, t1);
            }
        };

        // WHEN
        PageableList<ClassEntity> list = classDao.getWithPagination(new RequestCriteria(0, 10));

        // THEN
        checkPagingList(list, 4, 4L);
        assertTrue(ordering.isOrdered(list.getData()));
    }

    @Test
    public void getWithPaginationWithSearch() {
        // GIVEN
        RequestCriteria requestCriteria = new RequestCriteria(0, 10);
        requestCriteria.setSearch("Warrior");

        // WHEN
        PageableList<ClassEntity> list = classDao.getWithPagination(requestCriteria);

        // THEN
        checkPagingList(list, 1, 1L);
    }


    @Test
    public void saveWithCreateWithoutParent() {
        // GIVEN
        ClassEntity entity = prepareNewEntity(null);

        // WHEN
        ClassEntity saved = classDao.save(entity);

        // THEN
        assertNotNull(saved);
        assertNotNull(saved.getId());
        assertEquals(entity.getName(), saved.getName());
        assertEquals(0, saved.getLevel());
        assertEquals(entity.getType(), saved.getType());
        assertEquals(entity.getRace().getId(), saved.getRace().getId());
        assertNull(saved.getParent());
    }

    @Test
    public void saveWithCreateWithoutRace() {
        // GIVEN
        ClassEntity entity = prepareNewEntity(null);
        entity.setRace(null);
        String expectedMsg = MessageFormat.format(EMPTY_ENTITY, RaceEntity.getDescription());

        // WHEN
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> classDao.save(entity));

        // THEN
        assertEquals(expectedMsg, exception.getMessage());
    }

    @Test
    public void saveWithCreateWithoutRaceId() {
        // GIVEN
        ClassEntity entity = prepareNewEntity(null);
        entity.getRace().setId(null);
        String expectedMsg = MessageFormat.format(EMPTY_ENTITY, RaceEntity.getDescription());

        // WHEN
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> classDao.save(entity));

        // THEN
        assertEquals(expectedMsg, exception.getMessage());
    }

    @Test
    public void saveWithCreateWithRaceIdInvalid() {
        // GIVEN
        ClassEntity entity = prepareNewEntity(null);
        entity.getRace().setId(999L);
        String expectedMsg = MessageFormat.format(ENTITY_NOT_FOUND_BY_ID,
                RaceEntity.getDescription(), entity.getRace().getId());

        // WHEN
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> classDao.save(entity));

        // THEN
        assertEquals(expectedMsg, exception.getMessage());
    }

    @Test
    public void saveWithCreateWithParent() {
        // GIVEN
        ClassEntity entity = prepareNewEntity("Fighter");
        Long parentId = entityFinder.findClass("Fighter").getId();
        entity.setType(ClassType.FIGHTER);
        entity.setName("Human Knight");

        // WHEN
        ClassEntity saved = classDao.save(entity);

        // THEN
        assertNotNull(saved);
        assertNotNull(saved.getId());
        assertEquals(entity.getName(), saved.getName());
        assertEquals(1, saved.getLevel());
        assertEquals(entity.getType(), saved.getType());
        assertEquals(entity.getRace().getId(), saved.getRace().getId());
        assertEquals(parentId, saved.getParent().getId());
    }

    @Test
    public void saveWithCreateWithParentInvalidId() {
        // GIVEN
        ClassEntity entity = prepareNewEntity("Fighter");
        entity.getParent().setId(999L);
        entity.setType(ClassType.FIGHTER);
        entity.setName("Human Knight");
        String expectedMsg = MessageFormat.format(ENTITY_NOT_FOUND_BY_ID,
                ClassEntity.getDescription(), entity.getParent().getId());

        // WHEN
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> classDao.save(entity));

        // THEN
        assertEquals(expectedMsg, exception.getMessage());
    }

    @Test
    public void saveWithUpdateWithParent() {
        // GIVEN
        ClassEntity entity = prepareNewEntity("Fighter");
        Long parentId = entityFinder.findClass("Fighter").getId();
        entity.setType(ClassType.FIGHTER);
        entity.setName("Warrior1");
        entity.setId(entityFinder.findClassId("Warrior"));
        entity.setLevel(1);

        // WHEN
        ClassEntity saved = classDao.save(entity);

        // THEN
        assertNotNull(saved);
        assertEquals(entity.getId(), saved.getId());
        assertEquals(entity.getName(), saved.getName());
        assertEquals(1, saved.getLevel());
        assertEquals(entity.getType(), saved.getType());
        assertEquals(entity.getRace().getId(), saved.getRace().getId());
        assertEquals(parentId, saved.getParent().getId());
    }


    @Test()
    public void saveWithAlreadyExistsByName() {
        // GIVEN
        ClassEntity entity = prepareNewEntity(null);
        entity.setName("Warrior");
        String expectedMsg = MessageFormat.format(ENTITY_ALREADY_EXISTS,
                ClassEntity.getDescription(), entity.getName());

        // WHEN
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> classDao.save(entity));

        // THEN
        Assertions.assertEquals(expectedMsg, exception.getMessage());
    }

    @Test
    public void deleteById() {
        // GIVEN
        Long id = entityFinder.findClassId("Fighter");
        Long idChild = entityFinder.findClassId("Warrior");

        // WHEN
        classDao.deleteById(id);

        // THEN
        assertTrue(isDeleted(id, ClassEntity.class));
        assertTrue(isDeleted(idChild, ClassEntity.class));
    }

    private ClassEntity prepareNewEntity(String parentName) {
        ClassEntity entity = new ClassEntity();
        entity.setName("Elven Mage");
        entity.setType(ClassType.MAGE);
        entity.setRace(new RaceEntity());
        entity.getRace().setId(entityFinder.findRaceId("Human"));
        if (!ObjectUtils.isEmpty(parentName)) {
            entity.setParent(new ClassEntity());
            entity.getParent().setId(entityFinder.findClassId(parentName));
        }

        return entity;
    }
}
