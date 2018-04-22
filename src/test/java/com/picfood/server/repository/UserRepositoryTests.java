package com.picfood.server.repository;

/**
 * Created by shawn on 2018/4/22.
 */

import com.picfood.server.entity.User;
import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.*;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTests {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository repository;

    @Test
    public void testFindByUserId() throws Exception {
        User temp = new User();
        temp.setUserId("uuid");
        this.entityManager.persist(temp);
        this.entityManager.flush();
        User found = this.repository.findByUserId(temp.getUserId());
        assertThat(found.getUserId()).isEqualTo(temp.getUserId());
    }

    @Test
    public void testFindByEmail() throws Exception {
        User temp = new User();
        temp.setUserId("uuid");
        temp.setEmail("email");
        this.entityManager.persist(temp);
        this.entityManager.flush();
        User found = this.repository.findByEmail(temp.getEmail());
        assertThat(found.getEmail()).isEqualTo(temp.getEmail());
    }
}
