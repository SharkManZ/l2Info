package ru.shark.home.l2info.dao.entity;

import javax.persistence.MappedSuperclass;

/**
 * Базовая сущность.
 */
@MappedSuperclass
public abstract class BaseEntity {
    public abstract Long getId();
    public abstract void setId(Long id);
}
