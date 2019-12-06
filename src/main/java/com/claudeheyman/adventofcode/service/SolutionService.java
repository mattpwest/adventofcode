package com.claudeheyman.adventofcode.service;

import com.claudeheyman.adventofcode.service.solution.DaySolution;
import com.claudeheyman.adventofcode.service.solution.NotYetDoneSolution;
import com.claudeheyman.adventofcode.solved.Solved2018;
import com.claudeheyman.adventofcode.solved.Solved2019;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SolutionService {
	private final static DaySolution NOT_COMPLETED = new NotYetDoneSolution();

	@Autowired
	private Solved2018 solutions2018;

	@Autowired
	private Solved2019 solutions2019;

	public DaySolution getSolution(int year, int day) {
		switch (year) {
			case 2018 : return solutions2018.getSolution(day);
			case 2019 : return solutions2019.getSolution(day);
		}

		return NOT_COMPLETED;
	}
}
