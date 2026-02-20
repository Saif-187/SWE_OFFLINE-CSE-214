import java.util.Scanner;
public class Main{
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        System.err.println("Input your channel");
        String st=sc.nextLine();
        Notification notify=new SMS();
        if(st.equalsIgnoreCase("SMS")){
            notify=new SMS();
        }
        else if(st.equalsIgnoreCase("email")){
            notify=new email();
        }
        else if(st.equalsIgnoreCase("Push Notification")){
            notify=new push_notify();
        }
        notify.notifyUser();
    }
}