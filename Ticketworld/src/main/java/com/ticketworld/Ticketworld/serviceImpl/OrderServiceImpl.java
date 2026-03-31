package com.ticketworld.Ticketworld.serviceImpl;

import com.ticketworld.Ticketworld.dto.ErrorDTO;
import com.ticketworld.Ticketworld.dto.OrderDTO;
import com.ticketworld.Ticketworld.entity.Account;
import com.ticketworld.Ticketworld.entity.Order;
import com.ticketworld.Ticketworld.repositories.OrderRepository;
import com.ticketworld.Ticketworld.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public ResponseEntity<?> getAll(Account loggedAccount) {
        if (loggedAccount.getRole().name().equals("ADMIN")) {
            return ResponseEntity.ok(orderRepository.findAll());
        } else if (loggedAccount.getRole().name().equals("CUSTOMER")) {
            return ResponseEntity.ok(orderRepository.findByUserId(loggedAccount.getUser().getId()));
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(new ErrorDTO("You don't have permission"));
    }

    @Override
    public ResponseEntity<?> createOrder(Account loggedAccount, OrderDTO orderDTO) {
        if (!loggedAccount.getRole().name().equals("CUSTOMER")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(new ErrorDTO("Only customers can create orders"));
        }
        Order order = new Order();
        mapDTOToEntity(orderDTO, order);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderRepository.save(order));
    }

    @Override
    public ResponseEntity<?> getById(Long id) {
        return orderRepository.findById(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<?> deleteOrder(Account loggedAccount, Long id) {
        //Comprobamos si tiene Role de ADMIN y si no lo tiene, da error
        if (!isAdmin(loggedAccount)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        if (orderRepository.existsById(id)){
            orderRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<?> putOrder(Account loggedAccount, Long id, OrderDTO orderDTO) {
        //Comprobamos si tiene Role de ADMIN y si no lo tiene, da error
        if (!isAdmin(loggedAccount)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(new ErrorDTO("You don't have permission to modify"));
        }
        //Buscamos Order por su ID. Si el .map tiene el Order ejecutamos el código dentro de las llaves.
        //Si no existe, se salta el bloque
        //Al Order lo hemos llamado existingOrder
        return orderRepository.findById(id).map(existingOrder -> {
            //Cojo el metodo que paso los datos DTO a entidad y lo metemos al objeto
            mapDTOToEntity(orderDTO, existingOrder);
            //Guardamos los cambios
            orderRepository.save(existingOrder);
            return ResponseEntity.ok(existingOrder);
        //Si no existe le devolvemos un notFound
        }).orElse(ResponseEntity.notFound().build());
    }

    //Verificamos si la cuenta tiene rol ADMIN
    private boolean isAdmin(Account account) {
        return account != null && "ADMIN".equalsIgnoreCase(account.getRole().toString());
    }

    //Creamos un metodo para pasar los datos del DTO a entidad
    private void mapDTOToEntity(OrderDTO orderDTO, Order order){
        order.setUser(orderDTO.getUser());
        order.setPurchaseDate(orderDTO.getPurchaseDate());
        order.setTotal(orderDTO.getTotal());
        order.setPurchasestate(orderDTO.getPurchasestate());
        order.setPayment(orderDTO.getPayment());
    }
}
