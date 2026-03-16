package com.ticketworld.Ticketworld.serviceImpl;

import com.ticketworld.Ticketworld.dto.ErrorDTO;
import com.ticketworld.Ticketworld.dto.PlaceDTO;
import com.ticketworld.Ticketworld.entity.Account;
import com.ticketworld.Ticketworld.entity.Place;
import com.ticketworld.Ticketworld.repositories.PlaceRepository;
import com.ticketworld.Ticketworld.services.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaceServiceImpl implements PlaceService {

    @Autowired
    private PlaceRepository placeRepository;

    @Override
    public ResponseEntity<?> getAll() {
        List<Place> places = placeRepository.findAll();
        return ResponseEntity.ok(places);
    }

    @Override
    public ResponseEntity<?> createPlace(Account loggedAccount, PlaceDTO placeDTO) {
        //Comprobamos si tiene Role de ADMIN y si no lo tiene, da error
        if (!isAdmin(loggedAccount)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(new ErrorDTO("You don't have permission to create a place"));
        }
        Place place = new Place();
        mapDTOToEntity(placeDTO, place);
        return ResponseEntity.status(HttpStatus.CREATED).body(placeRepository.save(place));
    }

    @Override
    public ResponseEntity<?> getById(Long id) {
        return placeRepository.findById(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<?> deletePlace(Account loggedAccount, Long id) {
        //Comprobamos si tiene Role de ADMIN y si no lo tiene, da error
        if (!isAdmin(loggedAccount)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        if (placeRepository.existsById(id)){
            placeRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<?> putPlace(Account loggedAccount, Long id, PlaceDTO placeDTO) {
        //Comprobamos si tiene Role de ADMIN y si no lo tiene, da error
        if (!isAdmin(loggedAccount)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(new ErrorDTO("You don't have permission to modify"));
        }
        //Buscamos Place por su ID. Si el .map tiene el PLace ejecutamos el código dentro de las llaves.
        //Si no existe, se salta el bloque
        //Al Place lo hemos llamado existingPlace
        return placeRepository.findById(id).map(existingPlace -> {
            //Cojo el metodo que paso los datos DTO a entidad y lo metemos al objeto
            mapDTOToEntity(placeDTO, existingPlace);
            //Guardamos los cambios
            placeRepository.save(existingPlace);
            return ResponseEntity.ok(existingPlace);
        //Si no existe le devolvemos un notFound
        }).orElse(ResponseEntity.notFound().build());
    }

    //Verificamos si la cuenta tiene rol ADMIN
    private boolean isAdmin(Account account) {
        return account != null && "ADMIN".equalsIgnoreCase(account.getRole().toString());
    }

    //Creamos un metodo para pasar los datos del DTO a entidad
    private void mapDTOToEntity(PlaceDTO placeDTO, Place place){
        place.setName(placeDTO.getName());
        place.setAddress(placeDTO.getAddress());
        place.setCity(placeDTO.getCity());
        place.setProvince(placeDTO.getProvince());
        place.setCountry(placeDTO.getCountry());
        place.setTotalCapacity(placeDTO.getTotalCapacity());
        place.setSeatMap(placeDTO.getSeatMap());
        place.setEvents(placeDTO.getEvents());
    }
}