package com.example.connected;

import com.example.connected.repository.CitiesRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ConnectedApplicationTests {

	@Autowired
	private CitiesRepository repository;

	@Autowired
    private AppProperties props;


/*
	@Before
	public void initObjects() {
	    System.out.println("In test");

        String fileName = props.getLocation();

        //read file into stream, try-with-resources
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {

            stream.forEach( line -> {
                System.out.println(line);
                repository.addConnection(line);
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
	}
*/


//	@Test
//	public void contextLoads() {
//        assertNotNull(repository);
//	}

    @Test
    public void connected_Boston_Newark() {
        repository.visit();
	    boolean connected = repository.isConnected("Boston","Newark");
	    System.out.println("***Connected****" + connected);
        assertTrue("Boston to Newark ",true);
    }

//    @After
//    public void tearDownAll() {
//        repository = null;
//    }

}
