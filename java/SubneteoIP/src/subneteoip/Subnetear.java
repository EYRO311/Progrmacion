

package subneteoip;

import javax.swing.JOptionPane;

/**
 *
 * @author emist
 */
public class Subnetear {
    String ip[];
    String red = "";
    String nRedes = "";
    String clase = "";
    String resultadoUno = "";
    long mascaraBits[] = new long[4];
    int mascaraInt[] = new int[4];
    int bitsDerecha = 0;
    int bitsIzquierda = 0;
    String mascara = "";
    long numeroHost = 0;
    String mascaraInversa = "";

    public Subnetear(String red, String nRedes, String clase) {
        this.red = red;
        this.nRedes = nRedes;
        this.clase = clase;
        long ipDecimal = ipToLong(red);
        //System.out.println("IP A DECIMAL: "+ipDecimal);
        JOptionPane.showMessageDialog(null,"IP A DECIMAL: "+ipDecimal );
        obtenerMascaraBits();
        resultadoUno += "IP RED: " + red + " /" + bitsDerecha+"\n";
        resultadoUno += "SUBREDES: " + getNRedes() + "\n";
        resultadoUno += "MASCARA DE SUBRED: " + mascaraInt[0] + "." + mascaraInt[1] + "." + mascaraInt[2] + "." + mascaraInt[3] + "\n" ;
        numeroHost = (long) (Math.pow(2, bitsIzquierda));
        resultadoUno += "HOSTS POR SUBRED: " + (numeroHost - 2) + "\n\n";
        obtenerSubredes();

    }

    public void obtenerSubredes() {
        String ipred = red;
        resultadoUno += "SUBRED\tIP RED\tIP INICIAL\t IP FINAL\t\tBROADCAST\n";
        for (int i = 1; i <= getNRedes(); i++) {
            resultadoUno += i + "\t";
            resultadoUno += ipred + "\t";
            String ipinicial = convertirBitsaIP(Suma("1", IPaBits(ipred)));
            //System.out.println("Suma:" + ipinicial);
            JOptionPane.showMessageDialog(null,"Suma:" + ipinicial);
            resultadoUno += ipinicial + "\t";
            String broadcast = convertirBitsaIP(Suma(getBitsSuma(), IPaBits(ipred)));
            String ipfinal = convertirBitsaIP(Resta(IPaBits(broadcast), "1"));
            resultadoUno += ipfinal + "    \t";
            resultadoUno += broadcast + "\t";
            resultadoUno += "\n";
            ipred = convertirBitsaIP(Suma("1", IPaBits(broadcast)));
        }

    }

    public String getBitsSuma() {
        String x = "";
        for (int i = 0; i < bitsIzquierda; i++) {
            x += "1";
        }
        //System.out.println("bitsizquierda: "+x);
        JOptionPane.showMessageDialog(null,"bitsizquierda: "+x);
        return x;
    }

    public String getResultado() {
        return resultadoUno;
    }

    public void obtenerMascaraBits() {
        int x = 0;
        switch (clase) {
            case "A":
                x = 8;
                break;
            case "B":
                x = 16;
                break;
            case "C":
                x = 24;
                break;
            default:
                break;
        }
        if (Integer.parseInt(nRedes) == 1) {
            bitsDerecha = x;
        } else {
            bitsDerecha = x + getNBits();
        }
        bitsIzquierda = 32 - bitsDerecha;
        for (int i = 1; i <= 32; i++) {
            if (i <= bitsDerecha) {
                mascara += "1";
            } else {
                mascara += "0";

            }
        }
        obtenerMascaraOctetos();
    }

    public String convertirBitsaIP(String bits) {
        String ip = "";
        int puntos = 0;
        int y = 128;
        int nOcteto = 0;
        while (bits.length() < 32) {
            bits = "0" + bits;
        }
        char m[] = bits.toCharArray();
        int iterador = 0;
        for (int x = 0; x < 4; x++) {
            for (int i = 0; i < 8; i++) {
                nOcteto += (y * Integer.parseInt(String.valueOf(m[iterador])));
                y /= 2;
                iterador++;
            }
            ip += nOcteto;
            if (puntos < 3) {
                ip += ".";
            }
            puntos++;
            nOcteto = 0;
            y = 128;
        }
        return ip;
    }

    public void obtenerMascaraOctetos() {
        int y = 128;
        int nOcteto = 0;
        char m[] = mascara.toCharArray();
        int iterador = 0;
        for (int x = 0; x < 4; x++) {
            for (int i = 0; i < 8; i++) {
                nOcteto += (y * Integer.parseInt(String.valueOf(m[iterador])));
                y /= 2;
                iterador++;
            }
            mascaraInt[x] = nOcteto;
            nOcteto = 0;
            y = 128;
        }
    }

    public long ipToLong(String ipAddress) {
        String[] ipAddressInArray = ipAddress.split("\\.");
        long result = 0;
        for (int i = 0; i < ipAddressInArray.length; i++) {
            int power = 3 - i;
            int ip = Integer.parseInt(ipAddressInArray[i]);
            result += ip * Math.pow(256, power);
        }
        return result;
    }

    public int getNBits() {
        String binaryOctet = "";
        int octet = Integer.parseInt(nRedes);
        String r = Integer.toBinaryString(octet - 1);
        //System.out.println("NBits:" + r.length() + "");
        JOptionPane.showMessageDialog(null,"NBits:" + r.length() + "");
        return r.length();
    }

    public int getNRedes() {
        int x = 0;
        if (Integer.parseInt(nRedes) == 1) {
            x = 1;
        } else {
            x = (int) Math.pow(2, getNBits());
        }

        return x;
    }

    public String longToIp(String ip) {
        String binaryOctet = "";
        String[] octetArray = ip.split("\\.");
        int puntos = 0;
        for (String string : octetArray) {
            int octet = Integer.parseInt(string);
            String r = Integer.toBinaryString(octet);
            while (r.length() < 8) {
                r = "0" + r;
            }
            binaryOctet += r;
            if (puntos < 3) {
                binaryOctet += ".";
            }
            puntos++;
        }
        return binaryOctet;
    }

    public String IPaBits(String ip) {
        String ipbits = "";
        String[] octetArray = ip.split("\\.");
        int puntos = 0;
        for (String string : octetArray) {
            long octet = Long.parseLong(string);
            String r = Long.toBinaryString(octet);
            while (r.length() < 8) {
                r = "0" + r;
            }
            ipbits += r;
        }
        return ipbits;
    }

    public String toBits(String ip) {
        String binaryOctet = "";
        String[] octetArray = ip.split("\\.");
        int puntos = 0;
        int octet = Integer.parseInt(ip);
        String r = Integer.toBinaryString(octet);
        return r;
    }

    public long toBinario(int number) {
        long decimal = 0;
        long binary = number;
        long power = 0;
        while (binary != 0) {
            long lastDigit = binary % 10;
            decimal += lastDigit * Math.pow(2, power);
            power++;
            binary = binary / 10;
        }
        return decimal;
    }

    public String Suma(String a, String b) {
        long a1 = Long.parseLong(a, 2);
        long b1 = Long.parseLong(b, 2);
        long c1 = a1 + b1;
        String resultado = Long.toString(c1, 2);
        return resultado;
    }

    public String Resta(String a, String b) {
        long a1 = Long.parseLong(a, 2);
        long b1 = Long.parseLong(b, 2);
        long c1 = a1 - b1;
        String resultado = Long.toString(c1, 2);
        return resultado;
    }
}
