package ru.naumen.collection.task3;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>Написать консольное приложение, которое принимает на вход произвольный текстовый файл в формате txt.
 * Нужно собрать все встречающийся слова и посчитать для каждого из них количество раз, сколько слово встретилось.
 * Морфологию не учитываем.</p>
 * <p>Вывести на экран наиболее используемые (TOP) 10 слов и наименее используемые (LAST) 10 слов</p>
 * <p>Проверить работу на романе Льва Толстого “Война и мир”</p>
 *
 * @author vpyzhyanov
 * @since 19.10.2023
 */
public class WarAndPeace {

    private static final Path WAR_AND_PEACE_FILE_PATH = Path.of("src/main/resources",
            "Лев_Толстой_Война_и_мир_Том_1,_2,_3,_4_(UTF-8).txt");

    public static void main(String[] args) {
        //Сложность: O(1) для инициализации.
        //так как создание нового объекта HashMap не требует обработки данных, и поэтому занимает постоянное время.
        // HashMap выбран, так как обеспечивает O(1) время доступа по ключу,
        // что позволяет эффективно обновлять количество вхождений слов.
        Map<String, Integer> wordCountMap = new HashMap<>();



        //Сложность: O(n), где n — количество слов в файле.
        //Метод forEachWord проходит по всем словам в файле, что требует O(n) времени.
        //Для каждого слова выполняется операция put в HashMap, которая  имеет сложность O(1).
        // Использование метода getOrDefault также имеет сложность O(1).
        // Таким образом, для n слов общая сложность будет O(n).
        // Используем WordParser для чтения файла и обработки слов.
        new WordParser(WAR_AND_PEACE_FILE_PATH)
                .forEachWord(word -> wordCountMap.put(word, wordCountMap.getOrDefault(word, 0) + 1));

        // Получаем топ 10 наиболее часто используемых слов
        //Используем List так как  List сохраняет порядок элементов,
        // что позволяет легко выводить слова в том порядке, в котором они были отсортированы
        // List предоставляет удобные методы для итерации, такие как forEach, что упрощает вывод результатов на экран.
        List<Map.Entry<String, Integer>> topWorlds = getTopWords(wordCountMap, 10);
        System.out.println("TOP 10 наиболее используемых слов:");
        topWorlds.forEach(entry -> System.out.println(entry.getKey() + ": " + entry.getValue()));

        //Сложность: O(m log m), где m — количество уникальных слов в wordCountMap.
        //Метод getLastWords использует стримы для сортировки элементов.
        // Сортировка требует O(m log m) времени в среднем из-за алгоритма Timsort, используемого в Java.
        // Получаем топ 10 наименее часто используемых слов
        List<Map.Entry<String, Integer>> lastWorlds = getLastWords(wordCountMap, 10);
        System.out.println("TOP 10 наименее используемых слов:");
        lastWorlds.forEach(entry -> System.out.println(entry.getKey() + ": " + entry.getValue()));

        //Общаясложность метода main составляет:
        //O(n + m log m)
        //где:
        //n — общее количество слов в файле.
        //m — количество уникальных слов.
    }

    private static List<Map.Entry<String, Integer>> getTopWords(Map<String, Integer> wordCountMap, int n) {
        // Сортируем по убыванию частоты и берем первые n элементов.
        return wordCountMap.entrySet()
                .stream()
                .sorted((a, b) -> b.getValue().compareTo(a.getValue()))
                .limit(n)
                .toList();
    }

    private static List<Map.Entry<String, Integer>> getLastWords(Map<String, Integer> wordCountMap, int n) {
        // Сортируем по возрастанию частоты и берем первые n элементов.
        return wordCountMap.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .limit(n)
                .toList();
    }

}
