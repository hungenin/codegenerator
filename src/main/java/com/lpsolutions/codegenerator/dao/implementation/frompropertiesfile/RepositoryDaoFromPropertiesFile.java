package com.lpsolutions.codegenerator.dao.implementation.frompropertiesfile;

import com.lpsolutions.codegenerator.dao.RepositoryDao;
import com.lpsolutions.codegenerator.model.Repository;
import org.springframework.beans.factory.annotation.Value;

public class RepositoryDaoFromPropertiesFile implements RepositoryDao {
    @Value("${repositoryUrl}")
    String repositoryUrl;

    @Override
    public Repository getRepository() {
        return new Repository(repositoryUrl);
    }
}
