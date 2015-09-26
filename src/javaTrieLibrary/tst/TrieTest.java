package javaTrieLibrary.tst;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import javaTrieLibrary.Trie;

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
		assertTrue(trie.getWordCount() == 4351);
		System.out.println(trie.getChildren().get(0).getData());
		System.out.println(trie.getChildren().get(0).getWordCount());
	}
}
