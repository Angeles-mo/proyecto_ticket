package com.ticketworld.Ticketworld.config;

import com.ticketworld.Ticketworld.entity.Account;
import com.ticketworld.Ticketworld.entity.Artist;
import com.ticketworld.Ticketworld.entity.Rol;
import com.ticketworld.Ticketworld.entity.User;
import com.ticketworld.Ticketworld.repositories.AccountRepository;
import com.ticketworld.Ticketworld.repositories.ArtistRepository;
import com.ticketworld.Ticketworld.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initData(AccountRepository accountRepository,
                                      ArtistRepository artistRepository,
                                      UserRepository userRepository,
                                      PasswordEncoder passwordEncoder) {
        return args -> {

            // Evita duplicados si reinicio el proyecto

            if (accountRepository.findByEmail("user@ticketworld.com").isPresent()) {
                return;
            }
            /*
            // 1. Crea la Account con rol ARTIST
            Account account = new Account();
            account.setEmail("artist@ticketworld.com");
            account.setPassword(passwordEncoder.encode("artist123"));
            account.setRole(Rol.ADMIN);
            accountRepository.save(account);

            // 2. Crea el Artist vinculado a esa Account
            Artist artist = new Artist();
            artist.setName("Carla");
            artist.setLastName("García");
            artist.setMusicGenre("Rock");
            artist.setBiography("Artista de prueba generado al inicio");
            artist.setAccount(account);  // ← vincula el Artist con la Account
            artistRepository.save(artist);

            System.out.println("Artista de prueba creado: artist@ticketworld.com / artist123");

             */

            // Crea la Account con rol USER

            Account account = new Account();
            account.setEmail("user@ticketworld.com");
            account.setPassword(passwordEncoder.encode("user123"));
            account.setRole(Rol.ADMIN);
            accountRepository.save(account);

            User user = new User();
            user.setName("Carla");
            user.setLastName("García");
            user.setPhoneNumber("123456789");
            user.setOrders (null);
            user.setAccount(account);  // ← vincula el User con la Account
            userRepository.save(user);
        };
    }
}
