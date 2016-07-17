package helper;

import projects.NFAGeneratorBerrySethi.NFAGeneratorImpl;
import projects.NFAGeneratorBerrySethi.Node.Node;
import projects.NFAGeneratorBerrySethi.TransitionTable.TransitionTableImpl;

import javax.swing.*;

public class TestHelper {

    public static TransitionTableImpl buildtreeAndParse(String regex) {
        System.out.println("Parse regular expression.");
        Node rootNode = TreeBuilder.Parse(StringToolkit.Orify(regex));

        NFAGeneratorImpl gen = new NFAGeneratorImpl();
        TransitionTableImpl table = gen.nfaFromRegex(rootNode);

        System.out.println(table);
        
        Node.ResetIdCounter();
        
        return table;
    }

    public static void display(TransitionTableImpl table) {
        Viz frame = new Viz(table);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 300);
        frame.setVisible(true);

        while (true) {
        }
    }

}
