package interfaces;

import containers.Container;
import ports.Port;

public interface IShip {
	/*
	 * Do not modify the code in this file! 
	 * You are responsible for all the compilation errors that originated from the changes 
	 * made in any of these files including the addition or removal of libraries.
	 * 
	 */
	
	boolean sailTo(Port p);
	
	void reFuel(double newFuel);
	
	boolean load(Container cont);
	
	boolean unLoad(Container cont);
}
