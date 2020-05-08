package Server.Controller;

import Server.DbService;
import Server.Report;
import Server.Value;

import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@RestController
public class ControllerClass {
    DbService dbService = new DbService();

    @RequestMapping("/current/temperature")
    public String getCurrentTemperature(){
        return Integer.toString(dbService.getCurrentTemperature()); }

    @RequestMapping("/current/humidity")
    public String getCurrentHumidity(){
        return Integer.toString(dbService.getCurrentHumidity());
    }

    @RequestMapping("/current/lumen")
    public String getCurrentLumen(){ return Integer.toString(dbService.getCurrentLumen()); }

    @RequestMapping("/current/energyconsumption")
    public String getCurrentEnergyConsumption(){
        return Integer.toString(dbService.getCurrentEnergyConsumption());
    }

    @RequestMapping("/current/report")
    public Report getCurrentReport(){ return dbService.getCurrentReport(); }

    @RequestMapping("/update")
    public Report simulateNewReadings(){ return dbService.simulateNewReadings(); }

    @GetMapping("/report/{value}")
    public Report valueWeekAverage(@PathVariable String value) throws IOException, ClassNotFoundException {return dbService.valueWeekAverage(value);}

    @GetMapping("/{value}/current")
    public Value getCurrentValue(@PathVariable String value){return dbService.getCurrentValue(value);}

    @RequestMapping("/current/log")
    public String logCurrentValues() throws IOException, ClassNotFoundException { return dbService.logCurrentValues(); }
}
