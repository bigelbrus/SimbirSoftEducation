package com.example.simbirsoftapp.data.adapter;

import com.example.simbirsoftapp.data.model.Category;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CategoryTypeAdapter extends TypeAdapter<List<Category>> {
    @Override
    public void write(JsonWriter out, List<Category> value) throws IOException {
        //will be implemented later
    }

    @Override
    public List<Category> read(JsonReader in) throws IOException {
        List<Category> result = new ArrayList<>();
        in.beginArray();
        while (in.hasNext()) {
            in.beginObject();
            Category category = new Category();
            while (in.hasNext()) {
                switch (in.nextName()) {
                    case "id":
                        category.setId(in.nextInt());
                        break;
                    case "logo":
                        category.setLogo(in.nextString());
                        break;
                    case "text":
                        category.setText(in.nextString());
                        break;
                    default:
                        break;
                }
            }
            in.endObject();
            result.add(category);
        }
        in.endArray();
        return result;
    }

}
