import org.telegram.telegrambots.api.methods.AnswerInlineQuery;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.inlinequery.InlineQuery;
import org.telegram.telegrambots.api.objects.inlinequery.inputmessagecontent.InputMessageContent;
import org.telegram.telegrambots.api.objects.inlinequery.inputmessagecontent.InputTextMessageContent;
import org.telegram.telegrambots.api.objects.inlinequery.result.InlineQueryResult;
import org.telegram.telegrambots.api.objects.inlinequery.result.InlineQueryResultArticle;
import org.telegram.telegrambots.api.objects.inlinequery.result.InlineQueryResultDocument;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.exceptions.TelegramApiValidationException;

public class EnglorkBot extends TelegramLongPollingBot {
	
	private static final String TOKEN = "Token";
	private static final String NAME = "Name";

	@Override
	public String getBotUsername() {
		return NAME;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void onUpdateReceived(Update update) {
		if (update.hasInlineQuery() && update.getInlineQuery().hasQuery())

		{
			InlineQuery query = update.getInlineQuery();
			String id = query.getId();
			String text = query.getQuery();

			AnswerInlineQuery ans = new AnswerInlineQuery();
			ans.setInlineQueryId(id);
			InlineQueryResultArticle res = makeResult(text);
			ans.setResults(res);
			try {
				answerInlineQuery(ans);
			} catch (TelegramApiException e) {
				e.printStackTrace();
			}
		} else if (update.hasMessage() && update.getMessage().hasText() && update.getMessage().isUserMessage()) {
			SendMessage message = new SendMessage().setChatId(update.getMessage().getChatId())
					.setText(update.getMessage().getText());
			try {
				String orkalised = orkalise(message.getText());
				message.setText(orkalised);
				execute(message);
				System.out.println(message.getChatId());
			} catch (TelegramApiException e) {
				e.printStackTrace();
			}
		}
	}

	private static InlineQueryResultArticle makeResult(String text) {
		return new InlineQueryResultArticle().setId("0").setTitle("Orkified")
				.setInputMessageContent(new InputTextMessageContent().setMessageText(orkalise(text)));
	}

	@Override
	public String getBotToken() {
		return TOKEN;
	}

	private static String orkalise(String text) {
		return Englorker.orkify(text);
	}

}
