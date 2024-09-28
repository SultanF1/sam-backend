package org.sultan.Sam.chat.api;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.sultan.Sam.chat.service.ChatService;

@RestController
@AllArgsConstructor
class ChatController {
  private final Logger logger = LoggerFactory.getLogger(ChatController.class);
  private final ChatService chatService;

  @PostMapping("/chat")
  public String chat(String message, String chatId, String market) {
    logger.info("Received message {}, chatId {}, market {}", message, chatId, market);
    return chatService.chat(chatId, message, market);
  }
}
