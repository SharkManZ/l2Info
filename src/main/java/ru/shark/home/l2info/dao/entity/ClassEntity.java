package ru.shark.home.l2info.dao.entity;

import ru.shark.home.common.dao.entity.BaseEntity;
import ru.shark.home.l2info.enums.ClassType;

import javax.persistence.*;

/**
 * Сущность "Класс персонажа".
 */
@Entity
@Table(name = "L2_CLASS")
public class ClassEntity extends BaseEntity {
    private static final String DESCRIPTION = "Класс персонажа";

    /**
     * Уникальный идентификатор записи.
     */
    @Id
    @Column(name = "L2_ID")
    @SequenceGenerator(name = "ClassGenerator", sequenceName = "L2_CLASS_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ClassGenerator")
    private Long id;

    @Column(name = "L2_NAME", nullable = false)
    private String name;

    @Column(name = "L2_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private ClassType type;

    @ManyToOne
    @JoinColumn(name = "L2_RACE_ID", nullable = false)
    private RaceEntity race;

    @ManyToOne
    @JoinColumn(name = "L2_PARENT_CLASS_ID")
    private ClassEntity parent;

    @Column(name = "L2_LEVEL", nullable = false)
    private Integer level;

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

    public RaceEntity getRace() {
        return race;
    }

    public void setRace(RaceEntity race) {
        this.race = race;
    }

    public ClassEntity getParent() {
        return parent;
    }

    public void setParent(ClassEntity parent) {
        this.parent = parent;
    }

    public ClassType getType() {
        return type;
    }

    public void setType(ClassType type) {
        this.type = type;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public static String getDescription() {
        return DESCRIPTION;
    }
}
