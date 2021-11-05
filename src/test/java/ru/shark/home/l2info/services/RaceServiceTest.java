package ru.shark.home.l2info.services;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.shark.home.common.dao.common.PageableList;
import ru.shark.home.common.dao.common.RequestCriteria;
import ru.shark.home.common.services.dto.PageRequest;
import ru.shark.home.common.services.dto.response.BaseResponse;
import ru.shark.home.l2info.dao.dto.RaceDto;
import ru.shark.home.l2info.datamanager.RaceDataManager;
import ru.shark.home.l2info.util.BaseServiceTest;

import java.util.Arrays;

import static org.mockito.Mockito.*;

public class RaceServiceTest extends BaseServiceTest {
    private RaceService raceService;
    private RaceDataManager raceDataManager;

    @BeforeAll
    public void init() {
        raceDataManager = mock(RaceDataManager.class);
        raceService = new RaceService();
        raceService.setRaceDataManager(raceDataManager);
    }

    @BeforeEach
    public void initMethod() {
        reset(raceDataManager);
    }

    @Test
    public void getList() {
        // GIVEN
        PageRequest request = new PageRequest(0, 10);
        PageableList<RaceDto> pageList = new PageableList<>(Arrays.asList(new RaceDto()), 1L);
        when(raceDataManager.getWithPagination(any(RequestCriteria.class))).thenReturn(pageList);

        // WHEN
        BaseResponse response = raceService.getList(request);

        // THEN
        checkPagingResponse(response);
        verify(raceDataManager, times(1)).getWithPagination(any(RequestCriteria.class));
    }

    @Test
    public void save() {
        // GIVEN
        when(raceDataManager.save(any(RaceDto.class))).thenReturn(new RaceDto());
        // WHEN
        BaseResponse response = raceService.save(new RaceDto());

        // THEN
        checkResponseWithBody(response);
        verify(raceDataManager, times(1)).save(any(RaceDto.class));
    }

    @Test
    public void delete() {
        // WHEN
        BaseResponse response = raceService.delete(1L);

        // THEN
        checkResponse(response);
        verify(raceDataManager, times(1)).deleteById(anyLong());
    }
}
