package ru.shark.home.l2info.dao.service;

import org.springframework.beans.factory.annotation.Autowired;
import ru.shark.home.l2info.dao.entity.BaseEntity;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Transactional
public abstract class BaseDao<E extends BaseEntity> {

    private EntityManager em;
    private final Class<E> entityClass;

    protected BaseDao(Class<E> entityClass) {
        this.entityClass = entityClass;
    }

    public E save(E entity) {
        return (E) em.merge(entity);
    }

    public void delete(E entity) {
        em.remove(entity);
    }

    public E findById(Long id) {
        return em.find(entityClass, id);
    }

    public Class<E> getEntityClass() {
        return entityClass;
    }

    @Autowired
    public void setEm(EntityManager em) {
        this.em = em;
    }
}
