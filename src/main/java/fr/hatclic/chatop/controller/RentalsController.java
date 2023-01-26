
package fr.hatclic.chatop.controller;

import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import fr.hatclic.chatop.dto.RentalsDto;
import fr.hatclic.chatop.model.Rentals;
import fr.hatclic.chatop.model.Users;
import fr.hatclic.chatop.service.RentalsService;
import fr.hatclic.chatop.service.UsersService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/rentals")
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Rentals", description = "The rentals API. Contains all the operations that can be performed on a rental.")
public class RentalsController {

	@Autowired
	private RentalsService rentalsService;

	@Autowired
	private UsersService usersService;

	@Autowired
	private ModelMapper modelMapper;

	private final RentalsDto convertToDto(final Rentals rental) {
		final RentalsDto rentalDto = modelMapper.map(rental, RentalsDto.class);
		return rentalDto;
	}

	private final Rentals convertToEntity(final RentalsDto rentalDto) {
		final Rentals rental = modelMapper.map(rentalDto, Rentals.class);
		return rental;
	}

	/**
	 * Get all the rentals
	 * 
	 * @return The HTTP response
	 */
	@GetMapping("")
	public final ResponseEntity<Object> getAll() {
		try {
			final List<RentalsDto> rentalsDtoList = ((Collection<Rentals>) rentalsService.getAllRentals()).stream()
					.map(this::convertToDto).collect(Collectors.toList());
			final HashMap<String, List<RentalsDto>> map = new HashMap<>();
			map.put("rentals", rentalsDtoList);
			return ResponseEntity.ok().body(map);
		} catch (Error ex) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new HashMap<>());
		}
	}

	/**
	 * Get a rental by id
	 * 
	 * @param id The rental id
	 * @return The HTTP response
	 */
	@GetMapping("/{rentalId}")
	public final ResponseEntity<Object> get(@PathVariable("rentalId") Long id) {
		try {
			final RentalsDto rentalDto = convertToDto(rentalsService.findRentalById(id).get());
			return ResponseEntity.ok().body(rentalDto);
		} catch (Error ex) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new HashMap<>());
		}
	}

	/**
	 * Create a rental
	 * 
	 * @param picture     The rental picture (optional)
	 * @param name        The name of the rental
	 * @param surface     The surface of the rental
	 * @param price       The price of the rental
	 * @param description The description of the rental
	 * @return The HTTP response
	 */
	@PostMapping("/**")
	@ResponseBody
	public final ResponseEntity<Object> create(@RequestParam(required = false) MultipartFile picture,
			@RequestParam("name") String name, @RequestParam("surface") double surface,
			@RequestParam("price") double price, @RequestParam("description") String description) {
		final String mail = SecurityContextHolder.getContext().getAuthentication().getName();
		final Optional<Users> user = usersService.findByEmail(mail);
		try {
			final RentalsDto rental = new RentalsDto();
			rental.setRentalsDto(null, name, surface, price, picture.getOriginalFilename(), description,
					user.get().getId(), ZonedDateTime.now(), ZonedDateTime.now());
			rentalsService.createRental(convertToEntity(rental));
			final HashMap<String, String> map = new HashMap<>();
			map.put("message", "Rental created !");
			return ResponseEntity.ok().body(map);
		} catch (Error ex) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new HashMap<>());
		}
	}

	/**
	 * Update a rental
	 * 
	 * @param id          The rental id
	 * @param token       The user token
	 * @param picture     The rental picture (optional)
	 * @param name        The name of the rental
	 * @param surface     The surface of the rental
	 * @param price       The price of the rental
	 * @param description The description of the rental
	 * @return The HTTP response
	 */
	@PutMapping("/{rentalId}")
	@ResponseBody
	public final ResponseEntity<Object> put(@PathVariable("rentalId") Long id,
			@RequestParam(required = false) MultipartFile picture, @RequestParam("name") String name,
			@RequestParam("surface") double surface, @RequestParam("price") double price,
			@RequestParam("description") String description) {
		final String mail = SecurityContextHolder.getContext().getAuthentication().getName();
		final Optional<Users> user = usersService.findByEmail(mail);
		try {
			final RentalsDto rentalDto = convertToDto(rentalsService.findRentalById(id).get());
			rentalDto.setRentalsDto(id, name, surface, price, picture == null ? null : picture.getOriginalFilename(),
					description, user.get().getId(), ZonedDateTime.now(), ZonedDateTime.now());
			rentalsService.updateRental(convertToEntity(rentalDto));
			final HashMap<String, String> map = new HashMap<>();
			map.put("message", "Rental updated !");
			return ResponseEntity.ok().body(map);
		} catch (Error ex) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new HashMap<>());
		}
	}
}
