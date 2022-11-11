package com.lpsolutions.codegenerator.configuration;

import com.lpsolutions.codegenerator.dao.RepositoryDao;
import com.lpsolutions.codegenerator.dao.UserDao;
import com.lpsolutions.codegenerator.dao.implementation.fromcode.RepositoryDaoFromCode;
import com.lpsolutions.codegenerator.dao.implementation.fromcommandlineargument.UserDaoFromCommandLineArgument;
import com.lpsolutions.codegenerator.dao.implementation.frompropertiesfile.RepositoryDaoFromPropertiesFile;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class DaoConfiguration {
    private final DaoType repositoryDaoType = DaoType.PROPERTIES_FILE;
    private final DaoType userDaoType = DaoType.COMMAND_LINE_ARGUMENT;

    @Bean
    @Primary
    public RepositoryDao getRepositoryDao() {
        switch (repositoryDaoType) {
            case CODE:
                return new RepositoryDaoFromCode();
            case PROPERTIES_FILE:
                return new RepositoryDaoFromPropertiesFile();
            default:
                throw new RuntimeException("Not supported repository dao type!");
        }
    }

    @Bean
    @Primary
    public UserDao getUserDao() {
        switch (userDaoType) {
            case COMMAND_LINE_ARGUMENT:
                return new UserDaoFromCommandLineArgument();
            default:
                throw new RuntimeException("Not supported user dao type!");
        }
    }
}
