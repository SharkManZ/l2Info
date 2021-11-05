package ru.shark.home.l2info.dao.service;

import com.google.common.collect.Ordering;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import ru.shark.home.common.dao.common.PageableList;
import ru.shark.home.common.dao.common.RequestCriteria;
import ru.shark.home.common.dao.common.RequestFilter;
import ru.shark.home.common.dao.common.RequestSort;
import ru.shark.home.common.dao.repository.BaseRepository;
import ru.shark.home.common.dao.service.BaseDao;
import ru.shark.home.common.dao.util.SpecificationUtils;
import ru.shark.home.common.enums.FieldType;
import ru.shark.home.common.enums.FilterOperation;
import ru.shark.home.common.enums.SortDirection;
import ru.shark.home.l2info.dao.entity.WeaponEntity;
import ru.shark.home.l2info.dao.repository.WeaponRepository;
import ru.shark.home.l2info.enums.Grade;
import ru.shark.home.l2info.enums.WeaponType;
import ru.shark.home.l2info.util.DaoServiceTest;

import java.util.Arrays;
import java.util.List;

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
        checkPagingList(withPagination, 3, 3L);
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
        RequestCriteria requestStringLike = new RequestCriteria(0, 10);
        requestStringLike.setFilters(Arrays.asList(new RequestFilter("name", FieldType.STRING, FilterOperation.LIKE.getValue(),
                "long")));

        // WHEN
        PageableList<WeaponEntity> responseStringEquals = baseDao.getRepository().getWithPagination(requestStringEquals);
        PageableList<WeaponEntity> responseIntegerEquals = baseDao.getRepository().getWithPagination(requestIntegerEquals);
        PageableList<WeaponEntity> responseEnumEquals = baseDao.getRepository().getWithPagination(requestEnumEquals);
        PageableList<WeaponEntity> responseStringLike = baseDao.getRepository().getWithPagination(requestStringLike);

        // THEN
        checkPagingList(responseStringEquals, 1, 1L);
        assertEquals("Bow of Peril", responseStringEquals.getData().get(0).getName());
        checkPagingList(responseIntegerEquals, 1, 1L);
        assertEquals("Strengthened Long Bow", responseIntegerEquals.getData().get(0).getName());
        checkPagingList(responseEnumEquals, 1, 1L);
        assertEquals("Sword of Miracle", responseEnumEquals.getData().get(0).getName());
        checkPagingList(responseStringLike, 1, 1L);
        assertEquals("Strengthened Long Bow", responseStringLike.getData().get(0).getName());
    }

    @Test
    public void getWithPaginationWithSearch() {
        // GIVEN
        RequestCriteria requestCriteria = new RequestCriteria(0, 10);
        requestCriteria.setSearch("peril");
        Specification searchSpec = SpecificationUtils.searchSpecification("peril", "name");

        // WHEN
        PageableList withPagination = baseDao.getRepository().getWithPagination(requestCriteria, searchSpec);

        // THEN
        checkPagingList(withPagination, 1, 1L);
    }

    @Test
    public void getWithPaginationWithSort() {
        // GIVEN
        RequestCriteria requestCriteria = new RequestCriteria(0, 10);
        requestCriteria.setSorts(Arrays.asList(new RequestSort("name", SortDirection.ASC.name())));
        Ordering<WeaponEntity> ordering = new Ordering<WeaponEntity>() {
            @Override
            public int compare(@Nullable WeaponEntity weaponEntity, @Nullable WeaponEntity t1) {
                return weaponEntity.getName().compareTo(t1.getName());
            }
        };

        // WHEN
        PageableList withPagination = baseDao.getRepository().getWithPagination(requestCriteria);

        // THEN
        checkPagingList(withPagination, 3, 3L);
        List<WeaponEntity> data = withPagination.getData();
        Assertions.assertTrue(ordering.isOrdered(data));
    }

    @Test
    public void getWithPaginationWithSortDest() {
        // GIVEN
        RequestCriteria requestCriteria = new RequestCriteria(0, 10);
        requestCriteria.setSorts(Arrays.asList(new RequestSort("type", SortDirection.DESC.name())));
        Ordering<WeaponEntity> ordering = new Ordering<WeaponEntity>() {
            @Override
            public int compare(@Nullable WeaponEntity weaponEntity, @Nullable WeaponEntity t1) {
                return weaponEntity.getType().name().compareTo(t1.getType().name());
            }
        };

        // WHEN
        PageableList withPagination = baseDao.getRepository().getWithPagination(requestCriteria);

        // THEN
        checkPagingList(withPagination, 3, 3L);
        List<WeaponEntity> data = withPagination.getData();
        Assertions.assertTrue(ordering.reverse().isOrdered(data));
    }

    @Test
    public void getWithPaginationWithDefaultSort() {
        // GIVEN
        RequestCriteria requestCriteria = new RequestCriteria(0, 10);
        Ordering<WeaponEntity> ordering = new Ordering<WeaponEntity>() {
            @Override
            public int compare(@Nullable WeaponEntity weaponEntity, @Nullable WeaponEntity t1) {
                return weaponEntity.getName().compareTo(t1.getName());
            }
        };

        // WHEN
        PageableList withPagination = baseDao.getRepository().getWithPagination(requestCriteria, null, "name");

        // THEN
        checkPagingList(withPagination, 3, 3L);
        List<WeaponEntity> data = withPagination.getData();
        Assertions.assertTrue(ordering.isOrdered(data));
    }

    @Test
    public void getWithPaginationWithDefaultSortDesc() {
        // GIVEN
        RequestCriteria requestCriteria = new RequestCriteria(0, 10);
        Ordering<WeaponEntity> ordering = new Ordering<WeaponEntity>() {
            @Override
            public int compare(@Nullable WeaponEntity weaponEntity, @Nullable WeaponEntity t1) {
                return weaponEntity.getGrade().name().compareTo(t1.getGrade().name());
            }
        };

        // WHEN
        PageableList withPagination = baseDao.getRepository().getWithPagination(requestCriteria, null,
                "grade DESC");

        // THEN
        checkPagingList(withPagination, 3, 3L);
        List<WeaponEntity> data = withPagination.getData();
        Assertions.assertTrue(ordering.reverse().isOrdered(data));
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
