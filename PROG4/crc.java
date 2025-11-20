import java.util.*;

class crcpgm {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("\nEnter the message in binary bits : ");
        String msg = in.next();
        System.out.print("\nEnter the Generating Polynomial: ");
        String gen = in.next();

        int lt = msg.length();
        int lg = gen.length();

        // Append (lg - 1) zeros
        for (int i = 0; i < lg - 1; i++)
            msg += '0';

        System.out.print("\nModified message is: " + msg);
        String cs = crc(lg, lt, gen, msg);
        System.out.print("\nChecksum is: " + cs);

        // Append checksum to message
        String encoded = msg.substring(0, lt) + cs;
        System.out.print("\nTransferred encoded message is: " + encoded);

        System.out.print("\nEnter the received code: ");
        String recv = in.next();

        
        lt = recv.length() - (lg - 1);
        cs = crc(lg, lt, gen, recv);

        boolean error = false;
        for (int e = 0; e < lg - 1; e++) {
            if (cs.charAt(e) == '1') {
                error = true;
                break;
            }
        }

        if (error)
            System.out.println("Error detected.");
        else
            System.out.println("No Error Detected.");
    }

    public static String crc(int lg, int lt, String g, String t) {
        String cs = t.substring(0, lg);
        int e = lg;
        int c;

        do {
            if (cs.charAt(0) == '1')
                cs = xor(lg, g, cs);
            String s = "";
            for (c = 1; c <= lg - 1; c++)
                s += cs.charAt(c);
            if (e < t.length())   
                s += t.charAt(e);
            e++;
            cs = s;
        } while (e <= (lt + lg - 1));

        return cs;
    }

    public static String xor(int lg, String g, String cs) {
        String v = "";
        for (int c = 0; c < lg; c++)
            v += (cs.charAt(c) == g.charAt(c)) ? '0' : '1';
        return v;
    }
}

