package com.claudeheyman.adventofcode.controller;

import com.claudeheyman.adventofcode.service.Solver2019Day1;
import com.claudeheyman.adventofcode.service.Solver2019Day2;
import com.claudeheyman.adventofcode.service.Solver2019Day3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/solutions/2019")
@RestController
public class Solutions2019Controller extends AbstractSolutionsController {

	@Autowired
	private Solver2019Day1 solver1;
	@Autowired
	private Solver2019Day2 solver2;
	@Autowired
	private Solver2019Day3 solver3;

	@PostMapping(value = { "/1/{part}", "/1" })
	public ResponseEntity<String> solve1(
			@RequestBody String csvList,
			@PathVariable(required = false) String part) {

		List<Integer> values = splitCsvNumbers(csvList);

		String solution = "2".equals(part) ? solver1.solvePartTwo(values) : solver1.solvePartOne(values);

		return ResponseEntity.ok(solution);
	}

	@PostMapping(value = { "/2/{part}", "/2" })
	public ResponseEntity<String> solve2(
			@RequestBody String csvList,
			@PathVariable(required = false) String part) {

		List<Integer> values = splitCsvNumbers(csvList);

		String solution = "2".equals(part) ? solver2.solvePartTwo(values) : solver2.solvePartOne(values);

		return ResponseEntity.ok(solution);
	}

	@PostMapping(value = { "/3/{part}", "/3" })
	public ResponseEntity<String> solve3(
			@RequestBody String csvList,
			@PathVariable(required = false) String part) {

		List<List<String>> values = splitCsvLines(csvList);

		String solution = "2".equals(part) ? solver3.solvePartTwo(values) : solver3.solvePartOne(values);

		return ResponseEntity.ok(solution);
	}
}
