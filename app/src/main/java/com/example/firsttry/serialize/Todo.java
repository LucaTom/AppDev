package com.example.firsttry.serialize;

        import org.json.JSONException;
        import org.json.JSONObject;

public final class Todo implements Jsonable {

    public String description;
    public Day due;
    public boolean done = false;

    @Override
    //"lesen"-Funktion:
    public JSONObject json() throws JSONException {
        JSONObject result = new JSONObject();
        result.put("description", description);
        result.put("due", due.getDay());
        result.put("done", done);
        return result;
    }

    @Override
    //schreiben-Funktion:
    public Todo fromJson(JSONObject json) throws JSONException {
        this.description = json.getString(description);
        this.due = Day.valueOf(json.getString("due"));
        this.done = json.getBoolean("done");
        return this;
    }

    public static enum Day {
        MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY;

        public String getDay() { return this.name(); }
    }
}