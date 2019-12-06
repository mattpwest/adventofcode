package com.claudeheyman.adventofcode.solved.year2019;

import com.claudeheyman.adventofcode.service.input.PuzzleInput;
import com.claudeheyman.adventofcode.service.solution.AbstractSolution;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SolverDay1Part2 extends SolverDay1Part1 {
	@Override
	public int getPart() {
		return 2;
	}

	@Override
	protected int calculateMass(Integer weight) {
		var total = 0;
		var mass = super.calculateMass(weight);
		total += mass;

		while (mass > 0) {
			mass = super.calculateMass(mass);

			if (mass <= 0) break;

			total += mass;
		}

		return total;
	}
}
