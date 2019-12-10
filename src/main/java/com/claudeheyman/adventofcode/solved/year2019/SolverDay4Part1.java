package com.claudeheyman.adventofcode.solved.year2019;

import com.claudeheyman.adventofcode.service.input.PuzzleInput;
import com.claudeheyman.adventofcode.service.solution.AbstractSolution;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SolverDay4Part1 extends AbstractSolution {
	@Override
	public int getYear() {
		return 2019;
	}

	@Override
	public int getDay() {
		return 4;
	}

	@Override
	public int getPart() {
		return 1;
	}

	@Override
	public String solve(PuzzleInput input) {
		var inputs = input.retrieveNumbers();
		var start = inputs.get(0);
		var end = inputs.get(1);

		var count = 0;
		for (var i = start; i <= end; i++) {
			if (hasDecreasing(i) || !hasTwoAdjacent(i)) continue;

			count++;
		}

		return "" + count;
	}

	private boolean hasDecreasing(Integer i) {
		var num = i.toString();
		char last = '0';
		for (var digit : num.toCharArray()) {
			if (Integer.parseInt(digit + "") < Integer.parseInt(last + "")) {
				return true;
			}

			last = digit;
		}

		return false;
	}

	private boolean hasTwoAdjacent(Integer i) {
		var num = i.toString();
		char last = ' ';
		for (var digit : num.toCharArray()) {
			if (digit == last) {
				return true;
			}

			last = digit;
		}

		return false;
	}
}
