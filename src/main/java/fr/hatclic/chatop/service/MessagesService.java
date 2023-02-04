package fr.hatclic.chatop.service;

import java.time.ZonedDateTime;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fr.hatclic.chatop.model.Messages;
import fr.hatclic.chatop.repository.MessagesRepository;

@Service
public class MessagesService {

	@Autowired
	private MessagesRepository messagesRepository;

	public final Messages createMessage(final Messages message) {
		if (messageIsNull(message))
			throw new Error();
		message.setCreated_at(ZonedDateTime.now());
		message.setUpdated_at(ZonedDateTime.now());
		return messagesRepository.save(message);
	}

	public final Optional<Messages> findMessageById(final Long id) {
		if (id == null) {
			throw new Error();
		}
		return messagesRepository.findById(id);
	}

	public final Iterable<Messages> getAllMessages() {
		return messagesRepository.findAll();
	}

	public final Messages updateMessage(final Messages message) {
		if (messageIsNull(message) || message.getId() == null)
			throw new Error();
		return messagesRepository.save(message);
	}

	public final void deleteMessageById(final Long id) {
		if (id == null) {
			throw new Error();
		}
		messagesRepository.deleteById(id);
	}

	public final boolean messageIsNull(final Messages message) {
		if (message == null || message.getUser_id() == null || message.getRental_id() == null
				|| message.getMessage().trim().length() == 0) {
			return true;
		}
		return false;
	}

}
