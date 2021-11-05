package ru.shark.home.l2info.dao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import ru.shark.home.common.dao.common.PageableList;
import ru.shark.home.common.dao.common.RequestCriteria;
import ru.shark.home.common.dao.entity.BaseEntity;
import ru.shark.home.common.dao.service.BaseDao;
import ru.shark.home.common.dao.util.SpecificationUtils;
import ru.shark.home.l2info.dao.entity.ClassEntity;
import ru.shark.home.l2info.dao.entity.RaceEntity;
import ru.shark.home.l2info.dao.repository.ClassRepository;
import ru.shark.home.l2info.dao.repository.RaceRepository;

import java.text.MessageFormat;
import java.util.List;

import static ru.shark.home.common.common.ErrorConstants.*;

@Component
public class ClassDao extends BaseDao<ClassEntity> {
    private static final String NAME_FIELD = "name";
    private static final String LEVEL_FIELD = "level";

    private ClassRepository classRepository;
    private RaceRepository raceRepository;

    protected ClassDao() {
        super(ClassEntity.class);
    }

    public PageableList<ClassEntity> getWithPagination(RequestCriteria request) {
        Specification<BaseEntity> searchSpec = SpecificationUtils.searchSpecification(request.getSearch(), NAME_FIELD);
        return classRepository.getWithPagination(request, searchSpec, LEVEL_FIELD, NAME_FIELD);
    }

    @Override
    public ClassEntity save(ClassEntity entity) {
        if (entity == null) {
            throw new IllegalArgumentException(MessageFormat.format(EMPTY_ENTITY, ClassEntity.getDescription()));
        }

        ClassEntity byName = classRepository.findClassByName(entity.getName());
        if (byName != null && (entity.getId() == null || !entity.getId().equals(byName.getId()))) {
            throw new IllegalArgumentException(MessageFormat.format(ENTITY_ALREADY_EXISTS,
                    ClassEntity.getDescription(), entity.getName()));
        }

        if (entity.getRace() == null || entity.getRace().getId() == null) {
            throw new IllegalArgumentException(MessageFormat.format(EMPTY_ENTITY, RaceEntity.getDescription()));
        }
        if (raceRepository.findById(entity.getRace().getId()).orElse(null) == null) {
            throw new IllegalArgumentException(MessageFormat.format(ENTITY_NOT_FOUND_BY_ID,
                    RaceEntity.getDescription(), entity.getRace().getId()));
        }
        ClassEntity parentClass = null;
        if (entity.getParent() != null && entity.getParent().getId() != null) {
            parentClass = classRepository.findById(entity.getParent().getId()).orElse(null);
            if (parentClass == null) {
                throw new IllegalArgumentException(MessageFormat.format(ENTITY_NOT_FOUND_BY_ID,
                        ClassEntity.getDescription(), entity.getParent().getId()));
            }
        }


        if (entity.getId() == null) {
            if (parentClass == null) {
                entity.setLevel(0);
            } else {
                entity.setLevel(parentClass.getLevel() + 1);
            }
        }

        return super.save(entity);
    }

    @Override
    public void deleteById(Long id) {
        ClassEntity entity = findById(id);

        if (entity == null) {
            throw new IllegalArgumentException(MessageFormat.format(ENTITY_NOT_FOUND_BY_ID,
                    ClassEntity.getDescription(), id));
        }

        List<ClassEntity> list = classRepository.findClassByParentId(id);
        if (!ObjectUtils.isEmpty(list)) {
            list.forEach(classEntity -> deleteById(classEntity.getId()));
        }

        super.deleteById(id);
    }

    @Autowired
    public void setClassRepository(ClassRepository classRepository) {
        this.classRepository = classRepository;
    }

    @Autowired
    public void setRaceRepository(RaceRepository raceRepository) {
        this.raceRepository = raceRepository;
    }
}
