package com.hazelcast.imdg.microservices;

import java.io.Serializable;

public class SessionData implements Serializable {

    private final String id;
    private int count = 0;

    public SessionData(String id) {
        this.id = id;
    }

    public void incrementCount() {
        count++;
    }

    public String getId() {
        return id;
    }

    public int getCount() {
        return count;
    }
}
