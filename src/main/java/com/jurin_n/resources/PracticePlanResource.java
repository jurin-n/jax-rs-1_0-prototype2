package com.jurin_n.resources;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.jurin_n.application.PracticeApplicationService;
import com.jurin_n.domain.model.practice.PracticePlan;

@Path("/practice")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PracticePlanResource {
	  @Inject
	  PracticeApplicationService ts;
	  
	  @GET
	  @Path("/plan")
	  public Response getPracticePlanList(){
		  //サービス
		  List<PracticePlan> list = ts.getPracticePlanList();
		  
		  //レスポンス
		  return Response
				  .status(Response.Status.OK)
				  .entity(list)
				  .build();
	  }
	  
	  @GET
	  @Path("/plan/{id}")
	  public Response getPracticePlan(@PathParam("id") String id){
		  //サービス
		  PracticePlan plan = ts.getPracticePlan(id);
		  
		  //レスポンス
		  return Response
				  .status(Response.Status.OK)
				  .entity(plan)
				  .build(); 
	  }
	  
	  @POST
	  @Path("/plan")
	  public Response addPracticePlan(PracticePlan aPlan){
		  //サービス
		  PracticePlan plan = ts.addPracticePlan(aPlan);
		  
		  //レスポンス
		  return Response
				  .status(Response.Status.OK)
				  .entity(plan)
				  .build(); 
	  }
}
