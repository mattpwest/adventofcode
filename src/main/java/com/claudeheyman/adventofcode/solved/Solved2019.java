package com.claudeheyman.adventofcode.solved;

import com.claudeheyman.adventofcode.service.solution.DaySolution;
import com.claudeheyman.adventofcode.service.solution.YearSolution;
import com.claudeheyman.adventofcode.solved.year2019.SolverDayExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Solved2019 implements YearSolution {

	@Autowired
	private SolverDayExample daySolver;

	@Override
	public DaySolution getSolution(int day) {
		switch (day) {
			default: return daySolver;
		}
	}
}
