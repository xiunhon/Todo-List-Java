import java.util.Scanner;
/*
 * @author Group 3: Tien N., Victoria T., Niklas K.
 * due date 4/12/2021
 * Part One
Implement a to do list. Tasks have a priority between 1 and 9, and a description.
When the user enters the command add priority description, 
create a task with the the priority and the description entered in the command. 
Then the program adds a new task.
When the user enters next, the program removes and prints the most urgent task.
The quit command ends the program.
Use a priority queue in your solution.
Override the hashcode() and equals() so that two tasks with the same description are considered equal. 
Make sure to ignore the case of the characters.
If the new task already exists in the priority queue, let the user know that and don't add it again. 
Additionally, print out each object's hashcode to prove it.
 */
public class ToDoRunner {
	/**
	 * Check if the input is valid. Only accept 3 commands
	 * add priority description
	 * next
	 * quit
	 * @param User's input
	 * @return user's input if the input is valid
	 */
	public static String checkInput(String input) {
		Scanner in = new Scanner(System.in);
		
		boolean valid = false;
		while (!valid) {
			//split the input into a String Array, check valid such that:
			//first index of the String[] is the String command (add/next/quit)
			//String[] for "next" and "quit" command has exact length of 1
			while (input.isBlank()) {
				System.out.println("! Please enter an option.");
				input = in.nextLine();
			}
			String[] strArray = input.split(" ");
			if ((strArray.length == 1 && strArray[0].equalsIgnoreCase("next"))
				|| (strArray.length == 1 && strArray[0].equalsIgnoreCase("quit"))
				|| strArray[0].equalsIgnoreCase("add")) {
				valid = true;
			}
			else {
				System.out.println("! Invalid command, please try again.");
				input = in.nextLine();
			}
		}
		return input;
	}//end of checkInput
	
	/**
	 * main
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("---------------User's Portal---------------");
		System.out.println("-------------------------------------------\r\n"
				+ "To Do List - Please enter an option\r\n"
				+ "  - to add a new task: enter 'add priority description'\r\n"
				+ "    ('priority' is a number from 1 to 9, following by a task description)\r\n"
				+ "  - to remove and print most urgent task: enter 'next'\r\n"
				+ "  - to exit this program: enter 'quit'");
		String menu = "-------------------------------------------\r\n"
				+ "To Do List - Please enter an option\r\n"
				+ "  - add priority description (add a new task)\r\n"
				+ "  - next (remove and print most urgent task)\r\n"
				+ "  - quit (exit this program)";
		String input = checkInput(in.nextLine());
		ToDoList list = new ToDoList();
		while (!input.equalsIgnoreCase("quit")) {
			String[] strArray = input.split("\\s+");
			if (strArray[0].equalsIgnoreCase("add")) {
				try {
					// if task is added successfully, print message to inform user
					if (list.addTask(input)) {
						String description = "";
						for (int i = 2; i<strArray.length; i++) {
							description += strArray[i] + " ";
						}
						System.out.println("! Task '" + description.trim() + "' is added.");
					}
				} catch (InvalidInputException ie) {
					System.out.println(ie.getMessage());
				}
			}
			else if (strArray[0].equalsIgnoreCase("next")) {
				try {
					System.out.println(list.nextTask().toString());
				} catch (InvalidInputException ie) {
					System.out.println(ie.getMessage());
				}
			}
			System.out.println(menu);
			input = checkInput(in.nextLine());
		}
		if (!list.isEmpty()) {
			System.out.println("! The remained task(s) in ToDo list:");
			System.out.println(list.toString());
		}
		System.out.println("! Exit the program !");
		System.out.println();
		System.out.println("-------------Prefilled tester--------------"
				+ "\n-------------------------------------------\r\n");
		System.out.println("Adding the following 6 items to the list.");
		System.out.println("\"add 1 Complete Programming Exercise 15.11\"");
		System.out.println("\"add 8 Read for tomorrow's class\"");
		System.out.println("\"add 3 Soccer practice\"");
		System.out.println("\"add 6 Call parents\"");
		System.out.println("\"add 5 Have dinner with friends\"");
		System.out.println("\"add 9 Sleep well\"");
		try {
			list.addTask("add 1 Complete Programming Exercise 15.11");
			list.addTask("add 8 Read for tomorrow's class");
			list.addTask("add 3 Soccer practice");
			list.addTask("add 6 Call parents");
			list.addTask("add 5 Have dinner with friends");
			list.addTask("add 9 Sleep well");
			/*
			 * invalid format test
			 */
			System.out.println();
			System.out.println("Entering 'add bad command'");
			System.out.println("Expected: The priority must be an integer between 1 and 9.\n== ");
			try {
				list.addTask("add bad command");
			} catch (InvalidInputException e) {
				System.out.println(e.getMessage());
			}
			/*
			 * invalid format test
			 */
			System.out.println();
			System.out.println("Entering 'add 10 command'");
			System.out.println("Expected: The priority must be an integer between 1 and 9.\n== ");
			try {
				list.addTask("add 10 command");
			} catch (InvalidInputException e) {
				System.out.println(e.getMessage());
			}
			System.out.println();
			System.out.println("Pulling most urgent items out.");
			System.out.println("Expected: Complete Programming Exercise 15.11 == " + list.nextTask().toString());
			System.out.println("Expected: Soccer practice == " + list.nextTask().toString());
			System.out.println("Expected: Have dinner with friends == " + list.nextTask().toString());
			System.out.println("Expected: Call parents == " + list.nextTask().toString());
			System.out.println("Expected: Read for tomorrow's class == " + list.nextTask().toString());
			System.out.println("Expected: Sleep well == " + list.nextTask().toString());
			/*
			 * call nextTask() when list is empty
			 */
			System.out.println("Expected: There are no tasks in the list. == ");
			try {
				list.nextTask();
			} catch (InvalidInputException e) {
				System.out.println(e.getMessage());
			}
			
			/*
			 * duplicated test, 2 tasks with same description is duplicated
			 */
			System.out.println();
			System.out.println("Adding the following 2 items to the list.");
			System.out.println("\"add 5 Call parents\"");
			System.out.println("\"add 1 Call parents\"");
			System.out.println("Expected: Task 'Call parents' already exists in the list. == ");
			try {
				list.addTask("add 5 Call parents");
				list.addTask("add 1 Call parents");
			} catch (InvalidInputException e) {
				System.out.println(e.getMessage());
			}
			
			/*
			 * test equals() method and prove by printing hashCode()
			 */
			System.out.println();
			System.out.println("Creating 2 different tasks with the same description.");
			Task t1 = new Task (5, "Call parents");
			Task t2 = new Task (1, "Call parents");
			System.out.println("Expected: hashCode Task 1 = hashCode Task 2"
					+ "\n== ");
			if (t1.equals(t2)) { 
				System.out.print(t1.hashCode() + " = ");
				System.out.print(t2.hashCode());
			}
		} catch (InvalidInputException ie) {
			System.out.println(ie.getMessage());
		}
	}//end of main

}//end of class
