package com.umkc.rest;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.json.JSONException;
import org.json.JSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;
import com.sun.jersey.core.impl.provider.entity.StringProvider;
import com.sun.jersey.json.impl.provider.entity.JSONObjectProvider;
import com.umkc.dao.CoursesDAO;
import com.umkc.dao.MongoDatabaseClient;
import com.umkc.pojo.TestPojo;

@Path("/insert")
public class MongoInsert {
	@GET
	public String message() {
		return "Hello";
	}

	@POST
	@Path("create")
	@Produces(MediaType.APPLICATION_JSON)
	public String insertIntoMongo(String data) throws JSONException, IOException {
		System.out.println(data);

		Object dataJson = JSON.parse(data);

		DBObject dbObject = (DBObject) dataJson;

		System.out.println("after conversion" + dbObject.toString());

		MongoDatabaseClient mongoCLient = new MongoDatabaseClient();

		boolean status = mongoCLient.registerUser(dbObject);

		JSONObject jsonObject = new JSONObject();
		if (status) {
			try {
				jsonObject.put("statusmessage", "success");
			} catch (JSONException e) {
				System.out.println("Exception" + e.getMessage());
			}
		} else {
			try {
				jsonObject.put("statusmessage", "record already exists");
			} catch (JSONException e) {
				System.out.println("Exception" + e.getMessage());
			}
		}

		return jsonObject.toString();
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/course")
	public String insertCourse(String jsonData) {

		System.out.println(jsonData);

		Object dataJson = JSON.parse(jsonData);

		BasicDBObject dbObject = (BasicDBObject) dataJson;

		System.out.println("after conversion" + dbObject.toString());

		CoursesDAO coursedao = new CoursesDAO();

		boolean status = coursedao.insertCourseInfo(dbObject);

		JSONObject jsonObject = new JSONObject();

		if (status) {
			try {
				jsonObject.put("statusmessage", "success");
			} catch (JSONException e) {
				System.out.println("Exception" + e.getMessage());
			}
		} else {
			try {
				jsonObject.put("statusmessage", "record already exists");
			} catch (JSONException e) {
				System.out.println("Exception" + e.getMessage());
			}
		}
		return jsonObject.toString();
	}
	
	public static void main(String[] args) {
		MongoInsert mongoinsert = new MongoInsert();
		
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("courseid", "CS5551");
			jsonObject.put("coursename", "ASE");
			jsonObject.put("professorname", "DR.Lee");
			jsonObject.put("department", "CS");
			jsonObject.put("specialization", "DS");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		mongoinsert.insertCourse(jsonObject.toString());
	}
}
