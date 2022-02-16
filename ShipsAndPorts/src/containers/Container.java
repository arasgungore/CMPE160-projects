
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

/**
 * Contains the abstract Container class and the other child class that extend the Container class.
 */
package containers;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Abstract Container class.
 * @author Aras Güngöre
 *
 */
public abstract class Container implements Comparable<Container> {
	
	/**
	 * ID of the container.
	 */
	protected int ID;
	/**
	 * Weight of the container.
	 */
	protected int weight;
	
	
	
	/**
	 * A constructor with two parameters, namely ID and weight.
	 * @param ID ID of the container.
	 * @param weight Weight of the container.
	 */
	public Container(int ID, int weight) {
		this.ID = ID;
		this.weight = weight;
	}
	
	/**
	 * An abstract method which returns fuel consumption required by the container.
	 * @return Fuel consumption required by the container.
	 */
	public abstract double consumption();
	
	/**
	 * An abstract method which checks type, ID, and weight of two containers.
	 * @param other Other container.
	 * @return TRUE if both containers have the same type, ID, and weight, FALSE otherwise.
	 */
	public abstract boolean equals(Container other);
	
	
	
	// *** Extra Methods ***
	
	/**
	 * Getter method for ID.
	 * @return ID of the container.
	 */
	public int getID() {
		return ID;
	}
	
	/**
	 * Getter method for weight.
	 * @return Weight of the container.
	 */
	public int getWeight() {
		return weight;
	}
	
	/**
	 * Compares containers according to their ID.
	 * @param other Other container.
	 * @return Sign of the difference between the other container's and this container's IDs.
	 */
	@Override
    public int compareTo(Container other) {
        return ID < other.ID ? -1 : (ID == other.ID ? 0 : 1);
    }
	
	/**
	 * Sorts the containers by ID then categorizes the containers by type.
	 * @param containers An ArrayList which contains containers of different types.
	 * @return An ArrayList which contains ArrayLists of containers where each ArrayList corresponds to a
	 * particular type of container and is sorted by ID.
	 */
	static public ArrayList<ArrayList<Container>> getContainersSortedByType(ArrayList<Container> containers) {
		Collections.sort(containers);
		ArrayList<Container> basic_conts = new ArrayList<Container>();
		ArrayList<Container> heavy_conts = new ArrayList<Container>();
		ArrayList<Container> refrigerated_conts = new ArrayList<Container>();
		ArrayList<Container> liquid_conts = new ArrayList<Container>();
		
		for(Container cont : containers)
			if(cont instanceof BasicContainer)
				basic_conts.add(cont);
			else if(cont instanceof RefrigeratedContainer)
				refrigerated_conts.add(cont);
			else if(cont instanceof LiquidContainer)
				liquid_conts.add(cont);
			else
				heavy_conts.add(cont);
		
		ArrayList<ArrayList<Container>> res = new ArrayList<ArrayList<Container>>();
		res.add(basic_conts);
		res.add(heavy_conts);
		res.add(refrigerated_conts);
		res.add(liquid_conts);
		
		return res;
	}
	
}

//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

