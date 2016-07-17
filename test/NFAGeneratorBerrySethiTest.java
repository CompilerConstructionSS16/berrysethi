import berrysethi.TransitionTable;
import berrysethi.helper.TestHelper;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NFAGeneratorBerrySethiTest {

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

}
