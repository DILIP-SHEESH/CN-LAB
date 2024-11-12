import java.util.Scanner;

public class Leaky {

    static int min(int x, int y) {
        return (x < y) ? x : y;
    }

    public static void main(String[] args) {
        int drop = 0, psent, nsec, cap, pleft = 0, process;
        int[] inp = new int[25];
        Scanner sc = new Scanner(System.in);

        // Input for bucket size, process rate, and simulation time
        System.out.println("Enter The Bucket Size");
        cap = sc.nextInt();

        System.out.println("Enter The Operation Rate");
        process = sc.nextInt();

        System.out.println("Enter The Number Of Seconds You Want To Simulate");
        nsec = sc.nextInt();

        // Input for packet size at each second
        for (int i = 0; i < nsec; i++) {
            System.out.print("Enter The Size Of The Packet Entering at " + (i + 1) + " sec: ");
            inp[i] = sc.nextInt();
        }

        // Display the header for the table
        System.out.println("\nSecond | Packet Recieved | Packet Sent | Packet Left | Packet Dropped");
        System.out.println("-------------------------------------------------------------");

        // Simulate each second
        for (int i = 0; i < nsec; i++) {
            pleft += inp[i];

            // Handle packet overflow (drop)
            if (pleft > cap) {
                drop = pleft - cap;
                pleft = cap;
            }

            // Process packet sent and remaining
            psent = min(pleft, process);
            pleft -= psent;

            // Output the results for the current second
            System.out.println((i + 1) + "\t\t" + inp[i] + "\t\t" + psent + "\t\t" + pleft + "\t\t" + drop);

            // Reset drop for the next second
            drop = 0;
        }

        // Handle remaining packets if any
        while (pleft > 0) {
            if (pleft > cap) {
                drop = pleft - cap;
                pleft = cap;
            }

            psent = min(pleft, process);
            pleft -= psent;

            // Output for each second after the simulation time ends
            System.out.println((nsec + 1) + "\t\t0\t\t" + psent + "\t\t" + pleft + "\t\t" + drop);

            drop = 0;
            nsec++;
        }

        sc.close();
    }
}
