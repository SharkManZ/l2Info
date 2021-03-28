package ru.shark.home.l2info.dao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import ru.shark.home.l2info.dao.common.PageableList;
import ru.shark.home.l2info.dao.entity.WeaponEntity;
import ru.shark.home.l2info.repository.WeaponRepository;

@Component
public class WeaponDao extends BaseDao<WeaponRepository> {
    private WeaponRepository weaponRepository;

    public PageableList<WeaponEntity> getWithPagination(Pageable pageable) {
        Page<WeaponEntity> list = weaponRepository.findAll(pageable);

        return new PageableList<>(list.getContent(), list.getTotalElements());
    }

    @Override
    protected WeaponRepository getRepository() {
        return weaponRepository;
    }

    @Autowired
    public void setWeaponRepository(WeaponRepository weaponRepository) {
        this.weaponRepository = weaponRepository;
    }
}
