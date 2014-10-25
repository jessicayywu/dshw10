package dshw10;

public class Student {
	private String name;
	private int math;
	private int english;
	private double average;
	
	Student() {
		
	}
	
	Student(String name, int math, int english) {
		this.name = name;
		this.math = math;
		this.english = english;
	}
	
	public void setName(String name){
	    this.name = name;
	}
	
	public void setMath(int math){
		this.math = math;
	}
	
	public void setEnglish(int english){
	    this.english = english;
	}
	
	public void setAverage() {
		this.average = (double)(math + english) / 2;
	}
	
	public String getName(){
	    return name; 
	}
	
	public int getMath(){
	    return math;
	}
	
	public int getEnglish(){
	    return english;
	}
	
	public double getAverage() {
		return average;
	}
	
	public String getGrade() {
		switch((int)(math + english) / 20) {
			case 10:
			case 9:
				return "纔单";
			case 8:
				return "ヒ单";
			case 7:
				return "单";
			case 6:
				return "单";
			default:
				return "ぃの";
		}
	}
}
