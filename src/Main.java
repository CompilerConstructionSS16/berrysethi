import berrysethi.helper.TestHelper;

public class Main {

    public static void main(String[] args) {
        TestHelper.buildtreeAndParse("(ab)*|e+fg?");
        TestHelper.buildtreeAndParse("ab*");
        TestHelper.buildtreeAndParse("((ab)*)");
        TestHelper.buildtreeAndParse("(ab)?|(e)*(dg+h)");
        TestHelper.buildtreeAndParse("(a|b)*a(a|b)");
        TestHelper.buildtreeAndParse("ab|xe");

        TestHelper.buildtreeAndParse("(a|b)*a(a|b)");
    }

}
