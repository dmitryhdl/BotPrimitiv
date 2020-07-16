package JavaBotMessenger.JavaBotTelegram;

import JavaBotBrain.JavaBrain;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;


import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Bot extends TelegramLongPollingBot {


    public static void main(String[] args) {
        ApiContextInitializer.init(); // иницилизуем апи
        TelegramBotsApi botaApi = new TelegramBotsApi();
        try {
            botaApi.registerBot(new Bot());
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }

    }

    /**
     * Метод отправляет сообщения по типу send(строка) пользователю

     */
    private void sendMsg(Message message, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);
        try {
            sendMessger(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }

   public final Message sendMessger(SendMessage sendMessage) throws TelegramApiException {
        if (sendMessage == null){
            throw new TelegramApiRequestException("null");

        }else {
            return (Message)this.sendApiMethod(sendMessage);
        }
   }


    /**
     * Метод вызывается при получении данных от пользователя
     *
     * @param update
     */
    public void onUpdateReceived(Update update) {
        Random rnd = new Random();
        JavaBrain brain = new JavaBrain();
        Message msg = update.getMessage();
        String txt = msg.getText();

        /**
         * Возращает количество элементов коллекции
          */
        int keys = brain.getAnsAnaliz().size();


        /**
         * Убирает лишние пробелы и знаки припенания
         */
        String mes = String.join(" ", txt.split("[ {,!.}]+"));
        System.out.println(mes);

        for (Map.Entry<String, String> param : brain.getAnsAnaliz().entrySet()) {

            /**
             * Pattern — это конструктор регулярных выражений.
             * Под «капотом» метод compile вызывает закрытый конструктор класса Pattern для создания скомпилированного представления.
             * Такой способ создания экземпляра шаблона реализован с целью создания его в виде неизменяемого объекта.
             */
            Pattern pattern = Pattern.compile(param.getKey());

            /**
             *  Matcher – это «поисковик», «движок» регулярных выражений.
             *  Matcher представляет собой класс, из которого создается объект для поиска совпадений по шаблону.
             *  Для поиска ему надо дать две вещи: шаблон поиска и «адрес», по которому искать.
             */
            Matcher matcher = pattern.matcher(mes);

            /**
             * Метод find() ищет очередное совпадение в тексте с шаблоном.
             */


            if (matcher.find()) {
                int vrt = rnd.nextInt(keys);

                sendMsg(msg, brain.getAnswer().get(param.getValue()));
            }
        }
    }

    /**
     *
     * возращает имя пользователя бота
     */
    public String getBotUsername() {
        return "dmitryhdlBot";
    }

    /**
     *
     * возращает токен бота
     */
    public String getBotToken() {
        return "1230210035:AAEIlol7TWso0aS094h7CXQoVM2U7n5mVKg";
    }
}
