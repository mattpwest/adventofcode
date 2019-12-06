package com.claudeheyman.adventofcode.solved;

import com.claudeheyman.adventofcode.service.solution.DaySolution;
import com.claudeheyman.adventofcode.service.solution.YearSolution;
import com.claudeheyman.adventofcode.solved.year2019.Solver2019Day1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Solved2019 implements YearSolution {

	@Autowired
	private Solver2019Day1 day1;
	@Autowired
	private Solver2019Day1 day2;

	@Override
	public DaySolution getSolution(int day) {
		switch (day) {
			case 1: return day1;
			case 2: return day2;
			default: return null;
		}
	}
}
