public class Central {
    private static Central instance;
    private Central(){

    }
    public static Central getInstance(){
        if (instance == null) {
            instance = new Central();
        }
        return instance;
    }
    public void CloseBlind(){
        Blinds.getInstance().close();
    }
    public void OpenBlind(){
        Blinds.getInstance().open();
    }
    public void TurnAcOff(){
        Air.getInstance().off();
    }
    public void TurnAcOn(){
        Air.getInstance().on();
    }
}
