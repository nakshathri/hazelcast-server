
package com.cache.server;

import java.net.URL;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.hazelcast.config.Config;
import com.hazelcast.config.XmlConfigBuilder;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.Member;
import com.hazelcast.util.CollectionUtil;


/**
 * 
 * 
 *
 */
public class HazelcastServer {

	public static void main(String[] args) {
		try{
			System.out.println("initializing the Hazelcast Server");
			String configFileName = "hazelcast.xml";
			
			String instanceName = System.getProperty("instance");
			instanceName = StringUtils.isBlank(instanceName) ? "hazelcast" : instanceName;
			
			System.out.println("Starting the Hazelcast instance with configFile="+configFileName+" and for instance="+instanceName);
			URL configFileUrl = HazelcastServer.class.getClassLoader().getResource(configFileName);
			XmlConfigBuilder builder = new XmlConfigBuilder(configFileUrl);
			Config config = builder.build();
			HazelcastInstance instance1 = Hazelcast.newHazelcastInstance(config);
			HazelcastInstance instance2 = Hazelcast.newHazelcastInstance(config);
			HazelcastInstance instance3 = Hazelcast.newHazelcastInstance(config);
			System.out.println("Hazelcast server is initialized with configFile="+configFileName+" with the instanceName = "+instanceName);
			
			Set<Member> members = instance1.getCluster().getMembers();
			if(CollectionUtil.isNotEmpty(members)){
				System.out.println("Joined the cluster having node count="+members.size());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
