package com.umkc.rest;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONException;
import org.json.JSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.WriteResult;
import com.mongodb.client.MongoDatabase;
import com.mongodb.util.JSON;
import com.umkc.dao.CoursesDAO;
import com.umkc.dao.MongoDatabaseClient;

@Path("courseinfo")
public class CoursesInfo {
	@Path("test")
	@GET
	public String testCourse() {
		return "hello";
	}

	@Path("deletecourse")
	@DELETE
	public String deleteCourse(String jsondata) {
		System.out.println("data received from front end" + jsondata);

		Object jsonObject = JSON.parse(jsondata);

		BasicDBObject basicdbobject = (BasicDBObject) jsonObject;
		CoursesDAO coursesdao = new CoursesDAO();

		boolean status = coursesdao.deleteCourseFromDatabase(basicdbobject);

		JSONObject statusObject = new JSONObject();
		try {
		if(status){
			statusObject.put("status", "success");
		}
		else{
			
				statusObject.put("status", "fail");
			} 
		}catch (JSONException e) {
			System.out.println("exception in message"+e.getMessage());
		}
		return statusObject.toString();

	}
	
	
	
	@Path("insertcourse")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String createCourse(String jsonData){
		
		System.out.println("data received from front end" + jsonData);

		Object jsonObject = JSON.parse(jsonData);

		BasicDBObject basicdbobject = (BasicDBObject) jsonObject;
		CoursesDAO coursesdao = new CoursesDAO();
		
		JSONObject jsonobject = new JSONObject();
		try {
		if(coursesdao.insertCourseInfo(basicdbobject)){
			jsonobject.put("status", "success");
			
		}else{
		
				jsonobject.put("status", "fail");
			
		}
		} catch (JSONException e) {
			System.out.println("Exception in json conversion"+e.getMessage());
		}
		
		return jsonobject.toString();
		
	}
	
	@Path("updatecourse")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String updateCourse(String jsonData){
		
		System.out.println("data received from front end" + jsonData);

		Object jsonObject = JSON.parse(jsonData);

		BasicDBObject basicdbobject = (BasicDBObject) jsonObject;
		CoursesDAO coursesdao = new CoursesDAO();
		
		JSONObject jsonobject = new JSONObject();
		try {
		if(coursesdao.insertCourseInfo(basicdbobject)){
			jsonobject.put("status", "success");
			
		}else{
		
				jsonobject.put("status", "fail");
			
		}
		} catch (JSONException e) {
			System.out.println("Exception in json conversion"+e.getMessage());
		}
		
		return jsonobject.toString();
		
	}
	
	@Path("deletecourses")
	@DELETE
	public String deleteCourses(String jsondata) {
		System.out.println("data received from front end" + jsondata);

		Object jsonObject = JSON.parse(jsondata);

		BasicDBObject basicdbobject = (BasicDBObject) jsonObject;
		CoursesDAO coursesdao = new CoursesDAO();

		boolean status = coursesdao.deleteCourseFromDatabase(basicdbobject);

		JSONObject statusObject = new JSONObject();
		try {
		if(status){
			statusObject.put("status", "success");
		}
		else{
			
				statusObject.put("status", "fail");
			} 
		}catch (JSONException e) {
			System.out.println("exception in message"+e.getMessage());
		}
		return statusObject.toString();

	}
	
	@Path("insertcourses")
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public String createCourses(String jsonData){
		
		System.out.println("data received from front end" + jsonData);

		Object jsonObject = JSON.parse(jsonData);

		BasicDBObject basicdbobject = (BasicDBObject) jsonObject;
		CoursesDAO coursesdao = new CoursesDAO();
		
		JSONObject jsonobject = new JSONObject();
		try {
		if(coursesdao.insertCourseInfo(basicdbobject)){
			jsonobject.put("status", "success");
			
		}else{
		
				jsonobject.put("status", "fail");
			
		}
		} catch (JSONException e) {
			System.out.println("Exception in json conversion"+e.getMessage());
		}
		
		return jsonobject.toString();
		
	}

}
