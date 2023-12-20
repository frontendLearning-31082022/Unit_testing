import org.example.Motorcycle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MotorcycleTest {

    Motorcycle motorcycle=null;
    @BeforeEach
    public void setUp() {
        motorcycle=new Motorcycle("TestCompany","TestModel",2000);
    }

    @Test
    @DisplayName("wheelsCountTwo у мото 2 колеса")
    void wheelsCountTwo() {
        assertThat(motorcycle.getNumWheels()).isEqualTo(2);
    }

    @Test
    @DisplayName("testDriveSpeedIs75 скорость в режиме testDrive равна 75")
    void testDriveSpeedIs75() {
        motorcycle.testDrive();
        assertThat(motorcycle.getSpeed()).isEqualTo(75);
    }

    @Test
    @DisplayName("parkAfterTestDriveHasZeroSpeed переход в режим парковки после testDrive " +
            "приводит к нулевой скорости трансСредства")
    void parkAfterTestDriveHasZeroSpeed() {
        motorcycle.testDrive();
        motorcycle.park();
        assertThat(motorcycle.getSpeed()).isEqualTo(0);
    }

}