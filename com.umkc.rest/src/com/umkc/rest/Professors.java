package com.umkc.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.mongodb.BasicDBObject;
import com.umkc.dao.MongoDatabaseClient;

@Path("/professors")
public class Professors {
	@GET
	@Path("retrieve")
	@Produces(MediaType.APPLICATION_JSON)
	
	public String retrieveProfessors(){
		
		MongoDatabaseClient mongodatabaseClient = new MongoDatabaseClient();
		BasicDBObject basicdbObject = new BasicDBObject("usertype","prof");
		
		String json = mongodatabaseClient.retrieveMatchingRecord(basicdbObject);
		
		return json;
	}
	
	public static void main(String[] args) {
		MongoDatabaseClient mongodatabaseClient = new MongoDatabaseClient();
		BasicDBObject basicdbObject = new BasicDBObject("usertype","prof");
		
		String json = mongodatabaseClient.retrieveMatchingRecord(basicdbObject);
		
	System.out.println("Json Data"+json);
	}
}
