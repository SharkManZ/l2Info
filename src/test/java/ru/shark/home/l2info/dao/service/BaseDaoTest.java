package ru.shark.home.l2info.dao.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.shark.home.l2info.dao.common.PageableList;
import ru.shark.home.l2info.dao.common.RequestCriteria;
import ru.shark.home.l2info.dao.common.RequestFilter;
import ru.shark.home.l2info.dao.entity.WeaponEntity;
import ru.shark.home.l2info.dao.repository.BaseRepository;
import ru.shark.home.l2info.dao.repository.WeaponRepository;
import ru.shark.home.l2info.enums.FieldType;
import ru.shark.home.l2info.enums.FilterOperation;
import ru.shark.home.l2info.enums.Grade;
import ru.shark.home.l2info.enums.WeaponType;
import ru.shark.home.l2info.util.DaoServiceTest;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class BaseDaoTest extends DaoServiceTest {

    private BaseDaoImpl baseDao;

    @Autowired
    private WeaponRepository weaponRepository;

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

    @Test
    public void getWithPagination() {
        // GIVEN
        RequestCriteria requestCriteria = new RequestCriteria(0, 10);

        // WHEN
        PageableList withPagination = baseDao.getRepository().getWithPagination(requestCriteria);

        // THEN
        checkPagingList(withPagination, 2, 2L);
    }

    @Test
    public void getWithPaginationWithFilter() {
        // GIVEN
        RequestCriteria requestStringEquals = new RequestCriteria(0, 10);
        requestStringEquals.setFilters(Arrays.asList(new RequestFilter("name", FieldType.STRING, FilterOperation.EQ.getValue(),
                "Bow of Peril")));
        RequestCriteria requestIntegerEquals = new RequestCriteria(0, 10);
        requestIntegerEquals.setFilters(Arrays.asList(new RequestFilter("pAtk", FieldType.INTEGER, FilterOperation.EQ.getValue(),
                "179")));
        RequestCriteria requestEnumEquals = new RequestCriteria(0, 10);
        requestEnumEquals.setFilters(Arrays.asList(new RequestFilter("type", FieldType.ENUM, FilterOperation.EQ.getValue(),
                "SWORD")));

        // WHEN
        PageableList responseStringEquals = baseDao.getRepository().getWithPagination(requestStringEquals);
        PageableList responseIntegerEquals = baseDao.getRepository().getWithPagination(requestIntegerEquals);
        PageableList responseEnumEquals = baseDao.getRepository().getWithPagination(requestEnumEquals);

        // THEN
        checkPagingList(responseStringEquals, 1, 1L);
        checkPagingList(responseIntegerEquals, 1, 1L);
        checkPagingList(responseEnumEquals, 1, 1L);
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

        public BaseRepository<WeaponEntity> getRepository() {
            return weaponRepository;
        }
    }
}
