package projects.NFAGeneratorBerrySethi;

import nfa.TransitionTable;
import projects.NFAGenerator;
import projects.NFAGeneratorBerrySethi.AttributeCalculator.*;
import projects.NFAGeneratorBerrySethi.TransitionTable.StateImpl;
import projects.NFAGeneratorBerrySethi.TransitionTable.TransitionTableImpl;
import regex.Char;
import regex.RegularExpression;

import java.util.ArrayList;
import java.util.HashMap;

public class NFAGeneratorImpl implements NFAGenerator {

    @Override
    public TransitionTable nfaFromRegex(RegularExpression regex) {

        Attributes attr = new Attributes();

        // Traverse tree and calculate attributes.

        AttributeCalculatorVisitor firstlastempty = new FirstLastEmptyVisitor(attr);
        regex.accept(firstlastempty);

        AttributeCalculatorVisitor next = new NextVisitor(attr);
        regex.accept(next);

        LeafVisitor leafs = new LeafVisitor();
        regex.accept(leafs);

        return getTransitionTable(attr, leafs.getLeafs());
    }

    private TransitionTable getTransitionTable(Attributes attr, ArrayList<Char> leafs) {
        TransitionTableImpl table = new TransitionTableImpl();

        StateImpl startState = new StateImpl();
        table.setStartState(startState);

        HashMap<Char, StateImpl> states = new HashMap<>();

        int id = 0;
        for (Char character : leafs) {
            states.put(character, new StateImpl(id++));
        }

        if (attr.empty.get(startState))
            startState.setFinal();

        for (RegularExpression exp : attr.last.get(startState))
            states.get(exp).setFinal();

        for (Char character : leafs) {
            if (attr.first.get(startState).contains(character)) {
                table.addEntry(startState, states.get(character), character.getCharacter());
            }

            for (Char character_j : leafs) {
                if (attr.next.get(character).contains(character_j))
                    table.addEntry(states.get(character),
                            states.get(character_j),
                            character_j.getCharacter());
            }

        }

        return table;
    }

}
