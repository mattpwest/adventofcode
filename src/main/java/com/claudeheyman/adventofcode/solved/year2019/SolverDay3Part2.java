package com.claudeheyman.adventofcode.solved.year2019;

import com.claudeheyman.adventofcode.domain.euclidean.Line2;
import com.claudeheyman.adventofcode.domain.euclidean.Point2WithPathDistance;
import com.claudeheyman.adventofcode.service.input.PuzzleInput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.PriorityQueue;

@Slf4j
@Component
public class SolverDay3Part2 extends SolverDay3Part1 {
	@Override
	public int getPart() {
		return 2;
	}

	@Override
	public String solve(PuzzleInput input) {
		var wires = input.retrieveSplitLines();

		var wires1 = extractLines(wires.get(0));
		var wires2 = extractLines(wires.get(1));
		var intersections = new PriorityQueue<>(Comparator.comparingInt(Point2WithPathDistance::getPathDistance));

		var dist1 = 0;
		var dist2 = 0;
		for (var wire2 : wires2) {
			dist1 = 0;
			dist2 += wire2.length();
			for (var wire1 : wires1) {
				dist1 += wire1.length();

				var intersection = wire2.intersection(wire1);
				if (intersection != null && intersection.getManhattanDistanceFromOrigin() != 0) {
					intersections.add(new Point2WithPathDistance(
							intersection,
							+ dist1
									+ dist2
									- new Line2(intersection, wire1.getEnd()).length()
									- new Line2(intersection, wire2.getEnd()).length()
					));
				}
			}
		}

		log.debug(intersections.peek().toString());

		return "" + intersections.peek().getPathDistance();
	}
}
