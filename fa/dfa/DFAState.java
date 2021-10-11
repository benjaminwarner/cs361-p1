package fa.dfa;

import fa.State;

/**
 * A representation of a state in a DFA.
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
	
	/**
	 * Determine if two DFA states are equivalent to each other (two DFA states
	 * are equivalent if they have the same names)
	 *
	 * @return true if this state's name is equal to the input object's state name
	 */
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
