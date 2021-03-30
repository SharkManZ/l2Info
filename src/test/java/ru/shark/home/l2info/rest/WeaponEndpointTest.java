package ru.shark.home.l2info.rest;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.shark.home.l2info.services.WeaponService;
import ru.shark.home.l2info.services.dto.PageRequest;

import javax.ws.rs.core.Response;

import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class WeaponEndpointTest {
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
        Assertions.assertNotNull(response);
        verify(weaponService, timeout(1)).getList(any(PageRequest.class));
    }
}
