package com.memos.api.service;

import com.memos.api.exception.MemoNotFoundException;
import com.memos.api.model.Memo;
import com.memos.api.repository.MemoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemoServiceImpl implements MemoService {
    private final MemoRepository memoRepository;

    public MemoServiceImpl(MemoRepository memoRepository) {
        this.memoRepository = memoRepository;
    }

    @Override
    public List<Memo> getAllMemosForUser(String userId) {
        return memoRepository.findByUserIdOrderByCreationDateDesc(userId);
    }

    @Override
    public Memo createMemo(String userId, Memo memo) {
        Memo newMemo = new Memo();
        newMemo.setContent(memo.getContent());
        newMemo.setUserId(userId);
        return this.memoRepository.save(newMemo);
    }

    @Override
    public Memo updateMemo(String userId, String id, Memo updatedMemo) {
        return this.memoRepository.findById(id).map(memo -> {
                    if (!memo.getUserId().equals(userId)) {
                        throw new MemoNotFoundException(id);
                    }
                    memo.setContent(updatedMemo.getContent());
                    return memoRepository.save(memo);
                })
                .orElseThrow(() -> new MemoNotFoundException(id));
    }

    @Override
    public Memo getMemoById(String userId, String id) {
        return memoRepository.findById(id)
                .map(memo -> {
                    if (!memo.getUserId().equals(userId)) {
                        throw new MemoNotFoundException(id);
                    }
                    return memo;
                })
                .orElseThrow(() -> new MemoNotFoundException(id));
    }

    @Override
    public void deleteMemoById(String userId, String id) {
        memoRepository.findById(id).map(memo -> {
            if (!memo.getUserId().equals(userId)) {
                throw new MemoNotFoundException(id);
            }
            this.memoRepository.deleteById(id);
            return null;
        });
    }
}
