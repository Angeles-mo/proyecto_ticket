package com.ticketworld.Ticketworld.serviceImpl;

import com.ticketworld.Ticketworld.dto.ErrorDTO;
import com.ticketworld.Ticketworld.dto.PaymentDTO;
import com.ticketworld.Ticketworld.entity.Account;
import com.ticketworld.Ticketworld.entity.Payment;
import com.ticketworld.Ticketworld.repositories.PaymentRepository;
import com.ticketworld.Ticketworld.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public ResponseEntity<?> getAll(Account loggedAccount) {
        if (loggedAccount.getRole().name().equals("ADMIN")) {
            return ResponseEntity.ok(paymentRepository.findAll());
        } else if (loggedAccount.getRole().name().equals("CUSTOMER")) {
            return ResponseEntity.ok(paymentRepository.findByOrderUserId(loggedAccount.getUser().getId()));
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(new ErrorDTO("You don't have permission"));
    }

    @Override
    public ResponseEntity<?> createPayment(Account loggedAccount, PaymentDTO paymentDTO) {
        if (!loggedAccount.getRole().name().equals("CUSTOMER")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(new ErrorDTO("Only customers can create payments"));
        }
        Payment payment = new Payment();
        mapDTOToEntity(paymentDTO, payment);
        return ResponseEntity.status(HttpStatus.CREATED).body(paymentRepository.save(payment));
    }

    @Override
    public ResponseEntity<?> getById(Long id) {
        return paymentRepository.findById(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<?> deletePayment(Account loggedAccount, Long id) {
        //Comprobamos si tiene Role de ADMIN y si no lo tiene, da error
        if (!isAdmin(loggedAccount)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        if (paymentRepository.existsById(id)){
            paymentRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<?> putPayment(Account loggedAccount, Long id, PaymentDTO paymentDTO) {
        //Comprobamos si tiene Role de ADMIN y si no lo tiene, da error
        if (!isAdmin(loggedAccount)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(new ErrorDTO("You don't have permission to modify"));
        }
        //Buscamos Payment por su ID. Si el .map tiene el Payment ejecutamos el código dentro de las llaves.
        //Si no existe, se salta el bloque
        //Al Payment lo hemos llamado existingPayment
        return paymentRepository.findById(id).map(existingPayment -> {
            //Cojo el metodo que paso los datos DTO a entidad y lo metemos al objeto
            mapDTOToEntity(paymentDTO, existingPayment);
            //Guardamos los cambios
            paymentRepository.save(existingPayment);
            return ResponseEntity.ok(existingPayment);
        //Si no existe le devolvemos un notFound
        }).orElse(ResponseEntity.notFound().build());
    }

    //Verificamos si la cuenta tiene rol ADMIN
    private boolean isAdmin(Account account) {
        return account != null && "ADMIN".equalsIgnoreCase(account.getRole().toString());
    }

    //Creamos un metodo para pasar los datos del DTO a entidad
    private void mapDTOToEntity(PaymentDTO paymentDTO, Payment payment){
        payment.setOrder(paymentDTO.getOrder());
        payment.setPaymentMethod(paymentDTO.getPaymentMethod());
        payment.setTransactionId(paymentDTO.getTransactionId());
        payment.setPaymentstatus(paymentDTO.getPaymentstatus());
        payment.setPaymentDate(paymentDTO.getPaymentDate());
    }
}
