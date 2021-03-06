import helper.Viz;
import nfa.TransitionTable;
import org.junit.Test;
import projects.NFAGenerator;
import projects.NFAGeneratorBerrySethi.NFAGeneratorImpl;
import projects.NFAGeneratorBerrySethi.TransitionTable.TransitionTableImpl;
import regex.*;

import javax.swing.*;

import static org.junit.Assert.assertEquals;

public class NFAGeneratorBerrySethiTest {

    public static void display(TransitionTableImpl table) {
        Viz frame = new Viz(table);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 300);
        frame.setVisible(true);

        while (true) {
        }
    }

    @Test
    public void testNFAGeneratorImpl() throws Exception {
        CharImpl a = new CharImpl("a");
        CharImpl b = new CharImpl("b");

        Concat con = new Concat(a, b);

        NFAGenerator gen = new NFAGeneratorImpl();
        TransitionTable table = gen.nfaFromRegex(con);

        System.out.println(table);

        //display((TransitionTableImpl) table);

        // example in slides
        CharImpl a1 = new CharImpl("a1");
        CharImpl b2 = new CharImpl("b2");
        CharImpl a2 = new CharImpl("a2");
        CharImpl a3 = new CharImpl("a3");
        CharImpl b4 = new CharImpl("b4");

        Or westOr = new Or(a1, b2);
        Or eastOr = new Or(a3, b4);
        Star star = new Star(westOr);
        Concat eastConcat = new Concat(a2, eastOr);
        Concat root = new Concat(star, eastConcat);

        TransitionTable table2 = new NFAGeneratorImpl().nfaFromRegex(root);

        display((TransitionTableImpl) table2);

        // Test at least if did not crash.
        assertEquals(1, 1);
    }
}

class Concat implements ConcatenationExpression {

    RegularExpression left;
    RegularExpression right;

    Concat(RegularExpression left, RegularExpression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public RegularExpression getLHS() {
        return left;
    }

    @Override
    public RegularExpression getRHS() {
        return right;
    }
}

class Or implements AlternationExpression {

    RegularExpression left;
    RegularExpression right;

    Or(RegularExpression left, RegularExpression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public RegularExpression getLHS() {
        return left;
    }

    @Override
    public RegularExpression getRHS() {
        return right;
    }
}

class Star implements KleeneStarExpression {

    RegularExpression child;

    Star(RegularExpression child) {
        this.child = child;
    }

    @Override
    public RegularExpression getChild() {
        return child;
    }
}

class Plus implements PlusExpression {

    RegularExpression child;

    Plus(RegularExpression child) {
        this.child = child;
    }

    @Override
    public RegularExpression getChild() {
        return child;
    }
}

class QMark implements OptionalExpression {

    RegularExpression child;

    QMark(RegularExpression child) {
        this.child = child;
    }

    @Override
    public RegularExpression getChild() {
        return child;
    }
}

class CharImpl implements Char {
    String c;

    CharImpl(String c) {
        this.c = c;
    }

    @Override
    public String getCharacter() {
        return c;
    }
}