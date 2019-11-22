package com.claudeheyman.adventofcode.controller;

import com.claudeheyman.adventofcode.service.NumericalSequenceSolverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@RequestMapping("/solutions/2018")
@RestController
public class Solutions2018Controller {

	@Autowired
	private NumericalSequenceSolverService numberSequenceSolver;

	@PostMapping("/1")
	public ResponseEntity<String> solve1(
			@RequestBody String csvList,
			@RequestParam(defaultValue = "true") boolean part2) {

		List<Integer> values = splitCsv(csvList);

		if (part2) {
			return ResponseEntity.ok(numberSequenceSolver.solve2018_2(values));
		} else {
			return ResponseEntity.ok(numberSequenceSolver.solve2018_1(values));
		}
	}

	private List<Integer> splitCsv(String csvList) {
		String[] values = csvList.split("[,\n]");

		return Arrays.asList(values).parallelStream()
				.map(s -> s.replace("+", ""))
				.map(s -> Integer.parseInt(s.trim()))
				.collect(Collectors.toList());
	}
}
