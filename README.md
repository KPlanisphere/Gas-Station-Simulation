## Gas Station Employee Simulation

This project simulates an employee at a gas station performing various tasks such as refueling cars, cleaning windshields, and inflating tires. It demonstrates the use of Java threads to handle multiple concurrent activities, ensuring realistic simulation of an employee's tasks at a gas station.

### Description
The Gas Station Employee Simulation project uses Java's multithreading capabilities to simulate an employee performing various tasks at a gas station. The main class, `Empleado`, contains multiple threads representing different tasks, ensuring they are executed concurrently and synchronized to mimic real-life scenarios at a gas station.

### Features
- **Multithreading:** Utilizes Java's threading capabilities to manage concurrent tasks.
- **Synchronization:** Ensures conflicting actions are not performed simultaneously.
- **Simulation:** Creates a realistic scenario of a gas station employee's tasks using print statements to represent the employee's actions.

### File Structure
- **GasStationSim/**: Root directory containing project files and directories.
  - **pom.xml**: Maven Project Object Model file containing project configuration.
  - **src/main/java/**: Source directory containing Java source files.
    - **PemexSimulator.java**: Main Java source file for the project.
  - **target/**: Directory containing compiled classes and build related files.
    - **classes/**: Compiled class files.
      - **.netbeans_automatic_build**: NetBeans automatic build indicator file.
      - **Empleado.class**: Compiled bytecode of the Empleado class.
      - **PemexSimulator.class**: Compiled bytecode of the PemexSimulator class.
    - **maven-status/maven-compiler-plugin/compile/default-compile/**: Maven compiler plugin status files.
      - **createdFiles.lst**: List of files created during the build process.
      - **inputFiles.lst**: List of input files for the build process.
    - **test-classes/**: Directory for compiled test classes.
      - **.netbeans_automatic_build**: NetBeans automatic build indicator file for test classes.

### Main Classes and Methods

#### Empleado Class
This class simulates an employee performing various tasks at a gas station, utilizing multiple threads to handle different actions.

**Constructor:**
```java
public Empleado() {
    this.hiloLlenarTanquePrincipal = new Thread(this::LlenadoPrincipal);
    this.hiloLlenarTanque= new Thread(this::llenarTanque);
    this.hiloLimpiarParabrisas = new Thread(this::limpiarParabrisas);
    this.hiloLimpiarParabrisas2 = new Thread(this::limpiarParabrisas);
    this.hiloLlenarLlantas = new Thread(this::llenarLlantas);
}
```

Initializes the threads for various actions.

**Start Method:**

```java
public void start() {
    this.hiloLlenarTanquePrincipal.start();
}
```

Starts the main refueling thread, which sequentially manages other tasks.

**LlenadoPrincipal Method:**

```java
private synchronized void LlenadoPrincipal() {
    try {
        // Simulation of refueling and other tasks
        System.out.println("Un auto solicita llenado de tanque completo ($1200MXN)");
        System.out.println("LLENANDO TANQUE PRINCIPAL");
        Thread.sleep(1000);
        // Additional tasks
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
}
```

Manages the main refueling process and coordinates with other threads.

**LimpiarParabrisas Method:**

```java
private void limpiarParabrisas() {
    int cont = 0;
    while (cont < 3) {
        if (accionLimpiarParabrisas) {
            if(x == 1){
                System.out.println("\tLimpiando parabrisas...");
            } else {
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
```

Simulates the process of cleaning windshields.

**LlenarTanque Method:**

```java
private void llenarTanque() {
    int cont = 0;
    while (cont < 3) {
        if (accionLlenadoTanque) {
            System.out.println("\t\tLlenando tanque...");
            try {
                if(cont == 0){
                    Thread.sleep(10000);
                } else {
                    Thread.sleep(1000);
                }
                cont++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
```

Simulates the process of refueling a car.

**LlenarLlantas Method:**

```java
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
```

Simulates the process of inflating tires.

### Getting Started

To run the simulation, compile the Java files and execute the `PemexSimulator` class.

### Requirements

-   Java Development Kit (JDK)
-   Maven for managing project dependencies
-   An IDE or text editor for Java development

### Usage

1.  Clone the repository.
2.  Navigate to the project directory.
3.  Compile the Java files using `mvn compile`.
4.  Run the simulation using `mvn exec:java -Dexec.mainClass="PemexSimulator"`