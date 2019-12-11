package com.claudeheyman.adventofcode.solved.year2019;

import com.claudeheyman.adventofcode.service.input.PuzzleInput;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.assertj.core.api.Assertions.assertThat;

class SolverDay4Part1Test {

    SolverDay4Part1 cut = new SolverDay4Part1();

    @ParameterizedTest
    @CsvFileSource(resources = "/2019.4.1.csv", numLinesToSkip = 1)
    void solve(int number, boolean valid) {
        String output = cut.solve(new PuzzleInput(number + "\n" + number));
        boolean outputSaysValid = output.equals("1");

        assertThat(outputSaysValid)
                .isEqualTo(valid);
    }
}