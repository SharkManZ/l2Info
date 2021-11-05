package ru.shark.home.l2info.dao.service;

import com.google.common.collect.Ordering;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.shark.home.l2info.dao.common.PageableList;
import ru.shark.home.l2info.dao.common.RequestCriteria;
import ru.shark.home.l2info.dao.entity.WeaponEntity;
import ru.shark.home.l2info.enums.Grade;
import ru.shark.home.l2info.enums.WeaponType;
import ru.shark.home.l2info.util.DaoServiceTest;

import java.text.MessageFormat;

import static org.junit.jupiter.api.Assertions.*;
import static ru.shark.home.l2info.common.ErrorConstants.ENTITY_ALREADY_EXISTS;

public class WeaponDaoTest extends DaoServiceTest {

    @Autowired
    private WeaponDao weaponDao;

    @BeforeAll
    public void init() {
        loadWeapons("WeaponDaoTest/weapons.json");
    }

    @Test
    public void getWithPagination() {
        // GIVEN
        Ordering<WeaponEntity> ordering = new Ordering<WeaponEntity>() {
            @Override
            public int compare(@Nullable WeaponEntity weaponEntity, @Nullable WeaponEntity t1) {
                return weaponEntity.getName().compareTo(t1.getName());
            }
        };

        // WHEN
        PageableList<WeaponEntity> list = weaponDao.getWithPagination(new RequestCriteria(0, 10));

        // THEN
        checkPagingList(list, 2, 2L);
        assertTrue(ordering.isOrdered(list.getData()));
    }

    @Test
    public void getWithPaginationWithSearch() {
        // GIVEN
        RequestCriteria requestCriteria = new RequestCriteria(0, 10);
        requestCriteria.setSearch("long");

        // WHEN
        PageableList<WeaponEntity> list = weaponDao.getWithPagination(requestCriteria);

        // THEN
        checkPagingList(list, 1, 1L);
    }


    @Test
    public void saveWithCreate() {
        // GIVEN
        WeaponEntity entity = prepareNewEntity();

        // WHEN
        WeaponEntity saved = weaponDao.save(entity);

        // THEN
        assertNotNull(saved);
        assertNotNull(saved.getId());
        assertEquals(entity.getName(), saved.getName());
        assertEquals(entity.getType(), saved.getType());
        assertEquals(entity.getGrade(), saved.getGrade());
        assertEquals(entity.getpAtk(), saved.getpAtk());
        assertEquals(entity.getmAtk(), saved.getmAtk());
    }

    @Test
    public void saveWithUpdate() {
        // GIVEN
        WeaponEntity entity = prepareNewEntity();
        entity.setId(entityFinder.findWeapon("Bow of Peril").getId());

        // WHEN
        WeaponEntity saved = weaponDao.save(entity);

        // THEN
        assertNotNull(saved);
        assertEquals(entity.getId(), saved.getId());
        assertEquals(entity.getName(), saved.getName());
        assertEquals(entity.getType(), saved.getType());
        assertEquals(entity.getGrade(), saved.getGrade());
        assertEquals(entity.getpAtk(), saved.getpAtk());
        assertEquals(entity.getmAtk(), saved.getmAtk());
    }

    @Test()
    public void saveWithAlreadyExistsByName() {
        // GIVEN
        WeaponEntity entity = prepareNewEntity();
        entity.setId(entityFinder.findWeapon("Bow of Peril").getId());
        entity.setName("Strengthened Long Bow");
        String expectedMsg = MessageFormat.format(ENTITY_ALREADY_EXISTS,
                WeaponEntity.getDescription(), entity.getName());

        // WHEN
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> weaponDao.save(entity));

        // THEN
        Assertions.assertEquals(expectedMsg, exception.getMessage());
    }

    @Test
    public void deleteById() {
        // GIVEN
        Long id = entityFinder.findWeaponId("Bow of Peril");

        // WHEN
        weaponDao.deleteById(id);

        // THEN
        assertTrue(isDeleted(id, WeaponEntity.class));
    }

    private WeaponEntity prepareNewEntity() {
        WeaponEntity entity = new WeaponEntity();
        entity.setName("Soul Bow");
        entity.setType(WeaponType.BOW);
        entity.setGrade(Grade.A);
        entity.setpAtk(500);
        entity.setmAtk(150);

        return entity;
    }
}
