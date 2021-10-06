package fa.dfa;

import java.util.LinkedHashSet;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.LinkedHashSet;

import fa.State;
import fa.dfa.DFAState;

/**
 * An implementation of the DFAInterface.
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
	 *
	 * finalStates and states are stored using a LinkedHashSet to ensure order.
	 * transitions is stored using a LinkedHashMap for the same reason.
	 */
	public DFA() {
		this.finalStates = new LinkedHashSet<DFAState>();
		this.states = new LinkedHashSet<DFAState>();
		this.transitions = new LinkedHashMap<String, String>();
		this.alphabet = new LinkedHashSet<Character>();
	}
	
	// documented in FAInterface
	public void addStartState(String name) {
		this.startState = new DFAState(name);
	}
	
	// documented in FAInterface
	public void addState(String name) {
		DFAState state = new DFAState(name);
		this.states.add(state);
	}
	
	// documented in FAInterface
	public void addFinalState(String name) {
		DFAState state = new DFAState(name);
		this.finalStates.add(state);
	}
	
	// documented in FAInterface
	public void addTransition(String fromState, char onSymb, String toState) {
		String from = fromState + onSymb;
		this.transitions.put(from, toState);
	}
	
	// documented in FAInterface
	public Set<DFAState> getStates() {
		return this.states;
	}
	
	// documented in FAInterface
	public Set<DFAState> getFinalStates() {
		return this.finalStates;
	}
	
	// documented in FAInterface
	public State getStartState() {
		return this.startState;
	}
	
	// documented in FAInterface
	public Set<Character> getABC() {
		return this.alphabet;
	}
	
	// documented in DFAInterface
	public boolean accepts(String s) {
		return false;
	}
	
	// documented in DFAInterface
	public State getToState(DFAState from, char onSymb) {
		return null;
	}
	
	// documented in DFAInterface
	public String toString() {
		return "";
	}
}