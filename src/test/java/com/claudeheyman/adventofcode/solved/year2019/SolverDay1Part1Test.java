package com.claudeheyman.adventofcode.solved.year2019;

import com.claudeheyman.adventofcode.service.input.PuzzleInput;
import com.claudeheyman.adventofcode.service.solution.Solution;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.assertj.core.api.Assertions.assertThat;

class SolverDay1Part1Test {

    final Solution cut = new SolverDay1Part1();

    @ParameterizedTest
    @CsvFileSource(resources = "/2019.1.1.csv", numLinesToSkip = 1)
    void solve(int input, int output) {
        String solution = cut.solve(new PuzzleInput(String.format("%d", input)));

        assertThat(Integer.parseInt(solution)).isEqualTo(output);
    }
}