package pobj.tools;

import java.util.ArrayList;
import java.util.Random;

public class ArrayListTools <T> {
	public void mixArrayList(ArrayList<T> tab){
		
		Random r = new Random();
		for(int i=0; i<tab.size(); i++){
			int newIndex = r.nextInt(tab.size());
			T buf = tab.get(i);
			tab.set(i, tab.get(newIndex));
			tab.set(newIndex, buf);
		}
	}
}
