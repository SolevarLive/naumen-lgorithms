package ru.naumen.collection.task1;

import java.util.Objects;

/**
 * Билет
 *
 * @author vpyzhyanov
 * @since 19.10.2023
 */
public class Ticket {
    private long id;
    private String client;

    public Ticket(Long id, String client){
        this.id=id;
        this.client=client;
    }

    public long getId(){
        return id;
    }

    public String getClient(){
        return client;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return id == ticket.id;
    }

    @Override
    public int hashCode() {
        // Генерируем хеш-код на основе поля id
        return Long.hashCode(id);
    }
}
