/**
 * Represents an AssistantOnShift object from the University
 */
public class AssistantOnShift {
    // Attribute

    private Assistant assistant;
    private String date;
    private String time;

    /**
     * Status of an AssistantOnShift : FREE, BUSY
     */
    public enum Status {FREE,BUSY}
    private Status available;

    // Methods

    /**
     *
     * @return This AssistantOnShift status : FREE, BUSY
     */
    public Status getAvailable() {
        return available;
    }

    /**
     * Set the time availability of this AssistantOnShift
     * @param time A string time : HH:MM
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * Set this AssistantOnShift status
     * @param available Can be FREE or BUSY
     */
    public void setAvailable(Status available) {
        this.available = available;
    }

    /**
     *
     * @return Assistant object
     */
    public Assistant getAssistant() {
        return assistant;
    }

    /**
     *
     * @return String timeslot : dd/mm/yyyy HH:MM
     */
    public String getTimeSlot(){
        return date+" "+time;
    }

    /**
     *
     * @return String : | date> | available | assistantEmail |
     */
    public String toString(){
        return
                "| "+date+" "+time+" | "+available+" | "+assistant.getEmail()+" |";
    }

    /**
     * This constructor initializes an AssistantOnShift object
     * @param assistant An assistant object
     * @param date A date String : dd/mm/yyyy
     */
    public AssistantOnShift(Assistant assistant, String date){
        this.assistant = assistant;
        this.date = date;
    }

    /**
     * This constructor initializes an AssistantOnShift object
     * @param assistant An assistant object
     * @param date A date String : dd/mm/yyyy
     * @param time A time String : HH:MM
     */
    public AssistantOnShift(Assistant assistant, String date, String time){
        this(assistant,date);
        this.time = time;
        this.available = Status.FREE;
    }
}
