import berrysethi.TransitionTable;
import berrysethi.helper.TestHelper;
import org.junit.Test;
import projects.NFAGenerator;
import projects.NFAGeneratorBerrySethi.NFAGeneratorImpl;
import regex.*;

import static org.junit.Assert.assertEquals;

public class NFAGeneratorBerrySethiTest{

    @Test
    public void testNFAGeneratorViz() {
        TransitionTable table = TestHelper.buildtreeAndParse("(a|b)*a(a|b)");
        TestHelper.display(table);
    }

    @Test
    public void testNFAGenerator() {
        TestHelper.buildtreeAndParse("(ab)*|e+fg?");
        TestHelper.buildtreeAndParse("ab*");
        TestHelper.buildtreeAndParse("((ab)*)");
        TestHelper.buildtreeAndParse("(ab)?|(e)*(dg+h)");
        TestHelper.buildtreeAndParse("(a|b)*a(a|b)");
        TestHelper.buildtreeAndParse("ab|xe");

        // Test at least if did not crash.
        assertEquals(1, 1);
    }

    @Test
    public void testNFAGeneratorImpl() throws Exception {
        CharImpl a = new CharImpl("a");
        CharImpl b = new CharImpl("b");

        Concat con = new Concat(a, b);

        NFAGenerator gen = new NFAGeneratorImpl();
        nfa.TransitionTable table = gen.nfaFromRegex(con);

        System.out.println(table);

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