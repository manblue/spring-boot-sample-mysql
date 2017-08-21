package com.example.data;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.po.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {
    List<Person> findByName(String name);  
    
    List<Person> findByAddress(String address);  
      
    List<Person> findByNameAndAddress(String name,String address);  
      
    @Query("select p from Person p where p.name= :name and p.address= :address")  
    List<Person> withNameAndAddressQuery(@Param("name")String Name,@Param("address")String address);  
    
    @Modifying
    @Transactional
    @Query("update Person set address = :address where id = :id ")
//    Integer updatePerson(Integer id,String address);
    Integer updatePerson(@Param("id")Integer id,@Param("address")String address);

}
