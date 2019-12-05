package com.claudeheyman.adventofcode.controller;

import com.claudeheyman.adventofcode.service.Solver2018Day1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/solutions/2018")
@RestController
public class Solutions2018Controller extends AbstractSolutionsController {

	@Autowired
	private Solver2018Day1 solver;

	@PostMapping(value = { "/1/{part}", "/1" })
	public ResponseEntity<String> solve1(
			@RequestBody String csvList,
			@PathVariable(required = false) String part) {

		List<Integer> values = splitCsv(csvList);

		String solution = "2".equals(part) ? solver.solvePartTwo(values) : solver.solvePartOne(values);

		return ResponseEntity.ok(solution);
	}
}
