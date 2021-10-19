package ru.shark.home.l2info.datamanager;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.shark.home.l2info.dao.dto.WeaponDto;
import ru.shark.home.l2info.dao.entity.WeaponEntity;
import ru.shark.home.l2info.enums.Grade;
import ru.shark.home.l2info.enums.WeaponType;
import ru.shark.home.l2info.util.DaoServiceTest;

import static org.junit.jupiter.api.Assertions.*;

public class WeaponDataManagerTest extends DaoServiceTest {
    @Autowired
    private WeaponDataManager weaponDataManager;

    @BeforeAll
    public void init() {
        loadWeapons("WeaponDataManagerTest/weapons.json");
    }

    @Test
    public void findById() {
        // GIVEN
        WeaponEntity entity = entityFinder.findWeapon("bow of peril");

        // WHEN
        WeaponDto dto = weaponDataManager.findById(entity.getId());

        // THEN
        assertNotNull(dto);
        assertEquals(entity.getId(), dto.getId());
        assertEquals(entity.getName(), dto.getName());
        assertEquals(entity.getType(), dto.getType());
        assertEquals(entity.getGrade(), dto.getGrade());
        assertEquals(entity.getpAtk(), dto.getpAtk());
        assertEquals(entity.getmAtk(), dto.getmAtk());
    }

    @Test
    public void save() {
        // GIVEN
        WeaponDto dto = prepareDto(entityFinder.findWeapon("bow of peril").getId(), "bow of peril");

        // WHEN
        WeaponDto saved = weaponDataManager.save(dto);

        // THEN
        assertNotNull(dto);
        assertEquals(dto.getId(), saved.getId());
        assertEquals(dto.getType(), saved.getType());
        assertEquals(dto.getGrade(), saved.getGrade());
        assertEquals(dto.getpAtk(), saved.getpAtk());
        assertEquals(dto.getmAtk(), saved.getmAtk());
    }

    @Test
    public void deleteById() {
        // GIVEN
        Long id = entityFinder.findWeapon("bow of peril").getId();

        // WHEN
        weaponDataManager.deleteById(id);

        // THEN
        assertTrue(isDeleted(id, WeaponEntity.class));
    }

    private WeaponDto prepareDto(Long id, String name) {
        WeaponDto dto = new WeaponDto();
        dto.setId(id);
        dto.setName(name);
        dto.setType(WeaponType.BOW);
        dto.setGrade(Grade.B);
        dto.setpAtk(100);
        dto.setmAtk(100);

        return dto;
    }
}
