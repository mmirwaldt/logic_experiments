package net.mirwaldt.logic.propositional.normalizer.impl;

import net.mirwaldt.logic.propositional.interpretation.api.Interpretation;
import net.mirwaldt.logic.propositional.interpretation.impl.LongInterpretationsIterable;
import net.mirwaldt.logic.propositional.normalizer.api.PropositionNormalizer;
import net.mirwaldt.logic.propositional.proposition.api.Proposition;
import net.mirwaldt.logic.propositional.proposition.impl.AndProposition;
import net.mirwaldt.logic.propositional.proposition.impl.VariableProposition;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import static net.mirwaldt.logic.propositional.proposition.api.Proposition.not;

public class DisjunctivePropositionNormalizer implements PropositionNormalizer {
    @Override
    public Proposition normalize(Proposition proposition) {
        Proposition normalizedProposition = null;
        final List<String> variableNames = new ArrayList<>(proposition.findVariableNames());
        variableNames.sort(Comparator.naturalOrder());
        final LongInterpretationsIterable longInterpretationsIterable = new LongInterpretationsIterable(variableNames);

        for (Interpretation interpretation : longInterpretationsIterable) {
            if (proposition.evaluate(interpretation)) {
                final AndProposition conjunction = makeConjunction(variableNames, interpretation);
                if (normalizedProposition == null) {
                    normalizedProposition = conjunction;
                } else {
                    normalizedProposition = normalizedProposition.or(conjunction);
                }
            }
        }

        if (normalizedProposition == null) {
            throw new NoSuchElementException(
                    "No disjunctive normal form available for '" + proposition.toExpression() + "'!");
        } else {
            return normalizedProposition;
        }
    }

    private AndProposition makeConjunction(List<String> variableNames, Interpretation interpretation) {
        return AndProposition.create(variableNames.stream()
                .map(VariableProposition::new)
                .map(variableProposition ->
                        (interpretation.get(variableProposition.getVariableName())
                                ? variableProposition
                                : not(variableProposition))
                )
                .collect(Collectors.toList()));
    }
}