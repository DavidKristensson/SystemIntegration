package Server;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class DbService {
    GreenhouseSimulator greenhouseSimulator = new GreenhouseSimulator();

    public int getCurrentTemperature() {
        return greenhouseSimulator.getTemperature();
    }

    public int getCurrentHumidity() {
        return greenhouseSimulator.getHumidity();
    }

    public int getCurrentLumen() {
        return greenhouseSimulator.getLumen();
    }

    public int getCurrentEnergyConsumption() {
        return greenhouseSimulator.getEnergyConsumption();
    }

    public Report getCurrentReport() {
        Report report = new Report(
                greenhouseSimulator.getTemperature(),
                greenhouseSimulator.getHumidity(),
                greenhouseSimulator.getLumen());
        return report;
    }

    public Report simulateNewReadings() {
        greenhouseSimulator.simulateReadings();
        Report report = new Report(
                greenhouseSimulator.getTemperature(),
                greenhouseSimulator.getHumidity(),
                greenhouseSimulator.getLumen(),
                greenhouseSimulator.getEnergyConsumption());
        return report;
    }

    public Report valueWeekAverage(String value) throws IOException, ClassNotFoundException {
        Report report = new Report();
        Properties p = new Properties();
        p.load(new FileInputStream("C:\\Users\\User123\\OneDrive\\SystemIntegration\\InlupRest\\src\\main\\java\\Server\\settings.properties"));

        Class.forName("com.mysql.cj.jdbc.Driver");
        ResultSet rs = null;

        try (Connection con = DriverManager.getConnection(
                p.getProperty("connectionString"),
                p.getProperty("username"),
                p.getProperty("password"));
             PreparedStatement stmt = con.prepareStatement(
                     "select " + value + " from climatecentral order by created desc limit 7;")) {

            rs = stmt.executeQuery();

            if (value.equalsIgnoreCase("temperature")) {
                report.setValueType(Report.ValueType.TEMPERATURE);
                while (rs.next()) {
                    int dbValue = rs.getInt(value);
                    report.appendTemperatures(dbValue);
                }
            } else if (value.equalsIgnoreCase("humidity")) {
                report.setValueType(Report.ValueType.HUMIDITY);
                while (rs.next()) {
                    int dbValue = rs.getInt(value);
                    report.appendHumidity(dbValue);
                }
            } else if (value.equalsIgnoreCase("lumen")) {
                report.setValueType(Report.ValueType.LUMEN);
                while (rs.next()) {
                    int dbValue = rs.getInt(value);
                    report.appendLumen(dbValue);
                }
            } else if (value.equalsIgnoreCase("energyconsumption")) {
                report.setValueType(Report.ValueType.ENERGY_CONSUMPTION);
                while (rs.next()) {
                    int dbValue = rs.getInt(value);
                    report.appendEnergyConsumption(dbValue);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(report.getTemperatureList());
        System.out.println(report.getValueType());
        return report;
    }

    public Value getCurrentValue(String value) {
        if (value.equalsIgnoreCase("temperature")) {
            return new Value(greenhouseSimulator.getTemperature(), Value.ValueType.TEMPERATURE);
        } else if (value.equalsIgnoreCase("humidity")) {
            return new Value(greenhouseSimulator.getHumidity(), Value.ValueType.HUMIDITY);
        } else if (value.equalsIgnoreCase("lumen")) {
            return new Value(greenhouseSimulator.getLumen(), Value.ValueType.LUMEN);
        } else if (value.equalsIgnoreCase("energyconsumption")) {
            return new Value(greenhouseSimulator.getEnergyConsumption(), Value.ValueType.ENERGY_CONSUMPTION);
        } else {
            return new Value();
        }
    }

    public String logCurrentValues() throws IOException, ClassNotFoundException {
        Properties p = new Properties();
        p.load(new FileInputStream("C:\\Users\\User123\\OneDrive\\SystemIntegration\\InlupRest\\src\\main\\java\\Server\\settings.properties"));

        Class.forName("com.mysql.cj.jdbc.Driver");

        try (Connection con = DriverManager.getConnection(
                p.getProperty("connectionString"),
                p.getProperty("username"),
                p.getProperty("password"));
             PreparedStatement stmt = con.prepareStatement(
                     "INSERT INTO climatecentral(temperature, humidity, lumen, energyConsumption, manualInput) " +
                             "VALUES (?, ?, ?, ?, ?)")) {
            stmt.setInt(1, greenhouseSimulator.getTemperature());
            stmt.setInt(2, greenhouseSimulator.getHumidity());
            stmt.setInt(3, greenhouseSimulator.getLumen());
            stmt.setInt(4, greenhouseSimulator.getEnergyConsumption());
            stmt.setInt(5, 1);
            stmt.executeUpdate();
            return "Logging successful";

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Logging unsuccessful";
    }
}
