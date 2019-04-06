package net.mirwaldt.logic.propositional.api;

import net.mirwaldt.logic.propositional.impl.Propositions;

import java.util.Set;

import static net.mirwaldt.logic.propositional.util.PropositionUtils.toBit;

public interface Proposition {
    /**
     * evaluates an interpretation (the values for propositional variables)
     * @param interpretation the values for propositional variables
     * @return the evaluated value for the proposition
     * @throws java.util.NoSuchElementException if map contains not the needed values for the evaluation 
     */
    boolean evaluate(Interpretation interpretation);
    
    /**
     * converts proposition to expression string
     * @return the proposition as expression string
     */
    String toExpression();
    
    default int evaluateAsBit(Interpretation interpretation) {
        return toBit(evaluate(interpretation));
    }
    
    Set<String> findVariableNames();
    
    default Proposition negate() {
        return Propositions.negate(this);
    }
}
