package com.claudeheyman.adventofcode.solved.year2019;

import com.claudeheyman.adventofcode.service.input.PuzzleInput;
import com.claudeheyman.adventofcode.service.solution.AbstractSolution;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class SolverDay2Part1 extends AbstractSolution {
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
		return 1;
	}

	@Override
	public String solve(PuzzleInput input) {
		var code = input.retrieveNumbers();
		for (var i = 0; i < code.size(); i+= 4) {
			var instruction = code.get(i);
			if (instruction == 99) {
				return output(code);
			}

			var address1 = code.get(i + 1);
			var address2 = code.get(i + 2);
			var address3 = code.get(i + 3);

			var value1 = code.get(address1);
			var value2 = code.get(address2);

			if (instruction == 1) {
				code.set(address3, value1 + value2);
			} else if (instruction == 2) {
				code.set(address3, value1 * value2);
			} else {
				throw new IllegalArgumentException("Bad instruction " + instruction + " at position " + i);
			}
		}

		return output(code);
	}

	private String output(List<Integer> code) {
		return code
				.stream()
				.map(x -> String.format("%d", x))
				.collect(Collectors.joining(","));
	}
}
