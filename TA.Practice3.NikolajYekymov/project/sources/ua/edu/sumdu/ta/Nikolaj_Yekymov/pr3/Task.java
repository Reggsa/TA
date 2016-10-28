package ua.edu.sumdu.ta.Nikolaj_Yekymov.pr3;

import ua.edu.sumdu.ta.Nikolaj_Yekymov.pr3.ArrayTaskList;

/**
 * This class describes the data type "Task", which contains information about the essence of the task, 
 * its status (active / inactive), time interval, through which it is needed to repeat the notification about it
 * @version    1.0    20 Oct 2016
 * @author    Nikolaj Yekymov
 */
public class Task implements Cloneable {
	
	private String $title;
	private boolean $isActive,$isRepeated;
	private int $time, $start, $end, $repeat;
	
       /**
	* call setTitle method 
	* call setTime method
	* @param title $title variable initialization
	* @param time $time variable initialization
	*/
	public Task(String title, int time) {
		this.setTitle(title);
		this.setTime(time);
	}
	
       /**
	* call setTitle method
	* call setTime method
	* @param title $title variable initialization
	* @param start $start variable initialization
	* @param end $end variable initialization
	* @param repeat $repeat variable initialization
	*/
	public Task(String title, int start, int end, int repeat) {
		this.setTitle(title);
		this.setTime(start, end, repeat);
	}
	
	public Task() {}
	
       /**
	* @return value of $title variable
	*/
	public String getTitle() {
		return $title;
	}
	
       /**
	* @param title $title variable initialization
	*/
	public void setTitle(String title) {
		if (title != null) {
			$title = title;
		} else {
			System.out.println("Invalid argument for setTitle method");			
		}
	}
	
       /**
	* @return value of $isActive variable
	* $isActive - status of the Task(active / inactive)
	*/	
	public boolean isActive() {
		return $isActive;	
	}
	
   /**
	* Set status for the Task
	* @param active $isActive variable initialization
	*/
	public void setActive(boolean active) {
			$isActive = active;
	}

   /**
	* Set notification time for the Task
	* @param time $time variable initialization
	* $isRepeated variable initialization
	*/
	public void setTime(int time) {
		if (time > 0){
			$isRepeated = false;
			$time = time;
		} else {
			System.out.println("Invalid argument for setTime method of Task - " + this.$title);
		}
	}

   /**
	* Set start, end, repeat interval of the notification time for the Task
	* @param start $start variable initialization
	* @param end $end variable initialization
	* @param repeat $repeat variable initialization
	* $isRepeated variable initialization
	*/
	public void setTime(int start, int end, int repeat) {
		if (start >= 0 && start < end) {
			$isRepeated = true;
			$start = start;
			$end = end;
			$repeat = repeat;
		} else {
			System.out.println("Invalid arguments for setTime method of Task - " + this.$title);
		}
	}
	
   /**
	* @return value of $time variable (for not repeated Task)
	* @return value of $start variable (for repeated Task)
	*/
	public int getTime() {
		int result = ($isRepeated)? $start : $time;
		return result;
	}

   /**
	* @return value of $time variable (for not repeated Task)
	* @return value of $start variable (for repeated Task)
	*/
	public int getStartTime() {
		int result = (!$isRepeated)? $time : $start;
		return result;
	}
	
   /**
	* @return value of $time variable (for not repeated Task)
	* @return value of $end variable (for repeated Task)
	*/
	public int getEndTime() {
		int result = (!$isRepeated)? $time : $end;
		return result;
	}
	
   /**
	* @return value of $repeat variable (for repeated Task)
	* @return 0 (for not repeated Task)
	*/
	public int getRepeatInterval() {
		int result = (!$isRepeated)? 0 : $repeat;
		return result;
	}

   /**
	* @return value of $isRepeated variable
	*/
	public boolean isRepeated() {
		return $isRepeated;
	}
	
   /**
	* @return description of the Task
	*/
	public String toString() {
		String task = "Task \"" + getTitle();
		if (!$isActive) {
			task += "\" is inactive";
		} else if (!$isRepeated) {
			task += "\" at " + getTime();
		} else if ($isRepeated) {
			task += "\" from " + getStartTime() + " to " + getEndTime() + " every " + getRepeatInterval()+" seconds";
		}
		return task;
	}
	
   /**
	* @return the next notification time regarding the specified time
	* @param time specified time 
	*/
	public int nextTimeAfter(int time) {
		int res = -1;
		if (time >= 0) {
			if ($isActive) {
				if ($isRepeated) {
					if (time >= $start && time < $end) {
					  /*res = (((time - $start)/$repeat)+1)*$repeat + $start;
						if(res > $end){res = -1;}*/
						res=$start+$repeat;
						while(time >= res) {
							res += $repeat;
							if (($end - res) < 0) {
								res = -1;
								break;
							}
						}
					} else if (time < $start) {
						res = $start;
					}
				} else if (($time - time) > 0) {
						res = $time;
				}
			}
		}
		return res;
	}
	
	@Override
	public Task clone() {
		try {
			return (Task)super.clone();
		}
		catch( CloneNotSupportedException ex ) {
			throw new InternalError();
		}
	}
	
	@Override
	public int hashCode() {
		int result = 1000 + getTime() + getStartTime() + getEndTime() + getRepeatInterval();
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Task objTask = (Task) obj;
		if (getTime() != objTask.getTime())
			return false;
		if (getStartTime() != objTask.getStartTime())
			return false;
		if (getEndTime() != objTask.getEndTime())
			return false;
		if (getRepeatInterval() != objTask.getRepeatInterval())
			return false;
		if (isRepeated() != objTask.isRepeated())
			return false;
		if (getTitle() != objTask.getTitle())
			return false;
		return true;
	}
	
	public static void main(String[] args) {
		
	}
}
