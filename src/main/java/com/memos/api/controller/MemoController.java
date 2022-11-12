package com.memos.api.controller;

import com.memos.api.model.Memo;
import com.memos.api.security.CurrentUser;
import com.memos.api.security.UserPrincipal;
import com.memos.api.service.MemoService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MemoController {
    private final MemoService memoService;

    public MemoController(MemoService memoService) {
        this.memoService = memoService;
    }

    @GetMapping("/memos")
    @PreAuthorize("hasRole('USER')")
    List<Memo> getAll(@CurrentUser UserPrincipal userPrincipal) {
        String userId = userPrincipal.getId();
        return this.memoService.getAllMemosForUser(userId);
    }

    @GetMapping("/memos/{id}")
    @PreAuthorize("hasRole('USER')")
    Memo getOne(@PathVariable String id, @CurrentUser UserPrincipal userPrincipal) {
        String userId = userPrincipal.getId();
        return memoService.getMemoById(userId, id);
    }

    @PostMapping("/memos")
    @PreAuthorize("hasRole('USER')")
    Memo createMemo(@RequestBody Memo newMemo, @CurrentUser UserPrincipal userPrincipal) {
        String userId = userPrincipal.getId();
        System.out.println(userId);
        return memoService.createMemo(userId, newMemo);
    }

    @PutMapping("/memos/{id}")
    @PreAuthorize("hasRole('USER')")
    Memo updateMemo(@PathVariable String id, @RequestBody Memo memo, @CurrentUser UserPrincipal userPrincipal) {
        String userId = userPrincipal.getId();
        return memoService.updateMemo(userId, id, memo);
    }

    @PutMapping("/memos/update/{id}")
    @PreAuthorize("hasRole('USER')")
    public Memo updateById(@PathVariable String id, @CurrentUser UserPrincipal userPrincipal) {
        String userId = userPrincipal.getId();
        return memoService.getMemoById(userId, id);
    }

    @DeleteMapping("/memos/{id}")
    @PreAuthorize("hasRole('USER')")
    void deleteMemo(@PathVariable String id, @CurrentUser UserPrincipal userPrincipal) {
        String userId = userPrincipal.getId();
        this.memoService.deleteMemoById(userId, id);
    }

}
