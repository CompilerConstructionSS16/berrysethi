package projects.NFAGeneratorBerrySethi;

import nfa.TransitionTable;
import regex.Char;
import regex.RegularExpression;
import regex.RegularExpressionVisitor;

import java.util.ArrayList;
import java.util.HashMap;

public class TransitionTableVisitor implements RegularExpressionVisitor {

    ArrayList<Char> chars = new ArrayList<>();

    @Override
    public void visit(Char character) {
        chars.add(character);
    }

    public TransitionTable getTable(
            RegularExpression root,
            HashMap<RegularExpression, Boolean> empty,
            HashMap<RegularExpression, ArrayList<RegularExpression>> first,
            HashMap<RegularExpression, ArrayList<RegularExpression>> next,
            HashMap<RegularExpression, ArrayList<RegularExpression>> last) {

        TransitionTable table;

        // Add new root state.
        // TODO

        if (empty.get(root))
            // TODO

        for (RegularExpression exp : last.get(root))
            // TODO




        return table;
    }

}
