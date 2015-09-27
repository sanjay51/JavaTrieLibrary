package net.sanjav.javaTrieLibrary;

import java.util.Comparator;
import java.util.List;

import net.sanjav.javaTrieLibrary.unittests.CharPopularityModel;

public class Trie {
	Node rootNode;
	
	public Trie() {
		rootNode = new Node('0');
		rootNode.setRoot();
	}
	
	public void insertWord(String word, int frequency) {
		rootNode.insertWord(word.toLowerCase().toCharArray(), frequency);
	}
	
	public List<String> getWordsStartingWith(String prefix) {
		return rootNode.findWordsByPrefix(prefix.toCharArray());
	}
	
	public boolean containsWord(String word) {
		return rootNode.containsWord(word.toLowerCase().toCharArray());
	}
	
	public List<CharPopularityModel> getNextPopularCharacters(String prefix) {
		List<CharPopularityModel> fullList = rootNode.getNextCharsWithPopularity(prefix.toCharArray());
		
		fullList.sort(new Comparator<CharPopularityModel>() {
			@Override
			public int compare(CharPopularityModel char1, CharPopularityModel char2) {
				// TODO Auto-generated method stub
				return char1.getPopularity() > char2.getPopularity() ? -1 : 1;
			}
		});
		
		int sum = 0;
		for (CharPopularityModel model : fullList)
			sum += model.getPopularity();

		for (CharPopularityModel model : fullList)
			model.setTotalPopularity(sum);
		
		return fullList;
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
