package com.example.connected.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class CitiesRepository {
    private Map<String,LinkedList<String>> graph = new HashMap<String,LinkedList<String>>();

    private Logger log = LoggerFactory.getLogger(this.getClass());

    public void addConnection(String city1, String city2){
        log.info("**** Adding Connection ****");
        log.info("City 1: " + city1 + ", City 2: " + city2);
        LinkedList<String> list = graph.getOrDefault(city1, new LinkedList<String>());
        if (!list.contains(city2)) list.add(city2);
        graph.put(city1,list);

        LinkedList<String> list2 = graph.getOrDefault(city2, new LinkedList<String>());
        if (! list2.contains(city1)) list2.add(city1);
        graph.put(city2,list2);


        //graph.getOrDefault(city2, new LinkedList<String>()).add(city1);

        LinkedList<String> cities = graph.get(city1);

//        for(String city: cities){
//            log.info("check: " + city);
//        }

        for (String city : graph.keySet()) {
            log.info("Added city: " + city);
        }
    }

    public void addConnection(String line){

        log.info("line: " + line);
        String[] cities = line.split(",\\s*");
        if (2 == cities.length) addConnection(cities[0], cities[1]);
    }

    public boolean isConnected(String v, String w){

        if (v.isEmpty() || w.isEmpty()) return false;
        if (v.equals(w)) return false;

        //LinkedList<String> visited = new LinkedList<>();
        //visited.add(v);

        LinkedList<String> searched = new LinkedList<>();

        log.info("Size of KeySet: " + graph.keySet().size());

        Set<String> keys = graph.keySet();

        for (String city: keys ){
            log.info("key: " + city );
        }

        LinkedList<String> neighbors =  graph.getOrDefault(v,new LinkedList<String>());
        if(neighbors.isEmpty()) return false;

        if (neighbors.contains(w)) return true;

        while (!searched.isEmpty()){
            String city = searched.remove(0);
            if (!searched.contains(city)) {
                if (w.equals(city)){
                    //reached destination
                    return true;
                } else {
                    searched.addAll(graph.get(city));

                }
            }
        }
        return false;

    }


    public void visit(){
        //LinkedList<String> visited = new LinkedList<>();
        //visited.add(city);

        //System.out.print(city + " [");
        log.info("**Touring**");
        for(String city: graph.keySet()){
            System.out.print("City:" + city + " [");
            LinkedList<String> list = graph.getOrDefault(city,new LinkedList<String>());
            for(String dest: list){
                System.out.print(dest + ",");
            }
            System.out.println( "]");
        }
        System.out.println("");
    }

    public void  display(){
        ArrayList<String> cities = (ArrayList<String>) graph.keySet();
        for (String city: cities){
            System.out.print(city);
            LinkedList<String> neighbors = (LinkedList<String>) graph.get(city);
        }
    }
}
