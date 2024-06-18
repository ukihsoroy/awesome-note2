package io.ukihsoroy.gatling.types;

/**
 *
 * @author K.O
 */
public enum EnableType {

    /**
     *
     */
    YES(1),
    NO(0);

    EnableType(Integer value) {
        this.value = value;
    }

    private Integer value;

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
