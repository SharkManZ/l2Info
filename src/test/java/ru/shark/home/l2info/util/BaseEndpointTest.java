package ru.shark.home.l2info.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.TestInstance;

import javax.ws.rs.core.Response;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BaseEndpointTest {
    protected void checkResponse(Response response) {
        Assertions.assertNotNull(response);
        Assertions.assertEquals(javax.ws.rs.core.Response.Status.OK.getStatusCode(), response.getStatus());
    }
}
