package com.memos.api.exception;

public class MemoNotFoundException extends RuntimeException {
    public MemoNotFoundException(String id) {
        super("Could not find employee " + id);
    }
}
