package ru.shark.home.l2info.datamanager;

import org.springframework.stereotype.Component;
import ru.shark.home.common.dao.common.PageableList;
import ru.shark.home.common.dao.common.RequestCriteria;
import ru.shark.home.common.datamanager.BaseDataManager;
import ru.shark.home.l2info.dao.dto.WeaponDto;
import ru.shark.home.l2info.dao.entity.WeaponEntity;
import ru.shark.home.l2info.dao.service.WeaponDao;

@Component
public class WeaponDataManager extends BaseDataManager<WeaponEntity, WeaponDto> {
    public WeaponDataManager(WeaponDao dao) {
        super(dao, WeaponDto.class);
    }

    public PageableList<WeaponDto> getWithPagination(RequestCriteria request) {
        WeaponDao dao = (WeaponDao) getDao();
        return getConverterUtil().entityListToDtoPageableList(dao.getWithPagination(request), WeaponDto.class);
    }
}
