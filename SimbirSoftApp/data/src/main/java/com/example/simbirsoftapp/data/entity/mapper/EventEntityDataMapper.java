package com.example.simbirsoftapp.data.entity.mapper;

import com.example.simbirsoftapp.data.entity.EventEntity;
import com.example.simbirsoftapp.data.entity.UserEntity;
import com.example.simbirsoftapp.domain.Event;
import com.example.simbirsoftapp.domain.User;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class EventEntityDataMapper {

    @Inject
    EventEntityDataMapper(){}

    public Event transform(EventEntity eventEntity) {
        Event event = new Event();
        event.setEventAddress(eventEntity.getEventAddress());
        event.setEventCompany(eventEntity.getEventCompany());
        event.setEventDate(eventEntity.getEventDate());
        event.setEventDescription(eventEntity.getEventDescription());
        event.setEventName(eventEntity.getEventName());
        List<UserEntity> userEntityList = eventEntity.getEventPerson();
        List<User> userList = new ArrayList<>();
        for (UserEntity ue:userEntityList) {
            userList.add(new User(ue.getName(),ue.getSurname(),ue.getLogo(),ue.getRoundedLogo()));
        }
        event.setEventPerson(userList);
        event.setEventPhoto(eventEntity.getEventPhoto());
        event.setId(eventEntity.getId());
        event.setOrganisationSite(eventEntity.getOrganisationSite());
        event.setOrganisationTelephone(eventEntity.getOrganisationTelephone());
        return event;
    }
}
