package com.claudeheyman.adventofcode.solved.year2019;

import com.claudeheyman.adventofcode.domain.IntPuter;
import com.claudeheyman.adventofcode.service.input.PuzzleInput;
import com.claudeheyman.adventofcode.service.solution.AbstractSolution;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SolverDay2Part2 extends AbstractSolution {
	@Override
	public int getYear() {
		return 2019;
	}

	@Override
	public int getDay() {
		return 2;
	}

	@Override
	public int getPart() {
		return 2;
	}

	@Override
	public String solve(PuzzleInput input) {
		var computer = new IntPuter();
		for (var x = 0; x <= 99; x++) {
			for (var y = 0; y <= 99; y++) {
				var code = input.retrieveNumbers();
				code.set(1, x);
				code.set(2, y);
				var result = computer.calculate(code);
				var value = result.get(0);

				if (value == 19690720) {
					var noun = result.get(1);
					var verb = result.get(2);
					return "noun=" + noun + "; verb=" + verb + "; answer=" + (100 * noun + verb);
				}
			}
		}

		return "Answer not found...";
	}
}
