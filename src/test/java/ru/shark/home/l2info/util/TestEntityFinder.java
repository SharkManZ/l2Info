package ru.shark.home.l2info.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
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
}
