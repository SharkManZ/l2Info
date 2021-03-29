package ru.shark.home.l2info.dao.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import ru.shark.home.l2info.dao.common.PageableList;
import ru.shark.home.l2info.dao.entity.WeaponEntity;
import ru.shark.home.l2info.enums.Grade;
import ru.shark.home.l2info.enums.WeaponType;
import ru.shark.home.l2info.util.DaoServiceTest;

import static org.junit.jupiter.api.Assertions.*;

public class WeaponDaoTest extends DaoServiceTest {

    @Autowired
    private WeaponDao weaponDao;

    @BeforeAll
    public void init() {
        loadWeapons("WeaponDaoTest/weapons.json");
    }

    @Test
    public void getWithPagination() {
        // WHEN
        PageableList<WeaponEntity> list = weaponDao.getWithPagination(PageRequest.of(0, 1));

        // THEN
        checkPagingList(list, 1, 2L);
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
    public void saveWithCUpdate() {
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

    @Test
    public void delete() {
        // GIVEN
        WeaponEntity entity = entityFinder.findWeapon("Bow of Peril");

        // WHEN
        weaponDao.delete(entity);

        // THEN
        assertTrue(isDeleted(entity));
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
