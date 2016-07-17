package projects.NFAGeneratorBerrySethi.AttributeCalculator;

import regex.*;

public class FirstLastEmptyVisitor extends AttributeCalculatorVisitor {

    private Attributes attr;

    public FirstLastEmptyVisitor(Attributes attr) {
        this.attr = attr;
    }

    @Override
    public void visit(Char character) {
        attr.empty.put(character, false);

        addValue(attr.first, character, character);

        addValue(attr.last, character, character);
    }

    @Override
    public void post(AlternationExpression expression) {
        // '|'

        RegularExpression left = expression.getLHS();
        RegularExpression right = expression.getRHS();

        Boolean leftEmpty = attr.empty.get(left);
        Boolean rightEmpty = attr.empty.get(right);

        attr.empty.put(expression, leftEmpty | rightEmpty);

        addValue(attr.first, expression, attr.first.get(left));
        addValue(attr.first, expression, attr.first.get(right));

        addValue(attr.last, expression, attr.last.get(left));
        addValue(attr.last, expression, attr.last.get(right));
    }

    @Override
    public void post(ConcatenationExpression expression) {
        // '.'

        RegularExpression left = expression.getLHS();
        RegularExpression right = expression.getRHS();

        Boolean leftEmpty = attr.empty.get(left);
        Boolean rightEmpty = attr.empty.get(right);

        attr.empty.put(expression, leftEmpty && rightEmpty);

        if (attr.empty.get(left))
            addValue(attr.first, expression, attr.first.get(right));
        addValue(attr.first, expression, attr.first.get(left));

        if (attr.empty.get(right))
            addValue(attr.last, expression, attr.last.get(left));
        addValue(attr.last, expression, attr.last.get(right));
    }

    @Override
    public void post(KleeneStarExpression expression) {
        // '*'

        RegularExpression child = expression.getChild();

        attr.empty.put(expression, true);

        addValue(attr.first, expression, attr.first.get(child));

        addValue(attr.last, expression, attr.last.get(child));
    }

    @Override
    public void post(OptionalExpression expression) {
        // '?'

        RegularExpression child = expression.getChild();

        attr.empty.put(expression, true);

        addValue(attr.first, expression, attr.first.get(child));

        addValue(attr.last, expression, attr.last.get(child));
    }

    @Override
    public void post(PlusExpression expression) {
        // '+'

        RegularExpression child = expression.getChild();

        attr.empty.put(expression, false);

        addValue(attr.first, expression, attr.first.get(child));

        addValue(attr.last, expression, attr.last.get(child));
    }

}
