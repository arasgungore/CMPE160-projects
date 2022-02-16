
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

/**
 * Contains the abstract Container class and the other child class that extend the Container class.
 */
package containers;

/**
 * Heavy Container class which extends Container class.
 * @author Aras Güngöre
 *
 */
public class HeavyContainer extends Container {
	
	/**
	 * A constructor with two parameters, namely ID and weight.
	 * @param ID ID of the heavy container.
	 * @param weight Weight of the heavy container.
	 */
	public HeavyContainer(int ID, int weight) {
		super(ID, weight);
	}
	
	/**
	 * Returns fuel consumption required by the heavy container.
	 * @return Fuel consumption required by the heavy container.
	 */
	@Override
	public double consumption() {
		return 3.00 * weight;
	}
	
	/**
	 * Checks type, ID, and weight of two containers.
	 * @param other Other container.
	 * @return TRUE if both containers have the same type, ID, and weight, FALSE otherwise.
	 */
	@Override
	public boolean equals(Container other) {
		return other.getClass().getSimpleName().equals("HeavyContainer") && ID == other.ID && weight == other.weight;
	}
	
}

//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

