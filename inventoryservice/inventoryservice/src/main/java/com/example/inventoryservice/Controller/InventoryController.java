package com.example.inventoryservice.Controller;

import com.example.inventoryservice.Response.EventResponse;
import com.example.inventoryservice.Response.VenueResponse;
import com.example.inventoryservice.Service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class InventoryController {

    private final InventoryService invenServ;

    @Autowired
    public InventoryController(InventoryService invenServ) {
        this.invenServ = invenServ;
    }

    @GetMapping
    public @ResponseBody List<EventResponse> inventoryGetAllEvents(){
        List<EventResponse>l= invenServ.getAllEvents();
        return l;
    }

    @GetMapping("/inventory/venue/{venueId}")
    public @ResponseBody VenueResponse inventoryByVenueId(@PathVariable("venueId") Long venueId) {
        return invenServ.getVenueInformation(venueId);
    }

    @GetMapping("/inventory/event/{eventId}")
    public @ResponseBody EventResponse inventoryForEvent(@PathVariable("eventId") Long eventId) {
        return invenServ.getEventInventory(eventId);
    }

    @PutMapping("/inventory/event/{eventId}/capacity/{capacity}")
    public ResponseEntity<Void> updateEventCapacity(@PathVariable("eventId") Long eventId,
                                                    @PathVariable("capacity") Long ticketsBooked) {
        invenServ.updateEventCapacity(eventId, ticketsBooked);
        return ResponseEntity.ok().build();
    }

}
