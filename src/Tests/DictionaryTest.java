package Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Dictionary.Dictionary;

class DictionaryTest {

	@Test
	void testNewWord() {
		Dictionary dictionary = new Dictionary();
		dictionary.newWord("quebrado", "broken", "casse");
		dictionary.newWord("plastico", "plastic", "plastique");
		dictionary.newWord("corazon", "heart", "coeur");
		dictionary.newWord("perdido", "lost", "perdu");
		dictionary.newWord("sentimiento", "feeling", "sentiment");
		assertEquals("Contenido inOrder del diccionario ingles-espanol:\n(broken, quebrado), (feeling, sentimiento), (heart, corazon), (lost, perdido), (plastic, plastico).", dictionary.getEnglish());
		assertEquals("Contenido inOrder del diccionario frances-espanol:\n(casse, quebrado), (coeur, corazon), (perdu, perdido), (plastique, plastico), (sentiment, sentimiento).", dictionary.getFrench());
	}

	@Test
	void testWordFound() {
		Dictionary dictionary = new Dictionary();
		dictionary.newWord("quebrado", "broken", "casse");
		dictionary.newWord("plastico", "plastic", "plastique");
		dictionary.newWord("corazon", "heart", "coeur");
		dictionary.newWord("perdido", "lost", "perdu");
		dictionary.newWord("sentimiento", "feeling", "sentiment");
		assertEquals(true, dictionary.wordFound("heart", "coeur"));
		assertEquals(false, dictionary.wordFound("mouse", "souris"));
		assertEquals(true, dictionary.wordFound("", "perdu"));
	}

}
