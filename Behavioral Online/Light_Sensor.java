public class Light_Sensor {
    private static Light_Sensor instance;
    private Light_Sensor(){

    }
    public static Light_Sensor getInstance(){
        if (instance == null) {
            instance = new Light_Sensor();
        }
        return instance;
    }
    public void brightness(String s){
        if(s.equalsIgnoreCase("High")){
            System.out.println("High Brightness");
            Central.getInstance().CloseBlind();

        }
        else if(s.equalsIgnoreCase("Low")){
            System.out.println("Low Brightness");
            Central.getInstance().OpenBlind();
        }
    }
}
