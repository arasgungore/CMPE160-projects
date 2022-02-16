
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

/**
 * Contains the Port class which implements IPort interface.
 */
package ports;

import interfaces.IPort;
import ships.Ship;
import containers.Container;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Port class which implements IPort interface.
 * @author Aras Güngöre
 *
 */
public class Port implements IPort {
	
	/**
	 * ID of the port.
	 */
	private int ID;
	/**
	 * X coordinate of the port's location.
	 */
	private double X;
	/**
	 * Y coordinate of the port's location.
	 */
	private double Y;
	/**
	 * An ArrayList which keeps track of the containers currently in the port.
	 */
	private ArrayList<Container> containers;
	/**
	 * An ArrayList which keeps track of every ship that has visited the port.
	 */
	private ArrayList<Ship> history;
	/**
	 * An ArrayList which keeps track of the ships currently in the port.
	 */
	private ArrayList<Ship> current;
	
	
	
	/**
	 * A constructor with three parameters, namely ID, X, and Y.
	 * @param ID ID of the port.
	 * @param X X coordinate of the port's location.
	 * @param Y Y coordinate of the port's location.
	 */
	public Port(int ID, double X, double Y) {
		this.ID = ID;
		this.X = X;
		this.Y = Y;
		containers = new ArrayList<Container>();
		history = new ArrayList<Ship>();
		current = new ArrayList<Ship>();
	}
	
	/**
	 * A method that calculates the distance between this port and another port.
	 * @param other The other port.
	 * @return Distance between two ports.
	 */
	public double getDistance(Port other) {
		final double X_diff = X - other.X;
		final double Y_diff = Y - other.Y;
		return Math.sqrt(X_diff * X_diff + Y_diff * Y_diff);
	}
	
	/**
	 * Adds the given ship to current ArrayList if it is not a duplicate.
	 * @param s Incoming ship.
	 */
	@Override
	public void incomingShip(Ship s) {
		if(!current.contains(s))
			current.add(s);
	}
	
	/**
	 * Adds the given ship to history ArrayList if it is not a duplicate.
	 * @param s Outgoing ship.
	 */
	@Override
	public void outgoingShip(Ship s) {
		if(!history.contains(s))
			history.add(s);
	}
	
	
	
	// *** Extra Methods ***
	
	/**
	 * Getter method for containers.
	 * @return An ArrayList which keeps track of the containers currently in the port.
	 */
	public ArrayList<Container> getContainers() {
		return containers;
	}
	
	/**
	 * Getter method for current.
	 * @return An ArrayList which keeps track of the ships currently in the port.
	 */
	public ArrayList<Ship> getCurrent() {
		return current;
	}
	
	/**
	 * Overrides the toString method and returns the port's ID, x and y coordinates, container IDs listed by their respective types,
	 * and the contents of the ships that are currently located in the port sorted by their IDs.
	 * @return A string which starts with the line “Port ID: (x, y)”, followed by IDs of containers located in
	 * the port: “{BasicContainer, HeavyContainer, RefrigeratedContainer, LiquidContainer}: [IDLIST]”.
	 * Note that Port and its contents are indented with 2 white spaces. Then, the contents of the ships are listed where IDs come in ascending order.
	 */
	@Override
	public String toString() {
		String str = String.format("Port %d: (%.2f, %.2f)\n", ID, X, Y);
		ArrayList<ArrayList<Container>> sorted = Container.getContainersSortedByType(containers);
		for(ArrayList<Container> conts : sorted)
			if(!conts.isEmpty()) {
				str += "  " + conts.get(0).getClass().getSimpleName() + ":";
				for(Container cont : conts)
					str += " " + cont.getID();
				str += "\n";
			}
		
		Collections.sort(current);
		for(Ship ship : current)
			str += ship.toString();
			
		return str;
	}
	
}

//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

