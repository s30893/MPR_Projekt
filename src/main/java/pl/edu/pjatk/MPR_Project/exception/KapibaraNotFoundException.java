package pl.edu.pjatk.MPR_Project.exception;

public class KapibaraNotFoundException extends RuntimeException {
    public KapibaraNotFoundException() {
        super("Kapibara not found!");
    }
}
