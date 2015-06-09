
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class MyDatabase

{     	
	static long pointer;
	static String temp2;
	static boolean flag=false;
	static int counter =-1;
	static long arr[][];
	static ArrayList<Long> list=new ArrayList<Long>();
	static RandomAccessFile file = null;
	static ArrayList<Long> array = new ArrayList<Long>();
	static ArrayList<String> id= new ArrayList<String>();
	static ArrayList<String> lastName= new ArrayList<String>();
	static ArrayList<String> state= new ArrayList<String>();
	static ArrayList<String> email= new ArrayList<String>();
	static ArrayList<Long> pointers= new ArrayList<Long>();
	static int occur=0;
	static String[] tokens;
	static HashMap<String,ArrayList<Long>> mapLastName = new HashMap<String,ArrayList<Long>>();
	static HashMap<String, Long> mapState  = new HashMap<String, Long>();
	
	public static void main(String args[])throws IOException
    {
		  System.out.println("Begin");
		  System.out.println();
		  //Creation of files
		  FileInputStream in = null;
		  Writer writer=null;
		  Writer writer2=null;
		  Writer writer3=null;
	      File file2 = new File("src/id.ndx");
	      file2.createNewFile();
		  File file3 = new File("src/last_name.ndx");
		  file3.createNewFile();
		  File file4 = new File("src/state.ndx");
		  file4.createNewFile();
		  String unique="";
		  
		  //Creation of database from csv file. Database is stored in database.db file
		  try
	         {
	             in = new FileInputStream("src/us-500.csv");
	             System.out.println("Opening csv file for inputting values...");
	             System.out.println("");
	             file = new RandomAccessFile("src/data.db", "rw");
	             System.out.println("Creating the Random Access File");
	             System.out.println("");
	       	     file.seek(0);	       	     	             
	             int s;
	             while ((s = in.read()) != -1)
	             {
	                       file.writeByte(s);               //Else it writes the data as a byte to the file
	             }
	             System.out.println("Writing data records to the Random Access File");
	             System.out.println("");
	             
	         } 
		  
		  catch(IOException e)
		  {
			  
		  }
		  
		  finally 
	      {
	             in.close();
	             file.close();
	             
	       }		  	
		  
		  writer = new FileWriter(file2);
    	  writer.write("ID" +"  		"+ "ADDRESS \n");
    	  writer2 = new FileWriter(file3);
    	  writer2.write("LAST NAME" +"  		"+ '\t' + "ADDRESS \n");
    	  writer3 = new FileWriter(file4);
    	  writer3.write("STATE" +"  		"+ '\t' +"ADDRESS \n");
    	   
		  //Opening the database file for reading and writing
    	  file = new RandomAccessFile("src/data.db", "rw");
    	  file.seek(0);
    	  int l;
    	  String temp;
    	  int i=0;
          
          //Creating Index files for ID field,last_name field,state field
    	  System.out.println("Creating Index Files now...");
    	  System.out.println("");
          try
    	  {
    		  while((l = file.read())!=-1)
        	  {
    			
    			  if(l=='\n')
    			  {
    				  pointer=file.getFilePointer();
    				  pointers.add(pointer);
    			  }    			  
        	  } 
    		  int size=pointers.size();
    		  for(i=0;i<size-1;i++)
    		  {
    			  pointer=(pointers.get(i));
    			  file.seek(pointer);
    			  temp=file.readLine();
    			  tokens = temp.split(",");
        	      id.add(tokens[0]);
        	      lastName.add(tokens[2]);
        	      state.add(tokens[7]);
        	      email.add(tokens[12]);
    		  }
    		  
    		  for(i=0;i<size-1;i++)
    		  {
    			  writer.write(id.get(i) +"   \t" +"\t" + pointers.get(i) +"\n");
    			  writer2.write(lastName.get(i) +"                                      " + pointers.get(i) +"\n");
    			  writer3.write(state.get(i) +"                        "+pointers.get(i) +"\n");
    			  for(i=0;i<size-1;i++)
        		  {
        			  if((state.get(i)).equals(state.get(0)));
        			  {
        				  
        				  
        			  } 
        			 
        		  } 
    		  }
    		  
    		  System.out.println("id.ndx File created!");
    		  System.out.println("last_name.ndx File created!");
    		  System.out.println("state.ndx File created!");
    		  System.out.println("");
    	  }
    	  
    	  catch(IOException e)
    	  {
    		System.out.println("Error: "+e);
    	  }
    	  
    	  finally
    	  {
    		  file.close();
              writer.close();
              writer2.close();
              writer3.close();
    	  }
          
        /*Implementing hashmaps
          String v;i=0;
          while(v!=null)
          {
        	  for(int c=0;i<(pointers.size())-1;i++)
        	  {
        		  
        		  if(state.get(c).equals(state.get(i)))
        		  {
        			  array.add(pointers.get(c));
        			  mapState.put(state.get(c),array);
        		  }
        	  }
          }
          
          */
          
          
          
          
          //Required Action #1 - Select
          System.out.println("Starting the select action now...");
          System.out.println();
          unique = "\"50\"";
          String unique2="\"Foller\"";
          String unique3="\"WI\"";
          String comp="";
          try
          {
        	  file = new RandomAccessFile("src/data.db", "rw");
        	  System.out.println("ID Selected is: "+ unique); 
        	  System.out.println();
	        	for(i=0;i<id.size();i++)
	        	{
	        		comp=id.get(i);
	        		if(comp.equals(unique))
	        		{
	        			pointer=pointers.get(i);
	        			file.seek(pointer);
	        			System.out.println("ID found!");
	        			System.out.println(file.readLine());
	        			flag=true;
	        			System.out.println("Selection  - SUCCESSFUL");
	        		}
	        	}
	        		
	        		
	        		System.out.println("Last Name Selected is: "+ unique2);        	  
		        	for(i=0;i<lastName.size();i++)
		        	{
		        		comp=lastName.get(i);
		        		if(comp.equals(unique2))
		        		{
		        			pointer=pointers.get(i);
		        			System.out.println("Last name found!");
		        			file.seek(pointer);
		        			System.out.println(file.readLine());
		        			flag=true;
		        			
		        		}
	        	}
	        	
	        	if(!flag)
	        	{
	        		System.out.println("You have entered an invalid last name.");
	        		System.out.println("Selection  - FAILED");
	        		System.out.println();
	        	}
	        	else
	        		{
	        		   System.out.println("Selection  - SUCCESSFUL");
	        		   System.out.println();
	        		}
	        	
	        	System.out.println("State Selected is: "+ unique3);        	  
	        	for(i=0;i<state.size();i++)
	        	{
	        		comp=state.get(i);
	        		if(comp.equals(unique3))
	        		{
	        			pointer=pointers.get(i);
	        			System.out.println("State found!");
	        			System.out.println();
	        			file.seek(pointer);
	        			System.out.println(file.readLine());
	        			flag=true;
	        			
	        		}
        	}
        	
        	if(!flag)
        	{
        		System.out.println("You have entered an invalid state");
        		System.out.println("Selection  - FAILED");
        		System.out.println();
        	}
        	
        	else
        		{
        		   System.out.println("Selection  - SUCCESSFUL");
        		   System.out.println();
        		}
	        		
	        	
          }
          
          catch(IOException e)
    	  {
    		System.out.println("Error: "+e);
    	  }
          
          finally
    	  {
    		  file.close();
    	  }
          
          
          //Required Action #2 - Insert
          System.out.println("Starting the insertion of records now...");
          System.out.println();
          file = new RandomAccessFile("src/data.db", "rw");
          unique="\"502\"";
          unique2="\"jbutt@gmail.com\"";
          String newRec ="'502','James','Butt','Benton, John B Jr','6649 N Blue Gum St','New Orleans','Orleans','LA','70116','504-621-8927','504-845-1427','jbutt@gmail.com','http://www.bentonjohnbjr.com'";
          String newrec2="'502','James','Butt','Benton, John B Jr','6649 N Blue Gum St','New Orleans','Orleans','LA','70116','504-621-8927','504-845-1427','jamesb@gmail.com','http://www.bentonjohnbjr.com'";
          unique3="\"jamesb@gmail.com\"";
          System.out.println("Record to be inserted is:");
          System.out.println(newRec);
          System.out.println("Checking to see if record id is a duplicate...");
          
        	 for(i=0;i<id.size();i++)
        	 {
        		 if(id.get(i).equals(unique))
        		 {
        			 System.out.println("Sorry,the ID you are trying to enter already exists.");
        			 flag=false;
        			 break;
        		 }
        		
        	 }
        	 
        	 if(!flag)
         	{
         		System.out.println("Insertion  - FAILED");
         	}
         	else
         		{
         		 
        			 System.out.println("Record ID is not a duplicate");
        			 flag=true;
        			 System.out.println("Inserting the record into the database now");
        			 System.out.println();
        			 for(i=0;i<email.size();i++)
                	 {
                		 comp=email.get(i);
        				 if(comp.equals(unique2))
                		 {
                			 System.out.println("Sorry,the email ID you are trying to enter already exists.Every user must have a unique email ID");
                			 System.out.println("The duplicated email ID is: "+unique2);
                			 System.out.println("Insertion  - FAILED");
                			 
                			 flag=false;
                			 break;
                		 }
        				 
                	 }
        			 
        			 System.out.println("On changing the email ID to a unique one:");
        			 System.out.println("New email ID"+ unique3);
        			 System.out.println("Modified record:");
        			 System.out.println(newrec2);
        			 pointer=pointers.size();
        			 id.add("\"502\"");
        			 lastName.add("\"Butt\"");
        			 state.add("\"LA\"");
        			 email.add("\"jamesb@gmail.com\"");
        			 pointers.add(pointer);
        			 int ind=id.indexOf("\"502\"");
        			 file.seek(pointers.get(ind));
        			 file.write(newrec2.getBytes());
        			 flag=true;
        			 if(flag)
        			 {
        				 System.out.println("Insertion  - SUCCESSFUL");
        			 }
         		  }
        	 file.close();
        	 System.out.println("The total number of records in the database are : " + id.size());
       	  	 System.out.println();
        	             
             
          
         //Required Action # 3 - Delete
          System.out.println("Starting with the delete action now...");
          System.out.println();
          unique="\"12\"";  //Selecting ID no. 12 for deletion
          try
          {
        	  file = new RandomAccessFile("src/data.db", "rw");
        	  file.seek(0);
        	  System.out.println("Unique identifier(ID) selected for deletion: " + unique);
        	  System.out.println("Searching database for ID...");
        	  
        	   for(i=0;i<id.size();i++)
	        	{
	        		comp=id.get(i);
        		   if(comp.equals(unique))
	        		{
	        			System.out.println("The given ID exists in the database");
	        			pointer=pointers.get(i);
	        			
	        			Long pointer1 = pointers.get(i+1);	        			
	        			long len=(--pointer1)-pointer;
	        			file.seek(pointer);
	        			while(len!=0)
	        			 {
	               		     file.write(0);
	        				 len--;	        				 	               		  
	        			 }
	        			
	        			 writer = new FileWriter(file2);
	        			 writer2 = new FileWriter(file3);
	        			 writer3 = new FileWriter(file4);
	        			 System.out.println("Updating indices now...");
	        			 System.out.println();
	        		     writer.write("ID" +"  		"+'\t'+ "ADDRESS \n");
	        		     writer2.write("LAST NAME" +"  		"+ '\t' + "ADDRESS \n");
	        	    	 writer3.write("STATE" +"  		"+ '\t' +"ADDRESS \n");
	        			 id.remove(i);
	        			 lastName.remove(i);
	        			 state.remove(i);
	        			 email.remove(i);
	        			 pointers.remove(i);
	        			 System.out.println("The record has been deleted from the database");
	        			 System.out.println("All corresponding indices of the record have also been deleted");
	        			 System.out.println();
	        	    	 for(i=0;i<id.size();i++)
	           		     {
	           			      writer.write(id.get(i) +"   \t" +"\t" + pointers.get(i) +"\n");
	           			      writer2.write(lastName.get(i) +"   \t" +"\t" + pointers.get(i) +"\n");
	           			   	  writer3.write(state.get(i) +"   \t" +"\t" + pointers.get(i) +"\n");
	           		     }
	        	    	 
	        	    	 flag=true;
	        			 System.out.println("Deletion - SUCCESSFUL");
	        			 
	        			 break;
	        		}
	        	}
        	  
        	  if(!flag)
        	  { 
        		  System.out.println("The given ID does not exist in the database!");
        		  System.out.println("Deletion - FAILED");
        	  
        	  }
          }
          
          
          catch(IOException e)
    	  {
        	  System.out.println("Error: "+e);
    	  }
    	  
    	  finally
    	  {
    		  file.close();
    		  writer.close();
    	  }
         
          System.out.println("The total number of records in the database are : " + id.size());
    	  System.out.println();
          
          
          //Required Action #4 - Modify
          System.out.println("Starting with the modify action now...");
          unique = "\"498\"";
          String field = "phone1";
          String current="308-726-2182";   //Current Field Value
          String val="318-734-2672";         //Modified Field Value
          try
          {
        	  file = new RandomAccessFile("src/data.db", "rw");
        	  file.seek(0);
        	  System.out.println("Unique identifier(ID) selected for modification: " + unique);
        	  System.out.println("Field name selected for modification: "+field);
        	  System.out.println("Current Value of Field: "+current);
        	  System.out.println("Searching database for ID...");
        	  int p=0;
        	  int b1;
        	  
        	  for(i=0;i<id.size();i++)
	        	{
	        		comp=id.get(i);
        		  	if(comp.equals(unique))
	        		{
	        			System.out.println("The given ID exists in the database");
	        			pointer=pointers.get(i);
	        			file.seek(pointer);
	        			
						while((unique2=file.readLine())!=null)
	        			{
	        				
	        					if(unique2.contains(current))
	        					{
	        		    			
	        						file.seek(pointers.get(i));
	        						System.out.println("Record to be modified is:");
	        		    			System.out.println(file.readLine());
	        		    			unique3=unique2.replace(current, val);
	        		    			file.seek(pointers.get(i));
	        		    			file.write(unique3.getBytes());
	        		    			System.out.println("New Value of Field " + field + " : "+ val);
	        		    			System.out.println("The updated record is: ");
	        		    			pointer=pointers.get(i);
	        	        			file.seek(pointer);
	        		    			System.out.println(file.readLine());
	        		    			break;
	        		    			        		    	         
	        		    		}
	        			}
						
	        			 flag=true;
	        			 System.out.println("Modification - SUCCESSFUL");
	        			 break;
	        		}
	        	}
      	  
        	  if(!flag)
        	  { 
        		  System.out.println("The given ID does not exist in the database!");
        		  System.out.println("Modification - FAILED");
      	  
        	  }
        	  
          }
          
          catch(Exception e)
    	  {
        	  System.out.println("Error: "+e);
    	  }
          
          finally
          {
        	  file.close();
          }
          
          System.out.println("The total number of records in the database are : " + id.size());
    	  System.out.println();
    	  
          //Required Action #5 - Count
          System.out.println("Starting with the count action");
          System.out.println("Counting the number of records in the database now...");
          System.out.println();
          counter=id.size();
    	  System.out.println("The total number of records in the database are : " + counter);
    	  System.out.println();
    	  System.out.println("End of Program");
    	  
    }//End of main method
	
}//End of class