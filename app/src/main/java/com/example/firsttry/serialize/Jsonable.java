package com.example.firsttry.serialize;

import org.json.JSONException;
import org.json.JSONObject;

public interface Jsonable {
    public JSONObject json() throws JSONException;
    //wandelt objekt in Jsonobjekt um
    public Jsonable fromJson(JSONObject json) throws JSONException;
    //nimmt json objekt entgegen und füllt eigenes Objekt mit jsonobjekt-infos
}

//oben genannte Methoden müssen implemtiert wenn eine Klasse dieses Interface durch 'implements' aufruft