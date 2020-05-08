package ClientPackage;

import java.io.Serializable;
import java.util.ArrayList;

public class Report implements Serializable {
    private int temperature;
    private int humidity;
    private int lumen;
    private int energyConsumption;
    private ArrayList<Integer> temperatureList = new ArrayList();
    private ArrayList<Integer> humidityList = new ArrayList();
    private ArrayList<Integer> lumenList = new ArrayList();
    private ArrayList<Integer> energyConsumptionList = new ArrayList();
    ValueType valueType;

    public ArrayList<Integer> getTemperatureList() {
        return temperatureList;
    }

    public ArrayList<Integer> getHumidityList() {
        return humidityList;
    }

    public ArrayList<Integer> getLumenList() {
        return lumenList;
    }

    public ArrayList<Integer> setLumenList(ArrayList<Integer> lumenList) {
        return this.lumenList = lumenList;
    }

    public ArrayList<Integer> setHumidityList(ArrayList<Integer> humidityList) {
        return this.humidityList = humidityList;
    }

    public ArrayList<Integer> setTemperatureList(ArrayList<Integer> temperatureList) {
        return this.temperatureList = temperatureList;
    }

    public ArrayList<Integer> getEnergyConsumptionList() {
        return energyConsumptionList;
    }

    public void setEnergyConsumptionList(ArrayList<Integer> energyConsumptionList) {
        this.energyConsumptionList = energyConsumptionList;
    }

    enum ValueType{
        TEMPERATURE,
        HUMIDITY,
        LUMEN,
        ENERGY_CONSUMPTION
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public int getLumen() {
        return lumen;
    }

    public void setLumen(int lumen) {
        this.lumen = lumen;
    }

    public int getEnergyConsumption() {
        return energyConsumption;
    }

    public void setEnergyConsumption(int energyConsumption) {
        this.energyConsumption = energyConsumption;
    }

    public ValueType getValueType(){
        return valueType;
    }

    public ValueType setValueType(ValueType valueType){
        return this.valueType = valueType;
    }


    Report(){}
}
