package com.claudeheyman.adventofcode.service;

import com.claudeheyman.adventofcode.service.solver.NumericalSequenceSolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@Service
@Slf4j
public class Solver2018Day1 {

	@Autowired
	private NumericalSequenceSolver numericalSequenceSolver;

	public String solvePartOne(List<Integer> numbers) {
		int finalFrequency = numericalSequenceSolver.sum(numbers);
		return Integer.toString(finalFrequency);
	}

	public String solvePartTwo(List<Integer> numbers) {
		log.warn("Starting with " + numbers.size() + " frequency changes");

		Set<Integer> frequenciesVisited = new TreeSet<>();

		int iterationCount = 1;
		int currentFrequency = 0;

		while (iterationCount < 10_000) {
			log.error("<--------------------------------------------ITERATION "+iterationCount+"---------------------------------------------------------------->");

			for (int frequency : numbers) {
				currentFrequency += frequency;
				boolean alreadyVisited = !frequenciesVisited.add(currentFrequency);
				log.warn("> " +currentFrequency+ " + " +frequency+ (alreadyVisited?" --------------- found":""));
				if (alreadyVisited) {
					return Integer.toString(currentFrequency);
				}
			}

			iterationCount++;
		}

		return "not-found";
	}
}
