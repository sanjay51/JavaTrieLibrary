package javaTrieLibrary;

public class Test {

	public static void main(String[] args) {
		Trie trie = new Trie();
		
		trie.insertWord("Sanjay");
		trie.insertWord("Shalgam");
		trie.insertWord("Apple");
		trie.insertWord("abdo");
		trie.insertWord("abdoman");
		trie.insertWord("sanjay1");
		buildLibrary(trie);
		System.out.println(trie.getWordsStartingWith("x").toString());
	}
	
	private static void buildLibrary(Trie trie) {
	}
}
