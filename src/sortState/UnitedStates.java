package sortState;

import java.util.*;
import java.io.*; 

public class UnitedStates
{
	// instance variables
	private ArrayList <State> states;
	
	public UnitedStates()
	{
	   states = new ArrayList <State> ();
	   
	   readFile();
	   printStates();
	   
	   System.out.println();
	   System.out.println("=========================");
	   System.out.println("  Sorted by State");
	   System.out.println("=========================");
	   printStates();	
	   
	   menu();
	}
	
	
	
	/*
	 * Merge Sort
	 * 
	 * Use a merge sort to order the ArrayList
	 * by the state's name
	 */
	public void sortStates(int front, int back) {
		
		int mid = (front+back)/2;
		if (front < back) {
			sortStates(front, mid);
			sortStates(mid+1, back);
			merge(front, mid, back);
		}	
	}
	
	
	
	
	public void merge(int front, int mid,int back) {
		State[] states2 = new State[states.size()];
		int i = front;
		int j = mid +1;
		int k = front;
		
		while (i <= mid && j <= back) {
			if (states.get(i).getName().compareTo(states.get(j).getName()) < 0) {
				states2[k] = states.get(i);
				i++;
			}
			else {
				states2[k] = states.get(j);
				j++;
			}
			k++;
		}
		
		while (i <= mid) {
			states2[k] = states.get(i);
			i++;
			k++;
			
		}
		
		while (j <= back) {
			states2[k] = states.get(j);
			j++;
			k++;
			
		}
		for (int l = front ; l <= back; l++) {
			states.set(l, states2[l]);
		}
		
		
		
		
	}

	/*
	 * Quick Sort
	 * 
	 * Use a selection sort to order the ArrayList by the state's capital
	 */
	public void sortCapitals(int low, int high) {

		if (high - low > 0) {
			State piv = (State) states.get(low);
			
			
			ArrayList<State> statesLower = new ArrayList();
			ArrayList<State> statesHigher = new ArrayList();
			
			for(int i = low + 1; i < high; i++) {
				if(states.get(i).getCapital().compareTo(piv.getCapital())>0) {
					statesHigher.add(states.get(i));
				}
				else {
					statesLower.add(states.get(i));
				}
			}
			int savespot = 0;
			for(int i = 0; i < statesLower.size(); i++) {
				states.set(i+ low, statesLower.get(i));
				savespot = i + low;
			} 
			
			states.set(savespot + 1, piv);
			
			for (int i = 0; i < statesHigher.size();i++) {
				states.set(i + savespot + 2, statesHigher.get(i));
				
			}
			

			
			
			
		
			
			
			if (savespot > low) {
				sortCapitals(low, savespot);
			}
			if (savespot + 2 < high) {
				sortCapitals(savespot + 2 ,high);
			}
		}
		
		
		
	}
	
	
	
	
	

    public void menu()
	{
		
		boolean quitSelected = false;
		
		while (!quitSelected) {
			Scanner inKey = new Scanner(System.in);
			
			System.out.println("\n");
			System.out.println("=========================");
			System.out.println("          Menu");
			System.out.println("=========================");
			System.out.println("  1 - Sort by State");
			System.out.println("  2 - Sort by Capital");
			System.out.println("  3 - Exit");
			System.out.print("\n   Enter Selection: ");
			
			
			int choice = inKey.nextInt();
			
			
			switch (choice) {
			case (1):
	
				System.out.println("\n");
				System.out.println("=========================");
				System.out.println("  Sorted by State");
				System.out.println("=========================");
				sortStates(0, states.size() - 1);
				printStates();
				
				break;
			case (2):
	
				System.out.println("\n");
				System.out.println("=========================");
				System.out.println("  Sorted by Capital");
				System.out.println("=========================");
				sortCapitals(0, states.size() - 1);
				printStates();	
				
				break;
			case (3):
				System.out.println("\n\nGoodbye!");
				quitSelected = true;
				break;
			default:
				System.out.println("\nNot a valid Choice:");
				
	
			}
		
		}
		

	}
	
	
	
	
	
	public void printStates()
	{
		for(State s : states)
		{
			System.out.printf("%-15s", s.getName());
			System.out.printf("%-15s", s.getCapital());
			System.out.printf("%-25s", s.getNickname());
			System.out.printf("%10s\n", s.getPopulation());	
		}
	}
	
	public void readFile()
	{
		Scanner scan = null;
		try
		{
			scan = new Scanner(new File("states.txt"));
		}
		catch(FileNotFoundException ex)
		{
			System.out.println("File not Found!");
		}
		
		String name;
		String capital;
		String nickname;
		int population;
		while(scan.hasNextLine())
		{
			name = scan.nextLine();
			capital = scan.nextLine();
			nickname = scan.nextLine();
			population = scan.nextInt();
			if(scan.hasNextLine())
			{
			   String dummy = scan.nextLine();	
			}
			  
			
			State state = new State(name, capital, nickname, population);
			states.add(state);
		}
		
		
		
	}
}