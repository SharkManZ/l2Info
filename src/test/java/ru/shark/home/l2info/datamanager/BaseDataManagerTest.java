package ru.shark.home.l2info.datamanager;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.shark.home.common.dao.service.BaseDao;
import ru.shark.home.common.dao.util.ConverterUtil;
import ru.shark.home.common.datamanager.BaseDataManager;
import ru.shark.home.l2info.dao.dto.WeaponDto;
import ru.shark.home.l2info.dao.entity.WeaponEntity;
import ru.shark.home.l2info.dao.service.WeaponDao;
import ru.shark.home.l2info.enums.Grade;
import ru.shark.home.l2info.enums.WeaponType;
import ru.shark.home.l2info.util.DaoServiceTest;

import static org.junit.jupiter.api.Assertions.*;


public class BaseDataManagerTest extends DaoServiceTest {

    @Autowired
    private WeaponDao weaponDao;
    @Autowired
    private ConverterUtil converterUtil;
    private BaseDataManagerImpl dataManager;

    @BeforeAll
    public void init() {
        dataManager = new BaseDataManagerImpl(weaponDao);
        dataManager.setConverterUtil(converterUtil);
        loadWeapons("BaseDataManagerTest/weapons.json");
    }

    @Test
    public void saveWithCreate() {
        // GIVEN
        WeaponDto dto = prepareDto(1L, "Bop");

        // WHEN
        WeaponDto saved = (WeaponDto) dataManager.save(dto);

        // THEN
        assertNotNull(saved);
        assertNotNull(saved.getId());
        assertEquals(dto.getName(), saved.getName());
        assertEquals(dto.getType(), saved.getType());
        assertEquals(dto.getGrade(), saved.getGrade());
        assertEquals(dto.getpAtk(), saved.getpAtk());
        assertEquals(dto.getmAtk(), saved.getmAtk());
    }

    @Test
    public void saveWithUpdate() {
        // GIVEN
        WeaponDto dto = prepareDto(1L, "Bop");
        dto.setId(entityFinder.findWeapon("Bow Of Peril").getId());

        // WHEN
        WeaponDto saved = (WeaponDto) dataManager.save(dto);

        // THEN
        assertNotNull(saved);
        assertEquals(saved.getId(), dto.getId());
        assertEquals(dto.getName(), saved.getName());
        assertEquals(dto.getType(), saved.getType());
        assertEquals(dto.getGrade(), saved.getGrade());
        assertEquals(dto.getpAtk(), saved.getpAtk());
        assertEquals(dto.getmAtk(), saved.getmAtk());
    }

    @Test
    public void deleteById() {
        // GIVEN
        Long id = entityFinder.findWeapon("Bow Of Peril").getId();

        // WHEN
        dataManager.deleteById(id);

        // THEN
        assertTrue(isDeleted(id, WeaponEntity.class));
    }

    @Test
    public void findById() {
        // GIVEN
        WeaponEntity entity = entityFinder.findWeapon("Bow Of Peril");
        Long id = entity.getId();

        // WHEN
        WeaponDto dto = (WeaponDto) dataManager.findById(id);

        // THEN
        assertNotNull(dto);
        assertEquals(id, dto.getId());
        assertEquals(entity.getName(), dto.getName());
        assertEquals(entity.getType(), dto.getType());
        assertEquals(entity.getGrade(), dto.getGrade());
        assertEquals(entity.getpAtk(), dto.getpAtk());
        assertEquals(entity.getmAtk(), dto.getmAtk());
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

    private class BaseDataManagerImpl extends BaseDataManager {

        public BaseDataManagerImpl(BaseDao dao) {
            super(weaponDao, WeaponDto.class);
        }
    }
}
