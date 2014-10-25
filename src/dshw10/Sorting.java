package dshw10;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;

public class Sorting extends JFrame implements ActionListener{
	/** Create Components
	========================================================= */
	
	/** File
	--------------------------------- */
	
	/** Read a File */
	private JButton jbtRead = new JButton("讀取");
	private JLabel jlFileName = new JLabel("檔名：");
	private JTextField jtfFileName = new JTextField("");
	
	/** Save a File */
	private JButton jbtSaveAs = new JButton("另存新檔");
	
	/** Sorting
	--------------------------------- */
	private JLabel jlSortBy = new JLabel("選擇排序方式：");
	private String[] sortBy = {"姓名", "數學", "英文", "平均"};
	private JComboBox jcboSortBy = new JComboBox(sortBy);
	private JButton jbtSort = new JButton("排序");
	
	/** Searching
	--------------------------------- */
	private JLabel jlSearchName = new JLabel("輸入搜尋姓名：");
	private JTextField jtfSearchName = new JTextField("");
	private JButton jbtSearch = new JButton("搜尋");
	
	/** Results
	--------------------------------- */
	private JLabel jlSearchResult = new JLabel("搜尋結果：");
	private JTextArea jtaSearchResult = new JTextArea();
	private JLabel jlSortResult = new JLabel("排序結果：");
	private JTextArea jtaSortResult = new JTextArea();
	private JLabel jlProcess = new JLabel("過程：");
	private JTextArea jtaProcess = new JTextArea();
	
	/////////////////////////////////////////////////////////////
	
	/** Create an Array List
	========================================================= */
	ArrayList<Student> list = new ArrayList<Student>();
	
	/** =========================================================
		Constructor
	========================================================= */
	public Sorting() {
		/** Create Panels
		========================================================= */
		
		/** File
		--------------------------------- */
		JPanel p111 = new JPanel(new BorderLayout(5, 5));
		jtfFileName.setEnabled(false);
		jtfFileName.setBackground(Color.WHITE);
		
		p111.add(jlFileName, BorderLayout.WEST);
		p111.add(jtfFileName, BorderLayout.CENTER);
		
		JPanel p11 = new JPanel(new BorderLayout(5, 5));
		p11.add(jbtRead, BorderLayout.WEST);
		p11.add(p111, BorderLayout.CENTER);
		p11.add(jbtSaveAs, BorderLayout.EAST);
		
		/** Sorting
		--------------------------------- */	
		JPanel p121 = new JPanel(new GridLayout(1, 2, 5, 5));
		p121.add(jlSortBy);
		p121.add(jcboSortBy);
		
		JPanel p12 = new JPanel(new BorderLayout(40, 5));
		p12.add(p121, BorderLayout.CENTER);
		p12.add(jbtSort, BorderLayout.EAST);
		
		/** Searching
		--------------------------------- */
		JPanel p131 = new JPanel(new GridLayout(1, 2, 5, 5));
		p131.add(jlSearchName);
		p131.add(jtfSearchName);
		
		JPanel p13 = new JPanel(new BorderLayout(40, 5));
		p13.add(p131, BorderLayout.CENTER);
		p13.add(jbtSearch, BorderLayout.EAST);
		
		JPanel p1 = new JPanel(new GridLayout(3, 1, 5, 5));
		p1.add(p11);
		p1.add(p12);
		p1.add(p13);
		p1.setBorder(new EmptyBorder(7, 7, 7, 7));
		
		/** Results
		--------------------------------- */
		JPanel p21 = new JPanel(new BorderLayout(5, 5));
		jtaSearchResult.setEditable(false);
		jtaSearchResult.setBackground(Color.WHITE);
		
		JScrollPane jspSearchResult = new JScrollPane(jtaSearchResult);
		jspSearchResult.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		jspSearchResult.setVisible(true);
		
		p21.add(jlSearchResult, BorderLayout.NORTH);
		p21.add(jspSearchResult, BorderLayout.CENTER);
		
		JPanel p22 = new JPanel(new BorderLayout(5, 5));
		jtaSortResult.setEditable(false);
		jtaSortResult.setBackground(Color.WHITE);
		
		JScrollPane jspSortResult = new JScrollPane(jtaSortResult);
		jspSortResult.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		jspSortResult.setVisible(true);
		
		p22.add(jlSortResult, BorderLayout.NORTH);
		p22.add(jspSortResult, BorderLayout.CENTER);
		
		JPanel p23 = new JPanel(new BorderLayout(5, 5));
		jtaProcess.setEditable(false);
		jtaProcess.setBackground(Color.WHITE);
		
		JScrollPane jspProcess = new JScrollPane(jtaProcess);
		jspProcess.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		jspProcess.setVisible(true);
		
		p23.add(jlProcess, BorderLayout.NORTH);
		p23.add(jspProcess, BorderLayout.CENTER);
		
		JPanel p2 = new JPanel(new GridLayout(3, 1, 5, 5));
		p2.add(p21);
		p2.add(p22);
		p2.add(p23);
		p2.setBorder(new EmptyBorder(7, 7, 7, 7));
		
		setLayout(new BorderLayout(5, 20));
		add(p1, BorderLayout.NORTH);
		add(p2, BorderLayout.CENTER);
		
		/////////////////////////////////////////////////////////////
		
		/** Add Registers
		========================================================= */
		jbtRead.addActionListener(this);
		jbtSaveAs.addActionListener(this);
		jbtSort.addActionListener(this);
		jbtSearch.addActionListener(this);
	} // end of the constructor
	
	/** =========================================================
		Custom File Filter
	========================================================= */
	public class CustomFileFilter extends javax.swing.filechooser.FileFilter {
		private String description = null;
		private String extension = null;
		
		public CustomFileFilter(String description, String extension) {
			this.description = description;
			this.extension = extension;
		}
		
		public String getDescription() {
			return this.description;
		}
		
		public String getExtension() {
			return this.extension;
		}
		
		public boolean accept(File f) {
			if(f == null)
				return false;
			if(f.isDirectory())
				return true;
			
			return getName().toLowerCase().endsWith(extension);
		}
	} // end of CustomFileFilter
	
	/////////////////////////////////////////////////////////////

	/** =========================================================
		Listeners
	========================================================= */
	public void actionPerformed(ActionEvent e) {
		/** Read a File
		--------------------------------- */
		if(e.getSource() == jbtRead) {
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setDialogTitle("開啟檔案");
			fileChooser.setDialogType(JFileChooser.OPEN_DIALOG);
			fileChooser.setMultiSelectionEnabled(false);	
			fileChooser.addChoosableFileFilter(new CustomFileFilter("文字文件(*.txt)", "txt"));
			
			if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
				java.io.File file = fileChooser.getSelectedFile();
				jtaProcess.append("讀取" + file.getName() + "\n");
				jtfFileName.setText(file.getName());
				try {
					Scanner readFile = new Scanner(file);
					
					jtaProcess.append("=====================\n檔案內容：\n");
					
					while(readFile.hasNext()) {
						String line = readFile.nextLine();
						jtaProcess.append(line + "\n");
						Student student = new Student();
						student.setName(line.split(", ")[0]);
						student.setMath(Integer.parseInt(line.split(", ")[1]));
						student.setEnglish(Integer.parseInt(line.split(", ")[2]));
						student.setAverage();
						list.add(student);
					}
					readFile.close();
					jtaProcess.append("=====================\n共" + list.size() + "筆資料\n\n");
					
					
				} catch (FileNotFoundException e1) {
					JOptionPane.showMessageDialog(null, "讀檔錯誤", "錯誤", JOptionPane.ERROR_MESSAGE);
					jtaProcess.append("讀檔錯誤\n\n");
				}
			}
		}
		
		/** Save a File
		--------------------------------- */
		if(e.getSource() == jbtSaveAs) {
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setDialogTitle("另存新檔");
			fileChooser.setDialogType(JFileChooser.SAVE_DIALOG);
			fileChooser.setMultiSelectionEnabled(false);	
			fileChooser.addChoosableFileFilter(new CustomFileFilter("文字文件(*.txt)", "txt"));
			
			if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
				java.io.File file = fileChooser.getSelectedFile();
				try {
					java.io.PrintWriter writeFile = new java.io.PrintWriter(file);
					writeFile.print(jtaSortResult.getText());
					writeFile.close();
				} catch (FileNotFoundException e1) {
					JOptionPane.showMessageDialog(null, "存檔錯誤", "錯誤", JOptionPane.ERROR_MESSAGE);
				};
			}
		}
		
		/** Sorting
		--------------------------------- */
		if(e.getSource() == jbtSort) {
			Student[] students = list.toArray(new Student[list.size()]);
			switch(jcboSortBy.getSelectedIndex()) {
				case 0: 
					mergeSort(students);
					jtaProcess.append("姓名排序(Merge Sort)\n\n");
					jtaSortResult.append("姓名排序(Merge Sort)\n-----------------------\n");
					break;
				case 1:
					heapSort(students);
					jtaProcess.append("數學排序(Heap Sort)\n\n");
					jtaSortResult.append("數學排序(Heap Sort)\n-----------------------\n");
					break;
				case 2: 
					quickSort(students);
					jtaProcess.append("英文排序(Quick Sort)\n\n");
					jtaSortResult.append("英文排序(Quick Sort\n-----------------------\n");
					break;
				case 3: insertionSort(students);
						jtaProcess.append("平均排序(Insertion Sort)\n\n");
						jtaSortResult.append("平均排序(Insertion Sort)\n-----------------------\n");
			}
			
			for(int i = 0; i < students.length; i++) {
				String string = students[i].getName() + ", " +
						"Math: " + students[i].getMath() + ", " +
						"English: " + students[i].getEnglish() + ", " +
						"AVG: " + students[i].getAverage() + ", " +
						"(" + students[i].getGrade() + ")\n";
				jtaSortResult.append(string);
			}
			jtaSortResult.append("-----------------------\n");
		}
		
		/** Searching
		--------------------------------- */
		if(e.getSource() == jbtSearch) {
			jtaProcess.append("輸入姓名" + jtfSearchName.getText() + "，搜尋結果為：");
			
			Student[] students = list.toArray(new Student[list.size()]);
			mergeSort(students);
			int index = binarySearch(students, jtfSearchName.getText());
			if (index != -1) {
				String string = students[index].getName() + ", " +
						"Math: " + students[index].getMath() + ", " +
						"English: " + students[index].getEnglish() + ", " +
						"AVG: " + students[index].getAverage() + ", " +
						"(" + students[index].getGrade() + ")\n";
				
				jtaSearchResult.append(string);
				jtaProcess.append("搜尋成功\n");
			}
			else{
				jtaSearchResult.append("找不到" + jtfSearchName.getText() + "\n");
				jtaProcess.append("搜尋失敗\n");
			}
		}
	} // end of actionPerformed
	
	/////////////////////////////////////////////////////////////
	
	/** =========================================================
		Searching
	========================================================= */
	
	/** Binary Search
	--------------------------------- */
	public static int binarySearch(Student[] list, String name) {
		int low = 0, mid = 0, high = 0;
		high = list.length - 1;
		while(low <= high) {
			mid = (int)((low + high) / 2);
			if(list[mid].getName().compareTo(name) < 0)
				low = mid + 1;
			else if(list[mid].getName().compareTo(name) > 0)
				high = mid - 1;
			else
				return (mid);
		}
		return (-1);
		
	} // end of binarySearch
	
	/** =========================================================
		Sorting
	========================================================= */
	
	/** Merge Sort
	--------------------------------- */
	public static void mergeSort(Student[] list) {
		mergeSort(list, 0, list.length - 1);
	} // end of mergeSort
	
	/** The method for sorting the numbers */
	public static void mergeSort(Student[] list, int first, int last) { 
		if (first> last) {
			int center = (first + last) / 2;
			
			// Merge sort the first half
			mergeSort(list, first, center);
	
			// Merge sort the second half
			mergeSort(list, center + 1, last);
	
			// Merge firstHalf with secondHalf
			Student[] temp = merge(list, first, center, last);
			System.arraycopy(temp, 0, list, 0, temp.length);
		}
	} // end of mergeSort

	/** Merge Sort
	--------------------------------- */
	/** Merge two sorted lists */
	private static Student[] merge(Student[] list, int first, int center, int last) {
		Student[] temp = new Student[list.length];
		System.arraycopy(list, 0, temp, 0, list.length);
		
		int current1 = first; // Current index in list1
		int current2 = center + 1; // Current index in list2
		int current3 = first; // Current index in temp

		while (current1 <= center && current2 <= last) {
			if (list[current1].getName().compareTo(list[current2].getName()) < 0)
				temp[current3++] = list[current1++];
			else
				temp[current3++] = list[current2++];
		}

		while (current1 <= center)
			temp[current3++] = list[current1++];

		while (current2 <= last)
			temp[current3++] = list[current2++];
		
		return temp;
	} // end of merge
	
	/** Heap Sort
	--------------------------------- */
	public static void heapSort(Student[] list) {
		Student[] heap = new Student[list.length + 1];
		System.arraycopy(list, 0, heap, 1, list.length);
		
		int heapSize = list.length;
		
		buildHeap(heap);
		for(int i = list.length; i >= 2; i--) {
			Student temp = new Student();
			temp = heap[1];
        	heap[1] = heap[i];
        	heap[i] = temp;
        	heapSize--;
        	heapify(heap, 1, heapSize);
		}
		System.arraycopy(heap, 1, list, 0, heap.length - 1);
	} // end of heapSort
	
	private static void buildHeap(Student[] heap) {
		int heapSize = heap.length - 1;
		for(int i = heapSize / 2; i >= 1; i--) {
			heapify(heap, i, heapSize);
		}
	} // end of buildHeap

	private static void heapify(Student[] heap, int index, int heapSize) {
		int left = 2 * index;
	    int right = 2 * index + 1;
	    int largest = index;
	    
	    if(left <= heapSize) {
		    if(heap[left].getMath() > heap[index].getMath())
		    	largest = left;
	    }
	    
	    if(right <= heapSize) {    	
	    	if(heap[right].getMath() > heap[largest].getMath())
	    		largest = right;
	    }
	    
	    if(largest != index) {
	    	Student temp = new Student();
	    	temp = heap[index];
        	heap[index] = heap[largest];
        	heap[largest] = temp;
        	heapify(heap, largest, heapSize);
	    }
	} // end of heapify
	  
	/** Quick Sort
	--------------------------------- */
	public static void quickSort(Student[] list) {
		quickSort(list, 0, list.length - 1);
	} // end of quickSort

	private static void quickSort(Student[] list, int first, int last) {
		if (first < last) {
			int pivotIndex = partition(list, first, last);
			quickSort(list, first, pivotIndex);
			quickSort(list, pivotIndex + 1, last);
		}
	} // end of quickSort

	/** Partition the array list[first..last] */
	private static int partition(Student[] list, int first, int last) {
		int pivot = list[first].getEnglish(); // Choose the first element as the pivot
		int low = first - 1; // Index for forward search
		int high = last + 1; // Index for backward search

		while (true) {		     
			// Search backward from right
			do {
				high--;
			} while(list[high].getEnglish() > pivot);
		    	
			// Search forward from left
			do {
				low++;
			} while(list[low].getEnglish() < pivot);

			if(low < high) {
				// Swap two elements in the list
				Student temp = new Student();
		        temp = list[low];
		        list[low] = list[high];
		        list[high] = temp;
			}
			else 
				return high;
		}
	}
		  
	/** Insertion Sort
	--------------------------------- */
	public static void insertionSort(Student[] list) {
		for(int j = 1; j < list.length; j++) {
			Student key = list[j];
			int i = j - 1;
					
			while(i >= 0 && list[i].getAverage() > key.getAverage())
				list[i + 1] = list[i--];				
				list[i + 1] = key;
			}
	} // end of insertionSort

	/////////////////////////////////////////////////////////////
	
	/** =========================================================
		Main Method
	========================================================= */
	public static void main(String[] args) {
		Sorting frame = new Sorting();
		frame.setSize(350,500); // Set the frame size
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	} // end of the main method
} // end of the class
