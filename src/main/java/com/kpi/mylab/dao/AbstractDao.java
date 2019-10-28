package com.kpi.mylab.dao;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.CouchbaseCluster;

public abstract class AbstractDao {
    protected Bucket entries = CouchbaseCluster.create("127.0.0.1")
            .authenticate("qwerty", "cerber12345")
            .openBucket("users");
}
