import berrysethi.TransitionTable;
import berrysethi.helper.TestHelper;

public class MainViz {

    public static void main(String[] args) {
        TransitionTable table = TestHelper.buildtreeAndParse("(a|b)*a(a|b)");

        TestHelper.display(table);
    }

}
