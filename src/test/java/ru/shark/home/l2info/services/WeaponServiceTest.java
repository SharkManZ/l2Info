package ru.shark.home.l2info.services;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.shark.home.l2info.dao.common.PageableList;
import ru.shark.home.l2info.dao.dto.WeaponDto;
import ru.shark.home.l2info.datamanager.WeaponDataManager;
import ru.shark.home.l2info.services.dto.PageRequest;

import java.util.Arrays;

import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class WeaponServiceTest {
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
        when(weaponDataManager.getWithPagination(any(Pageable.class))).thenReturn(pageList);

        // WHEN
        PageableList<WeaponDto> list = weaponService.getList(request);

        // THEN
        Assertions.assertNotNull(list);
        Assertions.assertNotNull(list.getData());
        Assertions.assertNotNull(list.getTotalCount());
    }
}
