package ie.tudublin;

import java.util.ArrayList;
import java.util.Hashtable;

import processing.core.PApplet;

public class ScoreDisplay extends PApplet
{
	ArrayList<Note> notes = new ArrayList<Note>();
	String allNotes = "DEFGABcd";

	// String score = "DEFGABcd";
	// String score = "D2E2F2G2A2B2c2d2";
	String score = "DEF2F2F2EFA2A2B2AFD2E2D2D2D2";

	public void loadScore() {
		for(int i = 0; i < score.length(); i++) {
			int duration = 1;
			if( i < score.length()-1) {
				if(Character.isDigit(score.charAt(i + 1))) {
					duration = score.charAt(i + 1) - '0';
				} 
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
		colorMode(HSB);
		loadScore();
		printScores();

	}

	public void draw()
	{
		background(255);
		drawLines();	
		drawNotes();	

	}

	private void drawLines() {
		float border = width * 0.1f;
		stroke(0);
		strokeWeight(2);
		for(int i = 0; i < 6 ; i++) {
			float y = map (i, 0, 6, height/2 - 50,  height/2 + 50);
			line(border, y, width-border, y);
		}
	}

	void drawNotes()
	{
		fill(0);
		textAlign(CENTER, CENTER);
		textSize(16);
		float border = width * 0.1f;
		int i = 0;
		for(Note n : notes) {
			// write the letter
			char character = n.getNote();
			float x = map(i, 0, notes.size(), border+10, width-border);
			text(n.getNote(), x, height/2 - 120);
			
			// equally spaced
			int pos = allNotes.indexOf(character);
			float gap = (float)12.3 * pos;

			// draw the circles
			noStroke();
			circle(x, (height/2+45) - gap, 20);
			stroke(0);

			
			line(x+5, (height/2+45) - gap, x+5,((height/2+45) - gap) - 50);

			// crotchet (2) as no tick
			if(n.getDuration() == 1) {
				line( x+5, ((height/2+45) - gap) - 50, x+15, ((height/2+45) - gap) -40);
			}
		
			
			i++;
			
		}
	}

}
