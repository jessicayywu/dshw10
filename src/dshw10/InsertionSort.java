package dshw10;

public class InsertionSort {
	public static void insertionSort(int[] list) {
		for(int j = 1; j < list.length; j++) {
			int key = list[j];
			int i = j - 1;
			
			while(i >= 0 && list[i] > key)
				list[i + 1] = list[i--];
			
			list[i + 1] = key;
		}
	}
	
	  /** A test method */
	  public static void main(String[] args) {
	    int[] list = {2, 3, 2, 5, 6, 1, -2, 3, 14, 12};
	    insertionSort(list);
	    for (int i = 0; i < list.length; i++)
	      System.out.print(list[i] + " ");
	  }
}
