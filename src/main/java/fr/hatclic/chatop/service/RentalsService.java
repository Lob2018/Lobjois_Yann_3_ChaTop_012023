package fr.hatclic.chatop.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import fr.hatclic.chatop.model.Rentals;
import fr.hatclic.chatop.repository.RentalsRepository;

public class RentalsService {

	@Autowired
	private RentalsRepository rentalsRepository;

	public Rentals createRental(Rentals rental) {
		return rentalsRepository.save(rental);
	}

	public Optional<Rentals> findRentalById(final Long id) {
		return rentalsRepository.findById(id);
	}

	public Iterable<Rentals> getAllRentals() {
		return rentalsRepository.findAll();
	}

	public Rentals updateRental(Rentals rental) {
		return rentalsRepository.save(rental);
	}

	public void deleteRentalById(final Long id) {
		rentalsRepository.deleteById(id);
	}

}
