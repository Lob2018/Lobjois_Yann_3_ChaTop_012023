package fr.hatclic.chatop.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.hatclic.chatop.model.Users;

@Repository
public interface UsersRepository extends CrudRepository<Users, Long> {

}
