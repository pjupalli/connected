package com.example.connected;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties
public class AppProperties {

    public String getLocation() {
        return location;
    }

    @Value("${graph.location}")
    public String location;


    public void AppProperties(){}


}
