package model;
import java.util.Date;

public class Foto {
    private String url;
    private Date fecha;
    private String telescopio;
    
    public Foto(String url, Date fecha, String telescopio){
        this.url = url;
        this.fecha = fecha;
        this.telescopio = telescopio;
    }

    public Foto(String url, String telescopio, java.sql.Date fecha) {
    }

    public String getUrl(){
        return url;
    }

    public Date getFecha(){
        return fecha;
    }

    public String getTelescopio(){
        return telescopio;
    }
    
    
}

