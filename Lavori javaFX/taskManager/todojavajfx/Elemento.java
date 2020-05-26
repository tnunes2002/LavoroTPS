package todojavajfx;

public class Elemento {
    private String testo;
    private String data;
    
    public Elemento(String testo, String data){
        this.testo = testo;
        this.data = data;
    }

    public String getTesto() {
        return testo;
    }

    public void setTesto(String testo) {
        this.testo = testo;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
    
    
}
