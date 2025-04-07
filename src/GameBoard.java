/*===================================================================
Program Name: GameBoard.java (Frontend + Backend Code)
Author: William Zhang, Frank Chen, Juliana Zhu
Date: January 13th, 2024
Programming Language & Version Number: Java - Neon.la Release (4.6.1)
=====================================================================
Problem Definition: 
-> Allowing the user and AI to take turns asking differential yes or no questions to isolate a hidden character on a 6x4 grid. The first
   to guess the other's character wins.

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
 List of Identifiers 
  ===================================================================
 -Let br be a variable taken as an instance variable from the class bufferedreader 
 -Let characterPanel be the buttons used to display the characters left to guess for the user
 -Let refenceNames be the buttons used to reference the characters left
 -Let ROW be a constant that is the array row size for the grid
 -Let COL be a constant that is the array col size for the grid 
 -Let instructionalButton used as a button for instructions
 -Let playButton be the button used to Guess the character
 -Let homeButton be the button used to go back to the homescreen for the program
 -Let askQuestions be the button used for the user to ask questions to the AI
 -Let playerMaxNumGuess be the max number of guesses the user has
 -Let musicOffOn be the variable type boolean to check if the user would like music
 -Let chosenMode be the diffculty mode the user can select from
 -Let player1Name be the name for the user <type String>
 -Let questionList be an arraylist that stores all the questions
 -Let player1Selected character be the character the user reveals at the end
 -Let computerSelected character be the random character the computer selects 
 -Let numOfGuesses be a count <type Int> of how many guesses the user has left
 -Let userAnswers be the String type answer for the users response to the question
 -Let validateAnswers be an arraylist that houses all the answers needed to be validated by the computer
 -Let player1 be the object created from the Player class that has methods to control the users actions
 -Let computerPlayer be the object created from the computer class that has methods to control the AI actions
 -Let questionIsAskedEasy be a boolean array that checks the questions already asked for the computer on easy mode
 -Let questionIsAskedNormal be a boolean array that checks the questions already asked for the computer on normal mode
 -Let questionIsAskedHard be a boolean array that checks the questions already asked for the computer on hard mode
 -Let netTime be a variable that is used to reset the time taken for the user
 -Let win be a boolean that switches values and continues the game depending on if the game is won for the user/computer
 -Let flag be the type boolean that sets values and the actionlistners to start the users turn
 -Let exists winner be a boolean variable that changes if the user wins
 -Let compDecision be a variable of type int that marks an integer based on if the user selects to guess/ask questions
 -Let computerAksedQuestion be a string variable used to set values to an array of questions if the question has been selected by the user
 -Let validationQuestions be an arraylist of the list of question to validate at the end
 -Let userAnswersQuesitions be an arraylist of type String that marks the answers to every question asked
 -Let displayCharacters be an arraylist displaying all the characters that the user can pick from
 -Let characterComputerQuestionsLeft be an arraylist of type String that displays the characters the user has left to select from
 -Let playerQuestionsLeft be an arraylist of type String that contains all the questionsLeftToAsk
 -Let userCharactersReamining be an arraylist of type Characters that the user still has left
 -Let computerCharactersRemaining be an arraylist of type Characters that the computer still has left
 -Let player1QuestionAsked be an int that marks the index of the question asked
 -Let computerQuestionsAsked be an int that marks the index of the question asked	
 -Let musicFilePath be the path for the music file
===================================================================*/
import javax.swing.*;//Import the library
import java.awt.*;//Import the library
import java.awt.event.*;//Import the library
import java.io.BufferedReader;
import java.io.File;//Import the library
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;//Import the library
import java.util.Scanner;//Import the library
import javax.sound.sampled.*;


//Declare a class named GameBoard, extending JFrame and implementing ActionListener
	public class GameBoard extends JFrame implements ActionListener {
		
		// Constants for the dimensions of the game board (4 rows and 6 columns)
		 final int ROW = 4; //NEW
		 final int COL = 6; //NEW
		
		// JLabel representing the game screen
		JLabel gameScreen = new JLabel();
		
		// Buttons for navigating and playing the game
		JButton homeButton = new JButton("HOME");//DECLARE instructions BUTTON (1)
		JButton playButton = new JButton("PLAY");//DECLARE instructions BUTTON (2)
		JButton guessButton = new JButton("GUESS");//DECLARE instructions BUTTON (3)
		JButton askQuestionsButton = new JButton("ASK QUESTION");//DECLARE instructions BUTTON (4)
		
		// Panel for character buttons
		JPanel characterPanel = new JPanel();
		
		// 2D array of character buttons and reference array
		 JButton characterButton[][]= new JButton[ROW][COL];
		 Characters [][] referenceNameToButton = new Characters[ROW][COL]; //NEW
		
		// Variables needed for the game
		 int playerMaxNumGuess = 1;
		 boolean musicOffOn = true;
		 String chosenMode = "Hard";
		 String player1Name = "test";
	
		// Variables taken from GuessWhoMain
		 ArrayList<String> questionList = new ArrayList<>();
		 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		 Characters player1SelectedCharacters;
		 Characters computerSelectedCharacters;
	 
		// Arrays for storing names, scores, and user information for different difficulty levels
		 String[] namesEasy = new String[10];
		 double[] scoreArrayEasy = new double[10];
		 String userEasy;
		
		 String[] namesNormal = new String[10];
		 double[] scoreArrayNormal = new double[10];
		 String userNormal;
		
		 String[] namesHard = new String[10];
		 double[] scoreArrayHard = new double[10];
		 String userHard;
		
		// Variables for tracking game statistics
		 int numOfGuesses;
		 boolean isCorrectGuess;
		 boolean[] questionIsAskedEasy;
		 boolean[] questionIsAskedNormal;
		 boolean[] questionIsAskedHard;
		
		// Arrays for storing the number of guesses and time taken for each difficulty level
		 int [] numOfGuessesEasy = new int[10];
		 double[] timeEasy = new double[10];
		 int [] numOfGuessesNormal = new int[10];
		 double [] timeNormal = new double[10];
		 int [] numOfGuessesHard = new int[10];
		 double [] timeHard = new double[10];
		
		// Variables for tracking time during the game
		 long timeStart;
		 long timeEnd;
		 long netTime = 0;
		
		// Flags and variables related to game outcomes
		 boolean win = false;
		 int player1Score = 0; 
		 boolean flag;
		 boolean existsWinner;
		 int compDecision;
		 String computerAnswersToQuestion = "";
		
		// Additional variables for game validation
		 ArrayList<String> validationQuestions = new ArrayList<String>();
		 ArrayList<Characters> displayCharacters = new ArrayList<Characters>();
		 ArrayList<Characters> player1CharactersRemaining = new ArrayList<>();
		 ArrayList<Characters> computerCharactersRemaining = new ArrayList<>();
		 ArrayList<String> player1QuestionsLeft = new ArrayList<>();
		 ArrayList<String> computerQuestionsLeft = new ArrayList<>();
		 int player1QuestionAsked;
		 int computerQuestionAsked;
		 ArrayList<Integer> questionNumToValidate = new ArrayList<>();
		 ArrayList<String> userQuestionAnswersValidate = new ArrayList<>();
		 String userAnswers;
		boolean validate = true;
		 Player player1 = new Player (player1Name, player1Score, player1CharactersRemaining,player1QuestionsLeft);
		 Computer computerPlayer = new Computer(computerSelectedCharacters,computerCharactersRemaining,computerQuestionsLeft,chosenMode);
		 String musicFilePath = "C:\\Users\\leagu\\eclipse-workspace\\GuessWho\\guesswhomusic.wav";
		 SwingWorker<Void, Void> musicWorker;
		 Thread musicThread;
	 
	    //declare objects for all 24 guess who Characters, with the proper listed characteristics of each Character
	   	Characters Daniel = new Characters("Daniel","Green","Male", "Light", "Ginger", true, false, false, false, "Tied",false);
	    Characters Al = new Characters("Al","Green","Male", "Dark", "White", true, true, false, false, "Bald",false);
	    Characters Jordan = new Characters("Jordan","Brown", "Male", "Dark", "Black", true, false, false, false, "Short",true);
	    Characters Joe = new Characters("Joe","Brown","Male", "Dark", "White", true, true, true, false, "Bald",false);
	    Characters Sam = new Characters("Sam","Green", "Male", "Dark", "Black", false, false, false, true, "Short",false);
	    Characters Olivia = new Characters("Olivia","Brown", "Female", "Dark", "Black", false, false, false, false, "Tied",false);
	    Characters Nick = new Characters("Nick","Brown", "Male", "Light", "Blonde", false, false, false, false, "Short",true);
	    Characters David = new Characters("David","Brown", "Male", "Light","Blonde", true, false, true, true, "Short",false);
	    Characters Sofia = new Characters("Sofia","Green", "Female", "Dark", "Brown", false, false, true, false, "Short",true);
	    Characters Liz = new Characters("Liz","Blue", "Female", "Light", "White", false, true, true, false, "Long",false);
	    Characters Lily = new Characters("Lily","Green", "Female", "Dark", "Brown", false, false, true, true, "Long",false);
	    Characters Leo = new Characters("Leo","Brown", "Male","Dark", "White", false, false, true, false, "Short",false);
	    Characters Emma = new Characters("Emma","Brown", "Female", "Light", "Ginger", false, false, false, false, "Tied",false);
	    Characters Ben = new Characters("Ben","Brown", "Male", "Light", "Brown", false, true, false, false, "Short",true);
	    Characters Katie = new Characters("Katie","Blue", "Female", "Light", "Blonde", false, false, false, true, "Tied",false);
	    Characters Amy = new Characters("Amy","Brown", "Female", "Light", "Black", false, true, false, false, "Short",false);
	    Characters Mike = new Characters("Mike","Brown", "Male", "Light", "Black", false, false, true, true, "Short",false);
	    Characters Gabe = new Characters("Gabe","Brown","Male", "Dark", "Black", false, false, false, false, "Short",false);
	    Characters Farah = new Characters("Farah","Blue", "Female", "Dark", "Black", false, false, false, false, "Tied",false);
	    Characters Laura = new Characters("Laura","Green", "Female", "Dark", "Black", false, false, true, false, "Long",true);
	    Characters Eric = new Characters("Eric","Blue", "Male", "Light", "Black", false, false, false, false, "Short",false) ;
	    Characters Carmen = new Characters("Carmen","Brown", "Female", "Dark","White", false, false, true, false, "Short",true);
	    Characters Rachel = new Characters("Rachel","Blue","Female", "Light", "Brown", false, true, false, false, "Long",true);
	    Characters Mia = new Characters("Mia","Brown", "Female", "Dark", "Black", false, false, true, false, "Long",false);
	          
	    //adding objects to displayCharacters ArrayList type <Characters>
		public GameBoard()throws Exception {
			setTitle("Guess Who Final Project");
			validationQuestions.clear();
			displayCharacters.clear();
		    displayCharacters.add(Daniel);
		    displayCharacters.add(Al);
		    displayCharacters.add(Jordan);
		    displayCharacters.add(Joe);
		    displayCharacters.add(Sam);
		    displayCharacters.add(Olivia);
		    displayCharacters.add(Nick);
		    displayCharacters.add(David);
		    displayCharacters.add(Sofia);
		    displayCharacters.add(Liz);
		    displayCharacters.add(Lily);
		    displayCharacters.add(Leo);
		    displayCharacters.add(Emma);
		    displayCharacters.add(Ben);
		    displayCharacters.add(Katie);
		    displayCharacters.add(Amy);
		    displayCharacters.add(Mike);
		    displayCharacters.add(Gabe);
		    displayCharacters.add(Farah);
		    displayCharacters.add(Laura);
		    displayCharacters.add(Eric);
		    displayCharacters.add(Carmen);
		    displayCharacters.add(Rachel);
		    displayCharacters.add(Mia);
		    
		    fillReferenceButton(); // fills the arraylist for referenceButtons
	
					
	    	writeSettingVariables(); //set the variables from other class from settings
	    	
	        if (musicOffOn) {
	            playMusic(musicFilePath);
	
	            // Example: Stop playback after 5 seconds
	
	
	        }
	    	//System.out.println(playerMaxNumGuess);
	    	//System.out.println(musicOffOn);
	    	//System.out.println(chosenMode);
	    	//System.out.println(player1Name);
	        
	        
	        //Reset the boolean questions asked for each variable so that each different play, it is always false initially
	    	questionIsAskedEasy = new boolean [19];
	        for(int i = 0; i < questionIsAskedEasy.length; i++) {
	     	   questionIsAskedEasy[i] = false;
	        }
	        questionIsAskedNormal = new boolean [19];
	        for(int i = 0; i < questionIsAskedNormal.length; i++) {
	     	   questionIsAskedNormal[i] = false;
	        }
	        questionIsAskedHard = new boolean [19];
	        for(int i = 0; i < questionIsAskedHard.length; i++) {
	     	   questionIsAskedHard[i] = false;
	        }
	
	        //call to writeQuestionArrayList to write all the 18 questions to an arraylist for both CPU + Player to guess
	
		   
	      
	        int turnOrder = 0; // this is the variable for determining if computer/player goes first
	     // Initialize the components of the frame
	        initComponents();
	
	        // Set the focus painted property of the homeButton to false
	        homeButton.setFocusPainted(false);
	
	        // Set the size of the frame to 710x430 pixels
	        setSize(710, 430);
	
	        // Set the background image for the home screen
	        backGroundImage();
	
	        // Make the frame visible
	        setVisible(true);
	
	        // Pick computer characters for the game and store them in computerSelectedCharacters
	        computerSelectedCharacters = pickComputerCharacters(computerSelectedCharacters, displayCharacters);
	
	        // Record the starting time of the game
	        timeStart = System.nanoTime();
	
	        // Initialize flags and variables for the game
	        flag = false;
	        existsWinner = false;
	        userQuestionAnswersValidate.clear();
	        questionNumToValidate.clear();
	        userAnswers = "";
	        numOfGuesses = 0;
	        netTime = 0;
	
	        // Clear and initialize question-related lists
	        questionList.clear();
	        writeQuestionArrayList(questionList);
	        writeUserAndComputerQuestions();
	
	        // Set player1's name and questions remaining
	        player1.setPlayerName(player1Name);
	        player1.setQuestionsRemaining(player1QuestionsLeft);
	
	        // Set computerPlayer's questions remaining and pick computer characters
	        computerPlayer.setQuestionsRemaining(computerQuestionsLeft);
	        computerSelectedCharacters = pickComputerCharacters(computerSelectedCharacters, displayCharacters);
	        computerPlayer.setSelectedCharacter(computerSelectedCharacters);
	
	        // Fill user and computer characters for the game
	        fillUserAndComputerCharacters();
	
	        // Determine the turn order for the game
	        turnOrder = getTurnOrder();
	    	
	    	//if turn order is one...
	        if(turnOrder == 1) {
	        	//System.out.println("turn order is one, we will start game for computer first");
	        	startGame();
				remindPlayerTurn();//method to remind player it is their turn
	        }
	        else if(turnOrder == 2) {
	        	//System.out.println("turn order is two, we will start game for character first");
	        	remindPlayerTurn();//method to remind player it is their turn
	        	}
	
			
		}
		// Method to set the background image for the game screen
		public void backGroundImage(){
		    // Create an image icon from the specified file
		    ImageIcon icon = new ImageIcon("GameBoard.jpg");
	
		    // Get the image from the icon
		    Image img = icon.getImage();
	
		    // Scale the image to fit the game screen dimensions with smooth scaling
		    Image imgScale = img.getScaledInstance(gameScreen.getWidth(), gameScreen.getHeight(), Image.SCALE_SMOOTH);
	
		    // Create a new icon from the scaled image
		    ImageIcon scaledIcon = new ImageIcon(imgScale);
	
		    // Set the scaled icon as the background image for the game screen
		    gameScreen.setIcon(scaledIcon);
		}
	
		// Method to initialize components, buttons, and game screen display
		public void initComponents() throws Exception {
		    // Initialize the home button
            setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
            getContentPane().setLayout(null);
		    homeButton = new javax.swing.JButton();
		    homeButton.setBackground(new java.awt.Color(136, 9, 9));
		    homeButton.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
		    homeButton.setForeground(new java.awt.Color(255, 255, 255));
		    homeButton.setText("HOME");
		    homeButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 3, true));
		    getContentPane().add(homeButton);
		    homeButton.setBounds(465, 25, 157, 40);
	
		    // Initialize the guess button
		    guessButton = new javax.swing.JButton();
		    guessButton.setBackground(new java.awt.Color(136, 9, 9));
		    guessButton.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
		    guessButton.setForeground(new java.awt.Color(255, 255, 255));
		    guessButton.setText("GUESS");
		    guessButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 3, true));
		    getContentPane().add(guessButton);
		    guessButton.setBounds(465, 280, 157, 40);
	
		    // Initialize the ask questions button
		    askQuestionsButton = new javax.swing.JButton();
		    askQuestionsButton.setBackground(new java.awt.Color(136, 9, 9));
		    askQuestionsButton.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
		    askQuestionsButton.setForeground(new java.awt.Color(255, 255, 255));
		    askQuestionsButton.setText("ASK QUESTION");
		    askQuestionsButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 3, true));
		    getContentPane().add(askQuestionsButton);
		    askQuestionsButton.setBounds(465, 330, 157, 40);
	        //----------------------------------------------------------------------------------
	        //----------------------------------------------------------------------------------
		    //----------------------------------------------------------------------------------
	        //----------------------------------------------------------------------------------
			// CODE FOR BUTTON IMAGE REPLACEMENT
	        characterPanel.setBounds(23, 23, 417, 369);  // Setting the bounds for the mainPanel
			characterPanel.setLayout(new GridLayout(4, 6, 3, 1));
			characterPanel.setOpaque(false);
			
			for (int i = 0; i < ROW; i++) {
			    for (int j = 0; j < COL; j++) {
			        characterButton[i][j] = new javax.swing.JButton();  // Creating a JButton with a randomly selected vowel
			        characterButton[i][j].setPreferredSize(new Dimension(15, 20));
			        characterButton[i][j].setOpaque(true);  // Making the button opaque
			        characterButton[i][j].setContentAreaFilled(false);
			        characterButton[i][j].setBorderPainted(true);  // Removing the button border
			        characterPanel.add(characterButton[i][j]);  // Adding the button to the mainPanel
			    }
			}
			
			//gameButton[i][j].setBackground(Color.decode("#880909"));  // Setting the background color to red to eliminate
			
			add(characterPanel);
			
	        //adding action listeners to buttons
	        homeButton.addActionListener(this);  // Add an action listener to the button
			guessButton.addActionListener(this); // Add an action listener to the button
			askQuestionsButton.addActionListener(this); // Add an action listener to the button
	
			// Set the text of the game screen
			gameScreen.setText("griddisplay");
	
			// Add the game screen to the content pane
			getContentPane().add(gameScreen);
	
			// Set the bounds (position and size) of the game screen
			gameScreen.setBounds(0, 0, 700, 400);
	        pack();
	    }// </editor-fold>//GEN-END:initComponents

    	//method that corresponds to the buttons the user clicks on such as guess, ask question, or home
		public void actionPerformed(ActionEvent event){
		    String command = event.getActionCommand(); // Create action command variable
		    if (command.equals("GUESS")) {
		        numOfGuesses++; // Increment the number of guesses
		        playerMaxNumGuess--; // Decrement the maximum number of guesses allowed
		        int playerGuess = getPlayerGuess(); // Get the player's guess
		        if (!checkGuess(playerGuess) && playerMaxNumGuess == 0) {
		            // Check if the guess is incorrect and the maximum number of guesses is reached
		            printNotCorrectCharacter(); // Display a message for incorrect character guess
		            printYouLose(); // Display a message for losing the game
		            printRevealComputerCharacter(); // Display the computer's character
		            int trueCharacter = enterTrueChar(displayCharacters); // Prompt the user to enter the correct character
		            player1SelectedCharacters = displayCharacters.get(trueCharacter); // Set the player's selected character
		            try {
		                validationQuestions = computerPlayer.validateAnswers(player1SelectedCharacters, questionNumToValidate, userQuestionAnswersValidate, questionList);
		            } catch (Exception e1) {
		                e1.printStackTrace();
		            }
		            printPopUpValidation(); // Display a popup for validation questions
		            printFareWellMessage(); // Display a farewell message
	
		            existsWinner = true; // Mark that there is a winner
		            timeEnd = System.nanoTime(); // Record the end time
		            netTime = timeEnd - timeStart; // Calculate the net time
	
		            // Update leaderboard based on game mode
		            if (chosenMode.equals("Easy")) {
		                player1.setScore(numOfGuesses, player1Name, false, namesEasy, scoreArrayEasy, netTime);
		                try {
		                    player1.updateEasyLeaderboard(player1.getScore(), namesEasy, scoreArrayEasy, player1Name, numOfGuessesEasy, timeEasy, netTime, false, numOfGuesses);
		                } catch (NumberFormatException | IOException e) {
		                    e.printStackTrace();
		                }
		            } else if (chosenMode.equals("Normal")) {
		                player1.setScore(numOfGuesses, player1Name, false, namesNormal, scoreArrayNormal, netTime);
		                try {
		                    player1.updateNormalLeaderboard(player1.getScore(), namesNormal, scoreArrayNormal, player1Name, numOfGuessesNormal, timeNormal, netTime, false, numOfGuesses);
		                } catch (NumberFormatException | IOException e) {
		                    e.printStackTrace();
		                }
		            }
		        
		    
		            else if (chosenMode.equals("Hard")) {
		                // Handle incorrect guess for Hard mode
		                player1.setScore(numOfGuesses, player1Name, false, namesHard, scoreArrayHard, netTime);
		                try {
		                    player1.updateHardLeaderboard(player1.getScore(), namesHard, scoreArrayHard, player1Name, numOfGuessesHard, timeHard, netTime, false, numOfGuesses);
		                } catch (NumberFormatException | IOException e) {
		                    e.printStackTrace();
		                }
		            
					}
				}
		        else if (!checkGuess(playerGuess) && playerMaxNumGuess != 0) {
		            // Marking incorrect guess on the game board
		            for (int i = 0; i < ROW; i++) {
		                for (int j = 0; j < COL; j++) {
		                    // Checking for the button associated with the incorrect character
		                    if (referenceNameToButton[i][j].getName().equals(player1CharactersRemaining.get(playerGuess).getName())) {
		                        // Setting the background color to red to eliminate
		                        characterButton[i][j].setBackground(Color.decode("#880909"));
		                        characterButton[i][j].setOpaque(true);
		                        characterButton[i][j].repaint();
		                    }
		                }
		            }
		            // Printing a message for an incorrect guess
		            printNotCorrectCharacter();
		            // Removing the incorrectly guessed character from the remaining characters
		            player1CharactersRemaining.remove(playerGuess);
		            try {
		                // Starting the game again after an incorrect guess
		                startGame();
		                // Reminding the player of their turn
		                remindPlayerTurn();
		                // METHOD FOR POP UP FOR CHARACTER TO GO (This is a placeholder comment, as the actual method is not specified in the code)
		            } catch (Exception e) {
		                // Handling potential exceptions during game restart
		                e.printStackTrace();
		            }
		        }
		        else if (checkGuess(playerGuess)) {
		            // Printing a message for winning the game
		            printYouWin();
		            // Allowing the user to enter the index of their character from the full character list
		            int trueCharacter = enterTrueChar(displayCharacters);
		            // Setting the player's selected character based on the entered index
		            player1SelectedCharacters = displayCharacters.get(trueCharacter);
		            try {
		                // Validating answers for the player's selected character
		                validationQuestions = computerPlayer.validateAnswers(player1SelectedCharacters, questionNumToValidate, userQuestionAnswersValidate, questionList);
		            } catch (Exception e1) {
		                // Handling potential exceptions during answer validation
		                e1.printStackTrace();
		            }
		            // Printing a pop-up message for answer validation
		            printPopUpValidation();
		            // Printing a farewell message
		            printFareWellMessage();
	
		            if (chosenMode.equals("Easy")) {
		                // Updating the score and leaderboard for the Easy mode after a correct guess
		                player1.setScore(numOfGuesses, player1Name, true, namesEasy, scoreArrayEasy, netTime);
		                try {
		                    player1.updateEasyLeaderboard(player1.getScore(), namesEasy, scoreArrayEasy, player1Name, numOfGuessesEasy, timeEasy, netTime, true, numOfGuesses);
		                } catch (NumberFormatException e) {
		                    // Handling potential exceptions during leaderboard update
		                    e.printStackTrace();
		                } catch (FileNotFoundException e) {
		                    // Handling potential exceptions during leaderboard update
		                    e.printStackTrace();
		                } catch (IOException e) {
		                    // Handling potential exceptions during leaderboard update
		                    e.printStackTrace();
		                }
		            }
		            else if (chosenMode.equals("Normal")) {
		                // Updating the score and leaderboard for the Normal mode after a correct guess
		                player1.setScore(numOfGuesses, player1Name, true, namesNormal, scoreArrayNormal, netTime);
		                try {
		                    player1.updateNormalLeaderboard(player1.getScore(), namesNormal, scoreArrayNormal, player1Name, numOfGuessesNormal, timeNormal, netTime, true, numOfGuesses);
		                } catch (NumberFormatException e) {
		                    // Handling potential exceptions during leaderboard update
		                    e.printStackTrace();
		                } catch (FileNotFoundException e) {
		                    // Handling potential exceptions during leaderboard update
		                    e.printStackTrace();
		                } catch (IOException e) {
		                    // Handling potential exceptions during leaderboard update
		                    e.printStackTrace();
		                }
		            } else if (chosenMode.equals("Hard")) {
		                // Updating the score and leaderboard for the Hard mode after a correct guess
		                player1.setScore(numOfGuesses, player1Name, true, namesHard, scoreArrayHard, netTime);
		                try {
		                    player1.updateHardLeaderboard(player1.getScore(), namesHard, scoreArrayHard, player1Name, numOfGuessesHard, timeHard, netTime, true, numOfGuesses);
		                } catch (NumberFormatException e) {
		                    // Handling potential exceptions during leaderboard update
		                    e.printStackTrace();
		                } catch (FileNotFoundException e) {
		                    // Handling potential exceptions during leaderboard update
		                    e.printStackTrace();
		                } catch (IOException e) {
		                    // Handling potential exceptions during leaderboard update
		                    e.printStackTrace();
		                }
		            }
				}
	
		    }
		    
		    if (command.equals("ASK QUESTION")) {
		        // Asking a question and updating the game state accordingly
		        int questNum = displayQuestion();
		        eliminateCharacterDisplayGameboard(questNum, player1CharactersRemaining, computerSelectedCharacters, player1QuestionsLeft);
		        printComputerAnswerToQuestion(computerAnswersToQuestion, questNum, player1QuestionsLeft);
		        try {
		            startGame();
		            remindPlayerTurn();
		            // Method for user turn
		        } catch (Exception e) {
		            // Handling potential exceptions during game initiation
		            e.printStackTrace();
		        }
		    }
	
		    if (command.equals("HOME")) {
		        try {
		            // Adding a small delay before stopping music
		            Thread.sleep(100);
		        } catch (InterruptedException e) {
		            // Handling potential interruptions during the sleep
		            e.printStackTrace();
		        }
	
		        stopMusicImmediately(); // Stopping the background music
		    	
	
		        try {
		            launchHomeScreen(); // Attempting to launch the home screen
		        } catch (Exception e) {
		            // Handling potential exceptions during the home screen launch
		            e.printStackTrace();
		        }
		    }
		    
		}
	
		/**
		 * Launches the home screen.
		 * @throws Exception if there are issues during the launch.
		 */
		public void launchHomeScreen() throws Exception {
		    dispose(); // Disposes the current frame
		    HomeScreen g = new HomeScreen(); // Creates a new instance of HomeScreen
		    g.setVisible(true); // Sets the home screen to be visible
		}
	    /**
	     * @param args the command line arguments
	     */
	    
	
	   
		/**
		 * Closes the current game by posting a WINDOW_CLOSING event.
		 */
		public void closeGame() {
		    WindowEvent closeWindow = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
		    Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(closeWindow);
		}
	
		/**
		 * Reads settings variables from a text file and assigns them to corresponding fields.
		 * @throws Exception if there are issues during file reading or parsing.
		 */
		public  void writeSettingVariables() throws Exception {
		    ArrayList<String> writeToVariables = new ArrayList<String>();
		    File file = new java.io.File("variableGameBoard.txt"); // Retrieves the file
		    Scanner input = new Scanner(file); // Creates a Scanner variable for file reading
	
		    while (input.hasNext()) {
		        writeToVariables.add(input.next()); // Adds each line to the array list
		    }
	
		    // Parses and assigns values to  variables
		    playerMaxNumGuess = Integer.parseInt(writeToVariables.get(0));
		    musicOffOn = Boolean.parseBoolean(writeToVariables.get(1));
		    chosenMode = writeToVariables.get(2);
		    player1Name = writeToVariables.get(3);
		}
	
	    /*public  void recreateFile() throws Exception {
	    	File file = new java.io.File("variableGameBoard.txt");
	        file.createNewFile();
	    }*/
		/**
		 * Determines the turn order by prompting the user to choose who should move first.
		 * @return 1 if the computer moves first, 2 if the user moves first.
		 */
		public  int getTurnOrder() {
		    // Options to display in the dialog
		    Object[] options = {"Computer to Move First", "User to Move First"};
	
		    // Show the option dialog and get the user's choice
		    int choice = JOptionPane.showOptionDialog(
		            null,
		            "Welcome " + player1Name + "! Please decide who should move first.",
		            "Turn Order",
		            JOptionPane.YES_NO_OPTION,
		            JOptionPane.QUESTION_MESSAGE,
		            null, // Set this to null for the default icon
		            options,
		            options[0]
		    );
	
		    // Return 1 for Computer, 2 for User
		    if (choice == JOptionPane.YES_OPTION) {
		        return 1;
		    } else {
		        return 2;
		    }
	    }
	
	
		/**
		 * Displays a dialog for the user to select their guess from the remaining characters.
		 * @return The index of the selected guess in the list of remaining characters.
		 */
		public  int getPlayerGuess() {
		    JComboBox<String> guessComboBox = new JComboBox<>();
		    guessComboBox.removeAllItems();
	
		    // Populate the combo box with the names of the remaining characters
		    for (Characters character : player1CharactersRemaining) {
		        guessComboBox.addItem(character.getName());
		    }
	
		    JPanel panel = new JPanel();
		    panel.add(new JLabel("Select your guess:"));
		    panel.add(guessComboBox);
	
		    // Show the dialog and get the result
		    int result = JOptionPane.showConfirmDialog(null, panel, "Guess", JOptionPane.OK_CANCEL_OPTION);
	
		    if (result == JOptionPane.OK_OPTION) {
		        // User clicked OK, retrieve the selected guess
		        String playerGuess = guessComboBox.getSelectedItem().toString();
	
		        // Find the index of the selected guess in the list of remaining characters
		        int indexOfPlayerGuess = 0;
		        for (int i = 0; i < player1CharactersRemaining.size(); i++) {
		            if (player1CharactersRemaining.get(i).getName().equals(playerGuess)) {
		                indexOfPlayerGuess = i;
		                break;
		            }
		        }
	
		        return indexOfPlayerGuess;
		    } else {
		        // User canceled the dialog, handle accordingly
		        return -1; // or another suitable value
		    }
	    }
		/**
		 * Checks if the guessed character matches the computer's selected character.
		 * @param playerIndexGuess The index of the guessed character in the list of remaining characters.
		 * @return True if the guessed character matches the computer's selected character, false otherwise.
		 */
		public  boolean checkGuess(int playerIndexGuess) {
		    return computerSelectedCharacters == player1CharactersRemaining.get(playerIndexGuess);
		}
	
		/**
		 * Displays a dialog for the user to select a question from the remaining questions.
		 * @return The index of the selected question in the list of remaining questions.
		 */
		public  int displayQuestion() {
		    JComboBox<String> guessComboBox = new JComboBox<>();
		    guessComboBox.removeAllItems();
	
		    // Populate the combo box with the remaining questions
		    for (int i = 0; i < player1QuestionsLeft.size(); i++) {
		        guessComboBox.addItem(player1QuestionsLeft.get(i));
		    }
	
		    JPanel panel = new JPanel();
		    panel.add(new JLabel("Select your question:"));
		    panel.add(guessComboBox);
	
		    int indexOfSelectedQuestion = 0;
	
		    // Show the dialog and get the result
		    int result = JOptionPane.showConfirmDialog(null, panel, "Guess", JOptionPane.OK_CANCEL_OPTION);
	
		    if (result == JOptionPane.OK_OPTION) {
		        // User clicked OK, retrieve the selected question
		        String playerQuestion = guessComboBox.getSelectedItem().toString();
	
		        // Find the index of the selected question in the list of remaining questions
		        for (int i = 0; i < player1QuestionsLeft.size(); i++) {
		            if (player1QuestionsLeft.get(i).equals(playerQuestion)) {
		                indexOfSelectedQuestion = i;
		                break;
		            }
		        }
	
		        return indexOfSelectedQuestion;
		    }
	
		    // User canceled the dialog, return default value
		    return indexOfSelectedQuestion;
		}
		/**
		 * Starts the game by checking if any characters remain and making decisions for the computer's turn.
		 * @throws Exception Thrown if there is an exception during the game.
		 */
		public  void startGame() throws Exception {
		    timeStart = System.nanoTime();
	
		    // Check if any characters remain in the list
		    if (computerCharactersRemaining.size() == 0) {
		        // Display the full character list and prompt the user to enter the index of their character
		        int trueCharacter = enterTrueChar(displayCharacters);
	
		        // Print an error message when answering
		        printErrorWhenAnswering();
	
		        // Print a message revealing the computer's character
		        printRevealComputerCharacter();
	
		        // Set the selected character for the user
		        player1SelectedCharacters = displayCharacters.get(trueCharacter);
	
		        // Validate the user's answers
		        validationQuestions = computerPlayer.validateAnswers(player1SelectedCharacters, questionNumToValidate, userQuestionAnswersValidate, questionList);
	
		        // Print a pop-up validation message
		        printPopUpValidation();
	
		        // Print a farewell message
		        printFareWellMessage();
		    }
	
		    // Get the computer's decision by checking the characters remaining
		    compDecision = computerPlayer.checkGuessStatus(computerCharactersRemaining);
	
		    // If the computer decides to make a guess
		    if (compDecision == 1) {
		        // Set the index of the last character in the characters list
		        int indexOfCompGuess = 0;
	
		        // Print the computer's guess
		        printComputerGuess(computerCharactersRemaining);
	
		        // Display the full character list and prompt the user to enter the index of their character
		        int trueCharacter = enterTrueChar(displayCharacters);
	
		        // If the computer's guess is correct
		        if (computerCharactersRemaining.get(indexOfCompGuess) == displayCharacters.get(trueCharacter)) {
		            // Print a "You Lose" message
		            printYouLose();
	
		            // Print a message revealing the computer's character
		            printRevealComputerCharacter();
	
		            // Set the selected character for the user
		            player1SelectedCharacters = displayCharacters.get(trueCharacter);
	
		            // Validate the user's answers
		            validationQuestions = computerPlayer.validateAnswers(player1SelectedCharacters, questionNumToValidate, userQuestionAnswersValidate, questionList);
	
		            // Print a pop-up validation message
		            printPopUpValidation();
	
		            // Print a farewell message
		            printFareWellMessage();
		        } else {
		            // Print an error message when answering
		            printErrorWhenAnswering();
	
		            // Print a message revealing the computer's character
		            printRevealComputerCharacter();
	
		            // Set the selected character for the user
		            player1SelectedCharacters = displayCharacters.get(trueCharacter);
	
		            // Validate the user's answers
		            validationQuestions = computerPlayer.validateAnswers(player1SelectedCharacters, questionNumToValidate, userQuestionAnswersValidate, questionList);
	
		            // Print a pop-up validation message
		            printPopUpValidation();
	
		            // Print a farewell message
		            printFareWellMessage();
		        }
		    } 
		   
		 // If the computer's decision is to ask questions
		    else if (compDecision == 2) {
		        // System.out.println("\n\nI AM I COMP DECISION TWO\n\n\n");
		        if (chosenMode.equals("Easy")) {
		            // Generate a random question for the computer in Easy mode
		            computerQuestionAsked = computerPlayer.generateRandomQuestionEasy(computerCharactersRemaining, computerQuestionsLeft, questionIsAskedEasy);
	
		            // Get user answers for the generated question
		            userAnswers = getUserQuestionAnswer(computerQuestionsLeft, computerQuestionAsked, questionNumToValidate, userQuestionAnswersValidate);
	
		            // Perform character elimination for Easy mode
		            computerPlayer.computerEliminateCharactersEasy(computerCharactersRemaining, userAnswers, computerQuestionAsked, computerQuestionsLeft, questionIsAskedEasy);
		        } else if (chosenMode.equals("Normal")) {
		            // Generate a random question for the computer in Normal mode
		            computerQuestionAsked = computerPlayer.generateRandomQuestionNormal(computerCharactersRemaining, computerQuestionsLeft, questionIsAskedNormal);
	
		            // Get user answers for the generated question
		            userAnswers = getUserQuestionAnswer(computerQuestionsLeft, computerQuestionAsked, questionNumToValidate, userQuestionAnswersValidate);
	
		            // Perform character elimination for Normal mode
		            computerPlayer.computerEliminateCharactersNormal(computerCharactersRemaining, userAnswers, computerQuestionAsked, computerQuestionsLeft, questionIsAskedNormal);
		        } else if (chosenMode.equals("Hard")) {
		            // Find an optimal question for the computer in Hard mode
		            computerQuestionAsked = computerPlayer.findOptimalQuestionAI(computerCharactersRemaining, computerQuestionsLeft, questionIsAskedHard);
	
		            // Get user answers for the generated question
		            userAnswers = getUserQuestionAnswer(computerQuestionsLeft, computerQuestionAsked, questionNumToValidate, userQuestionAnswersValidate);
	
		            // Perform character elimination for Hard mode
		            computerPlayer.computerEliminateCharactersHard(computerCharactersRemaining, userAnswers, computerQuestionAsked, computerQuestionsLeft, questionIsAskedHard);
		        }
	
		        // Print the remaining computer characters
		        printComputerCharactersRemaining();
		    }
	
		   }
		/**
		 * Eliminates characters based on the user's answers to a question and displays the updated game board.
		 * @param indexOfPGuess Index of the player's guess.
		 * @param playerCharRemaining ArrayList containing the remaining player characters.
		 * @param computerSelectedCharacters The computer's selected character.
		 * @param player1QuestionsLeft ArrayList containing the remaining questions for the player.
		 */
		public  void eliminateCharacterDisplayGameboard(int indexOfPGuess, ArrayList<Characters> playerCharRemaining, Characters computerSelectedCharacters, ArrayList<String> player1QuestionsLeft) {
		    ArrayList<Integer> removeIndex = new ArrayList<Integer>();
	
		    // Check the condition based on the player's guess
		    if (indexOfPGuess == 0) { // Is the eye color brown?
		        if (computerSelectedCharacters.getEyeColour().equals("Brown")) {
		            for (int i = 0; i < playerCharRemaining.size(); i++) {
		                if (!playerCharRemaining.get(i).getEyeColour().equals("Brown")) {
		                    computerAnswersToQuestion = "Yes";
		                    removeIndex.add(i);
		                }
		            }
		        } else if (!computerSelectedCharacters.getEyeColour().equals("Brown")) {
		            for (int i = 0; i < playerCharRemaining.size(); i++) {
		                if (playerCharRemaining.get(i).getEyeColour().equals("Brown")) {
		                    computerAnswersToQuestion = "No";
		                    removeIndex.add(i);
		                }
		            }
		        }
		    } else if (indexOfPGuess == 1) { // Is the eye color blue?
		        if (computerSelectedCharacters.getEyeColour().equals("Blue")) {
		            for (int i = 0; i < playerCharRemaining.size(); i++) {
		                if (!playerCharRemaining.get(i).getEyeColour().equals("Blue")) {
		                    computerAnswersToQuestion = "Yes";
		                    removeIndex.add(i);
		                }
		            }
		        } else if (!computerSelectedCharacters.getEyeColour().equals("Blue")) {
		            for (int i = 0; i < playerCharRemaining.size(); i++) {
		                if (playerCharRemaining.get(i).getEyeColour().equals("Blue")) {
		                    computerAnswersToQuestion = "No";
		                    removeIndex.add(i);
		                }
		            }
		        }
		     }
		    if(indexOfPGuess == 2){ //
		        if(computerSelectedCharacters.getGender().equals("Male")){
		            for(int i = 0; i < playerCharRemaining.size(); i++){
		                    if(!playerCharRemaining.get(i).getGender().equals("Male")){
		                    	computerAnswersToQuestion = "Yes";
		                        removeIndex.add(i);
		                        
		                    }
		            }
		        }
		        else if(!computerSelectedCharacters.getGender().equals("Male")){
		            for(int i = 0; i < playerCharRemaining.size(); i++){
		                    if(playerCharRemaining.get(i).getGender().equals("Male")){
		                    	computerAnswersToQuestion = "No";
		                        removeIndex.add(i);
		                    }
		            }
		        }
		    }
		    if(indexOfPGuess == 3){ //
		        if(computerSelectedCharacters.getSkinTone().equals("Light")){
		            for(int i = 0; i < playerCharRemaining.size(); i++){
		                    if(!playerCharRemaining.get(i).getSkinTone().equals("Light")){
		                    	computerAnswersToQuestion = "Yes";
		                        removeIndex.add(i);
		                    }
		            }
		        }
		        else if(!computerSelectedCharacters.getSkinTone().equals("Light")){
		            for(int i = 0; i < playerCharRemaining.size(); i++){
		                    if(playerCharRemaining.get(i).getSkinTone().equals("Light")){
		                    	computerAnswersToQuestion = "No";
		                        removeIndex.add(i);
		                    }
		            }
		        }
		    }
		    if(indexOfPGuess == 4){ //
		        if(computerSelectedCharacters.getHairColour().equals("Black")){
		            for(int i = 0; i < playerCharRemaining.size(); i++){
		                    if(!playerCharRemaining.get(i).getHairColour().equals("Black")){
		                    	computerAnswersToQuestion = "Yes";
		                        removeIndex.add(i);
		                    }
		            }
		        }
		        else if(!computerSelectedCharacters.getHairColour().equals("Black")){
		            for(int i = 0; i < playerCharRemaining.size(); i++){
		                    if(playerCharRemaining.get(i).getHairColour().equals("Black")){
		                    	computerAnswersToQuestion = "No";
		                        removeIndex.add(i);
		                    }
		            }
		        }
		    }
		    if(indexOfPGuess == 5){ //
		        if(computerSelectedCharacters.getHairColour().equals("Brown")){
		            for(int i = 0; i < playerCharRemaining.size(); i++){
		                    if(!playerCharRemaining.get(i).getHairColour().equals("Brown")){
		                    	computerAnswersToQuestion = "Yes";
		                        removeIndex.add(i);
		                    }
		            }
		        }
		        else if(!computerSelectedCharacters.getHairColour().equals("Brown")){
		            for(int i = 0; i < playerCharRemaining.size(); i++){
		                    if(playerCharRemaining.get(i).getHairColour().equals("Brown")){
		                    	computerAnswersToQuestion = "No";
		                        removeIndex.add(i);
		                    }
		            }
		        }
		    }
		    if(indexOfPGuess == 6){ //
		        if(computerSelectedCharacters.getHairColour().equals("Blonde")){
		            for(int i = 0; i < playerCharRemaining.size(); i++){
		                    if(!playerCharRemaining.get(i).getHairColour().equals("Blonde")){
		                    	computerAnswersToQuestion = "Yes";
		                        removeIndex.add(i);
		                    }
		            }
		        }
		        else if(!computerSelectedCharacters.getHairColour().equals("Blonde")){
		            for(int i = 0; i < playerCharRemaining.size(); i++){
		                    if(playerCharRemaining.get(i).getHairColour().equals("Blonde")){
		                    	computerAnswersToQuestion = "No";
		                        removeIndex.add(i);
		                    }
		            }
		        }
		    }
		    if(indexOfPGuess == 7){ //
		        if(computerSelectedCharacters.getHairColour().equals("White")){
		            for(int i = 0; i < playerCharRemaining.size(); i++){
		                    if(!playerCharRemaining.get(i).getHairColour().equals("White")){
		                    	computerAnswersToQuestion = "Yes";
		                        removeIndex.add(i);
		                    }
		            }
		        }
		        else if(!computerSelectedCharacters.getHairColour().equals("White")){
		            for(int i = 0; i < playerCharRemaining.size(); i++){
		                    if(playerCharRemaining.get(i).getHairColour().equals("White")){
		                    	computerAnswersToQuestion = "No";
		                        removeIndex.add(i);
		                    }
		            }
		        }
		    }
		    if(indexOfPGuess == 8){ //
		        if(computerSelectedCharacters.getFacialHair()){
		            for(int i = 0; i < playerCharRemaining.size(); i++){
		                    if(!playerCharRemaining.get(i).getFacialHair()){
		                    	computerAnswersToQuestion = "Yes";
		                        removeIndex.add(i);
		                    }
		            }
		        }
		        else if(!computerSelectedCharacters.getFacialHair()){
		            for(int i = 0; i < playerCharRemaining.size(); i++){
		                    if(playerCharRemaining.get(i).getFacialHair()){
		                    	computerAnswersToQuestion = "No";
		                        removeIndex.add(i);
		                    }
		            }
		        }
		    }
		    if(indexOfPGuess == 9){ //
		        if(computerSelectedCharacters.getGlasses()){
		            for(int i = 0; i < playerCharRemaining.size(); i++){
		                    if(!playerCharRemaining.get(i).getGlasses()){
		                    	computerAnswersToQuestion = "Yes";
		                        removeIndex.add(i);
		                    }
		            }
		        }
		        else if(!computerSelectedCharacters.getGlasses()){
		            for(int i = 0; i < playerCharRemaining.size(); i++){
		                    if(playerCharRemaining.get(i).getGlasses()){
		                    	computerAnswersToQuestion = "No";
		                        removeIndex.add(i);
		                    }
		            }
		        }
		    }
		    if(indexOfPGuess == 10){ //
		        if(computerSelectedCharacters.getTeeth()){
		            for(int i = 0; i < playerCharRemaining.size(); i++){
		                    if(!playerCharRemaining.get(i).getTeeth()){
		                    	computerAnswersToQuestion = "Yes";
		                        removeIndex.add(i);
		                    }
		            }
		        }
		        else if(!computerSelectedCharacters.getTeeth()){
		            for(int i = 0; i < playerCharRemaining.size(); i++){
		                    if(playerCharRemaining.get(i).getTeeth()){
		                    	computerAnswersToQuestion = "No";
		                        removeIndex.add(i);
		                    }
		            }
		        }
		    }
		    if(indexOfPGuess == 11){ //
		        if(computerSelectedCharacters.getHat()){
		            for(int i = 0; i < playerCharRemaining.size(); i++){
		                    if(!playerCharRemaining.get(i).getHat()){
		                    	computerAnswersToQuestion = "Yes";
		                        removeIndex.add(i);
		                    }
		            }
		        }
		        else if(!computerSelectedCharacters.getHat()){
		            for(int i = 0; i < playerCharRemaining.size(); i++){
		                    if(playerCharRemaining.get(i).getHat()){
		                    	computerAnswersToQuestion = "No";
		                        removeIndex.add(i);
		                    }
		            }
		        }
		    }
		    if(indexOfPGuess == 12){ //
		        if(computerSelectedCharacters.getHairLength().equals("Short")){
		            for(int i = 0; i < playerCharRemaining.size(); i++){
		                    if(!playerCharRemaining.get(i).getHairLength().equals("Short")){
		                    	computerAnswersToQuestion = "Yes";
		                        removeIndex.add(i);
		                    }
		            }
		        }
		        else if(!computerSelectedCharacters.getHairLength().equals("Short")){
		            for(int i = 0; i < playerCharRemaining.size(); i++){
		                    if(playerCharRemaining.get(i).getHairLength().equals("Short")){
		                    	computerAnswersToQuestion = "No";
		                        removeIndex.add(i);
		                    }
		            }
		        }
		    }
		    if(indexOfPGuess == 13){ //
		        if(computerSelectedCharacters.getHairLength().equals("Tied")){
		            for(int i = 0; i < playerCharRemaining.size(); i++){
		                    if(!playerCharRemaining.get(i).getHairLength().equals("Tied")){
		                    	computerAnswersToQuestion = "Yes";
		                        removeIndex.add(i);
		                    }
		            }
		        }
		        else if(!computerSelectedCharacters.getHairLength().equals("Tied")){
		            for(int i = 0; i < playerCharRemaining.size(); i++){
		                    if(playerCharRemaining.get(i).getHairLength().equals("Tied")){
		                    	computerAnswersToQuestion = "No";
		                        removeIndex.add(i);
		                    }
		            }
		        }
		    }
		    if(indexOfPGuess == 14){ //
		        if(computerSelectedCharacters.getHairLength().equals("Long")){
		            for(int i = 0; i < playerCharRemaining.size(); i++){
		                    if(!playerCharRemaining.get(i).getHairLength().equals("Long")){
		                    	computerAnswersToQuestion = "Yes";
		                        removeIndex.add(i);
		                    }
		            }
		        }
		        else if(!computerSelectedCharacters.getHairLength().equals("Long")){
		            for(int i = 0; i < playerCharRemaining.size(); i++){
		                    if(playerCharRemaining.get(i).getHairLength().equals("Long")){
		                    	computerAnswersToQuestion = "No";
		                        removeIndex.add(i);
		                    }
		            }
		        }
		    }
		    
		    if(indexOfPGuess == 15){ //
		        if(computerSelectedCharacters.getPiercings()){
		            for(int i = 0; i < playerCharRemaining.size(); i++){
		                    if(!playerCharRemaining.get(i).getPiercings()){
		                    	computerAnswersToQuestion = "Yes";
		                        removeIndex.add(i);
		                    }
		            }
		        }
		        else if(!computerSelectedCharacters.getPiercings()){
		            for(int i = 0; i < playerCharRemaining.size(); i++){
		                    if(playerCharRemaining.get(i).getPiercings()){
		                    	computerAnswersToQuestion = "No";
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
			//this for loop will paste and make the visual display of each eliminated character
			// for the user red. That way, user will know that it has been taken
			for(int i = 0; i < ROW; i++) {
				for(int j = 0; j < COL; j++) {
					for(int k = 0; k < removeIndex.size(); k++) {
						////System.out.println("this is row: " + i + " and column: " + j + " The referenceName is: " + referenceNameToButton[i][j].getName() + " and the player going to be eliminate is: " + playerCharRemaining.get(removeIndex.get(k)).getName());
						if(referenceNameToButton[i][j].getName().equals(playerCharRemaining.get(removeIndex.get(k)).getName())) {
							characterButton[i][j].setBackground(Color.decode("#880909"));  // Setting the background color to red to eliminate
							characterButton[i][j].setOpaque(true);
							characterButton[i][j].repaint();
						}
					}
				}
			}
			
			//Remove players that don't match the answer from the player characters remaining
			for (int i = 0 ; i < removeIndex.size(); i++) {
				int index = removeIndex.get(i);
				playerCharRemaining.remove(index); //remove characters from playerChar arraylist
			}
		}
	
	
		// Entry point of the program
		public  void main(String[] args) throws Exception {
		    // Create a new instance of the GameBoard class
		    new GameBoard(); 
		}
	
		// Method to read questions from a file and populate an ArrayList
		public  void writeQuestionArrayList(ArrayList<String> questionBank) throws Exception {
		    // Specify the file path to read from
		    File file = new java.io.File("QuestionBank.txt");
		    
		    // Create a Scanner object to read from the file
		    Scanner input = new Scanner(file);
		    
		    // Iterate through each line in the file
		    while (input.hasNextLine()) {
		        // Read the current line from the file
		        String line = input.nextLine();
		        
		        // Add the current line to the questionBank ArrayList
		        questionBank.add(line);
		    }
		}
		// Method to clear and refill the lists of questions for both the player and the computer
		public  void writeUserAndComputerQuestions() throws Exception {
		    // Clear the lists containing questions for player 1 and the computer
		    player1QuestionsLeft.clear();
		    computerQuestionsLeft.clear();
		    
		    // Iterate through the questionList and add each question to both player1QuestionsLeft and computerQuestionsLeft
		    for (int i = 0; i < questionList.size(); i++) {
		        player1QuestionsLeft.add(questionList.get(i));
		        computerQuestionsLeft.add(questionList.get(i));
		    }
		}
		// Method to clear and refill the lists of characters for both the player and the computer
		public  void fillUserAndComputerCharacters() {
		    // Iterate through the list of characters remaining for computer (optional commented debug lines)
		    for(Characters i : computerCharactersRemaining) {
		        // System.out.print("Here is the list of all characters remaining before the CLEAR: " + i.getName());
		    }
		    
		    // Clear the lists containing characters for player 1 and the computer
		    player1CharactersRemaining.clear();
		    computerCharactersRemaining.clear();
		    
		    // Iterate through the list of characters remaining for computer after clearing (optional commented debug lines)
		    for(Characters i : computerCharactersRemaining) {
		        // System.out.print("Here is the list of all characters remaining after the CLEAR: " + i.getName());
		    }
		    
		    // Iterate through the displayCharacters list and add each character to both player1CharactersRemaining and computerCharactersRemaining
		    for(int i = 0; i < displayCharacters.size(); i++) {
		        player1CharactersRemaining.add(displayCharacters.get(i));
		        computerCharactersRemaining.add(displayCharacters.get(i));
		    }
		    
		    // Iterate through the entire list of computer characters remaining (optional commented debug lines)
		    for(Characters i : computerCharactersRemaining) {
		        // System.out.println("Player name is: " + i.getName());
		    }
		}
		// Method to randomly select a character for the computer from the displayCharacters list
		public  Characters pickComputerCharacters(Characters computerSelectedCharacters, ArrayList<Characters> displayCharacters) throws Exception {
		    // Generate a random index to select a character from the displayCharacters list
		    int randomCharactersNumber = (int) (Math.random() * 24);
		    
		    // Assign the selected character to the computerSelectedCharacters variable
		    computerSelectedCharacters = displayCharacters.get(randomCharactersNumber);
		    
		    // Optional debug lines to print the selected computer character
		    // System.out.println("The computer has selected: " + computerSelectedCharacters.getName());
		    // System.out.println("----------------------------------------------------");
		    
		    return computerSelectedCharacters;
		}
	
		// Method to prompt the user to enter the index of their selected character
		public  int enterTrueChar(ArrayList<Characters> displayCharacters) {
		    // Create an array of options for the dialog
		    Object[] options = displayCharacters.toArray();
	
		    // Display the options in a formatted string
		    StringBuilder message = new StringBuilder("Select the index of your selected character:\n");
		    for (int i = 0; i < displayCharacters.size(); i++) {
		        message.append(i).append(": ").append(displayCharacters.get(i).getName()).append("\n");
		    }
	
		    int index = -1;
	
		    // Prompt the user for the index using a text field
		    String input = JOptionPane.showInputDialog(
		            null,
		            message.toString(),
		            "Enter True Character",
		            JOptionPane.QUESTION_MESSAGE
		    );
		    
		    // Convert the user input to an integer and return the index
		    index = Integer.parseInt(input);
		    return index;
		}
		// Method to display an error message when the player answers a question incorrectly
		public  void printErrorWhenAnswering() {
		    String errorMessage = "Error!!! You answered some question incorrectly. We cannot continue the game further. You have lost the game!";
	
		    // Display the error message in a popup dialog with an error icon
		    JOptionPane.showMessageDialog(
		            null,
		            errorMessage,
		            "Error When Answering",
		            JOptionPane.ERROR_MESSAGE
		    );
		}
	
		// Method to display a message when the player loses the game
		public  void printYouLose() {
		    String errorMessage = player1Name + ", you have lost the game!";
	
		    // Display the message in a popup dialog with an error icon
		    JOptionPane.showMessageDialog(
		            null,
		            errorMessage,
		            "Game Lost",
		            JOptionPane.ERROR_MESSAGE
		    );
		}
	
		// Method to display a message when the player wins the game
		public  void printYouWin() {
		    String errorMessage = player1Name + ", you have won the game!";
	
		    // Display the message in a popup dialog with an error icon
		    JOptionPane.showMessageDialog(
		            null,
		            errorMessage,
		            "Game Won",
		            JOptionPane.ERROR_MESSAGE
		    );
		}
		//method to display pop up for computer guess
	   public  void printComputerGuess(ArrayList<Characters> computerCharactersRemaining) {
	        String errorMessage = "The computer guessed that your character is: " + computerCharactersRemaining.get(0).getName();

	        // Display the error message in a popup dialog
	        JOptionPane.showMessageDialog(
	                null,
	                errorMessage,
	                "Computer Guess",
	                JOptionPane.ERROR_MESSAGE
	        );
	    }
	   //method to display pop up for the computer character that it chose
	   public  void printRevealComputerCharacter() {
		   String errorMessage = "The computer's hidden character was: " + computerSelectedCharacters.getName();

	        // Display the error message in a popup dialog
	        JOptionPane.showMessageDialog(
	                null,
	                errorMessage,
	                "Computer Character Reveal",
	                JOptionPane.ERROR_MESSAGE
	        );
	   }
	   //method to display pop up for question validation
	   public  void printPopUpValidation () {
		   //System.out.println(validationQuestions);
		   String message = "";
		   for (int i = 0; i < validationQuestions.size();i++) {
			   message += validationQuestions.get(i);
			   message += '\n';
		   }
	        // Display the error message in a popup dialog
	        JOptionPane.showMessageDialog(
	                null,
	                message,
	                "Question Validation",
	                JOptionPane.ERROR_MESSAGE
	        );
	   }
	   //method to display farewell message
	   public  void printFareWellMessage () {
	        String message = player1Name + ", thank you for playing Guess Who! Press the home button to restart the game!";

	        // Display the error message in a popup dialog
	        JOptionPane.showMessageDialog(
	                null,
	                message,
	                "Question Validation",
	                JOptionPane.ERROR_MESSAGE
	        );
	   }
	   //method to display not correct character
	   public  void printNotCorrectCharacter() {
	        String message = "The Guess is Incorrect! You have " + playerMaxNumGuess + " guess(es) left!" ;

	        // Display the error message in a popup dialog
	        JOptionPane.showMessageDialog(
	                null,
	                message,
	                "Inncorrect Guess",
	                JOptionPane.ERROR_MESSAGE
	        );
	   }
	   
	   //method to get user question answer
	   public  String getUserQuestionAnswer(ArrayList<String> questionsLeft, int questionNum,
               ArrayList<Integer> questionsAskedByComputer,
               ArrayList<String> answersToQuestions) throws Exception {
				String questionText = questionsLeft.get(questionNum);
				String questionAsked = "The computer would like to ask: " + questionText
				+ '\n' + "Please answer with yes/no based on your character's attributes.";
				
				// Show the popup dialog with Yes and No buttons
				int option = JOptionPane.showOptionDialog(null, questionAsked, "Question",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
				
				String userAnswer;
				if (option == JOptionPane.YES_OPTION) {
					userAnswer = "y";
				} 
				else if (option == JOptionPane.NO_OPTION) {
					userAnswer = "n";
				} 
				else {
					throw new Exception("Invalid option selected");
				}
				
				// Update lists and return userAnswer
				questionsAskedByComputer.add(questionNum);
				answersToQuestions.add(userAnswer);
				questionsLeft.set(questionNum, "Already Asked");
				return userAnswer;
	   }
	   //method to reference 2d array to the array for the characters in the jpg
	   public  void fillReferenceButton () {
		   int index = 0;
		   for (int i = 0; i < ROW; i ++ ) {
			   for (int j = 0; j < COL ; j ++) {
				   referenceNameToButton [i][j] = displayCharacters.get(index);
				   index ++;
			   }
		   }
	   }
	   //method for pop up to remind the player that it is his/her turn
	   public  void remindPlayerTurn() {
		String reminderMessage = "It's your turn!";

		 // Show the reminder popup
		 JOptionPane.showMessageDialog(null, reminderMessage, "Reminder", JOptionPane.INFORMATION_MESSAGE);
	 	}
	   //method to remind player how many characters the AI has remaining
	   public  void printComputerCharactersRemaining() {
	        String message =  "HEADS UP! The Computer has: " + computerCharactersRemaining.size() + " of possible characters left to guess."
	        		 + '\n' + "AI is on: " + chosenMode;

	        // Display the error message in a popup dialog
	        JOptionPane.showMessageDialog(
	                null,
	                message,
	                "AI Characters Left",
	                JOptionPane.ERROR_MESSAGE
	        );

	    }
	   //method to print computer answer to question
	   public  void printComputerAnswerToQuestion(String questionAnswer, int questionNum, ArrayList<String>userQuestionsRemaining) {
	       //System.out.println(userQuestionsRemaining); 
		   String message =  "The computer has responded, <" + questionAnswer + "> to your question (" + userQuestionsRemaining.get(questionNum) + ")"
	        		 + '\n' + "Your gameboard has been updated with the list of potential characters to guess from.";

	        // Display the error message in a popup dialog
	        JOptionPane.showMessageDialog(
	                null,
	                message,
	                "Question Answers",
	                JOptionPane.ERROR_MESSAGE
	        );
		    player1QuestionsLeft.set(questionNum, "Already Asked");

	    }
	   //ethod to start music
	   public  void playMusic(String filePath) {
		    //System.out.println("Starting music playback...");

		    if (filePath == null || filePath.isEmpty()) {
		        //System.out.println("Invalid file path.");
		        return;
		    }

		    File musicFile = new File(filePath);

		    if (!musicFile.exists() || !musicFile.isFile()) {
		        //System.out.println("Invalid file path.");
		        return;
		    }

		    musicThread = new Thread(() -> {
		        try {
		            //System.out.println("Opening audio stream...");

		            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(musicFile);
		            AudioFormat audioFormat = audioInputStream.getFormat();

		            DataLine.Info dataLineInfo = new DataLine.Info(SourceDataLine.class, audioFormat);
		            SourceDataLine sourceDataLine = (SourceDataLine) AudioSystem.getLine(dataLineInfo);
		            sourceDataLine.open(audioFormat);
		            sourceDataLine.start();

		            byte[] buffer = new byte[4096];
		            int bytesRead = 0;

		            while (musicOffOn && (bytesRead = audioInputStream.read(buffer)) != -1) {
		                sourceDataLine.write(buffer, 0, bytesRead);

		                // Check if the thread has been interrupted
		                if (Thread.interrupted()) {
		                    //System.out.println("Music playback interrupted.");
		                    break;
		                }
		            }

		            sourceDataLine.drain();
		            sourceDataLine.close();
		            audioInputStream.close();

		            //System.out.println("Music playback finished.");
		        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
		            e.printStackTrace();
		            System.err.println("Error playing music");
		        }
		    });

		    musicThread.start();
		}
	   //method to stop music
	   public  void stopMusicImmediately() {
		    musicOffOn = false;  // Set the flag to stop the music in the playMusic method
		    if (musicWorker != null && !musicWorker.isDone()) {
		        musicWorker.cancel(true);  // Cancel the SwingWorker to stop the music
		    }
		    //System.out.println("Music stopped immediately.");
		}

}//end of GameBoard