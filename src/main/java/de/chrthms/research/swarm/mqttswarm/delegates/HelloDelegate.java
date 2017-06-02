package de.chrthms.research.swarm.mqttswarm.delegates;

import de.chrthms.bpm.iot.delegates.MicroDelegateExecution;
import de.chrthms.bpm.iot.delegates.MicroJavaDelegate;

/**
 * Created by christian on 23.05.17.
 */
public class HelloDelegate implements MicroJavaDelegate {

    @Override
    public void execute(MicroDelegateExecution microDelegateExecution) throws Exception {

        System.out.println("################## Hello MICRO-INTERFACE ;-) Delegate is up and running :D :D :D :D");
        System.out.println(">>>>>>>>>> MICRO ENGINE = " + microDelegateExecution.getMicroProcessEngine());
        System.out.println(">>>>>>>>>> MICRO ENGINE = " + microDelegateExecution.getMicroProcessEngine().getName());

    }

}
