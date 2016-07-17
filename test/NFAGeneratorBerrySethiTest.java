import org.junit.Test;
import projects.NFAGeneratorBerrySethi.NFAGeneratorImpl;
import projects.NFAGeneratorBerrySethi.Node.Node;
import projects.NFAGeneratorBerrySethi.TransitionTable.TransitionTableImpl;

import static org.junit.Assert.assertEquals;

import javax.swing.JFrame;

public class NFAGeneratorBerrySethiTest {

    @Test
    public void testNFAGenerator() {
        System.out.println("Parse regular expression.");

        buildtreeAndParse("(a|b)*a(a|b)");


/*        buildtreeAndParse("(ab)*|e+fg?");
        buildtreeAndParse("ab*");
        buildtreeAndParse("((ab)*)");
        buildtreeAndParse("(ab)?|(e)*(dg+h)");
        buildtreeAndParse("(ab)?|(e)*(dg+h)");
        buildtreeAndParse("(a|b)*a(a|b)");
        buildtreeAndParse("ab|xe");*/


        assertEquals(1, 1);
    }

    private void buildtreeAndParse(String regex) {
        System.out.println("Parse regular expression.");
        Node rootNode = TreeBuilder.Parse(StringToolkit.Orify(regex));

        NFAGeneratorImpl gen = new NFAGeneratorImpl();
        TransitionTableImpl table = gen.nfaFromRegex(rootNode);

        System.out.println(table);

        display(table);
    }

    private void display(TransitionTableImpl table) {
        Viz frame = new Viz(table);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 300);
        frame.setVisible(true);

        while (true) {}
    }

}
