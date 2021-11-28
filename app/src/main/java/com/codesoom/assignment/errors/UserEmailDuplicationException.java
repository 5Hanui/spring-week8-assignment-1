package com.codesoom.assignment.errors;

/**
 * 유저 이메일 중복 예외.
 */
public class UserEmailDuplicationException extends RuntimeException {
    public UserEmailDuplicationException(String email) {
        super("User email is alreay existed: " + email);
    }
}
