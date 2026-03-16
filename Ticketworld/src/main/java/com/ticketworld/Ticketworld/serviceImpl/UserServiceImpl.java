package com.ticketworld.Ticketworld.serviceImpl;

import com.ticketworld.Ticketworld.dto.AccountDTO;
import com.ticketworld.Ticketworld.dto.ErrorDTO;
import com.ticketworld.Ticketworld.dto.UserDTO;
import com.ticketworld.Ticketworld.entity.Account;
import com.ticketworld.Ticketworld.entity.Rol;
import com.ticketworld.Ticketworld.entity.User;
import com.ticketworld.Ticketworld.repositories.AccountRepository;
import com.ticketworld.Ticketworld.repositories.UserRepository;
import com.ticketworld.Ticketworld.services.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public ResponseEntity<?> getAll(Account loggedAccount) {
        if (loggedAccount.getRole().equals(Rol.ADMIN)){
            return ResponseEntity.status(HttpStatus.OK).body(userRepository.findAll().stream().map(User::toDTO));
        }else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorDTO("You don't have permissions to list users"));
        }
    }

    @Override
    public ResponseEntity<?> getById(Account loggedAccount, Long id) {
        if (!loggedAccount.getId().equals(id) && loggedAccount.getRole() != Rol.ADMIN){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorDTO("You don't have permission"));
        }
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()){
            User user = userOptional.get();
            return ResponseEntity.status(HttpStatus.OK).body(User.toDTO(user));
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDTO("User doesn't exist"));
        }
    }

    @Override
    public ResponseEntity<?> deleteUser(Account loggedAccount, Long id) {
        //Si no existe el usuario con esta ID en la base de datos devuelve un true
        if(!userRepository.existsById(id)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDTO("User not found"));
        }
        //Solo el ADMIN o el propio usuario puede borrar la cuenta
        if(!loggedAccount.getId().equals(id) && !"ADMIN".equals(loggedAccount.getRole().name())){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorDTO("You don't have permission to delete this profile"));
        }
        //Si se borra el usuario, el código 204 muestra que se ha borrado con exito y no tiene nada más que demostrar
        userRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Override
    @Transactional
    public ResponseEntity<?> createUser(Account loggedAccount, UserDTO userDTO, AccountDTO accountDTO) {
        try {
            //Creamos y guardamos la cuenta
            Account account = new Account();
            account.setEmail(accountDTO.getEmail());
            account.setPassword(accountDTO.getPassword());
            account.setRole(account.getRole());
            account.setUser(account.getUser());

            //Encriptamos la contraseña
            account.setPassword(passwordEncoder.encode(accountDTO.getPassword()));

            //Guardamos la cuenta
            Account saveAccount = accountRepository.save(account);

            //Creamos el usuario y lo asociamos a la nueva cuenta
            User user = new User();
            user.setName(userDTO.getName());
            user.setLastName(userDTO.getLastName());
            user.setPhoneNumber(userDTO.getPhoneNumber());
            user.setOrders(userDTO.getOrders());
            user.setAccount(userDTO.getAccount());

            //Aquí relacionamos la cuenta y el usuario
            user.setAccount(saveAccount);

            //Guardamos el usuario
            User savedUser = userRepository.save(user);

            //Retornamos un DTO de respuesta
            return ResponseEntity.status(HttpStatus.CREATED).body(User.toDTO(savedUser));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorDTO("Error creating the User: " + e.getMessage()));
        }
    }

    @Override
    public ResponseEntity<?> putUser(Account loggedAccount, Long id, UserDTO userDTO) {
        //Buscamos si el usuario existe
        Optional<User> existingUserOptional = userRepository.findById(id);

        if (existingUserOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDTO("User not found"));
        }
        //Solo el ADMIN o el usuario de la cuenta puede modificar
        if (!loggedAccount.getId().equals(id) && !"ADMIN".equals(loggedAccount.getRole().name())){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorDTO("You don't have permission to modify this profile"));
        }

        User existingUser = existingUserOptional.get();

        //Se actualiza los datos
        existingUser.setName(userDTO.getName());
        existingUser.setLastName(userDTO.getLastName());
        existingUser.setPhoneNumber(userDTO.getPhoneNumber());
        existingUser.setOrders(userDTO.getOrders());
        existingUser.setAccount(userDTO.getAccount());

        User updateUser = userRepository.save(existingUser);
        return ResponseEntity.ok(User.toDTO(updateUser));
    }
}
