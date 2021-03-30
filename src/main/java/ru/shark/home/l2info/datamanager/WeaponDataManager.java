package ru.shark.home.l2info.datamanager;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import ru.shark.home.l2info.dao.common.PageableList;
import ru.shark.home.l2info.dao.dto.WeaponDto;
import ru.shark.home.l2info.dao.entity.WeaponEntity;
import ru.shark.home.l2info.dao.service.BaseDao;
import ru.shark.home.l2info.dao.service.WeaponDao;

@Component
public class WeaponDataManager extends BaseDataManager<WeaponEntity, WeaponDto> {
    public WeaponDataManager(BaseDao dao) {
        super(dao, WeaponDto.class);
    }

    public PageableList<WeaponDto> getWithPagination(Pageable pageable) {
        WeaponDao dao = (WeaponDao) getDao();
        return getConverterUtil().entityListToDtoPageableList(dao.getWithPagination(pageable), WeaponDto.class);
    }
}
