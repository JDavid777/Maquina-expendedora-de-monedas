package negocio;
public class Moneda implements Comparable<Moneda> {
    private int denominacion;
    private int  cantidad;


    public Moneda(int denominacion, int cantidad) {
        this.denominacion = denominacion;
        this.cantidad = cantidad;
    }

    public Moneda(int denominacion) {
        this.denominacion = denominacion;
    }

    public int getDenominacion() {
        return denominacion;
    }

    public void setDenominacion(int denominacion) {
        this.denominacion = denominacion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "Moneda{" +
                "denominacion= $"+ denominacion +
                ", cantidad=" + cantidad +
                '}';
    }

    @Override
    public int compareTo(Moneda o) {
        if(this.denominacion==o.denominacion){
            return 1;
        }
        return 0;
    }
}
