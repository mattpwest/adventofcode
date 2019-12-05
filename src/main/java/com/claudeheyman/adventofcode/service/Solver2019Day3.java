package com.claudeheyman.adventofcode.service;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

		int minDistance = matrix.getMultipleVisitCells().stream()
				.mapToInt(xy -> Math.abs(xy.getKey()) + Math.abs(xy.getValue()))
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

	@NoArgsConstructor
	private static class WiringMatrix {
		private final Map<Integer, List<Integer>> visitedColumnsByRow = new TreeMap<>();
		private final Map<Integer, Integer> multipleVisits = new TreeMap<>();
		@Getter
		private int lastColumn = 0;
		@Getter
		private int lastRow = 0;

		public void resetStarting() {
			this.lastColumn = 0;
			this.lastRow = 0;
		}

		public void markPath(Path path) {
			int startingColumn = lastColumn;
			int startingRow = lastRow;
			int length = path.length;

			switch (path.direction) {
				case NORTH: IntStream.rangeClosed(startingRow+1, startingRow+length).forEach(newR -> markVisit(startingColumn, newR)); break;
				case EAST: IntStream.rangeClosed(startingColumn+1, startingColumn+length).forEach(newC -> markVisit(newC, startingRow)); break;
				case SOUTH: IntStream.rangeClosed(startingRow-length, startingRow-1).forEach(newR -> markVisit(startingColumn, newR)); break;
				case WEST: IntStream.rangeClosed(startingColumn-length, startingColumn-1).forEach(newC -> markVisit(newC, startingRow)); break;
			}
		}

		private void markVisit(int column, int row) {
			if (!visitedColumnsByRow.containsKey(row)) {
				visitedColumnsByRow.put(row, new ArrayList<>());
			}

			List<Integer> alreadyVisited = visitedColumnsByRow.get(row);
			if (alreadyVisited.contains(column)) {
				multipleVisits.put(column, row);
			} else {
				alreadyVisited.add(column);
			}

			lastColumn = column;
			lastRow = row;
		}

		public List<Map.Entry<Integer, Integer>> getMultipleVisitCells() {
			return new ArrayList<>(multipleVisits.entrySet());
		}

		public String createVisualRepresentation() {
			StringBuilder visual = new StringBuilder();

			int maxColumn = visitedColumnsByRow.values().stream().flatMapToInt(integers -> integers.stream().mapToInt(Integer::intValue)).max().orElse(1);
			int maxRow = visitedColumnsByRow.keySet().stream().mapToInt(Integer::intValue).max().orElse(1);

			visual.append("--------------------------------------------------------------------------\n");
			for (int y = maxRow-1; y >= 0; y--) {
				List<Integer> visited = visitedColumnsByRow.get(y);
				boolean multivisit = visited != null && visited.size() > 1;

				if (visited == null) {
					visual.append(".".repeat(maxColumn-1));
					visual.append("\n");
				} else {
					for (int x = 0; x <= maxColumn; x++) {
						if (visited.contains(x)) {
							visual.append(multivisit ? "~" : "+");
						} else {
							visual.append(".");
						}
					}
					visual.append("\n");
				}
			}
			visual.append("--------------------------------------------------------------------------\n");
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
			return null;
		}
	}
}
