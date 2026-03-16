package com.ticketworld.Ticketworld.serviceImpl;

import com.ticketworld.Ticketworld.dto.ErrorDTO;
import com.ticketworld.Ticketworld.dto.SingleTicketDTO;
import com.ticketworld.Ticketworld.entity.Account;
import com.ticketworld.Ticketworld.entity.SingleTicket;
import com.ticketworld.Ticketworld.repositories.SingleTicketRepository;
import com.ticketworld.Ticketworld.services.SingleTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SingleTicketServiceImpl implements SingleTicketService {

    @Autowired
    private SingleTicketRepository singleTicketRepository;

    @Override
    public ResponseEntity<?> getAll() {
        List<SingleTicket> singleTickets = singleTicketRepository.findAll();
        return new ResponseEntity<>(singleTickets, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> createSingleTicket(Account loggedAccount, SingleTicketDTO singleTicketDTO) {
        //Comprobamos si tiene Role de ADMIN y si no lo tiene, da error
        if (!isAdmin(loggedAccount)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(new ErrorDTO("You don't have permission to create"));
        }
        SingleTicket singleTicket = new SingleTicket();
        mapDTOToEntity(singleTicketDTO, singleTicket);
        return ResponseEntity.status(HttpStatus.CREATED).body(singleTicketRepository.save(singleTicket));
    }

    @Override
    public ResponseEntity<?> getById(Long id) {
        return singleTicketRepository.findById(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<?> deleteSingleTicket(Account loggedAccount, Long id) {
        //Comprobamos si tiene Role de ADMIN y si no lo tiene, da error
        if (!isAdmin(loggedAccount)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        if (singleTicketRepository.existsById(id)){
            singleTicketRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<?> putSingleTicket(Account loggedAccount, Long id, SingleTicketDTO singleTicketDTO) {
        //Comprobamos si tiene Role de ADMIN y si no lo tiene, da error
        if (!isAdmin(loggedAccount)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(new ErrorDTO("You don't have permission to modify"));
        }
        //Buscamos SingleTicket por su ID. Si el .map tiene el SingleTicket ejecutamos el código dentro de las llaves.
        //Si no existe, se salta el bloque
        //Al SingleTicket lo hemos llamado existingSingleTicket
        return singleTicketRepository.findById(id).map(existingSingleTicket -> {
            //Cojo el metodo que paso los datos DTO a entidad y lo metemos al objeto
            mapDTOToEntity(singleTicketDTO, existingSingleTicket);
            //Guardamos los cambios
            singleTicketRepository.save(existingSingleTicket);
            return ResponseEntity.ok(existingSingleTicket);
        //Si no existe le devolvemos un notFound
        }).orElse(ResponseEntity.notFound().build());
    }

    //Verificamos si la cuenta tiene rol ADMIN
    private boolean isAdmin(Account account) {
        return account != null && "ADMIN".equalsIgnoreCase(account.getRole().toString());
    }

    //Creamos un metodo para pasar los datos del DTO a entidad
    private void mapDTOToEntity(SingleTicketDTO singleTicketDTO, SingleTicket singleTicket){
        singleTicket.setTicketType(singleTicketDTO.getTicketType());
        singleTicket.setRow(singleTicketDTO.getRow());
        singleTicket.setSeatNumber(singleTicketDTO.getSeatNumber());
        singleTicket.setTicketCode(singleTicketDTO.getTicketCode());
        singleTicket.setTicketPrice(singleTicketDTO.getTicketPrice());
    }
}
