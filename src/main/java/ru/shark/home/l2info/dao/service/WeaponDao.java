package ru.shark.home.l2info.dao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import ru.shark.home.l2info.dao.common.PageableList;
import ru.shark.home.l2info.dao.entity.WeaponEntity;
import ru.shark.home.l2info.dao.repository.WeaponRepository;

import java.text.MessageFormat;

import static ru.shark.home.l2info.common.ErrorConstants.EMPTY_ENTITY;
import static ru.shark.home.l2info.common.ErrorConstants.ENTITY_ALREADY_EXISTS;

@Component
public class WeaponDao extends BaseDao<WeaponEntity> {
    private WeaponRepository weaponRepository;

    protected WeaponDao() {
        super(WeaponEntity.class);
    }

    public PageableList<WeaponEntity> getWithPagination(Pageable pageable) {
        Page<WeaponEntity> list = weaponRepository.findAll(pageable);

        return new PageableList<>(list.getContent(), list.getTotalElements());
    }

    @Override
    public WeaponEntity save(WeaponEntity entity) {
        if (entity == null) {
            throw new IllegalArgumentException(MessageFormat.format(EMPTY_ENTITY, WeaponEntity.getDescription()));
        }

        WeaponEntity byName = weaponRepository.findWeaponByName(entity.getName());
        if (byName != null && (entity.getId() == null || !entity.getId().equals(byName.getId()))) {
            throw new IllegalArgumentException(MessageFormat.format(ENTITY_ALREADY_EXISTS,
                    WeaponEntity.getDescription(), entity.getName()));
        }

        return super.save(entity);
    }

    @Autowired
    public void setWeaponRepository(WeaponRepository weaponRepository) {
        this.weaponRepository = weaponRepository;
    }
}
