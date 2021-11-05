package ru.shark.home.l2info.dao.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.shark.home.common.dao.repository.BaseRepository;
import ru.shark.home.l2info.dao.entity.RaceEntity;

@Repository
public interface RaceRepository extends BaseRepository<RaceEntity> {

    /**
     * Возвращает расу персонажа по названию.
     *
     * @param name название.
     * @return сущность расы.
     */
    @Query(name = "findRaceByName")
    RaceEntity findRaceByName(@Param("name") String name);
}
