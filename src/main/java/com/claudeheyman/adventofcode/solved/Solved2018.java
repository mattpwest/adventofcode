package com.claudeheyman.adventofcode.solved;

import com.claudeheyman.adventofcode.service.solution.DaySolution;
import com.claudeheyman.adventofcode.service.solution.YearSolution;
import com.claudeheyman.adventofcode.solved.year2018.Solver2018Day1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Solved2018 implements YearSolution {

	@Autowired
	private Solver2018Day1 day1;

	@Override
	public DaySolution getSolution(int day) {
		switch (day) {
			case 1: return day1;
			default: return null;
		}
	}
}
