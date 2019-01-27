package hello.world.interviewTask.dao;

import hello.world.interviewTask.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRep extends JpaRepository<User, Long> {

    User findByUsername(String login);
}
