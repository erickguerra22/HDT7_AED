package Dictionary;

import Tree.BinarySearchTree;
import Tree.ComparableAssociation;

public class Dictionary {
	private BinarySearchTree<ComparableAssociation<String,String>> english = new BinarySearchTree<ComparableAssociation<String,String>>();
	private BinarySearchTree<ComparableAssociation<String,String>> french = new BinarySearchTree<ComparableAssociation<String,String>>();
	
public Dictionary() {}
	
	public String addEnglish(String es, String en) {
		ComparableAssociation<String,String> newWord = new ComparableAssociation<String,String>(en,es);
		english.add(newWord);
		return "Palabra agregada exitosamente";
	}
	
	public String addFrench(String es, String fren) {
		ComparableAssociation<String,String> newWord = new ComparableAssociation<String,String>(fren,es);
		french.add(newWord);
		return "Palabra agregada exitosamente";
	}
	
	public void fillDictionary(String[] fileContent) {
		for(String row : fileContent) {
			String[] word = row.split(",");
			addEnglish(word[1], word[0]);
			addFrench(word[1], word[2]);
		}
	}
	
	
	
	private String fileToText(String[] fileContent) {
		String text = "";
		for(String row : fileContent) {
			text += row;
		}
		return text;
	}
	
}
