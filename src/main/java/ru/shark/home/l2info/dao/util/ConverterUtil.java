package ru.shark.home.l2info.dao.util;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.shark.home.l2info.dao.common.PageableList;
import ru.shark.home.l2info.dao.dto.Dto;
import ru.shark.home.l2info.dao.entity.BaseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.util.ObjectUtils.isEmpty;

@Component
public class ConverterUtil {
    private Mapper mapper;

    public <D extends Dto, E extends BaseEntity> List<D> entityListToDtoList(List<E> entityList, Class<D> dtoClass) {
        if (isEmpty(entityList)) {
            return new ArrayList<>();
        }

        List<D> dtoList = new ArrayList<>();
        for (E entity : entityList) {
            dtoList.add(mapper.map(entity, dtoClass));
        }

        return dtoList;
    }

    public <D extends Dto, E extends BaseEntity> PageableList<D> entityListToDtoPageableList(PageableList<E> entityList,
                                                                                             Class<D> dtoClass) {
        if (isEmpty(entityList) || isEmpty(entityList.getData())) {
            return new PageableList<>(new ArrayList<>(), 0L);
        }

        List<D> dtoList = new ArrayList<>();
        for (E entity : entityList.getData()) {
            dtoList.add(mapper.map(entity, dtoClass));
        }

        return new PageableList<>(dtoList, entityList.getTotalCount());
    }

    public <D extends Dto, E extends BaseEntity> List<E> dtoListToEntityList(List<D> dtoList, Class<E> entityClass) {
        if (isEmpty(dtoList)) {
            return new ArrayList<>();
        }

        List<E> entityList = new ArrayList<>();
        for (D dto : dtoList) {
            entityList.add(mapper.map(dto, entityClass));
        }

        return entityList;
    }

    public <D extends Dto, E extends BaseEntity> D entityToDto(E entity, Class<D> dtoClass) {
        if (entity == null) {
            return null;
        }

        return mapper.map(entity, dtoClass);
    }

    public <D extends Dto, E extends BaseEntity> E dtoToEntity(D dto, Class<E> entityClass) {
        if (dto == null) {
            return null;
        }

        return mapper.map(dto, entityClass);
    }

    @Autowired
    public void setMapper(Mapper mapper) {
        this.mapper = mapper;
    }
}
