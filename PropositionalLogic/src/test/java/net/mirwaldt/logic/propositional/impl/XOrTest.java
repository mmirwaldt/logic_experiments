package net.mirwaldt.logic.propositional.impl;

import net.mirwaldt.logic.propositional.api.Interpretation;
import net.mirwaldt.logic.propositional.api.Proposition;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static net.mirwaldt.logic.propositional.impl.Propositions.xor;
import static org.junit.jupiter.api.Assertions.*;

public class XOrTest {
    private final Proposition A = Propositions.variable("A");
    private final Proposition B = Propositions.variable("B");
    
    private final Proposition A_XOR_B = xor(A, B);
    private final Proposition NEGATED_A_XOR_B = A_XOR_B.negate();

    @Test
    void test_expression() {
        assertEquals("A ⩒ B", A_XOR_B.toExpression());
        assertEquals("A ↔ B", NEGATED_A_XOR_B.toExpression());
    }

    @Test
    void test_evaluate() {
        assertFalse(A_XOR_B.evaluate(Interpretation.of("A", true, "B", true)));
        assertTrue(A_XOR_B.evaluate(Interpretation.of("A", true, "B", false)));
        assertTrue(A_XOR_B.evaluate(Interpretation.of("A", false, "B", true)));
        assertFalse(A_XOR_B.evaluate(Interpretation.of("A", false, "B", false)));

        assertTrue(NEGATED_A_XOR_B.evaluate(Interpretation.of("A", true, "B", true)));
        assertFalse(NEGATED_A_XOR_B.evaluate(Interpretation.of("A", true, "B", false)));
        assertFalse(NEGATED_A_XOR_B.evaluate(Interpretation.of("A", false, "B", true)));
        assertTrue(NEGATED_A_XOR_B.evaluate(Interpretation.of("A", false, "B", false)));
    }
    
    @Test
    void test_findVariableNames() {
        assertEquals(Set.of("A", "B"), A_XOR_B.findVariableNames());
        assertEquals(Set.of("A", "B"), NEGATED_A_XOR_B.findVariableNames());
    }
}
