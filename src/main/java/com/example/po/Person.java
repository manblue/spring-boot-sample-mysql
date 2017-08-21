package com.example.po;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "lyc_person")
public class Person implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2178484165742524508L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;  
    private String name;  
    private Integer age;
    private String address;
    
    public Person() {
	}
    public Person(Integer id, String name, Integer age,String address) {  
        super();  
        this.id = id;  
        this.name = name;  
        this.age = age; 
        this.address = address;
    }  
    
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}  
    
    
    
}
