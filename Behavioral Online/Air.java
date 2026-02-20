public class Air {
    private static Air instance;
    private boolean isOn=false;
    private Air(){

    }
    public static Air getInstance(){
        if (instance == null) {
            instance = new Air();
        }
        return instance;
    }
    public void on(){
        isOn=true;
        System.out.println("Ac on");
    }
    public boolean getState(){
        return isOn;
    }
    public void off(){
        System.out.println("Ac off");
        isOn=false;
    }
}
