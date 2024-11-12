import java.util.Scanner;

public class RSA {
    // Calculate the greatest common divisor
    static int gcd(int m, int n) {
        while (n != 0) {
            int r = m % n;
            m = n;
            n = r;
        }
        return m;
    }

    public static void main(String args[]) {
        int p, q, n, e = 0, d = 0, phi;
        int[] nummes = new int[100];
        int[] encrypted = new int[100];
        int[] decrypted = new int[100];
        Scanner sc = new Scanner(System.in);

        // Get the message from the user
        System.out.println("Enter the Message to be encrypted:");
        String message = sc.nextLine();

        // Input p and q values
        System.out.println("Enter values of p and q:");
        p = sc.nextInt();
        q = sc.nextInt();

        // Compute n and phi(n)
        n = p * q;
        phi = (p - 1) * (q - 1);

        // Find the encryption key e
        for (int i = 2; i < phi; i++) {
            if (gcd(i, phi) == 1) {
                e = i;
                break;
            }
        }

        // Find the decryption key d
        for (int i = 2; i < phi; i++) {
            if ((e * i) % phi == 1) {
                d = i;
                break;
            }
        }

        // Convert message characters to integer representation
        int nofelem = message.length();
        for (int i = 0; i < nofelem; i++) {
            nummes[i] = message.charAt(i) - 96;
        }

        // Encrypt the message
        for (int i = 0; i < nofelem; i++) {
            encrypted[i] = 1;
            for (int j = 0; j < e; j++) {
                encrypted[i] = (encrypted[i] * nummes[i]) % n;
            }
        }

        // Display the encrypted message
        System.out.println("\nEncrypted message:");
        for (int i = 0; i < nofelem; i++) {
            System.out.print(encrypted[i]);
            System.out.print((char) (encrypted[i] + 96));
        }

        // Decrypt the message
        for (int i = 0; i < nofelem; i++) {
            decrypted[i] = 1;
            for (int j = 0; j < d; j++) {
                decrypted[i] = (decrypted[i] * encrypted[i]) % n;
            }
        }

        // Display the decrypted message
        System.out.println("\nDecrypted message:");
        for (int i = 0; i < nofelem; i++) {
            System.out.print((char) (decrypted[i] + 96));
        }

        sc.close();
    }
}
