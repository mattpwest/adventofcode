package com.claudeheyman.adventofcode.service.solver;

import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class NumericalSequenceSolver {

	public int sum(Collection<Integer> numbers) {
		return numbers.parallelStream()
				.reduce(Integer::sum)
				.orElse(0);
	}
}
