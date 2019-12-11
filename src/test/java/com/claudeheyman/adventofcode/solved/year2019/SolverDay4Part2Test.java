package com.claudeheyman.adventofcode.solved.year2019;

import com.claudeheyman.adventofcode.service.input.PuzzleInput;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class SolverDay4Part2Test {

    SolverDay4Part2 cut = new SolverDay4Part2();

    @ParameterizedTest
    @CsvFileSource(resources = "/2019.4.2.csv", numLinesToSkip = 1)
    void solve(int number, boolean valid) {
        String output = cut.solve(new PuzzleInput(number + "\n" + number));
        boolean outputSaysValid = output.equals("1");

        log.info("Tested {} valid: {}", number, outputSaysValid);

        assertThat(outputSaysValid)
                .isEqualTo(valid);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/2019.4.2.2.csv", numLinesToSkip = 1)
    void solve(int min, int max) {
        String output = cut.solve(new PuzzleInput(min + "\n" + max));
        var value = Integer.parseInt(output);

        assertThat(value).isGreaterThan(526); // Advent says this too low
        assertThat(value).isLessThan(960); // Should be less than previous answer
    }
}