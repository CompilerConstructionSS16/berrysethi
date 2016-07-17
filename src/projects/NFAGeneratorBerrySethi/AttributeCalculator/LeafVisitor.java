package projects.NFAGeneratorBerrySethi.AttributeCalculator;

import regex.Char;
import regex.RegularExpressionVisitor;

import java.util.ArrayList;


public class LeafVisitor implements RegularExpressionVisitor {

    private ArrayList<Char> leafs = new ArrayList<>();

    @Override
    public void visit(Char character) {
        leafs.add(character);
    }

    public ArrayList<Char> getLeafs() {
        return leafs;
    }

}
