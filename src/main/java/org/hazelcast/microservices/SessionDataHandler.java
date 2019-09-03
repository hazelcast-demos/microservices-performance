package org.hazelcast.microservices;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class SessionDataHandler {

    private static final String KEY = "ZEMdbr9SQF";

    public SessionData get(HttpSession session) {
        var object = session.getAttribute(KEY);
        if (object == null) {
            var data = new SessionData(session.getId());
            session.setAttribute(KEY, data);
            return data;
        }
        return SessionData.class.cast(object);
    }

    public void increment(HttpSession session) {
        var data = get(session);
        data.incrementCount();
        session.setAttribute(KEY, data);
    }
}
