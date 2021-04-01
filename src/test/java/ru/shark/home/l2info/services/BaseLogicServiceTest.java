package ru.shark.home.l2info.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.util.ObjectUtils;
import ru.shark.home.l2info.dao.common.RequestCriteria;
import ru.shark.home.l2info.dao.common.RequestFilter;
import ru.shark.home.l2info.dao.dto.WeaponDto;
import ru.shark.home.l2info.enums.FieldType;
import ru.shark.home.l2info.enums.FilterOperation;
import ru.shark.home.l2info.services.dto.Filter;
import ru.shark.home.l2info.services.dto.PageRequest;
import ru.shark.home.l2info.util.BaseServiceTest;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.util.ObjectUtils.isEmpty;

public class BaseLogicServiceTest extends BaseServiceTest {
    private BaseLogicService baseLogicService;

    @BeforeAll
    public void init() {
        baseLogicService = new BaseLogicService();
    }

    @Test
    public void getCriteria() {
        // GIVEN
        PageRequest request = new PageRequest(0, 10);
        request.setFilters(Arrays.asList(new Filter("grade", FilterOperation.EQ.getValue(), "A")));

        // WHEN
        RequestCriteria criteria = baseLogicService.getCriteria(request, WeaponDto.class);

        // THEN
        assertNotNull(criteria);
        assertEquals(request.getPage(), criteria.getPage());
        assertEquals(request.getSize(), criteria.getSize());
        assertFalse(isEmpty(criteria.getFilters()));
        assertEquals(request.getFilters().size(), criteria.getFilters().size());
        Filter expected = request.getFilters().get(0);
        RequestFilter actual = criteria.getFilters().get(0);
        assertEquals(expected.getField(), actual.getField());
        assertEquals(expected.getOperator(), actual.getOperation().getValue());
        assertEquals(expected.getValue(), actual.getValue());
    }

    @Test
    public void getFieldType() {
        // GIVEN
        String stringField = "name";
        String intField = "pAtk";
        String longField = "id";
        String enumField = "type";

        // WHEN
        FieldType stringType = baseLogicService.getFieldType(WeaponDto.class, stringField);
        FieldType intType = baseLogicService.getFieldType(WeaponDto.class, intField);
        FieldType longType = baseLogicService.getFieldType(WeaponDto.class, longField);
        FieldType enumType = baseLogicService.getFieldType(WeaponDto.class, enumField);

        // THEN
        assertEquals(FieldType.STRING, stringType);
        assertEquals(FieldType.INTEGER, intType);
        assertEquals(FieldType.INTEGER, longType);
        assertEquals(FieldType.ENUM, enumType);
    }
}
