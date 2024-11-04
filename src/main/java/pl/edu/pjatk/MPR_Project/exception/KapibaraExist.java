package pl.edu.pjatk.MPR_Project.exception;

public class KapibaraExist extends  RuntimeException{
    public KapibaraExist() {
        super("Kapibara istnieje");
    }
}
