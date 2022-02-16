
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

/**
 * Contains the abstract Container class and the other child class that extend the Container class.
 */
package containers;

/**
 * Basic Container class which extends Container class.
 * @author Aras Güngöre
 *
 */
public class BasicContainer extends Container {
	
	/**
	 * A constructor with two parameters, namely ID and weight.
	 * @param ID ID of the basic container.
	 * @param weight Weight of the basic container.
	 */
	public BasicContainer(int ID, int weight) {
		super(ID, weight);
	}
	
	/**
	 * Returns fuel consumption required by the basic container.
	 * @return Fuel consumption required by the basic container.
	 */
	@Override
	public double consumption() {
		return 2.50 * weight;
	}
	
	/**
	 * Checks type, ID, and weight of two containers.
	 * @param other Other container.
	 * @return TRUE if both containers have the same type, ID, and weight, FALSE otherwise.
	 */
	@Override
	public boolean equals(Container other) {
		return other.getClass().getSimpleName().equals("BasicContainer") && ID == other.ID && weight == other.weight;
	}
	
}

//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

