package utilidades;

public class Lista<T extends Comparable<T>> {
    private Nodo<T> cabeza;
    private int longitud;

    public Lista() {
        this.longitud = 0;
        this.cabeza=null;
    }
    public void add(T elemento){
        if (this.longitud==0){
            this.cabeza=new Nodo<T>(elemento);
        }
        else{
            this.getUltimo().setSiguiente(new Nodo<T>(elemento));
        }
        this.longitud++;
    }
    private Nodo<T> getUltimo(){
            Nodo<T> aux=this.cabeza;
            while (aux.getSiguiente()!=null){
                Nodo<T> temp= aux.getSiguiente();
                aux=temp;
            }
            return aux;
    }
    public void listar(){
        if (this.longitud>0){
            Nodo<T> aux=this.cabeza;
            do{
                System.out.println(aux.getDato().toString());
                Nodo<T> temp=aux.getSiguiente();
                aux=temp;
            }while (aux!=null);
        }
    }
    public void delete(T elemento){
        if(this.cabeza.getDato().compareTo(elemento)==1){
            Nodo<T> aux=this.cabeza;
            this.cabeza=aux.getSiguiente();
            this.longitud=0;

        }
        else if(this.longitud>1){
            boolean flag=false;
            Nodo<T> anterior=this.cabeza;
            Nodo<T> aux=anterior.getSiguiente();
            while (aux!=null){
                if(aux.getDato().compareTo(elemento)==1){
                    anterior.setSiguiente(aux.getSiguiente());
                    aux=null;
                    this.longitud--;
                    flag=true;
                    break;
                }
                anterior=aux;
                aux=anterior.getSiguiente();
            }
            if (!flag){
                System.out.println("No es encontró el elemento "+elemento+" en la lista.");
            }
        }
        else{
            System.out.println("Lista Vacia");
        }
    }
    public T getElement(T dato) {
        if (this.longitud>0){
        if (this.cabeza.getDato().compareTo(dato) == 1) {
            return this.cabeza.getDato();
        } else if (this.longitud > 1) {
            Nodo<T> aux = this.cabeza;
            while (aux != null) {
                if (aux.getDato().compareTo(dato) == 1) {
                    return aux.getDato();
                }
                Nodo<T> temp = aux.getSiguiente();
                aux = temp;
            }

        }
        }

        return null;
    }
    public void replace(T elemento){
        if(this.cabeza.getDato().compareTo(elemento)==1){
            this.cabeza.setDato(elemento);

        }
        else if(this.longitud>0){
            boolean flag=false;
            Nodo<T> aux=this.cabeza;
            while (aux!=null){
                if(aux.getDato().compareTo(elemento)==1){
                   aux.setDato(elemento);
                    break;
                }
             Nodo<T> temp=aux.getSiguiente();
                aux=temp;
            }
            if (!flag){
                System.out.println("No es encontró el elemento "+elemento+" en la lista.");
            }
        }
        else{
            System.out.println("Lista Vacia");
        }
    }
    public T getElement(int idx){
        if(this.longitud > 0) {
            int contador=0;
            Nodo<T> aux = this.cabeza;
            while (contador< this.longitud) {
                if (contador==idx) {
                    return aux.getDato();
                }
                Nodo<T> temp = aux.getSiguiente();
                aux = temp;
                contador++;
            }
        }
        return null;
    }
    public Nodo<T> getCabeza() {
        return cabeza;
    }

    public void setCabeza(Nodo<T> cabeza) {
        this.cabeza = cabeza;
    }

    public int getLongitud() {
        return longitud;
    }

    public void setLongitud(int longitud) {
        this.longitud = longitud;
    }
}
