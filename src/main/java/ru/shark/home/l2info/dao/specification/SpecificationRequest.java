package ru.shark.home.l2info.dao.specification;

import org.springframework.data.jpa.domain.Specification;
import ru.shark.home.l2info.dao.common.RequestCriteria;
import ru.shark.home.l2info.dao.common.RequestFilter;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.text.MessageFormat;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.util.ObjectUtils.isEmpty;
import static ru.shark.home.l2info.common.ErrorConstants.INVALID_FILTER_OPERATION;

public class SpecificationRequest implements Specification {
    private List<RequestFilter> filters;

    public SpecificationRequest(RequestCriteria requestCriteria) {
        this.filters = requestCriteria.getFilters();
    }

    @Override
    public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {

        if (isEmpty(filters)) {
            return null;
        }

        List<Predicate> predicates = filters.stream()
                .map(item -> buildPredicate(item, root, criteriaBuilder))
                .collect(Collectors.toList());

        return predicates.size() > 1 ? criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()])) :
                predicates.get(0);
    }

    private Predicate buildPredicate(RequestFilter filter, Root root, CriteriaBuilder criteriaBuilder) {
        switch (filter.getOperation()) {
            case EQ:
                return buildEqualsPredicate(filter, root, criteriaBuilder);
            default:
                throw new IllegalArgumentException(MessageFormat.format(INVALID_FILTER_OPERATION,
                        filter.getOperation().getValue()));
        }
    }

    private Predicate buildEqualsPredicate(RequestFilter filter, Root root, CriteriaBuilder criteriaBuilder) {
        if (root.get(filter.getField()).getJavaType() == String.class) {
            return criteriaBuilder.equal(criteriaBuilder.lower(root.get(filter.getField())),
                    filter.getValue().toLowerCase());
        }
        return null;
    }
}
