package com.iamshb.statemachine.impl;

import com.iamshb.statemachine.pub.State;

/**
 * Blank state class for creating simple states.
 */
public class BlankState implements State {
    @Override
    public void enter() {
        // Do Nothing here.
    }

    @Override
    public void update() {
        // Do Nothing here.
    }

    @Override
    public void exit() {
        // Do Nothing here
    }

    @Override
    public State getNextState() {
        return this;
    }
}
