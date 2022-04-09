package Dictionary;

import java.util.Iterator;

import Tree.BinarySearchTree;
import Tree.ComparableAssociation;

public class Dictionary {
	private BinarySearchTree<ComparableAssociation<String,String>> english = new BinarySearchTree<ComparableAssociation<String,String>>();
	private BinarySearchTree<ComparableAssociation<String,String>> french = new BinarySearchTree<ComparableAssociation<String,String>>();
	
public Dictionary() {}
	
	private void addEnglish(String es, String en) {
		ComparableAssociation<String,String> newWord = new ComparableAssociation<String,String>(en,es);
		english.add(newWord);
	}
	
	private void addFrench(String es, String fren) {
		ComparableAssociation<String,String> newWord = new ComparableAssociation<String,String>(fren,es);
		french.add(newWord);
	}
	
	public String newWord(String es, String en, String fren) {
		addEnglish(es,en);
		addFrench(es,fren);
		return "Palabra agregada exitosamente en ambos idiomas";
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
		String message = "Contenido inOrder del diccionario ingles-espanol:";
		message += "\n"+english.treeString();
		return message;
	}
	
	public String getFrench() {
		String message = "Contenido inOrder del diccionario frances-espanol:";
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
	
	public String removeWord(String eng, String fren) {
		String message = "";
		message += removeEnglish(eng) + "\n";
		message += removeFrench(fren);
		return message;
	}
	
	private String removeEnglish(String eng) {
		ComparableAssociation<String, String> remove = null;
		Iterator<ComparableAssociation<String, String>> iterator = english.iterator();
		while(iterator.hasNext()) {
			ComparableAssociation<String,String> found = iterator.next();
			if(found.getKey().toLowerCase().equals(eng.toLowerCase()))
				remove = found;
		}
		if(remove == null)
			return "No se ha encontrado esta palabra en el diccionario ingles.";
		try {
			english.remove(remove);
			return "Palabra eliminada del diccionario ingles";
		}catch(Exception e) {
			return "Ha ocurrido un error al eliminar la palabra del diccionario ingles.";
		}
	}
	
	private String removeFrench(String fren) {
		ComparableAssociation<String, String> remove = null;
		Iterator<ComparableAssociation<String, String>> iterator = french.iterator();
		while(iterator.hasNext()) {
			ComparableAssociation<String,String> found = iterator.next();
			if(found.getKey().toLowerCase().equals(fren.toLowerCase()))
				remove = found;
		}
		if(remove == null)
			return "No se ha encontrado esta palabra en el diccionario frances.";
		try {
			french.remove(remove);
			return "Palabra eliminada del diccionario frances";
		}catch(Exception e) {
			return "Ha ocurrido un error al eliminar la palabra del diccionario frances.";
		}
	}

	private String updateEnglish(String eng, String translate) {
		removeEnglish(eng);
		addEnglish(translate,eng);
		return "Diccionario ingles actualizado.";
	}

	private String updateFrench(String fren, String translate) {
		removeEnglish(fren);
		addEnglish(translate,fren);
		return "Diccionario frances actualizado.";
	}
	
	public boolean wordFound(String eng, String fren) {
		ComparableAssociation<String, String> found = null;
		Iterator<ComparableAssociation<String, String>> iterator = french.iterator();
		while(iterator.hasNext()) {
			ComparableAssociation<String,String> match = iterator.next();
			if(match.getKey().toLowerCase().equals(fren.toLowerCase()))
				found = match;
		}
		
		iterator = english.iterator();
		while(iterator.hasNext()) {
			ComparableAssociation<String,String> match = iterator.next();
			if(match.getKey().toLowerCase().equals(eng.toLowerCase()))
				found = match;
		}
		
		return !(found == null);
	}

	public String updateWord(String esp, String eng, String fren) {
		String message = "";
		message += updateEnglish(eng,esp)+"\n";
		message += updateFrench(fren,esp)+"\n"; 
		return message;
	}
	
	public void updateDictionary() {
		
	}
	
}
