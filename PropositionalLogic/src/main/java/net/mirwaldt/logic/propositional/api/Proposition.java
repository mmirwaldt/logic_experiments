package net.mirwaldt.logic.propositional.api;

import java.util.Map;

public interface Proposition {
    /**
     * evaluates an interpretation (the values for propositional variables)
     * @param interpretation the values for propositional variables
     * @return the evaluated value for the proposition
     * @throws java.util.NoSuchElementException if map contains not the needed values for the evaluation 
     */
    boolean evaluate(Map<String, Boolean> interpretation);
    String toExpression();
}
