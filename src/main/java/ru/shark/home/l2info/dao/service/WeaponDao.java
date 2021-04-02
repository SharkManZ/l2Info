package ru.shark.home.l2info.dao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import ru.shark.home.l2info.dao.common.PageableList;
import ru.shark.home.l2info.dao.common.RequestCriteria;
import ru.shark.home.l2info.dao.entity.BaseEntity;
import ru.shark.home.l2info.dao.entity.WeaponEntity;
import ru.shark.home.l2info.dao.repository.WeaponRepository;
import ru.shark.home.l2info.dao.util.SpecificationUtils;

import java.text.MessageFormat;

import static ru.shark.home.l2info.common.ErrorConstants.*;

@Component
public class WeaponDao extends BaseDao<WeaponEntity> {
    private static final String NAME_FIELD = "name";

    private WeaponRepository weaponRepository;

    protected WeaponDao() {
        super(WeaponEntity.class);
    }

    public PageableList<WeaponEntity> getWithPagination(RequestCriteria request) {
        Specification<BaseEntity> searchSpec = SpecificationUtils.searchSpecification(request.getSearch(), NAME_FIELD);
        return weaponRepository.getWithPagination(request, searchSpec, NAME_FIELD);
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

    @Override
    public void deleteById(Long id) {
        WeaponEntity entity = findById(id);

        if (entity == null) {
            throw new IllegalArgumentException(MessageFormat.format(ENTITY_NOT_FOUND_BY_ID,
                    WeaponEntity.getDescription(), id));
        }

        super.deleteById(id);
    }

    @Autowired
    public void setWeaponRepository(WeaponRepository weaponRepository) {
        this.weaponRepository = weaponRepository;
    }
}
