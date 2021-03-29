package ru.shark.home.l2info.datamanager;

import org.springframework.stereotype.Component;
import ru.shark.home.l2info.dao.dto.WeaponDto;
import ru.shark.home.l2info.dao.entity.WeaponEntity;
import ru.shark.home.l2info.dao.service.BaseDao;

@Component
public class WeaponDataManager extends BaseDataManager<WeaponEntity, WeaponDto> {
    public WeaponDataManager(BaseDao dao) {
        super(dao, WeaponDto.class);
    }
}
