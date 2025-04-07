/*===================================================================
Program Name: Player.java(Backend Code)
Author: William Zhang, Frank Chen, Juliana Zhu
Date: January 13th, 2024
Programming Language & Version Number: Java - Neon.la Release (4.6.1)
=====================================================================
Problem Definition: 
-> Allowing the user and AI to take turns asking differential yes or no questions to isolate a hidden character on a 6x4 grid. The first
   to guess the other's character wins.
-> This file is used to create the player object which will host all the functionalities of the player for the game
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
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Player class represents a participant in a guessing game.
 * It manages the player's score, selected character, remaining characters,
 * remaining questions, and player name.
 */
public class Player {
    protected static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    protected int score;
    protected Characters selectedCharacter;
    protected ArrayList<Characters> charactersRemaining;
    protected ArrayList<String> questionsRemaining;
    protected String playerName;

    /**
     * Constructor for the Player class.
     *
     * @param playerName               The name of the player.
     * @param givenScore               The initial score of the player.
     * @param initialCharactersRemaining The initial list of characters remaining for the player.
     * @param initialQuestionsRemaining The initial list of questions remaining for the player.
     */
    public Player(String playerName, int givenScore,
                  ArrayList<Characters> initialCharactersRemaining, ArrayList<String> initialQuestionsRemaining) {

        score = givenScore;
        charactersRemaining = initialCharactersRemaining;
        questionsRemaining = initialQuestionsRemaining;
    }

    /**
     * Constructor for the computer class because it does not need a score or player name.
     *
     * @param initialSelectedCharacter  The initial selected character for the computer player.
     * @param initialCharactersRemaining The initial list of characters remaining for the computer player.
     * @param initialQuestionsRemaining The initial list of questions remaining for the computer player.
     */
    public Player(Characters initialSelectedCharacter, ArrayList<Characters> initialCharactersRemaining, ArrayList<String> initialQuestionsRemaining) {
        selectedCharacter = initialSelectedCharacter;
        charactersRemaining = initialCharactersRemaining;
        questionsRemaining = initialQuestionsRemaining;
    }

    /**
     * Gets the current score of the player.
     *
     * @return The current score of the player.
     */
    public int getScore() {
        return score;
    }

    /**
     * Sets the score of the player based on the number of guesses, user name, correctness of guess,
     * and timing information.
     *
     * @param numOfGuess     The number of guesses made by the player.
     * @param userName       The name of the player.
     * @param isCorrectGuess Indicates whether the guess was correct.
     * @param names           An array of names.
     * @param scoreArray      An array containing scores.
     * @param netTime         The time taken for the guesses.
     */
    public void setScore(int numOfGuess, String userName, boolean isCorrectGuess, String [] names, double []scoreArray, long netTime) {
        double timeInSeconds = (double)netTime/1000000000;
        // Score calculation and related information goes here
        score = 1909090909;
        //System.out.println("Hi " + userName + "!" + "\nYou took " + numOfGuess + " guess this game");
        //if(isCorrectGuess)//System.out.println("You successfully guess your character with a time of " + timeInSeconds + " seconds" );
        //else //System.out.println("You did not successfully guess your character \nYou took a time of " + timeInSeconds + " seconds" );
        //System.out.println("Your total score for this game is: " + score);
        //System.out.println("Thank you for playing!!\n\n\n\n\n");
    }

    /**
     * Gets the selected character of the player.
     *
     * @return The selected character of the player.
     */
    public Characters getSelectedCharacter() {
        return selectedCharacter;
    }

    /**
     * Sets the selected character of the player.
     *
     * @param newSelectedCharacter The new selected character for the player.
     */
    public void setSelectedCharacter(Characters newSelectedCharacter) {
        selectedCharacter = newSelectedCharacter;
    }

    /**
     * Gets the list of characters remaining for the player.
     *
     * @return The list of characters remaining for the player.
     */
    public ArrayList<Characters> getCharactersRemaining() {
        return charactersRemaining;
    }

    /**
     * Sets the list of characters remaining for the player.
     *
     * @param newCharactersRemaining The new list of characters remaining for the player.
     */
    public void setCharactersRemaining(ArrayList<Characters> newCharactersRemaining) {
        charactersRemaining = newCharactersRemaining;
    }

    /**
     * Gets the list of questions remaining for the player.
     *
     * @return The list of questions remaining for the player.
     */
    public ArrayList<String> getQuestionsRemaining() {
        return questionsRemaining;
    }

    /**
     * Sets the list of questions remaining for the player.
     *
     * @param newQuestionsRemaining The new list of questions remaining for the player.
     */
    public void setQuestionsRemaining(ArrayList<String> newQuestionsRemaining) {
        questionsRemaining = newQuestionsRemaining;
    }

    /**
     * Gets the player's name.
     *
     * @return The player's name.
     */
    public String getPlayerName() {
        return playerName;
    }

    /**
     * Sets the player's name.
     *
     * @param newPlayerName The new player's name.
     */
    public void setPlayerName(String newPlayerName) {
        playerName = newPlayerName;
    }

    /**
     * Refills the questions for the player.
     *
     * @param totalQuestions The total list of questions available in the game.
     */
    public void refillQuestions(ArrayList<String> totalQuestions) {
        questionsRemaining.clear();
        for (int i = 0; i < totalQuestions.size(); i++) {
            questionsRemaining.add(totalQuestions.get(i));
        }
    }

    /**
     * Refills the characters for the player.
     *
     * @param totalCharacters The total list of characters available in the game.
     */
    public void refillCharacters(ArrayList<Characters> totalCharacters) {
        charactersRemaining.clear();
        for (int i = 0; i < totalCharacters.size(); i++) {
            totalCharacters.add(totalCharacters.get(i));
        }
    }

    // ... (Other methods)

    /**
     * Creates a player name by taking input from the user.
     *
     * @return The player's name.
     * @throws Exception If an error occurs during input.
     */
    public String createPlayerName() throws Exception {
        String name;
        //System.out.println("What is your name (Game Purposes): ");
        name = br.readLine();
        return name;
    }
    public boolean guessCharacter(ArrayList<Characters> userCharacters, Characters targetSelectedCharacter, int currNumGuesses) throws IOException {
        int guess = -1;
        //System.out.println("You have decided to guess the other player's character...");
        //System.out.println("Here is a list of possible characters to guess from: ");
        for (int i = 0 ; i < userCharacters.size(); i ++) {
            //System.out.println("[" + i + "]" + userCharacters.get(i).getName());
        }
        //System.out.println("");
        //System.out.println("Type the number you would like to guess: ");
        guess = Integer.parseInt(br.readLine());
        if (userCharacters.get(guess) == targetSelectedCharacter) {
            //System.out.println("Congrats! You have won the game!");
            //System.out.println("The opponents character was: " + targetSelectedCharacter.getName());
            return true;
        }
        else if(userCharacters.get(guess) != targetSelectedCharacter && currNumGuesses != 0){
            //System.out.println("Sorry, but you didn't guess the correct character. You still have " + currNumGuesses + " guess[es] left");
            return false;
        }
        else {
            //System.out.println("Sorry, but you have lost the game.");
            //System.out.println("The opponents hidden character was: " + targetSelectedCharacter.getName());
            return false;
        }
    }

    // ... (Other methods)

    /**
     * Prints the questions left for the player to ask the computer.
     *
     * @param userQuestions The list of questions available for the player.
     */
    public void printQuestionsLeft(ArrayList<String> userQuestions) {
        //System.out.println("These are the questions to ask the computer... ");
        //System.out.println("----------------------------------------------------");
        for (int i = 0; i < userQuestions.size(); i ++) {
            //System.out.println("[" + (i) + "]" + userQuestions.get(i));
        }
    }

    /**
     * Asks the computer a question based on the user's selection.
     *
     * @param userQuestions The list of questions available for the player.
     * @return The index of the selected question.
     * @throws IOException If an error occurs during input.
     */
    public int askQuestions(ArrayList<String> userQuestions) throws IOException {
        int valid = 0;
        int userQuestionNum = -1;
        while (valid == 0) {
            try {
                //System.out.println("Please select a question to ask: ");
                userQuestionNum = Integer.parseInt(br.readLine());
                if (userQuestions.get(userQuestionNum).equalsIgnoreCase("Already Asked")) {
                    //System.out.println("You asked that question already! Try another question!");
                } else {
                    valid = 1;
                }

            } catch (Exception e) {
                //System.out.println("Invalid output!");
            }
        }
        return userQuestionNum;
    }

    /**
     * Determines who goes first in the game.
     *
     * @return The order of play. 1 for Computer Goes First, 2 for Player Goes First.
     * @throws Exception If an error occurs during input.
     */
    public int determineWhoGoesFirst() throws Exception {
        int order;
        //System.out.println("[1]Computer Goes First");
        //System.out.println("[2]Player Goes First");
        order = Integer.parseInt(br.readLine());
        return order;
    }
    /**
     * Eliminates characters from the user's character list based on the computer's response to a question.
     *
     * @param userCharacters        The list of characters available for the player.
     * @param targetSelectedCharacters The character the computer has selected.
     * @param userQuestionNum       The index of the question asked by the player.
     * @param userQuestions         The list of questions available for the player.
     * @throws IOException If an error occurs during input.
     */
	public void eliminateCharacters(ArrayList<Characters> userCharacters, Characters targetSelectedCharacters,
			int userQuestionNum, ArrayList<String>  userQuestions) throws IOException{
		ArrayList<Integer> removeIndex = new ArrayList<Integer>();
	    //System.out.println("You have chosen question: " + userQuestions.get(userQuestionNum) + "");
	     
	    if(userQuestionNum == 0){ //Is the eye colour brown?
	        if(targetSelectedCharacters.getEyeColour().equals("Brown")){
	            for(int i = 0; i < userCharacters.size(); i++){
	         	   if(!userCharacters.get(i).getEyeColour().equals("Brown")){
	                        removeIndex.add(i);
	                    }
	            }
	        }
	        else if(!targetSelectedCharacters.getEyeColour().equals("Brown")){
	            for(int i = 0; i < userCharacters.size(); i++){
	                    if(userCharacters.get(i).getEyeColour().equals("Brown")){
	                        removeIndex.add(i);
	                    }
	            }
	        }
	    }
	     if(userQuestionNum == 1){ //Is the eye colour blue?
	        if(targetSelectedCharacters.getEyeColour().equals("Blue")){
	            for(int i = 0; i < userCharacters.size(); i++){
	                    if(!userCharacters.get(i).getEyeColour().equals("Blue")){
	                        removeIndex.add(i);
	                    }
	            }
	        }
	        else if(!targetSelectedCharacters.getEyeColour().equals("Blue")){
	            for(int i = 0; i < userCharacters.size(); i++){
	                    if(userCharacters.get(i).getEyeColour().equals("Blue")){
	                        removeIndex.add(i);
	                    }
	            }
	        }
	     }
	    if(userQuestionNum == 2){ //
	        if(targetSelectedCharacters.getGender().equals("Male")){
	            for(int i = 0; i < userCharacters.size(); i++){
	                    if(!userCharacters.get(i).getGender().equals("Male")){
	                        removeIndex.add(i);
	                        
	                    }
	            }
	        }
	        else if(!targetSelectedCharacters.getGender().equals("Male")){
	            for(int i = 0; i < userCharacters.size(); i++){
	                    if(userCharacters.get(i).getGender().equals("Male")){
	                        removeIndex.add(i);
	                    }
	            }
	        }
	    }
	    if(userQuestionNum == 3){ //
	        if(targetSelectedCharacters.getSkinTone().equals("Light")){
	            for(int i = 0; i < userCharacters.size(); i++){
	                    if(!userCharacters.get(i).getSkinTone().equals("Light")){
	                        removeIndex.add(i);
	                    }
	            }
	        }
	        else if(!targetSelectedCharacters.getSkinTone().equals("Light")){
	            for(int i = 0; i < userCharacters.size(); i++){
	                    if(userCharacters.get(i).getSkinTone().equals("Light")){
	                        removeIndex.add(i);
	                    }
	            }
	        }
	    }
	    if(userQuestionNum == 4){ //
	        if(targetSelectedCharacters.getHairColour().equals("Black")){
	            for(int i = 0; i < userCharacters.size(); i++){
	                    if(!userCharacters.get(i).getHairColour().equals("Black")){
	                        removeIndex.add(i);
	                    }
	            }
	        }
	        else if(!targetSelectedCharacters.getHairColour().equals("Black")){
	            for(int i = 0; i < userCharacters.size(); i++){
	                    if(userCharacters.get(i).getHairColour().equals("Black")){
	                        removeIndex.add(i);
	                    }
	            }
	        }
	    }
	    if(userQuestionNum == 5){ //
	        if(targetSelectedCharacters.getHairColour().equals("Brown")){
	            for(int i = 0; i < userCharacters.size(); i++){
	                    if(!userCharacters.get(i).getHairColour().equals("Brown")){
	                        removeIndex.add(i);
	                    }
	            }
	        }
	        else if(!targetSelectedCharacters.getHairColour().equals("Brown")){
	            for(int i = 0; i < userCharacters.size(); i++){
	                    if(userCharacters.get(i).getHairColour().equals("Brown")){
	                        removeIndex.add(i);
	                    }
	            }
	        }
	    }
	    if(userQuestionNum == 6){ //
	        if(targetSelectedCharacters.getHairColour().equals("Blonde")){
	            for(int i = 0; i < userCharacters.size(); i++){
	                    if(!userCharacters.get(i).getHairColour().equals("Blonde")){
	                        removeIndex.add(i);
	                    }
	            }
	        }
	        else if(!targetSelectedCharacters.getHairColour().equals("Blonde")){
	            for(int i = 0; i < userCharacters.size(); i++){
	                    if(userCharacters.get(i).getHairColour().equals("Blonde")){
	                        removeIndex.add(i);
	                    }
	            }
	        }
	    }
	    if(userQuestionNum == 7){ //
	        if(targetSelectedCharacters.getHairColour().equals("White")){
	            for(int i = 0; i < userCharacters.size(); i++){
	                    if(!userCharacters.get(i).getHairColour().equals("White")){
	                        removeIndex.add(i);
	                    }
	            }
	        }
	        else if(!targetSelectedCharacters.getHairColour().equals("White")){
	            for(int i = 0; i < userCharacters.size(); i++){
	                    if(userCharacters.get(i).getHairColour().equals("White")){
	                        removeIndex.add(i);
	                    }
	            }
	        }
	    }
	    if(userQuestionNum == 8){ //
	        if(targetSelectedCharacters.getFacialHair()){
	            for(int i = 0; i < userCharacters.size(); i++){
	                    if(!userCharacters.get(i).getFacialHair()){
	                        removeIndex.add(i);
	                    }
	            }
	        }
	        else if(!targetSelectedCharacters.getFacialHair()){
	            for(int i = 0; i < userCharacters.size(); i++){
	                    if(userCharacters.get(i).getFacialHair()){
	                        removeIndex.add(i);
	                    }
	            }
	        }
	    }
	    if(userQuestionNum == 9){ //
	        if(targetSelectedCharacters.getGlasses()){
	            for(int i = 0; i < userCharacters.size(); i++){
	                    if(!userCharacters.get(i).getGlasses()){
	                        removeIndex.add(i);
	                    }
	            }
	        }
	        else if(!targetSelectedCharacters.getGlasses()){
	            for(int i = 0; i < userCharacters.size(); i++){
	                    if(userCharacters.get(i).getGlasses()){
	                        removeIndex.add(i);
	                    }
	            }
	        }
	    }
	    if(userQuestionNum == 10){ //
	        if(targetSelectedCharacters.getTeeth()){
	            for(int i = 0; i < userCharacters.size(); i++){
	                    if(!userCharacters.get(i).getTeeth()){
	                        removeIndex.add(i);
	                    }
	            }
	        }
	        else if(!targetSelectedCharacters.getTeeth()){
	            for(int i = 0; i < userCharacters.size(); i++){
	                    if(userCharacters.get(i).getTeeth()){
	                        removeIndex.add(i);
	                    }
	            }
	        }
	    }
	    if(userQuestionNum == 11){ //
	        if(targetSelectedCharacters.getHat()){
	            for(int i = 0; i < userCharacters.size(); i++){
	                    if(!userCharacters.get(i).getHat()){
	                        removeIndex.add(i);
	                    }
	            }
	        }
	        else if(!targetSelectedCharacters.getHat()){
	            for(int i = 0; i < userCharacters.size(); i++){
	                    if(userCharacters.get(i).getHat()){
	                        removeIndex.add(i);
	                    }
	            }
	        }
	    }
	    if(userQuestionNum == 12){ //
	        if(targetSelectedCharacters.getHairLength().equals("Short")){
	            for(int i = 0; i < userCharacters.size(); i++){
	                    if(!userCharacters.get(i).getHairLength().equals("Short")){
	                        removeIndex.add(i);
	                    }
	            }
	        }
	        else if(!targetSelectedCharacters.getHairLength().equals("Short")){
	            for(int i = 0; i < userCharacters.size(); i++){
	                    if(userCharacters.get(i).getHairLength().equals("Short")){
	                        removeIndex.add(i);
	                    }
	            }
	        }
	    }
	    if(userQuestionNum == 13){ //
	        if(targetSelectedCharacters.getHairLength().equals("Tied")){
	            for(int i = 0; i < userCharacters.size(); i++){
	                    if(!userCharacters.get(i).getHairLength().equals("Tied")){
	                        removeIndex.add(i);
	                    }
	            }
	        }
	        else if(!targetSelectedCharacters.getHairLength().equals("Tied")){
	            for(int i = 0; i < userCharacters.size(); i++){
	                    if(userCharacters.get(i).getHairLength().equals("Tied")){
	                        removeIndex.add(i);
	                    }
	            }
	        }
	    }
	    if(userQuestionNum == 14){ //
	        if(targetSelectedCharacters.getHairLength().equals("Long")){
	            for(int i = 0; i < userCharacters.size(); i++){
	                    if(!userCharacters.get(i).getHairLength().equals("Long")){
	                        removeIndex.add(i);
	                    }
	            }
	        }
	        else if(!targetSelectedCharacters.getHairLength().equals("Long")){
	            for(int i = 0; i < userCharacters.size(); i++){
	                    if(userCharacters.get(i).getHairLength().equals("Long")){
	                        removeIndex.add(i);
	                    }
	            }
	        }
	    }
	    
	    if(userQuestionNum == 15){ //
	        if(targetSelectedCharacters.getPiercings()){
	            for(int i = 0; i < userCharacters.size(); i++){
	                    if(!userCharacters.get(i).getPiercings()){
	                        removeIndex.add(i);
	                    }
	            }
	        }
	        else if(!targetSelectedCharacters.getPiercings()){
	            for(int i = 0; i < userCharacters.size(); i++){
	                    if(userCharacters.get(i).getPiercings()){
	                        removeIndex.add(i);
	                    }
	            }
	        }
	    }
	    userQuestions.set(userQuestionNum, "Already Asked");
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
		// Remove characters from the user's character list using the sorted indices
	    for (int i = 0; i < removeIndex.size(); i++) {
	        int index = removeIndex.get(i);
	        userCharacters.remove(index);
	    }

	    // Display the remaining characters left to guess

	    //System.out.println("----------------------------------------------------");
	    //System.out.println("Here are your remaining characters left to guess.");
	    for (int i = 0; i < userCharacters.size(); i++) {
	        //System.out.println("[" + (i) + "]" + userCharacters.get(i).getName());
	    }
	  
	}
	/**
	 * Updates the leaderboard for the Easy mode based on the player's performance.
	 *
	 * @param pointEasy          The player's score in Easy mode.
	 * @param namesEasy          Array containing the names of players on the leaderboard.
	 * @param scoreArrayEasy     Array containing the scores of players on the leaderboard.
	 * @param userEasy           The username of the current player.
	 * @param numOfGuessesEasy   Array containing the number of guesses of players on the leaderboard.
	 * @param timeEasy           Array containing the completion time of players on the leaderboard.
	 * @param time               The completion time of the current player.
	 * @param win                Indicates whether the player won the game.
	 * @param numOfGuess         The number of guesses made by the player.
	 * @throws NumberFormatException If there is an error in parsing numbers.
	 * @throws FileNotFoundException If the file is not found.
	 * @throws IOException           If there is an I/O error.
	 */
	public void updateEasyLeaderboard(int pointEasy, String namesEasy[], double scoreArrayEasy[], String userEasy, int[] numOfGuessesEasy, double []timeEasy,long time, boolean win, int numOfGuess) throws NumberFormatException, FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		   File myObjEasy = new File("EasyModeLeaderboard.txt"); //creating a new text file
			if (myObjEasy.createNewFile()){ //if text file is created, do does the following below
				FileWriter myWriter1 = new FileWriter("EasyModeLeaderboard.txt"); //writing in the text file

				//preloaded users
				myWriter1.write("Bot1,100,2,13.1\n"); 
				myWriter1.write("Bot2,99,3,14.2\n"); 
				myWriter1.write("Bot3,98,3,14.7\n"); 
				myWriter1.write("Bot4,97,3,14.987787\n"); 
				myWriter1.write("Bot5,96,3,14.987787\n"); 
				myWriter1.write("Bot6,95,3,14.987787\n"); 
				myWriter1.write("Bot7,94,3,14.987787\n"); 
				myWriter1.write("Bot8,93,3,14.987787\n"); 
				myWriter1.write("Bot9,92,3,14.987787\n"); 
				myWriter1.write("Bot10,91,3,14.987787\n");

				myWriter1.close(); //closing the text file writer
				int place1 = 0;//this is for the 3d array, which rank

				Scanner myReader = new Scanner(myObjEasy); //creating a reader, using scanner to read string inputs side by side (not line by line)
				while (myReader.hasNextLine()) { //check if there is anything on the next line
					String data = myReader.nextLine(); //read the line

					String[] newStr = data.split("[,]", 0); //slip the data by spaces


					namesEasy[place1] = newStr[0];
					scoreArrayEasy[place1] = Double.parseDouble(newStr[1]);
					numOfGuessesEasy[place1] = (int) Double.parseDouble(newStr[2]);
					timeEasy[place1] = Double.parseDouble(newStr[3]);

					place1++; //add place1 to do all the ranks
				}
				myReader.close();//close the reader
			}else{ //if the file is already created
				int place1 = 0;//the rank in the leaderboard

				Scanner myReader = new Scanner(myObjEasy); //creating a reader to read using scanner
				while (myReader.hasNextLine()) { //check if there is anything on the next line
					String data = myReader.nextLine(); //read the line

					String[] newStr = data.split("[,]", 0); //slip the data by spaces

					namesEasy[place1] = newStr[0];
					scoreArrayEasy[place1] = Double.parseDouble(newStr[1]);
					numOfGuessesEasy[place1] = (int) Double.parseDouble(newStr[2]);
					timeEasy[place1] = Double.parseDouble(newStr[3]);

					place1++; //add place1 to do all the ranks
				}
				myReader.close();//closing the reader
			}
			


			//starting the sort


			int location = 0;
			double smallest = scoreArrayEasy[0];
			for (int i = 0; i<scoreArrayEasy.length; i++) {
				if (scoreArrayEasy[i] < smallest) {
					smallest = scoreArrayEasy[i];
					location = i;
				}
			}
			//System.out.println("This is the EASY LEADERBOARD CHECKIN \nPointEasy is: " + pointEasy + "\nValue of smallest is: " + smallest + "\nLocation is: " + location);
			if (pointEasy > smallest) {
				scoreArrayEasy[location] = pointEasy;
				namesEasy[location] = userEasy;
				numOfGuessesEasy[location] = numOfGuess;
				timeEasy[location] = time;
			}

			double temp;
			String tempStr;

			int tempGuess;
			
			double tempTime;
			for(int i=0; i<scoreArrayEasy.length-1; i++) {
				for(int j = 0; j < ((scoreArrayEasy.length-1)-i);j++){
					if (scoreArrayEasy[j] < scoreArrayEasy[j+1]) {
						temp = scoreArrayEasy[j];
						scoreArrayEasy[j] = scoreArrayEasy[j+1];
						scoreArrayEasy[j+1] = temp;

						tempStr = namesEasy[j];
						namesEasy[j] = namesEasy[j+1];
						namesEasy[j+1] = tempStr;
						
						tempGuess = numOfGuessesEasy[j];
						numOfGuessesEasy[j] = numOfGuessesEasy[j+1];
						numOfGuessesEasy[j+1] = tempGuess;
						
						tempTime = timeEasy[j];
						timeEasy[j] = timeEasy[j+1];
						timeEasy[j+1] = tempTime;
					}
				}
			}


			myObjEasy.delete();
			newCreationEasy(namesEasy, scoreArrayEasy, numOfGuessesEasy, timeEasy);
	}
	/**
	 * Updates the leaderboard for the Easy mode based on the player's performance.
	 *
	 * @param pointEasy          The player's score in Easy mode.
	 * @param namesEasy          Array containing the names of players on the leaderboard.
	 * @param scoreArrayEasy     Array containing the scores of players on the leaderboard.
	 * @param userEasy           The username of the current player.
	 * @param numOfGuessesEasy   Array containing the number of guesses of players on the leaderboard.
	 * @param timeEasy           Array containing the completion time of players on the leaderboard.
	 * @param time               The completion time of the current player.
	 * @param win                Indicates whether the player won the game.
	 * @param numOfGuess         The number of guesses made by the player.
	 * @throws NumberFormatException If there is an error in parsing numbers.
	 * @throws FileNotFoundException If the file is not found.
	 * @throws IOException           If there is an I/O error.
	 */
	public void newCreationEasy(String [] namesEasy, double [] scoreArrayEasy, int [] numOfGuessesEasy, double [] timeEasy) throws NumberFormatException, FileNotFoundException, IOException {
		File myObjHard = new File("EasyModeLeaderboard.txt");
		if (myObjHard.createNewFile()) {
			FileWriter myWriter = new FileWriter("EasyModeLeaderboard.txt");// generate text file
			// generates the easy mode leaderboard
			myWriter.write(namesEasy[0] +  "," + scoreArrayEasy[0] + "," + numOfGuessesEasy[0] + ", " + timeEasy[0] + "\n");
			myWriter.write(namesEasy[1] +  "," + scoreArrayEasy[1] + "," + numOfGuessesEasy[1] + ", " + timeEasy[1] + "\n");
			myWriter.write(namesEasy[2] +  "," + scoreArrayEasy[2] + "," + numOfGuessesEasy[2] + ", " + timeEasy[2] + "\n");
			myWriter.write(namesEasy[3] +  "," + scoreArrayEasy[3] + "," + numOfGuessesEasy[3] + ", " + timeEasy[3] + "\n");
			myWriter.write(namesEasy[4] +  "," + scoreArrayEasy[4] + "," + numOfGuessesEasy[4] + ", " + timeEasy[4] + "\n");
			myWriter.write(namesEasy[5] +  "," + scoreArrayEasy[5] + "," + numOfGuessesEasy[5] + ", " + timeEasy[5] + "\n");
			myWriter.write(namesEasy[6] +  "," + scoreArrayEasy[6] + "," + numOfGuessesEasy[6] + ", " + timeEasy[6] + "\n");
			myWriter.write(namesEasy[7] +  "," + scoreArrayEasy[7] + "," + numOfGuessesEasy[7] + ", " + timeEasy[7] + "\n");
			myWriter.write(namesEasy[8] +  "," + scoreArrayEasy[8] + "," + numOfGuessesEasy[8] + ", " + timeEasy[8] + "\n");
			myWriter.write(namesEasy[9] +  "," + scoreArrayEasy[9] + "," + numOfGuessesEasy[9] + ", " + timeEasy[9] + "\n");

			myWriter.close();
		/*	int place = 0;

			Scanner myReader = new Scanner(myObjHard);
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				String[] newStr = data.split("[,]", 0); // splits the spaces

				namesEasy[place1] = newStr[0];
				scoreArrayEasy[place1] = Double.parseDouble(newStr[1]);
				numOfGuessesEasy[place1] = (int) Double.parseDouble(newStr[2]);
				timeEasy[place1] = Double.parseDouble(newStr[3]);
			}
			myReader.close();
		}*/
		}
	}
	/**
	 * Updates the leaderboard for the Easy mode based on the player's performance.
	 *
	 * @param pointEasy          The player's score in Easy mode.
	 * @param namesEasy          Array containing the names of players on the leaderboard.
	 * @param scoreArrayEasy     Array containing the scores of players on the leaderboard.
	 * @param userEasy           The username of the current player.
	 * @param numOfGuessesEasy   Array containing the number of guesses of players on the leaderboard.
	 * @param timeEasy           Array containing the completion time of players on the leaderboard.
	 * @param time               The completion time of the current player.
	 * @param win                Indicates whether the player won the game.
	 * @param numOfGuess         The number of guesses made by the player.
	 * @throws NumberFormatException If there is an error in parsing numbers.
	 * @throws FileNotFoundException If the file is not found.
	 * @throws IOException           If there is an I/O error.
	 */	
		public void updateNormalLeaderboard(int pointNormal, String namesNormal[], double scoreArrayNormal[], String userNormal, int[] numOfGuessesNormal, double []timeNormal,long time, boolean win, int numOfGuess) throws NumberFormatException, FileNotFoundException, IOException {
			// TODO Auto-generated method stub
			   File myObjNormal = new File("NormalModeLeaderboard.txt"); //creating a new text file
				if (myObjNormal.createNewFile()){ //if text file is created, do does the following below
					FileWriter myWriter1 = new FileWriter("NormalModeLeaderboard.txt"); //writing in the text file

					//preloaded users
					myWriter1.write("Bot1,100,2,13.1\n"); 
					myWriter1.write("Bot2,99,3,14.2\n"); 
					myWriter1.write("Bot3,98,3,14.7\n"); 
					myWriter1.write("Bot4,97,3,14.987787\n"); 
					myWriter1.write("Bot5,96,3,14.987787\n"); 
					myWriter1.write("Bot6,95,3,14.987787\n"); 
					myWriter1.write("Bot7,94,3,14.987787\n"); 
					myWriter1.write("Bot8,93,3,14.987787\n"); 
					myWriter1.write("Bot9,92,3,14.987787\n"); 
					myWriter1.write("Bot10,91,3,14.987787\n");

					myWriter1.close(); //closing the text file writer
					int place1 = 0;//this is for the 3d array, which rank

					Scanner myReader = new Scanner(myObjNormal); //creating a reader, using scanner to read string inputs side by side (not line by line)
					while (myReader.hasNextLine()) { //check if there is anything on the next line
						String data = myReader.nextLine(); //read the line

						String[] newStr = data.split("[,]", 0); //slip the data by spaces


						namesNormal[place1] = newStr[0];
						scoreArrayNormal[place1] = Double.parseDouble(newStr[1]);
						numOfGuessesNormal[place1] = (int) Double.parseDouble(newStr[2]);
						timeNormal[place1] = Double.parseDouble(newStr[3]);

						place1++; //add place1 to do all the ranks
					}
					myReader.close();//close the reader
				}else{ //if the file is already created
					int place1 = 0;//the rank in the leaderboard

					Scanner myReader = new Scanner(myObjNormal); //creating a reader to read using scanner
					while (myReader.hasNextLine()) { //check if there is anything on the next line
						String data = myReader.nextLine(); //read the line

						String[] newStr = data.split("[,]", 0); //slip the data by spaces

						namesNormal[place1] = newStr[0];
						scoreArrayNormal[place1] = Double.parseDouble(newStr[1]);
						numOfGuessesNormal[place1] = (int) Double.parseDouble(newStr[2]);
						timeNormal[place1] = Double.parseDouble(newStr[3]);

						place1++; //add place1 to do all the ranks
					}
					myReader.close();//closing the reader
				}
				


				//starting the sort


				int location = 0;
				double smallest = scoreArrayNormal[0];
				for (int i = 0; i<scoreArrayNormal.length; i++) {
					if (scoreArrayNormal[i] < smallest) {
						smallest = scoreArrayNormal[i];
						location = i;
					}
				}
				//System.out.println("This is the Normal LEADERBOARD CHECKIN \nPointNormal is: " + pointNormal + "\nValue of smallest is: " + smallest + "\nLocation is: " + location);
				if (pointNormal > smallest) {
					scoreArrayNormal[location] = pointNormal;
					namesNormal[location] = userNormal;
					numOfGuessesNormal[location] = numOfGuess;
					timeNormal[location] = time;
				}

				double temp;
				String tempStr;

				int tempGuess;
				
				double tempTime;
				for(int i=0; i<scoreArrayNormal.length-1; i++) {
					for(int j = 0; j < ((scoreArrayNormal.length-1)-i);j++){
						if (scoreArrayNormal[j] < scoreArrayNormal[j+1]) {
							temp = scoreArrayNormal[j];
							scoreArrayNormal[j] = scoreArrayNormal[j+1];
							scoreArrayNormal[j+1] = temp;

							tempStr = namesNormal[j];
							namesNormal[j] = namesNormal[j+1];
							namesNormal[j+1] = tempStr;
							
							tempGuess = numOfGuessesNormal[j];
							numOfGuessesNormal[j] = numOfGuessesNormal[j+1];
							numOfGuessesNormal[j+1] = tempGuess;
							
							tempTime = timeNormal[j];
							timeNormal[j] = timeNormal[j+1];
							timeNormal[j+1] = tempTime;
						}
					}
				}


				myObjNormal.delete();
				newCreationNormal(namesNormal, scoreArrayNormal, numOfGuessesNormal, timeNormal);
		}
		/**
		 * Updates the leaderboard for the Normal mode based on the player's performance.
		 *
		 * @param pointEasy          The player's score in Easy mode.
		 * @param namesEasy          Array containing the names of players on the leaderboard.
		 * @param scoreArrayEasy     Array containing the scores of players on the leaderboard.
		 * @param userEasy           The username of the current player.
		 * @param numOfGuessesEasy   Array containing the number of guesses of players on the leaderboard.
		 * @param timeEasy           Array containing the completion time of players on the leaderboard.
		 * @param time               The completion time of the current player.
		 * @param win                Indicates whether the player won the game.
		 * @param numOfGuess         The number of guesses made by the player.
		 * @throws NumberFormatException If there is an error in parsing numbers.
		 * @throws FileNotFoundException If the file is not found.
		 * @throws IOException           If there is an I/O error.
		 */
		public void newCreationNormal(String [] namesNormal, double [] scoreArrayNormal, int [] numOfGuessesNormal, double [] timeNormal) throws NumberFormatException, FileNotFoundException, IOException {
			File myObjHard = new File("NormalModeLeaderboard.txt");
			if (myObjHard.createNewFile()) {
				FileWriter myWriter = new FileWriter("NormalModeLeaderboard.txt");// generate text file
				// generates the Normal mode leaderboard
				myWriter.write(namesNormal[0] +  "," + scoreArrayNormal[0] + "," + numOfGuessesNormal[0] + ", " + timeNormal[0] + "\n");
				myWriter.write(namesNormal[1] +  "," + scoreArrayNormal[1] + "," + numOfGuessesNormal[1] + ", " + timeNormal[1] + "\n");
				myWriter.write(namesNormal[2] +  "," + scoreArrayNormal[2] + "," + numOfGuessesNormal[2] + ", " + timeNormal[2] + "\n");
				myWriter.write(namesNormal[3] +  "," + scoreArrayNormal[3] + "," + numOfGuessesNormal[3] + ", " + timeNormal[3] + "\n");
				myWriter.write(namesNormal[4] +  "," + scoreArrayNormal[4] + "," + numOfGuessesNormal[4] + ", " + timeNormal[4] + "\n");
				myWriter.write(namesNormal[5] +  "," + scoreArrayNormal[5] + "," + numOfGuessesNormal[5] + ", " + timeNormal[5] + "\n");
				myWriter.write(namesNormal[6] +  "," + scoreArrayNormal[6] + "," + numOfGuessesNormal[6] + ", " + timeNormal[6] + "\n");
				myWriter.write(namesNormal[7] +  "," + scoreArrayNormal[7] + "," + numOfGuessesNormal[7] + ", " + timeNormal[7] + "\n");
				myWriter.write(namesNormal[8] +  "," + scoreArrayNormal[8] + "," + numOfGuessesNormal[8] + ", " + timeNormal[8] + "\n");
				myWriter.write(namesNormal[9] +  "," + scoreArrayNormal[9] + "," + numOfGuessesNormal[9] + ", " + timeNormal[9] + "\n");

				myWriter.close();
			/*	int place = 0;

				Scanner myReader = new Scanner(myObjHard);
				while (myReader.hasNextLine()) {
					String data = myReader.nextLine();
					String[] newStr = data.split("[,]", 0); // splits the spaces

					namesNormal[place1] = newStr[0];
					scoreArrayNormal[place1] = Double.parseDouble(newStr[1]);
					numOfGuessesNormal[place1] = (int) Double.parseDouble(newStr[2]);
					timeNormal[place1] = Double.parseDouble(newStr[3]);
				}
				myReader.close();
			}*/
			}
		}
			
		/**
		 * Updates the leaderboard for the Hard mode based on the player's performance.
		 *
		 * @param pointEasy          The player's score in Easy mode.
		 * @param namesEasy          Array containing the names of players on the leaderboard.
		 * @param scoreArrayEasy     Array containing the scores of players on the leaderboard.
		 * @param userEasy           The username of the current player.
		 * @param numOfGuessesEasy   Array containing the number of guesses of players on the leaderboard.
		 * @param timeEasy           Array containing the completion time of players on the leaderboard.
		 * @param time               The completion time of the current player.
		 * @param win                Indicates whether the player won the game.
		 * @param numOfGuess         The number of guesses made by the player.
		 * @throws NumberFormatException If there is an error in parsing numbers.
		 * @throws FileNotFoundException If the file is not found.
		 * @throws IOException           If there is an I/O error.
		 */
		public void updateHardLeaderboard(int pointHard, String namesHard[], double scoreArrayHard[], String userHard, int[] numOfGuessesHard, double []timeHard,long time, boolean win, int numOfGuess) throws NumberFormatException, FileNotFoundException, IOException {
			// TODO Auto-generated method stub
			   File myObjHard = new File("HardModeLeaderboard.txt"); //creating a new text file
				if (myObjHard.createNewFile()){ //if text file is created, do does the following below
					FileWriter myWriter1 = new FileWriter("HardModeLeaderboard.txt"); //writing in the text file

					//preloaded users
					myWriter1.write("Bot1,100,2,13.1\n"); 
					myWriter1.write("Bot2,99,3,14.2\n"); 
					myWriter1.write("Bot3,98,3,14.7\n"); 
					myWriter1.write("Bot4,97,3,14.987787\n"); 
					myWriter1.write("Bot5,96,3,14.987787\n"); 
					myWriter1.write("Bot6,95,3,14.987787\n"); 
					myWriter1.write("Bot7,94,3,14.987787\n"); 
					myWriter1.write("Bot8,93,3,14.987787\n"); 
					myWriter1.write("Bot9,92,3,14.987787\n"); 
					myWriter1.write("Bot10,91,3,14.987787\n");

					myWriter1.close(); //closing the text file writer
					int place1 = 0;//this is for the 3d array, which rank

					Scanner myReader = new Scanner(myObjHard); //creating a reader, using scanner to read string inputs side by side (not line by line)
					while (myReader.hasNextLine()) { //check if there is anything on the next line
						String data = myReader.nextLine(); //read the line

						String[] newStr = data.split("[,]", 0); //slip the data by spaces


						namesHard[place1] = newStr[0];
						scoreArrayHard[place1] = Double.parseDouble(newStr[1]);
						numOfGuessesHard[place1] = (int) Double.parseDouble(newStr[2]);
						timeHard[place1] = Double.parseDouble(newStr[3]);

						place1++; //add place1 to do all the ranks
					}
					myReader.close();//close the reader
				}else{ //if the file is already created
					int place1 = 0;//the rank in the leaderboard

					Scanner myReader = new Scanner(myObjHard); //creating a reader to read using scanner
					while (myReader.hasNextLine()) { //check if there is anything on the next line
						String data = myReader.nextLine(); //read the line

						String[] newStr = data.split("[,]", 0); //slip the data by spaces

						namesHard[place1] = newStr[0];
						scoreArrayHard[place1] = Double.parseDouble(newStr[1]);
						numOfGuessesHard[place1] = (int) Double.parseDouble(newStr[2]);
						timeHard[place1] = Double.parseDouble(newStr[3]);

						place1++; //add place1 to do all the ranks
					}
					myReader.close();//closing the reader
				}
				


				//starting the sort


				int location = 0;
				double smallest = scoreArrayHard[0];
				for (int i = 0; i<scoreArrayHard.length; i++) {
					if (scoreArrayHard[i] < smallest) {
						smallest = scoreArrayHard[i];
						location = i;
					}
				}
				//System.out.println("This is the Hard LEADERBOARD CHECKIN \nPointHard is: " + pointHard + "\nValue of smallest is: " + smallest + "\nLocation is: " + location);
				if (pointHard > smallest) {
					scoreArrayHard[location] = pointHard;
					namesHard[location] = userHard;
					numOfGuessesHard[location] = numOfGuess;
					timeHard[location] = time;
				}

				double temp;
				String tempStr;

				int tempGuess;
				
				double tempTime;
				for(int i=0; i<scoreArrayHard.length-1; i++) {
					for(int j = 0; j < ((scoreArrayHard.length-1)-i);j++){
						if (scoreArrayHard[j] < scoreArrayHard[j+1]) {
							temp = scoreArrayHard[j];
							scoreArrayHard[j] = scoreArrayHard[j+1];
							scoreArrayHard[j+1] = temp;

							tempStr = namesHard[j];
							namesHard[j] = namesHard[j+1];
							namesHard[j+1] = tempStr;
							
							tempGuess = numOfGuessesHard[j];
							numOfGuessesHard[j] = numOfGuessesHard[j+1];
							numOfGuessesHard[j+1] = tempGuess;
							
							tempTime = timeHard[j];
							timeHard[j] = timeHard[j+1];
							timeHard[j+1] = tempTime;
						}
					}
				}


				myObjHard.delete();
				newCreationHard(namesHard, scoreArrayHard, numOfGuessesHard, timeHard);
		}
		/**
		 * Creates or updates the Hard mode leaderboard based on the provided data.
		 *
		 * @param namesHard          Array containing the names of players in Hard mode.
		 * @param scoreArrayHard     Array containing the scores of players in Hard mode.
		 * @param numOfGuessesHard   Array containing the number of guesses of players in Hard mode.
		 * @param timeHard           Array containing the completion time of players in Hard mode.
		 * @throws NumberFormatException If there is an error in parsing numbers.
		 * @throws FileNotFoundException If the file is not found.
		 * @throws IOException           If there is an I/O error.
		 */
		public void newCreationHard(String[] namesHard, double[] scoreArrayHard, int[] numOfGuessesHard, double[] timeHard)
		        throws NumberFormatException, FileNotFoundException, IOException {
		    // Create a new text file for the Hard mode leaderboard if it does not exist
		    File myObjHard = new File("HardModeLeaderboard.txt");
		    if (myObjHard.createNewFile()) {
		        // If the file is created, write player data for Hard mode
		        FileWriter myWriter = new FileWriter("HardModeLeaderboard.txt");

		        // Write player data for the top 10 players in Hard mode
		        for (int i = 0; i < 10; i++) {
		            myWriter.write(namesHard[i] + "," + scoreArrayHard[i] + "," + numOfGuessesHard[i] + ", " + timeHard[i] + "\n");
		        }

		        myWriter.close();
		    }
		    // If the file already exists, do nothing (no update needed)
		}
}