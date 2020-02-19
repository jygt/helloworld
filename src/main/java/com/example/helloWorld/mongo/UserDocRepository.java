package com.example.helloWorld.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserDocRepository extends MongoRepository<UserDoc,String> {
}
