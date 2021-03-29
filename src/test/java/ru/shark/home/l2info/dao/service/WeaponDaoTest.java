package ru.shark.home.l2info.dao.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import ru.shark.home.l2info.dao.common.PageableList;
import ru.shark.home.l2info.dao.entity.WeaponEntity;
import ru.shark.home.l2info.enums.Grade;
import ru.shark.home.l2info.enums.WeaponType;

import static org.junit.jupiter.api.Assertions.*;

public class WeaponDaoTest extends BaseDaoTest {

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
    public void save() {
        // GIVEN
        WeaponEntity entity = prepareNewEntity();

        // WHEN
        WeaponEntity saved = weaponDao.save(entity);

        // THEN
        assertNotNull(saved);
        assertNotNull(saved.id);
        assertEquals(entity.name, saved.name);
        assertEquals(entity.type, saved.type);
        assertEquals(entity.grade, saved.grade);
        assertEquals(entity.pAtk, saved.pAtk);
        assertEquals(entity.mAtk, saved.mAtk);
    }

    @Test
    public void delete() {
        // GIVEN
        WeaponEntity entity = entityFinder.findWeapon("Bow of Peril");

        // WHEN
        weaponDao.delete(entity);

        // THEN
        isDeleted(entity);
    }

    private WeaponEntity prepareNewEntity() {
        WeaponEntity entity = new WeaponEntity();
        entity.name = "Soul Bow";
        entity.type = WeaponType.BOW;
        entity.grade = Grade.A;
        entity.pAtk = 500;
        entity.mAtk = 150;

        return entity;
    }
}
