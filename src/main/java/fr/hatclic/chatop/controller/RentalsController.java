package fr.hatclic.chatop.controller;

import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import fr.hatclic.chatop.model.Rentals;
import fr.hatclic.chatop.service.RentalsService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/rentals")
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Rentals", description = "The rentals API. Contains all the operations that can be performed on a rental.")
public class RentalsController {

	@Autowired
	private RentalsService rentalsService;

	@GetMapping("")
	public Iterable<Rentals> getAll() {
		List<Rentals> rentalsList = (List<Rentals>) rentalsService.getAllRentals();
		return rentalsList.isEmpty() ? Collections.emptyList() : rentalsList;
	}

	@GetMapping("/{rentalId}")
	public Rentals get(@PathVariable("rentalId") Long id) {
		return rentalsService.findRentalById(id).get();
	}

	@PostMapping("")
	@ResponseBody
	public HashMap<String, String> create(@RequestParam("picture") MultipartFile picture,
			@RequestParam("name") String name, @RequestParam("surface") double surface,
			@RequestParam("description") String description, @RequestParam("owner_id") Long owner_id) {
		Rentals rental = new Rentals();
		final HashMap<String, String> map = new HashMap<>();
		try {
			rental.setName(name);
			rental.setSurface(surface);
			rental.setDescription(description);
			rental.setOwner_id(owner_id);
			rental.setCreated_at(ZonedDateTime.now());
			rental.setUpdated_at(ZonedDateTime.now());
			rental.setPicture(picture.getOriginalFilename());
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
