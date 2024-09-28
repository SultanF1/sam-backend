package org.sultan.Sam.chat.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.PromptChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.QuestionAnswerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.InMemoryChatMemory;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ChatClientConfig {

  @Bean
  public ChatClient Client(
      org.springframework.ai.chat.client.ChatClient.Builder builder,
      VectorStore vectorStore,
      ChatMemory chatMemory) {
    return
        builder
            .defaultAdvisors(
                new PromptChatMemoryAdvisor(chatMemory),
                new QuestionAnswerAdvisor(vectorStore, SearchRequest.defaults()))
            .build();
  }

  @Bean
  public ChatMemory chatMemory() {
    return new InMemoryChatMemory();
  }
}
