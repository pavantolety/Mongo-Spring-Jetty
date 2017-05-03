package com.edv.mongprj.MongoPrj;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.edv.mongprj.model.FormData;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;


@Controller
public class App {

	/* Home page view controller and fetch ctc details from config collection*/
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String printWelcome(ModelMap modelMap) {

		try{
    		
            // To connect to mongodb server
            MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
   			
            // Now connect to your databases
            DB db = mongoClient.getDB( "test" );
            System.out.println("Connect to database successfully");
            //Authenticate by name/id and password
            boolean auth = db.authenticate("kalyan","passpass".toCharArray());
            System.out.println("Authentication Success: "+auth);
            
            DBCollection coll = db.getCollection("config");
            System.out.println("Collection config selected successfully");
   			
            DBCursor cursor = coll.find();
            List<BasicDBObject> configList = new ArrayList<BasicDBObject>();
            
            while (cursor.hasNext()) 
            {
            	configList.add((BasicDBObject) cursor.next());         
            }
            modelMap.put("configList", configList);
            System.out.println("Config List" + configList);
            
         }catch(Exception e){
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
         }
		return "hello";

	}
	
	

	/*@RequestMapping(value = "/hello/{name:.+}", method = RequestMethod.GET)
	public ModelAndView hello(@PathVariable("name") String name) {

		ModelAndView model = new ModelAndView();
		model.setViewName("hello");
		model.addObject("msg", name);

		return model;

	}*/
	
	/* Save a Configuration in config collection to Auto populate in Form*/
	@RequestMapping(value = "/saveConfig", method = RequestMethod.POST)
	public void SaveConfiguration(){
		
		try {
			MongoClient mongo = new MongoClient( "localhost" , 27017 );
			
			DB db = mongo.getDB("test");

			
			DBCollection table = db.getCollection("config");
			
			BasicDBObject document = new BasicDBObject();
			document.put("type", "CTC2");
			document.put("basic", 30);
			document.put("hra", 40);
			document.put("conv_allowance", 19200);
			document.put("med_allowance", 15000);
			
			String [] splAllowance = {"CTC", "HRA", "Conveyance Allowance", "Medical Allowance", "Special Allowance",};
			document.put("specialAllowances", splAllowance);
			System.out.println("Saving");
			table.insert(document);
			System.out.println("Saved");
			
			DBCursor cursor = table.find();
		    int i = 1;
				
		    while (cursor.hasNext()) { 
		       System.out.println("Inserted Document: "+i); 
		       System.out.println(cursor.next()); 
		       i++;
		    } 
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/getConfig", method =  RequestMethod.GET )
	public String GetConfiguration(ModelMap modelMap){
		
		try{
    		int limit = 0;
            // To connect to mongodb server
            MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
   			
            // Now connect to your databases
            DB db = mongoClient.getDB( "test" );
            System.out.println("Connect to database successfully");
            //Authenticate by name/id and password
            boolean auth = db.authenticate("kalyan","passpass".toCharArray());
            System.out.println("Authentication Success: "+auth);
            
            DBCollection coll = db.getCollection("config");
            System.out.println("Collection config selected successfully");
   			
            DBCursor cursor = coll.find();
            List<BasicDBObject> configList = new ArrayList<BasicDBObject>();
            
            while (cursor.hasNext()) 
            {
            	configList.add((BasicDBObject) cursor.next());         
            }
            modelMap.put("configList", configList);
            System.out.println("lllll" + configList);
            
         }catch(Exception e){
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
         }
		return "hello";
	}
	
	/* Saving from data of a structure after saving user data in structure collection*/
	@RequestMapping(value = {"/saveStructure"}, method = RequestMethod.GET)
	public String SaveStructure(FormData formData, RedirectAttributes redirectAttributes) throws IOException{
		
		try {
			MongoClient mongo = new MongoClient( "localhost" , 27017 );
			
			DB db = mongo.getDB("test");

			
			DBCollection table = db.getCollection("structure");
			
			BasicDBObject document = new BasicDBObject();
			System.out.println("6666"+formData.getFirstName());
			document.put("first_name", formData.getFirstName());
			document.put("last_name", formData.getLastName());
			document.put("designation", formData.getDesignation());
			document.put("department", formData.getDepartment());
			document.put("ctcType", formData.getCtcType());
			
			System.out.println("Saving");
			table.insert(document);
			System.out.println("Saved");

			
			DBCollection table1 = db.getCollection("structure");
			
			
			BasicDBObject whereQuery = new BasicDBObject();
			whereQuery.put("_id", formData.getCtcType());
			DBCursor cursor = table1.find(whereQuery);
			while (cursor.hasNext()) 
            {
            	 System.out.println(cursor.next()); 
            	 //modelMap.put("structure",cursor.next());
            }
			redirectAttributes.addAttribute("_id", formData.getCtcType());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:letterPage";
	}
	
	/* Auto triggered from saveStructure method to get redirected into new page with user and CTC details*/
	@RequestMapping(value = "/letterPage", method = RequestMethod.GET)
	public String viewLetter(FormData formData,ModelMap modelMap,@RequestParam String _id) {
    	
		System.out.println(_id);
		try {
			MongoClient mongo = new MongoClient( "localhost" , 27017 );
			
			DB db = mongo.getDB("test");

			
			DBCollection table = db.getCollection("config");
			
			BasicDBObject whereQuery = new BasicDBObject();
			whereQuery.put("_id", new ObjectId(_id));
			
			DBCursor cursor = table.find(whereQuery);
            
			if (cursor.hasNext()) 
            {
            	 System.out.println(cursor.next()); 
            	 modelMap.put("structure",cursor.next());
            }
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "letterPage";

	}
	

}
