package ru.shark.home.l2info.dao.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.shark.home.l2info.dao.dto.WeaponDto;
import ru.shark.home.l2info.dao.entity.WeaponEntity;
import ru.shark.home.l2info.enums.Grade;
import ru.shark.home.l2info.enums.WeaponType;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.util.ObjectUtils.isEmpty;

@SpringBootTest
public class ConverterUtilTest {

    @Autowired
    private ConverterUtil converterUtil;

    @Test
    public void entityToDto() {
        // GIVEN
        WeaponEntity entity = prepareEntity(1L, "Bop");

        // WHEN
        WeaponDto dto = converterUtil.entityToDto(entity, WeaponDto.class);

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
    public void dtoToEntity() {
        // GIVEN
        WeaponDto dto = prepareDto(1L, "Bop");

        // WHEN
        WeaponEntity entity = converterUtil.dtoToEntity(dto, WeaponEntity.class);

        // THEN
        assertNotNull(entity);
        assertEquals(dto.getId(), entity.getId());
        assertEquals(dto.getName(), entity.getName());
        assertEquals(dto.getType(), entity.getType());
        assertEquals(dto.getGrade(), entity.getGrade());
        assertEquals(dto.getpAtk(), entity.getpAtk());
        assertEquals(dto.getmAtk(), entity.getmAtk());
    }

    @Test
    public void entityListToDtoList() {
        // GIVEN
        List<WeaponEntity> list = Arrays.asList(prepareEntity(1L, "Bop"),
                prepareEntity(2L, "Emic"));

        // WHEN
        List<WeaponDto> result = converterUtil.entityListToDtoList(list, WeaponDto.class);

        // THEN
        Assertions.assertFalse(isEmpty(result));
        AtomicInteger idx = new AtomicInteger(0);
        for (WeaponDto dto : result) {
            WeaponEntity entity = list.get(idx.getAndIncrement());

            assertNotNull(dto);
            assertNotNull(entity);
            assertEquals(entity.getId(), dto.getId());
            assertEquals(entity.getName(), dto.getName());
            assertEquals(entity.getType(), dto.getType());
            assertEquals(entity.getGrade(), dto.getGrade());
            assertEquals(entity.getpAtk(), dto.getpAtk());
            assertEquals(entity.getmAtk(), dto.getmAtk());
        }
    }

    @Test
    public void dtoListToEntityList() {
        // GIVEN
        List<WeaponDto> list = Arrays.asList(prepareDto(1L, "Bop"),
                prepareDto(2L, "Emic"));

        // WHEN
        List<WeaponEntity> result = converterUtil.dtoListToEntityList(list, WeaponEntity.class);

        // THEN
        Assertions.assertFalse(isEmpty(result));
        AtomicInteger idx = new AtomicInteger(0);
        for (WeaponEntity entity : result) {
            WeaponDto dto = list.get(idx.getAndIncrement());

            assertNotNull(entity);
            assertNotNull(dto);
            assertEquals(dto.getId(), entity.getId());
            assertEquals(dto.getName(), entity.getName());
            assertEquals(dto.getType(), entity.getType());
            assertEquals(dto.getGrade(), entity.getGrade());
            assertEquals(dto.getpAtk(), entity.getpAtk());
            assertEquals(dto.getmAtk(), entity.getmAtk());
        }
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
