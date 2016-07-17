package projects.NFAGeneratorBerrySethi;

import nfa.TransitionTable;
import projects.NFAGenerator;
import regex.RegularExpression;

public class NFAGeneratorImpl implements NFAGenerator {

    @Override
    public TransitionTable nfaFromRegex(RegularExpression regex) {

        // Traverse tree and calculate attributes.
        AttributeCalculatorVisitor visitor = new AttributeCalculatorVisitor();
        regex.accept(visitor);

        return visitor.getTransitionTable();
    }

}
