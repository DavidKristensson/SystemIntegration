package Server;

import java.util.Random;

public class GreenhouseSimulator {
    private int temperature;
    private int humidity;
    private int lumen;
    private int energyConsumption;

    public int getEnergyConsumption() {
        return energyConsumption;
    }

    public int getHumidity() {
        return humidity;
    }

    public int getLumen() {
        return lumen;
    }

    public int getTemperature() {
        return temperature;
    }

    public void simulateReadings(){
        Random rand = new Random();
        temperature = rand.nextInt(100);
        humidity = rand.nextInt(100);
        lumen = rand.nextInt(100);
        energyConsumption = (temperature + humidity + lumen) / 3;
    }

    GreenhouseSimulator(){
        simulateReadings();
    }
}
