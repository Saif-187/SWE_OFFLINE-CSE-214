public class Blinds {
    private static Blinds instance;
    private boolean isOpen=true;
    private Blinds(){

    }
    public static Blinds getInstance(){
        if (instance == null) {
            instance = new Blinds();
        }
        return instance;
    }
    public void close(){
        isOpen=false;
        System.out.println("Blinds off");
        Central.getInstance().TurnAcOn();
    }
    public boolean getState(){
        return isOpen;
    }
    public void open(){
        isOpen=true;
        System.out.println("Blinds on");
        Central.getInstance().TurnAcOff();
    }
}
