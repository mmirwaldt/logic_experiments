package net.mirwaldt.logic.propositional.proposition.impl;

import net.mirwaldt.logic.propositional.interpretation.api.PropositionInterpretation;
import net.mirwaldt.logic.propositional.proposition.api.Proposition;
import org.junit.jupiter.api.Test;

import static net.mirwaldt.logic.propositional.proposition.api.Proposition.*;
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
        assertFalse(A_NAND_B.evaluate(PropositionInterpretation.of("A", true, "B", true)));
        assertTrue(A_NAND_B.evaluate(PropositionInterpretation.of("A", true, "B", false)));
        assertTrue(A_NAND_B.evaluate(PropositionInterpretation.of("A", false, "B", true)));
        assertTrue(A_NAND_B.evaluate(PropositionInterpretation.of("A", false, "B", false)));
    }
}
