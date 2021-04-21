import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Queue;
/*
 * @author Group 3: Tien N., Victoria T., Niklas K.
 */
public class ToDoList {
	private Queue<Task> list;
	/**
	 * default constructor creates a new Priority Queue to hold task items
	 */
	public ToDoList() {
		this.list = new PriorityQueue<>();
	}
	/**
	 * @param a String of input for adding a task
	 * @return true if the task is added successfully to the Prioriry Queue list
	 * @return false if String is in invalid format or already exits in the list
	 * @throws InvalidInputException
	 */
	public boolean addTask(String task) throws InvalidInputException{
		boolean added = false;
		String[] strArray = task.split("\\s+");
		// check if command is valid in this format, 
		// ["add"] [priority number] [description]
		if (strArray.length < 3) {
			throw new InvalidInputException ("! Invalid command." 
					+ "\nPlease check priority number, task description and try again.");
		}
		// if format passes the first check
		// try to parseInt index 1 to check if task has a priority number
		try {
			int priority = Integer.parseInt(strArray[1]);
			String description = "";
			for (int i = 2; i<strArray.length; i++) {
				description += strArray[i] + " ";
			}
			// if it's an integer, check the bounds from 1 to 9
			// if it's an integer from 1 to 9, check the description for duplicated
			if (priority >= 1 && priority <= 9) {
				Task newTask = new Task(priority, description.trim());
				int count = list.size();
				Iterator<Task> it = list.iterator();
				while (it.hasNext()) {
					if (newTask.equals(it.next())) {
						// if found duplicated description, throw a warning message
						throw new InvalidInputException("! Task '" + description.trim() + "' already exists in the list.");
					}
					else {
						count--;
					}
				}
				// count = 0 means no duplicated
				if (count == 0) {
					list.add(newTask);
					added = true;
				}
			}
			// out of bounds 1 to 9, throw a warning message
			else {
				throw new InvalidInputException("! The priority must be an integer between 1 and 9.");
			}
		} 
		// throw a warning message if it's not an integer
		catch (NumberFormatException e) {
			throw new InvalidInputException("! The priority must be an integer between 1 and 9.");
		}
		return added;
	}
	/**
	 * if the list is empty, throw a warning message
	 * otherwise, return the top most urgent task in the list based on its priority number
	 * @return the most priority Task object
	 * @throws InvalidInputException
	 */
	public Task nextTask() throws InvalidInputException{
		if (list.isEmpty()) {
			throw new InvalidInputException("! There are no tasks in the list.");
		}
		else {
			return list.poll();
		}
	}
	/**
	 * return true/false if the Priority Queue is empty
	 * @return true if it's empty
	 * @return false if it's not empty
	 */
	public boolean isEmpty() {
		if (this.list.isEmpty()) {
			return true;
		}
		else {
			return false;
		}
	}
	@Override
	/**
	 * description all items in the list
	 */
	public String toString() {
		Iterator<Task> it = list.iterator();
		String str = "";
		if (list.isEmpty()) {
			str = "! There are no tasks in the list.";
		}
		else {
			while (it.hasNext()) {
				str += "~" + it.next() + "\n";
			}
		}
		return str;
	}
}//end of class
