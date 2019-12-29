/*	Kevin Tran
	Semester Project
*/

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

@SuppressWarnings("unchecked") //Prevent typecast warning

//Records Class
class Records implements ActionListener{
	//Static Variables
	static CardLayout cardLayout;
	static JButton[] buttons = new JButton[36];
	static JTextField[] textField = new JTextField[9];
	static JFrame frame = new JFrame("Student Records");
	static Container host = frame.getContentPane();
	static String[] strGender = {"Male", "Female"};
	static JComboBox<String> genderBox = new JComboBox<String>(strGender);
	static String[] strLevel = {"Freshman", "Sophomore", "Junior", "Senior", "Graduate"};
	static JComboBox<String> levelBox = new JComboBox<String>(strLevel);
	static JRadioButton r1 = new JRadioButton("By ID", true);
	static JRadioButton r2 = new JRadioButton("By Name");
	static String[] category = {"ID", "Name", "Level", "Gender", "Age", "Lab 1", "Lab 2", "Lab 3", "Lab 4", "Lab 5", "Lab 6", "Lab 7", "Lab 8", "Lab 9", "Lab 10"};
	static DefaultTableModel tableModel; //Put data and column names in here so we can refresh
	static JTable table;
	
	static String className;
	static String loadFileName;
	static int numOfStudents = 0;
	static ArrayList<Student> students = new ArrayList<Student>();
	static boolean fromExit = false;
	static boolean saveAlready = false;
	//For setting lab scores
	static JLabel labLabel = new JLabel("");
	static int labNum = -1;
	static int labIndex = 0;
	
//Main Method ----------------------------------------------------------------------------------------------------------------------------------------------------------------------
	public static void main(String[] args) throws IOException{
		
		//Create ActionListener
		ActionListener AL = new Records();
		
		//Create Panels, Containers
		host.setLayout(cardLayout = new CardLayout());
		//Main Menu
		JPanel mainMenu = new JPanel(new BorderLayout());
		JPanel mainTitle = new JPanel(new GridLayout(2,1,3,3));
		JPanel u = new JPanel(new FlowLayout());
		JPanel mainButtons = new JPanel(new GridLayout(2,4,3,3));
			//Confirm Exit
			JPanel exitConfirm = new JPanel(new BorderLayout());
			JPanel exitButton = new JPanel(new FlowLayout());
		//Create Class
		JPanel createClass = new JPanel(new GridLayout(3,1,10,10));
		JPanel classBody = new JPanel(new FlowLayout());
		JPanel classButtons = new JPanel(new FlowLayout());
			//Confirmation of Creating Class
			JPanel classConfirm = new JPanel(new BorderLayout());
			JPanel classConfirmButtons = new JPanel(new FlowLayout());
		//Load Students
		JPanel load = new JPanel(new GridLayout(3,1,10,10));
		JPanel loadBody = new JPanel(new FlowLayout());
		JPanel loadButtons = new JPanel(new FlowLayout());
			//Load Confirm 
			JPanel loadConfirm = new JPanel(new BorderLayout());
			JPanel loadConfirmButton = new JPanel(new FlowLayout());
			//Load Success
			JPanel loadSuccess = new JPanel(new BorderLayout());
			JPanel loadSuccessButton = new JPanel(new FlowLayout());
			//Error Load Students
			JPanel errorLoad = new JPanel(new BorderLayout());
			JPanel errorLoadButton = new JPanel(new FlowLayout());
		//Enter Student Information
		JPanel enterInfo = new JPanel(new GridLayout(7,1,0,0));
		JPanel ssn = new JPanel(new GridLayout(1,2,0,0));
		JPanel studentName = new JPanel(new GridLayout(1,2,0,0));
		JPanel gender = new JPanel(new GridLayout(1,2,0,0));
		JPanel age = new JPanel(new GridLayout(1,2,0,0));
		JPanel acaLevel = new JPanel(new GridLayout(1,2,0,0));
		JPanel infoButtons = new JPanel(new FlowLayout());
			//Error Add Student
			JPanel errorAddStudent = new JPanel(new BorderLayout());
			JPanel errorAddButton = new JPanel(new FlowLayout());
			//Error Enter Student Info
			JPanel errorStudentInfo = new JPanel(new BorderLayout());
			JPanel errorStudentButton = new JPanel(new FlowLayout());
		//Enter Lab Scores
		JPanel enterScores = new JPanel(new BorderLayout());
		JPanel labText = new JPanel(new GridLayout(3,1,1,1));
		JPanel number = new JPanel(new FlowLayout());
		JPanel stuNames = new JPanel(new FlowLayout());
		JPanel score = new JPanel(new FlowLayout());
		JPanel labButton = new JPanel(new FlowLayout());
			//Error Enter Lab Scores
			JPanel errorEnterLabScores = new JPanel(new BorderLayout());
			JPanel errorEnterLabButton = new JPanel(new FlowLayout());
		//Sort Students
		JPanel sort = new JPanel(new BorderLayout());
		JPanel sortTop = new JPanel(new GridLayout(2,1,2,2));
		JPanel sortTopRadio = new JPanel(new FlowLayout());
		JPanel sortButtons = new JPanel(new FlowLayout());
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(r1);
		buttonGroup.add(r2);
		//View/Delete Students
		JPanel list = new JPanel(new BorderLayout());
		JPanel listButtons = new JPanel(new FlowLayout());
			//Existing File
			JPanel existingFile = new JPanel(new BorderLayout());
			JPanel existingButton = new JPanel(new FlowLayout());
			//Error Backup Students
			JPanel errorBackup = new JPanel(new BorderLayout());
			JPanel errorBackupButton = new JPanel(new FlowLayout());
			//Backup Successful
			JPanel backupSuccess = new JPanel(new BorderLayout());
			JPanel backupButton = new JPanel(new FlowLayout());
//Add Buttons, TextFields, etc. ----------------------------------------------------------------------------------------------------------------------------------------------------------------------
		//Main Menu
		mainTitle.add(new JLabel("<html><font size=5><b>Use The Buttons Below To Manage Students</b></html>", SwingConstants.CENTER));
		mainTitle.add(u);
		u.add(new JLabel("Class Name:"));
		u.add(textField[0] = new JTextField("", 15));
		u.add(new JLabel("Number of Students:"));
		u.add(textField[1] = new JTextField("0", 15));
		textField[0].setEditable(false);
		textField[1].setEditable(false);
		mainButtons.add(buttons[0] = new JButton("Create a New Class"));
		mainButtons.add(buttons[1] = new JButton("Load Students"));
		mainButtons.add(buttons[2] = new JButton("Add New Students"));
		mainButtons.add(buttons[3] = new JButton("Enter Lab Scores"));
		mainButtons.add(buttons[4] = new JButton("Sort Students"));
		mainButtons.add(buttons[5] = new JButton("View/Delete Students"));
		mainButtons.add(buttons[6] = new JButton("Backup Students"));
		mainButtons.add(buttons[7] = new JButton("Exit"));
			//Confirm Exit
			exitConfirm.add(new JLabel("You may lose data if you exit. Would you like to save?", SwingConstants.CENTER), BorderLayout.NORTH);
			exitButton.add(buttons[33] = new JButton("Save & Exit"));
			exitButton.add(buttons[34] = new JButton("Exit Without Saving"));
			exitButton.add(buttons[35] = new JButton("Cancel"));
		//Create Class
		classBody.add(new JLabel("Class Name:"));
		classBody.add(textField[2] = new JTextField("", 15));
		classButtons.add(buttons[8] = new JButton("Create"));
		classButtons.add(buttons[9] = new JButton("Cancel"));
		createClass.add(new JLabel("<html><font size=5><b>Create a New Class</b></html>", SwingConstants.CENTER));
			//Confirmation of Creating Class
			classConfirm.add(new JLabel("There is already a class name. Would you like to replace the name or clear all data and replace the name?", SwingConstants.CENTER), BorderLayout.NORTH);
			classConfirmButtons.add(buttons[22] = new JButton("Replace Name"));
			classConfirmButtons.add(buttons[23] = new JButton("Clear Data"));
			classConfirmButtons.add(buttons[24] = new JButton("Cancel"));
		//Load Students
		loadBody.add(new JLabel("Class Name:"));
		loadBody.add(textField[3] = new JTextField("", 15));
		loadButtons.add(buttons[10] = new JButton("Load"));
		loadButtons.add(buttons[11] = new JButton("Cancel"));
		load.add(new JLabel("<html><font size=5><b>Load Students from a File</b></html>", SwingConstants.CENTER));
			//Load Confirm 
			loadConfirm.add(new JLabel("If you load a file you may lose existing data. Load anyways?", SwingConstants.CENTER), BorderLayout.NORTH);
			loadConfirmButton.add(buttons[28] = new JButton("Load Anyways"));
			loadConfirmButton.add(buttons[29] = new JButton("Cancel"));
			//Load Success
			loadSuccess.add(new JLabel("Load successful.", SwingConstants.CENTER), BorderLayout.NORTH);
			loadSuccessButton.add(buttons[30] = new JButton("OK"));
			//Error Load Students
			errorLoad.add(new JLabel("File not found.", SwingConstants.CENTER), BorderLayout.NORTH);
			errorLoadButton.add(buttons[27] = new JButton("OK"));
		//Enter Student Information
		enterInfo.add(new JLabel("<html><font size=5><b>Enter Student Information</b></html>", SwingConstants.CENTER));
		ssn.add(new JLabel("SSN: ", SwingConstants.RIGHT));
		ssn.add(textField[4] = new JTextField("", 30));
		studentName.add(new JLabel("Name: ", SwingConstants.RIGHT));
		studentName.add(textField[5] = new JTextField("", 30));
		gender.add(new JLabel("Gender: ", SwingConstants.RIGHT));
		gender.add(genderBox);
		age.add(new JLabel("Age: ", SwingConstants.RIGHT));
		age.add(textField[6] = new JTextField("", 30));
		acaLevel.add(new JLabel("Academic Level: ", SwingConstants.RIGHT));
		acaLevel.add(levelBox);
		infoButtons.add(buttons[12] = new JButton("Save & Add Student"));
		infoButtons.add(buttons[13] = new JButton("Back to Top Menu"));
			//Error Add Student
			errorAddStudent.add(new JLabel("Please enter a class name first.", SwingConstants.CENTER), BorderLayout.NORTH);
			errorAddButton.add(buttons[25] = new JButton("OK"));
			//Error Enter Student Info
			errorStudentInfo.add(new JLabel("One or more fields are either unfilled or incorrectly filled.", SwingConstants.CENTER), BorderLayout.NORTH);
			errorStudentButton.add(buttons[19] = new JButton("OK"));
		//Enter Lab Scores
		enterScores.add(new JLabel("<html><font size=5><b>Enter Lab Scores</b></html>", SwingConstants.CENTER), BorderLayout.NORTH);
		number.add(new JLabel("Lab Number:", SwingConstants.RIGHT));
		number.add(textField[7] = new JTextField("", 2));
		stuNames.add(labLabel, SwingConstants.CENTER);
		score.add(new JLabel("Score:", SwingConstants.RIGHT));
		score.add(textField[8] = new JTextField("", 5));
		labText.add(number);
		labText.add(stuNames);
		labText.add(score);
		labButton.add(buttons[14] = new JButton("Back to Top Menu"));
			//Error Enter Lab Scores
			errorEnterLabScores.add(new JLabel("Please enter valid lab numbers or scores in the text fields.", SwingConstants.CENTER), BorderLayout.NORTH);
			errorEnterLabButton.add(buttons[20] = new JButton("OK"));
		//Sort Students
		sortTopRadio.add(r1);
		sortTopRadio.add(r2);
		sortTop.add(new JLabel("<html><font size=5><b>Sort Students</b></html>", SwingConstants.CENTER));
		sortTop.add(sortTopRadio);
		sortButtons.add(buttons[15] = new JButton("Sort"));
		sortButtons.add(buttons[16] = new JButton("Top Menu"));
		//View/Delete Students
		tableModel = new DefaultTableModel(getData(), category);
		table = new JTable(tableModel); //Calls getData method
		list.add(new JLabel("<html><font size=5><b>Student List</b></html>", SwingConstants.CENTER), BorderLayout.NORTH);
		listButtons.add(buttons[17] = new JButton("Delete Selected"));
		listButtons.add(buttons[18] = new JButton("Top Menu"));
			//Existing File
			existingFile.add(new JLabel("A file with the class name already exists. Would you like to overwrite?", SwingConstants.CENTER), BorderLayout.NORTH);
			existingButton.add(buttons[31] = new JButton("Overwrite"));
			existingButton.add(buttons[32] = new JButton("Cancel"));
			//Error Backup Students
			errorBackup.add(new JLabel("Invalid class name. Please change the class name.", SwingConstants.CENTER), BorderLayout.NORTH);
			errorBackupButton.add(buttons[21] = new JButton("OK"));
			//Backup Successful
			backupSuccess.add(new JLabel("Save successful.", SwingConstants.CENTER), BorderLayout.NORTH);
			backupButton.add(buttons[26] = new JButton("OK"));
//Add to Container ----------------------------------------------------------------------------------------------------------------------------------------------------------------------
		//Main Menu
		mainMenu.add(mainTitle, BorderLayout.NORTH);
		mainMenu.add(mainButtons, BorderLayout.SOUTH);
		host.add("mainMenu", mainMenu);
			//Confirm Exit
			exitConfirm.add(exitButton, BorderLayout.SOUTH);
			host.add("exitConfirm", exitConfirm);
		//Create Class
		createClass.add(classBody);
		createClass.add(classButtons);
		host.add("createClass", createClass);
			//Confirmation of Creating Class
			classConfirm.add(classConfirmButtons, BorderLayout.SOUTH);
			host.add("classConfirm", classConfirm);
		//Load Students
		load.add(loadBody);
		load.add(loadButtons);
		host.add("load", load);
			//Load Confirm 
			loadConfirm.add(loadConfirmButton, BorderLayout.SOUTH);
			host.add("loadConfirm", loadConfirm);
			//Load Success
			loadSuccess.add(loadSuccessButton, BorderLayout.SOUTH);
			host.add("loadSuccess", loadSuccess);
			//Error Load Students
			errorLoad.add(errorLoadButton, BorderLayout.SOUTH);
			host.add("errorLoad", errorLoad);
		//Enter Student Information
		enterInfo.add(ssn);
		enterInfo.add(studentName);
		enterInfo.add(gender);
		enterInfo.add(age);
		enterInfo.add(acaLevel);
		enterInfo.add(infoButtons);
		host.add("enterInfo", enterInfo);
			//Error Add Student
			errorAddStudent.add(errorAddButton, BorderLayout.SOUTH);
			host.add("errorAddStudent", errorAddStudent);
			//Error Enter Student Info
			errorStudentInfo.add(errorStudentButton, BorderLayout.SOUTH);
			host.add("errorStudentInfo", errorStudentInfo);
		//Enter Lab Scores
		enterScores.add(labText, BorderLayout.CENTER);
		enterScores.add(labButton, BorderLayout.SOUTH);
		host.add("enterScores", enterScores);
			//Error Enter Lab Scores
			errorEnterLabScores.add(errorEnterLabButton, BorderLayout.SOUTH);
			host.add("errorEnterLabScores", errorEnterLabScores);
		//Sort Students
		sort.add(sortTop, BorderLayout.NORTH);
		sort.add(sortButtons, BorderLayout.SOUTH);
		host.add("sort", sort);
		//View/Delete Students
		list.add(new JScrollPane(table), BorderLayout.CENTER);
		list.add(listButtons, BorderLayout.SOUTH);
		host.add("list", list);
			//Existing File
			existingFile.add(existingButton, BorderLayout.SOUTH);
			host.add("existingFile", existingFile);
			//Error Backup Students
			errorBackup.add(errorBackupButton, BorderLayout.SOUTH);
			host.add("errorBackup", errorBackup);
			//Backup Successful
			backupSuccess.add(backupButton, BorderLayout.SOUTH);
			host.add("backupSuccess", backupSuccess);
			
//Add ActionListener ----------------------------------------------------------------------------------------------------------------------------------------------------------------------
		for(int i = 0; i < buttons.length; i++){
			buttons[i].addActionListener(AL);
		}
		
		textField[7].addKeyListener(new KeyAdapter() { //KeyListener for lab number
			public void keyPressed(KeyEvent e) {
				try{
					if (e.getKeyCode()==KeyEvent.VK_ENTER){
						if(!textField[7].getText().equals("") && students.size() != 0){
							labNum = Integer.parseInt(textField[7].getText()) - 1;
							if(!(labNum > 9 || labNum < 0)){
								textField[7].setEditable(false);
								labLabel.setText((students.get(0)).getIDNumber() + " " + (students.get(labIndex)).getName());
							}else{
								labNum = -1;
							}
						}
						return;
					}
				}catch(Exception f){
					cardLayout.show(host, "errorEnterLabScores");
					textField[7].setText("");
					textField[7].setEditable(true);
				}		
			}});
		textField[8].addKeyListener(new KeyAdapter() { //KeyListener for lab scores
			public void keyPressed(KeyEvent e) {
				try{
					if (e.getKeyCode()==KeyEvent.VK_ENTER && labIndex <= students.size() - 1 && labNum != -1){
						if(!textField[8].getText().equals("") && labIndex != students.size()){
							(students.get(labIndex)).setLabScore(labNum, Integer.parseInt(textField[8].getText()));
							textField[8].setText("");
							labIndex++;
							saveAlready = false;
						}
						if(labIndex == students.size()){
							labIndex = 0;
							labNum = -1;
							textField[7].setText("");
							textField[7].setEditable(true);
							labLabel.setText("");
						}else{
							labLabel.setText((students.get(labIndex)).getIDNumber() + " " + (students.get(labIndex)).getName());
						}
						return;
					}
				}catch(Exception f){
					cardLayout.show(host, "errorEnterLabScores");
					textField[8].setText("");
				}
		}});
			 
		
		//Set Visible
		cardLayout.show(host, "mainMenu");
		frame.pack();
		frame.setSize(700, 250);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);
	}
//----------------------------------------------------------------------------------------------------------------------------------------------------------------------
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == buttons[0]){ // if "Create a New Class" is pressed at the main menu
			cardLayout.show(host, "createClass");
			return;
		}
		if(e.getSource() == buttons[1]){ // if "Load Students" is pressed at the main menu
			cardLayout.show(host, "load");
			return;
		}
		if(e.getSource() == buttons[2]){ // if "Add New Students" is pressed at the main menu
			if(className == null){
				cardLayout.show(host, "errorAddStudent"); 
			}else{
				cardLayout.show(host, "enterInfo");
				return;
			}
		}
			//Error Add Student
			if(e.getSource() == buttons[25]){ // If "OK" is selected at the error add student prompt
				cardLayout.show(host, "mainMenu");
				return;
			}
		if(e.getSource() == buttons[3]){ // if "Enter Lab Scores" is pressed at the main menu
			cardLayout.show(host, "enterScores");
			return;
		}
		if(e.getSource() == buttons[4]){ // if "Sort Students" is pressed at the main menu
			cardLayout.show(host, "sort");
			return;
		}
		if(e.getSource() == buttons[5]){ // if "View/Delete Students" is pressed at the main menu
			tableModel = new DefaultTableModel(getData(), category); //Calls getData method
			table.setModel(tableModel);
			cardLayout.show(host, "list");
			return;
		}
		if(e.getSource() == buttons[6]){ // if "Backup Students" is pressed at the main menu
			try{
				className = textField[0].getText();
				if(!className.equals("") && className != null){
					if(new File(className + ".txt").isFile()){
						cardLayout.show(host, "existingFile");
					}else{
						backupStudents();
						cardLayout.show(host, "backupSuccess");
						saveAlready = true;
					}
				}
			}catch(Exception f){
				cardLayout.show(host, "errorBackup");
			}
			return;
		}
			//Existing File
			if(e.getSource() == buttons[31]){// if "Overwrite" is pressed at the existing file prompt
				try{
					className = textField[0].getText();
					backupStudents();
					cardLayout.show(host, "backupSuccess");
					saveAlready = true;
				}catch(Exception f){
					cardLayout.show(host, "errorBackup");
				}
				return;
			}
			if(e.getSource() == buttons[32]){// if "Cancel" is pressed at the existing file prompt
				cardLayout.show(host, "mainMenu");
				return;
			}
			//Error Backup Students
			if(e.getSource() == buttons[21]){// if "OK" is pressed at the error backup students menu
				cardLayout.show(host, "mainMenu");
				return;
			}
			//Backup Successful
			if(e.getSource() == buttons[26]){// if "OK" is pressed at the backup successful menu
				if(fromExit){
					frame.dispose();
				}else{
					cardLayout.show(host, "mainMenu");
					return;
				}
			}
		if(e.getSource() == buttons[7]){ // if "Exit" is pressed at the main menu
			if((className == null || className.equals("")) && students.size() == 0 || saveAlready){
				frame.dispose();
			}else{
				cardLayout.show(host, "exitConfirm");
			}
		}
			//Confirm Exit
			if(e.getSource() == buttons[33]){ // if "Save & Exit" is pressed at the confirm exit prompt
				try{
					fromExit = true;
					className = textField[0].getText();
					if(!className.equals("") && className != null){
						if(new File(className + ".txt").isFile()){
							cardLayout.show(host, "existingFile");
						}else{
							backupStudents();
							cardLayout.show(host, "backupSuccess");
						}
					}
				}catch(Exception f){
					fromExit = false;
					cardLayout.show(host, "errorBackup");
				}
			}
			if(e.getSource() == buttons[34]){ // if "Exit Without Saving" is pressed at the confirm exit prompt
				frame.dispose();
			}
			if(e.getSource() == buttons[35]){ // if "Cancel" is pressed at the confirm exit prompt
				cardLayout.show(host, "mainMenu");
				return;
			}				
		if(e.getSource() == buttons[8]){// if "Create" is pressed at the create class menu
			if(className == null && students.size() == 0){
				className = textField[2].getText();
				textField[2].setText("");
				textField[0].setText(className);
				cardLayout.show(host, "mainMenu");
				saveAlready = false;
				return;
			}else{
				cardLayout.show(host, "classConfirm");
			}
		}
			//Confirmation of Create Class
			if(e.getSource() == buttons[22]){// if "Replace Name" is pressed at the confirmation menu
				className = textField[2].getText();
				textField[2].setText("");
				textField[0].setText(className);
				cardLayout.show(host, "mainMenu");
				saveAlready = false;
				return;
			}
			if(e.getSource() == buttons[23]){// if "Clear Data" is pressed at the confirmation menu
				className = textField[2].getText();
				textField[2].setText("");
				textField[0].setText(className);
				students.clear();
				textField[1].setText("0");
				cardLayout.show(host, "mainMenu");
				saveAlready = false;
				return;
			}
			if(e.getSource() == buttons[24]){// if "Cancel" is pressed at the confirmation menu
				cardLayout.show(host, "createClass");
				return;
			}
		if(e.getSource() == buttons[9]){// if "Cancel" is pressed at the create class menu
			cardLayout.show(host, "mainMenu");
			textField[2].setText("");
			return;
		}
		if(e.getSource() == buttons[10]){// if "Load" is pressed at the load students menu
			try{
				if(students.size() != 0){
					cardLayout.show(host, "loadConfirm");
				}else{
					loadFileName = textField[3].getText();
					numOfStudents = loadStudents(loadFileName);
					textField[1].setText(Integer.toString(numOfStudents));
					textField[0].setText(loadFileName);
					cardLayout.show(host, "loadSuccess");
					textField[3].setText("");
					saveAlready = true;
					return;
				}
			}catch(Exception h){
				cardLayout.show(host, "errorLoad");
			}
		}
		if(e.getSource() == buttons[11]){// if "Cancel" is pressed at the load students menu
			cardLayout.show(host, "mainMenu");
			return;
		}
			//Confirm Load
			if(e.getSource() == buttons[28]){// if "Load Anyways" is pressed at the confirm load menu
				try{
					loadFileName = textField[3].getText();
					textField[0].setText(loadFileName);
					numOfStudents = loadStudents(loadFileName);
					textField[1].setText(Integer.toString(numOfStudents));
					cardLayout.show(host, "loadSuccess");
					textField[3].setText("");
					saveAlready = true;
					return;
				}catch(Exception h){
					cardLayout.show(host, "errorLoad");
				}
			}
			if(e.getSource() == buttons[29]){// if "Cancel" is pressed at the confirm load prompt
				cardLayout.show(host, "load");
				return;
			}
			//Load Success
			if(e.getSource() == buttons[30]){// if "OK" is pressed at the load success prompt
				cardLayout.show(host, "mainMenu");
				return;
			}
			//Error Load Students
			if(e.getSource() == buttons[27]){// if "OK" is pressed at the error load students menu
				cardLayout.show(host, "load");
				return;
			}
		if(e.getSource() == buttons[12]){// if "Save & Add Student" is pressed at the add new students menu
			try{
				students.add(new Student(Integer.parseInt(textField[4].getText()), textField[5].getText(), strToLevel(levelBox.getSelectedItem().toString()), strToGender(genderBox.getSelectedItem().toString()), Integer.parseInt(textField[6].getText())));
				textField[4].setText("");
				textField[5].setText("");
				textField[6].setText("");
				genderBox.setSelectedItem("Male");
				levelBox.setSelectedItem("Freshman");
				numOfStudents++;
				textField[1].setText(Integer.toString(numOfStudents));
				saveAlready = false;
			}catch(Exception g){
				cardLayout.show(host, "errorStudentInfo");
			}
			return;
		}
			//Error Enter Student Info
			if(e.getSource() == buttons[19]){ // If "OK" is selected at the error student info prompt
				cardLayout.show(host, "enterInfo");
				return;
			}
		if(e.getSource() == buttons[13]){// if "Back to Top Menu" is pressed at the add new students menu
			cardLayout.show(host, "mainMenu");
			return;
		}
		if(e.getSource() == buttons[14]){// if "Back to Top Menu" is pressed at the enter lab scores menu
			cardLayout.show(host, "mainMenu");
			labNum = -1;
			labIndex = 0;
			textField[7].setText("");
			textField[8].setText("");
			textField[7].setEditable(true);
			textField[8].setEditable(true);
			labLabel.setText("");
			return;
		}
			//Error Enter Scores
			if(e.getSource() == buttons[20]){ // If "OK" is selected at the error enter lab scores menu
				cardLayout.show(host, "enterScores");
				return;
			}
		if(e.getSource() == buttons[15]){// if "Sort" is pressed at the sort students menu
			if(r1.isSelected()){
				sort(0);
			}else{
				sort(' ');
			}
			cardLayout.show(host, "mainMenu");
			r1.setSelected(true);
			saveAlready = false;
			return;
		}
		if(e.getSource() == buttons[16]){// if "Top Menu" is pressed at the sort students menu
			cardLayout.show(host, "mainMenu");
			r1.setSelected(true);
			return;
		}
		if(e.getSource() == buttons[17]){// if "Delete Selected" is pressed at the view/delete students menu
			if(table.getSelectedRow() != -1){
				students.remove(table.getSelectedRow());
				tableModel.removeRow(table.getSelectedRow());
				numOfStudents--;
				textField[1].setText(Integer.toString(numOfStudents));
				saveAlready = false;
			}
			return;
		}
		if(e.getSource() == buttons[18]){// if "Top Menu" is pressed at the view/delete students menu
			cardLayout.show(host, "mainMenu");
			return;
		}
	}
//----------------------------------------------------------------------------------------------------------------------------------------------------------------------
	public static char strToGender(String str){
		if(str.equals("Male")){
			return 'M';
		}else{
			return 'F';
		}
	}
//----------------------------------------------------------------------------------------------------------------------------------------------------------------------
	public static int strToLevel(String str){
		if(str.equals("Freshman")){
			return 1;
		}else if(str.equals("Sophomore")){
			return 2;
		}else if(str.equals("Junior")){
			return 3;
		}else if(str.equals("Senior")){
			return 4;
		}else{
			return 5;
		}
	}
//----------------------------------------------------------------------------------------------------------------------------------------------------------------------
	public static void sort(int a){
        for (int i=1; i<students.size(); i++){ 
			Student studentKey = students.get(i);
            int key = (students.get(i)).getIDNumber(); 
            int j = i-1; 
            while (j>=0 && (students.get(j)).getIDNumber() > key){ 
                students.set(j+1, students.get(j)); 
                j = j-1; 
            } 
            students.set(j+1, studentKey); 
        } 
	}
//----------------------------------------------------------------------------------------------------------------------------------------------------------------------
	public static void sort(char a){
		for (int i=1; i<students.size(); i++){ 
			Student studentKey = students.get(i);
            String key = (students.get(i)).getName(); 
            int j = i-1; 
            while (j>=0 && (students.get(j)).getName().compareToIgnoreCase(key) > 0){ 
                students.set(j+1, students.get(j)); 
                j = j-1; 
            } 
            students.set(j+1, studentKey); 
        } 
	}
//----------------------------------------------------------------------------------------------------------------------------------------------------------------------
	public static Object[][] getData(){
		Object[][] result = new Object[students.size()][15];
		if(students.size() != 0){
			for(int i = 0; i < students.size(); i++){
				result[i][0] = (students.get(i)).getIDNumber();
				result[i][1] = (students.get(i)).getName();
				result[i][2] = (students.get(i)).getLevel();
				result[i][3] = (students.get(i)).getGender();
				result[i][4] = (students.get(i)).getAge();
				for(int j = 0; j < 10; j++){
					result[i][j + 5] = (students.get(i)).getLabScore(j);
				}
			}
			return result;
		}else{
			return null;
		}
	}
//----------------------------------------------------------------------------------------------------------------------------------------------------------------------
	public static int loadStudents(String name) throws Exception{
		students.clear();
		FileInputStream fis = new FileInputStream(name + ".txt");
		ObjectInputStream ois = new ObjectInputStream(fis);
		try{
			students = (ArrayList<Student>)ois.readObject();
			className = name;
		}catch(EOFException e){
			ois.close();
		}
		return students.size();
	}
//----------------------------------------------------------------------------------------------------------------------------------------------------------------------
	public static void backupStudents() throws IOException{
		FileOutputStream fos = new FileOutputStream (className + ".txt", false);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(students);
		oos.close();
	}
}
//Student Class ----------------------------------------------------------------------------------------------------------------------------------------------------------------------
class Student implements Serializable{
	//Initialization
	private int idNumber = -1;
	private String name = "NULL";
	private int level = -1;
	private char gender = '0';
	private int age = -1;
	private int[] labScore = {0,0,0,0,0,0,0,0,0,0};
	
	//Constructors
	public Student(){
	}
	public Student(int idNumber, String name, int level, char gender, int age){
		this.idNumber = idNumber;
		this.name = name;
		this.gender = gender;
		this.level = level;
		this.age = age;
	}
	//Getters
	public int getIDNumber(){
		return idNumber;
	}
	public String getName(){
		return name;
	}
	public char getGender(){
		return gender;
	}
	public String getLevel(){
		switch(level){
			case 1:
				return "Freshman";
			case 2:
				return "Sophomore";
			case 3:
				return "Junior";
			case 4:
				return "Senior";
			default:
				return "Graduate";
		}
	}
	public int getAge(){
		return age;
	}
	public int getLabScore(int index){
		return labScore[index];
	}
	
	//Setters
	public void setIDNumber(int num){
		idNumber = num;
	}
	public void setName(String str){
		name = str;
	}
	public void setGender(char gen){
		gender = gen;
	}
	public void setLevel(int lvl){
		level = lvl;
	}
	public void setAge(int num){
		age = num;
	}
	public void setLabScore(int index, int num){
		labScore[index] = num;
	}
	
	public String toString(){
		return idNumber + " " + name + " " + gender + " " + level + " " + age + " " + labScore[0] + " " + labScore[1] + " " + labScore[2] + " " + labScore[3] + " " + labScore[4]  + " " + labScore[5] + " " + labScore[6] + " " + labScore[7] + " " + labScore[8] + " " + labScore[9];
	}
}