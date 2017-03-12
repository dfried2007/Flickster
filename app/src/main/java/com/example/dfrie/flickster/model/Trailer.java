package com.example.dfrie.flickster.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by dfrie on 3/12/2017.
 */

public class Trailer {

    String key;
    String name;
    String type;

    public String getKey() {
        return key;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public Trailer(JSONObject jobj) throws JSONException {
        key = jobj.getString("key");
        name = jobj.getString("name");
        type = jobj.getString("type");
    }
}
