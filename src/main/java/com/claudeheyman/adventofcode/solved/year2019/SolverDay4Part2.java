package com.claudeheyman.adventofcode.solved.year2019;

import com.claudeheyman.adventofcode.service.input.PuzzleInput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SolverDay4Part2 extends SolverDay4Part1 {
	@Override
	public int getPart() {
		return 2;
	}

	@Override
	public String solve(PuzzleInput input) {
		var inputs = input.retrieveNumbers();
		var start = inputs.get(0);
		var end = inputs.get(1);

		var count = 0;
		for (var i = start; i <= end; i++) {
			if (hasDecreasing(i) || !hasTwoAdjacent(i) || !hasExactlyTwoAdjacent(i)) continue;

			count++;
		}

		return "" + count;
	}

	private boolean hasExactlyTwoAdjacent(Integer i) {
		var num = i.toString();
		char last = ' ';
		var count = 1;
		for (var digit : num.toCharArray()) {
			if (digit == last) {
				count++;
			} else if (count == 2) {
				return true;
			} else {
				count = 1;
			}

			last = digit;
		}

		return count == 2;
	}
}
