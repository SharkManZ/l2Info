package ru.shark.home.l2info.dao.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.springframework.beans.factory.annotation.Autowired;
import ru.shark.home.l2info.dao.entity.WeaponEntity;
import ru.shark.home.l2info.util.DaoServiceTest;

public class WeaponRepositoryTest extends DaoServiceTest {
    @Autowired
    private WeaponRepository weaponRepository;

    @BeforeAll
    public void init() {
        loadWeapons("WeaponRepositoryTest/weapons.json");
    }

    @Test
    public void findWeaponByName() {
        // GIVEN
        WeaponEntity entity = entityFinder.findWeapon("Bow of Peril");

        // WHEN
        WeaponEntity weaponByName = weaponRepository.findWeaponByName(entity.getName());
        Assertions.assertTrue(new ReflectionEquals(entity).matches(weaponByName));
    }
}
