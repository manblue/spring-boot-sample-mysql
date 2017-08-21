package com.example.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.common.LYCReponse;
import com.example.data.PersonRepository;
import com.example.po.Person;

@RestController
@RequestMapping("person/")
public class DataController {

	protected static Logger logger=LoggerFactory.getLogger(DataController.class);
	
	@Autowired
	PersonRepository personRepository;
	
    @RequestMapping("/save")  
    public LYCReponse save(String name,String address,Integer age){  
        logger.debug("save 开始");  
        Person p=personRepository.save(new Person(null,name,age,address));  
        logger.debug("save 结束");  
        
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("person", p);
		return LYCReponse.addSuccessResponse("ok", data);
    }  
      
    @RequestMapping("/q1")  
    public LYCReponse q1(String address){  
        logger.debug("q1 开始");  
        logger.debug("q1 接收参数address={}",address);  
        List<Person> people=personRepository.findByAddress(address);  
		return LYCReponse.addListResponse("ok", people);
    }  
      
    @RequestMapping("/q2")  
    public LYCReponse q2(String name,String address){  
        logger.debug("q2 开始");  
        logger.debug("q2接收参数name={},address={}",name,address);  
        List<Person> people= personRepository.findByNameAndAddress(name, address);  
        return LYCReponse.addListResponse("ok", people);
    }  
      
    @RequestMapping("/q3")  
    public LYCReponse q3(String name,String address){  
        logger.debug("q3 开始");  
        logger.debug("q3接收参数name={},address={}",name,address);  
        List<Person> people=  personRepository.withNameAndAddressQuery(name, address);  
        return LYCReponse.addListResponse("ok", people);
    }  
      
    @RequestMapping("/sort")  
    public LYCReponse sort(){  
        logger.debug("sort 开始");  
        List<Person> people=personRepository.findAll(new Sort(Direction.ASC,"age"));  
        return LYCReponse.addListResponse("ok", people);
    }  
      
    @RequestMapping("/page")  
    public LYCReponse page(){  
        logger.debug("page 开始");  
        Page<Person> people=personRepository.findAll(new PageRequest(1,2));  
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("people", people);
        return LYCReponse.addSuccessResponse("ok", data);
    }  
    
    @RequestMapping("/{id}/update")  
    public LYCReponse updatePerson(@PathVariable("id")Integer id,String address) {
    	logger.debug("updatePerson 开始");  
    	Integer people=  personRepository.updatePerson(id, address);
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("people", people);
        return LYCReponse.addSuccessResponse("ok", data);
	}
}
