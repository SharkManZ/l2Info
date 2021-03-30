package ru.shark.home.l2info.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.shark.home.l2info.dao.common.PageableList;
import ru.shark.home.l2info.dao.dto.WeaponDto;
import ru.shark.home.l2info.datamanager.WeaponDataManager;
import ru.shark.home.l2info.services.dto.PageRequest;

@Component
public class WeaponService {
    private WeaponDataManager weaponDataManager;

    public PageableList<WeaponDto> getList(PageRequest request) {
        return weaponDataManager.getWithPagination(org.springframework.data.domain.PageRequest.of(request.getPage(),
                request.getSize()));
    }

    @Autowired
    public void setWeaponDataManager(WeaponDataManager weaponDataManager) {
        this.weaponDataManager = weaponDataManager;
    }
}
