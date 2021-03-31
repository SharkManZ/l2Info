package ru.shark.home.l2info.dao.repository;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import ru.shark.home.l2info.dao.common.PageableList;
import ru.shark.home.l2info.dao.common.RequestCriteria;
import ru.shark.home.l2info.dao.entity.BaseEntity;

import javax.persistence.EntityManager;

public class JpaBaseRepository<E extends BaseEntity> extends SimpleJpaRepository<E, Long> implements BaseRepository<E> {


    public JpaBaseRepository(JpaEntityInformation<E, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
    }

    @Override
    public PageableList<E> getWithPagination(RequestCriteria request) {
        return null;
    }
}
