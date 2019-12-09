package com.claudeheyman.adventofcode.domain.euclidean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Point2WithPathDistance extends Point2 {
    private int pathDistance;

    public Point2WithPathDistance(Point2 original, int pathDistance) {
        super(original.getX(), original.getY());

        this.pathDistance = pathDistance;
    }
}
