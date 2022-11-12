package com.memos.api.repository;
import com.memos.api.model.Memo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemoRepository extends MongoRepository<Memo, String> {
    List<Memo> findByUserIdOrderByCreationDateDesc(String userId);

}