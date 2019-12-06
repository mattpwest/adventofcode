package com.claudeheyman.adventofcode.solved.year2019;

import com.claudeheyman.adventofcode.service.input.PuzzleInput;
import com.claudeheyman.adventofcode.service.solution.AbstractSolution;
import com.claudeheyman.adventofcode.service.solution.Solution;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SolverDay1Part1 extends AbstractSolution {
	@Override
	public int getYear() {
		return 2019;
	}

	@Override
	public int getDay() {
		return 1;
	}

	@Override
	public int getPart() {
		return 1;
	}

	@Override
	public String solve(PuzzleInput input) {
		var total = 0;
		for (var weight : input.retrieveNumbers()) {
			total += calculateMass(weight);
		}
		return String.format("%d", total);
	}

	protected int calculateMass(Integer weight) {
		return (int) Math.floor(weight / 3.0) - 2;
	}
}
