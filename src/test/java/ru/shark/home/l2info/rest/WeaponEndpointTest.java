package ru.shark.home.l2info.rest;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.shark.home.l2info.dao.dto.WeaponDto;
import ru.shark.home.l2info.services.WeaponService;
import ru.shark.home.l2info.services.dto.PageRequest;
import ru.shark.home.l2info.util.BaseEndpointTest;

import javax.ws.rs.core.Response;

import static org.mockito.Mockito.*;


public class WeaponEndpointTest extends BaseEndpointTest {
    private WeaponEndpoint weaponEndpoint;
    private WeaponService weaponService;

    @BeforeAll
    public void init() {
        weaponService = mock(WeaponService.class);
        weaponEndpoint = new WeaponEndpoint();
        weaponEndpoint.setWeaponService(weaponService);
    }

    @BeforeEach
    public void initMethod() {
        reset(weaponService);
    }

    @Test
    public void getList() {
        // GIVEN
        PageRequest pageRequest = new PageRequest(0, 10);

        // WHEN
        Response response = weaponEndpoint.getList(pageRequest);

        // THEN
        checkResponse(response);
        verify(weaponService, times(1)).getList(any(PageRequest.class));
    }

    @Test
    public void save() {
        // WHEN
        Response response = weaponEndpoint.save(new WeaponDto());

        // THEN
        checkResponse(response);
        verify(weaponService, times(1)).save(any(WeaponDto.class));
    }

    @Test
    public void delete() {
        // WHEN
        Response response = weaponEndpoint.delete(1L);

        // THEN
        checkResponse(response);
        verify(weaponService, times(1)).delete(anyLong());
    }
}
