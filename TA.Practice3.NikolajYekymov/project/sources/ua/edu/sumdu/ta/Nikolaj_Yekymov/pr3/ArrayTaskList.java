package ua.edu.sumdu.ta.Nikolaj_Yekymov.pr3;

public class ArrayTaskList extends AbstractTaskList {
	
	private static final int increaseListNumber = 3;
	private static int listsNumber = 0;
	private static final String addToTitle= "[EDUCTR][TA] ";
	
	public ArrayTaskList() {
		
		listsNumber+=1;
	}
	
	public int getIncreaseListNumber() {
		return increaseListNumber;
	}
	
	public static int getListsNumber() {
		return listsNumber;
	}
	
	public void setTaskList(int i) {
		taskList = new Task[i];
	}
	
	public Task[] getTaskList() {
		return taskList;
	}
	
	public void add(Task task) {
		if(task != null){
			for(int i = 0; i < taskList.length; i++) {
				if(taskList[i] == null) {
					taskList[i] = task;
					String s = addToTitle + taskList[i].getTitle();
					taskList[i].setTitle(s);
					break;
				}
				else if((taskList.length - i) == 1 && taskList[i] != null) {
					cloneArray();
				}
			}
		}
	}
	
	public void cloneArray() {
		Task[] newList = new Task[taskList.length];
		for(int j = 0; j < newList.length; j++) {
			Task cloned = taskList[j].clone();
			newList[j] = cloned;
		}		
		setTaskList(newList.length + increaseListNumber);
		for(int l = 0; l < newList.length; l++) {
			Task clonedTwo = newList[l].clone();
			taskList[l] = clonedTwo;
		}
	}
	
	public void remove(Task task) {
		if(task != null) {
			for(int i = 0; i < taskList.length; i++) {
				if(taskList[i] != null) {
					if(taskList[i].equals(task)) {
						taskList[i] = null;
						break;
					} else System.out.println("Tasks are not equals");
				}
			}
		}
	}

	public Task getTask(int index) {
		if(index < 0 || size() < index) {
			return null;
		}
		return taskList[index];
	}
	
	public void addToArray(Task[] arr, Task t) {
		for(int i = 0; i < arr.length; i++) {
			if(arr[i] == null) {
				arr[i] = t;
				break;
			}
		}
	}
	public int needLength(int from, int to) {
		int n = 0;
		for(Task t : taskList) {
			if(t != null && t.isActive() == true) {
				if(t.getStartTime() > from && t.getStartTime() <= to) {
					n = n + 1;
				} else if(t.isRepeated() == true) {
					int res=t.getStartTime() + t.getRepeatInterval();
					if(res > from && res <= to) {
						n+=1;
					} else {
						while(res < t.getEndTime()) {
							res += t.getRepeatInterval();
							if ((t.getEndTime() - res) < 0) {
								break;
							} else if(res > from && res <= to) {
								n+=1;
								break;
							}
						}
					}
				}
			}
		}
		return n;
	}
	
	public Task[] incoming(int from, int to) {
		Task[] newArr = new Task[needLength(from,to)];
			for(Task t : taskList) {
				if(t != null && t.isActive() == true) {
					if(t.getStartTime() > from && t.getStartTime() <= to) {
						addToArray(newArr,t);
					} else if(t.isRepeated() == true) {
						int res=t.getStartTime() + t.getRepeatInterval();
						if(res > from && res <= to) {
							addToArray(newArr,t);
						} else {
							while(res < t.getEndTime()) {
								res += t.getRepeatInterval();
								if ((t.getEndTime() - res) < 0) {
									break;
								} else if(res > from && res <= to) {
									addToArray(newArr,t);
									break;
								}
							}
						}
					}
				}
			}
			return newArr;
	}
}