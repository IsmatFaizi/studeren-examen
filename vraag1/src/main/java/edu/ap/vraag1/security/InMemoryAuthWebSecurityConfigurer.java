package edu.ap.vraag1.security;


import edu.ap.vraag1.jpa.Users;
import edu.ap.vraag1.jpa.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class InMemoryAuthWebSecurityConfigurer {
    @Autowired
    private UsersRepository repository;
    List<Users> users = new ArrayList<>();

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        Users user1 = new Users("user1", "test", "admin");
        Users user2 = new Users("user2", "test", "writer");
        Users user3 = new Users("user3", "test", "reader");
        repository.save(user1);
        repository.save(user2);
        repository.save(user3);



        
        Iterable<Users> all = repository.findAll();
        all.forEach(user -> users.add(user));
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        List<UserDetails> userDetails = new ArrayList<>();
        if(!users.isEmpty()){
            for (Users user : users) {
                UserDetails userDetail = org.springframework.security.core.userdetails.User.withUsername(user.getUsername())
                        .password(encoder.encode(user.getPassword()))
                        .roles(user.getRole())
                        .build();
                userDetails.add(userDetail);
            }
        }

        return new InMemoryUserDetailsManager(userDetails);
    }
}
