package ru.shark.home.l2info.dao.specification;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import ru.shark.home.l2info.dao.common.RequestCriteria;
import ru.shark.home.l2info.dao.common.RequestFilter;
import ru.shark.home.l2info.dao.entity.WeaponEntity;
import ru.shark.home.l2info.enums.FieldType;
import ru.shark.home.l2info.enums.FilterOperation;
import ru.shark.home.l2info.enums.WeaponType;

import javax.persistence.criteria.Root;
import java.text.MessageFormat;
import java.util.Arrays;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static ru.shark.home.l2info.common.ErrorConstants.INVALID_NUMBER_FILTER_VALUE;
import static ru.shark.home.l2info.common.ErrorConstants.UNKNOWN_FILTER_FIELD;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SpecificationRequestTest {
    private SpecificationRequest specificationRequest;
    private RequestCriteria requestCriteria;

    @BeforeAll
    public void init() {
        requestCriteria = new RequestCriteria(0, 10);
        requestCriteria.setFilters(Arrays.asList(new RequestFilter("name", FieldType.STRING,
                        FilterOperation.EQ.getValue(), "Bow"),
                new RequestFilter("pAtk", FieldType.INTEGER,
                        FilterOperation.EQ.getValue(), "123"),
                new RequestFilter("type", FieldType.ENUM,
                        FilterOperation.EQ.getValue(), "BOW")));
        specificationRequest = new SpecificationRequest(requestCriteria);
    }

    @Test
    public void getEnumValue() {
        // GIVEN
        Root root = mock(Root.class);
        when(root.getJavaType()).thenReturn(WeaponEntity.class);

        // WHEN
        WeaponType enumValue = (WeaponType) specificationRequest.getEnumValue("type", root, "BOW");

        // THEN
        Assertions.assertNotNull(enumValue);
        Assertions.assertEquals(WeaponType.BOW, enumValue);
    }

    @Test
    public void getEnumValueWithUnknownField() {
        // GIVEN
        Root root = mock(Root.class);
        when(root.getJavaType()).thenReturn(WeaponEntity.class);
        String expectedException = MessageFormat.format(UNKNOWN_FILTER_FIELD, "field");

        // WHEN
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () ->
                specificationRequest.getEnumValue("field", root, "BOW"));

        // THEN
        Assertions.assertEquals(expectedException, exception.getMessage());
    }

    @Test
    public void getValueWithString() {
        // GIVEN
        RequestFilter requestFilter = new RequestFilter("name", FieldType.STRING,
                FilterOperation.EQ.getValue(), "Bow");
        Root root = mock(Root.class);

        // WHEN
        Object value = specificationRequest.getValue(requestFilter, root);

        // THEN
        Assertions.assertNotNull(value);
        Assertions.assertEquals(value, "Bow");
    }

    @Test
    public void getValueWithInteger() {
        // GIVEN
        RequestFilter requestFilter = new RequestFilter("pAtk", FieldType.INTEGER,
                FilterOperation.EQ.getValue(), "123");
        Root root = mock(Root.class);

        // WHEN
        Object value = specificationRequest.getValue(requestFilter, root);

        // THEN
        Assertions.assertNotNull(value);
        Assertions.assertEquals(value, 123L);
    }

    @Test
    public void getValueWithIntegerInvalid() {
        // GIVEN
        RequestFilter requestFilter = new RequestFilter("pAtk", FieldType.INTEGER,
                FilterOperation.EQ.getValue(), "1aaa23");
        Root root = mock(Root.class);
        String expectedException = MessageFormat.format(INVALID_NUMBER_FILTER_VALUE, requestFilter.getValue());

        // WHEN
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class,
                () -> specificationRequest.getValue(requestFilter, root));

        // THEN
        Assertions.assertEquals(expectedException, exception.getMessage());
    }

    @Test
    public void getValueWithEnum() {
        // GIVEN
        RequestFilter requestFilter = new RequestFilter("type", FieldType.ENUM,
                FilterOperation.EQ.getValue(), "BOW");
        Root root = mock(Root.class);
        when(root.getJavaType()).thenReturn(WeaponEntity.class);

        // WHEN
        Object value = specificationRequest.getValue(requestFilter, root);

        // THEN
        Assertions.assertNotNull(value);
        Assertions.assertEquals(value, WeaponType.BOW);
    }
}
