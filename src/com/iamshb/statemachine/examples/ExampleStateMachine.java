package com.iamshb.statemachine.examples;

import com.iamshb.statemachine.impl.AbstractStateMachine;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * An example state machine.
 */
public abstract class ExampleStateMachine extends AbstractStateMachine {

    /** Timer for runing the machine. */
    final Timer timer = new Timer();

    /** Input variable x. */
    private int x;

    /** Output Variable for tracking how many times this has run. */
    private int runCount;

    @Override
    public void start(Object... args) {
        // runt he machine ever 1 second.
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                update();
            }
        }, 1000, 1000);
    }

    @Override
    public void stop() {
        super.stop();
        // Need to stop the timer.
        timer.purge();
    }

    @Override
    protected void beforeUpdate() {
        // Every time get a random input.
        final Random random = new Random();
        x = random.nextInt();
    }

    @Override
    protected void afterUpdate() {
        // increment and print the output.
        ++runCount;
        System.out.println(runCount + " runs.");
    }

    /**
     * Gets the X input variable.
     * This provides access to the states for this input.
     *
     * @return x input variable.
     */
    protected final int getX() {
        return x;
    }
}
