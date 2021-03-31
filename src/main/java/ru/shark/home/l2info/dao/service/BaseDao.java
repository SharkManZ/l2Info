package ru.shark.home.l2info.dao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import ru.shark.home.l2info.dao.common.PageableList;
import ru.shark.home.l2info.dao.common.RequestCriteria;
import ru.shark.home.l2info.dao.entity.BaseEntity;
import ru.shark.home.l2info.dao.repository.BaseRepository;
import ru.shark.home.l2info.dao.specification.SpecificationRequest;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

/**
 * Базовый класс для сервисов доступа к данным.
 */
@Transactional(Transactional.TxType.REQUIRED)
public abstract class BaseDao<E extends BaseEntity> {

    private EntityManager em;
    private final Class<E> entityClass;

    protected BaseDao(Class<E> entityClass) {
        this.entityClass = entityClass;
    }

    public PageableList<E> getWithPagination(RequestCriteria request) {
        Page<E> all = getRepository().findAll(new SpecificationRequest(request),
                PageRequest.of(request.getPage(), request.getSize()));
        return new PageableList<>(all.getContent(), all.getTotalElements());
    }

    /**
     * Сохранение сущности.
     *
     * @param entity сущность для сохранения
     * @return сохраненная сущность
     */
    public E save(E entity) {
        return (E) em.merge(entity);
    }

    /**
     * Удаление сущности по идентификатору.
     */
    public void deleteById(Long id) {
        em.remove(findById(id));
    }

    /**
     * Удаление сущности.
     *
     * @param entity сущность для удаления
     */
    public void delete(E entity) {
        deleteById(entity.getId());
    }

    /**
     * Возвращает сущность по идентификатору
     *
     * @param id идентификатор
     * @return сущность
     */
    public E findById(Long id) {
        return em.find(entityClass, id);
    }

    /**
     * Возвращает класс сущности.
     */
    public Class<E> getEntityClass() {
        return entityClass;
    }

    public abstract BaseRepository<E> getRepository();

    @Autowired
    public void setEm(EntityManager em) {
        this.em = em;
    }
}
