package dshw10;

public class MergeSort {
	public static void mergeSort(int[] list) {
		mergeSort(list, 0, list.length - 1);
	}
	
	/** The method for sorting the numbers */
	public static void mergeSort(int[] list, int first, int last) { 
		if (first < last) {
			int center = (first + last) / 2;
			
			// Merge sort the first half
			mergeSort(list, first, center);
	
			// Merge sort the second half
			mergeSort(list, center + 1, last);
	
			// Merge firstHalf with secondHalf
			int[] temp = merge(list, first, center, last);
			System.arraycopy(temp, 0, list, 0, temp.length);
		}
	}

	  /** Merge two sorted lists */
	private static int[] merge(int[] list, int first, int center, int last) {
		int[] temp = new int[list.length];
		System.arraycopy(list, 0, temp, 0, list.length);
		
		int current1 = first; // Current index in list1
		int current2 = center + 1; // Current index in list2
		int current3 = first; // Current index in temp

		while (current1 <= center && current2 <= last) {
			if (list[current1] < list[current2])
				temp[current3++] = list[current1++];
			else
				temp[current3++] = list[current2++];
		}

		while (current1 <= center)
			temp[current3++] = list[current1++];

		while (current2 <= last)
			temp[current3++] = list[current2++];
		
		return temp;
	}

	  /** A test method */
	  public static void main(String[] args) {
	    int[] list = {2, 3, 2, 5, 6, 1, -2, 3, 14, 12};
	    mergeSort(list);
	    for (int i = 0; i < list.length; i++)
	      System.out.print(list[i] + " ");
	  }
	}
