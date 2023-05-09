package com.chatkau.security.service;

import com.chatkau.entity.UserLogin;
import com.chatkau.repository.UserLoginRepository;
import com.chatkau.security.util.UserLoginContext;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserLoginRepository userLoginRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserLogin> byUsername = userLoginRepository.findByUsername(username);
        if(byUsername.isEmpty()) {
            throw new UsernameNotFoundException("UsernameNotFoundException");
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(byUsername.get().getRole().toString()));

        return new UserLoginContext(byUsername.get(), authorities);
    }
}
