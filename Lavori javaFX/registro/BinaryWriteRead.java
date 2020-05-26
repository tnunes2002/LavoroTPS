
package registro;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


public class BinaryWriteRead {
    
     public static ArrayList<Contact> ReadContacts(ObjectInputStream in){
         ArrayList<Contact> contacts = new ArrayList<>();
         
       
         while(true){
            try{
                Contact d = (Contact)in.readObject();
                contacts.add(d);
            }catch(Exception e){
                break;
            } 
         }
                 
        return contacts;
    }
     
     public static void WriteContact(ObjectOutputStream out, Contact c){
         try{
             out.writeObject(c);
         }catch(IOException e){
             System.out.println(e);
             return;
         }
         
     }
}
