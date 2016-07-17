import helper.TestHelper;
import projects.NFAGeneratorBerrySethi.TransitionTable.TransitionTableImpl;

public class MainViz {

    public static void main(String[] args) {
        TransitionTableImpl table = TestHelper.buildtreeAndParse("(a|b)*a(a|b)");

        TestHelper.display(table);
    }

}
