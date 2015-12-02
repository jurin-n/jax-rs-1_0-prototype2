package com.jurin_n.jax_rs.resources;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.jurin_n.application.PracticeApplicationService;
import com.jurin_n.domain.model.practice.PracticeMember;
import com.jurin_n.jax_rs.providers.BaseJsonMarshaller;
import com.jurin_n.jax_rs.representation.PracticeMemberRepresentation;

@Path("/practice/member")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PracticeMemberResource {
	  @Inject
	  PracticeApplicationService ts;
	  
	  @GET
	  public Response getPracticeMemberList(){
		  //サービス
		  List<PracticeMember> list = ts.getPracticeMemberList();
		  
		  //レスポンス
		  Response response = this.practiceMemberResponse(list);
		  return response;
	  }
	  
	  private Response practiceMemberResponse(List<PracticeMember> list){
		  List<BaseJsonMarshaller> response = new ArrayList<>();
		  for(PracticeMember member : list){
			  PracticeMemberRepresentation res
			  		= new PracticeMemberRepresentation(
			  				 member.getPracticeMemberId().getId()
			  				,member.getName()
			  				);
			  response.add(res);
		  }
		  GenericEntity<List<BaseJsonMarshaller>> entity
		  	= new GenericEntity<List<BaseJsonMarshaller>>(response){};
		  
		  //レスポンス
		  return Response
				  .status(Response.Status.OK)
				  .entity(entity)
				  .build(); 
	  }
	  
	  @POST
	  public Response addPracticeMember(PracticeMember aMember){
		  //サービス
		  ts.addPracticeMember(aMember);
		  
		  //レスポンス
		  return Response
				  .status(Response.Status.CREATED)
				  .entity(aMember.getPracticeMemberId())
				  .build();
	  }
}
