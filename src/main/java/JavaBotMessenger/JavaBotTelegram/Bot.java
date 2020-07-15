package JavaBotMessenger.JavaBotTelegram;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;



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
     * @param message
     * @param text
     */
    private void sendMsg(Message message, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);
        try {
            sendMessge(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }

   public final Message sendMessge(SendMessage sendMessage) throws TelegramApiException {
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

        Message msg = update.getMessage();
        String txt = msg.getText();
        if (txt.equals("/start")) {
            sendMsg(msg, "Привет друг");
        }else {
            sendMsg(msg,"Красава");
        }
    }

    /**
     *
     * @return - возращает имя пользователя бота
     */
    public String getBotUsername() {
        return "dmitryhdlBot";
    }

    /**
     *
     * @return - возращает токен бота
     */
    public String getBotToken() {
        return "1230210035:AAEIlol7TWso0aS094h7CXQoVM2U7n5mVKg";
    }
}
