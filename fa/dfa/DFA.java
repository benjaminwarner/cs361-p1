package fa.dfa;

import java.util.Map;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.LinkedHashSet;

import fa.State;
import fa.dfa.DFAState;

/**
 * Set and Map implementation of the DFAInterface.
 * 
 * accepts is the main method of this class, as it will
 * determine if a string is accepted by the machine being
 * modeled. 
 *
 * There is only a single start state at a time; final and regular
 * states are maintained separately in their own sets. 
 *
 * @author Benjamin Warner
 */

public class DFA implements DFAInterface {
	
	private DFAState startState;
	private Set<DFAState> finalStates;
	private Set<DFAState> states;
	private Map<String, String> transitions;
	private Set<Character> alphabet;
	
	/**
	 * Create an empty DFA.
	 */
	public DFA() {
		this.finalStates = new LinkedHashSet<DFAState>();
		this.states = new LinkedHashSet<DFAState>();
		this.transitions = new LinkedHashMap<String, String>();
		this.alphabet = new LinkedHashSet<Character>();
	}
	
	/**
	 * Sets the start state of this DFA.
	 *
	 * @param name the name of the start state for this machine
	 */
	public void addStartState(String name) {
		this.startState = new DFAState(name);
	}
	
	/**
	 * Add a new state with name to the machine.
	 *
	 * @param name the name of the state to add to this machine
	 */
	public void addState(String name) {
		DFAState state = new DFAState(name);
		this.states.add(state);
	}
	
	/**
	 * Add a new final state with name to this machine.
	 *
	 * @param name the name of the final state to add to this machine
	 */
	public void addFinalState(String name) {
		DFAState state = new DFAState(name);
		this.finalStates.add(state);
	}
	
	/**
	 * Add a new transition from one state to another for this machine
	 *
	 * @param fromState the name of the state this machine will be transitioning from
	 * @param onSymb the input character required to perform a transition
	 * @param toState the name of the state this machine will transition to
	 */
	public void addTransition(String fromState, char onSymb, String toState) {
		String from = fromState + onSymb;
		this.transitions.put(from, toState);
		this.alphabet.add(onSymb);
	}
	
	/**
	 * Get all non-final states of this machine
	 *
	 * @return the set of non-final states
	 */
	public Set<DFAState> getStates() {
		return this.states;
	}
	
	/**
	 * Get all final states of this machine
	 *
	 * @return the set of final states
	 */
	public Set<DFAState> getFinalStates() {
		return this.finalStates;
	}
	
	/**
	 * Get the start state of this machine
	 *
	 * @return the start state
	 */
	public State getStartState() {
		return this.startState;
	}
	
	/**
	 * Get the alphabet of this machine
	 *
	 * @return the set of characters that make up this machine's alphabet
	 */
	public Set<Character> getABC() {
		return this.alphabet;
	}
	
	/**
	 * Simulate DFA processing on an input string to see if
	 * the string is accepted by the machine
	 *
	 * @param s the string to test for acceptance
	 * @return true if s is an accepted string of this machine's language and false otherwise
	 */
	public boolean accepts(String s) {
		DFAState finalState = this.startState;
		String[] data = s.split("");
		for (String transition : data) {
			String step = finalState.toString() + transition;
			finalState = new DFAState(this.transitions.get(step));
		}
		for (DFAState state : this.finalStates) {
			if (state.equals(finalState))
				return true;
		}
		return false;
	}
	
	// documented in DFAInterface
	public State getToState(DFAState from, char onSymb) {
		return null;
	}
	
	/**
	 * Returns the formal definition of this machine, which includes
	 * Q, Sigma, delta, q0, and F
	 *
	 * @return a string representation of this machine
	 */
	public String toString() {
		StringBuilder builder = new StringBuilder("Q = { ");
		for (DFAState state : this.finalStates)
			builder.append(state.toString() + " ");
		builder.append(this.startState.toString() + " ");
		for (DFAState state : this.states)
			builder.append(state.toString() + " ");
		builder.append("}\n");
		
		builder.append("Sigma = { ");
		for (Character c : this.alphabet)
			builder.append(c + " ");
		builder.append("}\n");
		
		Set<DFAState> delta = new LinkedHashSet<DFAState>();
		delta.addAll(this.finalStates);
		delta.add(this.startState);
		delta.addAll(this.states);
		builder.append("delta =\n");
		builder.append("\t\t");
		for (Character c: this.alphabet)
			builder.append(c + "\t");
		builder.append("\n");
		for (DFAState state : delta) {
			builder.append("\t" + state.getName() + "\t");
			for (Character c : this.alphabet) {
				String toState = this.transitions.get(state.getName() + c);
				builder.append(toState + "\t");
			}
			builder.append("\n");
		}
		
		builder.append(String.format("q0 = %s\n", this.startState.toString()));
		
		builder.append("F = { ");
		for (DFAState state: this.finalStates)
			builder.append(state.toString() + " ");
		builder.append("}\n");
		return builder.toString();
	}
}
