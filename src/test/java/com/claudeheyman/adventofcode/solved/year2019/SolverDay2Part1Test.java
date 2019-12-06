package com.claudeheyman.adventofcode.solved.year2019;

import com.claudeheyman.adventofcode.service.input.PuzzleInput;
import com.claudeheyman.adventofcode.service.solution.Solution;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.assertj.core.api.Assertions.assertThat;

class SolverDay2Part1Test {

    final Solution cut = new SolverDay2Part1();

    @ParameterizedTest
    @CsvFileSource(resources = "/2019.2.1.csv", numLinesToSkip = 1)
    void solve(String input, String output) {
        String solution = cut.solve(new PuzzleInput(input));

        assertThat(solution).isEqualTo(output);
    }
}