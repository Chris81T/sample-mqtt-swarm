package de.chrthms.research.swarm.mqttswarm.delegates;

import de.chrthms.mco.delegates.MicroDelegateExecution;
import de.chrthms.mco.delegates.MicroJavaDelegate;
import de.chrthms.mco.delegates.MicroJavaDelegateInterface;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

/**
 * Created by christian on 23.05.17.
 */
public class HelloDelegate implements MicroJavaDelegateInterface {

    @Override
    public void execute(MicroDelegateExecution microDelegateExecution) throws Exception {

        System.out.println("################## Hello MICRO-INTERFACE ;-) Delegate is up and running :D :D :D :D");
        System.out.println(">>>>>>>>>> MICRO ENGINE = " + microDelegateExecution.getMicroProcessEngine());
        System.out.println(">>>>>>>>>> MICRO ENGINE = " + microDelegateExecution.getMicroProcessEngine().getName());

    }

}
