package ru.shark.home.l2info.dao.entity;

import ru.shark.home.common.dao.entity.BaseEntity;

import javax.persistence.*;

/**
 * Сущность "Раса персонажа".
 */
@Entity
@Table(name = "L2_RACE")
public class RaceEntity extends BaseEntity {
    private static final String DESCRIPTION = "Раса персонажа";

    /**
     * Уникальный идентификатор записи.
     */
    @Id
    @Column(name = "L2_ID")
    @SequenceGenerator(name = "RaceGenerator", sequenceName = "L2_RACE_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RaceGenerator")
    private Long id;

    @Column(name = "L2_NAME", nullable = false)
    private String name;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static String getDescription() {
        return DESCRIPTION;
    }
}
