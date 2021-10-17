package com.messenger.demo.security;

import com.messenger.demo.dao.StudentRepository;
import com.messenger.demo.entity.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final StudentRepository studentRepository;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Student student = studentRepository.findByLogin(login);
        if (student == null) {
            throw new UsernameNotFoundException("Unknown user: " + login);
        }
        return User.builder()
                .username(student.getLogin())
                .password(student.getPassword())
                .roles(student.getRole().getName())
                .build();
    }
}
