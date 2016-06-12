public class Main {

    public static void main(String[] args) {
        Node rootNode = null;

        System.out.println("Parse regular expression.");

        //rootNode = TreeBuilder.Parse(StringToolkit.Orify("(ab)*|e+fg?"));
        rootNode = TreeBuilder.Parse(StringToolkit.Orify("(a|b)*a(a|b)"));

        //Parse(StringToolkit.Orify("ab*")).Debug("");
        //Parse( StringToolkit.Orify("((ab)*)")).Debug("");
        //Parse( StringToolkit.Orify("(ab)?|(e)*(dg+h)")).Debug("");
        //Parse( StringToolkit.Orify("(ab)?|(e)*(dg+h)")).Debug("");
        //Parse( StringToolkit.Orify("ab|xe")).Debug("");

        //rootNode.Debug("");

        System.out.println("Compute helper attributes.");
        rootNode.computeAttributes();
        AttributeCalculator.printAttributes(rootNode);
    }

}
