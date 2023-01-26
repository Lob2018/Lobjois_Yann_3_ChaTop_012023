package fr.hatclic.chatop.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fr.hatclic.chatop.model.Messages;
import fr.hatclic.chatop.repository.MessagesRepository;

@Service
public class MessagesService {

	@Autowired
	private MessagesRepository messagesRepository;

	public Messages createMessage(Messages message) {
		if (messageIsNull(message))
			throw new Error();
		return messagesRepository.save(message);
	}

	public Optional<Messages> findMessageById(final Long id) {
		if (id == null) {
			throw new Error();
		}
		return messagesRepository.findById(id);
	}

	public Iterable<Messages> getAllMessages() {
		return messagesRepository.findAll();
	}

	public Messages updateMessage(Messages message) {
		if (messageIsNull(message) || message.getId() == null)
			throw new Error();
		return messagesRepository.save(message);
	}

	public void deleteMessageById(final Long id) {
		if (id == null) {
			throw new Error();
		}
		messagesRepository.deleteById(id);
	}

	public boolean messageIsNull(Messages message) {
		if (message == null || message.getUser_id() == null || message.getRental_id() == null
				|| message.getMessage().trim().length() == 0) {
			return true;
		}
		return false;
	}

}
