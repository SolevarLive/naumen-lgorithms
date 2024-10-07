package ru.naumen.collection.task4;

import java.util.concurrent.*;
import java.util.function.Supplier;

/**
 * Класс управления расчётами
 */
public class ConcurrentCalculationManager<T> {
    // Используем BlockingQueue для хранения результатов вычислений
    // Выбор BlockingQueue
    // Обеспечивает потокобезопасный доступ к элементам
    // Гарантирует порядок добавления элементов, что позволяет извлекать результаты в том порядке,
    //   в котором были добавлены задачи.
    // LinkedBlockingQueue позволяет использовать два отдельных сегмента для чтения и записи,
    //   что улучшает производительность в многопоточных приложениях.
    private final BlockingQueue<Future<T>> resultsQueue = new LinkedBlockingQueue<>();

    // Используем ExecutorService для управления потоками.
    // Выбор ExecutorService
    // Позволяет управлять пулом потоков и повторно использовать их для выполнения задач,
    //    что снижает накладные расходы на создание новых потоков.
    //Executors.newCachedThreadPool() создает потоки по мере необходимости и повторно использует
    //    уже завершенные потоки, что делает его подходящим для задач с переменной нагрузкой.
    private final ExecutorService executorService = Executors.newCachedThreadPool();

    /**
     * Добавить задачу на параллельное вычисление
     */
    public void addTask(Supplier<T> task) {
        // Отправляем задачу на выполнение и помещаем Future в очередь результатов.
        Future<T> future = executorService.submit(task::get);
        resultsQueue.add(future); // Сложность O(1) для добавления в очередь.
    }

    /**
     * Получить результат вычисления.
     * Возвращает результаты в том порядке, в котором добавлялись задачи.
     */
    public T getResult() {
        try {
            // Извлекаем результат из очереди. Если задача еще не завершена,
            // метод блокируется до тех пор, пока результат не станет доступен.
            Future<T> future = resultsQueue.take(); // Сложность O(1) для извлечения из очереди.
            return future.get(); // Получаем результат выполнения задачи. Сложность O(1), но может блокироваться.
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Ошибка при получении результата", e);
        }
    }

    //итоговая сложность составляет: O(1)
}