package com.claudeheyman.adventofcode.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@Slf4j
public class Solver2019Day2 {

	public String solvePartTwo(List<Integer> numbers) {
		int noun = 0;
		int verb = 0;

		int limit = numbers.stream().max(Integer::compareTo).orElse(numbers.size());
		String expectedResult = "19690720";

		while (noun <= limit) {
			numbers.set(1, noun);
			numbers.set(2, verb);

			String solution = solvePartOne(numbers);
			solution = solution.substring(0, solution.indexOf(","));
			if (solution.equals(expectedResult))
				return Integer.toString(100 * noun + verb);

			if (verb != limit) {
				verb++;
			} else {
				noun++;
				verb = 0;
			}
		}

		return "no solution";
	}

	public String solvePartOne(List<Integer> numbers) {
		Map<Integer, Integer> codesByPosition = mapToPosition(numbers);

		int index = 0;
		while (index < numbers.size()) {
			Operation nextOperation = decode(codesByPosition, index);
			if (nextOperation == null) {
				break;
			}

			int targetValue = nextOperation.calculateTarget();
			codesByPosition.put(nextOperation.target, targetValue);

			index += 4;
		}

		return codesByPosition.keySet().stream()
				.sorted()
				.map(codesByPosition::get)
				.map(integer -> Integer.toString(integer))
				.collect(Collectors.joining(","));
	}

	private Map<Integer, Integer> mapToPosition(List<Integer> codes) {
		Map<Integer, Integer> codesByPosition = new TreeMap<>();
		for (int i = 0; i < codes.size(); i++) {
			codesByPosition.put(i, codes.get(i));
		}
		return codesByPosition;
	}

	private Operation decode(Map<Integer, Integer> codesByPosition, int fromPosition) {
		OperationType type = OperationType.forCode(codesByPosition.get(fromPosition));
		if (type == OperationType.STOP) {
			return null;
		}

		int firstValue = codesByPosition.get(codesByPosition.get(fromPosition + 1));
		int secondValue = codesByPosition.get(codesByPosition.get(fromPosition + 2));
		int targetPosition = codesByPosition.get(fromPosition + 3);

		return new Operation(targetPosition, type, firstValue, secondValue);
	}

	private static class Operation {
		public final OperationType type;
		public final int[] values;
		public final int target;

		Operation(int target, OperationType type, int... values) {
			this.type = type;
			this.values = values;
			this.target = target;
		}

		public Integer calculateTarget() {
			switch (type) {
				case ADDITION: return IntStream.of(values).sum();
				case MULTIPLICATION: return IntStream.of(values).reduce((a, b) -> a * b).orElse(0);
				case STOP: return null;
			}
			throw new UnsupportedOperationException(type + " has no execution code");
		}
	}

	private enum OperationType {
		ADDITION(1),
		MULTIPLICATION(2),
		STOP(99);

		private final int code;

		OperationType(int code) {
			this.code = code;
		}

		public static OperationType forCode(int opCode) {
			for (OperationType type : values()) {
				if (type.code == opCode) {
					return type;
				}
			}
			return null;
		}
	}
}
