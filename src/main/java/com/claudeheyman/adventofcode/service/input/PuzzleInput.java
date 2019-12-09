package com.claudeheyman.adventofcode.service.input;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PuzzleInput {
	private final String raw;

	public PuzzleInput(String rawInput) {
		this.raw = rawInput;
	}

	public List<Integer> retrieveNumbers() {
		String[] values = raw.split("[,\n]");

		return Arrays.asList(values).parallelStream()
				.map(s -> s.replace("+", ""))
				.map(s -> Integer.parseInt(s.trim()))
				.collect(Collectors.toList());
	}

	public List<String> retrieveLines() {
		String[] values = raw.split("[,\n]");

		return Arrays.asList(values).parallelStream()
				.map(String::trim)
				.collect(Collectors.toList());
	}

	public List<List<String>> retrieveSplitLines() {
		String[] values = raw.split("[\n]");

		return Arrays.asList(values).parallelStream()
				.map(String::trim)
				.map(line -> Arrays.asList(line.trim().split("[,]")))
				.collect(Collectors.toList());
	}
}
