package ru.shark.home.l2info.dao.common;

import ru.shark.home.l2info.enums.FilterOperation;

import java.text.MessageFormat;

import static ru.shark.home.l2info.common.ErrorConstants.INVALID_FILTER_OPERATION;

public class RequestFilter {
    private String field;
    private FilterOperation operation;
    private String value;

    public RequestFilter(String field, String operation, String value) {
        this.field = field;
        this.operation = FilterOperation.byValue(operation);
        if (this.operation == null) {
            throw new IllegalArgumentException(MessageFormat.format(INVALID_FILTER_OPERATION, operation));
        }
        this.value = value;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public FilterOperation getOperation() {
        return operation;
    }

    public void setOperation(FilterOperation operation) {
        this.operation = operation;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
