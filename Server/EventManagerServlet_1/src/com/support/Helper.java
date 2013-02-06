package com.support;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.dao.EventEntity;
import com.dao.GroupEntity;
import com.dao.UserEntity;
import com.model.Event;
import com.model.EventType;
import com.model.Group;
import com.model.User;

/**
 * this is the helper class used to create and parse xml data.
 * @author Ameya
 *
 */
public class Helper {

	
	public String convertToString(InputStream is){
		
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			is.close();
			return sb.toString();
		} catch (IOException e) {
			System.out.println("IOExcep");
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * this method will parse the credentials into an array list. (Used on server side)
	 * @param inputStream
	 * @return arraylist<String>
	 */
	public ArrayList<String> parseCredentials(InputStream inputStream){
		
		final String xmlRecords = convertToString(inputStream);
		
		try{
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	        DocumentBuilder db = dbf.newDocumentBuilder();
	        InputSource is = new InputSource();
	        is.setCharacterStream(new StringReader(xmlRecords));

	        Document doc = db.parse(is);
	        NodeList nodes = doc.getElementsByTagName("credentials");
	        
	        ArrayList<String> credentials = new ArrayList<String>();
	        
	        for(int i=0;i<nodes.getLength();i++){
	        	Element element = (Element) nodes.item(i);

		           NodeList eventid = element.getElementsByTagName("username");
		           Element line = (Element) eventid.item(0);
		           credentials.add(getCharacterDataFromElement(line));

		           NodeList eventname = element.getElementsByTagName("password");
		           line = (Element) eventname.item(0);
		           credentials.add(getCharacterDataFromElement(line));
	        }
	        
	        return credentials;
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * This method parses the contents to be displayed on home page. Group list and Event list of a user. (Used on client side)
	 * @param inputStream
	 * @return ArrayList<String>
	 */
	public ArrayList<String> parseHomePageXml(InputStream inputStream){
		
		final String xmlRecords = convertToString(inputStream);
		
		try{
			
	        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	        DocumentBuilder db = dbf.newDocumentBuilder();
	        InputSource is = new InputSource();
	        is.setCharacterStream(new StringReader(xmlRecords));

	        Document doc = db.parse(is);
	        NodeList nodes = doc.getElementsByTagName("grouplist");

	        ArrayList<String> groupAndEventNames = new ArrayList<String>();

	        for (int i = 0; i < nodes.getLength(); i++) {
	        	
	           Element element = (Element) nodes.item(i);
	           NodeList userid = element.getElementsByTagName("groupname");
	           
	           for(int j=0;j<userid.getLength();j++){
	        	   
	        	   Element userList = (Element) userid.item(j);
	        	   groupAndEventNames.add(getCharacterDataFromElement(userList));
	           }
	           
	        }
	        
	        NodeList eventNodes = doc.getElementsByTagName("eventlist");
	        
	        for(int i=0;i<eventNodes.getLength();i++){
	        	
	        	Element element = (Element) eventNodes.item(i);
	        	NodeList eventname = element.getElementsByTagName("eventname");
	        	Element eventNameElement = (Element) eventname.item(0);
	        	groupAndEventNames.add(getCharacterDataFromElement(eventNameElement));
	        }
	        
	        return groupAndEventNames;
	    
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return null;
	}

	
	/**
	 * This method parses the xml of list of groups. (Used on client side)
	 * @param inputStream
	 * @return xml String with groupname, description and members.
	 */
	public String parseGroupList(InputStream inputStream) {

		final String xmlRecords = convertToString(inputStream);
		
		try {
			
			Map<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
			ArrayList<String> userNameList = new ArrayList<String>();
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			InputSource is = new InputSource();
			is.setCharacterStream(new StringReader(xmlRecords));

			Document doc = db.parse(is);
			NodeList nodes = doc.getElementsByTagName("grouplist");

			for (int i = 0; i < nodes.getLength(); i++) {

				Element element = (Element) nodes.item(i);
				NodeList groups = element.getElementsByTagName("groups");

				for (int j = 0; j < groups.getLength(); j++) {

					Element group = (Element) groups.item(j);
					
					NodeList groupName = group.getElementsByTagName("groupname");
					Element name = (Element) groupName.item(0);
					userNameList.add(getCharacterDataFromElement(name));
					
					NodeList groupDescription = group.getElementsByTagName("groupdescription");
					Element description = (Element) groupDescription.item(0);
					userNameList.add(getCharacterDataFromElement(description));
					
					NodeList groupMembers = group.getElementsByTagName("members");
					
					for(int k=0;k< groupMembers.getLength();k++){
						
						Element member = (Element)groupMembers.item(k);
						
						NodeList username = member.getElementsByTagName("username");
						Element usernameElement = (Element) username.item(0);
					}
					
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	/**
	 * This method parses the user xml. 
	 * @param inputStream
	 * @return
	 */
	public User parseUser(InputStream inputStream){
		
		final String xmlRecords = convertToString(inputStream);
		
		try{
			
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	        DocumentBuilder db = dbf.newDocumentBuilder();
	        InputSource is = new InputSource();
	        is.setCharacterStream(new StringReader(xmlRecords));

	        Document doc = db.parse(is);
	        NodeList nodes = doc.getElementsByTagName("user");
	        
	        //get user fields from xml and create user object
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * this method parses group xml.
	 * @param inputStream
	 * @return
	 */
	public Group parseGroup(InputStream inputStream){
		
		final String xmlRecords = convertToString(inputStream);
		
		try {
			
	        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	        DocumentBuilder db = dbf.newDocumentBuilder();
	        InputSource is = new InputSource();
	        is.setCharacterStream(new StringReader(xmlRecords));

	        Document doc = db.parse(is);
	        NodeList nodes = doc.getElementsByTagName("group");

	        String groupName = "";
	        String groupDescription = "";
	        ArrayList<String> groupMembers = new ArrayList<String>();
	        
	        for (int i = 0; i < nodes.getLength(); i++) {
	        	
	           Element element = (Element) nodes.item(i);

	           NodeList grpname = element.getElementsByTagName("groupname");
	           Element line = (Element) grpname.item(0);
	           groupName = getCharacterDataFromElement(line);
	           
	           NodeList grpdesc = element.getElementsByTagName("groupdescription");
	           line = (Element) grpdesc.item(0);
	           groupDescription = getCharacterDataFromElement(line);
	           
	           NodeList eventdesc = element.getElementsByTagName("members");
	           line = (Element) eventdesc.item(0);
	           groupName = getCharacterDataFromElement(line);
	           
	           NodeList userid = element.getElementsByTagName("username");
	           
	           for(int j=0;j<userid.getLength();j++){
	        	   
	        	   Element user = (Element) userid.item(j);
		           groupMembers.add(getCharacterDataFromElement(user));
	           }
	           
	        }

	        Group group = new Group(groupName, groupDescription,groupMembers);
	        return group;
	    }
	    catch (Exception e) {
	        e.printStackTrace();
	    }
	    
	    return null;
	}
	/**
	 * This method parses the event xml.
	 * @param inputStream
	 * @return
	 */
	public Event parseEvent(InputStream inputStream){
		
		final String xmlRecords = convertToString(inputStream);
		
		try {
			
	        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	        DocumentBuilder db = dbf.newDocumentBuilder();
	        InputSource is = new InputSource();
	        is.setCharacterStream(new StringReader(xmlRecords));

	        Document doc = db.parse(is);
	        NodeList nodes = doc.getElementsByTagName("event");

	        String eventDescription = null;
	        String eventName = null;
	        String eventLocation = null;
	        String eventDate = null;
	        String eventTime = null;
	        EventType eventType = null;
	        String eventOwnerId = null;
	        ArrayList<String> groupList = new ArrayList<String>();

	        for (int i = 0; i < nodes.getLength(); i++) {
	        	
	           Element element = (Element) nodes.item(i);

	           NodeList eventdesc = element.getElementsByTagName("eventdescription");
	           Element line = (Element) eventdesc.item(0);
	           eventDescription = getCharacterDataFromElement(line);

	           NodeList eventname = element.getElementsByTagName("eventname");
	           line = (Element) eventname.item(0);
	           eventName = getCharacterDataFromElement(line);
	           
	           NodeList eventloc = element.getElementsByTagName("eventloc");
	           line = (Element) eventloc.item(0);
	           eventLocation = getCharacterDataFromElement(line);
	           
	           NodeList eventdate = element.getElementsByTagName("eventdate");
	           line = (Element) eventdate.item(0);
	           eventDate = getCharacterDataFromElement(line);
	           
	           NodeList eventtime = element.getElementsByTagName("eventtime");
	           line = (Element) eventtime.item(0);
	           eventTime = getCharacterDataFromElement(line);
	           
	           NodeList eventtype = element.getElementsByTagName("eventtype");
	           line = (Element) eventtype.item(0);
	           eventType = EventType.valueOf(getCharacterDataFromElement(line));
	           
	           NodeList eventowner = element.getElementsByTagName("eventownerid");
	           line = (Element) eventowner.item(0);
	           eventOwnerId = getCharacterDataFromElement(line);
	           
	           NodeList userid = element.getElementsByTagName("groupname");
	           
	           for(int j=0;j<userid.getLength();j++){
	        	   
	        	   Element userList = (Element) userid.item(j);
		           groupList.add(getCharacterDataFromElement(userList));
	           }
	           
	        }
	        Event event = new Event(eventName, eventDescription, eventDate, eventTime, eventType, eventLocation, eventOwnerId, groupList);
	        return event;
	        
	    }
	    catch (Exception e) {
	        e.printStackTrace();
	    }
	    
	    return null;
	}
	
	 public static String getCharacterDataFromElement(Element e) {
		    Node child = e.getFirstChild();
		    if (child instanceof CharacterData) {
		       CharacterData cd = (CharacterData) child;
		       return cd.getData();
		    }
		    return "?";
	 }
	 
	 /**
	  * this method creates the home page xml to be sent to client from server.(Used on server side).
	  * @param eventEntities
	  * @return
	  */
	 public String createHomePageXml(ArrayList<EventEntity> eventEntities){
		 
		 StringBuffer homePageXml = new StringBuffer();
		 homePageXml.append("<homepage>");
		 homePageXml.append("<grouplist>");
		 
		 for(EventEntity entity :eventEntities){
			 
			  ArrayList<GroupEntity> groupEntities = entity.getGroupEntity();
			  for(GroupEntity groupEntity : groupEntities){
				 
				 homePageXml.append("<groupname>"+groupEntity.getGroupName()+"</groupname>");
			 }
			
		 }
		 
		 homePageXml.append("</grouplist>");
		 homePageXml.append("<eventlist>");
		 
		 for(EventEntity eventEntity : eventEntities){
			 
			 homePageXml.append("<eventname>"+eventEntity.getEventName()+"</eventname>");
		 }
		 homePageXml.append("</eventlist>");
		 homePageXml.append("</homepage>");
		 
		 return homePageXml.toString();
	 }
	 
	 public String createGroupXmlTest(ArrayList<GroupEntity> groupEntities){
		 
		 StringBuffer groupXml = new StringBuffer();
		 
		 groupXml.append("<grouplist>");
		 
		 for(GroupEntity entity : groupEntities){
			 
			 groupXml.append("<groups>");
			 groupXml.append("<groupname>"+entity.getGroupName()+"</groupname>");
			 groupXml.append("<groupdescription>"+entity.getGroupDescription()+"</groupdescription>");
			 groupXml.append("<members>");
			 ArrayList<UserEntity> userEntities = entity.getUserEntities();
			 for(UserEntity userentity : userEntities){
				 groupXml.append("<username>"+userentity.getName()+"</username>");
			 }
			 groupXml.append("</members>");
			 groupXml.append("</groups>");
		 }
		groupXml.append("</grouplist>");
		
		return groupXml.toString();
		
	}
	 
	 public String createGroupXml(Group group){
		 
		 StringBuffer groupXml = new StringBuffer();
		 
		 groupXml.append("<group>");
		 groupXml.append("<groupname>"+group.getGroupName()+"</groupname>");
		 groupXml.append("<groupdescription>"+group.getGroupDescription()+"</groupdescription>");
		 groupXml.append("<members>");
		 ArrayList<String> userList = group.getMembers();
		 for(String username : userList){
			 groupXml.append("<username>"+username+"</username>");
		 }
		 groupXml.append("</members>");
		 groupXml.append("</group>");
		 
		 return groupXml.toString();
	 }
	 
		    
	public String getCurrentDate(){
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date date = new Date();
		return dateFormat.format(date);
	}
	
	public String getCurrentTime(){
		
		DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        return timeFormat.format(date);
	}
}

  /*public static void main(String arg[]) {
     String xmlRecords =
      "<data>" +
      " <employee>" +
      "   <name>John</name>" +
      "   <title>Manager</title>" +
      "	<age>10</age>"+
      " </employee>" +
      " <employee>" +
      "   <name>Sara</name>" +
      "   <title>Clerk</title>" +
      "	<age>10</age>"+
      " </employee>" +
      "</data>";

    try {
        DocumentBuilderFactory dbf =  DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        InputSource is = new InputSource();
        is.setCharacterStream(new StringReader(xmlRecords));

        Document doc = db.parse(is);
        NodeList nodes = doc.getElementsByTagName("employee");

        // iterate the employees
        for (int i = 0; i < nodes.getLength(); i++) {
           Element element = (Element) nodes.item(i);

           NodeList name = element.getElementsByTagName("name");
           Element line = (Element) name.item(0);
           System.out.println("Name: " + getCharacterDataFromElement(line));
           NodeList title = element.getElementsByTagName("title");
           line = (Element) title.item(0);
           System.out.println("Title: " + getCharacterDataFromElement(line));
           
           NodeList age = element.getElementsByTagName("age");
           line = (Element) age.item(0);
           System.out.println("age: " + getCharacterDataFromElement(line));
        }
    }
    catch (Exception e) {
        e.printStackTrace();
    }
    
    
  }*/

 