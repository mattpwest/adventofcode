package com.claudeheyman.adventofcode.service;

import com.claudeheyman.adventofcode.service.solution.Solution;
import com.claudeheyman.adventofcode.service.solution.AbstractSolution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SolutionService {

	private final List<Solution> solutions;
	private Map<String, Solution> lookup;

	@Autowired
	public SolutionService(List<Solution> solutions) {
		this.solutions = solutions;

		buildLookupTable();
	}

	public Solution getSolution(int year, int day, int part) {
		return lookup.get(AbstractSolution.buildId(year, day, part));
	}

	private void buildLookupTable() {
		lookup = new HashMap<>();

		for (var solution : solutions) {
			var id = solution.getId();
			lookup.put(id, solution);
		}
	}
}
