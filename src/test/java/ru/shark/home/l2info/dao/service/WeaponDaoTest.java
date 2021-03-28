package ru.shark.home.l2info.dao.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import ru.shark.home.l2info.dao.common.PageableList;
import ru.shark.home.l2info.dao.entity.WeaponEntity;

public class WeaponDaoTest extends BaseDaoTest{
    @Autowired
    private WeaponDao weaponDao;

    @BeforeAll
    public void init() {
        loadWeapons("WeaponDaoTest/weapons.json");
    }

    @Test
    public void getWithPagination() {
        // WHEN
        PageableList<WeaponEntity> list = weaponDao.getWithPagination(PageRequest.of(0, 2));

        // THEN
        checkPagingList(list, 2, 2L);
    }
}
