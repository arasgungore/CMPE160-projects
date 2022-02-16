
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

/**
 * Contains the Main class.
 */
package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

import java.util.ArrayList;
import ports.Port;
import ships.Ship;
import containers.*;

/**
 * Main class.
 * @author Aras Güngöre
 *
 */
public class Main {
	
	/**
	 * Main method.
	 * @param args Command line arguments where args[0] is the input filename/directory and args[1] is the output filename/directory.
	 * @throws FileNotFoundException Exception thrown if the directories given in args don't exist.
	 */
	public static void main(String[] args) throws FileNotFoundException {
		
		// Read input.
		
		// Create a new Scanner object to read data from the input file with directory "args[0]".
		Scanner in = new Scanner(new File(args[0]));
		
		final int N = in.nextInt();		// N : Number of lines the input file has.
		
		// Create ArrayLists in order to reach any container, ship or port I want with IDs for indexes in time complexity O(1).
		ArrayList<Container> conts = new ArrayList<Container>();
		ArrayList<Ship> ships = new ArrayList<Ship>();
		ArrayList<Port> ports = new ArrayList<Port>();
		
		for(int i = 0; i < N; i++) {	// Iterate over N lines.
			final int operation_type = in.nextInt();
			switch(operation_type) {
				case 1: {		// Create a container.
					final int cont_ID = conts.size();
					final int port_ID = in.nextInt();
					final int weight = in.nextInt();
					Container cont;
					
					if(in.hasNextInt()) {	// If the next input is an integer, the container is either type Heavy or Basic.
						if(weight > 3000)
							cont = new HeavyContainer(cont_ID, weight);
						else
							cont = new BasicContainer(cont_ID, weight);
					}
					else {					// If the next input is a character, the container is either type Liquid or Refrigerated.
						final char special_type = in.next().charAt(0);
						if(special_type == 'L')
							cont = new LiquidContainer(cont_ID, weight);
						else
							cont = new RefrigeratedContainer(cont_ID, weight);
					}
					
					// Add the generated container to the ports "containers" ArrayList and the general "conts" ArrayList.
					ports.get(port_ID).getContainers().add(cont);
					conts.add(cont);
					break; }
				
				case 2: {		// Create a ship.
					final int ship_ID = ships.size();
					final int port_ID = in.nextInt();
					final int totalWeightCapacity = in.nextInt();
					final int maxNumberOfAllContainers = in.nextInt();
					final int maxNumberOfHeavyContainers = in.nextInt();
					final int maxNumberOfRefrigeratedContainers = in.nextInt();
					final int maxNumberOfLiquidContainers = in.nextInt();
					final double fuelConsumptionPerKM = in.nextDouble();
					
					// Add the generated ship to the port's "current" ArrayList and the general "ships" ArrayList.
					ships.add(new Ship(ship_ID, ports.get(port_ID), totalWeightCapacity,
							maxNumberOfAllContainers, maxNumberOfHeavyContainers, maxNumberOfRefrigeratedContainers,
							maxNumberOfLiquidContainers, fuelConsumptionPerKM));
					break; }
				
				case 3: {		// Create a port.
					final int port_ID = ports.size();
					final double X = in.nextDouble();
					final double Y = in.nextDouble();
					
					// Add the generated port to the "ports" ArrayList.
					ports.add(new Port(port_ID, X, Y));
					break; }
				
				case 4: {		// Load a container to a ship.
					final int ship_ID = in.nextInt();
					final int cont_ID = in.nextInt();
					ships.get(ship_ID).load(conts.get(cont_ID));
					break; }
				
				case 5: {		// Unload a container from a ship.
					final int ship_ID = in.nextInt();
					final int cont_ID = in.nextInt();
					ships.get(ship_ID).unLoad(conts.get(cont_ID));
					break; }
				
				case 6: {		// Sail ship to another port.
					final int ship_ID = in.nextInt();
					final int port_ID = in.nextInt();
					ships.get(ship_ID).sailTo(ports.get(port_ID));
					break; }
				
				case 7: {		// Refuel ship.
					final int ship_ID = in.nextInt();
					final double fuel = in.nextDouble();
					ships.get(ship_ID).reFuel(fuel);
					break; }
				
				default:		// Invalid operation.
					System.out.println("Invalid operation.");
			}
		}
		
		in.close();							// Closes the Scanner object.
		
		
		
		// Write output.
		
		// Create a new PrintStream object to read data from the input file with directory "args[1]".
		PrintStream out = new PrintStream(new File(args[1]));
		
		for(Port port : ports)
			out.print(port.toString());		// Prints the ports' content to the output file.
		
		out.close();						// Closes the PrintStream object.
		
	}
}

//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

