package com.claudeheyman.adventofcode.domain.euclidean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class Point2 {
    private int x;
    private int y;

    public Point2 plus(Point2 other) {
        return new Point2(this.x + other.x, this.y + other.y);
    }

    public int getManhattanDistanceFromOrigin() {
        return Math.abs(x) + Math.abs(y);
    }
}
