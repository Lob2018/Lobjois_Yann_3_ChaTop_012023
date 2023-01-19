package fr.hatclic.chatop.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.hatclic.chatop.model.Messages;

@Repository
public interface MessagesRepository extends CrudRepository<Messages, Long> {

}
