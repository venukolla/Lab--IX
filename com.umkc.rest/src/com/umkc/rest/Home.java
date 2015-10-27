package com.umkc.rest;

import java.util.HashMap;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONException;
import org.json.JSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.util.JSON;
import com.umkc.dao.CoursesDAO;
import com.umkc.dao.MongoDatabaseClient;

@Path("mongo")
public class Home {

	@GET
	@Produces(MediaType.TEXT_HTML)
	public String homeStatus() {
		return "<p>Welcome to rest service</p>";
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/login")

	public String doLogin(String data) throws JSONException {
		System.out.println("data received from front end"+data);
		
		Object jsonObject = JSON.parse(data);
		
		BasicDBObject basicdbobject = (BasicDBObject) jsonObject;

		MongoDatabaseClient mongods = new MongoDatabaseClient();
		boolean status = mongods.performLoginValidation(basicdbobject);
		
		JSONObject statusJson = new JSONObject();
		
		if(status){
		try {
			statusJson.put("statusmessage", "success");
			System.out.println("setting up success message");
		} catch (JSONException e) {
			System.out.println("Exception"+e.getMessage());
		}
		}else{
			try {
				statusJson.put("statusmessage", "record already exists");
				System.out.println("Setting uo the failure message");
			} catch (JSONException e) {
				System.out.println("Exception"+e.getMessage());
			}
		}
		
		
		return statusJson.toString();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/courses")
	public String retrieveCourses(){
		
		CoursesDAO courseDAO = new CoursesDAO();
		
		DBCursor dbcoursecursor = courseDAO.retrieveAllCourses();
		
		return dbcoursecursor.toArray().toString();
				
	}

}
