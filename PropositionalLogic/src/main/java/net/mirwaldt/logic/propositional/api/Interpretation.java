package net.mirwaldt.logic.propositional.api;

import net.mirwaldt.logic.propositional.impl.LongInterpretation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static net.mirwaldt.logic.propositional.util.BitUtils.encode;
import static net.mirwaldt.logic.propositional.util.PropositionUtils.fromBit;
import static net.mirwaldt.logic.propositional.util.PropositionUtils.toBit;

/**
 * maps every variable to a single boolean value.
 */
public interface Interpretation {
    static Interpretation of(String var, int val) {
        fromBit(val); // use as check
        return new LongInterpretation(Collections.singletonList(var), val);
    }

    static Interpretation of(String var1, int val1, String var2, int val2) {
        long bits = encode(0, val1);
        bits = encode(bits, 1, val2);
        return new LongInterpretation(List.of(var1, var2), bits);
    }

    static Interpretation of(String var1, int val1, String var2, int val2, String var3, int val3) {
        long bits = encode(0, val1);
        bits = encode(bits, 1, val2);
        bits = encode(bits, 2, val3);
        return new LongInterpretation(List.of(var1, var2, var3), bits);
    }

    static Interpretation of(String var1, int val1, String var2, int val2,
                             String var3, int val3, String var4, int val4) {
        long bits = encode(0, val1);
        bits = encode(bits, 1, val2);
        bits = encode(bits, 2, val3);
        bits = encode(bits, 3, val4);
        return new LongInterpretation(List.of(var1, var2, var3, var4), bits);
    }

    static Interpretation of(String var, boolean val) {
        return of(var, toBit(val));
    }

    static Interpretation of(String var1, boolean val1, String var2, boolean val2) {
        return of(var1, toBit(val1), var2, toBit(val2));
    }

    static Interpretation of(String var1, boolean val1, String var2, boolean val2, String var3, boolean val3) {
        return of(var1, toBit(val1), var2, toBit(val2), var3, toBit(val3));
    }

    static Interpretation of(String var1, boolean val1, String var2, boolean val2,
                             String var3, boolean val3, String var4, boolean val4) {
        return of(var1, toBit(val1), var2, toBit(val2), var3, toBit(val3), var4, toBit(val4));
    }

    static Interpretation ofPairs(Pair firstPair, Pair... pairs) {
        if (62 < pairs.length) {
            throw new IllegalArgumentException(
                    "More than 63 variables are not supported yet.");
        }
        final List<String> variableNames = new ArrayList<>();
        variableNames.add(firstPair.getVariableName());
        long bits = encode(0, 0, firstPair.getValue());
        int bitIndexCounter = 1;
        for (Pair pair : pairs) {
            variableNames.add(pair.getVariableName());
            bits = encode(bits, bitIndexCounter++, firstPair.getValue());
        }
        return new LongInterpretation(variableNames, bits);
    }

    /**
     * get the boolean value (i.e. the interpretation) for a variable
     *
     * @param variableName the name of the variable
     * @return the boolean value (i.e. the interpretation) of the variable
     */
    boolean get(String variableName);

    default int getAsBit(String variableName) {
        return toBit(get(variableName));
    }

    List<String> getVariableNames();

    String asBitsWhitespaceSeparated();

    class Pair {
        private final String variableName;
        private final int value;

        public Pair(String variableName, int value) {
            this.variableName = variableName;
            this.value = value;
        }

        String getVariableName() {
            return variableName;
        }

        int getValue() {
            return value;
        }
    }
}
