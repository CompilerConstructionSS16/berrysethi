package projects.NFAGeneratorBerrySethi.AttributeCalculator;

import regex.RegularExpression;
import regex.RegularExpressionVisitor;

import java.util.ArrayList;
import java.util.HashMap;

/*

    empty: Post-order DFS
    first: Post-order DFS
    next: Pre-order DFS
    last: Post-order DFS

 */

public class AttributeCalculatorVisitor implements RegularExpressionVisitor {

    protected void addValue(HashMap<RegularExpression, ArrayList<RegularExpression>> map,
                            RegularExpression key, RegularExpression value) {
        if (value == null)
            return;

        if (!map.containsKey(key)) {
            map.put(key, new ArrayList<>());
        }
        map.get(key).add(value);
    }

    protected void addValue(HashMap<RegularExpression, ArrayList<RegularExpression>> map,
                            RegularExpression key, ArrayList<RegularExpression> values) {
        if (values == null)
            return;

        if (!map.containsKey(key)) {
            map.put(key, new ArrayList<>());
        }
        map.get(key).addAll(values);
    }

}
