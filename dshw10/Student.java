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
				return "�u��";
			case 8:
				return "�ҵ�";
			case 7:
				return "�A��";
			case 6:
				return "����";
			default:
				return "���ή�";
		}
	}
}
