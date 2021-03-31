package ru.shark.home.l2info.services;

import ru.shark.home.l2info.dao.common.RequestCriteria;
import ru.shark.home.l2info.dao.common.RequestFilter;
import ru.shark.home.l2info.services.dto.PageRequest;

import java.util.stream.Collectors;

import static org.springframework.util.ObjectUtils.isEmpty;

public class BaseService {

    protected RequestCriteria getCriteria(PageRequest request) {
        RequestCriteria criteria = new RequestCriteria(request.getPage(), request.getSize());
        if (!isEmpty(request.getFilters())) {
            criteria.setFilters(request.getFilters().stream().map(item -> new RequestFilter(item.getField(),
                    item.getOperator(), item.getValue())).collect(Collectors.toList()));
        }

        return criteria;
    }
}
