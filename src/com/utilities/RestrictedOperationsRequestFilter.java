package com.utilities;

import java.io.IOException;
import java.util.HashMap;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import com.google.gson.Gson;

@Provider
@PreMatching
public class RestrictedOperationsRequestFilter implements ContainerRequestFilter {
	private Client client = ClientBuilder.newClient();
	Gson gson = new Gson();

	@Override
	public void filter(ContainerRequestContext ctx) throws IOException {

		// users/login
		if (!ctx.getUriInfo().getPath().equalsIgnoreCase("users/login") && !ctx.getUriInfo().getPath().equalsIgnoreCase("users/register")) {
			String token1 = ctx.getHeaderString("Bearer");
			HashMap<String, String> publisher = new HashMap<String, String>();
			publisher.put("token", token1);

			Response res = client.target("http://localhost:8080/edu_can_db/rest/tokens/validate")
					.request(MediaType.APPLICATION_JSON).post(Entity.entity(publisher, MediaType.APPLICATION_JSON));

			ResposeResult output = res.readEntity(ResposeResult.class);

			if (output.getIs_error()) {
				ResposeResult response = new ResposeResult(true, "Access Denied");
				ctx.abortWith(Response.status(Response.Status.FORBIDDEN).entity(gson.toJson(response)).build());
			}
		}
		
		//ctx.abortWith(Response.status(Response.Status.FORBIDDEN).entity(ctx.getUriInfo().getPath()).build());

	}

}
