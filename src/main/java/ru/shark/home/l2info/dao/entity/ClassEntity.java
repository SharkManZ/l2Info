package ru.shark.home.l2info.dao.entity;

import javax.persistence.*;

/**
 * Сущность "Класс персонажа".
 */
@Entity
@Table(name = "L2_WEAPON")
public class ClassEntity extends BaseEntity{
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

    @ManyToOne
    @JoinColumn(name = "L2_RACE_ID", nullable = false)
    private RaceEntity race;

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

    public static String getDescription() {
        return DESCRIPTION;
    }
}
