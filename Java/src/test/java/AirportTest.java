import planes.ExperimentalPlane;
import models.ClassificationLevel;
import models.ExperimentalTypes;
import models.MilitaryType;
import org.testng.Assert;
import org.testng.annotations.Test;
import planes.MilitaryPlane;
import planes.PassengerPlane;
import planes.Plane;
import java.util.Arrays;
import java.util.List;

public class AirportTest {

    private static final List<Plane> planes = Arrays.asList(
            new PassengerPlane(
                    "Boeing-737",
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
                    MilitaryType.TRANSPORT),
            new ExperimentalPlane("Bell X-14",
                    277,
                    482,
                    500,
                    ExperimentalTypes.HIGH_ALTITUDE,
                    ClassificationLevel.SECRET),
            new ExperimentalPlane("Ryan X-13 Vertijet",
                    560,
                    307,
                    500,
                    ExperimentalTypes.VTOL,
                    ClassificationLevel.TOP_SECRET)
    );

    private static final PassengerPlane planeWithMaxPassengerCapacity = new PassengerPlane("Boeing-747",
            980,
            16100,
            70500,
            242);

    @Test
    public void testGetTransportMilitaryPlanes() {
        Airport airport = new Airport(planes);
        List<MilitaryPlane> transportMilitaryPlanes = airport.getTransportMilitaryPlanes();
        boolean hasAtLeastOneMilitaryTypeOfTransport = false;
        for (MilitaryPlane militaryPlane : transportMilitaryPlanes) {
            if (militaryPlane.getType() == MilitaryType.TRANSPORT) {
                hasAtLeastOneMilitaryTypeOfTransport = true;
                break;
            }
        }
        Assert.assertTrue(hasAtLeastOneMilitaryTypeOfTransport);
    }

    @Test
    public void testGetPassengerPlaneWithMaxCapacity() {
        Airport airport = new Airport(planes);
        PassengerPlane expectedPlaneWithMaxPassengersCapacity = airport.getPassengerPlaneWithMaxPassengersCapacity();
        Assert.assertEquals(planeWithMaxPassengerCapacity, expectedPlaneWithMaxPassengersCapacity);
    }

    @Test
    public void testSortByMaxLoadCapacity() {
        Airport airport = new Airport(planes);
        airport.sortByMaxLoadCapacity();
        List<? extends Plane> planesSortedByMaxLoadCapacity = airport.getPlanes();
        boolean nextPlaneMaxLoadCapacityIsHigherThanCurrent = true;
        for (int i = 0; i < planesSortedByMaxLoadCapacity.size() - 1; i++) {
            if (planesSortedByMaxLoadCapacity.get(i).getMaxLoadCapacity() >
                    planesSortedByMaxLoadCapacity.get(i + 1).getMaxLoadCapacity()) {
                nextPlaneMaxLoadCapacityIsHigherThanCurrent = false;
                break;
            }
        }
        Assert.assertTrue(nextPlaneMaxLoadCapacityIsHigherThanCurrent);
    }

    @Test
    public void testHasAtLeastOneBomberInMilitaryPlanes() {
        Airport airport = new Airport(planes);
        List<MilitaryPlane> bomberMilitaryPlanes = airport.getBomberMilitaryPlanes();
        boolean hasAtLeastOneBomberInMilitaryPlanes = false;
        for (MilitaryPlane militaryPlane : bomberMilitaryPlanes) {
            if (militaryPlane.getType() == MilitaryType.BOMBER) {
                hasAtLeastOneBomberInMilitaryPlanes = true;
                break;
            }
        }
        Assert.assertTrue(hasAtLeastOneBomberInMilitaryPlanes);
    }

    @Test
    public void testExperimentalPlanesHasClassificationLevelHigherThanUnclassified() {
        Airport airport = new Airport(planes);
        List<ExperimentalPlane> experimentalPlanes = airport.getExperimentalPlanes();
        boolean hasUnclassifiedPlanes = false;
        for (ExperimentalPlane experimentalPlane : experimentalPlanes) {
            if (experimentalPlane.getClassificationLevel() == ClassificationLevel.UNCLASSIFIED) {
                hasUnclassifiedPlanes = true;
                break;
            }
        }
        Assert.assertFalse(hasUnclassifiedPlanes);
    }

}