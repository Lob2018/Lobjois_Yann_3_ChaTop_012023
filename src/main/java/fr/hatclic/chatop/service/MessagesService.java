package fr.hatclic.chatop.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import fr.hatclic.chatop.model.Messages;
import fr.hatclic.chatop.repository.MessagesRepository;

public class MessagesService {

	@Autowired
	private MessagesRepository messagesRepository;

	public Messages createMessage(Messages message) {
		return messagesRepository.save(message);
	}

	public Optional<Messages> findMessageById(final Long id) {
		return messagesRepository.findById(id);
	}

	public Iterable<Messages> getAllMessages() {
		return messagesRepository.findAll();
	}

	public Messages updateMessage(Messages message) {
		return messagesRepository.save(message);
	}

	public void deleteMessageById(final Long id) {
		messagesRepository.deleteById(id);
	}

}
