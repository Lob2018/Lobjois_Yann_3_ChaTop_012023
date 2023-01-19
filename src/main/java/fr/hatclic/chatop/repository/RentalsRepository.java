package fr.hatclic.chatop.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.hatclic.chatop.model.Rentals;

@Repository
public interface RentalsRepository extends CrudRepository<Rentals, Long> {

}
