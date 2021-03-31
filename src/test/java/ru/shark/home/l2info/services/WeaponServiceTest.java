package ru.shark.home.l2info.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Pageable;
import ru.shark.home.l2info.dao.common.PageableList;
import ru.shark.home.l2info.dao.common.RequestCriteria;
import ru.shark.home.l2info.dao.dto.WeaponDto;
import ru.shark.home.l2info.datamanager.WeaponDataManager;
import ru.shark.home.l2info.services.dto.PageRequest;
import ru.shark.home.l2info.services.dto.response.BaseResponse;
import ru.shark.home.l2info.util.BaseServiceTest;

import java.util.Arrays;

import static org.mockito.Mockito.*;

public class WeaponServiceTest extends BaseServiceTest {
    private WeaponService weaponService;
    private WeaponDataManager weaponDataManager;

    @BeforeAll
    public void init() {
        weaponDataManager = mock(WeaponDataManager.class);
        weaponService = new WeaponService();
        weaponService.setWeaponDataManager(weaponDataManager);
    }

    @BeforeEach
    public void initMethod() {
        reset(weaponDataManager);
    }

    @Test
    public void getList() {
        // GIVEN
        PageRequest request = new PageRequest(0, 10);
        PageableList<WeaponDto> pageList = new PageableList<>(Arrays.asList(new WeaponDto()), 1L);
        when(weaponDataManager.getWithPagination(any(RequestCriteria.class))).thenReturn(pageList);

        // WHEN
        BaseResponse response = weaponService.getList(request);

        // THEN
        checkPagingResponse(response);
        verify(weaponDataManager, times(1)).getWithPagination(any(RequestCriteria.class));
    }

    @Test
    public void save() {
        // GIVEN
        when(weaponDataManager.save(any(WeaponDto.class))).thenReturn(new WeaponDto());
        // WHEN
        BaseResponse response = weaponService.save(new WeaponDto());

        // THEN
        checkResponseWithBody(response);
        verify(weaponDataManager, times(1)).save(any(WeaponDto.class));
    }

    @Test
    public void delete() {
        // WHEN
        BaseResponse response = weaponService.delete(1L);

        // THEN
        checkResponse(response);
        verify(weaponDataManager, times(1)).deleteById(anyLong());
    }
}
