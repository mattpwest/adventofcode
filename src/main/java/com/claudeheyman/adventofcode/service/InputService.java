package com.claudeheyman.adventofcode.service;

import com.claudeheyman.adventofcode.service.input.PuzzleInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Service
public class InputService {

    @Autowired
    private ResourceLoader fileLoader;

    public PuzzleInput getPuzzleInput(int year, int day, int part, String requestBody, String requestParam) throws IOException {
        if (StringUtils.hasText(requestParam)) {
            return new PuzzleInput(requestParam);
        }

        if (StringUtils.hasText(requestBody)) {
            return new PuzzleInput(requestBody);
        }

        return new PuzzleInput(getFileContents(year, day, part));
    }

    private String getFileContents(int year, int day, int part) throws IOException {
        Resource inputFile = fileLoader.getResource("classpath:/" + year + "." + day + "." + part + ".input");

        byte[] bytes = inputFile.getInputStream().readAllBytes();// Files.readAllBytes(Paths.get("/input/" + year + "/" + day));
        return new String(bytes, StandardCharsets.UTF_8);
    }
}
