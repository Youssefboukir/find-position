package com.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.ResourceUtils;

import java.io.FileNotFoundException;

@SpringBootTest
class TestApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void test() throws FileNotFoundException {
        String filePath = ResourceUtils.getFile("classpath:files/carte.txt").getAbsolutePath();
        String fileDataPath = ResourceUtils.getFile("classpath:files/data.txt").getAbsolutePath();
        String expectedFinalPosition = "(9,2)";

        String finalPosition = TestApplication.findPosition(filePath, fileDataPath);

        Assertions.assertEquals(expectedFinalPosition, finalPosition);
    }

}
