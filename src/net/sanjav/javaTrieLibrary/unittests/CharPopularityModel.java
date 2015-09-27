package net.sanjav.javaTrieLibrary.unittests;

public class CharPopularityModel {
	char character;
	int popularity;
	int totalPopularity = 0;
	
	public CharPopularityModel(char character, int popularity) {
		this.character = character;
		this.popularity = popularity;
	}

	public char getCharacter() {
		return character;
	}

	public int getPopularity() {
		return popularity;
	}
	
	public void setTotalPopularity(int total) {
		this.totalPopularity = total;
	}
	
	public float getRelativePopularity() {
		if (totalPopularity == 0) return 100;
		
		return (((float) popularity)/totalPopularity) * 100;
	}
}
