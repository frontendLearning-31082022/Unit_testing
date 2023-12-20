import org.example.Car;
import org.example.Vehicle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;


class CarTest {

    Car car=null;

    @BeforeEach
    public void setUp() {
        car=new Car("TestCompany","TestModel",2000);
    }

    @Test
    @DisplayName("carIsInstanceOfVehicle машина потомок класса техники")
    void carIsInstanceOfVehicle() {
        assertInstanceOf(Vehicle.class,car);
    }

    @Test
    @DisplayName("wheelsCountFour у машины 4 колеса")
    void wheelsCountFour() {
        assertThat(car.getNumWheels()).isEqualTo(4);
    }

    @Test
    @DisplayName("testDriveSpeedIs60 скорость в режиме testDrive равна 60")
    void testDriveSpeedIs60() {
        car.testDrive();
        assertThat(car.getSpeed()).isEqualTo(60);
    }

    @Test
    @DisplayName("parkAfterTestDriveHasZeroSpeed переход в режим парковки после testDrive " +
            "приводит к нулевой скорости трансСредства")
    void parkAfterTestDriveHasZeroSpeed() {
        car.testDrive();
        car.park();
        assertThat(car.getSpeed()).isEqualTo(0);
    }

}