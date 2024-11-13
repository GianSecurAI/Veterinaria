package Procesos;

import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;

/**
 *
 * @author NITRO 5
 */
public class PresentarMenu {

    public static void Centrar(JInternalFrame frame, JDesktopPane escritorio) {
        escritorio.removeAll();
        escritorio.add(frame);
        frame.setVisible(true);
        // Obtener el tama�o del escritorio
        Dimension tamanioescritorio = escritorio.getSize();
        // Establecer el tama�o del frame para que coincida con el tama�o del escritorio
        frame.setSize(tamanioescritorio);
        // Establecer la localizaci�n en (0,0) ya que el tama�o es el mismo que el del escritorio
        frame.setLocation(0, 0);
        // Asignar un �cono al frame
        ImageIcon icon = new ImageIcon("/_Icons/logotipo.png"); // Aseg�rate de que la ruta sea correcta
        JFrame parentWindow = (JFrame) javax.swing.SwingUtilities.windowForComponent(escritorio);
        parentWindow.setTitle("Clinica Veterinaria - " + frame.getTitle());
        frame.setFrameIcon(icon);
        frame.show();
        escritorio.repaint();
    }

}
