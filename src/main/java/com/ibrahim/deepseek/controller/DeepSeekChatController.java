package com.ibrahim.deepseek.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DeepSeekChatController {

    private final ChatClient chatClient;

    public DeepSeekChatController(ChatClient.Builder chatClient) {
        this.chatClient = chatClient.build();
    }

    @GetMapping("/")
    public String showForm() {
        return "chat-form"; // This points to chat-form.html in src/main/resources/templates/
    }

    @PostMapping("/sendPrompt")
    public String processPrompt(@RequestParam("chat") String chat, Model model) {
        try {
            String response = chatClient.prompt(chat).call().content();
            model.addAttribute("chat", chat);
            model.addAttribute("response", response);
        } catch (Exception e) {
            model.addAttribute("error", "Error: " + e.getMessage());
        }
        return "chat-form";
    }
}