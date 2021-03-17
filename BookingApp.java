import java.util.ArrayList;

/**
 * Loads the mainMenu method with initial data
 */
public class BookingApp {
    public static void main(String[] args){

        // Initializing Room objects
        Room r1 = new Room("IC100", 1);
        Room r2 = new Room("IC101", 5);
        Room r3 = new Room("IC102", 5);

        Room.roomArrayList.add(r1); // Adding rooms to the public static variable roomArrayList
        Room.roomArrayList.add(r2);
        Room.roomArrayList.add(r3);

        // Initializing Assistant objects
        Assistant a1 = new Assistant("joe", "joe@uok.ac.uk");
        Assistant a2 = new Assistant("bob", "bob@uok.ac.uk");
        Assistant a3 = new Assistant("sia", "sia@uok.ac.uk");
        Assistant.assistantArrayList.add(a1);
        Assistant.assistantArrayList.add(a2);
        Assistant.assistantArrayList.add(a3);


        // Initializing BookableRoom objects : 3 time slots for each Room
        BookableRoom br1 = new BookableRoom(r1, "25/02/2021 07:00");
        BookableRoom br2 = new BookableRoom(r1, "25/02/2021 08:00");
        BookableRoom br3 = new BookableRoom(r1, "25/02/2021 09:00");

        BookableRoom br4 = new BookableRoom(r2, "25/02/2021 07:00");
        BookableRoom br5 = new BookableRoom(r2, "25/02/2021 08:00");
        BookableRoom br6 = new BookableRoom(r2, "25/02/2021 09:00");

        BookableRoom br7 = new BookableRoom(r3, "25/02/2021 07:00");
        BookableRoom br8 = new BookableRoom(r3, "25/02/2021 08:00");
        BookableRoom br9 = new BookableRoom(r3, "25/02/2021 09:00");

        ArrayList<BookableRoom> bookableRooms = new ArrayList<>();
        bookableRooms.add(br1); bookableRooms.add(br2); bookableRooms.add(br3);
        bookableRooms.add(br4); bookableRooms.add(br5); bookableRooms.add(br6);
        bookableRooms.add(br7); bookableRooms.add(br8); bookableRooms.add(br9);
        for(BookableRoom room : bookableRooms){
            room.setStatus(BookableRoom.Status.EMPTY);
        }

        // Initializing AssistantsOnShift
        AssistantOnShift aos1  = new AssistantOnShift(a1, "25/02/2021");
        AssistantOnShift aos2  = new AssistantOnShift(a1, "25/02/2021");
        AssistantOnShift aos3 = new AssistantOnShift(a1, "25/02/2021");

        AssistantOnShift aos4  = new AssistantOnShift(a2, "25/02/2021");
        AssistantOnShift aos5 = new AssistantOnShift(a2, "25/02/2021");
        AssistantOnShift aos6  = new AssistantOnShift(a2, "25/02/2021");

        ArrayList<AssistantOnShift> assistantOnShifts = new ArrayList<>();
        assistantOnShifts.add(aos1); assistantOnShifts.add(aos2); assistantOnShifts.add(aos3);
        assistantOnShifts.add(aos4); assistantOnShifts.add(aos5); assistantOnShifts.add(aos6);
        int ctr = 0;
        for(AssistantOnShift assistant : assistantOnShifts){
            if(ctr%3 == 0){
                assistant.setTime("07:00");
                assistant.setAvailable(AssistantOnShift.Status.FREE);
            }
            if(ctr%3 == 1){
                assistant.setTime("08:00");
                assistant.setAvailable(AssistantOnShift.Status.FREE);
            }
            if(ctr%3 == 2){
                assistant.setTime("09:00");
                assistant.setAvailable(AssistantOnShift.Status.FREE);
            }
            ctr++;
        }

        // Initializing Bookings
        Booking booking1 = new Booking(br2, assistantOnShifts.get(1), "10/10/2010 08:00", "std1@uok.ac.uk");
        Booking booking2 = new Booking(br3, assistantOnShifts.get(2), "10/10/2010 09:00", "std2@uok.ac.uk");
        booking2.setStatus(Booking.Status.SCHEDULED);
        booking1.setStatus(Booking.Status.COMPLETED);
        ArrayList<Booking> bookings = new ArrayList<>();
        bookings.add(booking1); bookings.add(booking2);

        // Initializing BookingSystem
        BookingSystem bookingSystem = new BookingSystem(bookableRooms, assistantOnShifts, bookings);
        bookingSystem.mainMenu();
    }
}
