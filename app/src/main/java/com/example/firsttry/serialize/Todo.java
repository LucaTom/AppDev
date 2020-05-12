package com.example.firsttry.serialize;

        import android.os.Parcel;
        import android.os.Parcelable;

        import org.json.JSONException;
        import org.json.JSONObject;

public final class Todo implements Jsonable, Parcelable {

    public String description;
    public String due;
    public boolean done;

    public Todo() {

    }

    public Todo(Parcel parcel) {
        this.description = parcel.readString();
        this.due = parcel.readString();
        this.done = parcel.readInt() != 0;
    }

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
        this.description = json.getString("description");
        this.due = json.getString("due");
        this.done = json.getBoolean("done");
        return this;
    }



    public static final Creator<Todo> CREATOR = new Creator<Todo>() {
        @Override
        public Todo createFromParcel(Parcel in) { return new Todo(in); }

        @Override
        public Todo[] newArray(int size) { return new Todo[size]; }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.description);
        dest.writeString(this.due);
        dest.writeInt(this.done ? 1 : 0);
    }
}