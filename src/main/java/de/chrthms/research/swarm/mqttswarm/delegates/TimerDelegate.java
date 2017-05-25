package de.chrthms.research.swarm.mqttswarm.delegates;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

/**
 * Created by christian on 24.05.17.
 */
public class TimerDelegate implements JavaDelegate {

    private static int counter = 1;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        System.out.println("TIMER DELEGATION " + counter + " TIME(S)...");
        counter++;
    }
}
