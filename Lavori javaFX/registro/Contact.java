
package registro;

import java.io.Serializable;
import java.util.Date;


public class Contact implements Serializable{
    
    private String number;
    private String name;
    private String surname;
    
    public Contact(
        String number,
        String name,
        String surname
    ){
        this.number = number;
        this.name = name;
        this.surname = surname;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
    
    
}
