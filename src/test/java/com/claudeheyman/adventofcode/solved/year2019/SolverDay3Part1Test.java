package com.claudeheyman.adventofcode.solved.year2019;

import com.claudeheyman.adventofcode.service.input.PuzzleInput;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.assertj.core.api.Assertions.assertThat;

class SolverDay3Part1Test {

    SolverDay3Part1 cut = new SolverDay3Part1();

    @ParameterizedTest
    @CsvFileSource(resources = "/2019.3.1.csv", numLinesToSkip = 1)
    void solve(String wire1, String wire2, int answer) {
        String output = cut.solve(new PuzzleInput(wire1 + "\n" + wire2));

        assertThat(Integer.parseInt(output))
                .isEqualTo(answer);
    }
}