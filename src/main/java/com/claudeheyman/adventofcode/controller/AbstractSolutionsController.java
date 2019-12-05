package com.claudeheyman.adventofcode.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractSolutionsController {

	protected List<Integer> splitCsvNumbers(String csvList) {
		String[] values = csvList.split("[,\n]");

		return Arrays.asList(values).parallelStream()
				.map(s -> s.replace("+", ""))
				.map(s -> Integer.parseInt(s.trim()))
				.collect(Collectors.toList());
	}

	protected List<String> splitCsv(String csvList) {
		String[] values = csvList.split("[,\n]");

		return Arrays.asList(values).parallelStream()
				.map(String::trim)
				.collect(Collectors.toList());
	}

	protected List<List<String>> splitCsvLines(String csvList) {
		String[] values = csvList.split("[\n]");

		return Arrays.asList(values).parallelStream()
				.map(String::trim)
				.map(line -> Arrays.asList(line.trim().split("[,]")))
				.collect(Collectors.toList());
	}
}
