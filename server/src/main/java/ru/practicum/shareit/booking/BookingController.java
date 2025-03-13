package ru.practicum.shareit.booking;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.shareit.booking.dto.BookingDto;
import ru.practicum.shareit.booking.dto.CreateBookingDto;
import ru.practicum.shareit.booking.model.BookingState;

import java.util.List;

import static ru.practicum.shareit.Constants.HEADER_USER_PARAMETER;

@RestController
@RequestMapping("/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    BookingDto createBooking(
            @RequestHeader(HEADER_USER_PARAMETER) long bookerId,
            @RequestBody CreateBookingDto dto) {
        return bookingService.create(dto, bookerId);
    }

    @PatchMapping("/{bookingId}")
    BookingDto processBooking(@RequestHeader(HEADER_USER_PARAMETER) Long userId,
                              @PathVariable Long bookingId,
                              @RequestParam Boolean approved) {
        return bookingService.processBooking(userId, bookingId, approved);
    }

    @GetMapping("/{bookingId}")
    BookingDto findById(@RequestHeader(HEADER_USER_PARAMETER) Long userId,
                        @PathVariable Long bookingId) {
        return bookingService.findById(bookingId, userId);
    }

    @GetMapping
    List<BookingDto> findByBookerAndState(@RequestHeader(HEADER_USER_PARAMETER) Long bookerId,
                                          @RequestParam(defaultValue = "ALL") BookingState state) {
        return bookingService.findByBookerAndState(bookerId, state);
    }

    @GetMapping("/owner")
    List<BookingDto> findByOwnerAndState(@RequestHeader(HEADER_USER_PARAMETER) Long ownerId,
                                         @RequestParam(defaultValue = "ALL") BookingState state) {

        return bookingService.findByOwnerAndState(ownerId, state);
    }

}
