package com.home.routing;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Repository
@Log4j2
public class CountryRepo {

    private static final String COUNTRIES_JSON = "countries.json";
    private List<Country> countries;

    @PostConstruct
    public void init() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream inputStream = CountryRepo.class.getClassLoader().getResourceAsStream(COUNTRIES_JSON);
        countries = objectMapper.readValue(inputStream, new TypeReference<List<Country>>() {});
        log.info(countries);
    }

    public List<Country> getAllCountries(){
        return countries;
    }
}
