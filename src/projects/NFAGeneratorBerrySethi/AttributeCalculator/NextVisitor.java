package projects.NFAGeneratorBerrySethi.AttributeCalculator;

import regex.*;

public class NextVisitor extends AttributeCalculatorVisitor {

    private Attributes attr;

    public NextVisitor(Attributes attr) {
        this.attr = attr;
    }

    @Override
    public void pre(AlternationExpression expression) {
        // '|'

        RegularExpression left = expression.getLHS();
        RegularExpression right = expression.getRHS();

        addValue(attr.next, left, attr.next.get(expression));
        addValue(attr.next, right, attr.next.get(expression));
    }

    @Override
    public void pre(ConcatenationExpression expression) {
        // '.'

        RegularExpression left = expression.getLHS();
        RegularExpression right = expression.getRHS();

        addValue(attr.next, right, attr.next.get(expression));

        if (attr.empty.get(right))
            addValue(attr.next, left, attr.next.get(expression));
        addValue(attr.next, left, attr.first.get(right));
    }

    @Override
    public void pre(KleeneStarExpression expression) {
        // '*'

        RegularExpression child = expression.getChild();

        addValue(attr.next, child, attr.next.get(expression));
        addValue(attr.next, child, attr.first.get(child));
    }

    @Override
    public void pre(OptionalExpression expression) {
        // '?'

        RegularExpression child = expression.getChild();

        addValue(attr.next, child, attr.next.get(expression));
    }

    @Override
    public void pre(PlusExpression expression) {
        // '+'

        RegularExpression child = expression.getChild();

        addValue(attr.next, child, attr.next.get(expression));
        addValue(attr.next, child, attr.first.get(child));
    }

}
