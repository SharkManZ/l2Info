package ru.shark.home.l2info.rest;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.shark.home.common.services.dto.PageRequest;
import ru.shark.home.l2info.dao.dto.RaceDto;
import ru.shark.home.l2info.services.RaceService;
import ru.shark.home.l2info.util.BaseEndpointTest;

import javax.ws.rs.core.Response;

import static org.mockito.Mockito.*;


public class RaceEndpointTest extends BaseEndpointTest {
    private RaceEndpoint raceEndpoint;
    private RaceService raceService;

    @BeforeAll
    public void init() {
        raceService = mock(RaceService.class);
        raceEndpoint = new RaceEndpoint();
        raceEndpoint.setRaceService(raceService);
    }

    @BeforeEach
    public void initMethod() {
        reset(raceService);
    }

    @Test
    public void getList() {
        // GIVEN
        PageRequest pageRequest = new PageRequest(0, 10);

        // WHEN
        Response response = raceEndpoint.getList(pageRequest);

        // THEN
        checkResponse(response);
        verify(raceService, times(1)).getList(any(PageRequest.class));
    }

    @Test
    public void save() {
        // WHEN
        Response response = raceEndpoint.save(new RaceDto());

        // THEN
        checkResponse(response);
        verify(raceService, times(1)).save(any(RaceDto.class));
    }

    @Test
    public void delete() {
        // WHEN
        Response response = raceEndpoint.delete(1L);

        // THEN
        checkResponse(response);
        verify(raceService, times(1)).delete(anyLong());
    }
}
