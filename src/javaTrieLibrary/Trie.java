package javaTrieLibrary;

import java.util.List;

public class Trie {
	Node rootNode;
	
	public Trie() {
		rootNode = new Node('0');
		rootNode.setRoot();
	}
	
	public void insertWord(String word) {
		rootNode.insertWord(word.toLowerCase().toCharArray());
	}
	
	public List<String> getWordsStartingWith(String prefix) {
		return rootNode.findWordsByPrefix(prefix.toCharArray());
	}
	
	public boolean containsWord(String word) {
		return rootNode.containsWord(word.toLowerCase().toCharArray());
	}
	
	public int getWordCount() {
		return rootNode.getWordCount();
	}
	
	public List<Node> getChildren() {
		return rootNode.getChildren();
	}
	
	public void print() {
		rootNode.print();
	}
}
