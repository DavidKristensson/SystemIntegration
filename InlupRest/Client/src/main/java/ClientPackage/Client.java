package ClientPackage;

import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Client {
    private static void getCurrentValue(String value){
        final String uri = "http://localhost:8080/{value}/current";
        Map<String, String> params = new HashMap<>();
        params.put("value", value);
        RestTemplate restTemplate = new RestTemplate();
        Value valueObj = restTemplate.getForObject(uri, Value.class, params);
        if(valueObj.getValueType() == Value.ValueType.TEMPERATURE){
            System.out.println("Current temperature is: " + valueObj.getValue());
        }
        else if(valueObj.getValueType() == Value.ValueType.HUMIDITY){
            System.out.println("Current humidity is: " + valueObj.getValue());
        }
        else if(valueObj.getValueType() == Value.ValueType.LUMEN){
            System.out.println("Current lumen is: " + valueObj.getValue());
        }
        else if(valueObj.getValueType() == Value.ValueType.ENERGY_CONSUMPTION){
            System.out.println("Current energy consumption is: " + valueObj.getValue());
        }
        else{
            System.out.println("Command not understood"); //Kan göras bättre, skickar iväg till servern för att returna dålig input
        }
    }

    private static void getCurrentTemperature(){
        final String uri = "http://localhost:8080/current/temperature";
        RestTemplate restTemplate = new RestTemplate();
        String temperatureString = restTemplate.getForObject(uri, String.class);
        int temperature = Integer.parseInt(temperatureString);
        System.out.println("Current temperature is: " + temperature);
    }

    private static void getCurrentHumidity(){
        final String uri = "http://localhost:8080/current/humidity";
        RestTemplate restTemplate = new RestTemplate();
        String humidityString = restTemplate.getForObject(uri, String.class);
        int humidity = Integer.parseInt(humidityString);
        System.out.println("Current humidity is: " + humidity);
    }

    private static void getCurrentLumen(){
        final String uri = "http://localhost:8080/current/lumen";
        RestTemplate restTemplate = new RestTemplate();
        String lumenString = restTemplate.getForObject(uri, String.class);
        int lumen = Integer.parseInt(lumenString);
        System.out.println("Current lumen is: " + lumen);
    }

    private static void getCurrentEnergyConsumption(){
        final String uri = "http://localhost:8080/current/energyconsumption";
        RestTemplate restTemplate = new RestTemplate();
        String energyConsumptionString = restTemplate.getForObject(uri, String.class);
        int energyConsumption = Integer.parseInt(energyConsumptionString);
        System.out.println("Current energy consumption is: " + energyConsumption);
    }

    private static void getCurrentReport(){
        final String uri = "http://localhost:8080/current/report";
        RestTemplate restTemplate = new RestTemplate();
        Report report = restTemplate.getForObject(uri, Report.class);
        System.out.println("Current temperature is: " + report.getTemperature()
                + ", Current humidity is: " + report.getHumidity()
                + ", Current lumen is: " + report.getLumen());
    }

    private static void simulateNewReadings(){
        final String uri = "http://localhost:8080/update";
        RestTemplate restTemplate = new RestTemplate();
        Report report = restTemplate.getForObject(uri, Report.class);
        System.out.println("Updated temperature is: " + report.getTemperature()
                + ", Updated humidity is: " + report.getHumidity()
                + ", Updated lumen is: " + report.getLumen());
    }

    private static void getWeekReportAverage(String value){
        final String uri = "http://localhost:8080/report/{value}";
        Map<String, String> params = new HashMap<>();
        params.put("value", value);
        RestTemplate restTemplate = new RestTemplate();
        Report report = restTemplate.getForObject(uri, Report.class, value);

        if(report.getValueType() == Report.ValueType.TEMPERATURE){
            int counter = 1, sum = 0;
            float average;
            for(int temp : report.getTemperatureList()){
                System.out.println("Temperature day " + counter + ": " + temp);
                sum += temp;
                counter++;
            }
            average = sum / counter;
            System.out.println("Average from entire week: " + average);
        }
        else if(report.getValueType() == Report.ValueType.HUMIDITY){
            int counter = 1, sum = 0;
            float average;
            for(int humidity : report.getHumidityList()){
                System.out.println("Humidity day " + counter + ": " + humidity);
                sum += humidity;
                counter++;
            }
            average = sum / counter;
            System.out.println("Average from entire week: " + average);
        }
        else if(report.getValueType() == Report.ValueType.LUMEN){
            int counter = 1, sum = 0;
            float average;
            for(int lumen : report.getLumenList()){
                System.out.println("Temperature day " + counter + ": " + lumen);
                sum += lumen;
                counter++;
            }
            average = sum / counter;
            System.out.println("Average from entire week: " + average);
        }
        else if(report.getValueType() == Report.ValueType.ENERGY_CONSUMPTION){
            int sum = 0;
            for(int ec : report.getEnergyConsumptionList()){
                sum += ec;
            }
            Scanner sc = new Scanner(System.in);
            System.out.println("Kw/h price: ");
            int krPerKwh = sc.nextInt();
            sc.nextLine();
            int totalCost = sum * krPerKwh;
            System.out.println("Electrical cost for the entire week: " + totalCost);
        }
        else{
            System.out.println("Command not understood"); //Kan göras bättre, skickar iväg till servern för att returna dålig input
        }
    }

    private static void manuallyLogCurrentValues(){
        final String uri = "http://localhost:8080//current/log";
        RestTemplate restTemplate = new RestTemplate();
        String report = restTemplate.getForObject(uri, String.class);
        System.out.println(report);
    }


    Client(){
        while(true){
            System.out.println("****************************************");
            System.out.println("Greenhouse console");
            System.out.println("****************************************");
            System.out.println("1. Current specific value from greenhouse");
            System.out.println("2. Current report of all greenhouse values");
            System.out.println("3. Update values from green house");
            System.out.println("4. Energy Consumption last 24/7 hours");
            System.out.println("5. Report of whole weeks values and average");
            System.out.println("--------------------------------------");
            System.out.println("6. Manually log current greenhouse values to database");
            System.out.println("--------------------------------------");


            Scanner sc = new Scanner(System.in);

            int choice = sc.nextInt();
            sc.nextLine();

            if(choice == 1){
                System.out.println("Do you want current temperature, humidity, lumen or energy consumption?");
                String input = sc.nextLine();
                getCurrentValue(input);
            }
            else if(choice == 2){
                getCurrentReport();
            }
            else if(choice == 3){
                simulateNewReadings();
            }
            else if(choice == 4){
                getCurrentEnergyConsumption();
            }
            else if(choice == 5){
                System.out.println("For temperature, humidity or lumen?");
                String input = sc.nextLine();
                getWeekReportAverage(input);
            }
            else if(choice == 6){
                manuallyLogCurrentValues();
            }
            else if(choice == 7){
                getWeekReportAverage("energyconsumption");
            }
            else if(choice == 8){
            }
            else if(choice == 9){
                System.out.println("Do you want current temperature, humidity, lumen or energy consumption?");
                String input = sc.nextLine();
                getCurrentValue(input);
            }
        }
    }
}
