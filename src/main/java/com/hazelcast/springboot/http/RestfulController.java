package com.hazelcast.springboot.http;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestfulController {
	private static final Logger LOGGER = LoggerFactory.getLogger(RestfulController.class);

	@Autowired
    private HazelStorage Service;
	
	@RequestMapping(value="/getcount", method = RequestMethod.GET)
	 public ResponseEntity<countdetail> getCount(/*@RequestBody LogIn person,*/HttpSession httpSession) {
		 HazelStorage h=Service;
			//h = HazelStorage.getInstance();
		 
	    	countdetail result =new countdetail();
	    	result.sessionid=httpSession.getId();
			result.count = (Integer) httpSession.getAttribute("hits");
			MyObject ob = h.get(result.sessionid);
			if(ob!=null){
				result.lastValue = ob.MyValue;
			}else{
				result.lastValue="";
			}
			
			
			LOGGER.info("getcount called");
			LOGGER.info("getCount() called, hits was '{}', session id '{}'", result.count, result.sessionid);
			
			MyObject o = new MyObject();
			o.MyValue = "hit"+result.count;
			
			h.replace(result.sessionid, o);
			
			return new ResponseEntity<countdetail> (result,HttpStatus.OK);
	    }
}
