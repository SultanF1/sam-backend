package org.sultan.Sam.chat.service;

import static org.springframework.ai.chat.client.advisor.AbstractChatMemoryAdvisor.CHAT_MEMORY_CONVERSATION_ID_KEY;
import static org.springframework.ai.chat.client.advisor.AbstractChatMemoryAdvisor.CHAT_MEMORY_RETRIEVE_SIZE_KEY;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.QuestionAnswerAdvisor;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.ai.chat.prompt.ChatOptionsBuilder;
import org.springframework.stereotype.Service;
import org.sultan.Sam.markets.service.MarketService;


@Service
@AllArgsConstructor
@Slf4j
public class ChatService {

  private final ChatClient chatClient;
  private final MarketService marketService;

  public String chat(String chatId, String userMessageContent, String market) {
    ChatOptions build =
        ChatOptionsBuilder.builder()
            .withTemperature(marketService.getMarketTemperature(market))
            .build();
    return this.chatClient
        .prompt()
        .user(userMessageContent)
        .options(build)
        .system(marketService.getMarketPrompt(market))
        .advisors(
            a ->
                a.param(CHAT_MEMORY_CONVERSATION_ID_KEY, chatId)
                    .param(CHAT_MEMORY_RETRIEVE_SIZE_KEY, 100)
                    .param(
                        QuestionAnswerAdvisor.FILTER_EXPRESSION,
                        "market == '" + market.toLowerCase() + "'"))
        .call()
        .content();
  }
}
