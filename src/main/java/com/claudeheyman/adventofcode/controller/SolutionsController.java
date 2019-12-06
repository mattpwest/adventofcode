package com.claudeheyman.adventofcode.controller;

import com.claudeheyman.adventofcode.service.InputService;
import com.claudeheyman.adventofcode.service.SolutionService;
import com.claudeheyman.adventofcode.service.input.PuzzleInput;
import com.claudeheyman.adventofcode.service.solution.DaySolution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@RequestMapping("/solutions")
@RestController
public class SolutionsController {

	@Autowired
	private InputService inputService;

	@Autowired
	private SolutionService solutionService;

	protected PuzzleInput getInput(int year, int day, String body, String queryParam) throws IOException {
		return inputService.getPuzzleInput(year, day, body, queryParam);
	}

	@PostMapping(value = { "/{year}/{day}/{part}", "/{year}/{day}" })
	public ResponseEntity<String> solvePost(
			@RequestBody(required = false) String body,
			@PathVariable int year,
			@PathVariable int day,
			@PathVariable(required = false) String part) {

		return solve(year, day, part, body, null);
	}

	@GetMapping(value = { "/{year}/{day}/{part}", "/{year}/{day}" })
	public ResponseEntity<String> solveGet(
			@RequestParam(name = "input", required = false) String inputQueryParam,
			@PathVariable int year,
			@PathVariable int day,
			@PathVariable(required = false) String part
			) {

		return solve(year, day, part, null, inputQueryParam);
	}

	private ResponseEntity<String> solve(int year, int day, String part, String body, String queryParam) {

		int partNo = part == null ? 1 : Integer.parseInt(part);

		try {
			PuzzleInput input = getInput(year, day, body, queryParam);
			DaySolution solution = solutionService.getSolution(year, day);

			return ResponseEntity.ok(solution.solve(partNo, input));

		} catch (IOException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("I/O error reading file input");
		}
	}

}
