package ru.shark.home.l2info.dao.service;

import org.springframework.data.repository.PagingAndSortingRepository;
import ru.shark.home.l2info.dao.entity.BaseEntity;

import javax.transaction.Transactional;

@Transactional
public abstract class BaseDao<T extends PagingAndSortingRepository> {
    public <E extends BaseEntity> E save(E entity) {
        return (E) getRepository().save(entity);
    }

    protected abstract T getRepository();
}
