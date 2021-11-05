package ru.shark.home.l2info.datamanager;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.shark.home.common.dao.dto.EnumDto;
import ru.shark.home.l2info.dao.dto.ClassDto;
import ru.shark.home.l2info.dao.dto.RaceDto;
import ru.shark.home.l2info.dao.entity.ClassEntity;
import ru.shark.home.l2info.util.DaoServiceTest;

import static org.junit.jupiter.api.Assertions.*;

public class ClassDataManagerTest extends DaoServiceTest {
    @Autowired
    private ClassDataManager classDataManager;

    @BeforeAll
    public void init() {
        loadRace("ClassDataManagerTest/race.json");
        loadClass("ClassDataManagerTest/class.json");
    }

    @Test
    public void findById() {
        // GIVEN
        ClassEntity entity = entityFinder.findClass("Warrior");

        // WHEN
        ClassDto dto = classDataManager.findById(entity.getId());

        // THEN
        assertNotNull(dto);
        assertEquals(entity.getId(), dto.getId());
        assertEquals(entity.getName(), dto.getName());
    }

    @Test
    public void save() {
        // GIVEN
        ClassDto dto = prepareDto(entityFinder.findRaceId("human"), "Warrior");

        // WHEN
        ClassDto saved = classDataManager.save(dto);

        // THEN
        assertNotNull(dto);
        assertEquals(dto.getId(), saved.getId());
        assertEquals(dto.getName(), saved.getName());
        assertEquals(dto.getType().getCode(), "MAGE");
        assertEquals(dto.getRace().getId(), saved.getRace().getId());
    }

    @Test
    public void deleteById() {
        // GIVEN
        Long id = entityFinder.findClassId("warrior");

        // WHEN
        classDataManager.deleteById(id);

        // THEN
        assertTrue(isDeleted(id, ClassEntity.class));
    }

    private ClassDto prepareDto(Long raceId, String name) {
        ClassDto dto = new ClassDto();
        if (name != null) {
            dto.setId(entityFinder.findClassId(name));
            dto.setName(name);
        } else {
            dto.setName("newClass");
        }
        dto.setRace(new RaceDto());
        dto.getRace().setId(raceId);
        dto.setType(new EnumDto("MAGE", null));

        return dto;
    }
}
