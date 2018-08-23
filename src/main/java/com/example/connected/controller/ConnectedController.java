package com.example.connected.controller;

import com.example.connected.repository.CitiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class ConnectedController implements ErrorController {
    private static final String template = "Hello, %s!";

    @Autowired
    private CitiesRepository repository;


    @RequestMapping("/")
    public String info() {
        try {
            String result = "no";
            return result;
        } catch(Exception e){
            final String no = "no";
            return no;
        }
    }




    @RequestMapping("/connected")
    public String connected(@RequestParam(value="source" ,defaultValue = "") String source,
                            @RequestParam(value="dest" ,defaultValue = "") String dest) {
        String result = "no";
        try {
            result = "no";
            if ("".equals((source)) || "".equals(dest)) return result;
            if (repository.isConnected(source, dest)) result = "yes";
        } catch(Exception e) {
             result = "no";
        } finally {
            return result;
        }
    }



    private static final String PATH = "/error";

    @RequestMapping(value = PATH)
    public String error() {
        final String no = "no";
        return no;
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }

/*    @ExceptionHandler({IllegalArgumentException.class, NullPointerException.class})
    void handleBadRequests(HttpServletResponse response) throws IOException {
        //response.sendError(HttpStatus.BAD_REQUEST.value());

    }*/
}
