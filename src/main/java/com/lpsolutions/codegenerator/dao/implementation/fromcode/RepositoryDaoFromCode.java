package com.lpsolutions.codegenerator.dao.implementation.fromcode;

import com.lpsolutions.codegenerator.dao.RepositoryDao;
import com.lpsolutions.codegenerator.model.Repository;

@org.springframework.stereotype.Repository
public class RepositoryDaoFromCode implements RepositoryDao {
    private final String repositoryUrl = "https://github.com/hungenin/codegenerator";

    @Override
    public Repository getRepository() {
        return new Repository(repositoryUrl);
    }
}
