package Modelo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author NITRO 5
 */
public class ArregloMascotas implements Serializable {

    private static final long serialVersionUID = 1L;
    //Atributos
    private Mascota[] Lista;
    private static int cantMascotas = 0;

    //Constructor
    public ArregloMascotas() {
        Lista = new Mascota[50]; // 50 espacios de almacenamiento
    }

    //Metodos
    public void AgregarMascota(Mascota mascota) {
        // Crear un nuevo array con una longitud mayor en 1
        Mascota[] tempList = new Mascota[Lista.length + 1];

        // Copiar los elementos del array actual al nuevo array
        for (int i = 0; i < Lista.length; i++) {
            tempList[i] = Lista[i];
        }

        // Agregar la nueva mascota al final del nuevo array
        tempList[Lista.length] = mascota;

        // Asignar el nuevo array a la referencia de Lista
        Lista = tempList;
    }

    public void EliminarMascota(String idMascota) {
        // Crear una lista temporal para almacenar las mascotas que no ser�n eliminadas
        ArrayList<Mascota> list = new ArrayList<>();

        // Recorrer el array de mascotas y agregar a la lista temporal las mascotas que no tengan el ID especificado
        for (Mascota m : Lista) {
            if (m != null && !m.getCodigo().equals(idMascota)) {
                list.add(m);
            }
        }

        // Convertir la lista temporal de vuelta a un array
        Mascota[] tempL = new Mascota[list.size()];
        tempL = list.toArray(tempL);

        // Asignar el nuevo array a la referencia de Lista
        Lista = tempL;
    }

    //Crear Archivo Binario con los datos
    public void GuardarEnArchivo() {
        try {
            FileOutputStream fos = new FileOutputStream("ListaMascotas.bin");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(Lista);
//            System.out.println("ARCHIVO ACTUALIZADO");
            oos.close();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "ERROR no se puede guardar.." + ex);
        }
    }

    //Metodo para recuperar los datos del Archivo Binario
    public void RecuperarDeArchivo() {
        try {
            FileInputStream fis = new FileInputStream("ListaMascotas.bin");
            ObjectInputStream ois = new ObjectInputStream(fis);
            Lista = (Mascota[]) ois.readObject();
            ois.close();
        } catch (IOException | ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "ERROR no se puede recuperar.." + ex);
        }
    }
    //metodo que actualizará el contador luego de recuperar los datos del archivo binario

    public void ActualizarCantidadMascotas() {
        cantMascotas = 0;
        for (Mascota mascota : Lista) {
            if (mascota != null) {
                cantMascotas++;
            }
        }
    }
    //método que mostrar en pantalla la informacion cantidad empleados por area y suma sueldo

    //Getter and Setters
    public Mascota[] getLista() {
        return Lista;
    }

    public void setLista(Mascota[] Lista) {
        this.Lista = Lista;
    }

    public static int getCantMascotas() {
        return cantMascotas;
    }

    public static void setCantMascotas(int aCantMascotas) {
        cantMascotas = aCantMascotas;
    }

}
