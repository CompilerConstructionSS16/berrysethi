import java.util.ArrayList;


public class Node {
	public enum Type{
		CONCAT, OR, Q_MARK, ASTERISK, PLUS, LEAF
	}
	public static int idCounter = 0;
	
	public Node parent;
	public ArrayList<Node> children, first, last, next;
	public boolean empty;
	public Type type;
	public int id;
	public int firstCharIndex;
	public char nonterminal;
	
	public Node( Type type){
		this.type = type;
		if( type != Type.LEAF){
			id = -1;
		}else{
			id = idCounter;
			idCounter++;
		}
		children = new ArrayList<Node>();
		first = new ArrayList<Node>();
		last = new ArrayList<Node>();
		next = new ArrayList<Node>();
		firstCharIndex = -1;
		
	}

	public void addParent( Node parent){
		if( parent == null) return;
		this.parent = parent;
		parent.children.add( this);
		if( parent.firstCharIndex  == -1 || parent.firstCharIndex > firstCharIndex){
			parent.firstCharIndex = firstCharIndex;
		}
	}

	public void Debug(String tabs){
		if( type == Type.LEAF){
			System.out.print(tabs+ nonterminal+ id);
		}else{
			System.out.print(tabs+ type);
		}
		//tabs += '\t';
		if( children.size() > 0) System.out.print(tabs+ "{");
		
		for( int i = 0; i < children.size(); i++){
			children.get(i).Debug(tabs);
		}

		if( children.size() > 0) System.out.print(tabs+ "}");

	}
	
}
