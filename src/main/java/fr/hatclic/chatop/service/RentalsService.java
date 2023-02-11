package fr.hatclic.chatop.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import fr.hatclic.chatop.model.Rentals;
import fr.hatclic.chatop.repository.RentalsRepository;

@Service
public class RentalsService {

	@Autowired
	private RentalsRepository rentalsRepository;

	@Value("${chatop.service.local.httpserver.path}")
	private String httpserverPath;

	@Value("${chatop.service.default.picture}")
	private String defaultPicture;

	// The user's environment variable
	@Value("${CHATOP_YL_LOCAL_PATH_FOLDER}")
	private String folderPath;

	public final Rentals createRental(final Rentals rental, MultipartFile picture) throws IOException {
		if (rentalIsNull(rental))
			throw new Error();
		if (picture == null) {
			rental.setPicture(defaultPicture);
			return rentalsRepository.save(rental);
		}
		if (rental.getPicture().trim().length() != 0 && isAPitcure(picture)) {
			final String path = localPiturePath(picture);
			if (path != null)
				rental.setPicture(path);
		} else {
			throw new Error();
		}
		return rentalsRepository.save(rental);
	}

	public final Optional<Rentals> findRentalById(final Long id) {
		if (id == null) {
			throw new Error();
		}
		return rentalsRepository.findById(id);
	}

	public final Iterable<Rentals> getAllRentals() {
		return rentalsRepository.findAll();
	}

	public final Rentals updateRental(final Rentals rental, MultipartFile picture) throws IOException {
		if (rentalIsNull(rental) || rental.getId() == null)
			throw new Error();
		if (picture == null) {
			rental.setPicture(defaultPicture);
			return rentalsRepository.save(rental);
		}
		if (rental.getPicture().trim().length() != 0 && isAPitcure(picture)) {
			final String path = localPiturePath(picture);
			if (path != null)
				rental.setPicture(path);
		} else {
			throw new Error();
		}
		return rentalsRepository.save(rental);
	}

	public final void deleteRentalById(final Long id) {
		if (id == null) {
			throw new Error();
		}
		rentalsRepository.deleteById(id);
	}

	public final boolean rentalIsNull(final Rentals rental) {
		if (rental == null || rental.getName().trim().length() == 0 || rental.getSurface() == 0.0
				|| rental.getPrice() == 0.0 || rental.getDescription().trim().length() == 0
				|| rental.getOwner_id() == null) {
			return true;
		}
		return false;
	}

	public final boolean isAPitcure(final MultipartFile picture) {
		final String mimeType = picture.getContentType();
		if (mimeType != null && (mimeType.equals("image/png") || mimeType.equals("image/jpeg"))) {
			return true;
		}

		return false;
	}

	public final String localPiturePath(final MultipartFile picture) throws IOException {
		File path = new File(folderPath + "/" + picture.getOriginalFilename());
		path.createNewFile();
		FileOutputStream output = new FileOutputStream(path);
		output.write(picture.getBytes());
		output.close();
		return httpserverPath + picture.getOriginalFilename();
	}

}
