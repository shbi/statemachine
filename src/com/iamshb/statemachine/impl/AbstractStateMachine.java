package com.iamshb.statemachine.impl;

import com.iamshb.statemachine.pub.State;
import com.iamshb.statemachine.pub.StateMachine;
import com.sun.tools.classfile.StackMap_attribute;

/**
 * Abstract State Machine.
 * before update runs before update is called on the state.
 * This is a good place to set up and read inputs.
 * after update runs after the state is updated.
 * This is a good place to clean up temp variables and format outputs.
 */
public abstract class AbstractStateMachine implements StateMachine {

    /** The current State. */
    private State state;

    @Override
    public void update() {

        final State nextState = getNextState();
        if (nextState != state) {
            if (state != null) {
                state.exit();
            }
            state = nextState;
            state.enter();
        }
        beforeUpdate();
        state.update();
        afterUpdate();
    }

    @Override
    public void stop() {
        state.exit();
        state = null;
    }

    /**
     * Gets the next state. If no state change is desired, returns the current
     * state.
     * Defaults to asking the current state for the next state
     * can be overridden or extended for more flexible architecture.
     *
     * @return the next State in the state machine.
     */
    protected State getNextState() {
        return state == null ? getStartState() : state.getNextState();
    }
    protected abstract State getStartState();
    protected abstract void beforeUpdate();
    protected abstract void afterUpdate();
}
