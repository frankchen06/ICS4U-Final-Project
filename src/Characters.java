/*===================================================================
Program Name: Characters.java (Backend Code)
Author: William Zhang, Frank Chen, Juliana Zhu
Date: January 13th, 2024
Programming Language & Version Number: Java - Neon.la Release (4.6.1)
=====================================================================
Problem Definition: 
-> Allowing the user and AI to take turns asking differential yes or no questions to isolate a hidden character on a 6x4 grid. The first
   to guess the other's character wins.
 

Input - User prompted for various actions in the program
-> User can choose between making the first move or allowing the computer to move first has 24 objects created of characters to guess from

Output - User is displayed various panels and pop-ups in the program
-> Objects are involved for the output of the GameBoard class
-> Users are outputted of the remaining 24 objects lef to guess
-> Various popups of the game throughout
-

Process - Take the user's input and process to display an output
-> User's question is taken from the input and processed through a method to determine whether the computer's selected character
   possesses this attribute or not
-> Question is eliminated from array list of player questions left to be asked
-> Characters that do not match are eliminated from an array list of characters remaining
-> If the character that the user guesses matches the computer's character then display that user won
===================================================================*/

public class Characters {	
	// Identifiers for character attributes
	private String name;               // Character's name
	private String eyeColour;          // Character's eye color
	private String gender;             // Character's gender
	private String skinTone;           // Character's skin tone
	private String hairColour;         // Character's hair color
	private boolean facialHair;        // Does the character have facial hair
	private boolean glasses;           // Does the character wear glasses
	private boolean teeth;             // Does the character show teeth when smiling
	private boolean hat;               // Does the character wear a hat
	private String hairLength;         // Character's hair length
	private boolean piercings;         // Does the character have piercings

	// Constructor for character objects that will be instantiated as the 24 objects
	public Characters(String selectedName, String typeEyeColour, String typeGender, String typeSkinTone,
			String typeHairColour, boolean hasFacialHair, boolean hasGlasses,
			boolean showsTeeth, boolean hasHat, String typeHairLength, boolean hasPiercings) {
		// Initialize character attributes
		name = selectedName;
		eyeColour = typeEyeColour;
		gender = typeGender;
		skinTone = typeSkinTone;
		hairColour = typeHairColour;
		facialHair = hasFacialHair;
		glasses = hasGlasses;
		teeth = showsTeeth;
		hat = hasHat;
		hairLength = typeHairLength;
		piercings = hasPiercings;
	}
	//getter and setter methods for the attributes 
	public String getName() {
		return name;
	}
	//getter and setter methods for the attributes
	public void setName(String newName) {
		name = newName;
	}
	public String getEyeColour() {
		return eyeColour;
	}
	//getter and setter methods for the attributes
	public void setEyeColour(String newEyeColour) {
		eyeColour = newEyeColour;
	}
	//getter and setter methods for the attributes
	public String getGender() {
		return gender;
	}
	//getter and setter methods for the attributes
	public void setGender(String newGender) {
		gender = newGender;
	}
	//getter and setter methods for the attributes
	public String getSkinTone() {
		return skinTone;
	}
	//getter and setter methods for the attributes
	public void setSkinTone(String newSkinTone) {
		skinTone = newSkinTone;
	}
	//getter and setter methods for the attributes
	public String getHairColour() {
		return hairColour;
	}
	//getter and setter methods for the attributes
	public void setHairColour(String newHairColour) {
		hairColour = newHairColour;
	}
	//getter and setter methods for the attributes
	public boolean getFacialHair() {
		return facialHair;
	}
	//getter and setter methods for the attributes
	public void setFacialHair(boolean newFacialHair) {
		facialHair = newFacialHair;
	}
	//getter and setter methods for the attributes
	public boolean getGlasses() {
		return glasses;
	}
	//getter and setter methods for the attributes
	public void setGlasses(boolean newGlasses) {
		glasses = newGlasses;
	}
	//getter and setter methods for the attributes
	public boolean getTeeth() {
		return teeth;
	}
	//getter and setter methods for the attributes
	public void setTeeth(boolean newTeeth) {
		teeth = newTeeth;
	}
	//getter and setter methods for the attributes
	public boolean getHat() {
		return hat;
	}
	//getter and setter methods for the attributes
	public void setHat(boolean newHat) {
		hat = newHat;
	}
	//getter and setter methods for the attributes
	public String getHairLength() {
		return hairLength;
	}
	//getter and setter methods for the attributes
	public void setHairLength(String newHairLength) {
		hairLength = newHairLength;
	}
	//getter and setter methods for the attributes
	public boolean getPiercings() {
		return piercings;
	}
	//getter and setter methods for the attributes
	public void setPiercings(boolean newPiercings) {
		piercings = newPiercings;
	}
}