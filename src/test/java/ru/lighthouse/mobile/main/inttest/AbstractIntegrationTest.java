package ru.lighthouse.mobile.main.inttest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import ru.lighthouse.mobile.main.App;

@SpringBootTest(classes = App.class)
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class AbstractIntegrationTest {
    public static final String DEFAULT_PHONE_NUMBER = "79779873676";

    @Autowired
    protected MockMvc mvc;
}
