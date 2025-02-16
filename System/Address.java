package System;
/**
 * The Address class represents a physical address with a street, avenue, and optional subunits.
 */
public class Address {
    private int street;
    private int avenue;
    private int[] subUnits;

    public Address(int street, int avenue, int... subUnits) {
        this.street = street;
        this.avenue = avenue;
        this.subUnits = subUnits;
    }

    // Getters and Setters
    public int getStreet() { return street; }
    public int getAvenue() { return avenue; }
    public int[] getSubUnits() { return subUnits; }
    /**
     * Checks if this address is a sub-address of another address.
     *
     * @param other the other address to compare with
     * @return true if this address is a sub-address of the other address, false otherwise
     */
    public boolean isSubAddressOf(Address other) {
        if (this.street != other.street || this.avenue != other.avenue) return false;
        if (this.subUnits.length <= other.subUnits.length) return false;
        for (int i = 0; i < other.subUnits.length; i++) {
            if (this.subUnits[i] != other.subUnits[i]) return false;
        }
        return true;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("(" + street + "," + avenue);
        for (int unit : subUnits) {
            sb.append(",").append(unit);
        }
        sb.append(")");
        return sb.toString();
    }
}
