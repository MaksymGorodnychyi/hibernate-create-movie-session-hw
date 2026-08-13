package mate.academy;

import java.time.LocalDate;
import java.time.LocalDateTime;
import mate.academy.lib.Injector;
import mate.academy.model.CinemaHall;
import mate.academy.model.Movie;
import mate.academy.model.MovieSession;
import mate.academy.service.CinemaHallService;
import mate.academy.service.MovieService;
import mate.academy.service.MovieSessionService;

public class Main {
    private static final Injector injector = Injector.getInstance("mate.academy");

    public static void main(String[] args) {
        MovieService movieService =
                (MovieService) injector.getInstance(MovieService.class);
        Movie fastAndFurious = new Movie("Fast and Furious");
        fastAndFurious.setDescription(
                "An action film about street racing, heists, and spies."
        );
        movieService.add(fastAndFurious);

        Movie avatar = new Movie("Avatar");
        avatar.setDescription(
                "A science fiction film about the world of Pandora."
        );
        movieService.add(avatar);

        System.out.println("Get movie by id:");
        System.out.println(movieService.get(fastAndFurious.getId()));

        System.out.println("All movies:");
        movieService.getAll().forEach(System.out::println);

        CinemaHallService cinemaHallService =
                (CinemaHallService) injector.getInstance(CinemaHallService.class);
        CinemaHall firstHall = new CinemaHall();
        firstHall.setCapacity(100);
        firstHall.setDescription("Big cinema hall");
        cinemaHallService.add(firstHall);

        CinemaHall secondHall = new CinemaHall();
        secondHall.setCapacity(50);
        secondHall.setDescription("Small cinema hall");
        cinemaHallService.add(secondHall);

        System.out.println("Get cinema hall by id:");
        System.out.println(cinemaHallService.get(firstHall.getId()));

        System.out.println("All cinema halls:");
        cinemaHallService.getAll().forEach(System.out::println);

        final MovieSessionService movieSessionService =
                (MovieSessionService) injector.getInstance(MovieSessionService.class);

        MovieSession firstSession = new MovieSession();
        firstSession.setMovie(fastAndFurious);
        firstSession.setCinemaHall(firstHall);
        firstSession.setShowTime(
                LocalDateTime.of(2026, 8, 15, 19, 0)
        );
        movieSessionService.add(firstSession);

        MovieSession secondSession = new MovieSession();
        secondSession.setMovie(fastAndFurious);
        secondSession.setCinemaHall(secondHall);
        secondSession.setShowTime(
                LocalDateTime.of(2026, 8, 16, 19, 0)
        );
        movieSessionService.add(secondSession);

        MovieSession thirdSession = new MovieSession();
        thirdSession.setMovie(avatar);
        thirdSession.setCinemaHall(firstHall);
        thirdSession.setShowTime(
                LocalDateTime.of(2026, 8, 15, 21, 0)
        );
        movieSessionService.add(thirdSession);

        System.out.println(
                "Fast and Furious sessions on August 15:"
        );

        movieSessionService.findAvailableSessions(
                fastAndFurious.getId(),
                LocalDate.of(2026, 8, 15)
        ).forEach(System.out::println);
    }
}
