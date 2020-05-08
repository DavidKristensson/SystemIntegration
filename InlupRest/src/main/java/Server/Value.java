package Server;

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

    Value(){}

    Value(int value, ValueType valueType){
        this.value = value;
        this.valueType = valueType;
    }
}
