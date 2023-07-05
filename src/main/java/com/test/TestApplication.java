package com.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.ResourceUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@SpringBootApplication
public class TestApplication {

    public static void main(String[] args) throws FileNotFoundException {
        SpringApplication.run(TestApplication.class, args);
        String filePath = ResourceUtils.getFile("classpath:files/carte.txt").getAbsolutePath();
        String fileDataPath = ResourceUtils.getFile("classpath:files/data.txt").getAbsolutePath();
        findPosition(filePath,fileDataPath);
    }

    public static String findPosition(String filePath,String fileDataPath) {
        try {
            // read the file that contain the carte
            List<String> lines = readFile(filePath);
            String mapString = String.join("\n", lines);
            // read the file that contains the data
            List<String> list = readFile(fileDataPath);
            String[] initialCoordinates = list.get(0).split(",");
            int x = Integer.parseInt(initialCoordinates[0]);
            int y = Integer.parseInt(initialCoordinates[1]);
            String directions = list.get(1);
            for (char direction : directions.toCharArray()) {
                switch (direction) {
                    case 'N' -> y--;
                    case 'S' -> y++;
                    case 'E' -> x++;
                    case 'O' -> x--;
                }
                if (!isValidMove(mapString, x, y)) {
                    System.out.println("Invalid move Stopped at (" + x + "," + y + ")");
                    return null;
                }
            }
            System.out.println("Final position: (" + x + "," + y + ")");
            return "(" + x + "," + y + ")";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static boolean isValidMove(String mapString, int x, int y) {
        int mapWidth = mapString.indexOf('\n') + 1;
        int index = y * mapWidth + x;
        if (x < 0 || x >= mapWidth || y < 0 || index >= mapString.length() || mapString.charAt(index) == '#') {
            return false;
        }
        return true;
    }

    private static List<String> readFile(String filePath) throws IOException {
        return Files.readAllLines(Path.of(filePath));
    }
}
