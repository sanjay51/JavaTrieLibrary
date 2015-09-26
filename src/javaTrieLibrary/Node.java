package javaTrieLibrary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Node {
	Node parent;
	char data;
	int wordCount;
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
	
	private Node addChildNode(char key) {
		if (! children.containsKey(key)) {
			children.put(key, new Node(key));
		}
		
		return children.get(key);
	}
	
	public void insertWord(char[] word) {
		if (containsWord(word)) {
			return; //If already contains, just ignore.
		}
		
		insertWord(word, 0);
	}
	
	private void insertWord(char[] word, int charPointer) {
		Node node = addChildNode(word[charPointer]);
		this.incrementWordCount();
		
		if (charPointer == word.length - 1) {
			node.setMakesAWord(true);
		} else {
			node.insertWord(word, charPointer+1);
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
		Node currentNode = this;
		for (char c: prefix) {
			if (currentNode.keyExists(c)) {
				currentNode = currentNode.getChildNode(c);
			} else {
				currentNode = null;
				break;
			}
		}
		
		Node subrootNode = currentNode;
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
