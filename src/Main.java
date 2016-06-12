import sun.reflect.generics.tree.Tree;

public class Main {

    public static void main(String[] args) {
        Node rootNode = null;

        System.out.println("Parse regular expression.");

        //Parse(StringToolkit.Orify("ab*")).Debug("");
        rootNode = TreeBuilder.Parse(StringToolkit.Orify("(ab)*|e+fg?"));
        //Parse( StringToolkit.Orify("((ab)*)")).Debug("");
        //Parse( StringToolkit.Orify("(ab)?|(e)*(dg+h)")).Debug("");
        //Parse( StringToolkit.Orify("(ab)?|(e)*(dg+h)")).Debug("");
        //Parse( StringToolkit.Orify("ab|xe")).Debug("");

        rootNode.Debug("");

        System.out.println("Compute helper attributes.");
        rootNode.computeAttributes();
    }

}
