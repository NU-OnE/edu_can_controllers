package com.appointment;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.utilities.ResposeResult;

@Path("/appointments")
public class AppointmentController {

	ResposeResult response = new ResposeResult(true, null);
	Gson gson = new Gson();
	private Client client = ClientBuilder.newClient();
	
	
	@Path("store")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public ResposeResult store(Appointment appointment) {
				
		Response res = client.target("http://localhost:8080/edu_can_db/rest/appointments/store")
						.request(MediaType.APPLICATION_JSON)
						.post(Entity.entity(appointment, MediaType.APPLICATION_JSON));

		return res.readEntity(ResposeResult.class);
	}
}
