package Pila;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.TreeSet;
import javax.swing.JOptionPane;
import Persistencia.*;
import Procesos.*;

/**
 *
 * @author FAMILIA
 */
public class PilaEmpleado implements Serializable {

    private static final long serialVersionUID = 1L;
    LinkedList<Empleado> pila;
    HashMap<String, Empleado> tablaHash;
    TreeSet<Empleado> arbolBinario;

    public PilaEmpleado() {
        pila = new LinkedList<>();
        tablaHash = new HashMap<>();
        arbolBinario = new TreeSet<>();
        //pila = DatosOperario.RecuperaDeArchivo().getPila();
    }

    // M�todo que agrega datos a la pila
    public void Agregar(Empleado obj) {
        pila.push(obj);
        tablaHash.put(obj.getCod(), obj);
        arbolBinario.add(obj);
        DatosEmpleado.GuardarEnArchivo(this);
    }

    // M�todo que verifica el primer elemento de la pila
    public void VerPrimerElemento() {
        Mensajes.MostrarTexto(pila.peekFirst().toString());
    }

    // M�todo que verifica el �ltimo elemento de la pila
    public void VerUltimoElemento() {
        Mensajes.MostrarTexto(pila.peekLast().toString());
    }

    // M�todo para eliminar un elemento de la pila
    public void Eliminar() {
        String codigo = JOptionPane.showInputDialog(null, "Ingrese el c�digo del empleado a eliminar:");
        if (codigo == null || codigo.isEmpty()) {
            JOptionPane.showMessageDialog(null, "C�digo no ingresado. Operaci�n cancelada.");
            return;
        }

        Empleado eliminado = tablaHash.remove(codigo);
        if (eliminado == null) {
            JOptionPane.showMessageDialog(null, "El c�digo " + codigo + " no existe en la pila.");
            return;
        }

        pila.remove(eliminado);
        arbolBinario.remove(eliminado);
        DatosEmpleado.GuardarEnArchivo(this);
        JOptionPane.showMessageDialog(null, "Empleado con c�digo " + codigo + " eliminado correctamente.");
    }

    // M�todo que busca un dato en la pila usando la tabla hash
    public void BuscarPorCodigo(String codbuscado) {
        Empleado encontrado = tablaHash.get(codbuscado);
        if (encontrado != null) {
            Mensajes.MostrarTexto(encontrado.toString());
        } else {
            Mensajes.MostrarTexto("El c�digo " + codbuscado + " no existe en la pila");
        }
    }

    // Getter y setter
    public LinkedList<Empleado> getPila() {
        return pila;
    }

    public void setPila(LinkedList<Empleado> pila) {
        this.pila = pila;
    }
}
