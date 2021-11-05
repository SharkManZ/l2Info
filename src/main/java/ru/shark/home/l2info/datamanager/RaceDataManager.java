package ru.shark.home.l2info.datamanager;

import org.springframework.stereotype.Component;
import ru.shark.home.common.dao.common.PageableList;
import ru.shark.home.common.dao.common.RequestCriteria;
import ru.shark.home.common.datamanager.BaseDataManager;
import ru.shark.home.l2info.dao.dto.RaceDto;
import ru.shark.home.l2info.dao.entity.RaceEntity;
import ru.shark.home.l2info.dao.service.RaceDao;

@Component
public class RaceDataManager extends BaseDataManager<RaceEntity, RaceDto> {
    public RaceDataManager(RaceDao dao) {
        super(dao, RaceDto.class);
    }

    public PageableList<RaceDto> getWithPagination(RequestCriteria request) {
        RaceDao dao = (RaceDao) getDao();
        return getConverterUtil().entityListToDtoPageableList(dao.getWithPagination(request), RaceDto.class);
    }
}
