package com.example.firsttry.serialize;

        import android.content.Context;
        import android.util.Log;

        import org.json.JSONArray;
        import org.json.JSONException;
        import org.json.JSONObject;

        import java.io.FileInputStream;
        import java.io.FileNotFoundException;
        import java.io.FileOutputStream;
        import java.io.FileWriter;
        import java.io.IOException;
        import java.io.OutputStreamWriter;
        import java.io.Writer;
        import java.nio.charset.StandardCharsets;
        import java.util.ArrayList;

public class FileHelper<E extends Jsonable> {
    //FileHelper kann nur von Klassen, die Jasonable implemtiert haben, gentuzt werden
    // damit ich Funktionen von e.interface aufrufen kann siehe z. 42

    public static final String FILENAME = "listinfo.json";
    private final Context context;

    //mit Nico:
    private final Class<E> cls;


    public FileHelper(Context context, Class<E> cls) {
        this.context = context;
        this.cls = cls;
    }

    public void writeData(ArrayList<E> items) {


        //fkt bekommt arraylist mit elementen vom typ e übergeben (objekte dieser klasse haben flt json und fromJson)
        //übersetzt mithilfe von json()
        //erstellt jsonarray welches in die datei listinfo.json reingeschrieben wird


        FileOutputStream fos = null;
        try {
            fos = context.openFileOutput(FILENAME, Context.MODE_PRIVATE);
        } catch(FileNotFoundException e) {
            e.printStackTrace();
            return;
        }
        try (Writer fw = new OutputStreamWriter(fos)){
            JSONArray jsonArray = new JSONArray();
            for(E e : items)
                jsonArray.put(e.json());
            JSONObject json = new JSONObject();
            json.put("todos", jsonArray);
            fw.write(json.toString());
        } catch(IOException | JSONException e) {
            e.printStackTrace();
            return;
        }
    }

    public ArrayList<E> readData() {

        ArrayList<E> items = new ArrayList<>();
        try {
            FileInputStream fis = context.openFileInput(FILENAME);
            int size = (int) fis.getChannel().size();
            byte[] bytes = new byte[size];
            fis.read(bytes, 0, size);
            String content = new String(bytes, StandardCharsets.UTF_8);
            Log.d("FileHelper", "test" + content);
            JSONObject json = new JSONObject(content);
            JSONArray array = json.getJSONArray("todos");
            for (int i = 0; i < array.length(); i++) {
                JSONObject todojson = (JSONObject) array.get(i);
                E e = (E) cls.newInstance().fromJson(todojson);
                items.add(e);
            }
       } catch(IOException | JSONException e) {
            e.printStackTrace();
            return null;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return items;
    }

}