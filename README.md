# Todo_List
Using Java Collection - Priority Queue to implement a ToDo List program

Implement a to do list. Tasks have a priority between 1 and 9, and a description.
When the user enters the command add priority description, create a task with the the priority and the description entered in the command. Then the program adds a new task.
When the user enters next, the program removes and prints the most urgent task.
The quit command ends the program.
Use a priority queue in your solution.
Override the hashcode() and equals() so that two tasks with the same description are considered equal. Make sure to ignore the case of the characters.
If the new task already exists in the priority queue, let the user know that and don't add it again. Additionally, print out each object's hashcode to prove it.

Sample Output:
To Do List - Please enter an option
  add priority description (add a new task)
  next (remove and print most urgent task)
  quit (exit this program)

> add 3 description of new task
> add 4 even newer task
> add 2 least important task
> next
least important task
> next
description of new task
> next
even newer task
> quit
Press any key to continue . . .
