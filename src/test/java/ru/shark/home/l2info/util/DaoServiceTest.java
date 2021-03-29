package ru.shark.home.l2info.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.shark.home.l2info.dao.common.PageableList;
import ru.shark.home.l2info.dao.entity.BaseEntity;
import ru.shark.home.l2info.util.TestDataLoader;
import ru.shark.home.l2info.util.TestEntityFinder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Transactional
public class DaoServiceTest {
    @Autowired
    private TestDataLoader testDataLoader;

    @Autowired
    protected TestEntityFinder entityFinder;

    @PersistenceContext
    protected EntityManager em;

    protected <T extends BaseEntity> void checkPagingList(PageableList<T> list, Integer count, Long total) {
        Assertions.assertNotNull(list);
        Assertions.assertNotNull(list.getData());
        Assertions.assertEquals(count, list.getData().size());
        Assertions.assertEquals(list.getTotalCount(), total);
    }

    protected void loadWeapons(String... files) {
        testDataLoader.cleanUp();
        testDataLoader.loadWeapons(files);
    }

    protected <E extends BaseEntity> boolean isDeleted(E entity) {
        Long count = (Long) em.createQuery("select count(e.id) from " + entity.getClass().getSimpleName() + " e " +
                "where e.id = " +
                entity.getId())
                .getSingleResult();
        return count == 0;
    }

    protected <E extends BaseEntity> boolean isDeleted(Long id, Class<E> entityClass) {
        Long count = (Long) em.createQuery("select count(e.id) from " + entityClass.getSimpleName() + " e " +
                "where e.id = " +
                id)
                .getSingleResult();
        return count == 0;
    }
}
