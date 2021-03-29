package ru.shark.home.l2info.dao.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.shark.home.l2info.dao.entity.WeaponEntity;
import ru.shark.home.l2info.enums.Grade;
import ru.shark.home.l2info.enums.WeaponType;
import ru.shark.home.l2info.util.DaoServiceTest;

import static org.junit.jupiter.api.Assertions.*;

public class BaseDaoTest extends DaoServiceTest {

    private BaseDao baseDao;

    @BeforeAll
    public void init() {
        baseDao = new BaseDaoImpl();
        baseDao.setEm(em);
        loadWeapons("BaseDaoTest/weapons.json");
    }

    @Test
    public void saveWithCreate() {
        // GIVEN
        WeaponEntity entity = prepareEntity(1L, "BOP");

        // WHEN
        WeaponEntity saved = (WeaponEntity) baseDao.save(entity);

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
        WeaponEntity entity = prepareEntity(1L, "BOP");
        entity.setId(entityFinder.findWeapon("bow of peril").getId());

        // WHEN
        WeaponEntity saved = (WeaponEntity) baseDao.save(entity);

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
        WeaponEntity weaponEntity = entityFinder.findWeapon("bow of peril");

        // WHEN
        baseDao.delete(weaponEntity);

        // THEN
        assertTrue(isDeleted(weaponEntity));
    }

    @Test
    public void findById() {
        // GIVEN
        Long id = entityFinder.findWeapon("bow of peril").getId();

        // WHEN
        WeaponEntity entity = (WeaponEntity) baseDao.findById(id);

        // THEN
        assertNotNull(entity);
        assertEquals(id, entity.getId());
    }

    private WeaponEntity prepareEntity(Long id, String name) {
        WeaponEntity weaponEntity = new WeaponEntity();
        weaponEntity.setId(id);
        weaponEntity.setName(name);
        weaponEntity.setType(WeaponType.BOW);
        weaponEntity.setGrade(Grade.B);
        weaponEntity.setpAtk(100);
        weaponEntity.setmAtk(100);

        return weaponEntity;
    }

    private class BaseDaoImpl extends BaseDao<WeaponEntity> {

        protected BaseDaoImpl() {
            super(WeaponEntity.class);
        }
    }
}
