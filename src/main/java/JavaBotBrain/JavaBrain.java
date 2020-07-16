package JavaBotBrain;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.Date;
public class JavaBrain {



    public Map<String, String> getAnsAnaliz() {
        return ansAnaliz;
    }

    public Map<String, String> getAnswer() {

        return answer;
    }

    /**
     * Анализ ответов
     */
    final Map<String,String> ansAnaliz = new HashMap<String, String>(){{
       // Приведствие
        put("Хай","hello");
        put("Привет","hello");
        put("Здорово","hello");
        put("Доброго дня","hello");

        // Время
        put("время","time");

        // Погода
        put("погода","wather");


    }};

    /**
     * Ответы
     */
    final Map<String, String> answer = new HashMap<String, String>() {{
        put("hello", "Добрый день, рад вас видеть!");

        // Время
        Date date = new Date();
        String dat = date.toString();
        put("time",dat);

        // Погода
        put("wather","https://rp5.ru/%D0%9F%D0%BE%D0%B3%D0%BE%D0%B4%D0%B0_%D0%B2_%D0%A1%D0%B0%D0%BD%D0%BA%D1%82-%D0%9F%D0%B5%D1%82%D0%B5%D1%80%D0%B1%D1%83%D1%80%D0%B3%D0%B5");

    }};









}
