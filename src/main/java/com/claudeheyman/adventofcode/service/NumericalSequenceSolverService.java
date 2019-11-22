package com.claudeheyman.adventofcode.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@Service
@Slf4j
public class NumericalSequenceSolverService {

	public String solve2018_1(List<Integer> numbers) {
		int finalFrequency = numbers.parallelStream()
				.reduce(Integer::sum)
				.orElse(0);

		return Integer.toString(finalFrequency);
	}

	public String solve2018_2(List<Integer> numbers) {
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
