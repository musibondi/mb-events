package org.musibondi.events.dal.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.musibondi.events.SpringBootWebApplication;
import org.musibondi.events.dal.EventDAL;
import org.musibondi.events.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@DataMongoTest
@SpringBootTest(classes = {EventDALImpl.class, EmbeddedMongoAutoConfiguration.class})
public class EventDALImplTest {

    @Autowired
    private EventDAL eventDAL;


    @Test
    public void shouldAddEvent(){

        EventData eventData = new EventData();
        eventData.setStatus(EventStatus.PLANNING);
        eventData.setType(EventType.PUBLIC);
        eventData.setEventId("idGeneratedMagicly");
        eventData.setEndDate(LocalDateTime.now().plusMonths(2));
        eventData.setStartDate(LocalDateTime.now().plusMonths(1));
        eventData.setTags(new ArrayList<>());

        User attendeeUser1 = new User();
        attendeeUser1.setUserId("attendeeUser1");

        User attendeeUser2 = new User();
        attendeeUser2.setUserId("attendeeUser2");

        User attendeeUser3 = new User();
        attendeeUser3.setUserId("attendeeUser3");

        ArrayList attendeeList = new ArrayList<User>();
        attendeeList.add(attendeeUser1);
        attendeeList.add(attendeeUser2);
        attendeeList.add(attendeeUser3);

        eventData.setAttendeeList(attendeeList);

        User owner = new User();
        owner.setUserId("owner1");

        List<User> ownerList = new ArrayList<>();
        ownerList.add(owner);
        eventData.setOwnerList(ownerList);

        eventData.setPlaylistId("1223344556677899");

        Assert.assertTrue(eventDAL.createEvent(eventData));

        EventData returnedEventData = eventDAL.getEventDataById("idGeneratedMagicly");

        Assert.assertNotNull(returnedEventData);

        Assert.assertEquals(eventData.getEndDate(), returnedEventData.getEndDate());
        Assert.assertEquals(eventData.getStartDate(), returnedEventData.getStartDate());
        Assert.assertEquals(eventData.getEventId(), returnedEventData.getEventId());
        Assert.assertEquals(eventData.getPlaylistId(), returnedEventData.getPlaylistId());
        Assert.assertEquals(eventData.getStatus(), returnedEventData.getStatus());
        Assert.assertEquals(eventData.getTags(), returnedEventData.getTags());
        Assert.assertEquals(eventData.getType(), returnedEventData.getType());
        Assert.assertEquals(eventData.getAttendeeList().size(), returnedEventData.getAttendeeList().size());
        Assert.assertEquals(eventData.getOwnerList().size(), returnedEventData.getOwnerList().size());

    }

    @Test
    public void shouldAddOwnerToEvent(){

        EventData eventData = new EventData();
        eventData.setStatus(EventStatus.PLANNING);
        eventData.setType(EventType.PUBLIC);
        eventData.setEventId("id1234");
        eventData.setEndDate(LocalDateTime.now().plusMonths(2));
        eventData.setStartDate(LocalDateTime.now().plusMonths(1));
        eventData.setTags(new ArrayList<>());

        User attendeeUser1 = new User();
        attendeeUser1.setUserId("attendeeUser1");

        User attendeeUser2 = new User();
        attendeeUser2.setUserId("attendeeUser2");

        User attendeeUser3 = new User();
        attendeeUser3.setUserId("attendeeUser3");

        ArrayList attendeeList = new ArrayList<User>();
        attendeeList.add(attendeeUser1);
        attendeeList.add(attendeeUser2);
        attendeeList.add(attendeeUser3);

        eventData.setAttendeeList(attendeeList);

        User owner = new User();
        owner.setUserId("owner1");

        List<User> ownerList = new ArrayList<>();
        ownerList.add(owner);
        eventData.setOwnerList(ownerList);

        eventData.setPlaylistId("1223344556677899");

        Assert.assertTrue(eventDAL.createEvent(eventData));

        EventData returnedEventData = eventDAL.getEventDataById("id1234");


        Assert.assertEquals(eventData.getEndDate(), returnedEventData.getEndDate());
        Assert.assertEquals(eventData.getStartDate(), returnedEventData.getStartDate());
        Assert.assertEquals(eventData.getEventId(), returnedEventData.getEventId());
        Assert.assertEquals(eventData.getPlaylistId(), returnedEventData.getPlaylistId());
        Assert.assertEquals(eventData.getStatus(), returnedEventData.getStatus());
        Assert.assertEquals(eventData.getTags(), returnedEventData.getTags());
        Assert.assertEquals(eventData.getType(), returnedEventData.getType());
        Assert.assertEquals(eventData.getAttendeeList().size(), returnedEventData.getAttendeeList().size());
        Assert.assertEquals(eventData.getOwnerList().size(), returnedEventData.getOwnerList().size());

        Assert.assertTrue(eventDAL.addUserToEvent("newUserOwner", "id1234", UserType.OWNER));

        List<EventData> returnedDataList = eventDAL.getEventDataOwnerByUserId("newUserOwner");

        Assert.assertFalse(returnedDataList.isEmpty());
        returnedEventData = returnedDataList.get(0);

        Assert.assertEquals(eventData.getEndDate(), returnedEventData.getEndDate());
        Assert.assertEquals(eventData.getStartDate(), returnedEventData.getStartDate());
        Assert.assertEquals(eventData.getEventId(), returnedEventData.getEventId());
        Assert.assertEquals(eventData.getPlaylistId(), returnedEventData.getPlaylistId());
        Assert.assertEquals(eventData.getStatus(), returnedEventData.getStatus());
        Assert.assertEquals(eventData.getTags(), returnedEventData.getTags());
        Assert.assertEquals(eventData.getType(), returnedEventData.getType());
        Assert.assertEquals(eventData.getAttendeeList().size(), returnedEventData.getAttendeeList().size());
        Assert.assertEquals(eventData.getOwnerList().size() +1, returnedEventData.getOwnerList().size());

        Boolean isNewOwnerPresent = false;
        for(User user : eventData.getOwnerList()){
            if(user.getUserId().equalsIgnoreCase("newUserOwner")){
                isNewOwnerPresent = true;
                break;
            }
        }

        Assert.assertTrue(isNewOwnerPresent);
    }

    public EventDAL getEventDAL() {
        return eventDAL;
    }

    public void setEventDAL(EventDAL eventDAL) {
        this.eventDAL = eventDAL;
    }
}
