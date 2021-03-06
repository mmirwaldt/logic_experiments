package net.mirwaldt.logic.propositional.proposition.impl;

import net.mirwaldt.logic.propositional.interpretation.api.PropositionInterpretation;
import net.mirwaldt.logic.propositional.proposition.api.Proposition;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

import static net.mirwaldt.logic.propositional.proposition.api.Proposition.TRUE;
import static net.mirwaldt.logic.propositional.proposition.api.Proposition.variable;
import static org.junit.jupiter.api.Assertions.*;

public class VariablePropositionTest {
    private final VariableProposition A = variable("A");

    @Test
    void test_nullForbidden() {
        assertThrows(NullPointerException.class, () -> variable(null));
    }

    @Test
    void test_toExpression() {
        assertEquals("A", A.toExpression());
    }

    @Test
    void test_evaluateWithoutValue() {
        assertThrows(NoSuchElementException.class, () -> A.evaluate(PropositionInterpretation.of("B", true)));
    }

    @Test
    void test_evaluateWithValueFalse() {
        assertFalse(A.evaluate(PropositionInterpretation.of("A", false)));
    }

    @Test
    void test_evaluateWithValueTrue() {
        assertTrue(A.evaluate(PropositionInterpretation.of("A", true)));
    }
    
    @Test
    void test_findVariableNames() {
        assertEquals(Set.of("A"), A.findVariableNames());
    }
    
    @Test
    void test_getVariableName() {
        assertEquals("A", A.getVariableName());
    }

    @Test
    void test_equals() {
        Proposition A = variable("A");
        assertFalse(A.equals(null));
        assertTrue(A.equals(A));
        assertTrue(A.equals(variable("A")));
        assertFalse(A.equals(variable("B")));
    }

    @Test
    void test_hashCodeWithEquals() {
        Set<Proposition> propositions = new HashSet<>();
        propositions.add(TRUE);
        propositions.add(variable("A"));

        assertFalse(propositions.contains(variable("B")));
        assertTrue(propositions.contains(variable("A")));
    }
}
