
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        while(true){
            String s=sc.nextLine();
            if(s.equalsIgnoreCase("End")){
                break;
            }
            Light_Sensor.getInstance().brightness(s);
        }
    }
}
