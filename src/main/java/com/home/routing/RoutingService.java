package com.home.routing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.*;

@Service
public class RoutingService {

    private Map<String, LinkedList<String>> adj = new HashMap<>();      /* adjacency list */
    @Autowired
    private CountryRepo countryRepo;

    @PostConstruct
    public void init() throws IOException {
        List<Country> countries = countryRepo.getAllCountries();
        for (Country country : countries) {
            for (String border : country.getBorders()) {
                insertEdge(country.getCca3(), border);
            }
        }
    }

    private void insertEdge(String v, String w) {
        if(adj.containsKey(v)){
            adj.get(v).add(w);
        }else{
            LinkedList<String> edges = new LinkedList<>();
            edges.add(w);
            adj.put(v, edges);
        }
    }

    public Optional<LinkedList<String>> findRoute(String origin, String destination) {
        Queue<String> que = new LinkedList<>();
        Map<String, LinkedList<String>> routes = new HashMap<>();
        routes.put(origin, new LinkedList<>(Collections.singletonList(origin)));
        String neighbor;
        que.add(origin);       /* root node is added to the top of the queue */
        while (que.size() != 0) {
            origin = que.poll();        /* remove the top element of the queue */
            for (int i = 0; i < adj.get(origin).size(); i++)  /* iterate through the linked list and push all neighbors into queue */ {
                neighbor = adj.get(origin).get(i);
                if(!routes.containsKey(neighbor)){
                    LinkedList<String> newRoutes = new LinkedList<>(routes.get(origin));
                    newRoutes.add(neighbor);
                    routes.put(neighbor, newRoutes);
                    que.add(neighbor);
                    if(neighbor.equals(destination)){
                        return Optional.of(newRoutes);
                    }
                }
            }
        }
        return Optional.empty();
    }

    public boolean isValidCca3(String cca3){
        return cca3.length() == 3 && adj.containsKey(cca3);
    }
}
