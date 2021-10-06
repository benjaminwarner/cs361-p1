package fa.dfa;

import fa.State;

/** An extension of the fa.State class
 *
 *
 * @author Benjamin Warner
 */
 
public class DFAState extends State {

	/**
	 * Create a new DFAState.
	 */
	public DFAState(String name) {
		this.name = name;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null)
			return false;
		DFAState state = (DFAState) o;
		return this.name.equals(state.getName());
	}
}
