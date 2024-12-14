package com.example.service;

import com.example.model.ChatPartnerDTO;
import com.example.model.Message;
import com.example.model.MessageDTO;
import com.example.repository.AccountRepository;
import com.example.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private AccountService accountService;

    public Message saveMessage(Message chatMessage) {
        Message message = new Message();
        message.setToId(chatMessage.getToId());
        message.setFromId(chatMessage.getFromId());
        message.setMessageText(chatMessage.getMessageText());
        message.setTimestamp(new java.util.Date());
        return messageRepository.save(message);
    }

    public List<Message> getMessagesBetween(String toId, String fromId) {
        return messageRepository.findMessagesBetween(toId, fromId);
    }

    public List<MessageDTO> getMessagesBetween2(String toId, String fromId) {
        List<MessageDTO> list = new ArrayList<>();
        List<Message> messages =  messageRepository.findMessagesBetween(toId, fromId);
        for (Message message : messages) {
            MessageDTO listmes = new MessageDTO();
            listmes.setFromId(message.getFromId());
            listmes.setToId(message.getToId());
            listmes.setMessageText(message.getMessageText());
            listmes.setTimestamp(message.getTimestamp());
            listmes.setAvatar(accountService.findByUserID(message.getFromId()).getAvatar());

            list.add(listmes);
        }
        return list;
    }


    public List<Message> getLatestMessages(String fromId) {
        return messageRepository.findLatestMessages(fromId);
    }

    public List<ChatPartnerDTO> getLatestChatMessages(String fromId) {
        List<ChatPartnerDTO> chatPartnerList = new ArrayList<>();

        // Lấy danh sách Bill theo CTVID và trạng thái
        List<Message> messages = getLatestMessages(fromId);
        for (Message message : messages) {
            ChatPartnerDTO chatPartner = new ChatPartnerDTO();
            chatPartner.setAccount(accountService.findByUserID(message.getToId()));
            chatPartner.setMessage(message.getMessageText());
            chatPartner.setLastMessageTime(message.getTimestamp());

            chatPartnerList.add(chatPartner);
        }
        return chatPartnerList;
    }


}

