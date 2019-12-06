package com.claudeheyman.adventofcode.controller;

import com.claudeheyman.adventofcode.service.InputService;
import com.claudeheyman.adventofcode.service.SolutionService;
import com.claudeheyman.adventofcode.service.input.PuzzleInput;
import com.claudeheyman.adventofcode.service.solution.Solution;
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

	@PostMapping(value = { "/{year}/{day}" })
	public ResponseEntity<String> solvePost(
			@RequestBody(required = false) String body,
			@PathVariable int year,
			@PathVariable int day,
			@RequestParam(defaultValue = "1") int part
	) {

		return solve(year, day, part, body, null);
	}

	@GetMapping(value = { "/{year}/{day}" })
	public ResponseEntity<String> solveGet(
			@RequestParam(name = "input", required = false) String inputQueryParam,
			@PathVariable int year,
			@PathVariable int day,
			@RequestParam(defaultValue = "1") int part
	) {

		return solve(year, day, part, null, inputQueryParam);
	}

	private ResponseEntity<String> solve(int year, int day, int part, String body, String queryParam) {
		try {
			PuzzleInput input = getInput(year, day, body, queryParam);
			Solution solution = solutionService.getSolution(year, day, part);

			if (solution == null) {
				return ResponseEntity.notFound().build();
			}

			return ResponseEntity.ok(solution.solve(input));
		} catch (IOException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("I/O error reading file input");
		}
	}

}
