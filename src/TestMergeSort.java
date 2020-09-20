
public class TestMergeSort {
	
	public static void main(String[] args) {
		String[] array = {"Lisa", "Adam", "John", "Vicky", "George", "Beth", "Kate", "Aaron", "Jinny"};
		
		System.out.print("Given array: ");
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
		
		mergeSort(array);		
		System.out.print("Sorted array: ");
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        } 
        System.out.println();
        
        //Checks to see if mergeSort actually works to match the correct array
        /*
         String[] correct = {"Aaron", "Adam", "Beth", "George", "Jinny", "John", "Kate", "Lisa", "Vicky"};
		 boolean same = true;
         while(same) {
        	for(int i = 0; i < array.length; i++) {
        		if(array[i].equals(correct[i])) {
        			same = true;
        		}else {
        			same = false;
                	System.out.println();
        			System.out.println("Not Correct!");
        			return;
        		}
        	}
        	System.out.println();
        	System.out.println("Correct!");
        	return;
        }
        */
        
	}
	
	public static <T> void mergeSort(T[] array) {
		@SuppressWarnings("unchecked")
		T[] tempArr = (T[]) new Object[array.length];
		divideIntoHalf(array, tempArr, 0, array.length - 1);
	}
	
	//helper methods
	private static <T> void divideIntoHalf(T[] array, T[] tempArr, int start, int end) {
		if(start < end) {
			int mid = (start + end) / 2;
			divideIntoHalf(array, tempArr, start, mid);
			divideIntoHalf(array, tempArr, mid + 1, end);
			mergeHalves(array, tempArr, start, mid, end);
		}
		
	}
	private static <T> void mergeHalves(T[] array, T[] tempArr, int start, int mid, int end) {
		int f1 = start, l1 = mid;
		int f2 = mid + 1, l2 = end;
		
		int index = f1;
		
		while(f1 <= l1 && f2 <= l2) {
			if(((String) array[f1]).compareTo((String) array[f2]) < 0) {
				tempArr[index] = array[f1++];
			}else {
				tempArr[index] = array[f2++];
			}
			index++;
		}
		
		while(f1 <= l1) {
			tempArr[index] = array[f1++];
			index++;
		}
		
		while(f2 <= l2) {
			tempArr[index] = array[f2++];
			index++;
		}
		
		for(index = start; index <= end; index++) {
			array[index] = tempArr[index];
		}
	}
}