package com.claudeheyman.adventofcode.domain.euclidean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Line2 {
    private Point2 start;
    private Point2 end;

    public Point2 intersection(Line2 line) {
        var line1dx = start.getX() - end.getX();
        var line1dy = start.getY() - end.getY();
        var line2dx = line.start.getX() - line.end.getX();
        var line2dy = line.start.getY() - line.end.getY();

        if (line1dx == 0 && line2dx == 0) {
            return null;
        } else if (line1dy == 0 && line2dy == 0) {
            return null;
        }

        if (line1dx == 0 && isBetween(this.start.getX(), line.start.getX(), line.end.getX())) {
            return new Point2(this.start.getX(), line.start.getY());
        } else if (line2dx == 0 && isBetween(line.getStart().getX(), this.start.getX(), this.end.getX())) {
            return new Point2(line.start.getX(), this.start.getY());
        } else if (line1dy == 0 && isBetween(this.start.getY(), line.start.getY(), line.end.getY())) {
            return new Point2(line.start.getX(), this.start.getY());
        } else if (line2dy == 0 && isBetween(line.start.getY(), this.start.getY(), this.end.getY())) {
            return new Point2(this.start.getX(), line.start.getY());
        }

        return null;
    }

    private boolean isBetween(int x, int x1, int x2) {
        return (x >= x1 && x <= x2) || (x >= x2 && x <= x1);
    }
}
