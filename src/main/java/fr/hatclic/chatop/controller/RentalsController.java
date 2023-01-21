package fr.hatclic.chatop.controller;

import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import fr.hatclic.chatop.model.Rentals;
import fr.hatclic.chatop.model.Users;
import fr.hatclic.chatop.service.RentalsService;

@RestController
@RequestMapping("/api/rentals")
public class RentalsController {

	@Autowired
	private RentalsService rentalsService;

	@GetMapping("")
	public Iterable<Rentals> getAll() {
		System.out.println("rentals");
		List<Rentals> rentalsList = (List<Rentals>) rentalsService.getAllRentals();
		return rentalsList.isEmpty() ? Collections.emptyList() : rentalsList;
	}

	@GetMapping("/{rentalId}")
	public Rentals get(@PathVariable("rentalId") Long id) {
		return rentalsService.findRentalById(id).get();
	}

	@PostMapping("")
	@ResponseBody
	public HashMap<String, String> create(@ModelAttribute Rentals rental) {
		final HashMap<String, String> map = new HashMap<>();
		try {
			rental.setCreated_at(ZonedDateTime.now());
			rental.setUpdated_at(ZonedDateTime.now());
			rentalsService.createRental(rental);
			map.put("token", "jwt");
			return map;
//			return ResponseEntity.ok(map.toString());
		} catch (Error ex) {
			return map;
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	@PutMapping("/{rentalId}")
	@ResponseBody
	public HashMap<String, String> put(@PathVariable("rentalId") Long id, @ModelAttribute Rentals rental) {
		final HashMap<String, String> map = new HashMap<>();
		rental.setId(id);
		rental.setUpdated_at(ZonedDateTime.now());
		try {
			rentalsService.updateRental(rental);
			map.put("token", "jwt");
			return map;
		} catch (Error e) {
			map.put("message", "error");
			return map;
		}

	}
}
