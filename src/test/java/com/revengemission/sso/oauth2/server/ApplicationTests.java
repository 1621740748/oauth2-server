package com.revengemission.sso.oauth2.server;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.revengemission.sso.oauth2.server.persistence.entity.UserAccountEntity;
import com.revengemission.sso.oauth2.server.persistence.repository.UserAccountRepository;
import com.revengemission.sso.oauth2.server.utils.JsonUtil;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {


    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    UserAccountRepository userAccountRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Ignore
    @Test
    public void localDateTest() {

        LocalDate date = LocalDate.of(1988, 6, 6);
        int count = jdbcTemplate.update("update user_account_entity set birthday=? where id=1; ", date);
        System.out.println("count = " + count);

    }

    @Ignore
    @Test
    public void insertUserAccount() throws JsonProcessingException {
        UserAccountEntity userAccountEntity = new UserAccountEntity();
        userAccountEntity.setUsername(RandomStringUtils.randomAlphabetic(10));
        userAccountEntity.setPassword(passwordEncoder.encode("tgb.258"));
        userAccountEntity.setAccountOpenCode(UUID.randomUUID().toString());
        LocalDate date = LocalDate.of(1988, 6, 6);
        userAccountEntity.setBirthday(date);
        userAccountRepository.save(userAccountEntity);

        System.out.println(JsonUtil.objectToJsonString(userAccountEntity));
        System.out.println("---------------------------");

    }

    @Ignore
    @Test
    public void updateUserAccount() {
        userAccountRepository.findById(1L).ifPresent(e -> {
            e.setRemarks("123");
            userAccountRepository.save(e);
        });

        System.out.println("---------------------------");

    }

}
