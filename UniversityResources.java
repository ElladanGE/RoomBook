import java.util.ArrayList;

/**
 * Represents the University resources : Assistants and Rooms
 */
public class UniversityResources {
    // Attributes

    private ArrayList<Assistant> Assistants;
    private ArrayList<Room> Rooms;

    // Methods

    /**
     * Adds new Assistant to University Ressources
     * @param a An assistant
     */
    public void addAssistants(Assistant a){
        Assistants.add(a);
        Assistant.assistantArrayList.add(a);
    }

    /**
     * Adds new Room to University Ressources
     * @param room A Room
     */
    public void addRooms(Room room){
        Rooms.add(room);
        Room.roomArrayList.add(room);
    }

    // Constructor

    /**
     * This constructor initializes the University Ressources
     * @param as An arraylist of Assistant objects
     * @param rooms An arraylist of Room objects
     */
    public UniversityResources(ArrayList<Assistant> as, ArrayList<Room> rooms){
        this.Assistants = as;
        this.Rooms = rooms;
    }
}
