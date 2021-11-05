package ru.shark.home.l2info.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.shark.home.l2info.dao.entity.ClassEntity;
import ru.shark.home.l2info.dao.entity.RaceEntity;
import ru.shark.home.l2info.dao.entity.WeaponEntity;

import javax.persistence.EntityManager;

@Component
public class TestEntityFinder {
    @Autowired
    private EntityManager em;

    public WeaponEntity findWeapon(String name) {
        return (WeaponEntity) em.createQuery("select w from WeaponEntity w where lower(w.name ) = lower(:name)")
                .setParameter("name", name)
                .getSingleResult();
    }

    public Long findWeaponId(String name) {
        return (Long) em.createQuery("select w.id from WeaponEntity w where lower(w.name ) = lower(:name)")
                .setParameter("name", name)
                .getSingleResult();
    }

    public RaceEntity findRace(String name) {
        return (RaceEntity) em.createQuery("select r from RaceEntity r where lower(r.name ) = lower(:name)")
                .setParameter("name", name)
                .getSingleResult();
    }

    public Long findRaceId(String name) {
        return (Long) em.createQuery("select r.id from RaceEntity r where lower(r.name ) = lower(:name)")
                .setParameter("name", name)
                .getSingleResult();
    }

    public ClassEntity findClass(String name) {
        return (ClassEntity) em.createQuery("select r from ClassEntity r where lower(r.name ) = lower(:name)")
                .setParameter("name", name)
                .getSingleResult();
    }

    public Long findClassId(String name) {
        return (Long) em.createQuery("select r.id from ClassEntity r where lower(r.name ) = lower(:name)")
                .setParameter("name", name)
                .getSingleResult();
    }
}
