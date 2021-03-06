package ru.lighthouse.mobile.main;


import org.junit.jupiter.api.Test;
import ru.lighthouse.mobile.main.inttest.AbstractIntegrationTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.lighthouse.mobile.main.App.HEALTH_RESPONSE;
import static ru.lighthouse.mobile.main.App.HEALTH_CHECK_URI;

public class AppTest extends AbstractIntegrationTest {

    @Test
    public void testHealth() throws Exception {
        mvc.perform(get(HEALTH_CHECK_URI))
           .andExpect(status().isOk())
           .andExpect(content().string(HEALTH_RESPONSE));
    }
}