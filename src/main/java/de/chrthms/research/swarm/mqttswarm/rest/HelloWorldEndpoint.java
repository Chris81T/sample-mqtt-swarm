package de.chrthms.research.swarm.mqttswarm.rest;


import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.ProcessEngineConfiguration;

import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;


@Path("/hello")
public class HelloWorldEndpoint {

	@GET
	@Produces("text/plain")
	public Response doGet() {
		System.out.println("INCOMING REQUEST...");

		final String driver = "org.h2.Driver";
		final String url = "jdbc:h2:~/test";
		final String username = "sa";
		final String password = "sa";

        ProcessEngine processEngine = ProcessEngineConfiguration.createStandaloneProcessEngineConfiguration()
                .setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE)
                .setJdbcDriver(driver)
                .setJdbcUrl(url)
                .setJdbcUsername(username)
                .setJdbcPassword(password)
                .setHistory(ProcessEngineConfiguration.HISTORY_AUTO)
                .setJobExecutorActivate(Boolean.TRUE)
                .buildProcessEngine();

        System.out.println("Instantiated process engine = " + processEngine);

        return Response.ok("Hello from WildFly Swarm!").build();
	}
}