import java.util.ArrayList;

/**
 * Represents an Assistant from the University
 */
public class Assistant {
    // Attributes
    private String name;
    private String email;

    /**
     * ArrayList of Assistants
     */
    public static ArrayList<Assistant> assistantArrayList = new ArrayList<Assistant>();

    // Methods

    /**
     *
     * @return Assistant name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return Assistant email
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @return Return ArrayList of assistants
     */
    public static ArrayList<Assistant> getAssistantList(){
        return assistantArrayList;
    }

    /**
     *
     * @return String : | name | email |
     */
    public String toString(){
        return
                "| "+name+" |"+" "+email+" |";
    }

    // Constructor

    /**
     * This constructor initializes an Assistant
     * @param name String name
     * @param email String email
     */
    public Assistant(String name, String email){
        if (name.equals(" ")){
            // Check name isn't blank
            throw new IllegalArgumentException("Blank name not permitted!");
        }
        else if(!email.contains("@uok.ac.uk")){
            // Check email contains @uok.ac.uk
            throw new IllegalArgumentException("Incorrect email format!");
        }
        if (assistantArrayList != null) {
            for (Assistant ai : assistantArrayList) {
                if (ai.getEmail().equals(email)) {
                    // Go throw ArrayList of assistant to check uniqueness of email
                    throw new IllegalArgumentException("This email is not unique!");
                }
            }
        }
        this.name = name;
        this.email = email;
    }
}
