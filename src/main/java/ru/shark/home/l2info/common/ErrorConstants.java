package ru.shark.home.l2info.common;

/**
 * Константы ошибок.
 */
public class ErrorConstants {
    /**
     * Коды ошибок.
     */
    public static final String ERR_500 = "500";

    public static final String INVALID_FILTER_OPERATION = "Не поддерживаемая операция фильтрации \"{0}\"";
    public static final String UNSUPPORTED_FILTER_FIELD_TYPE = "Тип поля \"{0}\" не поддерживается в фильтрах";
    public static final String INVALID_NUMBER_FILTER_VALUE = "Передано не верное числовое значение фильтра {0}";
    public static final String UNKNOWN_FILTER_FIELD = "Поле \"{0}\" отсутствует в сущности";

    public static final String EMPTY_ENTITY = "Не передана сущность \"{0}\"";
    public static final String ENTITY_ALREADY_EXISTS = "Сущность \"{0}\" с ключом \"{1}\" уже существует";
    public static final String ENTITY_NOT_FOUND_BY_ID = "Сущность \"{0}\" с идентификатором {1} не найдена";

}
