package hello.world.interviewTask.dao;

import hello.world.interviewTask.entity.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepTest {

    @Autowired
    private UserRep userRep;

    private User user;

    @Before
    public void setUp() throws Exception {
        user = new User();
        user.setUsername("user");
        user.setPassword("pass");
        userRep.save(user);
    }

    @After
    public void tearDown() throws Exception {
        userRep.delete(user);
    }

    @Test
    public void findByUsername_whenFound() {
        User userFromDb = userRep.findByUsername("user");
        assertNotNull(userFromDb);
    }

    @Test
    public void findByUsername_whenNorFound() {
        User userFromDb = userRep.findByUsername("empty");
        assertNull(userFromDb);
    }
}