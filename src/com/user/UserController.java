package com.user;

import javax.ws.rs.GET;
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

@Path("/users")
public class UserController {

	ResposeResult response = new ResposeResult(true, null);
	Gson gson = new Gson();
	private Client client = ClientBuilder.newClient();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ResposeResult getUsers() {
		ResposeResult result = client.target("http://localhost:8080/edu_can_db/rest/users")
				// .path(String.valueOf(id))
				.request(MediaType.APPLICATION_JSON).get(ResposeResult.class);

		return result;
	}

	@Path("login")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public ResposeResult logIn(User user) {
				
		Response res = client.target("http://localhost:8080/edu_can_db/rest/tokens/new")
						.request(MediaType.APPLICATION_JSON)
						.post(Entity.entity(user, MediaType.APPLICATION_JSON));

		return res.readEntity(ResposeResult.class);
	}
	
	@Path("register")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public ResposeResult store(User user) {
				
		Response res = client.target("http://localhost:8080/edu_can_db/rest/users/store")
						.request(MediaType.APPLICATION_JSON)
						.post(Entity.entity(user, MediaType.APPLICATION_JSON));

		return res.readEntity(ResposeResult.class);
	}
}
