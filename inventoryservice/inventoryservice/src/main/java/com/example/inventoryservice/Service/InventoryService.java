package com.example.inventoryservice.Service;

import com.example.inventoryservice.Entity.Event;
import com.example.inventoryservice.Entity.Venue;
import com.example.inventoryservice.Repository.EventRepository;
import com.example.inventoryservice.Repository.VenueRepository;
import com.example.inventoryservice.Response.EventResponse;
import com.example.inventoryservice.Response.VenueResponse;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class InventoryService {

    private final EventRepository eventRepo;
    private final VenueRepository venueRepo;

    @Autowired
    public InventoryService(EventRepository eventRepo, VenueRepository venuerepo) {
        this.eventRepo = eventRepo;
        this.venueRepo = venuerepo;
    }

    public List<EventResponse> getAllEvents() {
        final List<Event> events = eventRepo.findAll();

        return events.stream().map(event -> EventResponse.builder()
                .event(event.getName())
                .capacity(event.getLeftCapacity())
                .venue(event.getVenue())
                .build()).collect(Collectors.toList());
    }

    public VenueResponse getVenueInformation(final Long venueId) {
        final Venue venue = venueRepo.findById(venueId).orElse(null);

        return VenueResponse.builder()
                .venueId(venue.getId())
                .venueName(venue.getName())
                .totalCapacity(venue.getTotalCapacity())
                .build();
    }

    public EventResponse getEventInventory(final Long eventId) {
        final Event event = eventRepo.findById(eventId).orElse(null);

        return EventResponse.builder()
                .event(event.getName())
                .capacity(event.getLeftCapacity())
                .venue(event.getVenue())
                .ticketPrice(event.getTicketPrice())
                .eventId(event.getId())
                .build();
    }

    public void updateEventCapacity(final Long eventId, final Long ticketsBooked) {
        final Event event = eventRepo.findById(eventId).orElse(null);
        event.setLeftCapacity(event.getLeftCapacity() - ticketsBooked);
        eventRepo.saveAndFlush(event);
        log.info("Updated event capacity for event id: {} with tickets booked: {}", eventId, ticketsBooked);
    }

}
