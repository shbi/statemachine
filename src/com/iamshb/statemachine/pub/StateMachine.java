package com.iamshb.statemachine.pub;

/**
 * Created by jschneider on 12/16/14.
 */
public interface StateMachine {
    void start(final Object... args);
    void update();
    void stop();
}
