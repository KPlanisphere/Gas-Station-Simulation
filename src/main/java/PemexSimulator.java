class Empleado {
    private final Thread hiloLlenarTanquePrincipal;
    private final Thread hiloLlenarTanque;  
    private final Thread hiloLimpiarParabrisas;  
    private final Thread hiloLimpiarParabrisas2;
    private final Thread hiloLlenarLlantas;  
    
    private boolean accionLlenadoTanque = false;
    private boolean accionLlenadoLlantas = false;
    private boolean accionLimpiarParabrisas = false;

    int x = 1;
    
    public Empleado() {
        this.hiloLlenarTanquePrincipal = new Thread(this::LlenadoPrincipal);
        this.hiloLlenarTanque= new Thread(this::llenarTanque);
        this.hiloLimpiarParabrisas = new Thread(this::limpiarParabrisas);
        this.hiloLimpiarParabrisas2 = new Thread(this::limpiarParabrisas);
        this.hiloLlenarLlantas = new Thread(this::llenarLlantas);
    }

    public void start() {
        this.hiloLlenarTanquePrincipal.start();
    }

    private synchronized void LlenadoPrincipal() {
        try {
            System.out.println("Un auto solicita llenado de tanque completo ($1200MXN)");
            System.out.println("LLENANDO TANQUE PRINCIPAL");
            Thread.sleep(1000);
            
            // Mientras el tanque principal es llenado, el empleado limpia el parabrisas del auto principal
            System.out.println("Empleado limpia parabrisas del auto principal");
            accionLimpiarParabrisas = true;
            Thread.sleep(1000);
            
            //En espera a que termine de limpiar el parabrisas
            this.hiloLimpiarParabrisas.start();
            Thread.sleep(3000);
            
            // El empleado termina de limpiar
            System.out.println("Empleado termina de limpiar el parabrisas");
            this.accionLimpiarParabrisas = false;
            Thread.sleep(1000);
            
            // Llegada de un nuevo auto
            System.out.println("Un segundo auto solicita $150MXN de gasolina");
            this.accionLlenadoTanque = true;
            
            // El empleado atiende al segundo auto
            System.out.println("LLENANDO TANQUE DEL SEGUNDO AUTO");
            this.hiloLlenarTanque.start();
            //Thread.sleep(12000);
            
            // El empleado limpia el parabrisas al segundo auto
            System.out.println("\t\tEmpleado limpia parabrisas del segundo auto");
            accionLimpiarParabrisas = true;
            Thread.sleep(1000);
            
            //En espera a que termine de limpiar el parabrisas
            x = 0;
            this.hiloLimpiarParabrisas2.start();
            Thread.sleep(3000);
            
            // El empleado termina de limpiar del segundo auto
            System.out.println("\t\t\tEmpleado termina de limpiar el parabrisas ");
            this.accionLimpiarParabrisas = false;
            Thread.sleep(1000);
            
            // El empleado llena las llantas del segundo auto
            System.out.println("\t\t\tEmpleado llena llantas del segundo auto");
            this.accionLlenadoLlantas = true;
            
            // Espera a que el hilo de retocar labios termine
            this.hiloLlenarLlantas.start();
            Thread.sleep(4000);
            
            // El empleado termina de llenar las llantas
            System.out.println("\t\t\tEmpleado termina de llenar llantas");
            this.accionLlenadoLlantas = false;
            Thread.sleep(1000);
            
            ///LLENADO CONTINUA
            
            // El empleado termina de llenar el tanque del segundo auto
            System.out.println("\t\tEmpleado termina de llenar el tanque del segundo auto");
            this.accionLlenadoTanque = false;
            Thread.sleep(1000);
            
             // El empleado termina de atender al segundo auto 
            System.out.println("\tEmpleado cobra al segundo auto");

            // El empleado termina de llenar el tanque del auto principal
            System.out.println("Empleado termina de llenar el tanque del auto principal");
            
            // El empleado termina de atender el auto principal
            System.out.println("Empleado cobra al auto principal");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void limpiarParabrisas() {
        int cont = 0;
        while (cont < 3) {
              if (accionLimpiarParabrisas) {
                  if(x == 1){
                      System.out.println("\tLimpiando parabrisas...");
                  }
                  else{
                      System.out.println("\t\t\tLimpiando parabrisas...");
                  }
                  try {
                      Thread.sleep(1000);
                      cont++;
                  } catch (InterruptedException e) {
                      e.printStackTrace();
                  }
              }
          }
    }
    
    private void llenarTanque() {
        int cont = 0;
        while (cont < 3) {
            if (accionLlenadoTanque) {
                System.out.println("\t\tLlenando tanque...");
                try {
                    if(cont == 0){
                        Thread.sleep(10000);
                    }
                    else{
                        Thread.sleep(1000);
                    }
                    cont++;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    private void llenarLlantas() {
        int cont = 0;
        while (cont < 4) {
            if(accionLlenadoLlantas){
                System.out.println("\t\t\tInflando llanta...");
                try {
                    Thread.sleep(1000);
                    cont++;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            
        }
    }
}

public class PemexSimulator {
    public static void main(String[] args) {
        Empleado empleado = new Empleado();
        empleado.start();
    }
}
