package de.chrthms.research.swarm.mqttswarm.delegates;



import de.chrthms.bpm.iot.delegates.MicroDelegateExecution;
import de.chrthms.bpm.iot.delegates.MicroJavaDelegate;

import java.time.LocalDateTime;

/**
 * Created by christian on 28.05.17.
 */
public class MqttSendDelegate implements MicroJavaDelegate {

    @Override
    public void execute(MicroDelegateExecution microDelegateExecution) throws Exception {

        System.out.println("SEND MESSAGE TO PIHAB....");

        microDelegateExecution.getProcessEngineServices().getHistoryService().createHistoricCaseInstanceQuery();

        microDelegateExecution.getMicroMqttService()
                .createMqttCommand()
                .brokerUrl("tcp://pihab:1883")
                .topic("swarm/test")
                .publish("Hello hello, I am the swarm at " + LocalDateTime.now())
                .execute();

        System.out.println("MESSAGE SENT!....");



    }

}
