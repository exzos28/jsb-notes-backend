package com.memos.api.service;
import java.util.List;

import com.memos.api.model.Memo;

public interface MemoService {
    List<Memo> getAllMemosForUser(String userId);
    Memo createMemo(String userId, Memo memo);
    Memo updateMemo(String userId, String id, Memo memo);
    Memo getMemoById(String userId, String id);
    void deleteMemoById(String userId, String id);
}
