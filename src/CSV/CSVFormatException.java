package CSV;

public class CSVFormatException extends Exception {

    public String error;

    public CSVFormatException(String error){
        this.error = error;
    }
}
