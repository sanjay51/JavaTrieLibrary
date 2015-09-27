package net.sanjav.javaTrieLibrary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sanjav.javaTrieLibrary.unittests.CharPopularityModel;

public class Node {
	Node parent;
	char data;
	int wordCount;
	int frequency = 0; //general frequency if it makesAWord(), else 0
	int popularity = 0; //sum of frequency of all the words contained in that subtree
	private boolean makesAWord = false;
	Map<Character, Node> children;
	
	public Node(char data) {
		this.data = data;
		children = new HashMap<Character, Node>();
		wordCount = 0;
	}

	public boolean isRoot() {
		return (parent == null);
	}
	
	public void setRoot() {
		this.parent = null;
	}
	
	public boolean makesAWord() {
		return makesAWord;
	}
	
	public void setMakesAWord(boolean flag) {
		this.makesAWord = flag;
	}
	
	public char getData() {
		return data;
	}
	
	public int getFrequency() {
		return this.frequency;
	}
	
	public int getPopularity() {
		return this.popularity;
	}
	
	public int getWordCount() {
		return this.wordCount;
	}
	
	private void incrementWordCount() {
		this.wordCount++;
	}
	
	public List<Node> getChildren() {
		List<Node> childList = new ArrayList<>();
		for (Map.Entry<Character, Node> entry : children.entrySet()) {
			childList.add(entry.getValue());
		}
		
		return childList;
	}
	
	public boolean containsWord(char[] word) {
		Node currentNode = this;
		int pointer = 0;
		while (pointer < word.length) {
			if (currentNode.keyExists(word[pointer])) {
				currentNode = currentNode.getChildNode(word[pointer]);
				pointer++;
			} else {
				return false;
			}
		}
		
		if (currentNode.makesAWord()) {
			return true;
		}
		
		return false;
	}
	
	public List<CharPopularityModel> getNextCharsWithPopularity(char[] prefix) {
		List<CharPopularityModel> list = new ArrayList<>();
		Node subrootNode = getPrefixSubroot(prefix);
		
		if (subrootNode != null) {
		    for (Node node : subrootNode.getChildren()) {
			    list.add(new CharPopularityModel(node.getData(), node.getPopularity()));
		    }
		}
		
		return list;
	}
	
	private Node getPrefixSubroot(char[] prefix) {
		Node currentNode = this;
		for (char c: prefix) {
			if (currentNode.keyExists(c)) {
				currentNode = currentNode.getChildNode(c);
			} else {
				currentNode = null;
				break;
			}
		}
		
		return currentNode;
	}
	
	private void incrementPopularity(int frequency) {
		this.popularity += frequency;
	}
	
	private void setFrequency(int frequency) {
		this.frequency = frequency;
	}
	
	private Node addChildNode(char key) {
		if (! children.containsKey(key)) {
			children.put(key, new Node(key));
		}
		
		return children.get(key);
	}
	
	public void insertWord(char[] word, int frequency) {
		if (containsWord(word)) {
			return; //If already contains, just ignore.
		}
		
		insertWord(word, 0, frequency);
	}
	
	private void insertWord(char[] word, int charPointer, int frequency) {
		Node node = addChildNode(word[charPointer]);
		this.incrementWordCount();
		this.incrementPopularity(frequency);
		
		if (charPointer == word.length - 1) {
			node.setMakesAWord(true);
			node.setFrequency(frequency);
		} else {
			node.insertWord(word, charPointer+1, frequency);
		}
	}
	
	private boolean keyExists(char key) {
		return children.containsKey(key);
	}
	
	private Node getChildNode(char key) {
		return children.get(key);
	}
	
	private boolean hasChildren() {
		return (children.size() != 0);
	}
	
	public List<String> findWordsByPrefix(char[] prefix) {
		//find root substring node
		Node subrootNode = getPrefixSubroot(prefix);
		
		if (subrootNode == null || (! subrootNode.hasChildren())) {
			return new ArrayList<String>(); //No words found
		}
		
		return subrootNode.getAllWordsWithPrefix(prefix);
	}
	
	private List<String> getAllWordsWithPrefix(char[] prefix) {
		List<String> words = new ArrayList<>();
		
		for (Map.Entry<Character, Node> entry : children.entrySet()) {
			Node currentNode = entry.getValue();
			if (currentNode.makesAWord()) {
				words.add(String.valueOf(prefix) + currentNode.getData());
			}
			
			if (currentNode.hasChildren()) {
				char[] newPrefix = (String.valueOf(prefix) + currentNode.getData()).toCharArray();
				words.addAll(currentNode.getAllWordsWithPrefix(newPrefix));
			}
		}
		
		return words;
	}
	
	public void print() {
		System.out.print("");
		for (Map.Entry<Character, Node> entry : children.entrySet()) {
			System.out.print(entry.getValue().getData() + "    ");
			entry.getValue().print();
		}
	}
}
