package ru.shark.home.l2info.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.shark.home.l2info.dao.dto.RaceDto;
import ru.shark.home.l2info.datamanager.RaceDataManager;
import ru.shark.home.l2info.services.dto.PageRequest;
import ru.shark.home.l2info.services.dto.response.BaseResponse;

import static ru.shark.home.l2info.common.ErrorConstants.ERR_500;

@Component
public class RaceService extends BaseLogicService {

    private RaceDataManager raceDataManager;

    public BaseResponse getList(PageRequest request) {
        BaseResponse response;
        try {
            response = new BaseResponse<>();
            response.setBody(raceDataManager.getWithPagination(getCriteria(request, RaceDto.class)));
            response.setSuccess(true);

        } catch (Exception e) {
            response = BaseResponse.buildError(ERR_500, "Ошибка при получении списка расс: " + e.getMessage());
        }

        return response;
    }

    public BaseResponse save(RaceDto dto) {
        BaseResponse response;

        try {
            response = new BaseResponse();
            response.setBody(raceDataManager.save(dto));
            response.setSuccess(true);
        } catch (Exception e) {
            response = BaseResponse.buildError(ERR_500, "Ошибка при сохранении расы:" + e.getMessage());
        }

        return response;
    }

    public BaseResponse delete(Long id) {
        BaseResponse response;

        try {
            response = new BaseResponse();
            raceDataManager.deleteById(id);
            response.setSuccess(true);
        } catch (Exception e) {
            response = BaseResponse.buildError(ERR_500, "Ошибка при удалении расы: " + e.getMessage());
        }

        return response;
    }

    @Autowired
    public void setRaceDataManager(RaceDataManager raceDataManager) {
        this.raceDataManager = raceDataManager;
    }
}
