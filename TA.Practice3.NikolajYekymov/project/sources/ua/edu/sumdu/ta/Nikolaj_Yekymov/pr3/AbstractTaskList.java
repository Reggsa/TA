package ua.edu.sumdu.ta.Nikolaj_Yekymov.pr3;

abstract class AbstractTaskList {
	
	public Task[] taskList = new Task[10];
	
	abstract void add(Task task);
	
	abstract void remove(Task task);
	
	abstract Task getTask(int index);
	
	public int size() {
		int a = 0;
		for(int i = 0; i < taskList.length; i++) {
			if(taskList[i] != null) {
				a+=1;
			}
		}
		return a;
	}
	
}