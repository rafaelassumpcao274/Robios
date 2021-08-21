package com.robios;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;
import org.bson.types.ObjectId;

public class CoordinateBD {
	List<Document> steps = new ArrayList<Document>();
	MongoClient conn;
	MongoDatabase db;


	public CoordinateBD(HashMap<Integer,Integer> x,HashMap<Integer,Integer> y){
		
		for(int i = 0;i<x.size();i++) {
			this.steps.add(new Document("init", i));
			this.steps.add(new Document("x", x.get(i)));
			this.steps.add(new Document("y", y.get(i)));
			this.steps.add(new Document("Steps",x.size()));
		}

		connectionBd();
		insertMongo(conn, db);
		
	}
	public void insertMongo(MongoClient conn,MongoDatabase db) {

		 MongoCollection<Document> collection = db.getCollection("Coordinate");
		 collection.insertMany(this.steps);
	}
	
	public void connectionBd() {
		this.setConn(MongoClients.create("mongodb://localhost:27017"));		
		this.setDb(conn.getDatabase("Robios"));
	}
	

	
	
	public MongoClient getConn() {
		return conn;
	}
	public void setConn(MongoClient conn) {
		this.conn = conn;
	}
	public MongoDatabase getDb() {
		return db;
	}
	public void setDb(MongoDatabase db) {
		this.db = db;
	}
	
	public Document getSteps() {
		return (Document) steps;
	}
	public void setSteps(Document steps) {
		this.steps = (List<Document>) steps;
	}
}
