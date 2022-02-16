
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

/**
 * Contains the Ship class which implements IShip interface.
 */
package ships;

import interfaces.IShip;
import ports.Port;
import containers.*;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Ship class which implements IShip interface.
 * @author Aras Güngöre
 *
 */
public class Ship implements IShip, Comparable<Ship> {
	
	/**
	 * ID of the ship.
	 */
	private int ID;
	/**
	 * Fuel of the ship.
	 */
	private double fuel;
	/**
	 * The port which the ship is currently located at.
	 */
	private Port currentPort;
	
	// *** Extra Fields ***
	
	/**
	 * Containers carried by the ship.
	 */
	private ArrayList<Container> containers;
	/**
	 * Total weight capacity of the ship.
	 */
	private int totalWeightCapacity;
	/**
	 * Maximum total number of containers the ship can carry at once.
	 */
	private int maxNumberOfAllContainers;
	/**
	 * Maximum number of heavy containers the ship can carry at once.
	 */
	private int maxNumberOfHeavyContainers;
	/**
	 * Maximum number of refrigerated containers the ship can carry at once.
	 */
	private int maxNumberOfRefrigeratedContainers;
	/**
	 * Maximum number of liquid containers the ship can carry at once.
	 */
	private int maxNumberOfLiquidContainers;
	/**
	 * Fuel consumption rate per kilometer of the ship.
	 */
	private double fuelConsumptionPerKM;
	
	/**
	 * Current total weight the ship is carrying.
	 */
	private int totalWeight;
	/**
	 * Total number of containers the ship is carrying.
	 */
	private int numberOfAllContainers;
	/**
	 * Total number of heavy containers the ship is carrying.
	 */
	private int numberOfHeavyContainers;
	/**
	 * Total number of refrigerated containers the ship is carrying.
	 */
	private int numberOfRefrigeratedContainers;
	/**
	 * Total number of liquid containers the ship is carrying.
	 */
	private int numberOfLiquidContainers;
	
	
	
	/**
	 * A constructor with eight parameters.
	 * @param ID ID of the ship.
	 * @param p The port where the ship is currently located at.
	 * @param totalWeightCapacity Total weight capacity of the ship.
	 * @param maxNumberOfAllContainers Maximum number of all containers the ship can carry at once.
	 * @param maxNumberOfHeavyContainers Maximum number of heavy containers the ship can carry at once.
	 * @param maxNumberOfRefrigeratedContainers Maximum number of refrigerated containers the ship can carry at once.
	 * @param maxNumberOfLiquidContainers Maximum number of liquid containers the ship can carry at once.
	 * @param fuelConsumptionPerKM Fuel consumption rate per kilometer of the ship.
	 */
	public Ship(int ID, Port p, int totalWeightCapacity, int maxNumberOfAllContainers, int maxNumberOfHeavyContainers,
			int maxNumberOfRefrigeratedContainers, int maxNumberOfLiquidContainers, double fuelConsumptionPerKM) {
		this.ID = ID;
		fuel = 0.0;
		currentPort = p;
		currentPort.incomingShip(this);
		
		containers = new ArrayList<Container>();
		this.totalWeightCapacity = totalWeightCapacity;
		this.maxNumberOfAllContainers = maxNumberOfAllContainers;
		this.maxNumberOfHeavyContainers = maxNumberOfHeavyContainers;
		this.maxNumberOfRefrigeratedContainers = maxNumberOfRefrigeratedContainers;
		this.maxNumberOfLiquidContainers = maxNumberOfLiquidContainers;
		this.fuelConsumptionPerKM = fuelConsumptionPerKM;
		totalWeight = 0;
		numberOfAllContainers = 0;
		numberOfHeavyContainers = 0;
		numberOfRefrigeratedContainers = 0;
		numberOfLiquidContainers = 0;
	}
	
	/**
	 * Returns the list of all containers currently in the ship sorted by ID.
	 * @return List of all containers currently in the ship sorted by ID.
	 */
	public ArrayList<Container> getCurrentContainers() {
		Collections.sort(containers);
		return containers;
	}
	
	/**
	 * Checks if the ship can successfully sail to the given destination port.
	 * If it can be reached, sails the ship to the given destination port.
	 * @param p The given destination port.
	 * @return TRUE if the ship has enough fuel to sail to the given port, FALSE otherwise.
	 */
	@Override
	public boolean sailTo(Port p) {
		double total_fuel_cost = fuelConsumptionPerKM;
		for(Container cont : containers)
			total_fuel_cost += cont.consumption();
		total_fuel_cost *= currentPort.getDistance(p);
		
		boolean can_sail = total_fuel_cost <= fuel;
		
		if(can_sail) {
			fuel -= total_fuel_cost;
			currentPort.getCurrent().remove(this);
			currentPort.outgoingShip(this);
			p.incomingShip(this);
			currentPort = p;
		}
		
		return can_sail;
	}
	
	/**
	 * Adds fuel to the ship.
	 * @param newFuel Amount of added fuel.
	 */
	@Override
	public void reFuel(double newFuel) {
		if(newFuel < 0.0)
			throw new IllegalArgumentException();
		
		fuel += newFuel;
	}
	
	/**
	 * Checks if the given container can be successfully loaded to the ship.
	 * If it can be loaded, loads the given container to the ship.
	 * @param cont The given container.
	 * @return TRUE if the given container can be loaded, FALSE otherwise.
	 */
	@Override
	public boolean load(Container cont) {
		boolean can_load;
		
		if(!currentPort.getContainers().contains(cont) ||
			numberOfAllContainers >= maxNumberOfAllContainers ||
			totalWeight + cont.getWeight() > totalWeightCapacity)
			can_load = false;
		else if(cont instanceof HeavyContainer) {
			if(numberOfHeavyContainers >= maxNumberOfHeavyContainers)
				can_load = false;
			else if(cont instanceof RefrigeratedContainer)
				can_load = numberOfRefrigeratedContainers < maxNumberOfRefrigeratedContainers;
			else if(cont instanceof LiquidContainer)
				can_load = numberOfLiquidContainers < maxNumberOfLiquidContainers;
			else
				can_load = true;
		}
		else
			can_load = true;
		
		if(can_load) {
			currentPort.getContainers().remove(cont);
			containers.add(cont);
			numberOfAllContainers++;
			totalWeight += cont.getWeight();
			if(cont instanceof HeavyContainer) {
				numberOfHeavyContainers++;
				if(cont instanceof RefrigeratedContainer)
					numberOfRefrigeratedContainers++;
				else if(cont instanceof LiquidContainer)
					numberOfLiquidContainers++;
			}
		}
		
		return can_load;
	}
	
	/**
	 * Returns true if the given container can be successfully unloaded from a ship.
	 * If it can be unloaded, unloads the given container from the ship.
	 * @param cont The given container.
	 * @return TRUE if the given container can be unloaded, FALSE otherwise.
	 */
	@Override
	public boolean unLoad(Container cont) {
		boolean can_unLoad = containers.contains(cont);
		
		if(can_unLoad) {
			containers.remove(cont);
			currentPort.getContainers().add(cont);
			numberOfAllContainers--;
			totalWeight -= cont.getWeight();
			if(cont instanceof HeavyContainer) {
				numberOfHeavyContainers--;
				if(cont instanceof RefrigeratedContainer)
					numberOfRefrigeratedContainers--;
				else if(cont instanceof LiquidContainer)
					numberOfLiquidContainers--;
			}
		}
		
		return can_unLoad;
	}
	
	
	
	// *** Extra Methods ***
	
	/**
	 * Compares ships according to their ID.
	 * @param other Other ship.
	 * @return Sign of the difference between the other ship's and this ship's IDs.
	 */
	@Override
    public int compareTo(Ship other) {
        return ID < other.ID ? -1 : (ID == other.ID ? 0 : 1);
    }
	
	/**
	 * Overrides the toString method and returns the ship's ID, the remaining fuel, and container IDs listed by their respective types.
	 * @return A string where each line starts with 2 white spaces. Leading line is “Ship ID: FUEL_LEFT”.
	 * Containers in the ship are indicated by their IDs and the order of containers types are listed as follows: Basic, Heavy, Refrigerated, Liquid.
	 * Note that Ship and its contents are also indented with 2 white spaces.
	 */
	@Override
	public String toString() {
		String str = String.format("  Ship %d: %.2f\n", ID, fuel);
		ArrayList<ArrayList<Container>> sorted = Container.getContainersSortedByType(containers);
		for(ArrayList<Container> conts : sorted)
			if(!conts.isEmpty()) {
				str += "    " + conts.get(0).getClass().getSimpleName() + ":";
				for(Container cont : conts)
					str += " " + cont.getID();
				str += "\n";
			}
				
		return str;
	}
	
}

//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

