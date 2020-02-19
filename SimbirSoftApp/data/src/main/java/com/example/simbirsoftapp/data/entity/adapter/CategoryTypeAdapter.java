package com.example.simbirsoftapp.data.entity.adapter;

import com.example.simbirsoftapp.data.entity.CategoryEntity;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CategoryTypeAdapter extends TypeAdapter<List<CategoryEntity>> {
    @Override
    public void write(JsonWriter out, List<CategoryEntity> value) throws IOException {
        //will be implemented later
    }

    @Override
    public List<CategoryEntity> read(JsonReader in) throws IOException {
        List<CategoryEntity> result = new ArrayList<>();
        in.beginArray();
        while (in.hasNext()) {
            in.beginObject();
            CategoryEntity category = new CategoryEntity();
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
