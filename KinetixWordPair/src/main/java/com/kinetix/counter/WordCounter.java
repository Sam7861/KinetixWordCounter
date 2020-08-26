package com.kinetix.counter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Main class with a starting execution point of the program.
 * This class mainly maintains the list, facilitates to add any language word
 * if it is Alphabetic and returns the similar words count in the list
 *
 * @author Sam
 */

public class WordCounter {

    private static final Log LOGGER = LogFactory.getLog(WordCounter.class);
    private List<WordPair> wordPairList = new ArrayList<>();
    private Translator translator;

    WordCounter(Translator translator) {
        this.translator = translator;
    }

    /**
     * Receives word as input String parameter,
     * Then validates whether it is alphabetic or not and adds the word to the counter
     *
     * @param word to be added to the Wordpair list
     */
    public void addWord(String word) throws Exception {
        if (Objects.isNull(word) || word.isEmpty()) {
            throw new IllegalArgumentException("Word should not be null/empty");
        }
        if (!isAlphabetic(word)) {
            throw new IllegalArgumentException("Given word is not alphabetic: " + word);
        }
        String englishEquivalentWord = translator.translate(word);
        wordPairList.add(new WordPair(word, englishEquivalentWord));
    }

    /**
     * checks whether the given word is alphabetic
     *
     * @param givenWord to be added to the Wordpair list.
     * @return String equivalent English word.
     */
    private boolean isAlphabetic(String givenWord) {
        return givenWord != null && givenWord.chars().allMatch(Character::isLetter);
    }

    /**
     * Receives word as input string, searches and counts the similar words in the list
     * - here it considers any word as case insensitive (converts to lower case) for counting.
     *
     * @param givenWord to be added to the Wordpair list.
     * @return list of words.
     */
    public int getCountOf(String givenWord) {

        List<String> collect = wordPairList.stream().map(x -> x.getTranslatedWord()).collect(Collectors.toList());
        Map<String, Integer> wordPairMap = collect.stream()
                .collect(Collectors.toMap(w -> w.toLowerCase(), w -> 1, Integer::sum));

        LOGGER.debug(" wordPairMap --> " + wordPairMap);

        if (wordPairMap.get(givenWord.toLowerCase()) != null) {
            LOGGER.info("Given word " + givenWord + "'s equivalent wordscount in list is " + wordPairMap.get(givenWord.toLowerCase()));
            return wordPairMap.get(givenWord.toLowerCase());
        }

        LOGGER.info("Given word " + givenWord + "'s equivalent wordscount in list is 0");
        return 0;
    }
}
