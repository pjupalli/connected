package com.example.connected;

import com.example.connected.repository.CitiesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

@SpringBootApplication
@Controller
public class ConnectedApplication  {


    private AppProperties props;


    @Autowired
    public void setApp(AppProperties props) {
        this.props = props;
    }

	public static void main(String[] args) {
		SpringApplication.run(ConnectedApplication.class, args);
	}


	@Bean
	CommandLineRunner init(CitiesRepository repository) {



		return args -> {

		    Logger log = LoggerFactory.getLogger((this.getClass()));

            String fileName = props.getLocation();

            //read file into stream, try-with-resources
            try (Stream<String> stream = Files.lines(Paths.get(fileName))) {

                stream.forEach( line -> {
                    System.out.println("**Line**" + line);
                    repository.addConnection(line);
                });
                //repository.visit();

            } catch (IOException e) {
                log.error("error" + e.getLocalizedMessage());
                e.printStackTrace();
            }
        };

	}
}
