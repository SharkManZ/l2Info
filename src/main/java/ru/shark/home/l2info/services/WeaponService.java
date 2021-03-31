package ru.shark.home.l2info.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.shark.home.l2info.dao.dto.WeaponDto;
import ru.shark.home.l2info.datamanager.WeaponDataManager;
import ru.shark.home.l2info.services.dto.PageRequest;
import ru.shark.home.l2info.services.dto.response.BaseResponse;

import static ru.shark.home.l2info.common.ErrorConstants.ERR_500;

@Component
public class WeaponService extends BaseService {
    private WeaponDataManager weaponDataManager;

    public BaseResponse getList(PageRequest request) {
        BaseResponse response;
        try {
            response = new BaseResponse<>();
            response.setBody(weaponDataManager.getWithPagination(getCriteria(request)));
            response.setSuccess(true);

        } catch (Exception e) {
            response = BaseResponse.buildError(ERR_500, "Ошибка при получении списка оружия: " + e.getMessage());
        }

        return response;
    }

    public BaseResponse save(WeaponDto dto) {
        BaseResponse response;

        try {
            response = new BaseResponse();
            response.setBody(weaponDataManager.save(dto));
            response.setSuccess(true);
        } catch (Exception e) {
            response = BaseResponse.buildError(ERR_500, "Ошибка при сохранении оружия:" + e.getMessage());
        }

        return response;
    }

    public BaseResponse delete(Long id) {
        BaseResponse response;

        try {
            response = new BaseResponse();
            weaponDataManager.deleteById(id);
            response.setSuccess(true);
        } catch (Exception e) {
            response = BaseResponse.buildError(ERR_500, "Ошибка при удалении оружия: " + e.getMessage());
        }

        return response;
    }

    @Autowired
    public void setWeaponDataManager(WeaponDataManager weaponDataManager) {
        this.weaponDataManager = weaponDataManager;
    }
}
