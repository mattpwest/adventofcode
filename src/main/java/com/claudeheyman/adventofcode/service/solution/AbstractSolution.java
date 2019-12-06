package com.claudeheyman.adventofcode.service.solution;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public abstract class AbstractSolution implements Solution {
	@Override
	public String getId() {
		return buildId(getYear(), getDay(), getPart());
	}

	public static String buildId(int year, int day, int part) {
		return String.format("%d-%d-%d", year, day, part);
	}
}
