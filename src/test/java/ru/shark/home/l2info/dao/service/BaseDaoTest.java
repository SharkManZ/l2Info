package ru.shark.home.l2info.dao.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.shark.home.l2info.dao.common.PageableList;
import ru.shark.home.l2info.dao.entity.BaseEntity;
import ru.shark.home.l2info.util.TestDataLoader;

import javax.transaction.Transactional;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Transactional
public class BaseDaoTest {
    @Autowired
    private TestDataLoader testDataLoader;

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
}
