package fr.mojope.hackathon;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by christophe on 14/06/17.
 */
@RestController
public class Ping {

    @RequestMapping("/ping")
    public String ping() {
        return "OK3";
    }

    @RequestMapping("/")
    public String messageRacine() {
        return "Services de la team MOJoPE";
    }
}
