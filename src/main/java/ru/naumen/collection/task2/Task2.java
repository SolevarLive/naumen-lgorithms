package ru.naumen.collection.task2;

import java.util.*;

/**
 * Дано:
 * <pre>
 * public class User {
 *     private String username;
 *     private String email;
 *     private byte[] passwordHash;
 *     …
 * }
 * </pre>
 * Нужно реализовать метод
 * <pre>
 * public static List<User> findDuplicates(Collection<User> collA, Collection<User> collB);
 * </pre>
 * <p>который возвращает дубликаты пользователей, которые есть в обеих коллекциях.</p>
 * <p>Одинаковыми считаем пользователей, у которых совпадают все 3 поля: username,
 * email, passwordHash. Дубликаты внутри коллекций collA, collB можно не учитывать.</p>
 * <p>Метод должен быть оптимален по производительности.</p>
 * <p>Пользоваться можно только стандартными классами Java SE.
 * Коллекции collA, collB изменять запрещено.</p>
 *
 * См. {@link User}
 *
 * @author vpyzhyanov
 * @since 19.10.2023
 */
public class Task2 {

    /**
     * Возвращает дубликаты пользователей, которые есть в обеих коллекциях
     */
    public static List<User> findDuplicates(Collection<User> collA, Collection<User> collB) {
        // Используем HashSet для хранения пользователей из collA.
        // HashSet обеспечивает O(1) сложность на операции добавления и проверки наличия элемента.
        // Это достигается за счет использования хеш-таблицы.
        Set<User> setA = new HashSet<>(collA);

        // Список для хранения найденных дубликатов.
        // ArrayList позволяет динамически изменять размер и обеспечивает O(1) сложность для добавления элементов в конец.
        List<User> userList = new ArrayList<>();

        // Проходим по каждому пользователю из collB.
        for (User user : collB) {
            // Операция contains у HashSet имеет O(1) сложность благодаря хешированию.
            if (setA.contains(user)) {
                userList.add(user);
            }
        }
        //Сложность алгоритма: O(n + m), где n — количество элементов в collA,
        // а m — количество элементов в collB. Потому что
        // мы сначала добавляем все элементы из collA в HashSet (O(n)),
        // а затем проверяем каждый элемент из collB на наличие в HashSet (O(m)).
        return userList;
    }
}
