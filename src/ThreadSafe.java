
/**
 * @author Abhishek Jain
 * Thread Synchronization means-
 * When one thread is acting on an object then another thread cannot (blocked) act on the same object
 * till the completion of first thread operation.
 *
 * Below code is the implementation for same.
 */

class RailTicketReservation implements Runnable{
    int TotalBerthCount = 10;
    int availableBerthCount = 1;
    int wantedBerthCount;

    RailTicketReservation(int bookingBerthCount)
    {
        this.wantedBerthCount = bookingBerthCount;
    }
    public void run() {
        synchronized (this) //using this to synchronize current object.
        {
            String passengerName = Thread.currentThread().getName();
            System.out.println("\nBooking request from passenger " + passengerName);
            System.out.println("At this point, Total available berth count is = " + availableBerthCount);
            if(availableBerthCount >= wantedBerthCount)
            {
                System.out.println(wantedBerthCount + " Berths reserved for " + passengerName);
                try {
                    Thread.sleep(1000);
                    availableBerthCount = availableBerthCount-wantedBerthCount;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            else
            {
                System.out.println("Sorry " + passengerName + "..., No berths available!!!!, All berths are already booked.");
            }
        }
    }
}
public class ThreadSafe {
    public static void main(String[] args) {
        System.out.println("######## Thread Synchronization Implementation ######## ");
        RailTicketReservation railTicketBookingObj = new RailTicketReservation(1);
        Thread t1 = new Thread(railTicketBookingObj, "FirstPerson_ABC");
        Thread t2 = new Thread(railTicketBookingObj, "SecondPerson_XYZ");
        t1.start();
        t2.start();
    }
}
