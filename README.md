# Repositorio para la asignatura de Algoritmia PL2

Acordaros de hacer PULL cada vez que vayáis a editar vuestro código.

## Guía de ejecución de java en consola para dummies 😁

### Antes de empezar a trabajar:

Copia el archivo `rutalaboratorio.bat` al directorio en el que vayas a realizar la práctica. 

Supongamos para el ejemplo que la ruta es `C:\Users\alg\Desktop\practicas\practica0`

<img width="1150" height="147" alt="imagen" src="https://github.com/user-attachments/assets/9854b42c-ef84-4717-911b-b99a54cbee61" />

#### **Edita el archivo rutalaboratorio.bat**

En el **set path** pondremos la ubicación del /bin del jdk de java.

>[!TIP]
> Podemos usar el comando `java -XshowSettings:properties -version` para identificar la ubicación de esta carpeta.
> 
> El directorio aparece referenciado en java.home (y al comienzo del java.library.path):
> 
> <img width="535" height="64" alt="imagen" src="https://github.com/user-attachments/assets/0669d314-2d5b-4149-973d-940cb4f41f77" />

En el **set classpath** pondremos la ruta de la práctica que contiene el fichero .java

<img width="500" height="49" alt="imagen" src="https://github.com/user-attachments/assets/31be971e-b44b-4dde-a18f-e6cb606aff77" />

### Como empezar a trabajar: Ruta de Java y Compilación:

Muevete a la carpeta de la práctica dentro de la consola de comandos:
- Abriendo la propia carpeta desde el explorador y escribiendo cmd en la barra de búsqueda.
  
  <img width="562" height="86" alt="imagen" src="https://github.com/user-attachments/assets/3ac94dda-cf2f-4690-9f29-9d4a7f153557" />

- Escribiendo cmd en la barra del menú de inicio y navegando hasta la carpeta.

  <img width="248" height="65" alt="imagen" src="https://github.com/user-attachments/assets/ad8d6eac-8e9b-4659-98cc-d5620823bbb7" />

Ejecuto `rutadelaboratorio.bat` en el directorio de trabajo de la práctica **y no cierro la consola**.

<img width="610" height="168" alt="Captura de pantalla 2026-01-29 121323" src="https://github.com/user-attachments/assets/aa17f6ac-02e9-4b8b-ad1f-611133fdb6dc" />

Acto seguido, vamos a compilar los .java con el comando `javac -d . *.java`
- `javac` es el comando para realizar la compilación de clases java, nos permite crear archivos .class
- la opción `-d .` nos crea el/los directorios que necesiten las clases. Si estas se encuentran todas en el paquete _algoritmia_ nos creará una carpeta con ese nombre. El `.` especifica que lo generará en el repositorio actual.
- `*.java` especifica que el comando afectará a todos los archivos con extensión .java de la carpeta.

Una vez ejecutado el comando y corregidos los posibles errores de compilación, en nuestro directorio de trabajo deberían haber aparecido las carpetas correspondientes a los paquetes definidos en las clases compiladas.

<img width="477" height="79" alt="imagen" src="https://github.com/user-attachments/assets/07e8a68e-66d3-481b-b664-8beeb5e791e3" />

Dentro de la/s carpeta/s generada/s se encuentra el .class de las clases compiladas

### Como ejecutar

> [!IMPORTANT]
> Desde la consola de comandos, debemos asegurarnos de estar en la carpeta del directorio de trabajo.
> En nuestro ejemplo **C:\Users\alg\Desktop\practicas\practica0**

>[!CAUTION]
>No cerréis la consola para parar la ejecución del programa.
>
>En su lugar emplead el comando `Ctrl + C`
>
>Si no lo hacéis así, tendréis que volver a ejecutar `rutalaboratorio.bat` para ejecutar en una consola nueva

>**JAVA CON OPTIMIZACIÓN**
>
>Ejecutamos el comando `java paquete.clase`
>
><img width="1259" height="215" alt="ejec1" src="https://github.com/user-attachments/assets/c64069af-8746-4026-af26-5783e60705fe" />

>**JAVA CON OPTIMIZACIÓN**
>
>Ejecutamos el comando `java -Xint paquete.clase`
>
><img width="1259" height="215" alt="ejec" src="https://github.com/user-attachments/assets/c38a4bea-fa4f-4f5d-a7ca-f7331a0286eb" />




