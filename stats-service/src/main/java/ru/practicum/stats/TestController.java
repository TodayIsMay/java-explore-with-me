package ru.practicum.stats;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
public class TestController {

    @GetMapping("/events")
    public Integer testMethod(@PathVariable String number) {
        Map<String,String> uriVariables = new HashMap<>();
        uriVariables.put("number", number);
        ResponseEntity<Integer> responseEntity = new RestTemplate().getForEntity(
                "http://localhost:8080/test/diff/{number}", Integer.class, uriVariables);
        Integer response = responseEntity.getBody();
        return response.intValue();
    }
}
