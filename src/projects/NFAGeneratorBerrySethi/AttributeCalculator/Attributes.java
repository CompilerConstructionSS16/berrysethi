package projects.NFAGeneratorBerrySethi.AttributeCalculator;

import regex.RegularExpression;

import java.util.ArrayList;
import java.util.HashMap;

public class Attributes {

    public HashMap<RegularExpression, Boolean> empty = new HashMap<>();

    public HashMap<RegularExpression, ArrayList<RegularExpression>> first = new HashMap<>();

    public HashMap<RegularExpression, ArrayList<RegularExpression>> last = new HashMap<>();

    public HashMap<RegularExpression, ArrayList<RegularExpression>> next = new HashMap<>();

}
