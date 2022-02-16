
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

/**
 * Contains the abstract Container class and the other child class that extend the Container class.
 */
package containers;

/**
 * Refrigerated Container class which extends the Heavy Container class.
 * @author Aras Güngöre
 *
 */
public class RefrigeratedContainer extends HeavyContainer {
	
	/**
	 * A constructor with two parameters, namely ID and weight.
	 * @param ID ID of the refrigerated container.
	 * @param weight Weight of the refrigerated container.
	 */
	public RefrigeratedContainer(int ID, int weight) {
		super(ID, weight);
	}
	
	/**
	 * Returns fuel consumption required by the refrigerated container.
	 * @return Fuel consumption required by the refrigerated container.
	 */
	@Override
	public double consumption() {
		return 5.00 * weight;
	}
	
	/**
	 * Checks type, ID, and weight of two containers.
	 * @param other Other container.
	 * @return TRUE if both containers have the same type, ID, and weight, FALSE otherwise.
	 */
	@Override
	public boolean equals(Container other) {
		return other.getClass().getSimpleName().equals("RefrigeratedContainer") && ID == other.ID && weight == other.weight;
	}
	
}

//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

