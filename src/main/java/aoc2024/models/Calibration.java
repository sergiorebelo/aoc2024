package aoc2024.models;

import aoc2024.utils.MathUtils.LongsOperation;

import java.util.Arrays;
import java.util.List;

public class Calibration {

    private final long testValue;
    private final long[] numbers;
    List<LongsOperation> ops;

    public Calibration( String line) {
        String[] parts = line.split(":");
        this.testValue = Long.parseLong(parts[0].trim());
        this.numbers = Arrays.stream(parts[1].trim().split(" "))
                .mapToLong(Long::parseLong)
                .toArray();
    }

    public long testValue() { return testValue; }

    public void setSupportedOperations(List<LongsOperation> ops) { this.ops=ops; }

    public boolean isValid(long soFar, int pos) {
        return pos == numbers.length-1
                ? ops.stream().anyMatch(op -> op.apply(soFar, numbers[pos]) == testValue)
                : ops.stream().anyMatch(op -> isValid(op.apply(soFar, numbers[pos]), pos + 1));
    }
}
