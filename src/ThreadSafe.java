
/**
 * @author Abhishek Jain
 *
 * Thread Synchronization means-
 * When one thread is acting on an object then another thread cannot (blocked) act on the same object,
 * till the completion of first thread operation.
 *
 * Below code is the implementation for same.
 */

class RailTicketReservation implements Runnable{
    int TotalBerthCount = 10;       //Total berth in Train
    int availableBerthCount = 1;    //count to handle - Total vacant berth
    int wantedBerthCount;           //count to handle - how many berths passenger is trying to book

    RailTicketReservation(int bookingBerthCount)
    {
        //accepting berth required by Passenger in constructor, and assigning it to program variable.
        this.wantedBerthCount = bookingBerthCount;
    }
    public void run() {
        synchronized (this) //using this to synchronize current object.
        {
            //extracting Thread name, in this program consider this as a passenger name.
            String passengerName = Thread.currentThread().getName();
            System.out.println("\nBooking request from passenger " + passengerName);
            System.out.println("At this point, Total available berth count is = " + availableBerthCount);

            //checking if vacant berth count is equal or more than berth required by passenger or not. and taking action accordingly.
            if(availableBerthCount >= wantedBerthCount)
            {
                System.out.println(wantedBerthCount + " Berths reserved for " + passengerName);
                try {
                    Thread.sleep(1000);
                    //After ticket booking, updating vacant berth count again.
                    availableBerthCount = availableBerthCount-wantedBerthCount;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            else
            {
                //if vacant berth count is less than required berth count, then showing below message to passenger.
                System.out.println("Sorry " + passengerName + "..., No berths available!!!!, All berths are already booked.");
            }
        }
    }
}
public class ThreadSafe {
    public static void main(String[] args) {
        System.out.println("######## Thread Synchronization Implementation ######## ");
        //mentioned booking for 1 berth.
        RailTicketReservation railTicketBookingObj = new RailTicketReservation(1);

        //attached RailTicketReservation object with Thread t1 and t2.
        Thread t1 = new Thread(railTicketBookingObj, "FirstPerson_ABC");
        Thread t2 = new Thread(railTicketBookingObj, "SecondPerson_XYZ");

        //started both Thread t1 & t2 for processing.
        t1.start();
        t2.start();
    }
}
