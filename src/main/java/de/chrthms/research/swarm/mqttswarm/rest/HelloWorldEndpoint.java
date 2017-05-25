package de.chrthms.research.swarm.mqttswarm.rest;


import de.chrthms.mco.MicroProcessEngine;
import de.chrthms.mco.MicroProcessEngineFactory;
import org.camunda.bpm.engine.repository.Deployment;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.InputStream;
import java.time.LocalDateTime;

/**
 * For remote debugging:
 * java -jar -Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5005 sample-mqtt-swarm-swarm.jar
 */
@Path("/hello")
public class HelloWorldEndpoint {

	@GET
	@Produces("text/plain")
	public Response doGet() {
		System.out.println("INCOMING REQUEST - first try of MCO :-) - ...");

		final String driver = "org.h2.Driver";
		final String url = "jdbc:h2:~/test";
		final String username = "sa";
		final String password = "sa";

        MicroProcessEngine processEngine = MicroProcessEngineFactory.getInstance()
                .jdbcDriver(driver)
                .jdbcUrl(url)
                .jdbcUsername(username)
                .jdbcPassword(password)
//                .mqttEnabled(true)
//                .mqttBroker("tcp://pihab:1883")
//                .mqttClientId("SampleSwarmProcess")
                .build();

        System.out.println("Instantiated micro- process engine = " + processEngine);

        Deployment deployment = processEngine.createDeploymentFromResource("sample.bpmn");

        System.out.println("deployment time= " + deployment.getDeploymentTime());
        System.out.println("\nid= " + deployment.getId());
        System.out.println("\nname= " + deployment.getName());
        System.out.println("\nsource= " + deployment.getSource()+"\n\n");

        ProcessInstance processInstance = processEngine.getRuntimeService()
                .startProcessInstanceByKey("Process_Sample");

        System.out.println("\nprocessInstanceId= " + processInstance.getProcessInstanceId());
        System.out.println("\nid= " + processInstance.getId());
        System.out.println("\nprocessDefinitionId= " + processInstance.getProcessDefinitionId());

//        Deployment timerDeployment = processEngine.createDeploymentFromResource("timer-start.bpmn");
//        System.out.println("\nDeployed timer-start sample process. Waiting for delegate execution five times.....");
//
//        System.out.println("Try to send MQTT Message....");

//        processEngine.sendMessageToItem("swarm/engine", "Eine Nachricht um " + LocalDateTime.now());

        return Response.ok("Hello from WildFly Swarm!").build();
	}

	@GET
    @Path("/pub")
    @Produces("text/plain")
    public Response publish() {

	    System.out.println("About to publish a message to the pihab broker...");

	    /**
	     * Auf dem pihab ausfuehren: mosquitto_sub -h localhost -v -t test
	     */
        try {
            MqttClient client = new MqttClient("tcp://pihab:1883", MqttClient.generateClientId());

            client.connect();

            MqttMessage message = new MqttMessage(("Eine erste Nachricht um " + LocalDateTime.now()).getBytes());

            client.publish("/swarm/item", message);

            client.disconnect();

        } catch (MqttException e) {
            e.printStackTrace();
        }


        return Response.ok().build();
    }

}