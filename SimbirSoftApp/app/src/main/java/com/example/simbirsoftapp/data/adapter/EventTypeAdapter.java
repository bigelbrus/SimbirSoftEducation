package com.example.simbirsoftapp.data.adapter;

import com.example.simbirsoftapp.data.model.Event;
import com.example.simbirsoftapp.data.model.User;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EventTypeAdapter extends TypeAdapter<List<Event>> {
    @Override
    public void write(JsonWriter out, List<Event> value) throws IOException {
        //will be implemented later
    }

    @Override
    public List<Event>  read(JsonReader in) throws IOException {
        List<Event> result = new ArrayList<>();
        in.beginArray();
        while (in.hasNext()) {
            Event event = new Event();
            in.beginObject();
            while (in.hasNext()) {
                switch (in.nextName()) {
                    case "id":
                        event.setId(in.nextInt());
                        break;
                    case "eventName":
                        event.setEventName(in.nextString());
                        break;
                    case "eventDate":
                        event.setEventDate(in.nextString());
                        break;
                    case "eventCompany":
                        event.setEventCompany(in.nextString());
                        break;
                    case "eventAddress":
                        event.setEventAddress(in.nextString());
                        break;
                    case "organisationTelephone":
                        in.beginArray();
                        while (in.hasNext()) {
                            event.addOrganisationTelephone(in.nextString());
                        }
                        in.endArray();
                        break;
                    case "eventPhoto":
                        in.beginArray();
                        while (in.hasNext()) {
                            event.addEventPhoto(in.nextString());
                        }
                        in.endArray();
                        break;
                    case "eventDescription":
                        event.setEventDescription(in.nextString());
                        break;
                    case "organisationSite":
                        event.setOrganisationSite(in.nextString());
                        break;
                    case "eventPerson":
                        in.beginArray();
                        while (in.hasNext()) {
                            User person = new User();
                            in.beginObject();
                            in.nextName();
                            person.setName(in.nextString());
                            in.nextName();
                            person.setSurname(in.nextString());
                            in.nextName();
                            person.setRoundedLogo(in.nextString());
                            in.endObject();
                            event.addEventPerson(person);
                        }
                        in.endArray();
                        break;
                    default:
                        break;
                }
            }
            in.endObject();
            result.add(event);
        }
        in.endArray();
        return result;
    }
}
