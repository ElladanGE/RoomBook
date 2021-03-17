/**
 * Represents a BookableRoom from the University
 */
public class BookableRoom {

    // Attributes

    private Room room;
    private String timeSlot;
    private int occupancy = 0;
    enum Status {EMPTY, AVAILABLE, FULL}
    private Status status;

    // Methods

    /**
     *
     * @return BookableRoom time slot : dd/mm/yyyy HH:MM
     */
    public String getTimeSlot() {
        return timeSlot;
    }

    /**
     * Increase occupancy of a BookableRoom, and change status:
     * If max capacity reached : FULL
     * Otherwise : AVAILABLE
     */
    public void incrOccupancy() {
        if(status == Status.FULL){
            // If Room is full throw exception
            throw new IllegalArgumentException("Maximum occupancy reached!");
        }
        this.occupancy++;
        if (occupancy == getRoom().getCapacity()){
            status = Status.FULL;
        }
        else{
            status = Status.AVAILABLE;
        }
    }

    /**
     * Decrease occupancy of this BookableRoom, and change status:
     * If occupancy is 0 : EMPTY
     * Otherwise : AVAILABLE
     */
    public void decrOccupancy(){
        if(occupancy == 0){
            throw new IllegalArgumentException("Occupancy already at 0!");
        }
        this.occupancy--;
        if (occupancy == 0){
            status = Status.EMPTY;
        }
        else{status = Status.AVAILABLE;}
    }

    /**
     *
     * @return Occupancy of this BookableRoom
     */
    public int getOccupancy() {
        return occupancy;
    }

    /**
     *
     * @param status Set the status of this BookableRoom : EMPTY, AVAILABLE, FULL
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     *
     * @return Status of this BookableRoom : EMPTY, AVAILABLE, FULL
     */
    public Status getStatus() {
        return status;
    }

    /**
     *
     * @return Room object
     */
    public Room getRoom() {
        return room;
    }

    /**
     *
     * @return String : | timeSlot | status | roomCode | occupancy |
     */
    public String toString() {
        return "| "+timeSlot+" | "+status+" | "+room.getCode()+" | "+occupancy+" |";
    }

    // Constructor

    /**
     * This constructor initializes a BookableRoom object
     * @param room A room object
     * @param timeSlot A timeslot : dd/mm/yyyy HH:MM
     */
    public BookableRoom(Room room, String timeSlot){
        if (occupancy >= room.getCapacity()){
            throw new IllegalArgumentException("Room capacity is full");
        }
        this.room = room;
        this.timeSlot = timeSlot;
    }
}
