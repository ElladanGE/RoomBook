import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents the Booking System
 */
public class BookingSystem {
    // Attributes

    private ArrayList<BookableRoom> BookableRooms;
    private ArrayList<AssistantOnShift> AssistantsOnShift;
    private ArrayList<Booking> Bookings;

    // Methods

    /**
     *
     * @return ArrayList of Bookable Rooms
     */
    public ArrayList<BookableRoom> getBookableRooms() {
        return BookableRooms;
    }

    /**
     *  Add BookableRoom Object to BookingSystem
     */
    void addBookableRoom(){
        clearConsole();
        int code;
        String date;
        String time;
        StringBuilder addRoom = new StringBuilder("University of Knowledge - COVID test\n\n" +
                "Adding bookable room\n");
        int i = 10;
        // List Rooms with unique ID
        for(Room room : Room.roomArrayList){
            i++;
            String ID = i+". ";
            addRoom.append(ID).append(room).append("\n");
        }
        addRoom.append("Please enter one of the following:\n\n");
        addRoom.append("The sequential ID listed to a room, a date (dd/mm/yyyy), and a time (HH:MM), \n" +
                "separated by a white space.\n0. Back to main menu.\n-1. Quit application.\n\n");
        System.out.print(addRoom);
        Scanner scanner = new Scanner(System.in);
        while (true){
            String myObj = scanner.nextLine();
            if(myObj.equals("0")){
                mainMenu();
                break;
            }
            if(myObj.equals("-1")){
                System.exit(0);
            }
            String[] temp = myObj.split(" ");
            try {
                code = Integer.parseInt(temp[0]);
                date = temp[1];
                time = temp[2];
                String timeSlot = date + " " + time;
                Room newRoom = null;
                int ctr = 10;
                for(Room room : Room.roomArrayList){
                    ctr++;
                    if(ctr == code){
                        newRoom = room;
                        for(BookableRoom room1 : BookableRooms){
                            // Check if the maximum amount of bookings have already been made
                            if(room1.getRoom() == room && room1.getTimeSlot().equals(timeSlot) && room1.getStatus()
                        == BookableRoom.Status.FULL){
                                throw new IllegalArgumentException("Room already booked fully");
                            }
                        }
                        BookableRooms.add(new BookableRoom(room, timeSlot));
                        BookableRooms.get(BookableRooms.size() - 1).setStatus(BookableRoom.Status.EMPTY);
                        break;
                    }
                }
                System.out.println("Bookable Room added successfully:\n" +
                        newRoom + "\n"+
                        "Please, enter one of the following:\n" +
                        "<new line>\n" +
                        "The sequential ID listed to a room, a date (dd/mm/yyyy), and a time (HH:MM),\n" +
                        "separated by a white space.\n" +
                        "0. Back to main menu.\n" +
                        "-1. Quit application.\n" +
                        "<new line>\n");
            } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException e) {
                String error = "Error!\n" + e.getLocalizedMessage() + "\nPlease, enter one of the" +
                        "following:\n\nThe sequential ID listed to a room, a date (dd/mm/yyyy), and a time (HH:MM),\n" +
                        "separated by a white space.\n0. Back to main menu.\n-1. Quite application\n";
                System.out.print(error);
            }
        }
    }

    /**
     * Add AssistantOnShift Object to BookingSystem
     */
    void addAssistantOnShift(){
        clearConsole();
        int code;
        String date = "";
        StringBuilder addAssistant = new StringBuilder("University of Knowledge - COVID test\n\nAdding assistant on" +
                "shift\n\n");
        int ctr = 10;
        for(int i = 0; i < Assistant.assistantArrayList.size(); i++){
            ctr++;
            String ID = ctr+". ";
            addAssistant.append(ID).append(Assistant.assistantArrayList.get(i)).append("\n");
        }
        addAssistant.append("Please enter one of the following:\n\nThe sequential ID of an assistant and date " +
                "(dd/mm/yyyy), separated by a white space.\n" +
                "0. Back to main menu.\n" +
                "-1. Quit application.\n");
        System.out.print(addAssistant);
        Scanner scanner = new Scanner(System.in);
        while (true){
            String myObj = scanner.nextLine();
            if(myObj.equals("0")){
                mainMenu();
                break;
            }
            if(myObj.equals("-1")){
                System.exit(0);
            }
            try{
                code = Integer.parseInt(myObj.split(" ")[0]);
                date = myObj.split(" ")[1];
                String assistant = "";
                for (int j = 0; j < Assistant.assistantArrayList.size(); j++){
                    if (j + 11 == code){
                        // Add time slots for a new AssistantOnShift
                        assistant = Assistant.assistantArrayList.get(j).toString();
                        AssistantsOnShift.add(new AssistantOnShift(Assistant.assistantArrayList.get(j),
                                date, "07:00"));
                        AssistantsOnShift.add(new AssistantOnShift(Assistant.assistantArrayList.get(j),
                                date, "08:00"));
                        AssistantsOnShift.add(new AssistantOnShift(Assistant.assistantArrayList.get(j),
                                date, "09:00"));
                    }
                }
                System.out.println("Assistant on Shift added successfully:\n" +
                        assistant + "\n"+
                        "Please, enter one of the following:\n" +
                        "<new line>\n" +
                        "The sequential ID of an assistant and a date (dd/mm/yyyy), separated by a white space.\n" +
                        "0. Back to main menu.\n" +
                        "-1. Quit application.\n" +
                        "<new line>\n");
            } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException e){
                String error = "Error!\n" + e.getLocalizedMessage() + "\nPlease, enter one of the" +
                        "following:\n\nTThe sequential ID of an assistant and a date (dd/mm/yyyy), " +
                        "separated by a white space.\n0. Back to main menu.\n-1. Quite application\n";
                System.out.print(error);
            }
        }
    }

    /**
     * Add Booking Object to BookingSystem
     */
    void addBooking(){
        clearConsole();
        int code;
        int ctr = 10;
        String email = "";
        StringBuilder addBooking = new StringBuilder("University of Knowledge - COVID test\n\n" +
                "Adding booking (appointment for a COVID test) to the system\n\nList of available time-slots:\n");
        for (BookableRoom room : BookableRooms){
            if (room.getStatus() == BookableRoom.Status.EMPTY || room.getStatus() == BookableRoom.Status.AVAILABLE){
                for (AssistantOnShift assistant : AssistantsOnShift){
                        if (assistant.getTimeSlot().equals(room.getTimeSlot()) && assistant.getAvailable() ==
                                AssistantOnShift.Status.FREE){
                            ctr++;
                            String ID = ctr+". ";
                            addBooking.append(ID).append(assistant.getTimeSlot()).append("\n");
                        }
                    }
            }
        }
        addBooking.append("\n").append("Please enter one of the following:\n\nThe sequential ID of an available" +
                "time-slot and the student email, separated by a white space.\n0. Back to main menu.\n-1. Quit" +
                "application.\n\n");
        System.out.print(addBooking);
        Scanner scanner = new Scanner(System.in);
        while(true) {
            String myObj = scanner.nextLine();
            if (myObj.equals("0")) {
                mainMenu();
                break;
            }
            if (myObj.equals("-1")) {
                System.exit(0);
            }
            try {
                code = Integer.parseInt(myObj.split(" ")[0]);
                email = myObj.split(" ")[1];
                String addedBooking = "";
                int ctr2 = 10;
                for (BookableRoom room : BookableRooms){
                    if (room.getStatus() == BookableRoom.Status.EMPTY || room.getStatus() == BookableRoom.Status.AVAILABLE){
                        for (AssistantOnShift assistant : AssistantsOnShift){
                            if (assistant.getTimeSlot().equals(room.getTimeSlot()) && assistant.getAvailable() ==
                                    AssistantOnShift.Status.FREE){
                                ctr2++;
                                if (ctr2 == code){
                                    Booking newBooking = new Booking(room, assistant, assistant.getTimeSlot()
                                    , email);
                                    addedBooking = newBooking.toString();
                                    Bookings.add(newBooking);
                                    break;
                                }
                            }
                        }

                    }
                }
                int ctr3 = 10;
                StringBuilder bookingSuccess = new StringBuilder("Booking added succesfully:\n");
                bookingSuccess.append(addedBooking).append("\n");
                bookingSuccess.append("List of available time-slots:\n");
                for (BookableRoom room : BookableRooms){
                    if (room.getStatus() == BookableRoom.Status.EMPTY || room.getStatus() == BookableRoom.Status.AVAILABLE){
                        for (AssistantOnShift assistant : AssistantsOnShift){
                            if (assistant.getTimeSlot().equals(room.getTimeSlot()) && assistant.getAvailable() ==
                                    AssistantOnShift.Status.FREE){
                                ctr3++;
                                String ID = ctr3+". ";
                                bookingSuccess.append(ID).append(assistant.getTimeSlot()).append("\n");
                            }
                        }
                    }
                }
                bookingSuccess.append("\nPlease, enter one of the following:\n\nThe sequential ID of an available" +
                        "time-slot and the student email, separated by a white space.\n0. Back to main menu.\n" +
                        "-1. Quit application.\n\n");
                System.out.println(bookingSuccess);


            } catch(IllegalArgumentException | ArrayIndexOutOfBoundsException e){
                String error = "Error!\n" + e.getLocalizedMessage() + "\nPlease, enter one of the" +
                        "following:\n\nTThe sequential ID of an available time-slot and the student email, " +
                        "separated by a white space.\n0. Back to main menu.\n-1. Quite application\n";
                System.out.print(error);
            }
        }
    }

    /**
     * Remove a BookableRoom Object from Booking System
     */
    void removeBookableRoom(){
        clearConsole();
        int code;
        int ctr = 10;
        StringBuilder remRoom = new StringBuilder("University of Knowledge - COVID test\n\n");
        for (BookableRoom room : BookableRooms){
            if (room.getStatus().equals(BookableRoom.Status.EMPTY)){
                ctr++;
                String ID = ctr+". ";
                remRoom.append(ID).append(room).append("\n");
            }
        }
        remRoom.append("Removing bookable room\n\nPlease, enter one of the following:\n\nThe sequential ID to select" +
                "the bookable room to be removed.\n 0. Back to main menu\n-1. Quite application.\n");
        System.out.print(remRoom);
        int lastCode = 1000; // Used to compares last entered to new one
        ArrayList<Integer> usedCodes = new ArrayList<>(); // Array of previously entered ID's
        Scanner scanner = new Scanner(System.in);
        while(true){
            String myObj = scanner.nextLine();
            if(myObj.equals("0")){
                mainMenu();
                break;
            }
            if(myObj.equals("-1")){
                System.exit(0);
            }
            try{
                String removedRoom = "";
                code = Integer.parseInt(myObj);
                int ctr2 = 10;
                for(Integer integer : usedCodes){
                    if(integer == code){
                        // Check to see if unique ID has been entered already
                        throw new IllegalArgumentException("Bookable room already added!");
                    }
                }
                if(code > lastCode){
                    // If code entered is higher than previous, elements in the array will have decreased position
                    // by 1 after removed element
                    code--;
                }
                for (BookableRoom room : BookableRooms){
                    if (room.getStatus().equals(BookableRoom.Status.EMPTY)){
                        ctr2++;
                        if (code == ctr2){
                            removedRoom = room.toString();
                            BookableRooms.remove(room);
                            usedCodes.add(code);
                            lastCode = code;
                            break;
                        }
                    }
                }
                System.out.println("Bookable Room removed successfully:\n" +
                        removedRoom + "\n"+
                        "Please, enter one of the following:\n" +
                        "<new line>\n" +
                        "The sequential ID listed to a room\n" +
                        "0. Back to main menu.\n" +
                        "-1. Quit application.\n" +
                        "<new line>\n");
            } catch (IllegalArgumentException | NullPointerException e){
                String error = "Error!\n" + e.getLocalizedMessage() + "\nPlease, enter one of the" +
                        "following:\n\nTThe sequential ID to select the bookable room to be removed.\n" +
                        "0. Back to main menu.\n-1. Quite application\n";
                System.out.print(error);
            }
        }
    }

    /**
     * Remove AssistantOnShift object from BookingSystem
     */
    void removeAssistantOnShift(){
        clearConsole();
        StringBuilder remAssistant = new StringBuilder("University of Knowledge - COVID test\n\n");
        int code;
        int ctr = 10; // Used to show unique ID
        for(AssistantOnShift assistants : AssistantsOnShift){
            if (assistants.getAvailable().equals(AssistantOnShift.Status.FREE)) {
                ctr++;
                String ID = ctr + ". ";
                remAssistant.append(ID).append(assistants).append("\n");
            }
        }
        remAssistant.append("Please, enter one of the following:\n\nThe sequential ID to select the assistant on" +
                "shift to be removed.\n0. Back to menu.\n-1. Quit application.\n\n");
        System.out.print(remAssistant);
        int lastCode = 1000; // Used to compare a new entered Id to previous one
        ArrayList<Integer> usedCodes = new ArrayList<>(); // Array of already entered ID's
        Scanner scanner = new Scanner(System.in);
        while (true){
            String myObj = scanner.nextLine();
            if(myObj.equals("0")){
                mainMenu();
                break;
            }
            if(myObj.equals("-1")){
                System.exit(0);
            }
            try{
                String removedAssistant = "";
                code = Integer.parseInt(myObj);
                for(Integer integer : usedCodes){
                    if(integer == code){
                        throw new IllegalArgumentException("Bookable room already added!");
                    }
                }
                if(code > lastCode){
                    // Similar to remove bookable Room, array element will have decrease position by 1
                    code--;
                }
                int ctr2 = 10;
                for(AssistantOnShift assistants : AssistantsOnShift){
                    if (assistants.getAvailable().equals(AssistantOnShift.Status.FREE)) {
                        ctr2++;
                        if(code == ctr2){
                            removedAssistant = assistants.toString();
                            AssistantsOnShift.remove(assistants);
                            lastCode = code;
                            usedCodes.add(code);
                        }
                    }
                }
                System.out.println("Assistant on Shift removed successfully:\n" +
                        removedAssistant + "\n"+
                        "Please, enter one of the following:\n" +
                        "<new line>\n" +
                        "The sequential ID to the assistant on shift to be removed\n" +
                        "0. Back to main menu.\n" +
                        "-1. Quit application.\n" +
                        "<new line>\n");
            }catch(IllegalArgumentException | ArrayIndexOutOfBoundsException e){
                String error = "Error!\n" + e.getLocalizedMessage() + "\nPlease, enter one of the" +
                        "following:\n\nThe sequential ID to select the assistant on shift to be removed.\n" +
                        "0. Back to main menu.\n-1. Quite application\n";
                System.out.print(error);
            }
        }
    }

    /**
     * Remove Booking Object from BookingSystem
     */
    void removeBooking(){
        clearConsole();
        StringBuilder remBooking = new StringBuilder("University of Knowledge - COVID test\n\n");
        int ctr = 10; // Used for unique ID
        for(Booking booking : Bookings){
            if(booking.getStatus().equals(Booking.Status.SCHEDULED)){
                ctr++;
                String ID = ctr+". ";
                remBooking.append(ID).append(booking).append("\n");
            }
        }
        remBooking.append("Removing booking from the system\n\nPlease, enter one of the following:\n\nThe sequential" +
                "ID to select the booking to be removed from the listed bookings above.\n0. Back to main menu.\n-1." +
                " Quit application.\n");
        System.out.println(remBooking);
        int lastCode = 1000; // Used to check if unique ID was already entered
        ArrayList<Integer> usedCodes = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        while(true){
            String myObj = scanner.nextLine();
            if(myObj.equals("0")){
                mainMenu();
                break;
            }
            if(myObj.equals("-1")){
                System.exit(0);
            }
            try{
                String removedBooking = "";
                int code = Integer.parseInt(myObj);
                for(Integer integer : usedCodes){
                    if(code == integer){
                        throw new IllegalArgumentException("Booking already removed!");
                    }
                }
                if(code > lastCode){
                    code--;
                }
                int ctr3 = 10;
                for(int i = 0; i < Bookings.size(); i++){
                    if (Bookings.get(i).getStatus() == Booking.Status.SCHEDULED) {
                        ctr3++;
                        if (ctr3 == code) {
                            removedBooking = Bookings.get(i).toString();
                            Bookings.get(i).removedBooking(); // Removed booking returns used ressources
                            Bookings.remove(i);
                            lastCode = code;
                            usedCodes.add(code);
                            break;
                        }
                    }
                }
                System.out.println("Booking removed successfully:\n"+removedBooking+"\nPlease, enter one of the" +
                        " following:\n\nThe sequential ID to select the booking to be removed from the listed bookings" +
                        " above.\n" +
                        "0. Back to main menu.\n-1. Quit application.\n");

            } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException e){
                String error = "Error!\n" + e.getLocalizedMessage() + "\nPlease, enter one of the" +
                        "following:\n\nThe sequential ID to select the booking to be removed from the listed" +
                        "bookings above.\n" +
                        "0. Back to main menu.\n-1. Quite application\n";
                System.out.print(error);
            }
        }
    }

    /**
     * List available BookableRoom Objects
     * 1. ALL
     * 2. SCHEDULED
     * 3. COMPLETED
     */
    void showBookings(){
        clearConsole();
        String bookingList = "University of Knowledge - COVID test\n\nSelect which booking" +
                "to list:\n1. All\n2. Only bookings status:SCHEDULED\n3. Only bookings status:COMPLETED\n0. " +
                "Back to main menu.\n-1. Quit application.\n";
        System.out.println(bookingList);
        Scanner scanner = new Scanner(System.in);
        while(true){
            String myObj = scanner.nextLine();
            if(myObj.equals("0")){
                mainMenu();
                break;
            }
            if(myObj.equals("-1")){
                System.exit(0);
            }
            if(myObj.equals("1")){
                int ctr = 10;
                for(Booking booking : Bookings){
                    ctr++;
                    String ID = ctr+". ";
                    System.out.println(ID+booking);
                }
                System.out.println("0. Back to main menu.\n-1. Quit application.\n");
            }
            if(myObj.equals("2")){
                int ctr = 10;
                for(Booking booking : Bookings){
                    if(booking.getStatus() == Booking.Status.SCHEDULED) {
                        ctr++;
                        String ID = ctr + ". ";
                        System.out.println(ID + booking);
                    }
                }
                System.out.println("0. Back to main menu.\n-1. Quit application.\n");
            }
            if(myObj.equals("3")){
                int ctr = 10;
                for(Booking booking : Bookings){
                    if(booking.getStatus() == Booking.Status.COMPLETED) {
                        ctr++;
                        String ID = ctr + ". ";
                        System.out.println(ID + booking);
                    }
                }
                System.out.println("0. Back to main menu.\n-1. Quit application.\n");
            }
        }
    }

    /**
     * List all AssistantOnShift Objects
     */
    void showAssistantsOnShift(){
        clearConsole();
        StringBuilder assistantList = new StringBuilder("University of Knowledge - COVID test\n\n");
        int ctr = 10;
        for(AssistantOnShift assistants : AssistantsOnShift){
            ctr++;
            String ID = ctr+". ";
            assistantList.append(ID).append(assistants).append("\n");
        }
        assistantList.append("0. Back to main menu.\n");
        assistantList.append("-1. Quit application. \n\n");
        System.out.print(assistantList);
    }

    /**
     * List all BookableRoom objects
     */
    void showBookableRooms(){
        clearConsole();
        StringBuilder bookingList = new StringBuilder("University of Knowledge - COVID test\n\n");
        int i = 10;
        for(BookableRoom room : BookableRooms){
            i++;
            String ID = i+". ";
            bookingList.append(ID).append(room).append("\n");
        }
        bookingList.append("0. Back to main menu.\n");
        bookingList.append("-1. Quit application. \n\n");
        System.out.print(bookingList);
    }

    /**
     * Conclude Booking object with status : SCHEDULED
     */
    void concludeBooking(){
        clearConsole();
        StringBuilder concludeBooking = new StringBuilder("University of Knowledge - COVID test\n\n");
        int ctr = 10;
        for(Booking booking : Bookings){
            if(booking.getStatus().equals(Booking.Status.SCHEDULED)){
                ctr++;
                String ID = ctr+". ";
                concludeBooking.append(ID).append(booking).append("\n");
            }
        }
        concludeBooking.append("Conclude booking\n\nPlease, enter one of the following:\n\nThe sequential ID" +
                " to select the booking to be completed.\n0. Back to main menu.\n-1. Quit application.\n");
        System.out.println(concludeBooking);
        int lastCode = 1000;
        ArrayList<Integer> usedCodes = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        while(true){
            String myObj = scanner.nextLine();
            if(myObj.equals("0")){
                mainMenu();
                break;
            }
            if(myObj.equals("-1")){
                System.exit(0);
            }
            try{
                int code = Integer.parseInt(myObj);
                for(Integer integer : usedCodes){
                    if(integer == code){
                        throw new IllegalArgumentException("Booking already completed!");
                    }
                }
                if(code > lastCode){
                    code--;
                }
                int ctr3 = 10;
                String completedBooking = "";
                for(Booking booking : Bookings){
                    if(booking.getStatus() == Booking.Status.SCHEDULED){
                        ctr3++;
                        if (code == ctr3) {
                            lastCode = ctr3;
                            completedBooking = booking.toString();
                            booking.completedBooking(); // Changes status of booking
                            usedCodes.add(code);
                            break;
                        }
                    }
                }
                System.out.println("Booking completed successfully:\n"+completedBooking+"\nPlease, enter one of the" +
                        "following:\n\nThe sequential ID to select the booking to be completed.\n0. Back to main" +
                        " menu\n-1. Quit application.\n");
            } catch (IllegalArgumentException e){
                String error = "Error!\n" + e.getLocalizedMessage() + "\nPlease, enter one of the" +
                        "following:\n\nThe sequential ID to select the booking to be completed.\n"+
                        "0. Back to main menu.\n-1. Quite application\n";
                System.out.print(error);
            }

        }
    }

    /**
     * Print main menu to screen, runs all BookingSystem methods depending on user input
     */
    public void mainMenu(){
        clearConsole();
        String menu =   """
                        University of Knowledge - COVID test
                        <new line>
                        Manage Bookings
                        <new line>
                        Please, enter the number to select your option:
                        <new line>
                        To manage Bookable Rooms:
                            1. List
                            2. Add
                            3. Remove
                        To manage Assistants on Shift:
                            4. List
                            5. Add
                            6. Remove
                        To manage Bookings:
                            7. List
                            8. Add
                            9. Remove
                            10. Conclude
                        After selecting one the options above, you will be presented other screens.
                        If you press 0, you will be able to return to this main menu.
                        Press -1 (or ctrl+c) to quit this application.
                        <new line>
                        """;
        System.out.println(menu);
        Scanner scanner = new Scanner(System.in);
        while(true){
            String myObj = scanner.nextLine();
            if(myObj.equals("0")){
                mainMenu();
                scanner.close();
                break;
            }
            if(myObj.equals("-1")){
                System.exit(0);
            }
            try{
                int code = Integer.parseInt(myObj);
                if(code == 1){
                    showBookableRooms();
                }
                else if(code == 2){
                    addBookableRoom();
                }
                else if(code == 3){
                    removeBookableRoom();
                }
                else if(code == 4){
                    showAssistantsOnShift();
                }
                else if(code == 5) {
                    addAssistantOnShift();
                }
                else if(code == 6){
                    removeAssistantOnShift();
                }
                else if(code == 7) {
                    showBookings();
                }
                else if(code == 8){
                    addBooking();
                }
                else if(code == 9) {
                    removeBooking();
                }
                else if(code == 10){
                    concludeBooking();
                }
                else{
                    throw new IllegalArgumentException("Incorrect input");
                }
            } catch(IllegalArgumentException e){
                System.out.println("Error!\n" + e.getLocalizedMessage()+"\nPlease enter number between 1-10");
            }
        }
    }

    /**
     * Clears console
     */
    public static void clearConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            }
            else {
                System.out.print("\033\143");
            }
        } catch (IOException | InterruptedException ex) {System.out.println("ClearConsole not working with this OS");}
    }

    // Constructor

    /**
     * Initializes BookingSystem object
     * @param rooms An arraylist of BookableRoom objects
     * @param assistants An arraylist of AssistantOnShift objects
     * @param bookings An arraylist of Booking objects
     */
    public BookingSystem(ArrayList<BookableRoom> rooms, ArrayList<AssistantOnShift> assistants,
                         ArrayList<Booking> bookings){
        this.AssistantsOnShift = assistants;
        this.BookableRooms = rooms;
        this.Bookings = bookings;
    }
}
