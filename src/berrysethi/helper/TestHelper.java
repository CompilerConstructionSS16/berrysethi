package berrysethi.helper;

import berrysethi.NFAGeneratorBerrySethi;
import berrysethi.Node.Node;
import berrysethi.TransitionTable;
import helper.Viz;

import javax.swing.*;

public class TestHelper {

    public static TransitionTable buildtreeAndParse(String regex) {
        System.out.println("Parse regular expression.");
        Node rootNode = TreeBuilder.Parse(StringToolkit.Orify(regex));

        NFAGeneratorBerrySethi gen = new NFAGeneratorBerrySethi();
        TransitionTable table = gen.nfaFromRegex(rootNode);

        System.out.println(table);

        Node.ResetIdCounter();

        return table;
    }

    public static void display(TransitionTable table) {
        Viz frame = new Viz(table);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 300);
        frame.setVisible(true);

        while (true) {
        }
    }

}
