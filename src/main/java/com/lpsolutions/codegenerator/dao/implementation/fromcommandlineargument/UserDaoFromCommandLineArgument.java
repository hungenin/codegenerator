package com.lpsolutions.codegenerator.dao.implementation.fromcommandlineargument;

import com.lpsolutions.codegenerator.dao.UserDao;
import com.lpsolutions.codegenerator.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoFromCommandLineArgument implements UserDao {
    @Autowired
    ApplicationArguments appArgs;

    @Override
    public User getUser() {
        return createUserFromArguments();
    }

    private User createUserFromArguments() {
        if (appArgs == null || appArgs.getSourceArgs().length == 0) return new User("", "");
        if (appArgs.getSourceArgs().length == 1) return new User(appArgs.getSourceArgs()[0], "");
        return new User(appArgs.getSourceArgs()[0], appArgs.getSourceArgs()[1]);
    }
}
