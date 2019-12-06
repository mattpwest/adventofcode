package com.claudeheyman.adventofcode.service.solution;

import com.claudeheyman.adventofcode.service.input.PuzzleInput;

public interface Solution {
	String getId();
	int getYear();
	int getDay();
	int getPart();

	String solve(PuzzleInput input);
}
