import java.util.ArrayList;

/**
 * Represents a Room from the University
 */
public class Room {
    // Attributes
    private String code;
    private double capacity;
    public static ArrayList<Room> roomArrayList = new ArrayList<>();

    // Methods

    /**
     *
     * @return The room capacity
     */
    public double getCapacity() {
        return capacity;
    }

    /**
     *  Set the Room capacity
     * @param capacity A double of the room capacity
     */
    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    /**
     *
     * @return The room code
     */
    public String getCode() {
        return code;
    }

    /**
     *
     * @return The ArrayList of Rooms
     */
    public static ArrayList<Room> getRoomArrayList(){
        return roomArrayList;
    }

    /**
     *
     * @return String : | room code | room capacity |
     */
    public String toString(){
        return
                "| "+code+" |"+" capacity: "+capacity+" |";
    }

    // Constructors

    /**
     * This constructor initializes a Room
     * @param code A room code
     * @param capacity The room capacity
     */
    public Room(String code, double capacity){
        if (capacity <= 0){
            // check negative inputs
            throw new IllegalArgumentException("Capacity must be > 0!");
        }
        if (roomArrayList != null) {
            for (Room room : roomArrayList) {
                if (room.getCode().equals(code)) {
                    // Check room code is unique
                    throw new IllegalArgumentException("This code is not unique!");
                }
            }
        }
        this.code = code;
        this.capacity = capacity;
    }
}
