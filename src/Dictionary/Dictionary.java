package Dictionary;

import java.util.Iterator;

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
	
	private String englishToSpanish(String word) {
		Iterator<ComparableAssociation<String, String>> iterator = english.iterator();
		while(iterator.hasNext()) {
			ComparableAssociation<String,String> found = iterator.next();
			if(found.getKey().toLowerCase().equals(word.toLowerCase()))
				word = found.getValue();
		}
		return word;
	}
	
	private String frenchToSpanish(String word) {
		Iterator<ComparableAssociation<String, String>> iterator = french.iterator();
		while(iterator.hasNext()) {
			ComparableAssociation<String,String> found = iterator.next();
			if(found.getKey().toLowerCase().equals(word.toLowerCase())) {
				word = found.getValue();
			}
		}
		return word;
	}
	
	public String translateText(String[] fileContent) {
		String text = fileToText(fileContent);
		String transText = "";
		String[] words = text.split(" ");	
		for(String word : words) {
			String transWord = "";
			word = word.replaceAll("['\\/\",:;.]", "");
			transWord = englishToSpanish(word);
			if(word.equals(transWord))
				transWord = frenchToSpanish(word);
			transWord = transWord.equals(word) ? "*"+ word +"*" : transWord;
			transText += transWord + " ";
		}
		return transText;
	}
	
	public String getEnglish() {
		String message = "Contenido inOrder del diccionario ingles-espanol";
		message += "\n"+english.treeString();
		return message;
	}
	
	public String getFrench() {
		String message = "Contenido inOrder del diccionario frances-espanol";
		message += "\n"+french.treeString();
		return message;
	}
	
	private String fileToText(String[] fileContent) {
		String text = "";
		for(String row : fileContent) {
			text += row;
		}
		return text;
	}
	
}
