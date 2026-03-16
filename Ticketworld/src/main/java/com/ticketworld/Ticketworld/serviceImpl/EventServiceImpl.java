package com.ticketworld.Ticketworld.serviceImpl;

import com.ticketworld.Ticketworld.dto.ErrorDTO;
import com.ticketworld.Ticketworld.dto.EventDTO;
import com.ticketworld.Ticketworld.entity.Account;
import com.ticketworld.Ticketworld.entity.Event;
import com.ticketworld.Ticketworld.repositories.EventRepository;
import com.ticketworld.Ticketworld.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;

    @Override
    public ResponseEntity<?> createEvent(Account loggedAccount, EventDTO eventDTO) {
        //Comprobamos si tiene Role de ADMIN y si no lo tiene, da error
        if (!isAdmin(loggedAccount)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(new ErrorDTO("You don't have permission to create an event"));
        }
        Event event = new Event();
        mapDTOToEntity(eventDTO, event);
        return ResponseEntity.status(HttpStatus.CREATED).body(eventRepository.save(event));
    }

    @Override
    public ResponseEntity<?> getAll() {
        List<Event> events = eventRepository.findAll();
        return ResponseEntity.ok(events);
    }

    @Override
    public ResponseEntity<?> getById(Long id) {
        return eventRepository.findById(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<?> deleteEvent(Account loggedAccount, Long id) {
        //Comprobamos si tiene Role de ADMIN y si no lo tiene, da error
        if (!isAdmin(loggedAccount)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        if (eventRepository.existsById(id)){
            eventRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<?> putEvent(Account loggedAccount, Long id, EventDTO eventDTO) {
        //Comprobamos si tiene Role de ADMIN y si no lo tiene, da error
        if (!isAdmin(loggedAccount)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(new ErrorDTO("You don't have permission to modify"));
        }
        //Buscamos Event por su ID. Si el .map tiene el Event ejecutamos el código dentro de las llaves.
        //Si no existe, se salta el bloque
        //Al Event lo hemos llamado existingEvent
        return eventRepository.findById(id).map(existingEvent -> {
            //Cojo el metodo que paso los datos DTO a entidad y lo metemos al objeto
            mapDTOToEntity(eventDTO, existingEvent);
            //Guardamos los cambios
            eventRepository.save(existingEvent);
            return ResponseEntity.ok(existingEvent);
        //Si no existe le devolvemos un notFound
        }).orElse(ResponseEntity.notFound().build());
    }

    //Verificamos si la cuenta tiene rol ADMIN
    private boolean isAdmin(Account account) {
        return account != null && "ADMIN".equalsIgnoreCase(account.getRole().toString());
    }

    //Creamos un metodo para pasar los datos del DTO a entidad
    private void mapDTOToEntity(EventDTO eventDTO, Event event){
        event.setPlace(eventDTO.getPlace());
        event.setTitle(eventDTO.getTitle());
        event.setDescription(event.getDescription());
        event.setDateStartTime(eventDTO.getDateStartTime());
        event.setDateEndTime(eventDTO.getDateEndTime());
        event.setStatus(eventDTO.getStatus());
        event.setArtists(eventDTO.getArtists());
        event.setTicketTypes(eventDTO.getTicketTypes());
    }
}