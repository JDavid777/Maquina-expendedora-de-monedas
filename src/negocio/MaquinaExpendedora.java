package negocio;

import utilidades.Lista;
import java.util.Scanner;
import java.util.Vector;

public class MaquinaExpendedora {
    private Lista<Moneda> monedas;

    public MaquinaExpendedora() {
        this.monedas = new Lista<>();
    }
    public void add(Moneda moneda){
        monedas.add(moneda);
    }
     public void run(){
            int option=0;
            Scanner lectura=new Scanner(System.in);
            do {
                menu();
                System.out.println("Seleccione una opción");
                option=Integer.parseInt(lectura.nextLine());
                switch (option){
                    case 1:
                        crearMoneda();break;
                    case 2:
                        consultarMoneda();break;
                    case 3:
                        actualizarMoneda();break;
                    case 4:
                        eliminarMoneda();break;
                    case 5:
                        simularCambio();break;
                }
            }while(option !=6);

     }
     private void crearMoneda(){
        Scanner linea=new Scanner(System.in);
        int denominacion,cantidad;
         System.out.println("Crear Moneda");
        System.out.println("Ingrese la denominación: ");
        denominacion=Integer.parseInt(linea.nextLine());
         System.out.println("Ingrese la cantidad");
         cantidad=Integer.parseInt(linea.nextLine());
         Moneda moneda=monedas.getElement(new Moneda(denominacion,cantidad));
         if(moneda==null){
             this.monedas.add(new Moneda(denominacion,cantidad));
         }
         else if(moneda.getCantidad()==0){
             this.monedas.replace(moneda);
         }
         else{
             System.out.println("Ya existen monedas con esa denominacion.");
         }

     }
     private void consultarMoneda(){
        if(monedas.getLongitud()>0) {

            System.out.println("Consultando Moneda");
            System.out.println("Ingrese la denominación: ");
            Moneda m = monedas.getElement(new Moneda(Integer.parseInt(new Scanner(System.in).nextLine())));
            if (m != null) {
                System.out.println(m.toString());
            } else {
                System.out.println("No existe esa denominación");
            }
        }
        else{
            System.out.println("La maquina no tiene monedas...");
        }
     }
     private void actualizarMoneda(){
        if(monedas.getLongitud()>0) {
            System.out.println("Actualizando Moneda");
            System.out.println("Ingrese la denominación: ");
            int denominacion=Integer.parseInt(new Scanner(System.in).nextLine());

            System.out.println("Ingrese la nueva cantidad: ");
            int cantidad=Integer.parseInt(new Scanner(System.in).nextLine());
            monedas.replace(new Moneda(denominacion, cantidad));
        }
        else{
            System.out.println("La maquina no tiene monedas...");
        }
     }
     private void eliminarMoneda(){
        if(monedas.getLongitud()>0) {
            System.out.println("Eliminando Moneda");
            System.out.println("Ingrese la denominación de la moneda a eliminar: ");
            monedas.delete(new Moneda(Integer.parseInt(new Scanner(System.in).nextLine())));
        }
        else{
            System.out.println("La maquina no tiene monedas...");
        }

     }
     public void simularCambio(){
         if(monedas.getLongitud()>0) {
             System.out.println("Dando devuelta...");
             System.out.println("Ingrese el monto: ");
             int monto=Integer.parseInt(new Scanner(System.in).nextLine());
             Lista<Moneda> cambio=this.Cambio(monto);
             if(cambio!=null){
                 if(calcularSaldo(cambio)==monto)
                    cambio.listar();
                 else
                     System.out.println("No es posible devolver la cantidad solicitada. Por favor ingrese un monto multiplo de: " +this.denominacionesActuales().toString());
             }
             else{
                 System.out.println("Saldo insuficiente...");
             }
         }
     }
     private Lista<Moneda> Cambio(int monto){
        if(calcularSaldo(this.monedas)>=monto){
             int i = 0;
             Lista<Moneda> devuelta = new Lista<Moneda>();
             while (i < this.monedas.getLongitud()) {
                 if (monto >= monedas.getElement(i).getDenominacion() && monedas.getElement(i).getCantidad() > 0) {
                     Moneda moneda=new Moneda(this.monedas.getElement(i).getDenominacion(),1);
                     if(devuelta.getElement(moneda)==null){
                         devuelta.add(moneda);
                         monto -= monedas.getElement(i).getDenominacion();
                         monedas.getElement(i).setCantidad(monedas.getElement(i).getCantidad() - 1);
                     }
                    else {
                         devuelta.getElement(moneda).setCantidad(devuelta.getElement(moneda).getCantidad()+1);
                         monto -= monedas.getElement(i).getDenominacion();
                         monedas.getElement(i).setCantidad(monedas.getElement(i).getCantidad() - 1);
                     }
                 } else {
                     i++;
                 }
             }
             return devuelta;
         }
        return null;
     }

     private static int calcularSaldo(Lista<Moneda> monedas){
        if (monedas.getLongitud()>0){
            int saldoTotal=0;
            for(int i=monedas.getLongitud(); i>0;i--){
                Moneda moneda=monedas.getElement(i-1);
                saldoTotal+=moneda.getDenominacion()*moneda.getCantidad();
            }
            return saldoTotal;
        }
        else{
            return 0;
        }
     }

private Vector<Integer> denominacionesActuales(){
        if (this.monedas.getLongitud()>0){
            Vector<Integer> denominaciones = new Vector<>();
            for (int i =this.monedas.getLongitud(); i>0;i--){
                denominaciones.add(this.monedas.getElement(i-1).getDenominacion());
            }
            return denominaciones;
        }
        return null;
}
private void menu(){
        System.out.println("++++++++++++++++++++++++Menú Principal++++++++++++++++++++++++");
         System.out.println("1.Crear moneda"                                              );
         System.out.println("2.Consultar una moneda por denominación"                     );
         System.out.println("3.Atualizar cantidad de monedas de una alguna denominación." );
         System.out.println("4.Eliminar moneda por denominación");
         System.out.println("5.Simular proceso de devolución de una cantidad X en pesos"  );
         System.out.println("6.Apagar maquina"                                            );
         System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

     }
}
