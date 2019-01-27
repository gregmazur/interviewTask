package hello.world.interviewTask.service;

import hello.world.interviewTask.dao.UserRep;
import hello.world.interviewTask.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRep userRep;

    public User getUser(String name){
        return userRep.findByUsername(name);
    }

    @Override
    public User saveUser(User user) {
        return userRep.save(user);
    }
}
