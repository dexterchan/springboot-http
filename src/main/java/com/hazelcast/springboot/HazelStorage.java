package com.hazelcast.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hazelcast.core.HazelcastInstance;

@Service
public class HazelStorage<S> {
 @Autowired
 private HazelcastInstance instance;
 private String myName="Storage";
 
 public HazelStorage(){
	 this.myName = this.myName+this.getClass().getName();
	 
 }
 
 public void insert (String key,S value) {
   instance.getMap(myName).put(key, value);
 }
 
}