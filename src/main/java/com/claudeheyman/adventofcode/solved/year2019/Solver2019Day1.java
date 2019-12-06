package com.claudeheyman.adventofcode.solved.year2019;

import com.claudeheyman.adventofcode.service.input.PuzzleInput;
import com.claudeheyman.adventofcode.service.solution.DaySolution;
import com.claudeheyman.adventofcode.service.solver.NumericalSequenceSolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class Solver2019Day1 implements DaySolution {

	@Autowired
	private NumericalSequenceSolver numericalSequenceSolver;

	public String solvePartOne(List<Integer> numbers) {
		List<Integer> fuel = deriveFuelMass(numbers);

		return Integer.toString(numericalSequenceSolver.sum(fuel));
	}

	public String solvePartTwo(List<Integer> numbers) {
		List<Integer> totalWeights = new LinkedList<>();

		List<Integer> toBeWeighed = numbers;
		while (!toBeWeighed.isEmpty()) {
			List<Integer> weighed = deriveFuelMass(toBeWeighed);
			totalWeights.addAll(weighed);
			toBeWeighed = weighed;
		}

		return Integer.toString(numericalSequenceSolver.sum(totalWeights));
	}

	private List<Integer> deriveFuelMass(List<Integer> weights) {
		return weights.parallelStream()
				.map(mass -> ((int) Math.floor(mass / 3D)) - 2)
				.filter(integer -> integer > 0)
				.collect(Collectors.toList());
	}

	@Override
	public String solve(int part, PuzzleInput input) {
		List<Integer> numbers = input.retrieveNumbers();
		if (part == 1) {
			return solvePartOne(numbers);
		} else {
			return solvePartTwo(numbers);
		}
	}
}
