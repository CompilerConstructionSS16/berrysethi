package projects.NFAGeneratorBerrySethi;

import nfa.TransitionTable;
import projects.NFAGenerator;
import regex.RegularExpression;

public class NFAGeneratorImpl implements NFAGenerator {

    @Override
    public TransitionTable nfaFromRegex(RegularExpression regex) {

        return null;
    }

    public TransitionTable nfaFromRegex(NodeInterface node) {

        System.out.println("Compute helper attributes.");
        //node.computeAttributes();

        return null;
    }
}
