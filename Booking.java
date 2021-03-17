/**
 * Represents a Booking object
 */
public class Booking {

    // Attribute

    private BookableRoom room;
    private AssistantOnShift assistant;
    private String timeSlot;
    private String email;

    /**
     * Status of a Booking : SCHEDULED, COMPLETED
     */
    public enum Status {SCHEDULED, COMPLETED}
    private Status status;

    // Methods

    /**
     *
     * @return Status of this Booking : SCHEDULED, COMPLETED
     */
    public Status getStatus() {
        return status;
    }

    /**
     * Set the status of this Booking : SCHEDULED, COMPLETED
     * @param status Status of a Booking
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     * If Booking removed : decrease Room object occupancy and make AssistantOnShift status FREE
     */
    public void removedBooking(){
        room.decrOccupancy();
        assistant.setAvailable(AssistantOnShift.Status.FREE);
    }

    /**
     * If Booking completed, set status to COMPLETED
     */
    public void completedBooking(){
        status = Status.COMPLETED;
    }

    /**
     *
     * @return String : | timeSlot | status | assistantEmail | roomCode | studentEmail |
     */
    public String toString(){
        return
                "| "+timeSlot+" | "+status+" | "+assistant.getAssistant().getEmail()+" | "+room.getRoom().getCode()+
                        " | "+email+" |";
    }

    // Constructors

    /**
     * This constructor initializes a Booking object
     * @param room A BookableRoom object
     * @param assistant An AssistantOnShift object
     * @param timeSlot A timeSlot String : dd/mm/yy HH:MM
     */
    public Booking(BookableRoom room, AssistantOnShift assistant, String timeSlot){
        this.room = room;
        this.assistant = assistant;
        this.timeSlot = timeSlot;
        this.room.incrOccupancy();
        this.assistant.setAvailable(AssistantOnShift.Status.BUSY);
        this.status = Status.SCHEDULED;
        if (this.room.getOccupancy() > this.room.getRoom().getCapacity()){
            throw new IllegalArgumentException("Room capacity reached!");
        }

    }

    /**
     * This constructor initializes a Booking object
     * @param room A BookableRoom object
     * @param assistant An AssitantOnShift object
     * @param timeSlot A timeSlot String : dd/mm/yyyy HH:MM
     * @param email A student email String : *@uok.ac.uk
     */
    public Booking(BookableRoom room, AssistantOnShift assistant, String timeSlot, String email){
        this(room, assistant, timeSlot);
        if(!email.contains("@uok.ac.uk")){
            // Check email contains @uok.ac.uk
            throw new IllegalArgumentException("Incorrect email format!");
        }
        this.email = email;
    }
}
