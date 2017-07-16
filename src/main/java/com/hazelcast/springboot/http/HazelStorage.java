package com.hazelcast.springboot.http;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hazelcast.core.HazelcastInstance;

@Service
public class HazelStorage {
 
 private HazelcastInstance instance;
 
 public static String myName="Storage";
 
 private static HazelStorage _instance=null;
 
 
 @Autowired
 public HazelStorage(HazelcastInstance hazelcastInstance) {
     this.instance = hazelcastInstance;
     //myName = this.myName+this.getClass().getName();
 }
 
 public static HazelStorage getInstance(){
	 if(_instance ==null){
		 _instance = new HazelStorage();
	 }
	 
	 return _instance;
 }
 private HazelStorage(){
	 //this.myName = this.myName+this.getClass().getName();
	 
 }
 
 public MyObject get(String key){
	 int i=0;
	 i=0;
	 return (MyObject) instance.getMap(myName).get(key);
 }
 
 public void insert (String key,MyObject value) {
   instance.getMap(myName).put(key, value);
 }
 public void replace (String key,MyObject value) {
	 insert(key,value);
	 instance.getMap(myName).replace(key,value);
	   
 }
}