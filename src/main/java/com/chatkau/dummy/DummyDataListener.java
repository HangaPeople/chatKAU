package com.chatkau.dummy;

import com.chatkau.entity.UserDetail;
import com.chatkau.entity.UserLogin;
import com.chatkau.entity.UserRole;
import com.chatkau.repository.UserDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@Transactional
public class DummyDataListener implements ApplicationListener<ContextRefreshedEvent> {

    private final UserDetailRepository userDetailRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        loadUserData();
    }

    private void loadUserData() {
        saveUserDataIfNotNull(1L, "sample", "test", "test", 2017101231L, 4L, UserRole.ROLE_USER);
    }

    private void saveUserDataIfNotNull(Long id, String nickname, String username, String password, Long studentNumber, Long grade, UserRole role) {
        Optional<UserDetail> byId = userDetailRepository.findById(id);
        if(byId.isPresent()) {
            return;
        }
        UserDetail user = UserDetail.builder()
                .id(id)
                .nickname(nickname)
                .username(username)
                .studentNumber(studentNumber)
                .grade(grade)
                .build();

        UserLogin login = UserLogin.builder()
                .userDetail(user)
                .username(username)
                .password(bCryptPasswordEncoder.encode(password))
                .role(role)
                .build();

        user.setUserLogin(login);

        userDetailRepository.save(user);
    }
}
