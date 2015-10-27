package com.umkc.dao;

import javax.ws.rs.core.Response;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.WriteResult;

public class CoursesDAO {

	// Checking weather courses record exists or not
	public boolean doesCourseRecordExits(BasicDBObject dbobject) {

		DBCollection dbcollection = createCoursesDBCollection();

		DBCursor dbcursor = dbcollection.find(dbobject);

		if (dbcursor.hasNext()) {
			return true;
		}

		return false;
	}

	// Creating DBCollection for courses Collection in database group13
	private static DBCollection createCoursesDBCollection() {

		MongoClient mongoclient = new MongoClient("localhost", 27017);

		DB db = mongoclient.getDB("group13");

		DBCollection dbcollection = db.getCollection("courses");

		return dbcollection;
	}

	public DBCursor retrieveAllCourses() {

		DBCollection dbcoursecollection = createCoursesDBCollection();

		return dbcoursecollection.find();

	}

	public boolean insertCourseInfo(BasicDBObject basicdbobject) {

		DBCollection coursedbcollection = createCoursesDBCollection();

		if (doesCourseRecordExits(basicdbobject)) {
			System.out.println("record exists");
			return false;
		} else {
			System.out.println("Entering a new record into mongodatabase");

			coursedbcollection.insert(basicdbobject);

			return true;
		}
	}
	
	public boolean updateCourseInfo(BasicDBObject basicdbobject) {

		DBCollection coursedbcollection = createCoursesDBCollection();

		if (doesCourseRecordExits(basicdbobject)) {
			System.out.println("record exists");
			return false;
		} else {
			System.out.println("Entering a new record into mongodatabase");
			
			System.out.println("Course ID"+basicdbobject.get("courseid"));

			BasicDBObject queryDocument = new BasicDBObject("courseid",basicdbobject.get("courseid"));
			coursedbcollection.update(queryDocument,basicdbobject);

			return true;
		}
	}

	public boolean deleteCourseFromDatabase(BasicDBObject basicdbobject) {

		DBCollection dbcollection = createCoursesDBCollection();

		dbcollection.remove(basicdbobject);

//		return writeresult.toString();
		
		return doesCourseRecordExits(basicdbobject);

//		return  Response.ok() //200
//				.entity(writeresult	)
//				.header("Access-Control-Allow-Origin", "*")
//				.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
//				.build();
	}
}
