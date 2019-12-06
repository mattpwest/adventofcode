package com.claudeheyman.adventofcode.solved.year2019;

import com.claudeheyman.adventofcode.domain.IntPuter;
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
		var computer = new IntPuter();
		return output(computer.calculate(code));
	}

	private String output(List<Integer> code) {
		return code
				.stream()
				.map(x -> String.format("%d", x))
				.collect(Collectors.joining(","));
	}
}
