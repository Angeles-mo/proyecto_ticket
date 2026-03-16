package com.ticketworld.Ticketworld.serviceImpl;

import com.ticketworld.Ticketworld.dto.ErrorDTO;
import com.ticketworld.Ticketworld.dto.TicketTypeDTO;
import com.ticketworld.Ticketworld.entity.Account;
import com.ticketworld.Ticketworld.entity.TicketType;
import com.ticketworld.Ticketworld.repositories.TicketTypeRepository;
import com.ticketworld.Ticketworld.services.TicketTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketTypeServiceImpl implements TicketTypeService {

    @Autowired
    private TicketTypeRepository ticketTypeRepository;

    @Override
    public ResponseEntity<?> getAll() {
        List<TicketType> ticketTypes = ticketTypeRepository.findAll();
        return new ResponseEntity<>(ticketTypes, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> createTicketType(Account loggedAccount, TicketTypeDTO ticketTypeDTO) {
        //Comprobamos si tiene Role de ADMIN y si no lo tiene, da error
        if (!isAdmin(loggedAccount)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(new ErrorDTO("You don't have permission to create a ticket type"));
        }
        TicketType ticketType = new TicketType();
        mapDTOToEntity(ticketTypeDTO, ticketType);
        return ResponseEntity.status(HttpStatus.CREATED).body(ticketTypeRepository.save(ticketType));
    }

    @Override
    public ResponseEntity<?> getById(Long id) {
        return ticketTypeRepository.findById(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<?> deleteTicketType(Account loggedAccount, Long id) {
        //Comprobamos si tiene Role de ADMIN y si no lo tiene, da error
        if (!isAdmin(loggedAccount)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        if (ticketTypeRepository.existsById(id)){
            ticketTypeRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<?> putTicketType(Account loggedAccount, Long id, TicketTypeDTO ticketTypeDTO) {
        //Comprobamos si tiene Role de ADMIN y si no lo tiene, da error
        if (!isAdmin(loggedAccount)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(new ErrorDTO("You don't have permission to modify"));
        }
        //Buscamos TicketType por su ID. Si el .map tiene el TicketType ejecutamos el código dentro de las llaves.
        //Si no existe, se salta el bloque
        //Al TicketType lo hemos llamado existingTicketType
        return ticketTypeRepository.findById(id).map(existingTicketType -> {
            //Cojo el metodo que paso los datos DTO a entidad y lo metemos al objeto
            mapDTOToEntity(ticketTypeDTO, existingTicketType);
            //Guardamos los cambios
            ticketTypeRepository.save(existingTicketType);
            return ResponseEntity.ok(existingTicketType);
        //Si no existe le devolvemos un notFound
        }).orElse(ResponseEntity.notFound().build());
    }

    //Verificamos si la cuenta tiene rol ADMIN
    private boolean isAdmin(Account account) {
        return account != null && "ADMIN".equalsIgnoreCase(account.getRole().toString());
    }

    //Creamos un metodo para pasar los datos del DTO a entidad
    private void mapDTOToEntity(TicketTypeDTO ticketTypeDTO, TicketType ticketType){
        ticketType.setEvent(ticketTypeDTO.getEvent());
        ticketType.setName(ticketTypeDTO.getName());
        ticketType.setPrice(ticketTypeDTO.getPrice());
        ticketType.setCapacity(ticketTypeDTO.getCapacity());
        ticketType.setSingleTickets(ticketTypeDTO.getSingleTickets());
    }
}
