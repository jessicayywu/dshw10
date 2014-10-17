package dshw10;

public class QuickSort {
	  public static void quickSort(int[] list) {
	    quickSort(list, 0, list.length - 1);
	  }

	  private static void quickSort(int[] list, int first, int last) {
	    if (first < last) {
	      int pivotIndex = partition(list, first, last);
	      quickSort(list, first, pivotIndex);
	      quickSort(list, pivotIndex + 1, last);
	    }
	  }

	  /** Partition the array list[first..last] */
	  private static int partition(int[] list, int first, int last) {
	    int pivot = list[first]; // Choose the first element as the pivot
	    int low = first - 1; // Index for forward search
	    int high = last + 1; // Index for backward search

	    while (true) {
			     
		      // Search backward from right
		      do {
		        high--;
		      } while(list[high] > pivot);
	    	
		      // Search forward from left
			     do {
		        low++;
			     } while(list[low] < pivot);

	      if(low < high) {
	      // Swap two elements in the list
	        int temp = list[low];
	        list[low] = list[high];
	        list[high] = temp;
	      }
	      else 
	    	  	return high;
	    }
	 }

	  /** A test method */
	  public static void main(String[] args) {
	    int[] list = {2, 3, 2, 5, 6, 1, -2, 3, 14, 12};
	    quickSort(list);
	    for (int i = 0; i < list.length; i++)
	      System.out.print(list[i] + " ");
	  }
	}

