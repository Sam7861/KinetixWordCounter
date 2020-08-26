package com.kinetix.counter;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class WordCounterTest {

	private Translator englishTranslator= Mockito.mock(Translator.class);

	@Test
	void shouldThrowExceptionWhenGivenWordIsNull() {
		//Given
		WordCounter wordCounter = new WordCounter(englishTranslator);
		//Then
		Exception exception = assertThrows(IllegalArgumentException.class, () -> wordCounter.addWord(null));
		assertEquals(exception.getMessage(), "Word should not be null/empty");
	}

	@Test
	void shouldThrowExceptionWhenGivenWordIsEmpty() {
		//Given
		WordCounter wordCounter = new WordCounter(englishTranslator);
		//Then
		Exception exception = assertThrows(IllegalArgumentException.class, () -> wordCounter.addWord(""));
		assertEquals(exception.getMessage(), "Word should not be null/empty");
	}

	@Test
	void shouldThrowExceptionWhenGivenAlphanumericWord() {
		//Given
		WordCounter wordCounter = new WordCounter(englishTranslator);
		//Then
		Exception exception = assertThrows(IllegalArgumentException.class, () -> wordCounter.addWord("Orange123"));
		assertEquals(exception.getMessage(), "Given word is not alphabetic: Orange123");
	}

	@Test
	void shouldReturnCountAsOneWhenGivenValidWord() throws Exception {

		//Given
		WordCounter wordCounter = new WordCounter(englishTranslator);
		when(englishTranslator.translate("Orange")).thenReturn("Orange");

		//When
		wordCounter.addWord("Orange");

		//Then
		assertEquals(1, wordCounter.getCountOf("Orange"));
	}

	@Test
	void shouldReturnWordCountAs1_WhenGivenDifferentWords() throws Exception {

		//Given
		WordCounter wordCounter = new WordCounter(englishTranslator);
		when(englishTranslator.translate("Orange")).thenReturn("Orange");
		when(englishTranslator.translate("Apple")).thenReturn("Apple");

		//When
		wordCounter.addWord("Orange");
		wordCounter.addWord("Apple");

		//Then
		assertEquals(1, wordCounter.getCountOf("Orange"));
		assertEquals(1, wordCounter.getCountOf("Apple"));
	}

	@Test
	void shouldIncrementCountWhenGivenWordsWithDifferentCapitilization() throws Exception {

		//Given
		WordCounter wordCounter = new WordCounter(englishTranslator);
		when(englishTranslator.translate("CAT")).thenReturn("Cat");
		when(englishTranslator.translate("Cat")).thenReturn("Cat");

		//When
		wordCounter.addWord("CAT");
		wordCounter.addWord("Cat");

		//Then
		assertEquals(2, wordCounter.getCountOf("Cat"));
	}

	@Test
	void shouldIncrementCountWhenGivenSameWordInDifferentLanguages() throws Exception {
		//Given
		WordCounter wordCounter = new WordCounter(englishTranslator);
		when(englishTranslator.translate("FLOWER")).thenReturn("Flower");
		when(englishTranslator.translate("FLOR")).thenReturn("Flower");
		when(englishTranslator.translate("BLUME")).thenReturn("Flower");

		//When
		wordCounter.addWord("FLOWER");
		wordCounter.addWord("FLOR");
		wordCounter.addWord("BLUME");

		//Then
		assertEquals(3, wordCounter.getCountOf("Flower"));
	}
}
