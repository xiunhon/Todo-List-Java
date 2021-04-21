/*
 * @author Tien N.
 * A task object has 2 attributes:
 * a priority number and a description
 */
public class Task implements Comparable<Task>{
	private int aPriority;
	private String aDescription;
	/**
	 * overloaded constructor
	 * @param pPriority
	 * @param pDescription
	 */
	public Task(int pPriority, String pDescription) {
		aPriority = pPriority;
		aDescription = pDescription;
	}
	@Override
	/**
	 * compare 2 tasks by their priority number
	 * @return negative integer if task1 < task2
	 * @return 0 if task1 = task2
	 * @return positive integer if task1 > task2
	 */
	public int compareTo(Task pTask) { 
		String task1 = String.valueOf(this.aPriority) + " " + this.aDescription;
		String task2 = String.valueOf(pTask.aPriority) + " " + pTask.aDescription;
		return task1.compareTo(task2);
	}
	@Override
	/**
	 * 2 tasks are equal if they have the same description
	 * @return true if equals
	 * @return false if not equals
	 */
	public boolean equals(Object o) {
		Task otherTask = (Task) o;
		if (this.aDescription.equalsIgnoreCase(otherTask.aDescription)) {
			return true;
		}
		else {
			return false;
		}
	}
	@Override
	/**
	 * corresponding with equals() method
	 * @return a task's hashCode based on its description
	 */
	public int hashCode() {
		return this.aDescription.hashCode();
	}
	@Override
	/**
	 * @return description of a task
	 */
	public String toString() {
		return this.aDescription.trim();
	}
}//end of class
