package ie.tudublin;

import java.util.ArrayList;

import processing.core.PApplet;
import ddf.minim.Minim;
import ddf.minim.AudioOutput;




public class ScoreDisplay extends PApplet
{
	Minim minim;
	AudioOutput out;
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
		minim = new Minim(this);
		out = minim.getLineOut(); out.setTempo(80);
		colorMode(HSB);
		loadScore();
		printScores();	
		// DEF2F2F2EFA2A2B2AFD2E2D2D2D2
		out.playNote( 2.0f, 2.9f, "D" );
		out.playNote( 2.9f, 3.9f, "E" );
		out.playNote( 3.9f, 4.9f, "F" );
		out.playNote( 4.9f, 5.9f, "F" );
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
		textAlign(CENTER, CENTER);

		textSize(16);
		float border = width * 0.1f;
		int i = 0;
		int size= 20;
		for(Note n : notes) {

			// getting the note 
			char note = n.getNote();
			float x = map(i, 0, notes.size(), border+10, width-border);

			// checking if mouse if hovering on the same x axis as a note
			if(mouseX <= x + size && mouseX >= x - size) {
				fill(255, 255, 255);
				stroke(255, 255, 255);
			} else {
				fill(0);
				stroke(0);
			}

			text(n.getNote(), x, height/2 - 120);

			// equally spaced
			int pos = allNotes.indexOf(note);
			float gap = (float)12.3 * pos;

			// draw the circles and lines
			circle(x, (height/2+45) - gap, size);
			line(x+5, (height/2+45) - gap, x+5,((height/2+45) - gap) - 50);

			// crotchet (2) as no tick
			if(n.getDuration() == 1) {
				line( x+5, ((height/2+45) - gap) - 50, x+15, ((height/2+45) - gap) -40);
			}
			i++;
		}
	}
}
