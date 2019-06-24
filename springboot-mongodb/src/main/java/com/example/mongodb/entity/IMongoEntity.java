package com.example.mongodb.entity;


import org.springframework.data.mongodb.core.query.Query;

public interface IMongoEntity {

     String getId();
     Query getQuery();
     Query getQueryForUpdateById();
}
