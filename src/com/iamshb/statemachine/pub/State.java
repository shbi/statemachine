package com.iamshb.statemachine.pub;

/**
 * Created by jschneider on 12/16/14.
 */
public interface State {
    void enter();
    void update();
    void exit();
    State getNextState();
}
