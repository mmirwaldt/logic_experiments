package net.mirwaldt.logic.propositional.impl;

import net.mirwaldt.logic.propositional.api.Interpretation;
import net.mirwaldt.logic.propositional.api.Proposition;
import org.junit.jupiter.api.Test;

import static net.mirwaldt.logic.propositional.api.Proposition.*;
import static org.junit.jupiter.api.Assertions.*;

public class NandTest {
    private final Proposition A = variable("A");
    private final Proposition B = variable("B");

    private final Proposition A_NAND_B = A.nand(B);

    @Test
    void test_expression() {
        assertEquals("¬(A ∧ B)", A_NAND_B.toExpression());
    }

    @Test
    void test_evaluate() {
        assertFalse(A_NAND_B.evaluate(Interpretation.of("A", true, "B", true)));
        assertTrue(A_NAND_B.evaluate(Interpretation.of("A", true, "B", false)));
        assertTrue(A_NAND_B.evaluate(Interpretation.of("A", false, "B", true)));
        assertTrue(A_NAND_B.evaluate(Interpretation.of("A", false, "B", false)));
    }
}
