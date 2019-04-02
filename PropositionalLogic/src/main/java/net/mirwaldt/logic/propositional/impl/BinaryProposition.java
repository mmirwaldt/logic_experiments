package net.mirwaldt.logic.propositional.impl;

import net.mirwaldt.logic.propositional.api.Proposition;
import net.mirwaldt.logic.propositional.util.api.BiBooleanPredicate;

import java.util.Map;

import static net.mirwaldt.logic.propositional.util.PropositionUtils.toFinalExpression;

public class BinaryProposition implements Proposition {
    private final Proposition leftProposition;
    private final Proposition rightProposition;
    private final BiBooleanPredicate biBooleanPredicate;
    private final String expressionTemplate;

    public BinaryProposition(Proposition leftProposition, Proposition rightProposition, 
                             BiBooleanPredicate biBooleanPredicate, String expressionTemplate) {
        this.leftProposition = leftProposition;
        this.rightProposition = rightProposition;
        this.biBooleanPredicate = biBooleanPredicate;
        this.expressionTemplate = expressionTemplate;
    }

    @Override
    public boolean evaluate(Map<String, Boolean> interpretation) {
        return biBooleanPredicate.test(leftProposition.evaluate(interpretation), 
                rightProposition.evaluate(interpretation));
    }

    @Override
    public String toExpression() {
        final String leftExpression = toFinalExpression(leftProposition);
        final String rightExpression = toFinalExpression(rightProposition);
        return String.format(expressionTemplate, leftExpression, rightExpression);
    }
}
