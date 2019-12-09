package com.claudeheyman.adventofcode.solved.year2019;

import com.claudeheyman.adventofcode.domain.euclidean.Line2;
import com.claudeheyman.adventofcode.domain.euclidean.Point2;
import com.claudeheyman.adventofcode.service.input.PuzzleInput;
import com.claudeheyman.adventofcode.service.solution.AbstractSolution;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

@Slf4j
@Component
public class SolverDay3Part1 extends AbstractSolution {
	@Override
	public int getYear() {
		return 2019;
	}

	@Override
	public int getDay() {
		return 3;
	}

	@Override
	public int getPart() {
		return 1;
	}

	@Override
	public String solve(PuzzleInput input) {
		var wires = input.retrieveSplitLines();

		var wires1 = extractLines(wires.get(0));
		var wires2 = extractLines(wires.get(1));
		var intersections = new PriorityQueue<>(Comparator.comparingInt(Point2::getManhattanDistanceFromOrigin));

		for (var wire2 : wires2) {
			for (var wire1 : wires1) {
				var intersection = wire2.intersection(wire1);
				if (intersection != null && intersection.getManhattanDistanceFromOrigin() != 0) {
					intersections.add(intersection);
				}
			}
		}

		log.debug(intersections.peek().toString());

		return "" + intersections.peek().getManhattanDistanceFromOrigin();
	}

	private List<Line2> extractLines(List<String> wires) {
		var start = new Point2(0, 0);
		List<Line2> lines = new ArrayList<>();
		for (String move : wires) {
			var end = parseMove(move).plus(start);
			var line = new Line2(start, end);
			lines.add(line);
			start = end;
		}
		return lines;
	}

	private Point2 parseMove(String move) {
		String direction = move.substring(0, 1).toUpperCase();
		int magnitude = Integer.parseInt(move.substring(1));

		if (direction.equals("U")) {
			return new Point2(0, magnitude);
		} else if (direction.equals("D")) {
			return new Point2(0, -magnitude);
		} else if (direction.equals("L")) {
			return new Point2(-magnitude, 0);
		} else if (direction.equals("R")){
			return new Point2(magnitude, 0);
		}

		log.warn(String.format("Returning (0,0) - likely invalid move: '%s'.", move));
		return new Point2(0, 0);
	}


}
