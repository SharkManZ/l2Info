package ru.shark.home.l2info.dao.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.shark.home.l2info.dao.entity.WeaponEntity;

@Repository
public interface WeaponRepository extends PagingAndSortingRepository<WeaponEntity, Long> {

    /**
     * Возвращает оружие по названию.
     *
     * @param name название.
     * @return сущность оружия.
     */
    @Query(name = "findWeaponByName")
    WeaponEntity findWeaponByName(@Param("name") String name);
}
