package com.claudeheyman.adventofcode.solved.year2018;

import com.claudeheyman.adventofcode.service.input.PuzzleInput;
import com.claudeheyman.adventofcode.service.solution.DaySolution;
import com.claudeheyman.adventofcode.service.solver.NumericalSequenceSolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@Component
public class Solver2018Day1 implements DaySolution {

	@Autowired
	private NumericalSequenceSolver numericalSequenceSolver;

	@Override
	public String solve(int part, PuzzleInput input) {
		List<Integer> numbers = input.retrieveNumbers();
		if (part == 1) {
			return solvePartOne(numbers);
		} else {
			return solvePartTwo(numbers);
		}
	}

	public String solvePartOne(List<Integer> numbers) {
		int finalFrequency = numericalSequenceSolver.sum(numbers);
		return Integer.toString(finalFrequency);
	}

	public String solvePartTwo(List<Integer> numbers) {
		Set<Integer> frequenciesVisited = new TreeSet<>();

		int iterationCount = 1;
		int currentFrequency = 0;

		while (iterationCount < 10_000) {
			for (int frequency : numbers) {
				currentFrequency += frequency;
				boolean alreadyVisited = !frequenciesVisited.add(currentFrequency);
				if (alreadyVisited) {
					return Integer.toString(currentFrequency);
				}
			}

			iterationCount++;
		}

		return "not-found";
	}

}

