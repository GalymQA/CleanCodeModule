import models.MilitaryType;
import planes.MilitaryPlane;
import planes.PassengerPlane;
import planes.Plane;
import java.util.Arrays;
import java.util.List;

public class Runner {

    private static List<Plane> planes = Arrays.asList(
            new PassengerPlane("Boeing-737",
                    900,
                    12_000,
                    60_500,
                    164),
            new PassengerPlane("Boeing-737-800",
                    940,
                    12_300,
                    63_870,
                    192),
            new PassengerPlane("Boeing-747",
                    980,
                    16_100,
                    70_500,
                    242),
            new PassengerPlane("Airbus A320",
                    930,
                    11_800,
                    65_500,
                    188),
            new PassengerPlane("Airbus A330",
                    990,
                    14_800,
                    80_500,
                    222),
            new PassengerPlane("Embraer 190",
                    870,
                    8_100,
                    30_800,
                    64),
            new PassengerPlane("Sukhoi Superjet 100",
                    870,
                    11_500,
                    50_500,
                    140),
            new PassengerPlane("Bombardier CS300",
                    920,
                    11_000,
                    60_700,
                    196),
            new MilitaryPlane("B-1B Lancer",
                    1_050,
                    21_000,
                    80_000,
                    MilitaryType.BOMBER),
            new MilitaryPlane("B-2 Spirit",
                    1_030,
                    22_000,
                    70_000,
                    MilitaryType.BOMBER),
            new MilitaryPlane("B-52 Stratofortress",
                    1_000,
                    20_000,
                    80_000,
                    MilitaryType.BOMBER),
            new MilitaryPlane("F-15",
                    1_500,
                    12_000,
                    10_000,
                    MilitaryType.FIGHTER),
            new MilitaryPlane("F-22",
                    1_550,
                    13_000,
                    11_000,
                    MilitaryType.FIGHTER),
            new MilitaryPlane("C-130 Hercules",
                    650,
                    5_000,
                    110_000,
                    MilitaryType.TRANSPORT)
    );

    public static void main(String[] args) {
        Airport airport = new Airport(planes);
        Airport militaryAirport = new Airport(airport.getMilitaryPlanes());
        Airport passengerAirport = new Airport(airport.getPassengerPlanes());
        System.out.println("Military airport sorted by max distance: " + militaryAirport
                .sortByMaxDistance()
                .toString());
        System.out.println("Passenger airport sorted by max speed: " + passengerAirport
                .sortByMaxSpeed()
                .toString());
        System.out.println("Plane with max passenger capacity: " +
                passengerAirport.getPassengerPlaneWithMaxPassengersCapacity());
    }

}
