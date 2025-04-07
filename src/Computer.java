/*===================================================================
Program Name: Computer.java(Backend Code)
Author: William Zhang, Frank Chen, Juliana Zhu
Date: January 13th, 2024
Programming Language & Version Number: Java - Neon.la Release (4.6.1)
=====================================================================
Problem Definition: 
-> Allowing the user and AI to take turns asking differential yes or no questions to isolate a hidden character on a 6x4 grid. The first
   to guess the other's character wins.
-> This file is used to create the computer object which will host all the functionalities of the computer/AI for the game
Input - User prompted for various actions in the program
-> User can choose between making the first move or allowing the computer to move first
-> User can choose between making a guess or asking a question whenever it is the user's turn
-> User can choose to ask one question from a drop-down menu of 16 questions
-> User can choose to guess a character from the drop-down list of characters that have yet to be eliminated from the grid
-> User can choose to return to the home screen

Output - User is displayed various panels and pop-ups in the program
-> User is prompted to choose the game play order
-> User is prompted whenever it is the user's turn to play
-> Grid of 24 characters is displayed
-> Each time the computer responds to a question, the grid is updated and eliminates characters accordingly
-> Each time the user responds to a question, the number of characters remaining on the computer's grid is displayed
-> Drop-down menu of 16 questions is displayed and updates to show user which questions have already been asked
-> Drop-down list of characters is displayed and updates to show only characters that still remain on the grid

Process - Take the user's input and process to display an output
-> User's question is taken from the input and processed through a method to determine whether the computer's selected character
   possesses this attribute or not
-> Question is eliminated from array list of player questions left to be asked
-> Characters that do not match are eliminated from an array list of characters remaining
-> If the character that the user guesses matches the computer's character then display that user won
===================================================================*/
import java.lang.Math;
import java.io.*;
import java.util.ArrayList;

/**
 * The `Computer` class represents a computer player in a guessing game.
 * Extends the `Player` class and adds functionality specific to computer players.
 */
public class Computer extends Player {

    private String diffcultyMode;

    /**
     * Constructor for the `Computer` class.
     *
     * @param intialSelectedCharacter     The initial selected character for the computer player.
     * @param intialCharactersRemaining   The initial list of characters remaining to be guessed.
     * @param intialQuestionsRemaining    The initial list of questions remaining to be asked.
     * @param chosenMode                  The difficulty mode chosen for the computer player.
     */
    public Computer(Characters intialSelectedCharacter, ArrayList<Characters> intialCharactersRemaining,
                    ArrayList<String> intialQuestionsRemaining, String chosenMode) {
        super(intialSelectedCharacter, intialCharactersRemaining, intialQuestionsRemaining);
        diffcultyMode = chosenMode;
    }

    // Getter and setter methods

    /**
     * Gets the difficulty mode of the computer player.
     *
     * @return The difficulty mode of the computer player.
     */
    public String getDiffcultyMode() {
        return diffcultyMode;
    }

    /**
     * Sets the difficulty mode of the computer player.
     *
     * @param newMode The new difficulty mode to set.
     */
    public void setDiffcultyMode(String newMode) {
        diffcultyMode = newMode;
    }

    /**
     * Checks the guess status based on the remaining characters.
     *
     * @param charactersRemaining The list of characters remaining.
     * @return An integer representing the guess status:
     *         - 1 if the computer has found the character and does not need to ask questions on its turn.
     *         - 2 if the computer cannot guess the final character and must use questions.
     *         - 0 if there is an error or an unexpected state.
     */
    public int checkGuessStatus(ArrayList<Characters> charactersRemaining) {
        int computerFinalGuess;
        if (charactersRemaining.size() == 1) {
            computerFinalGuess = 1;
        } else if (charactersRemaining.size() > 1) {
            computerFinalGuess = 2;
        } else {
            computerFinalGuess = 0;
        }
        return computerFinalGuess;
    }

    /**
     * Attempts to guess the hidden character.
     *
     * @param charactersRemaining The list of characters remaining to be guessed.
     * @param hiddenCharacter      The hidden character that the computer is trying to guess.
     * @return `false` if the computer successfully guesses the hidden character, `true` otherwise.
     */
    public boolean computerGuessCharacter(ArrayList<Characters> charactersRemaining, Characters hiddenCharacter) {
        boolean error;
        if (charactersRemaining.get(0) == hiddenCharacter) {
            error = false;
        } else {
            error = true;
        }
        return error;
    }


	//returns questionNum to be used in eleminatecharacters method in other class
    /**
     * Finds the optimal question for the AI based on the remaining characters and questions.
     *
     * @param computerCharactersLeft The list of characters remaining for the computer player.
     * @param questionsLeft          The list of questions remaining to be asked.
     * @param questionIsAskedHard    An array indicating whether each question has been asked in hard mode.
     * @return The index of the optimal question to ask based on the remaining characters and questions.
     */
    public int findOptimalQuestionAI (ArrayList<Characters>computerCharactersLeft,  ArrayList<String> questionsLeft, boolean [] questionIsAskedHard) {
		//first question
		//userTargetCharacter is the users hidden character
		int yesOccurences = 0;
		int noOccurences = 0;
		ArrayList<Integer>optimalDifference = new ArrayList<Integer>();
		ArrayList<Integer>questionNum = new ArrayList<Integer>();
		for (int i = 0 ; i < 16 ; i ++) { // this for loop would go to the size of all questions (change to 19 when updated questions)
			//if question asked already
			questionNum.add(i); // add question
		}
		//testing all 16 question cases
		//first question
        for(int i = 0; i < computerCharactersLeft.size(); i++){
         	if(!computerCharactersLeft.get(i).getEyeColour().equals("Brown")){
         		noOccurences ++;
         		yesOccurences = computerCharactersLeft.size() - noOccurences;
            }
        }
  
		optimalDifference.add(Math.abs(yesOccurences - noOccurences));
		yesOccurences = 0;
		noOccurences = 0;			
		//second question
		for(int i = 0; i < computerCharactersLeft.size(); i++){
         	if(!computerCharactersLeft.get(i).getEyeColour().equals("Blue")){
         		noOccurences ++;
         		yesOccurences = computerCharactersLeft.size() - noOccurences;
            }
        }
  
		optimalDifference.add(Math.abs(yesOccurences - noOccurences));
		yesOccurences = 0;
		noOccurences = 0;
		//third question
		for(int i = 0; i < computerCharactersLeft.size(); i++){
         	if(!computerCharactersLeft.get(i).getGender().equals("Male")){
         		noOccurences ++;
         		yesOccurences = computerCharactersLeft.size() - noOccurences;
            }
        }
		
		optimalDifference.add(Math.abs(yesOccurences - noOccurences));
		yesOccurences = 0;
		noOccurences = 0;
		//fourth question
		for(int i = 0; i < computerCharactersLeft.size(); i++){
         	if(!computerCharactersLeft.get(i).getSkinTone().equals("Light")){
         		noOccurences ++;
         		yesOccurences = computerCharactersLeft.size() - noOccurences;
            }
        }
  
		optimalDifference.add(Math.abs(yesOccurences - noOccurences));
		yesOccurences = 0;
		noOccurences = 0;	
		//fifth question
		for(int i = 0; i < computerCharactersLeft.size(); i++){
         	if(!computerCharactersLeft.get(i).getHairColour().equals("Black")){
         		noOccurences ++;
         		yesOccurences = computerCharactersLeft.size() - noOccurences;
            }
        }
		optimalDifference.add(Math.abs(yesOccurences - noOccurences));
		yesOccurences = 0;
		noOccurences = 0;
		//sixth question
		for(int i = 0; i < computerCharactersLeft.size(); i++){
         	if(!computerCharactersLeft.get(i).getHairColour().equals("Brown")){
         		noOccurences ++;
         		yesOccurences = computerCharactersLeft.size() - noOccurences;
            }
        }
  
		optimalDifference.add(Math.abs(yesOccurences - noOccurences));
		yesOccurences = 0;
		noOccurences = 0;	
		//seventh question
		for(int i = 0; i < computerCharactersLeft.size(); i++){
         	if(!computerCharactersLeft.get(i).getHairColour().equals("Blonde")){
         		noOccurences ++;
         		yesOccurences = computerCharactersLeft.size() - noOccurences;
            }
        }
  
		optimalDifference.add(Math.abs(yesOccurences - noOccurences));
		yesOccurences = 0;
		noOccurences = 0;	
		//eighth question
		for(int i = 0; i < computerCharactersLeft.size(); i++){
         	if(!computerCharactersLeft.get(i).getHairColour().equals("White")){
         		noOccurences ++;
         		yesOccurences = computerCharactersLeft.size() - noOccurences;
            }
        }
  
		optimalDifference.add(Math.abs(yesOccurences - noOccurences));
		yesOccurences = 0;
		noOccurences = 0;	
		//ninth question
		for(int i = 0; i < computerCharactersLeft.size(); i++){
         	if(!computerCharactersLeft.get(i).getFacialHair()){
         		noOccurences ++;
         		yesOccurences = computerCharactersLeft.size() - noOccurences;
            }
        }
  
		optimalDifference.add(Math.abs(yesOccurences - noOccurences));
		yesOccurences = 0;
		noOccurences = 0;	
		//tenth question
		for(int i = 0; i < computerCharactersLeft.size(); i++){
         	if(!computerCharactersLeft.get(i).getGlasses()){
         		noOccurences ++;
         		yesOccurences = computerCharactersLeft.size() - noOccurences;
            }
        }
  
		optimalDifference.add(Math.abs(yesOccurences - noOccurences));
		yesOccurences = 0;
		noOccurences = 0;
		//eleventh question
        for(int i = 0; i < computerCharactersLeft.size(); i++){
         	if(!computerCharactersLeft.get(i).getTeeth()){
         		noOccurences ++;
         		yesOccurences = computerCharactersLeft.size() - noOccurences;
            }
        }
  
		optimalDifference.add(Math.abs(yesOccurences - noOccurences));
		yesOccurences = 0;
		noOccurences = 0;
		//Tweleveth question
        for(int i = 0; i < computerCharactersLeft.size(); i++){
         	if(!computerCharactersLeft.get(i).getHat()){
         		noOccurences ++;
         		yesOccurences = computerCharactersLeft.size() - noOccurences;
            }
        }
  
		optimalDifference.add(Math.abs(yesOccurences - noOccurences));
		yesOccurences = 0;
		noOccurences = 0;
		//Thirtheen question
        for(int i = 0; i < computerCharactersLeft.size(); i++){
         	if(!computerCharactersLeft.get(i).getHairLength().equals("Short")){
         		noOccurences ++;
         		yesOccurences = computerCharactersLeft.size() - noOccurences;
            }
        }
  
		optimalDifference.add(Math.abs(yesOccurences - noOccurences));
		yesOccurences = 0;
		noOccurences = 0;
		//Fourteenth
        for(int i = 0; i < computerCharactersLeft.size(); i++){
         	if(!computerCharactersLeft.get(i).getHairLength().equals("Tied")){
         		noOccurences ++;
         		yesOccurences = computerCharactersLeft.size() - noOccurences;
            }
        }
		optimalDifference.add(Math.abs(yesOccurences - noOccurences));
		yesOccurences = 0;
		noOccurences = 0;
        //fifthteen 
        for(int i = 0; i < computerCharactersLeft.size(); i++){
         	if(!computerCharactersLeft.get(i).getHairLength().equals("Long")){
         		noOccurences ++;
         		yesOccurences = computerCharactersLeft.size() - noOccurences;
            }
        } 
		optimalDifference.add(Math.abs(yesOccurences - noOccurences));
		yesOccurences = 0;
		noOccurences = 0;
		//sixteenth question
		for(int i = 0; i < computerCharactersLeft.size();i++) {
			if(!computerCharactersLeft.get(i).getPiercings()) {
				noOccurences ++;
				yesOccurences = computerCharactersLeft.size() - noOccurences;
			}
		}
		optimalDifference.add(Math.abs(yesOccurences - noOccurences));
		yesOccurences = 0;
		noOccurences = 0;	
		//find max in optimal difference, set to true if not already, then output that as the queston num
		int min = 100;
		int index = -1;
		for(int i = 0; i < optimalDifference.size(); i++) {
			if(optimalDifference.get(i)<min && !questionIsAskedHard[i]) {
				min = optimalDifference.get((i));
				index = i;
			}
		}
		questionIsAskedHard[index] = true;

		for(int i = 0; i < 16; i++) {
			//System.out,println(questionIsAskedHard[i]);
		}
		return questionNum.get(index);
	}
    /**
     * Gets the user's answer to a specific question asked by the computer.
     *
     * @param questionsLeft           The list of questions remaining to be asked.
     * @param questionNum             The index of the question to be asked.
     * @param questionsAskedByComputer The list of questions already asked by the computer.
     * @param answersToQuestions      The list of answers corresponding to the questions asked by the computer.
     * @return The user's answer to the specified question.
     * @throws Exception If there is an error reading the user's input.
     */
	public String getQuestionAskedAnswer (ArrayList<String>questionsLeft, int questionNum, ArrayList<Integer>questionsAskedByComputer,ArrayList<String>answersToQuestions) throws Exception {
		String userAnswer;
		//System.out,println("The computer would like to ask: " + questionsLeft.get(questionNum));
		//System.out,println("Type [y] if this is true or type [n] if this is not true");
		userAnswer = br.readLine();
		questionsAskedByComputer.add(questionNum);
		answersToQuestions.add(userAnswer);
		//System.out,println("I have set:  " + questionsLeft.get(questionNum) + " to already asked");
		questionsLeft.set(questionNum, "Already Asked");

		return userAnswer;
	}
	//can delete computerQuestionsLeft after test cases
	/**
	 * Eliminates characters from the computer's list based on the user's answer to a specific question.
	 *
	 * @param computerCharactersLeft The list of characters remaining for the computer to guess.
	 * @param userAnswer             The user's answer to the computer's question.
	 * @param computerQuestionNum    The index of the question asked by the computer.
	 * @param computerQuestionsLeft  The list of questions remaining for the computer to ask.
	 * @param questionIsAskedHard    An array indicating whether a question has been asked by the computer.
	 */
	public void computerEliminateCharactersHard(ArrayList<Characters> computerCharactersLeft, String userAnswer,
			int computerQuestionNum, ArrayList<String>computerQuestionsLeft, boolean [] questionIsAskedHard) { 
		//start of method
		ArrayList<Integer> removeIndex = new ArrayList<Integer>();
		//System.out,println("The Computer has Chosen: " + computerQuestionsLeft.get(computerQuestionNum) + "(only for testing)");
	    
	    if(computerQuestionNum == 0){ //Is the eye colour brown?
	        if(userAnswer.equalsIgnoreCase("y")){
	        	questionIsAskedHard[0] = questionIsAskedHard[1] = true;
	            for(int i = 0; i < computerCharactersLeft.size(); i++){
	         	   if(!computerCharactersLeft.get(i).getEyeColour().equals("Brown")){
	                        removeIndex.add(i);
	                    }
	            }
	        }
	        else if(userAnswer.equalsIgnoreCase("n")){
	        	questionIsAskedHard[0] = true;
	            for(int i = 0; i < computerCharactersLeft.size(); i++){
	                    if(computerCharactersLeft.get(i).getEyeColour().equals("Brown")){
	                        removeIndex.add(i);
	                    }
	            }
	        }
	    }
	     if(computerQuestionNum == 1){ //Is the eye colour blue?
	        if(userAnswer.equalsIgnoreCase("y")){
	        	questionIsAskedHard[0] = questionIsAskedHard[1] = true;
	            for(int i = 0; i < computerCharactersLeft.size(); i++){
	                    if(!computerCharactersLeft.get(i).getEyeColour().equals("Blue")){
	                        removeIndex.add(i);
	                    }
	            }
	        }
	        else if(userAnswer.equalsIgnoreCase("n")){
	        	questionIsAskedHard[1] = true;
	            for(int i = 0; i < computerCharactersLeft.size(); i++){
	                    if(computerCharactersLeft.get(i).getEyeColour().equals("Blue")){
	                        removeIndex.add(i);
	                    }
	            }
	        }
	     }
	    if(computerQuestionNum== 2){ //
	    	questionIsAskedHard[2] = true;
	        if(userAnswer.equalsIgnoreCase("y")){
	            for(int i = 0; i < computerCharactersLeft.size(); i++){
	                    if(!computerCharactersLeft.get(i).getGender().equals("Male")){
	                        removeIndex.add(i);

	                    }
	            }
	        }
	        else if(userAnswer.equalsIgnoreCase("n")){
	            for(int i = 0; i < computerCharactersLeft.size(); i++){
	                    if(computerCharactersLeft.get(i).getGender().equals("Male")){
	                        removeIndex.add(i);
	                    }
	            }
	        }
	    }
	    if(computerQuestionNum== 3){ //
	    	questionIsAskedHard[3] = true;
	        if(userAnswer.equalsIgnoreCase("y")){
	            for(int i = 0; i < computerCharactersLeft.size(); i++){
	                    if(!computerCharactersLeft.get(i).getSkinTone().equals("Light")){
	                        removeIndex.add(i);
	                    }
	            }
	        }
	        else if(userAnswer.equalsIgnoreCase("n")){
	            for(int i = 0; i < computerCharactersLeft.size(); i++){
	                    if(computerCharactersLeft.get(i).getSkinTone().equals("Light")){
	                        removeIndex.add(i);
	                    }
	            }
	        }
	    }
	    if(computerQuestionNum== 4){ //
	        if(userAnswer.equalsIgnoreCase("y")){
	        	questionIsAskedHard[6] = questionIsAskedHard[5] = questionIsAskedHard[6] = questionIsAskedHard[7] = true;
	            for(int i = 0; i < computerCharactersLeft.size(); i++){
	                    if(!computerCharactersLeft.get(i).getHairColour().equals("Black")){
	                        removeIndex.add(i);
	                    }
	            }
	        }
	        else if(userAnswer.equalsIgnoreCase("n")){
	        	questionIsAskedHard[4] = true;
	            for(int i = 0; i < computerCharactersLeft.size(); i++){
	                    if(computerCharactersLeft.get(i).getHairColour().equals("Black")){
	                        removeIndex.add(i);
	                    }
	            }
	        }
	    }
	    if(computerQuestionNum== 5){ //
	        if(userAnswer.equalsIgnoreCase("y")){
	        	questionIsAskedHard[6] = questionIsAskedHard[5] = questionIsAskedHard[6] = questionIsAskedHard[7] = true;
	            for(int i = 0; i < computerCharactersLeft.size(); i++){
	                    if(!computerCharactersLeft.get(i).getHairColour().equals("Brown")){
	                        removeIndex.add(i);
	                    }
	            }
	        }
	        else if(userAnswer.equalsIgnoreCase("n")){
	        	questionIsAskedHard[5] = true;
	            for(int i = 0; i < computerCharactersLeft.size(); i++){
	                    if(computerCharactersLeft.get(i).getHairColour().equals("Brown")){
	                        removeIndex.add(i);
	                    }
	            }
	        }
	    }
	    if(computerQuestionNum== 6){ //
	        if(userAnswer.equalsIgnoreCase("y")){
	        	questionIsAskedHard[6] = questionIsAskedHard[5] = questionIsAskedHard[6] = questionIsAskedHard[7] = true;
	            for(int i = 0; i < computerCharactersLeft.size(); i++){
	                    if(!computerCharactersLeft.get(i).getHairColour().equals("Blonde")){
	                        removeIndex.add(i);
	                    }
	            }
	        }
	        else if(userAnswer.equalsIgnoreCase("n")){
	        	questionIsAskedHard[6] = true;
	            for(int i = 0; i < computerCharactersLeft.size(); i++){
	                    if(computerCharactersLeft.get(i).getHairColour().equals("Blonde")){
	                        removeIndex.add(i);
	                    }
	            }
	        }
	    }
	    if(computerQuestionNum== 7){ //
	        if(userAnswer.equalsIgnoreCase("y")){
	        	questionIsAskedHard[6] = questionIsAskedHard[5] = questionIsAskedHard[6] = questionIsAskedHard[7] = true;
	            for(int i = 0; i < computerCharactersLeft.size(); i++){
	                    if(!computerCharactersLeft.get(i).getHairColour().equals("White")){
	                        removeIndex.add(i);
	                    }
	            }
	        }
	        else if(userAnswer.equalsIgnoreCase("n")){
	        	questionIsAskedHard[7] = true;
	            for(int i = 0; i < computerCharactersLeft.size(); i++){
	                    if(computerCharactersLeft.get(i).getHairColour().equals("White")){
	                        removeIndex.add(i);
	                    }
	            }
	        }
	    }
	    if(computerQuestionNum== 8){ //
	    	questionIsAskedHard[8] = true;
	        if(userAnswer.equalsIgnoreCase("y")){
	            for(int i = 0; i < computerCharactersLeft.size(); i++){
	                    if(!computerCharactersLeft.get(i).getFacialHair()){
	                        removeIndex.add(i);
	                    }
	            }
	        }
	        else if(userAnswer.equalsIgnoreCase("n")){
	            for(int i = 0; i < computerCharactersLeft.size(); i++){
	                    if(computerCharactersLeft.get(i).getFacialHair()){
	                        removeIndex.add(i);
	                    }
	            }
	        }
	    }
	    if(computerQuestionNum== 9){ //
	    	questionIsAskedHard[9] = true;
	        if(userAnswer.equalsIgnoreCase("y")){
	            for(int i = 0; i < computerCharactersLeft.size(); i++){
	                    if(!computerCharactersLeft.get(i).getGlasses()){
	                        removeIndex.add(i);
	                    }
	            }
	        }
	        else if(userAnswer.equalsIgnoreCase("n")){
	            for(int i = 0; i < computerCharactersLeft.size(); i++){
	                    if(computerCharactersLeft.get(i).getGlasses()){
	                        removeIndex.add(i);
	                    }
	            }
	        }
	    }
	    if(computerQuestionNum== 10){ //
	    	questionIsAskedHard[10] = true;
	        if(userAnswer.equalsIgnoreCase("y")){
	            for(int i = 0; i < computerCharactersLeft.size(); i++){
	                    if(!computerCharactersLeft.get(i).getTeeth()){
	                        removeIndex.add(i);
	                    }
	            }
	        }
	        else if(userAnswer.equalsIgnoreCase("n")){
	            for(int i = 0; i < computerCharactersLeft.size(); i++){
	                    if(computerCharactersLeft.get(i).getTeeth()){
	                        removeIndex.add(i);
	                    }
	            }
	        }
	    }
	    if(computerQuestionNum== 11){ //
	    	questionIsAskedHard[11] = true;
	        if(userAnswer.equalsIgnoreCase("y")){
	            for(int i = 0; i < computerCharactersLeft.size(); i++){
	                    if(!computerCharactersLeft.get(i).getHat()){
	                        removeIndex.add(i);
	                    }
	            }
	        }
	        else if(userAnswer.equalsIgnoreCase("n")){
	            for(int i = 0; i < computerCharactersLeft.size(); i++){
	                    if(computerCharactersLeft.get(i).getHat()){
	                        removeIndex.add(i);
	                    }
	            }
	        }
	    }
	    if(computerQuestionNum== 12){ //
	        if(userAnswer.equalsIgnoreCase("y")){
		    	questionIsAskedHard[12] = questionIsAskedHard[13] = questionIsAskedHard[14] = true;
	            for(int i = 0; i < computerCharactersLeft.size(); i++){
	                    if(!computerCharactersLeft.get(i).getHairLength().equals("Short")){
	                        removeIndex.add(i);
	                    }
	            }
	        }
	        else if(userAnswer.equalsIgnoreCase("n")){
	        	questionIsAskedHard[12] = true;
	            for(int i = 0; i < computerCharactersLeft.size(); i++){
	                    if(computerCharactersLeft.get(i).getHairLength().equals("Short")){
	                        removeIndex.add(i);
	                    }
	            }
	        }
	    }
	    if(computerQuestionNum== 13){ //
	        if(userAnswer.equalsIgnoreCase("y")){
		    	questionIsAskedHard[12] = questionIsAskedHard[13] = questionIsAskedHard[14] = true;
	            for(int i = 0; i < computerCharactersLeft.size(); i++){
	                    if(!computerCharactersLeft.get(i).getHairLength().equals("Tied")){
	                        removeIndex.add(i);
	                    }
	            }
	        }
	        else if(userAnswer.equalsIgnoreCase("n")){
	        	questionIsAskedHard[13] = true;
	            for(int i = 0; i < computerCharactersLeft.size(); i++){
	                    if(computerCharactersLeft.get(i).getHairLength().equals("Tied")){
	                        removeIndex.add(i);
	                    }
	            }
	        }
	    }
	    if(computerQuestionNum== 14){ //
	        if(userAnswer.equalsIgnoreCase("y")){
		    	questionIsAskedHard[12] = questionIsAskedHard[13] = questionIsAskedHard[14] =  true;
	            for(int i = 0; i < computerCharactersLeft.size(); i++){
	                    if(!computerCharactersLeft.get(i).getHairLength().equals("Long")){
	                        removeIndex.add(i);
	                    }
	            }
	        }
	        else if(userAnswer.equalsIgnoreCase("n")){
	        	questionIsAskedHard[14] = true;
	            for(int i = 0; i < computerCharactersLeft.size(); i++){
	                    if(computerCharactersLeft.get(i).getHairLength().equals("Long")){
	                        removeIndex.add(i);
	                    }
	            }
	        }
	    }
	    
	    if(computerQuestionNum== 15){ //
	        if(userAnswer.equalsIgnoreCase("y")){
		    	questionIsAskedHard[15] = true;
	            for(int i = 0; i < computerCharactersLeft.size(); i++){
	                    if(!computerCharactersLeft.get(i).getPiercings()){
	                        removeIndex.add(i);
	                    }
	            }
	        }
	        else if(userAnswer.equalsIgnoreCase("n")){
	        	questionIsAskedHard[15] = true;
	            for(int i = 0; i < computerCharactersLeft.size(); i++){
	                    if(computerCharactersLeft.get(i).getPiercings()){
	                        removeIndex.add(i);
	                    }
	            }
	        }
	    }
	    //bubble sort remove index from highest index to lowest so that we can remove the indexes
	    boolean swapped = true;
		int comparisons = 0 ;
		int temp;
		while (swapped == true) {
			swapped = false;
			for (int j = 0 ; j < removeIndex.size() - comparisons - 1; j ++) {
				if (removeIndex.get(j)<removeIndex.get(j+1)) {
					swapped = true;
					temp = removeIndex.get(j);
					removeIndex.set(j, removeIndex.get(j+1));
					removeIndex.set(j+1, temp);					
				}
			}
			comparisons ++;
		}
		
		//go thru remove index arraylist to remove characters for player
		
		for (int i = 0 ; i < removeIndex.size(); i++) {
			int index = removeIndex.get(i);
			//System.out,println("I am removing this character: " + computerCharactersLeft.get(index));
			computerCharactersLeft.remove(index); //remove characters from computer arraylist
		}
	    //System.out,println("----------------------------------------------------");
		   //System.out,println("The Computer has the Following Characters left to guess.(ONLY SHOWING FOR TESTING CASES)");
	    for(int i = 0; i < computerCharactersLeft.size(); i ++) {
	 	   //System.out,println("[" + (i) + "]" + computerCharactersLeft.get(i).getName());
	    }

	  
	}
	//careful with this method to pass the orginal arraylist of question (containing all 16)
	
	//display that user was correct and validation showed 100% accuracy
	
	public ArrayList<String> validateAnswers (Characters hiddenCharacter, ArrayList<Integer>questionsAskedByComputer,ArrayList<String>answersToQuestions, ArrayList<String>orginalQuestions)throws Exception {
		int count = 0;
		ArrayList<String> validationResults = new ArrayList <String>();
		validationResults.add("GAME RESULTS");
		validationResults.add("-----------------------------------"); 
	        // Append variables to the file
		for (int i = 0 ; i < questionsAskedByComputer.size(); i ++) {
			switch (questionsAskedByComputer.get(i)) {
				case 0:
					if (hiddenCharacter.getEyeColour().equals("Brown")) {
						if (answersToQuestions.get(i).equalsIgnoreCase("n")) {
							validationResults.add("For Question: " + orginalQuestions.get(0));
							validationResults.add("You answered: No to Brown Eyes, but the answer should be Yes");
							validationResults.add("-----------------------------------"); 
							count ++;
						}
					}
					else {
						if (answersToQuestions.get(i).equalsIgnoreCase("y")) {
							validationResults.add("For Question: " + orginalQuestions.get(0));
							validationResults.add("You answered: Yes to Brown Eyes, but the answer should be No");
							validationResults.add("-----------------------------------"); 
							count ++;
						}
					}
					break;
				case 1:
					if (hiddenCharacter.getEyeColour().equals("Blue")) {
						if (answersToQuestions.get(i).equalsIgnoreCase("n")) {
							validationResults.add("For Question: " + orginalQuestions.get(1));
							validationResults.add("You answered: No to Blue Eyes, but the answer should be Yes");
							validationResults.add("-----------------------------------"); 
							count ++;
						}
					}
					else {
						if (answersToQuestions.get(i).equalsIgnoreCase("y")) {
							validationResults.add("For Question: " + orginalQuestions.get(1));
							validationResults.add("You answered: Yes to Blue Eyes, but the answer should be No");
							validationResults.add("-----------------------------------"); 
							count ++;
						}
					}
					break;
				case 2:
					if (hiddenCharacter.getGender().equals("Male")){
						if (answersToQuestions.get(i).equalsIgnoreCase("n")) {
							validationResults.add("For Question: " + orginalQuestions.get(2));
							validationResults.add("You answered: No to Male Character, but the answer should be Yes");
							validationResults.add("-----------------------------------"); 
							count ++;
						}
					}
					else {
						if (answersToQuestions.get(i).equalsIgnoreCase("y")) {
							validationResults.add("For Question: " + orginalQuestions.get(2));
							validationResults.add("You answered: Yes to Male Character, but the answer should be No");
							validationResults.add("-----------------------------------"); 
							count ++;
						}
					}
					break;
				case 3:
					if (hiddenCharacter.getSkinTone().equals("Light")){
						if (answersToQuestions.get(i).equalsIgnoreCase("n")) {
							validationResults.add("For Question: " + orginalQuestions.get(3));
							validationResults.add("You answered: No to Light Skin, but the answer should be Yes");
							validationResults.add("-----------------------------------"); 
							count ++;
						}
					}
					else {
						if (answersToQuestions.get(i).equalsIgnoreCase("y")) {
							validationResults.add("For Question: " + orginalQuestions.get(3));
							validationResults.add("You answered: Yes to Light Skin, but the answer should be No");
							validationResults.add("-----------------------------------"); 
							count ++;
						}
					}
					break;
				case 4:
					if (hiddenCharacter.getHairColour().equals("Black")){
						if (answersToQuestions.get(i).equalsIgnoreCase("n")) {
							validationResults.add("For Question: " + orginalQuestions.get(4));
							validationResults.add("You answered: No to Black Eyes, but the answer should be Yes");
							validationResults.add("-----------------------------------"); 
							count ++;
						}
					}
					else {
						if (answersToQuestions.get(i).equalsIgnoreCase("y")) {
							validationResults.add("For Question: " + orginalQuestions.get(4));
							validationResults.add("You answered: Yes to Black Hair, but the answer should be No");
							validationResults.add("-----------------------------------"); 
							count ++;
						}
					}
					break;
				case 5:
					if (hiddenCharacter.getHairColour().equals("Brown")){
						if (answersToQuestions.get(i).equalsIgnoreCase("n")) {
							validationResults.add("For Question: " + orginalQuestions.get(5));
							validationResults.add("You answered: No to Brown Hair, but the answer should be Yes");
							validationResults.add("-----------------------------------"); 
							count ++;
						}
					}
					else {
						if (answersToQuestions.get(i).equalsIgnoreCase("y")) {
							validationResults.add("For Question: " + orginalQuestions.get(5));
							validationResults.add("You answered: Yes to Brown Hair, but the answer should be No");
							validationResults.add("-----------------------------------"); 
							count ++;
						}
					}
					break;
				case 6:
					if (hiddenCharacter.getHairColour().equals("Blonde")){
						if (answersToQuestions.get(i).equalsIgnoreCase("n")) {
							validationResults.add("For Question: " + orginalQuestions.get(6));
							validationResults.add("You answered: No to Blonde Hair, but the answer should be Yes");
							validationResults.add("-----------------------------------"); 
							count ++;
						}
					}
					else {
						if (answersToQuestions.get(i).equalsIgnoreCase("y")) {
							validationResults.add("For Question: " + orginalQuestions.get(6));
							validationResults.add("You answered: Yes to Blonde Hair, but the answer should be No");
							validationResults.add("-----------------------------------"); 
							count ++;
						}
					}
					break;
				case 7:
					if (hiddenCharacter.getHairColour().equals("White")){
						if (answersToQuestions.get(i).equalsIgnoreCase("n")) {
							validationResults.add("For Question: " + orginalQuestions.get(7));
							validationResults.add("You answered: No to White Hair, but the answer should be Yes");
							validationResults.add("-----------------------------------"); 
							count ++;
						}
					}
					else {
						if (answersToQuestions.get(i).equalsIgnoreCase("y")) {
							validationResults.add("For Question: " + orginalQuestions.get(7));
							validationResults.add("You answered: Yes to White Hair, but the answer should be No");
							validationResults.add("-----------------------------------"); 
							count ++;
						}
					}
					break;
				case 8:
					if (hiddenCharacter.getFacialHair()){
						if (answersToQuestions.get(i).equalsIgnoreCase("n")) {
							validationResults.add("For Question: " + orginalQuestions.get(8));
							validationResults.add("You answered: No to Facial Hair, but the answer should be Yes");
							validationResults.add("-----------------------------------"); 
							count ++;
						}
					}
					else {
						if (answersToQuestions.get(i).equalsIgnoreCase("y")) {
							validationResults.add("For Question: " + orginalQuestions.get(8));
							validationResults.add("You answered: Yes to Facial Hair, but the answer should be No");
							validationResults.add("-----------------------------------"); 
							count ++;
						}
					}
					break;
				case 9:
					if (hiddenCharacter.getGlasses()){
						if (answersToQuestions.get(i).equalsIgnoreCase("n")) {
							validationResults.add("For Question: " + orginalQuestions.get(9));
							validationResults.add("You answered: No to Glasses, but the answer should be Yes");
							validationResults.add("-----------------------------------"); 
							count ++;
						}
					}
					else {
						if (answersToQuestions.get(i).equalsIgnoreCase("y")) {
							validationResults.add("For Question: " + orginalQuestions.get(9));
							validationResults.add("You answered: Yes to Glasses, but the answer should be No");
							validationResults.add("-----------------------------------"); 
							count ++;
						}
					}
					break;
				case 10:
					if (hiddenCharacter.getTeeth()){
						if (answersToQuestions.get(i).equalsIgnoreCase("n")) {
							validationResults.add("For Question: " + orginalQuestions.get(10));
							validationResults.add("You answered: No to Teeth, but the answer should be Yes");
							validationResults.add("-----------------------------------"); 
							count ++;
						}
					}
					else {
						if (answersToQuestions.get(i).equalsIgnoreCase("y")) {
							validationResults.add("For Question: " + orginalQuestions.get(10));
							validationResults.add("You answered: Yes to Teeth, but the answer should be No");
							validationResults.add("-----------------------------------"); 
							count ++;
						}
					}
					break;
				case 11:
					if (hiddenCharacter.getHat()){
						if (answersToQuestions.get(i).equalsIgnoreCase("n")) {
							validationResults.add("For Question: " + orginalQuestions.get(11));
							validationResults.add("You answered: No to Hat, but the answer should be Yes");
							validationResults.add("-----------------------------------"); 
							count ++;
						}
					}
					else {
						if (answersToQuestions.get(i).equalsIgnoreCase("y")) {
							validationResults.add("For Question: " + orginalQuestions.get(11));
							validationResults.add("You answered: Yes to Hat, but the answer should be No");
							validationResults.add("-----------------------------------"); 
							count ++;
						}
					}
					break;
				case 12:
					if (hiddenCharacter.getHairLength().equals("Short")){
						if (answersToQuestions.get(i).equalsIgnoreCase("n")) {
							validationResults.add("For Question: " + orginalQuestions.get(12));
							validationResults.add("You answered: No to Short Hair, but the answer should be Yes");
							validationResults.add("-----------------------------------"); 
							count ++;
						}
					}
					else {
						if (answersToQuestions.get(i).equalsIgnoreCase("y")) {
							validationResults.add("For Question: " + orginalQuestions.get(12));
							validationResults.add("You answered: Yes to Short Hair, but the answer should be No");
							validationResults.add("-----------------------------------"); 
							count ++;
						}
					}
					break;
				case 13:
					if (hiddenCharacter.getHairLength().equals("Tied")){
						if (answersToQuestions.get(i).equalsIgnoreCase("n")) {
							validationResults.add("For Question: " + orginalQuestions.get(13));
							validationResults.add("You answered: No to Tied Hair, but the answer should be Yes");
							validationResults.add("-----------------------------------"); 
							count ++;
						}
					}
					else {
						if (answersToQuestions.get(i).equalsIgnoreCase("y")) {
							validationResults.add("For Question: " + orginalQuestions.get(13));
							validationResults.add("You answered: Yes to Tied Hair, but the answer should be No");
							validationResults.add("-----------------------------------"); 
							count ++;
						}
					}
					break;
				case 14:
					if (hiddenCharacter.getHairLength().equals("Long")){
						if (answersToQuestions.get(i).equalsIgnoreCase("n")) {
							validationResults.add("For Question: " + orginalQuestions.get(14));
							validationResults.add("You answered: No to Long Hair, but the answer should be Yes");
							validationResults.add("-----------------------------------"); 
							count ++;
						}
					}
					else {
						if (answersToQuestions.get(i).equalsIgnoreCase("y")) {
							validationResults.add("For Question: " + orginalQuestions.get(14));
							validationResults.add("You answered: Yes to Long Hair, but the answer should be No");
							validationResults.add("-----------------------------------"); 
							count ++;
						}
					}
					break;
				case 15:
					if (hiddenCharacter.getPiercings()){
						if (answersToQuestions.get(i).equalsIgnoreCase("n")) {
							validationResults.add("For Question: " + orginalQuestions.get(15));
							validationResults.add("You answered: No to Piercings, but the answer should be Yes");
							validationResults.add("-----------------------------------"); 
							count ++;
						}
					}
					else {
						if (answersToQuestions.get(i).equalsIgnoreCase("y")) {
							validationResults.add("For Question: " + orginalQuestions.get(15));
							validationResults.add("You answered: Yes to Piercings, but the answer should be No");
							validationResults.add("-----------------------------------"); 
							count ++;
						}
					}
					break;
				//only for testing purposes
				default:
			}
			
		}
		validationResults.add("Summary of Game Played: You answered " + count + " wrong.");
		validationResults.add("-----------------------------------"); 
		validationResults.add("Finished Validating...");
		return validationResults;
	}
	/**
	 * Generates a random question index for the computer to ask in Normal mode.
	 *
	 * @param computerCharactersRemaining The list of characters remaining for the computer to guess.
	 * @param computerQuestionsLeft       The list of questions remaining for the computer to ask.
	 * @param questionIsAskedNormal       An array indicating whether a question has been asked in Normal mode.
	 * @return The randomly generated question index.
	 */
	public int generateRandomQuestionNormal(ArrayList<Characters> computerCharactersRemaining,
	                                        ArrayList<String> computerQuestionsLeft, boolean[] questionIsAskedNormal) {
	    // Initialize the questionNum variable
	    int questionNum;

	    // Loop until a non-asked question is found
	    while (true) {
	        // Generate a random question index between 0 and 14 (inclusive)
	        questionNum = (int) (Math.random() * 15);

	        // Check if the question has not been asked before
	        if (!questionIsAskedNormal[questionNum]) {
	            // Break the loop if the question is not asked
	            break;
	        }
	    }

	    // Return the randomly generated question index
	    return questionNum;
	}
	//ArrayList<Characters> computerCharactersLeft, String userAnswer,
	//int computerQuestionNum, ArrayList<String>computerQuestionsLeft
	/**
	 * Elimantes characters
	 *
	 * @param computerCharactersRemaining The list of characters remaining for the computer to guess.
	 * @param computerQuestionsLeft       The list of questions remaining for the computer to ask.
	 * @param questionIsAskedNormal       An array indicating whether a question has been asked in Normal mode.
	 * @return The randomly generated question index.
	 */
	public void computerEliminateCharactersNormal(ArrayList<Characters> computerCharactersLeft, String userAnswer,
			int computerQuestionNum, ArrayList<String> computerQuestionsLeft, boolean[] questionIsAskedNormal) {
		// TODO Auto-generated method stub
		ArrayList<Integer> removeIndex = new ArrayList<Integer>();
		//System.out,println("The Computer has Chosen: " + computerQuestionsLeft.get(computerQuestionNum) + "(only for testing)");
	     
	    if(computerQuestionNum == 0){ //Is the eye colour brown?
	        if(userAnswer.equalsIgnoreCase("y")){
	        	questionIsAskedNormal[0] = questionIsAskedNormal[1] = true;
	            for(int i = 0; i < computerCharactersLeft.size(); i++){
	         	   if(!computerCharactersLeft.get(i).getEyeColour().equals("Brown")){
	                        removeIndex.add(i);
	                    }
	            }
	        }
	        else if(userAnswer.equalsIgnoreCase("n")){
	        	questionIsAskedNormal[0] = true;
	            for(int i = 0; i < computerCharactersLeft.size(); i++){
	                    if(computerCharactersLeft.get(i).getEyeColour().equals("Brown")){
	                        removeIndex.add(i);
	                    }
	            }
	        }
	    }
	     if(computerQuestionNum == 1){ //Is the eye colour blue?
	        if(userAnswer.equalsIgnoreCase("y")){
	        	questionIsAskedNormal[0] = questionIsAskedNormal[1] = true;
	            for(int i = 0; i < computerCharactersLeft.size(); i++){
	                    if(!computerCharactersLeft.get(i).getEyeColour().equals("Blue")){
	                        removeIndex.add(i);
	                    }
	            }
	        }
	        else if(userAnswer.equalsIgnoreCase("n")){
	        	questionIsAskedNormal[1] = true;
	            for(int i = 0; i < computerCharactersLeft.size(); i++){
	                    if(computerCharactersLeft.get(i).getEyeColour().equals("Blue")){
	                        removeIndex.add(i);
	                    }
	            }
	        }
	     }
	    if(computerQuestionNum== 2){ //
	    	questionIsAskedNormal[2] = true;
	        if(userAnswer.equalsIgnoreCase("y")){
	            for(int i = 0; i < computerCharactersLeft.size(); i++){
	                    if(!computerCharactersLeft.get(i).getGender().equals("Male")){
	                        removeIndex.add(i);

	                    }
	            }
	        }
	        else if(userAnswer.equalsIgnoreCase("n")){
	            for(int i = 0; i < computerCharactersLeft.size(); i++){
	                    if(computerCharactersLeft.get(i).getGender().equals("Male")){
	                        removeIndex.add(i);
	                    }
	            }
	        }
	    }
	    if(computerQuestionNum== 3){ //
	    	questionIsAskedNormal[3] = true;
	        if(userAnswer.equalsIgnoreCase("y")){
	            for(int i = 0; i < computerCharactersLeft.size(); i++){
	                    if(!computerCharactersLeft.get(i).getSkinTone().equals("Light")){
	                        removeIndex.add(i);
	                    }
	            }
	        }
	        else if(userAnswer.equalsIgnoreCase("n")){
	            for(int i = 0; i < computerCharactersLeft.size(); i++){
	                    if(computerCharactersLeft.get(i).getSkinTone().equals("Light")){
	                        removeIndex.add(i);
	                    }
	            }
	        }
	    }
	    if(computerQuestionNum== 4){ //
	        if(userAnswer.equalsIgnoreCase("y")){
	        	questionIsAskedNormal[6] = questionIsAskedNormal[5] = questionIsAskedNormal[6] = questionIsAskedNormal[7] = true;
	            for(int i = 0; i < computerCharactersLeft.size(); i++){
	                    if(!computerCharactersLeft.get(i).getHairColour().equals("Black")){
	                        removeIndex.add(i);
	                    }
	            }
	        }
	        else if(userAnswer.equalsIgnoreCase("n")){
	        	questionIsAskedNormal[4] = true;
	            for(int i = 0; i < computerCharactersLeft.size(); i++){
	                    if(computerCharactersLeft.get(i).getHairColour().equals("Black")){
	                        removeIndex.add(i);
	                    }
	            }
	        }
	    }
	    if(computerQuestionNum== 5){ //
	        if(userAnswer.equalsIgnoreCase("y")){
	        	questionIsAskedNormal[6] = questionIsAskedNormal[5] = questionIsAskedNormal[6] = questionIsAskedNormal[7] = true;
	            for(int i = 0; i < computerCharactersLeft.size(); i++){
	                    if(!computerCharactersLeft.get(i).getHairColour().equals("Brown")){
	                        removeIndex.add(i);
	                    }
	            }
	        }
	        else if(userAnswer.equalsIgnoreCase("n")){
	        	questionIsAskedNormal[5] = true;
	            for(int i = 0; i < computerCharactersLeft.size(); i++){
	                    if(computerCharactersLeft.get(i).getHairColour().equals("Brown")){
	                        removeIndex.add(i);
	                    }
	            }
	        }
	    }
	    if(computerQuestionNum== 6){ //
	        if(userAnswer.equalsIgnoreCase("y")){
	        	questionIsAskedNormal[6] = questionIsAskedNormal[5] = questionIsAskedNormal[6] = questionIsAskedNormal[7] = true;
	            for(int i = 0; i < computerCharactersLeft.size(); i++){
	                    if(!computerCharactersLeft.get(i).getHairColour().equals("Blonde")){
	                        removeIndex.add(i);
	                    }
	            }
	        }
	        else if(userAnswer.equalsIgnoreCase("n")){
	        	questionIsAskedNormal[6] = true;
	            for(int i = 0; i < computerCharactersLeft.size(); i++){
	                    if(computerCharactersLeft.get(i).getHairColour().equals("Blonde")){
	                        removeIndex.add(i);
	                    }
	            }
	        }
	    }
	    if(computerQuestionNum== 7){ //
	        if(userAnswer.equalsIgnoreCase("y")){
	        	questionIsAskedNormal[6] = questionIsAskedNormal[5] = questionIsAskedNormal[6] = questionIsAskedNormal[7] = true;
	            for(int i = 0; i < computerCharactersLeft.size(); i++){
	                    if(!computerCharactersLeft.get(i).getHairColour().equals("White")){
	                        removeIndex.add(i);
	                    }
	            }
	        }
	        else if(userAnswer.equalsIgnoreCase("n")){
	        	questionIsAskedNormal[7] = true;
	            for(int i = 0; i < computerCharactersLeft.size(); i++){
	                    if(computerCharactersLeft.get(i).getHairColour().equals("White")){
	                        removeIndex.add(i);
	                    }
	            }
	        }
	    }
	    if(computerQuestionNum== 8){ //
	    	questionIsAskedNormal[8] = true;
	        if(userAnswer.equalsIgnoreCase("y")){
	            for(int i = 0; i < computerCharactersLeft.size(); i++){
	                    if(!computerCharactersLeft.get(i).getFacialHair()){
	                        removeIndex.add(i);
	                    }
	            }
	        }
	        else if(userAnswer.equalsIgnoreCase("n")){
	            for(int i = 0; i < computerCharactersLeft.size(); i++){
	                    if(computerCharactersLeft.get(i).getFacialHair()){
	                        removeIndex.add(i);
	                    }
	            }
	        }
	    }
	    if(computerQuestionNum== 9){ //
	    	questionIsAskedNormal[9] = true;
	        if(userAnswer.equalsIgnoreCase("y")){
	            for(int i = 0; i < computerCharactersLeft.size(); i++){
	                    if(!computerCharactersLeft.get(i).getGlasses()){
	                        removeIndex.add(i);
	                    }
	            }
	        }
	        else if(userAnswer.equalsIgnoreCase("n")){
	            for(int i = 0; i < computerCharactersLeft.size(); i++){
	                    if(computerCharactersLeft.get(i).getGlasses()){
	                        removeIndex.add(i);
	                    }
	            }
	        }
	    }
	    if(computerQuestionNum== 10){ //
	    	questionIsAskedNormal[10] = true;
	        if(userAnswer.equalsIgnoreCase("y")){
	            for(int i = 0; i < computerCharactersLeft.size(); i++){
	                    if(!computerCharactersLeft.get(i).getTeeth()){
	                        removeIndex.add(i);
	                    }
	            }
	        }
	        else if(userAnswer.equalsIgnoreCase("n")){
	            for(int i = 0; i < computerCharactersLeft.size(); i++){
	                    if(computerCharactersLeft.get(i).getTeeth()){
	                        removeIndex.add(i);
	                    }
	            }
	        }
	    }
	    if(computerQuestionNum== 11){ //
	    	questionIsAskedNormal[11] = true;
	        if(userAnswer.equalsIgnoreCase("y")){
	            for(int i = 0; i < computerCharactersLeft.size(); i++){
	                    if(!computerCharactersLeft.get(i).getHat()){
	                        removeIndex.add(i);
	                    }
	            }
	        }
	        else if(userAnswer.equalsIgnoreCase("n")){
	            for(int i = 0; i < computerCharactersLeft.size(); i++){
	                    if(computerCharactersLeft.get(i).getHat()){
	                        removeIndex.add(i);
	                    }
	            }
	        }
	    }
	    if(computerQuestionNum== 12){ //
	        if(userAnswer.equalsIgnoreCase("y")){
		    	questionIsAskedNormal[12] = questionIsAskedNormal[13] = questionIsAskedNormal[14] = true;
	            for(int i = 0; i < computerCharactersLeft.size(); i++){
	                    if(!computerCharactersLeft.get(i).getHairLength().equals("Short")){
	                        removeIndex.add(i);
	                    }
	            }
	        }
	        else if(userAnswer.equalsIgnoreCase("n")){
	        	questionIsAskedNormal[12] = true;
	            for(int i = 0; i < computerCharactersLeft.size(); i++){
	                    if(computerCharactersLeft.get(i).getHairLength().equals("Short")){
	                        removeIndex.add(i);
	                    }
	            }
	        }
	    }
	    if(computerQuestionNum== 13){ //
	        if(userAnswer.equalsIgnoreCase("y")){
		    	questionIsAskedNormal[12] = questionIsAskedNormal[13] = questionIsAskedNormal[14] = true;
	            for(int i = 0; i < computerCharactersLeft.size(); i++){
	                    if(!computerCharactersLeft.get(i).getHairLength().equals("Tied")){
	                        removeIndex.add(i);
	                    }
	            }
	        }
	        else if(userAnswer.equalsIgnoreCase("n")){
	        	questionIsAskedNormal[13] = true;
	            for(int i = 0; i < computerCharactersLeft.size(); i++){
	                    if(computerCharactersLeft.get(i).getHairLength().equals("Tied")){
	                        removeIndex.add(i);
	                    }
	            }
	        }
	    }
	    if(computerQuestionNum== 14){ //
	        if(userAnswer.equalsIgnoreCase("y")){
		    	questionIsAskedNormal[12] = questionIsAskedNormal[13] = questionIsAskedNormal[14] =  true;
	            for(int i = 0; i < computerCharactersLeft.size(); i++){
	                    if(!computerCharactersLeft.get(i).getHairLength().equals("Long")){
	                        removeIndex.add(i);
	                    }
	            }
	        }
	        else if(userAnswer.equalsIgnoreCase("n")){
	        	questionIsAskedNormal[14] = true;
	            for(int i = 0; i < computerCharactersLeft.size(); i++){
	                    if(computerCharactersLeft.get(i).getHairLength().equals("Long")){
	                        removeIndex.add(i);
	                    }
	            }
	        }
	    }
	    
	    if(computerQuestionNum== 15){ //
	        if(userAnswer.equalsIgnoreCase("y")){
		    	questionIsAskedNormal[15] = true;
	            for(int i = 0; i < computerCharactersLeft.size(); i++){
	                    if(!computerCharactersLeft.get(i).getPiercings()){
	                        removeIndex.add(i);
	                    }
	            }
	        }
	        else if(userAnswer.equalsIgnoreCase("n")){
	        	questionIsAskedNormal[15] = true;
	            for(int i = 0; i < computerCharactersLeft.size(); i++){
	                    if(computerCharactersLeft.get(i).getPiercings()){
	                        removeIndex.add(i);
	                    }
	            }
	        }
	    }
	    //bubble sort remove index from highest index to lowest so that we can remove the indexes
	    boolean swapped = true;
		int comparisons = 0 ;
		int temp;
		while (swapped == true) {
			swapped = false;
			for (int j = 0 ; j < removeIndex.size() - comparisons - 1; j ++) {
				if (removeIndex.get(j)<removeIndex.get(j+1)) {
					swapped = true;
					temp = removeIndex.get(j);
					removeIndex.set(j, removeIndex.get(j+1));
					removeIndex.set(j+1, temp);					
				}
			}
			comparisons ++;
		}
		
		//go thru remove index arraylist to remove characters for player
		
		for (int i = 0 ; i < removeIndex.size(); i++) {
			
			int index = removeIndex.get(i);
			//System.out,println("I am removing this character: " + computerCharactersLeft.get(index));
			computerCharactersLeft.remove(index); //remove characters from computer arraylist
		}
	    //System.out,println("----------------------------------------------------");
		  //System.out,println("The Computer has the Following Characters left to guess.(ONLY SHOWING FOR TESTING CASES)");
	    for(int i = 0; i < computerCharactersLeft.size(); i ++) {
	 	   //System.out,println("[" + (i) + "]" + computerCharactersLeft.get(i).getName());
	    }
		
	}

	/**
	 * Generates a random question index for the computer to ask in Easy mode.
	 *
	 * @param computerCharactersRemaining The list of characters remaining for the computer to guess.
	 * @param computerQuestionsLeft       The list of questions remaining for the computer to ask.
	 * @param questionIsAskedEasy         An array indicating whether a question has been asked in Easy mode.
	 * @return The randomly generated question index.
	 */
	public int generateRandomQuestionEasy(ArrayList<Characters> computerCharactersRemaining,
	                                       ArrayList<String> computerQuestionsLeft, boolean[] questionIsAskedEasy) {
	    // Initialize the questionNum variable
	    int questionNum;

	    // Loop until a non-asked question is found
	    while (true) {
	        // Generate a random question index between 0 and 14 (inclusive)
	        questionNum = (int) (Math.random() * 15);

	        // Check if the question has not been asked before
	        if (!questionIsAskedEasy[questionNum]) {
	            // Break the loop if the question is not asked
	            break;
	        }
	    }

	    // Return the randomly generated question index
	    return questionNum;
	}
	/**
	 * Eliminates characters based on the user's answer to the current question in Easy mode.
	 *
	 * @param computerCharactersLeft    The list of characters remaining for the computer to guess.
	 * @param userAnswer                The user's answer to the current question ("y" or "n").
	 * @param computerQuestionNum       The index of the current question.
	 * @param computerQuestionsLeft     The list of questions remaining for the computer to ask.
	 * @param questionIsAskedEasy       An array indicating whether a question has been asked in Easy mode.
	 */
	public void computerEliminateCharactersEasy(ArrayList<Characters> computerCharactersLeft, String userAnswer,
			int computerQuestionNum, ArrayList<String> computerQuestionsLeft, boolean[] questionIsAskedEasy) {
		// TODO Auto-generated method stub
		ArrayList<Integer> removeIndex = new ArrayList<Integer>();
		//System.out,println("The Computer has Chosen: " + computerQuestionsLeft.get(computerQuestionNum) + "(only for testing)");
	     
	    if(computerQuestionNum == 0){ //Is the eye colour brown?
        	questionIsAskedEasy[0] = true;
	        if(userAnswer.equalsIgnoreCase("y")){
	            for(int i = 0; i < computerCharactersLeft.size(); i++){
	         	   if(!computerCharactersLeft.get(i).getEyeColour().equals("Brown")){
	                        removeIndex.add(i);
	                    }
	            }
	        }
	        else if(userAnswer.equalsIgnoreCase("n")){
	            for(int i = 0; i < computerCharactersLeft.size(); i++){
	                    if(computerCharactersLeft.get(i).getEyeColour().equals("Brown")){
	                        removeIndex.add(i);
	                    }
	            }
	        }
	    }
	     if(computerQuestionNum == 1){ //Is the eye colour blue?
	    	 questionIsAskedEasy[1] = true;
	        if(userAnswer.equalsIgnoreCase("y")){
	            for(int i = 0; i < computerCharactersLeft.size(); i++){
	                    if(!computerCharactersLeft.get(i).getEyeColour().equals("Blue")){
	                        removeIndex.add(i);
	                    }
	            }
	        }
	        else if(userAnswer.equalsIgnoreCase("n")){
	            for(int i = 0; i < computerCharactersLeft.size(); i++){
	                    if(computerCharactersLeft.get(i).getEyeColour().equals("Blue")){
	                        removeIndex.add(i);
	                    }
	            }
	        }
	     }
	    if(computerQuestionNum== 2){ //
	    	questionIsAskedEasy[2] = true;
	        if(userAnswer.equalsIgnoreCase("y")){
	            for(int i = 0; i < computerCharactersLeft.size(); i++){
	                    if(!computerCharactersLeft.get(i).getGender().equals("Male")){
	                        removeIndex.add(i);

	                    }
	            }
	        }
	        else if(userAnswer.equalsIgnoreCase("n")){
	            for(int i = 0; i < computerCharactersLeft.size(); i++){
	                    if(computerCharactersLeft.get(i).getGender().equals("Male")){
	                        removeIndex.add(i);
	                    }
	            }
	        }
	    }
	    if(computerQuestionNum== 3){ //
	    	questionIsAskedEasy[3] = true;
	        if(userAnswer.equalsIgnoreCase("y")){
	            for(int i = 0; i < computerCharactersLeft.size(); i++){
	                    if(!computerCharactersLeft.get(i).getSkinTone().equals("Light")){
	                        removeIndex.add(i);
	                    }
	            }
	        }
	        else if(userAnswer.equalsIgnoreCase("n")){
	            for(int i = 0; i < computerCharactersLeft.size(); i++){
	                    if(computerCharactersLeft.get(i).getSkinTone().equals("Light")){
	                        removeIndex.add(i);
	                    }
	            }
	        }
	    }
	    if(computerQuestionNum== 4){ //
	    	questionIsAskedEasy[4] = true;
	        if(userAnswer.equalsIgnoreCase("y")){
	            for(int i = 0; i < computerCharactersLeft.size(); i++){
	                    if(!computerCharactersLeft.get(i).getHairColour().equals("Black")){
	                        removeIndex.add(i);
	                    }
	            }
	        }
	        else if(userAnswer.equalsIgnoreCase("n")){
	            for(int i = 0; i < computerCharactersLeft.size(); i++){
	                    if(computerCharactersLeft.get(i).getHairColour().equals("Black")){
	                        removeIndex.add(i);
	                    }
	            }
	        }
	    }
	    if(computerQuestionNum== 5){ //
	    	questionIsAskedEasy[5] = true;
	        if(userAnswer.equalsIgnoreCase("y")){
	            for(int i = 0; i < computerCharactersLeft.size(); i++){
	                    if(!computerCharactersLeft.get(i).getHairColour().equals("Brown")){
	                        removeIndex.add(i);
	                    }
	            }
	        }
	        else if(userAnswer.equalsIgnoreCase("n")){
	            for(int i = 0; i < computerCharactersLeft.size(); i++){
	                    if(computerCharactersLeft.get(i).getHairColour().equals("Brown")){
	                        removeIndex.add(i);
	                    }
	            }
	        }
	    }
	    if(computerQuestionNum== 6){ //
	    	questionIsAskedEasy[6] = true;
	        if(userAnswer.equalsIgnoreCase("y")){
	            for(int i = 0; i < computerCharactersLeft.size(); i++){
	                    if(!computerCharactersLeft.get(i).getHairColour().equals("Blonde")){
	                        removeIndex.add(i);
	                    }
	            }
	        }
	        else if(userAnswer.equalsIgnoreCase("n")){
	            for(int i = 0; i < computerCharactersLeft.size(); i++){
	                    if(computerCharactersLeft.get(i).getHairColour().equals("Blonde")){
	                        removeIndex.add(i);
	                    }
	            }
	        }
	    }
	    if(computerQuestionNum== 7){ //
	    	questionIsAskedEasy[7] = true;
	        if(userAnswer.equalsIgnoreCase("y")){
	            for(int i = 0; i < computerCharactersLeft.size(); i++){
	                    if(!computerCharactersLeft.get(i).getHairColour().equals("White")){
	                        removeIndex.add(i);
	                    }
	            }
	        }
	        else if(userAnswer.equalsIgnoreCase("n")){
	            for(int i = 0; i < computerCharactersLeft.size(); i++){
	                    if(computerCharactersLeft.get(i).getHairColour().equals("White")){
	                        removeIndex.add(i);
	                    }
	            }
	        }
	    }
	    if(computerQuestionNum== 8){ //
	    	questionIsAskedEasy[8] = true;
	        if(userAnswer.equalsIgnoreCase("y")){
	            for(int i = 0; i < computerCharactersLeft.size(); i++){
	                    if(!computerCharactersLeft.get(i).getFacialHair()){
	                        removeIndex.add(i);
	                    }
	            }
	        }
	        else if(userAnswer.equalsIgnoreCase("n")){
	            for(int i = 0; i < computerCharactersLeft.size(); i++){
	                    if(computerCharactersLeft.get(i).getFacialHair()){
	                        removeIndex.add(i);
	                    }
	            }
	        }
	    }
	    if(computerQuestionNum== 9){ //
	    	questionIsAskedEasy[9] = true;
	        if(userAnswer.equalsIgnoreCase("y")){
	            for(int i = 0; i < computerCharactersLeft.size(); i++){
	                    if(!computerCharactersLeft.get(i).getGlasses()){
	                        removeIndex.add(i);
	                    }
	            }
	        }
	        else if(userAnswer.equalsIgnoreCase("n")){
	            for(int i = 0; i < computerCharactersLeft.size(); i++){
	                    if(computerCharactersLeft.get(i).getGlasses()){
	                        removeIndex.add(i);
	                    }
	            }
	        }
	    }
	    if(computerQuestionNum== 10){ //
	    	questionIsAskedEasy[10] = true;
	        if(userAnswer.equalsIgnoreCase("y")){
	            for(int i = 0; i < computerCharactersLeft.size(); i++){
	                    if(!computerCharactersLeft.get(i).getTeeth()){
	                        removeIndex.add(i);
	                    }
	            }
	        }
	        else if(userAnswer.equalsIgnoreCase("n")){
	            for(int i = 0; i < computerCharactersLeft.size(); i++){
	                    if(computerCharactersLeft.get(i).getTeeth()){
	                        removeIndex.add(i);
	                    }
	            }
	        }
	    }
	    if(computerQuestionNum== 11){ //
	    	questionIsAskedEasy[11] = true;
	        if(userAnswer.equalsIgnoreCase("y")){
	            for(int i = 0; i < computerCharactersLeft.size(); i++){
	                    if(!computerCharactersLeft.get(i).getHat()){
	                        removeIndex.add(i);
	                    }
	            }
	        }
	        else if(userAnswer.equalsIgnoreCase("n")){
	            for(int i = 0; i < computerCharactersLeft.size(); i++){
	                    if(computerCharactersLeft.get(i).getHat()){
	                        removeIndex.add(i);
	                    }
	            }
	        }
	    }
	    if(computerQuestionNum== 12){ //
	    	questionIsAskedEasy[12] = true;
	        if(userAnswer.equalsIgnoreCase("y")){
	            for(int i = 0; i < computerCharactersLeft.size(); i++){
	                    if(!computerCharactersLeft.get(i).getHairLength().equals("Short")){
	                        removeIndex.add(i);
	                    }
	            }
	        }
	        else if(userAnswer.equalsIgnoreCase("n")){
	            for(int i = 0; i < computerCharactersLeft.size(); i++){
	                    if(computerCharactersLeft.get(i).getHairLength().equals("Short")){
	                        removeIndex.add(i);
	                    }
	            }
	        }
	    }
	    if(computerQuestionNum== 13){ //
	    	questionIsAskedEasy[13] = true;
	        if(userAnswer.equalsIgnoreCase("y")){
	            for(int i = 0; i < computerCharactersLeft.size(); i++){
	                    if(!computerCharactersLeft.get(i).getHairLength().equals("Tied")){
	                        removeIndex.add(i);
	                    }
	            }
	        }
	        else if(userAnswer.equalsIgnoreCase("n")){
	            for(int i = 0; i < computerCharactersLeft.size(); i++){
	                    if(computerCharactersLeft.get(i).getHairLength().equals("Tied")){
	                        removeIndex.add(i);
	                    }
	            }
	        }
	    }
	    if(computerQuestionNum== 14){ //
	    	questionIsAskedEasy[14] = true;
	        if(userAnswer.equalsIgnoreCase("y")){
	            for(int i = 0; i < computerCharactersLeft.size(); i++){
	                    if(!computerCharactersLeft.get(i).getHairLength().equals("Long")){
	                        removeIndex.add(i);
	                    }
	            }
	        }
	        else if(userAnswer.equalsIgnoreCase("n")){
	            for(int i = 0; i < computerCharactersLeft.size(); i++){
	                    if(computerCharactersLeft.get(i).getHairLength().equals("Long")){
	                        removeIndex.add(i);
	                    }
	            }
	        }
	    }
	    
	    if(computerQuestionNum== 15){ //
	    	questionIsAskedEasy[15] = true;
	        if(userAnswer.equalsIgnoreCase("y")){
	            for(int i = 0; i < computerCharactersLeft.size(); i++){
	                    if(!computerCharactersLeft.get(i).getPiercings()){
	                        removeIndex.add(i);
	                    }
	            }
	        }
	        else if(userAnswer.equalsIgnoreCase("n")){
	            for(int i = 0; i < computerCharactersLeft.size(); i++){
	                    if(computerCharactersLeft.get(i).getPiercings()){
	                        removeIndex.add(i);
	                    }
	            }
	        }
	    }
	    //bubble sort remove index from highest index to lowest so that we can remove the indexes
	    boolean swapped = true;
		int comparisons = 0 ;
		int temp;
		while (swapped == true) {
			swapped = false;
			for (int j = 0 ; j < removeIndex.size() - comparisons - 1; j ++) {
				if (removeIndex.get(j)<removeIndex.get(j+1)) {
					swapped = true;
					temp = removeIndex.get(j);
					removeIndex.set(j, removeIndex.get(j+1));
					removeIndex.set(j+1, temp);					
				}
			}
			comparisons ++;
		}
		
		//go thru remove index arraylist to remove characters for player
		
		for (int i = 0 ; i < removeIndex.size(); i++) {
			//System.out,println(" I AM REMOVING CHARACTERS RIGHT NOW");
			int index = removeIndex.get(i);
			//System.out,println("I am removing this character: " + computerCharactersLeft.get(index));
			computerCharactersLeft.remove(index); //remove characters from computer arraylist
		}
	    //System.out,println("----------------------------------------------------");
		   //System.out,println("The Computer has the Following Characters left to guess.(ONLY SHOWING FOR TESTING CASES)");
	    for(int i = 0; i < computerCharactersLeft.size(); i ++) {
	 	   //System.out,println("[" + (i) + "]" + computerCharactersLeft.get(i).getName());
	    }
	}//end of method
}//end of class