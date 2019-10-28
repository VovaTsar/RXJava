package com.kpi.mylab.dao;

import com.couchbase.client.java.document.json.JsonArray;
import com.couchbase.client.java.document.json.JsonObject;
import com.couchbase.client.java.query.N1qlQuery;
import com.couchbase.client.java.query.N1qlQueryResult;
import com.couchbase.client.java.query.N1qlQueryRow;
import com.kpi.mylab.model.EntryModel;
import org.springframework.stereotype.Component;

@Component
public class DownloadDao extends AbstractDao {
    public synchronized Integer countEntries(){
        N1qlQueryResult result = this.entries.query(N1qlQuery.simple("SELECT count(*) FROM users"));
        N1qlQueryRow row = result.allRows().get(0);
        JsonObject json = row.value();
        return (int)json.get("$1");
    }

    public EntryModel entryForRXJava(int offset){
        String query = "SELECT * FROM users LIMIT 1 OFFSET $1";
        N1qlQueryResult result = this.entries.query(N1qlQuery.parameterized(query, JsonArray.from(offset)));
        if(result.allRows() == null && result.allRows().isEmpty())
            return null;
        JsonObject json = (JsonObject) result.allRows().get(0).value().get("users");
        return new EntryModel(json.getString("name"), json.getInt("age"));
    }
}
