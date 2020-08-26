# KinetixWordCounter

-> WordCounter Class

WordCounter is main class which contain mainly 1)addWord and 2)getCountOf methods.

Implementation Idea is - as per the problem statement, any language word can be stored in list if it is aplhabetic. 
Then if you supply any word it has to return the count of similar words(English equivalent). 
Hence storing wordpair object of two words, 1)given input word any language word and 2) English equivalent word and adding to arraylist. 
Then count the number of equivalent english words. 
Here we need to treat all similar words in differnt languages as same. 
So,obviously we need to treat same words in lower and upper cases as same word. 
Hence while counting the similar words converting each word to lowercase for final word count.

-> WordPair Class

WordPair Class creates object of two words. 1)given any language alphabetic word and 2)Euivalent english word.

-> Translator Class

Translator class was not implemented. As per the problem statement assuming it was supplied by some third party provider (like GoogleTranslateApi). 
