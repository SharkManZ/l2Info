package ru.shark.home.l2info.dao.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.shark.home.common.dao.repository.BaseRepository;
import ru.shark.home.l2info.dao.entity.ClassEntity;

import java.util.List;

@Repository
public interface ClassRepository extends BaseRepository<ClassEntity> {

    /**
     * Возвращает класс персонажа по названию.
     *
     * @param name название.
     * @return сущность класса.
     */
    @Query(name = "findClassByName")
    ClassEntity findClassByName(@Param("name") String name);

    /**
     * Возвращает список дочерних классов для указанного родителя.
     */
    @Query(name = "findClassByParentId")
    List<ClassEntity> findClassByParentId(@Param("parentId") Long id);
}
