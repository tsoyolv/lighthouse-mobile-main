package ru.lighthouse.mobile.main.unittest;


import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Base64;

//import static org.junit.jupiter.api.Assertions.assertTrue;

public class CheckUnitTest extends AbstractUnitTest {

    @Test
    public void testImagePercentage() throws IOException {
        System.out.println(1 % 5);
        System.out.println(Base64.getEncoder().encodeToString(
                ("-----BEGIN RSA PRIVATE KEY-----\n" +
                        "-----END RSA PRIVATE KEY-----")
                        .getBytes()));
    }

}