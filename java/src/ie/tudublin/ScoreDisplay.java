package ie.tudublin;

import java.util.ArrayList;


import processing.core.PApplet;

public class ScoreDisplay extends PApplet
{
	ArrayList<Note> notes = new ArrayList<Note>();

	// String score = "DEFGABcd";
	// String score = "D2E2F2G2A2B2c2d2";
	String score = "DEF2F2F2EFA2A2B2AFD2E2D2D2D2";

	public void loadScore() {
		for(int i = 0; i < score.length()-1; i++) {
			int duration = 1;
			if(Character.isDigit(score.charAt(i + 1))) {
				duration = score.charAt(i + 1) - '0';
				
			} 
			if(!Character.isDigit(score.charAt(i))) {
				Note note = new Note(score.charAt(i), duration);
				notes.add(note);
			}

		}
	}

	public void printScores() {
		for(Note n : notes) {
			if(n.getDuration() == 1) {
				println(n.getNote() + "\t" + n.getDuration() + " \t" + "Quaver");
			} else {
				println(n.getNote() + "\t" + n.getDuration() + " \t" + "Crotchet");
			}
		}
	}

	
	public void settings()
	{
		size(1000, 500);

		// How to convert a character to a number
		char c = '7'; // c holds the character 7 (55)
		int i = c - '0'; // i holds the number 7 (55 - 48) 
		println(i);
	}

	public void setup() 
	{
		loadScore();
		printScores();
	}

	public void draw()
	{
		background(255);
		
	}

	void drawNotes()
	{
	}
}
