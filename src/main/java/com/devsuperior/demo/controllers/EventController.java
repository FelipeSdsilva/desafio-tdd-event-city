package com.devsuperior.demo.controllers;

import com.devsuperior.demo.dto.EventDTO;
import com.devsuperior.demo.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

@RestController
@RequestMapping(value = "/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping
    public ResponseEntity<List<EventDTO>> getAllEvents() {
        return ResponseEntity.ok(eventService.findAllEvents());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<EventDTO> getEventPerId(@PathVariable Long id) {
        return ResponseEntity.ok(eventService.findEventPerId(id));
    }

    @PostMapping
    public ResponseEntity<EventDTO> postNewEvent(@RequestBody EventDTO eventDTO) {
        EventDTO event = eventService.insertNewEvent(eventDTO);
        URI uri = fromCurrentRequest().path("/{id}").buildAndExpand(event.getId()).toUri();
        return ResponseEntity.created(uri).body(event);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<EventDTO> putEventPerId(@PathVariable Long id, @RequestBody EventDTO eventDTO) {
        return ResponseEntity.ok(eventService.updateEventPerId(id, eventDTO));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteEventPerId(@PathVariable Long id) {
        eventService.deleteEventPerId(id);
        return ResponseEntity.noContent().build();
    }
}
