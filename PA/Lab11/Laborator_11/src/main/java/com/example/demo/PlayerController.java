package com.example.demo;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/players")
public class PlayerController {

    @GetMapping
    public List<Player> read() {
        List<Player> returnList = new ArrayList<Player>();
        returnList.add(new Player("Test1"));
        returnList.add(new Player("Test2"));
        return returnList;
    }
}

