package dshw10;

public class HeapSort {
	public static void heapSort(int[] list) {
		int[] heap = new int[list.length + 1];
		System.arraycopy(list, 0, heap, 1, list.length);
		
		int heapSize = list.length;
		
		buildHeap(heap);
		for(int i = list.length; i >= 2; i--) {
			int temp = heap[1];
        	heap[1] = heap[i];
        	heap[i] = temp;
        	heapSize--;
        	heapify(heap, 1, heapSize);
		}
		System.arraycopy(heap, 1, list, 0, heap.length - 1);
	  }
	
	private static void buildHeap(int[] heap) {
		int heapSize = heap.length - 1;
		for(int i = heapSize / 2; i >= 1; i--) {
			heapify(heap, i, heapSize);
		}
	}

	  private static void heapify(int[] heap, int index, int heapSize) {
	    int left = 2 * index;
	    int right = 2 * index + 1;
	    int largest = index;
	    
	    if(left <= heapSize) {
		    if(heap[left] > heap[index])
		    	largest = left;
	    }
	    
	    if(right <= heapSize) {    	
	    	if(heap[right] > heap[largest])
	    		largest = right;
	    }
	    
	    if(largest != index) {
	    	int temp = heap[index];
        	heap[index] = heap[largest];
        	heap[largest] = temp;
        	heapify(heap, largest, heapSize);
	    }
	  }

	  /** A test method */
	  public static void main(String[] args) {
	    int[] list = {2, 3, 2, 5, 6, 1, -2, 3, 14, 12};
	    heapSort(list);
	    for (int i = 0; i < list.length; i++)
	      System.out.print(list[i] + " ");
	  }
}
