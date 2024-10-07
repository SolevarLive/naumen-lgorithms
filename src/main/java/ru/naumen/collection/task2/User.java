package ru.naumen.collection.task2;

import java.util.Arrays;
import java.util.Objects;

/**
 * Пользователь
 *
 * @author vpyzhyanov
 * @since 19.10.2023
 */
public class User {
    private String username;
    private String email;
    private byte[] passwordHash;

    public User(String username, String email, byte[] passwordHash){
        this.email=email;
        this.passwordHash=passwordHash;
        this.username=username;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(username, user.username) &&
                Objects.equals(email, user.email) &&
                Arrays.equals(passwordHash, user.passwordHash);
    }

    @Override
    public int hashCode() {
        // Генерируем хеш-код на основе полей username и email
        int result = Objects.hash(username, email);
        // Добавляем хеш-код passwordHash
        result = 31 * result + Arrays.hashCode(passwordHash);
        return result;
    }
}
