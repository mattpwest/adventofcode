package com.claudeheyman.adventofcode.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IntPuter {
    public enum Operation {
        ADD(1, 4),
        MULTIPLY(2, 4),
        END(99, 1);

        private int code;
        private int parameters;

        Operation(int opcode, int parameters) {
            this.code = opcode;
            this.parameters = parameters;
        }
    }

    static Map<Integer, Operation> lookup = new HashMap<>();

    static {
        for (var operation : Operation.values()) {
            lookup.put(operation.code, operation);
        }
    }

    public List<Integer> calculate(List<Integer> memory) {
        for (var i = 0; i < memory.size(); i += 4) {
            var instruction = lookup.get(memory.get(i));
            if (Operation.END.equals(instruction)) {
                return memory;
            }

            if (Operation.ADD.equals(instruction)) {
                var address1 = memory.get(i + 1);
                var address2 = memory.get(i + 2);
                var result = add(memory.get(address1), memory.get(address2));
                memory.set(memory.get(i + 3), result);
            } else if (Operation.MULTIPLY.equals(instruction)) {
                var address1 = memory.get(i + 1);
                var address2 = memory.get(i + 2);
                var result = multiply(memory.get(address1), memory.get(address2));
                memory.set(memory.get(i + 3), result);
            } else {
                throw new IllegalArgumentException("Bad instruction " + instruction + " at position " + i);
            }
        }

        return memory;
    }

    private Integer add(Integer a, Integer b) {
        return a + b;
    }

    private Integer multiply(Integer a, Integer b) {
        return a * b;
    }
}
