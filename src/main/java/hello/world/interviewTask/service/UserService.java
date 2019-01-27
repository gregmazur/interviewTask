package hello.world.interviewTask.service;

import hello.world.interviewTask.entity.User;

public interface UserService {

    User getUser(String name);

    User saveUser(User user);
}
