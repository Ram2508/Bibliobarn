package com.bibliobarn.beans;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "user")
public class UserAccount implements Serializable{
 
   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    
   private int userid;
   private String firstName;
   private String lastName; 
   private String userName;
   private String gender;
   private String password;
   private String email;
   private String phone;
    
   	public UserAccount() {
        
   	}
   	
   	public String getFirstName() {
	   return firstName;
   	}

   	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getUserid() {
		return userid;
   }

   public void setUserid(int userid) {
	   this.userid = userid;
   }
   
   public String getUserName() {
       return userName;
   }
 
   public void setUserName(String userName) {
       this.userName = userName;
   }
 
   public String getGender() {
       return gender;
   }
 
   public void setGender(String gender) {
       this.gender = gender;
   }
 
   public String getPassword() {
       return password;
   }
 
   public void setPassword(String password) {
       this.password = password;
   }

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public String getPhone() {
	return phone;
}

public void setPhone(String phone) {
	this.phone = phone;
}
 
}