package com.kinetix.counter;
/**
 * Pluggable translator
 * @author Sam
 *
 */

public interface Translator {
	
	/**
	 * @param word to be translated.
	 * @return translated word.
	 */
    public String translate(String word) ;
}

