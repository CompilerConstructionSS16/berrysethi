package projects.NFAGeneratorBerrySethi;

import regex.*;

import java.util.ArrayList;
import java.util.HashMap;

/*

    empty: Post-order DFS
    first: Post-order DFS
    next: Pre-order DFS
    last: Post-order DFS

 */

public class AttributeCalculatorVisitor implements RegularExpressionVisitor {

    HashMap<RegularExpression, Boolean> empty = new HashMap<>();

    HashMap<RegularExpression, ArrayList<RegularExpression>> first = new HashMap<>();

    HashMap<RegularExpression, ArrayList<RegularExpression>> next = new HashMap<>();

    HashMap<RegularExpression, ArrayList<RegularExpression>> last = new HashMap<>();

    private void addValue(HashMap<RegularExpression, ArrayList<RegularExpression>> map,
                          RegularExpression key, RegularExpression value) {
        if (!map.containsKey(key)) {
            map.put(key, new ArrayList<>());
        }
        map.get(key).add(value);
    }

    private void addValue(HashMap<RegularExpression, ArrayList<RegularExpression>> map,
                          RegularExpression key, ArrayList<RegularExpression> values) {
        if (!map.containsKey(key)) {
            map.put(key, new ArrayList<>());
        }
        map.get(key).addAll(values);
    }

    @Override
    public void visit(Char character) {
        empty.put(character, false);

        addValue(first, character, character);

        addValue(last, character, character);
    }

    @Override
    public void post(AlternationExpression expression) {
        // '|'

        RegularExpression left = expression.getLHS();
        RegularExpression right = expression.getRHS();

        Boolean leftEmpty = empty.get(left);
        Boolean rightEmpty = empty.get(right);

        empty.put(expression, leftEmpty | rightEmpty);

        addValue(first, expression, first.get(left));
        addValue(first, expression, first.get(right));

        addValue(last, expression, last.get(left));
        addValue(last, expression, last.get(right));
    }

    @Override
    public void post(ConcatenationExpression expression) {
        // '.'

        RegularExpression left = expression.getLHS();
        RegularExpression right = expression.getRHS();

        Boolean leftEmpty = empty.get(left);
        Boolean rightEmpty = empty.get(right);

        empty.put(expression, leftEmpty && rightEmpty);

        if (empty.get(left))
            addValue(first, expression, first.get(right));
        addValue(first, expression, first.get(left));

        if (empty.get(right))
            addValue(last, expression, last.get(left));
        addValue(last, expression, last.get(right));
    }

    @Override
    public void post(KleeneStarExpression expression) {
        // '*'

        RegularExpression child = expression.getChild();

        empty.put(expression, true);

        addValue(first, expression, first.get(child));

        addValue(last, expression, last.get(child));
    }

    @Override
    public void post(OptionalExpression expression) {
        // '?'

        RegularExpression child = expression.getChild();

        empty.put(expression, true);

        addValue(first, expression, first.get(child));

        addValue(last, expression, last.get(child));
    }

    @Override
    public void post(PlusExpression expression) {
        // '+'

        RegularExpression child = expression.getChild();

        empty.put(expression, false);

        addValue(first, expression, first.get(child));

        addValue(last, expression, last.get(child));
    }

    @Override
    public void pre(AlternationExpression expression) {
        // '|'

        RegularExpression left = expression.getLHS();
        RegularExpression right = expression.getRHS();

        addValue(next, left, next.get(expression));
        addValue(next, right, next.get(expression));
    }

    @Override
    public void pre(ConcatenationExpression expression) {
        // '.'

        RegularExpression left = expression.getLHS();
        RegularExpression right = expression.getRHS();

        addValue(next, right, next.get(expression));

        if (empty.get(right))
            addValue(next, left, next.get(expression));
        addValue(next, left, first.get(right));
    }

    @Override
    public void pre(KleeneStarExpression expression) {
        // '*'

        RegularExpression child = expression.getChild();

        addValue(next, child, next.get(expression));
        addValue(next, child, first.get(child));
    }

    @Override
    public void pre(OptionalExpression expression) {
        // '?'

        RegularExpression child = expression.getChild();

        addValue(next, child, next.get(expression));
    }

    @Override
    public void pre(PlusExpression expression) {
        // '+'

        RegularExpression child = expression.getChild();

        addValue(next, child, next.get(expression));
        addValue(next, child, first.get(child));
    }
}
