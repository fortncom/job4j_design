package ru.job4j.io;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        byte exception = 3;
        short countException = 15;
        LOG.error("Программа завершилась с ошибкой №{}. Количество критических завершений {}.",
                exception, countException);
        boolean debugOn = true;
        long timeDebug = 32143244356243423L;
        LOG.debug("Процесс отладки запущен? - {}. продолжительность {} мс.",
                debugOn, timeDebug);
        String name = "Tom";
        char level = 'A';
        int id = 242452352;
        LOG.info("Пользователь {} с уровнем доступа {} и id {} зарегистрирован.",
                name, level, id);
        int success = 1284;
        float averageSuccess = (success + countException) / countException;
        LOG.info("Среднее количество успешных сборок на одно критическое завершение {}.",
                averageSuccess);
        double timeSession = 55.5;
        LOG.warn("Превышено время работы сессии {}. Сессия завершена.",
                timeSession);
    }
}
