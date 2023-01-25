package fr.hatclic.chatop.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fr.hatclic.chatop.model.Rentals;
import fr.hatclic.chatop.repository.RentalsRepository;

@Service
public class RentalsService {

	@Autowired
	private RentalsRepository rentalsRepository;

	public Rentals createRental(Rentals rental) {
		if (rentalIsNull(rental))
			throw new Error();
		return rentalsRepository.save(rental);
	}

	public Optional<Rentals> findRentalById(final Long id) {
		if (id == null) {
			throw new Error();
		}
		return rentalsRepository.findById(id);
	}

	public Iterable<Rentals> getAllRentals() {
		return rentalsRepository.findAll();
	}

	public Rentals updateRental(Rentals rental) {
		if (rentalIsNull(rental) || rental.getId() == null)
			throw new Error();
		return rentalsRepository.save(rental);
	}

	public void deleteRentalById(final Long id) {
		if (id == null) {
			throw new Error();
		}
		rentalsRepository.deleteById(id);
	}

	public boolean rentalIsNull(Rentals rental) {
		if (rental == null || rental.getName().trim().length() == 0 || rental.getSurface() == 0.0
				|| rental.getPrice() == 0.0 || rental.getDescription().trim().length() == 0
				|| rental.getOwner_id() == null) {
			return true;
		}
		return false;
	}

}
