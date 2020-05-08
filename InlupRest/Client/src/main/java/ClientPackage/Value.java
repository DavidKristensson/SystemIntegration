package ClientPackage;

public class Value {
    private int value;
    private ValueType valueType;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    enum ValueType{
        TEMPERATURE,
        HUMIDITY,
        LUMEN,
        ENERGY_CONSUMPTION
    }

    public ValueType getValueType(){
        return valueType;
    }
}
