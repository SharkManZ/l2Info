package ru.shark.home.l2info.dao.entity;

import ru.shark.home.l2info.dao.entity.api.WithId;

import javax.persistence.MappedSuperclass;

/**
 * Базовая сущность.
 */
@MappedSuperclass
public abstract class BaseEntity implements WithId {
}
