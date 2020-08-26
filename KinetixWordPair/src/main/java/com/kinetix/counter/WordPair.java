package com.kinetix.counter;
import lombok.Data;

/**
 * This class consists of two words as pair -
 * 1-Anylanguage word, 2- English equivalent word
 * 
 * @author Sam
 *
 */
@Data
public class WordPair {

	private String word;
	private String translatedWord;

	public WordPair(String word,String translatedWord){
		this.word = word;
		this.translatedWord = translatedWord;
	}

	public String getWord() {
		return word;
	}

	public String getTranslatedWord() { 
		return translatedWord; 
	}

}
