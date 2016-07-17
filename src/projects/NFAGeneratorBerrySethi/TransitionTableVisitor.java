package projects.NFAGeneratorBerrySethi;

import nfa.TransitionTable;
import regex.Char;
import regex.RegularExpressionVisitor;

public class TransitionTableVisitor implements RegularExpressionVisitor {

    // Needs access to attributes ...

    @Override
    public void visit(Char character) {

    }

    public TransitionTable getTable() {
        TransitionTable table;

        // TODO

        return table;
    }

}
