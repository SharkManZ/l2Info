package ru.shark.home.l2info.dao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import ru.shark.home.l2info.dao.common.PageableList;
import ru.shark.home.l2info.dao.common.RequestCriteria;
import ru.shark.home.l2info.dao.entity.BaseEntity;
import ru.shark.home.l2info.dao.entity.RaceEntity;
import ru.shark.home.l2info.dao.entity.WeaponEntity;
import ru.shark.home.l2info.dao.repository.RaceRepository;
import ru.shark.home.l2info.dao.util.SpecificationUtils;

import java.text.MessageFormat;

import static ru.shark.home.l2info.common.ErrorConstants.*;

@Component
public class RaceDao extends BaseDao<RaceEntity> {
    private static final String NAME_FIELD = "name";

    private RaceRepository raceRepository;

    protected RaceDao() {
        super(RaceEntity.class);
    }

    public PageableList<RaceEntity> getWithPagination(RequestCriteria request) {
        Specification<BaseEntity> searchSpec = SpecificationUtils.searchSpecification(request.getSearch(), NAME_FIELD);
        return raceRepository.getWithPagination(request, searchSpec, NAME_FIELD);
    }

    @Override
    public RaceEntity save(RaceEntity entity) {
        if (entity == null) {
            throw new IllegalArgumentException(MessageFormat.format(EMPTY_ENTITY, RaceEntity.getDescription()));
        }

        RaceEntity byName = raceRepository.findRaceByName(entity.getName());
        if (byName != null && (entity.getId() == null || !entity.getId().equals(byName.getId()))) {
            throw new IllegalArgumentException(MessageFormat.format(ENTITY_ALREADY_EXISTS,
                    RaceEntity.getDescription(), entity.getName()));
        }

        return super.save(entity);
    }

    @Override
    public void deleteById(Long id) {
        RaceEntity entity = findById(id);

        if (entity == null) {
            throw new IllegalArgumentException(MessageFormat.format(ENTITY_NOT_FOUND_BY_ID,
                    RaceEntity.getDescription(), id));
        }

        super.deleteById(id);
    }

    @Autowired
    public void setRaceRepository(RaceRepository raceRepository) {
        this.raceRepository = raceRepository;
    }
}
