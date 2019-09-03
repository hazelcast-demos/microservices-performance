package org.hazelcast.microservices;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class SessionDataController {

    private final SessionDataHandler sessionDataHandler;

    public SessionDataController(SessionDataHandler sessionDataHandler) {
        this.sessionDataHandler = sessionDataHandler;
    }

    @GetMapping("/session")
    public SessionData readSessionData(HttpSession session) {
        var sessionData = sessionDataHandler.get(session);
        sessionDataHandler.increment(session);
        return sessionData;
    }
}
