package com.claudeheyman.adventofcode.service;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class Solver2019Day3 {


	public String solvePartTwo(List<List<String>> commandLines) {
		return solvePartOne(commandLines);
	}

	public String solvePartOne(List<List<String>> commandLines) {
		WiringMatrix matrix = new WiringMatrix();

		for (List<String> commands : commandLines) {
			matrix.resetStarting();
			generatePaths(commands).forEach(matrix::markPath);
			log.info(matrix.createVisualRepresentation());
		}

		Cell checkDistanceFrom = new Cell(0,0);
		int minDistance = matrix.getMultipleVisits().stream()
				.mapToInt(cell -> cell.calculateManhattanDistance(checkDistanceFrom))
				.min()
				.orElse(-1);

		return Integer.toString(minDistance);
	}

	private List<Path> generatePaths(List<String> commands) {
		return commands.stream()
				.map(Path::new)
				.collect(Collectors.toList());
	}

	@ToString
	private static class Path {
		public final Direction direction;
		public final int length;

		Path(String command) {
			this.direction = Direction.forCommand(command);
			this.length = Integer.parseInt(command.substring(1));
		}
	}

	@Data
	private static class Cell {
		public final int column;
		public final int row;

		public Cell move(Direction direction) {
			switch (direction) {
				case NORTH: return new Cell(column, row + 1);
				case EAST: return new Cell(column + 1, row);
				case SOUTH: return new Cell(column, row - 1);
				case WEST: return new Cell(column - 1, row);
			}
			throw new UnsupportedOperationException("Cannot move for Direction " + direction);
		}

		int calculateManhattanDistance(Cell other) {
			return Math.abs(column - other.column) + Math.abs(row - other.row);
		}
	}

	@NoArgsConstructor
	private static class WiringMatrix {
		@Getter
		private final Set<Cell> visited = new HashSet<>();
		@Getter
		private final List<Cell> multipleVisits = new ArrayList<>();
		@Getter
		private Cell lastCell = new Cell(0,0);

		private final Set<Cell> currentPath = new HashSet<>();

		public void resetStarting() {
			this.lastCell = new Cell(0,0);
		}

		public void markPath(Path path) {
			List<Cell> visits = createPathVisits(lastCell, path);

			//Juggling via currentPath is needed because multiple visits
			//of a path on itself should not be counted
			currentPath.addAll(visits);
			multipleVisits.addAll(currentPath.stream()
					.filter(visited::contains)
					.collect(Collectors.toList()));
			visited.addAll(currentPath);
			currentPath.clear();

			lastCell = visits.get(visits.size()-1);
		}

		private List<Cell> createPathVisits(Cell startingCell, Path path) {
			List<Cell> toVisit = new ArrayList<>(path.length);
			Cell cell = startingCell;
			for (int i = 0; i < path.length; i++) {
				cell = cell.move(path.direction);
				toVisit.add(cell);
			}
			return toVisit;
		}

		public String createVisualRepresentation() {
			StringBuilder visual = new StringBuilder();

			IntSummaryStatistics columnStats = visited.stream()
					.mapToInt(Cell::getColumn)
					.summaryStatistics();
			IntSummaryStatistics rowStats = visited.stream()
					.mapToInt(Cell::getRow)
					.summaryStatistics();

			for (int y = rowStats.getMax()+2; y >= rowStats.getMin()-2; y--) {
				for (int x = columnStats.getMin()-2; x <= rowStats.getMax()+2; x++) {
					boolean visitOnce = visited.contains(new Cell(x, y));
					boolean visitMultiple = multipleVisits.contains(new Cell(x, y));

					if (!visitOnce) {
						visual.append(".");
					} else {
						visual.append(visitMultiple ? "+" : "~");
					}
				}
				visual.append("\n");
			}
			return visual.toString();
		}
	}

	private enum Direction {
		NORTH("U"),
		EAST("R"),
		SOUTH("D"),
		WEST("L");

		private final String key;

		Direction(String key) { this.key = key; }

		public static Direction forCommand(String command) {
			for (Direction d : values())
				if (command.startsWith(d.key)) return d;
			throw new NullPointerException("No direction corresponding to " + command);
		}
	}
}
