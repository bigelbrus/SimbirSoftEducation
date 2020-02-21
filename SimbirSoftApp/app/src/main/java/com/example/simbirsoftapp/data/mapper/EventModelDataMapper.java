package com.example.simbirsoftapp.data.mapper;

import com.example.simbirsoftapp.domain.Event;
import com.example.simbirsoftapp.data.model.EventModel;
import com.example.simbirsoftapp.data.model.UserModel;
import com.example.simbirsoftapp.domain.User;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class EventModelDataMapper {

    @Inject
    EventModelDataMapper(){}

    public EventModel transform(Event event) {
        EventModel eventModel = new EventModel();
        eventModel.setOrganisationSite(event.getOrganisationSite());
        eventModel.setEventAddress(event.getEventAddress());
        eventModel.setEventCompany(event.getEventCompany());
        eventModel.setEventDate(event.getEventDate());
        eventModel.setEventDescription(event.getEventDescription());
        eventModel.setEventName(event.getEventName());
        List<UserModel>userModelList = new ArrayList<>();
        for (User user:event.getEventPerson()) {
            userModelList.add(new UserModel(user.getName(),user.getSurname(),user.getLogo(),user.getRoundedLogo()));
        }
        eventModel.setEventPerson(userModelList);
        eventModel.setId(event.getId());
        eventModel.setOrganisationTelephone(event.getOrganisationTelephone());
        eventModel.setEventPhoto(event.getEventPhoto());
        return eventModel;
    }
}
