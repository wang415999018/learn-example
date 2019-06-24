package com.example.mongodb.dao;

import com.example.mongodb.entity.IMongoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MongodbService {

    @Autowired
    public MongoTemplate mongoTemplate;

    public void save(Object object){
        mongoTemplate.save(object);
    }

    public void insert(Object object){
        mongoTemplate.insert(object);
    }

    public Object findById(String id, Class T ){
        Query query = new Query(Criteria.where("id").is(id));
        return mongoTemplate.findOne(query,T);
    }

    public List find(IMongoEntity entity){
        return mongoTemplate.find(entity.getQuery(),entity.getClass());
    }

    public List findByMapForAnd(Map<String,String> map, Class T){
        Query query = new Query();
        for (String key:map.keySet()){
            query.addCriteria(Criteria.where(key).is(map.get(key)));
        }
        return mongoTemplate.find(query,T);
    }

    public long delete(IMongoEntity entity){
        return mongoTemplate.remove(entity.getQuery(),entity.getClass()).getDeletedCount();
    }

    public MongoTemplate getMongoTemplate() {
        return mongoTemplate;
    }
}
