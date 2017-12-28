package stopcar.test;

public class TestHashMod {
    public static void main(String[] args) {
        String newIP = "2016122715521603";
//        for (int i = 0; i < 9; i++) {
//            newIP += i;
            int hashCode1 = newIP.hashCode();
            System.out.println("hashCode1="+hashCode1);
            int serverPos1 = hashCode1 % 3;
            System.out.println("serverPos1="+Math.abs(serverPos1));
//            newIP = "510502100000207020170602083621V";
//        }

    }
}
