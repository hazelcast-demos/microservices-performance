package com.hazelcast.imdg.microservices;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class SessionDataHandler {

    private static final String KEY = "ZEMdbr9SQF";

    public SessionData get(HttpSession session) {
        Object object = session.getAttribute(KEY);
        if (object == null) {
            SessionData data = new SessionData(session.getId());
            session.setAttribute(KEY, data);
            return data;
        }
        return (SessionData) object;
    }

    public void increment(HttpSession session) {
        SessionData data = get(session);
        data.incrementCount();
        session.setAttribute(KEY, data);
    }
}
