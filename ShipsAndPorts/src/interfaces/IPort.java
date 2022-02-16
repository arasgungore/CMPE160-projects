package interfaces;

import ships.Ship;

public interface IPort {
	/*
	 * Do not modify the code in this file! 
	 * You are responsible for all the compilation errors that originated from the changes 
	 * made in any of these files including the addition or removal of libraries.
	 * 
	 */
	
	void incomingShip(Ship s);
	
	void outgoingShip(Ship s);
}
