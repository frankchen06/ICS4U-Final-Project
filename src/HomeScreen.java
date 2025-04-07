/*===================================================================
Program Name: HomeScreen.java (Frontend + Backend Code)
Author: William Zhang, Frank Chen, Juliana Zhu
Date: January 13th, 2024
Programming Language & Version Number: Java - Neon.la Release (4.6.1)
=====================================================================
Problem Definition: 
-> Welcoming the user to the game and preparing for game play by providing instructions and various options for settings

Input - User prompted for various actions in the program
-> User can choose between making a maximum of 1, 2, or 3 guesses during the game play
-> User can choose to turn music on or off during the game play
-> User can choose for the computer to be on easy, medium, or hard mode during the game play
-> User can choose to see instructions on how to play the game
-> User can choose to start playing the game whenever they want
-> User is prompted to enter their name after choosing to start the game
-> User can choose to view the leader board

Output - User is displayed various panels and pop-ups in the program
-> Panel with step by step instructions on how to play the game is displayed
-> Panel prompting user to enter their name is displayed
-> Settings panel is displayed with drop-down menus for guesses, music, and computer modes
-> Leader board panel is displayed with times and scores

Process - Take the user's input and process to display an output
-> User's input is taken and processed to display corresponding outputs in the form of pop-up panels
-> User's name is taken and processed to later be used when storing results for the leader board
-> User's input on maximum number of guesses is taken and processed in the game play
-> User's input on whether music is turned on or off is taken and processed in the game play
-> User's input on what mode the computer should play on is taken and processed in the game play
===================================================================
List of Identifiers 
 -Let playerMaxNumGuess type int that is the variable for the max number of player guesses
 -Let musicOffOn type boolean that sets if the music is on or off for the game
 -Let chosenMode be type String that sets the diffculty mode of the game
 -Let player1Name be type String that is the variable for the player that plays the game
 -Let namesEasy be a type String array that sets as the leaderboard for the top 10 easy mode winners
 -Let namesNormal be a type String array that sets as the leaderboard for the top 10 normal mode winners
 -Let namesHard be a type String array that sets as the leaderboard for the top 10 hard mode winners
 -Let scoresArrayEasy be type a double array that carries the score for the easy leaderboard textfile
 -Let scoresArrayNormal be type a double array that carries the score for the normal leaderboard textfile
 -Let scoresArrayHard be type a double array that carries the score for the hard leaderboard textfile
 -Let numOfGuessesHard be an int array of the number of guesses it took for the leaderboard
 -Let numOfGuessesMedium be an int array of the number of guesses it took for the leaderboard
 -Let numOfGuessesEasy be an int array of the number of guesses it took for the leaderboard
 -Let timesEasy be the time take to finish the game for easy mode on the leaderboard
 -Let timesNormal be the time take to finish the game for normal mode on the leaderboard
 -Let timesHard be the time take to finish the game for hard mode on the leaderboard
 -Let homeScreen be the Jlabel for the entire homescreen GUI display
 -Let intstructionalsButton be a Jbutton that when clicked displays the instructions for the game
 -Let playButton be a Jbutton that when clicked plays the game and displays the gameboard
 -Let leaderBoardButton be a Jbutton that when clicked shows the rankings for the game 
 -Let settingsButton be a Jbutton that when clicked allows the user to pick out the various settings for the game
===================================================================*/

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import javax.swing.*;//Import the library
import java.awt.*;//Import the library
import java.awt.event.*;//Import the library
import java.io.File;//Import the library
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;//Import the library
import java.util.Scanner;//Import the library
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import java.awt.event.WindowEvent;

//this homescreen will be the main class where everything is called 
//instructions, settings, and leaderboards will be pop-ups for the user
//to play the game, the user will be taken to the gameboard class (object to be created for gameboard class in HomeScreen.java)

public class HomeScreen extends JFrame implements ActionListener{//START OF GUI2DAssignment 
	// Player-related identifiers
	int playerMaxNumGuess = 1;
	boolean musicOffOn = true;
	String chosenMode = "Hard";
    String player1Name = "";

	// Arrays for Easy mode
	String[] namesEasy = new String[10];
	double[] scoreArrayEasy = new double[10];
	int[] numOfGuessesEasy = new int[10];
	double[] timeEasy = new double[10];

	// Arrays for Normal mode
	String[] namesNormal = new String[10];
	double[] scoreArrayNormal = new double[10];
	int[] numOfGuessesNormal = new int[10];
	double[] timeNormal = new double[10];

	// Arrays for Hard mode
	String[] namesHard = new String[10];
	double[] scoreArrayHard = new double[10];
	int[] numOfGuessesHard = new int[10];
	double[] timeHard = new double[10];

	// GUI components
	JLabel homeScreen = new JLabel();
	JButton instructionsButton = new JButton("INSTRUCTIONS"); // DECLARE instructions BUTTON (1)
	JButton playButton = new JButton("PLAY"); // DECLARE play BUTTON (2)
	JButton leaderBoardButton = new JButton("RANKINGS"); // DECLARE rankings BUTTON (3)
	JButton settingsButton = new JButton("SETTINGS"); // DECLARE settings BUTTON (4)

	/**
	 * Constructor to store all the information and start up the GUI
	 * @throws Exception
	 */
	//constructor for  HomeScreen
	public HomeScreen() throws Exception {
		setSize(710,430);
		setTitle("Guess Who Final Project");
		//settings cords for instructions button
		instructionsButton.setBackground(new java.awt.Color(136, 9, 9));
        instructionsButton.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        instructionsButton.setForeground(new java.awt.Color(255, 255, 255));
        instructionsButton.setText("INSTRUCTIONS");
        instructionsButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 3, true));
        getContentPane().add(instructionsButton);
        instructionsButton.setBounds(13, 20, 160, 38);
        
        //setting cords for playButton on homescreen
        playButton.setBackground(new java.awt.Color(136, 9, 9));
        playButton.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        playButton.setForeground(new java.awt.Color(255, 255, 255));
        playButton.setText("PLAY");
        playButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 3, true));
        getContentPane().add(playButton);
        playButton.setBounds(70, 302, 77, 75);
        
        //setting cords for leaderBoardButton
        leaderBoardButton.setBackground(new java.awt.Color(136, 9, 9));
        leaderBoardButton.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        leaderBoardButton.setForeground(new java.awt.Color(255, 255, 255));
        leaderBoardButton.setText("RANKINGS");
        leaderBoardButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 3, true));
        getContentPane().add(leaderBoardButton);
        leaderBoardButton.setBounds(560, 302, 77, 75);
        
        //setting cords for settingsButton
        settingsButton.setBackground(new java.awt.Color(136, 9, 9));
        settingsButton.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        settingsButton.setForeground(new java.awt.Color(255, 255, 255));
        settingsButton.setText("SETTINGS");
        settingsButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 3, true));
        getContentPane().add(settingsButton);
        settingsButton.setBounds(614, 80, 70, 44);
        
        
        //adding action listeners to buttons
        instructionsButton.addActionListener(this);  // Add an action listener to the button
		playButton.addActionListener(this); // Add an action listener to the button
		leaderBoardButton.addActionListener(this); // Add an action listener to the button
		settingsButton.addActionListener(this);
		
		
		//for homescreen image
        homeScreen.setText("BackgroundImage");
        getContentPane().add(homeScreen);
        homeScreen.setBounds(0, 0, 700, 400);
		backGroundImage();
		setVisible(true); 
        
	}
	/**
	 * Sets the background image for the home screen.
	 *
	 * @throws Exception if there is an issue loading or processing the image.
	 */
	public void backGroundImage() throws Exception{
		//sets the background image for the homescreen
		ImageIcon icon = new ImageIcon("HomePage.jpg");
        Image img = icon.getImage();
        Image imgScale = img.getScaledInstance(homeScreen.getWidth(), homeScreen.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(imgScale);
        homeScreen.setIcon(scaledIcon);
	}
    //Interaction section
	/**
	 * actionPerformed method to check for action performed
	 * @param ActionEvent event - The variable used to get the event
	 * @return void
	 */
	public void actionPerformed(ActionEvent event){
		String command = event.getActionCommand();//Create action command variable
	    if (command.equals("INSTRUCTIONS")) {
	    	showInstructionsPopup();
	    }
	    else if (command.equals("SETTINGS")) {
	    	showSettingsPopup(playerMaxNumGuess, musicOffOn, chosenMode);
	    }
	    else if (command.equals("PLAY")) {
	    	try {
				getPlayerNamePopup();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    else if(command.equals("RANKINGS")) {
	    	try {
				showLeaderboardPopup();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    
	}
	/**
	 * Displays a pop-up window containing leaderboards for Easy, Normal, and Hard modes.
	 *
	 * @throws Exception if there is an issue displaying the leaderboard pop-up.
	 */
	private void showLeaderboardPopup() throws Exception {
        // Create an array to store leaderboard information for each mode
     //   String[] easyLeaderboard = {"Player1", "Player2", "Player3", "asdfadfs", "asdfasdfasdf", "asdfasdfad", "asdfasdf", "asdfasdfasdf", "asdfasdfad", "asdfasdf", "asdfasdfasdf", "asdfasdfad", "asdfasdf"};
      //  String[] mediumLeaderboard = {"Player4", "Player5", "Player6"};
       // String[] hardLeaderboard = {"Player7", "Player8", "Player9"};

        // Create a panel with a tabbed pane to display leaderboards for each mode
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Easy", createEasyLeaderboardPanel());//add tabs
        tabbedPane.addTab("Normal", createNormalLeaderboardPanel());//add tabs
        tabbedPane.addTab("Hard", createHardLeaderboardPanel());//add tabs


        // Create a panel to hold the tabbed pane and close button
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(tabbedPane, BorderLayout.CENTER);

        // Show the leaderboard pop-up
        JOptionPane.showMessageDialog(this, panel, "Leaderboard [TOP 10]", JOptionPane.PLAIN_MESSAGE);
    }
	/**
	 * Creates a JScrollPane containing the leaderboard panel for the Easy mode.
	 *
	 * @return JScrollPane containing the Easy mode leaderboard panel.
	 * @throws IOException if there is an issue reading or writing the leaderboard file.
	 */
	private JScrollPane createEasyLeaderboardPanel() throws IOException {
	    JPanel leaderboardPanel = new JPanel(new GridLayout(0, 1)); // Single column, multiple rows
	    int verticalSpacing = 10; // Adjust the spacing value as needed

	    //-------------------------------------------------------------------------
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
			//System.out.println("NAME\tSCORE");
			//System.out.println("-------------------------------------------");
			for(int i = 0; i < 10; i++) {
	            //System.out.println(namesEasy[i] + "\t" + scoreArrayEasy[i]);
	        }
			String[] columnNames = {"Name", "Score", "Guesses", "Time"};
		    Object[][] rowData = new Object[namesEasy.length][4];

		    for (int i = 0; i < namesEasy.length; i++) {
		        rowData[i][0] = namesEasy[i];
		        rowData[i][1] = scoreArrayEasy[i];
		        rowData[i][2] = numOfGuessesEasy[i];
		        rowData[i][3] = timeEasy[i];
		    }

		    JTable leaderboardTable = new JTable(rowData, columnNames);
		    JScrollPane scrollPane = new JScrollPane(leaderboardTable);


	    // Wrap the leaderboard panel in a JScrollPane
	    //JScrollPane scrollPane = new JScrollPane(leaderboardPanel);

	    // Set up scrolling policies as needed
	   // scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	    //scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

	    // Optionally set the preferred size of the scroll pane
	    scrollPane.setPreferredSize(new Dimension(300, 200));

	    return scrollPane;
	}
	/**
	 * Creates a JScrollPane containing the leaderboard panel for the Normal mode.
	 *
	 * @return JScrollPane containing the Easy mode leaderboard panel.
	 * @throws IOException if there is an issue reading or writing the leaderboard file.
	 */
	private JScrollPane createNormalLeaderboardPanel() throws IOException {
	    JPanel leaderboardPanel = new JPanel(new GridLayout(0, 1)); // Single column, multiple rows
	    int verticalSpacing = 10; // Adjust the spacing value as needed

	    //-------------------------------------------------------------------------
		   File myObjNormal = new File("NormalModeLeaderboard.txt"); //creating a new text file
			if (myObjNormal.createNewFile()){ //if text file is created, do does the following below
				FileWriter myWriter1 = new FileWriter("NormalModeLeaderboard.txt"); //writing in the text file

				//preloaded users
				myWriter1.write("Bot1,100,2,13.1\n"); 
				myWriter1.write("Bot2,99,3,14.2\n"); 
				myWriter1.write("Bot3,98,3,14.7\n"); 
				myWriter1.write("Bot4,97,3,114.987787\n"); 
				myWriter1.write("Bot5,96,3,114.987787\n"); 
				myWriter1.write("Bot6,95,3,1114.987787\n"); 
				myWriter1.write("Bot7,94,3,14.987787\n"); 
				myWriter1.write("Bot8,93,3,114.987787\n"); 
				myWriter1.write("Bot9,92,3,114.987787\n"); 
				myWriter1.write("Bot10,91,3,114.987787\n");

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
			//System.out.println("NAME\tSCORE");
			//System.out.println("-------------------------------------------");
			for(int i = 0; i < 10; i++) {
	            //System.out.println(namesNormal[i] + "\t" + scoreArrayNormal[i]);
	        }
			String[] columnNames = {"Name", "Score", "Guesses", "Time"};
		    Object[][] rowData = new Object[namesNormal.length][4];

		    for (int i = 0; i < namesNormal.length; i++) {
		        rowData[i][0] = namesNormal[i];
		        rowData[i][1] = scoreArrayNormal[i];
		        rowData[i][2] = numOfGuessesNormal[i];
		        rowData[i][3] = timeNormal[i];
		    }

		    JTable leaderboardTable = new JTable(rowData, columnNames);
		    JScrollPane scrollPane = new JScrollPane(leaderboardTable);


	    // Wrap the leaderboard panel in a JScrollPane
	    //JScrollPane scrollPane = new JScrollPane(leaderboardPanel);

	    // Set up scrolling policies as needed
	   // scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	    //scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

	    // Optionally set the preferred size of the scroll pane
	    scrollPane.setPreferredSize(new Dimension(300, 200));

	    return scrollPane;
	}
	/**
	 * Creates a JScrollPane containing the leaderboard panel for the Hard mode.
	 *
	 * @return JScrollPane containing the Easy mode leaderboard panel.
	 * @throws IOException if there is an issue reading or writing the leaderboard file.
	 */
	private JScrollPane createHardLeaderboardPanel() throws IOException {
	    JPanel leaderboardPanel = new JPanel(new GridLayout(0, 1)); // Single column, multiple rows
	    int verticalSpacing = 10; // Adjust the spacing value as needed

	    //-------------------------------------------------------------------------
		   File myObjHard = new File("HardModeLeaderboard.txt"); //creating a new text file
			if (myObjHard.createNewFile()){ //if text file is created, do does the following below
				FileWriter myWriter1 = new FileWriter("HardModeLeaderboard.txt"); //writing in the text file

				//preloaded users
				myWriter1.write("Bot1,100,2,13.1\n"); 
				myWriter1.write("Bot2,99,3,214.2\n"); 
				myWriter1.write("Bot3,98,3,214.7\n"); 
				myWriter1.write("Bot4,97,3,214.987787\n"); 
				myWriter1.write("Bot5,96,3,214.987787\n"); 
				myWriter1.write("Bot6,95,3,214.987787\n"); 
				myWriter1.write("Bot7,94,3,214.987787\n"); 
				myWriter1.write("Bot8,93,3,214.987787\n"); 
				myWriter1.write("Bot9,92,3,214.987787\n"); 
				myWriter1.write("Bot10,91,3,214.987787\n");

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
			//System.out.println("NAME\tSCORE");
			//System.out.println("-------------------------------------------");
			for(int i = 0; i < 10; i++) {
	            //System.out.println(namesHard[i] + "\t" + scoreArrayHard[i]);
	        }
	   
			String[] columnNames = {"Name", "Score", "Guesses", "Time"};
		    Object[][] rowData = new Object[namesHard.length][4];

		    for (int i = 0; i < namesHard.length; i++) {
		        rowData[i][0] = namesHard[i];
		        rowData[i][1] = scoreArrayHard[i];
		        rowData[i][2] = numOfGuessesHard[i];
		        rowData[i][3] = timeHard[i];
		    }

		    JTable leaderboardTable = new JTable(rowData, columnNames);
		    JScrollPane scrollPane = new JScrollPane(leaderboardTable);


	    // Wrap the leaderboard panel in a JScrollPane
	    //JScrollPane scrollPane = new JScrollPane(leaderboardPanel);

	    // Set up scrolling policies as needed
	   // scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	    //scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

	    // Optionally set the preferred size of the scroll pane
	    scrollPane.setPreferredSize(new Dimension(300, 200));

	    return scrollPane;
	}

	/**
	  * calls the method for instructions abt popup
	  * @param args
	  * @throws Exception
	  */
	private void showInstructionsPopup() {
	    String instructions =
	            "Welcome to the Guess Who Game by JFW Games!\n\n" +
	            "To start, click the Play Button, after you will be directed to enter your player name.\n" +
	            "Think of a character and do not reveal it until the end.\n" +
	            "Each round you have the option to guess/ask questions to the computer.\n" +
	            "You will narrow down a list of potential characters to guess from. When ready, feel free to guess the computer’s character.\n" +
	            "The computer will ask you questions, where you have to respond yes/no.\n" +
	            "For any help, click the help buttons at various parts of the game!\n\n" +
	            "Good luck!";

	   
	    JTextArea textArea = new JTextArea(instructions); // Create a JTextArea to allow line breaks in the instructions
	    textArea.setEditable(false);
	    textArea.setLineWrap(true);
	    textArea.setWrapStyleWord(true);
	    JScrollPane scrollPane = new JScrollPane(textArea);	    // Place the JTextArea in a JScrollPane for scrolling
	    scrollPane.setPreferredSize(new Dimension(400, 300));
	    JOptionPane.showMessageDialog(this,scrollPane,"Game Instructions",JOptionPane.PLAIN_MESSAGE);	    // Show the JScrollPane in a JOptionPane
	}
	/**
	  * calls the method to show settings of the game
	  * @param args
	  * @throws Exception
	  */
	private void showSettingsPopup(int playerMaxNumGuess2, boolean musicOffOn2, String chosenMode2) {
	    String[] guessesOptions = {"1", "2", "3"};
	    String[] musicOptions = {"True", "False"};
	    String[] modeOptions = {"Easy", "Normal", "Hard"};

	    JComboBox<String> guessesComboBox = new JComboBox<>(guessesOptions);
	    JComboBox<String> musicComboBox = new JComboBox<>(musicOptions);
	    JComboBox<String> modeComboBox = new JComboBox<>(modeOptions);

	    guessesComboBox.setSelectedItem(String.valueOf(playerMaxNumGuess));
	    musicComboBox.setSelectedItem(String.valueOf(musicOffOn));
	    modeComboBox.setSelectedItem(chosenMode);

	    JPanel panelSetting = new JPanel(new GridLayout(3, 2));//gridlayout for the popup on settings
	    panelSetting.add(new JLabel("Max Number of Guesses:"));
	    panelSetting.add(guessesComboBox);
	    panelSetting.add(new JLabel("Music On/Off:"));
	    panelSetting.add(musicComboBox);
	    panelSetting.add(new JLabel("Computer Chosen Mode:"));
	    panelSetting.add(modeComboBox);

	    int option = JOptionPane.showConfirmDialog(this,panelSetting,"Settings", JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE );
	   
	    if (option == JOptionPane.OK_OPTION) {//
	        // Retrieve selected values and update variables
	    	//parse to according type
	        playerMaxNumGuess = Integer.parseInt((String) guessesComboBox.getSelectedItem());
	        //System.out.println("player max guess is: " + playerMaxNumGuess);
	        musicOffOn = Boolean.parseBoolean((String) musicComboBox.getSelectedItem());
	        //System.out.println("mustic is: " + musicOffOn);
	        chosenMode = (String) modeComboBox.getSelectedItem();
	        //System.out.println("chosenmode is: " + chosenMode);
	    }
	}
	/**
	 * Launches the game board by writing variables, disposing of the current GUI,
	 * and creating and displaying a new {@code GameBoard} instance.
	 *
	 * @throws Exception if there is an issue writing variables or launching the game board.
	 */
	private void launchGameBoard()throws Exception {//GEN-FIRST:event_jButton2ActionPerformed
		try {
	        writeVariables();
        } catch (Exception e) {
            e.printStackTrace();
        }
		dispose();//exit gui

        GameBoard gb = new GameBoard();
        gb.setVisible(true);
    }
	/**
	 * Displays a pop-up dialog to get the player's name.
	 * If the user clicks OK and enters a non-empty name, it updates the {@code player1Name}
	 * variable and proceeds to launch the game board.
	 *
	 * @throws Exception if there is an issue launching the game board.
	 */
	private void getPlayerNamePopup() throws Exception{
	    JTextField playerNameField = new JTextField();
	    Object[] message = {
	        "Enter your name:", playerNameField
	    };

	    int option = JOptionPane.showConfirmDialog(this, message, "Enter Name", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

	    if (option == JOptionPane.OK_OPTION && !playerNameField.getText().isEmpty()) {
	        // Retrieve the player's name and update the variable
	        player1Name = playerNameField.getText();
	        //System.out.println("So your name is: " + player1Name);
	        
	        try {
	            launchGameBoard();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	}
	/**
	 * Writes the game variables to a text file named "variableGameBoard.txt" so that we can access thru gameboard
	 * If the file doesn't exist, it creates a new one.
	 *
	 * @throws Exception if there is an issue writing to the file.
	 */
	private void writeVariables() throws Exception {
		File file = new File("variableGameBoard.txt");
        try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
            // Writing nothing effectively deletes the contents
        } catch (IOException e) {
            e.printStackTrace();
        }
	    
	    
        File updatedfile = new File("variableGameBoard.txt");

        // Append variables to the file
        try (PrintWriter writer = new PrintWriter(new FileWriter(updatedfile, true))) {       

        	writer.flush();
            writer.println(playerMaxNumGuess);
            writer.println(musicOffOn);
            writer.println(chosenMode);
            writer.println(player1Name);
            writer.close();
        }
        catch(Exception e) {
        	
        }
    }
	public static void main(String[] args) throws Exception{//start of main method 
		new HomeScreen(); // constructor in main
	}

}//END OF GuessWhoProject