package org.ace.insurance.life.interfaces;

import java.io.Serializable;

/**
 * @author Zaw Than Oo
 * @Use It is used to sort the entities which have custom registration number
 *      format.
 */
public interface ISorter extends Serializable {
	public String getRegistrationNo();
}
