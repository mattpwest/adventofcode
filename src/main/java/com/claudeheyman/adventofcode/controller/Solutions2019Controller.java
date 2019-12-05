package com.claudeheyman.adventofcode.controller;

import com.claudeheyman.adventofcode.service.Solver2019Day1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/solutions/2019")
@RestController
public class Solutions2019Controller {

	@Autowired
	private Solver2019Day1 solver;

	@PostMapping(value = { "/1/{part}", "/1" })
	public ResponseEntity<String> solve1(
			@RequestBody String csvList,
			@PathVariable(required = false) String part) {

		List<Integer> values = splitCsv(csvList);

		String solution = "2".equals(part) ? solver.solvePartTwo(values) : solver.solvePartOne(values);

		return ResponseEntity.ok(solution);
	}

	private List<Integer> splitCsv(String csvList) {
		String[] values = csvList.split("[,\n]");

		return Arrays.asList(values).parallelStream()
				.map(s -> s.replace("+", ""))
				.map(s -> Integer.parseInt(s.trim()))
				.collect(Collectors.toList());
	}
}
