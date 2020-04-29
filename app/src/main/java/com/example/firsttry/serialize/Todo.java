package com.example.firsttry.serialize;

        import org.json.JSONException;
        import org.json.JSONObject;

public final class Todo implements Jsonable {

    public String description;
    public String due;
    public boolean done = false;

    @Override
    //"lesen"-Funktion:
    public JSONObject json() throws JSONException {
        JSONObject result = new JSONObject();
        result.put("description", description);
        result.put("due", due);
        result.put("done", done);
        return result;
    }

    @Override
    //schreiben-Funktion:
    public Todo fromJson(JSONObject json) throws JSONException {
        this.description = json.getString(description);
        this.due = json.getString("due");
        this.done = json.getBoolean("done");
        return this;
    }

}