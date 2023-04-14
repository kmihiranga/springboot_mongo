package com.techwizlanka.productservice.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.techwizlanka.productservice.repository")
public class MongoConfig extends AbstractMongoClientConfiguration {
    @Value("${udemy.mongodb.replicaset.database}")
    private String database;

    @Value("${udemy.mongodb.replicaset.authMechanism}")
    private String authMechanism;

    @Value("${udemy.mongodb.replicaset.username}")
    private String replicasetUsername;

    @Value("${udemy.mongodb.replicaset.password}")
    private String replicasetPassword;

    @Value("${udemy.mongodb.replicaset.primary}")
    private String replicasetPrimary;

    @Value("${udemy.mongodb.replicaset.name}")
    private String replicasetName;

    @Value("${udemy.mongodb.replicaset.port}")
    private String replicasetPort;

    @Value("${udemy.mongodb.replicaset.authentication-database}")
    private String replicasetAuthenticationDb;

    @Value("${udemy.mongodb.replicaset.maxPoolSize}")
    private String replicasetMaxPoolSize;

    @Override
    public MongoClient mongoClient() {
        return MongoClients.create(mongoUri());
    }

    public String mongoUri() {
        return String.format("mongodb://%s:%s@%s:%s/%d?replicaSet=%s&authSource=%s&maxPoolSize=%s&authMechanism=%s&ssl=false", replicasetUsername, replicasetPassword, replicasetPrimary, replicasetPort, database, replicasetName, replicasetAuthenticationDb, replicasetMaxPoolSize, authMechanism);
    }

    @Override
    protected String getDatabaseName() {
        return database;
    }
}
