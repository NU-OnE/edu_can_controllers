package com.consultant;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.user.User;
import com.utilities.ResposeResult;

@Path("/counsellors")

public class CounsellorService {

	ResposeResult response = new ResposeResult(true, null);
	Gson gson = new Gson();
	private Client client = ClientBuilder.newClient();
	
	@Path("store")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public ResposeResult store(Counsellor counsellor) {
				
		Response res = client.target("http://localhost:8080/edu_can_db/rest/counsellors/store")
						.request(MediaType.APPLICATION_JSON)
						.post(Entity.entity(counsellor, MediaType.APPLICATION_JSON));

		return res.readEntity(ResposeResult.class);
	}
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ResposeResult getConsultants() {
		ResposeResult result = client.target("http://localhost:8080/edu_can_db/rest/counsellors")
				// .path(String.valueOf(id))
				.request(MediaType.APPLICATION_JSON).get(ResposeResult.class);

		return result;
	}
}
