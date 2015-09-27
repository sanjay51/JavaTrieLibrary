package net.sanjav.javaTrieLibrary.unittests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import net.sanjav.javaTrieLibrary.Node;
import net.sanjav.javaTrieLibrary.Trie;

public class TrieTest {
	Trie trie = new Trie();
	
	@Before
	public void before() {
		LibInitiate.initTrie(trie);
	}
	
	@Test
	public void testTrie() {
		//test with a common word
		assertTrue(trie.containsWord("photograph"));
		
		//test with a long incorrect word
		assertFalse(trie.containsWord("somerandomword"));
		
		//test with a small incorrect word
		assertFalse(trie.containsWord("xy"));
		
		//test with an incorrect word which is a substring of a legitimate word
		assertFalse(trie.containsWord("thi"));
	}
	
	@Test
	public void testWordCount() {
		assertTrue(trie.getWordCount() == 4350);
	}
	
	@Test
	public void testGetNextPopularCharacters() {
		List<CharPopularityModel> list = trie.getNextPopularCharacters("qu");
		
		float totalPercent = 0;
		for (CharPopularityModel character : list) {
			System.out.println(character.getCharacter() + ": " + character.getRelativePopularity());
			totalPercent += character.getRelativePopularity();
			
			if (character.getCharacter() == 'e') {
				assertEquals((int) character.getRelativePopularity(), 39);
			}
		}
		
		assertEquals((int)totalPercent, 100);
	}
	
	@Test
	public void testGetNextPopularCharactersWhenPrefixDoesntExist() {
		trie.getNextPopularCharacters("ks"); //shouldn't throw exception
	}
	
	@Test
	public void testFrequencyAndPopularity() {
		System.out.println("Word count: " + trie.getWordCount());
		for (Node node : trie.getChildren()) {
			System.out.println("Frequency of " + node.getData() + ": " + node.getFrequency());
			System.out.println("Popularity of " + node.getData() + ": " + node.getPopularity());
		}
	}
}
