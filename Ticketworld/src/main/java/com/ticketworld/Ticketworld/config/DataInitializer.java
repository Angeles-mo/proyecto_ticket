package com.ticketworld.Ticketworld.config;

import com.ticketworld.Ticketworld.entity.Account;
import com.ticketworld.Ticketworld.entity.Artist;
import com.ticketworld.Ticketworld.entity.Rol;
import com.ticketworld.Ticketworld.repositories.AccountRepository;
import com.ticketworld.Ticketworld.repositories.ArtistRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initData(AccountRepository accountRepository,
                                      ArtistRepository artistRepository,
                                      PasswordEncoder passwordEncoder) {
        return args -> {

            // Evita duplicados si reinicio el proyecto
            if (accountRepository.findByEmail("artist@ticketworld.com").isPresent()) {
                return;
            }

            // 1. Crea la Account con rol ARTIST
            Account account = new Account();
            account.setEmail("artist2@ticketworld.com");
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
        };
    }
}
