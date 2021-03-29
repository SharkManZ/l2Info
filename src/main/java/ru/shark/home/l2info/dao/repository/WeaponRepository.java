package ru.shark.home.l2info.dao.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.shark.home.l2info.dao.entity.WeaponEntity;

@Repository
public interface WeaponRepository extends PagingAndSortingRepository<WeaponEntity, Long> {
}
