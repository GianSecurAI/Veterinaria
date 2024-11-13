package Controlador;

import Modelo.Cita;
import Modelo.Mascota;
import Modelo.Servicio;
import Persistencia.DataCita;
import Persistencia.DataServicio;
import Pila.Empleado;
import Vista.VentanaCitas;
import com.itextpdf.text.Chunk;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JTable;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import java.awt.Component;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.TableModel;

public class ControladorV2 {

    VentanaCitas c;
    DefaultTableModel modelo;
    int filaSeleccionada = -1;
    DataCita dataCita;

    public ControladorV2(VentanaCitas c) {
        this.c = c;
    }

    public void init() {
        modelo = (DefaultTableModel) c.tblDatosCita.getModel();
        dataCita = new DataCita();
        filaSeleccionada = 0;
        limpiar();
        c.tblDatosCita.getTableHeader().setReorderingAllowed(false);
    }

    public void consultar() {
        modelo.setRowCount(0);
        modelo = dataCita.consultar(c.tblDatosCita);
        c.tblDatosCita.setModel(modelo);
//        c.tblDatosCita.setRowSelectionInterval(filaSeleccionada, filaSeleccionada);
    }

    public void limpiar() {
        eventoSeleccionServicio();
        rellenarComboIDMascota();

        c.txtCodCita.setText(getIdActual());
//        c.comboIdMascota.setSelectedIndex(0);
        c.txtIDPropietario.setText("");
//        c.comboIdDoctor.setSelectedIndex(0);
//        c.cbxServicio.setSelectedIndex(0);
        c.txaDetalleCita.setText("");
        consultar();
        selectInfoPropietario();
        filaSeleccionada = -1;
    }

    public void guardar() {
        String codigoCita = c.txtCodCita.getText();
        String idMascota = c.comboIdMascota.getSelectedItem().toString();
        String idPropietario = c.txtIDPropietario.getText();
        String nombreDoctor = c.comboIdDoctor.getSelectedItem().toString();
        Timestamp fecha = obtenerFechaActual();
        if (sinVacio(codigoCita, idMascota, idPropietario, nombreDoctor)) {
            int res = JOptionPane.YES_NO_OPTION;
            int result = JOptionPane.showConfirmDialog(null, "Confirmar registro", "Confirmacion", res);
            if (result == 0) {
                String servicio = c.cbxServicio.getSelectedItem().toString();
                /**
                 * *
                 * 0: pendiente 1: cancelado 2: atendido
                 */
                Cita cit = new Cita(codigoCita, idMascota, idPropietario, nombreDoctor, servicio, fecha, 0);
                dataCita.registrar(cit);
                consultar();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Rellene todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void clickTabla() {
        filaSeleccionada = c.tblDatosCita.getSelectedRow();
        String codigoCita = (String) c.tblDatosCita.getValueAt(filaSeleccionada, 0);
        String idMascota = (String) c.tblDatosCita.getValueAt(filaSeleccionada, 1);
        String idPropietario = (String) c.tblDatosCita.getValueAt(filaSeleccionada, 2);
        String nombreDoctor = (String) c.tblDatosCita.getValueAt(filaSeleccionada, 3);
        String servicio = (String) c.tblDatosCita.getValueAt(filaSeleccionada, 4);
        String fecha = c.tblDatosCita.getValueAt(filaSeleccionada, 5).toString();
        String nameEstado = c.tblDatosCita.getValueAt(filaSeleccionada, 6).toString();
        int parseEstado = Cita.parseEstado(nameEstado);
        c.comboEstado.setSelectedIndex(parseEstado);
        imprimirInfoTextArea(codigoCita, idMascota, idPropietario, nombreDoctor, servicio, fecha);
    }

    private void rellenarComboIDMascota() {
        try {
            c.comboIdMascota.removeAllItems();
            for (Mascota mas : ControladorV1.listaMascotas()) {
                c.comboIdMascota.addItem(mas.getCodigo());
            }
        } catch (Exception e) {
            System.out.println("Error" + e);
        }

    }
    int idPosicionServicio = 0;

    public void rellenarComboDoctores() {
        try {
            idPosicionServicio = c.cbxServicio.getSelectedIndex();
            if (idPosicionServicio > -1) {
                ArrayList<Servicio> listaServiciosEncontrador = new ArrayList<>();
                listaServiciosEncontrador = serviciosEncontrador();
                Servicio serTemp = listaServiciosEncontrador.get(idPosicionServicio);
//            System.out.println("ID SERVICIO: " + listaServiciosEncontrador.get(idPosicionServicio).getIdServicio());
                c.comboIdDoctor.removeAllItems();
                for (Empleado emp : ControladorV4.listaDoctores("doctor")) {
                    String areaTemp = serTemp.getArea();
                    if (areaTemp.equals(emp.getArea())) {
                        c.comboIdDoctor.addItem(emp.getNombre());
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error" + e);
        }

    }

    public void selectInfoPropietario() {

        c.comboIdMascota.addActionListener((java.awt.event.ActionEvent evt) -> {
            try {
                /*Recojo la mascota seleccionada en el combo idMascota                
                 */
                String idMascota = (String) c.comboIdMascota.getSelectedItem();
                /*hago un for each recoriendo toda la lista de mascotas que existen 
                 */
                for (Mascota mas : ControladorV1.listaMascotas()) {
                    //Recojo temporalmente el código del propietario asignado a la mascota                    
                    String idPropietario = mas.getCodigo();
                    //Una vez teniendo el´código del propietario 
                    //posteriormente comparamos el id de la mascota seleccionada
                    //y procedemos a mostrar el id del propietario asignado
                    if (idPropietario.equals(idMascota)) {
                        //y se muestra automáticamente en el TXTPropietario
                        c.txtIDPropietario.setText(mas.getPropietario());
                    }
                }
            } catch (Exception e) {
                System.out.println("Error" + e);
            }

        });
    }

    public void cambiarEstado() {
        if (filaSeleccionada > -1) {
            String codigoCita = (String) c.tblDatosCita.getValueAt(filaSeleccionada, 0);
            Cita citEditada = dataCita.obtenerCita(codigoCita);
            int estadoSelect = c.comboEstado.getSelectedIndex();
            citEditada.setEstado(estadoSelect);
            dataCita.actualizar(citEditada);
            consultar();
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione una cita", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void imprimirInfoTextArea(String codigoCita, String idMascota, String idPropietario, String nombreDoctor, String servicio, String fecha) {
        c.txaDetalleCita.setText("");
        c.txaDetalleCita.append("\n\t\t\t\tINFO CITA\t\t");
        c.txaDetalleCita.append("\n");
        c.txaDetalleCita.append("\t - COD          : " + codigoCita);
        c.txaDetalleCita.append("\n");
        c.txaDetalleCita.append("\t - IdMascota    : " + idMascota);
        c.txaDetalleCita.append("\n");
        c.txaDetalleCita.append("\t - IdPropietario: " + idPropietario);
        c.txaDetalleCita.append("\n");
        c.txaDetalleCita.append("\t - NombreDoctor : " + nombreDoctor);
        c.txaDetalleCita.append("\n");
        c.txaDetalleCita.append("\t - Servicio     : " + servicio);
        c.txaDetalleCita.append("\n");
        c.txaDetalleCita.append("\t - Fecha        : " + fecha);
        c.txaDetalleCita.append("\n");
    }

    private int ID_ACTUAL() {
        try {
            int idActual = 0;
            for (Cita citaa : new Persistencia.DataCita().obtenerTodasLasCitas()) {
                idActual = Integer.parseInt(citaa.getCodigoCita());
            }
            return idActual;
        } catch (NumberFormatException e) {
            System.out.println("ERROR: " + e.getMessage());
            return 0;
        }
    }

    private String getIdActual() {
        int valorActualId = ID_ACTUAL() + 1;
        String nuevaID = String.format("%08d", valorActualId);
        return nuevaID;
    }

    public void eliminar() {
        if (filaSeleccionada > -1) {
            int res = JOptionPane.YES_NO_OPTION;
            int result = JOptionPane.showConfirmDialog(null, "Confirmar eliminacion", "Confirmacion", res);
            if (result == 0) {
                String codigoCita = (String) c.tblDatosCita.getValueAt(filaSeleccionada, 0);
                dataCita.borrar(codigoCita);
                limpiar();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione una fila", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private Timestamp obtenerFechaActual() {
        Date fechaActual = new Date();
        Timestamp timestamp = new Timestamp(fechaActual.getTime());
        return timestamp;
    }

    private boolean sinVacio(String... txts) {
        for (String t : txts) {
            if (t.equals("")) {
                return false;
            }
        }
        return true;
    }

    public void buscarCodigo() {
        DefaultTableModel filtro = buscarEnColumna(modelo, 0, c.txtBuscarCod.getText());
        c.tblDatosCita.setModel(filtro);
    }

    public void ordenar() {
        int columna = c.cbxOrdenarCita.getSelectedIndex();
        ordenarPorColumna(c.tblDatosCita, columna, true);
    }

    private DefaultTableModel buscarEnColumna(DefaultTableModel model, int numeroColumna, String datoBuscar) {
        DefaultTableModel filteredModel = (new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Codigo Cita", "ID Mascota", "ID Propietario", "Nombre Doctor", "Servicio", "Fecha", "Estado"
                }
        ) {
            boolean[] canEdit = new boolean[]{
                false, false, false, false, false, false, false
            };

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        int fila = buscarBinario(model, numeroColumna, datoBuscar);

        if (fila != -1) {
            Object[] rowData = new Object[model.getColumnCount()];
            for (int i = 0; i < model.getColumnCount(); i++) {
                rowData[i] = model.getValueAt(fila, i);
            }
            filteredModel.addRow(rowData);
        } else {
//            System.out.println("No se encontró ninguna fila con el dato especificado en la columna " + numeroColumna);
        }

        return filteredModel;
    }

    private int buscarBinario(DefaultTableModel model, int numeroColumna, String datoBuscar) {
        int inicio = 0;
        int fin = model.getRowCount() - 1;

        while (inicio <= fin) {
            int medio = (inicio + fin) / 2;
            String datoMedio = model.getValueAt(medio, numeroColumna).toString();
            int comparacion = datoMedio.compareTo(datoBuscar);

            if (comparacion == 0) {
                return medio;
            } else if (comparacion < 0) {
                inicio = medio + 1;
            } else {
                fin = medio - 1;
            }
        }

        return -1;
    }

    private static void ordenarPorColumna(JTable table, int numeroColumna, boolean ascendente) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();

        Object[][] rowData = new Object[model.getRowCount()][model.getColumnCount()];
        for (int i = 0; i < model.getRowCount(); i++) {
            for (int j = 0; j < model.getColumnCount(); j++) {
                rowData[i][j] = model.getValueAt(i, j);
            }
        }

        Arrays.sort(rowData, (Object[] o1, Object[] o2) -> {
            Comparable<Object> c1 = (Comparable<Object>) o1[numeroColumna];
            Comparable<Object> c2 = (Comparable<Object>) o2[numeroColumna];
            return c1.compareTo(c2);
        });

        if (!ascendente) {
            for (int i = 0; i < rowData.length / 2; i++) {
                Object[] temp = rowData[i];
                rowData[i] = rowData[rowData.length - i - 1];
                rowData[rowData.length - i - 1] = temp;
            }
        }

        model.setRowCount(0);

        for (Object[] row : rowData) {
            model.addRow(row);
        }
    }

    public void rellenarComboServicio() {
        DataServicio dataSer = new DataServicio();
        String codMascota = c.comboIdMascota.getSelectedItem().toString();
        c.cbxServicio.removeAllItems();
        for (Servicio se : dataSer.consultar()) {
            String tempMascota = se.getIdMascota();
            if (tempMascota.equals(codMascota)) {
                c.cbxServicio.addItem(se.getNomServicio());
            }
        }
    }

    public ArrayList<Servicio> serviciosEncontrador() {
        ArrayList<Servicio> listaServicios = new ArrayList<>();
        DataServicio dataSer = new DataServicio();
        String codMascota = c.comboIdMascota.getSelectedItem().toString();
        for (Servicio se : dataSer.consultar()) {
            String tempMascota = se.getIdMascota();
            if (tempMascota.equals(codMascota)) {
                listaServicios.add(se);
            }
        }
        return listaServicios;
    }

    private void eventoSeleccionServicio() {
        c.cbxServicio.addActionListener((java.awt.event.ActionEvent evt) -> {
            rellenarComboDoctores();
        });
    }

    public void exportarDatosATabla(JTable tabla, Component parentComponent) {
        // Obtener el modelo de la tabla
        TableModel modelo = tabla.getModel();

        // Crear el documento PDF
        Document documento = new Document();
        try {
            // Especificar el nombre del archivo PDF
            String fileName = "Informe_Citas_Veterinaria.pdf"; // Puedes personalizar el nombre
            PdfWriter.getInstance(documento, new FileOutputStream(fileName));
            documento.open();

            // Agregar un logo o encabezado de la veterinaria (si tienes uno)
            Image logo = Image.getInstance("src/_Icons/Logo_128px.png");
            documento.add(logo);
            // Título del informe
            Paragraph titulo = new Paragraph("Informe de Citas", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 24));
            titulo.setAlignment(Element.ALIGN_CENTER);
            documento.add(titulo);

            // Espacio
            documento.add(new Paragraph("\n"));

            // Información de la veterinaria
            Paragraph infoVeterinaria = new Paragraph("Veterinaria PetVet\n"
                    + "Teléfono: 123-456-7890\n"
                    + "Email: contacto@veterinariapetvet.com\n"
                    + "Dirección: Calle Real 123, Ciudad\n",
                    FontFactory.getFont(FontFactory.HELVETICA, 12));
            infoVeterinaria.setAlignment(Element.ALIGN_CENTER);
            documento.add(infoVeterinaria);

            // Espacio
            documento.add(new Paragraph("\n"));

            // Crear una tabla para los datos
            PdfPTable tablaDatos = new PdfPTable(modelo.getColumnCount());
            tablaDatos.setWidthPercentage(100);

            // Agregar encabezados a la tabla
            for (int j = 0; j < modelo.getColumnCount(); j++) {
                PdfPCell celda = new PdfPCell(new Phrase(modelo.getColumnName(j), FontFactory.getFont(FontFactory.HELVETICA_BOLD)));
                celda.setHorizontalAlignment(Element.ALIGN_CENTER);
                tablaDatos.addCell(celda);
            }

            // Agregar datos a la tabla
            for (int i = 0; i < modelo.getRowCount(); i++) {
                for (int j = 0; j < modelo.getColumnCount(); j++) {
                    tablaDatos.addCell(modelo.getValueAt(i, j).toString());
                }
            }

            // Agregar la tabla al documento
            documento.add(tablaDatos);

            // Espacio
            documento.add(new Paragraph("\n"));

            // Agregar un pie de página
            Paragraph piePagina = new Paragraph("© 2024 Veterinaria PetVet. Todos los derechos reservados.", FontFactory.getFont(FontFactory.HELVETICA, 10));
            piePagina.setAlignment(Element.ALIGN_CENTER);
            documento.add(piePagina);

            // Mostrar mensaje de éxito
            JOptionPane.showMessageDialog(parentComponent, "Datos exportados con éxito a " + fileName, "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (DocumentException | IOException e) {
            // Mostrar mensaje de error
            JOptionPane.showMessageDialog(parentComponent, "Error al exportar datos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            // Cerrar el documento
            documento.close();
        }
    }

    public void exportarCitaSeleccionada(String codigoCita, String idMascota, String idPropietario, String nombreDoctor, String servicio, String fecha) {
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream("Cita_" + codigoCita + ".pdf"));
            document.open();

            // Logo y nombre de la veterinaria
            Image logo = Image.getInstance("src/_Icons/Logo_128px.png"); // Asegúrate de tener el logo en el directorio correcto
            logo.scaleToFit(100, 100);
            logo.setAlignment(Element.ALIGN_LEFT);
            document.add(logo);

            Paragraph nombreVeterinaria = new Paragraph("Veterinaria PetVet\n\n", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 22));
            nombreVeterinaria.setAlignment(Element.ALIGN_CENTER);
            document.add(nombreVeterinaria);

            Paragraph direccion = new Paragraph("Dirección: Av. Siempre Viva 123, Lima\nTeléfono: (01) 234-5678 | Correo: contacto@petvet.com\n\n", FontFactory.getFont(FontFactory.HELVETICA, 10));
            direccion.setAlignment(Element.ALIGN_CENTER);
            document.add(direccion);

            // Título de la cita
            Paragraph titulo = new Paragraph("Cita de Atención Veterinaria\n\n", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16));
            titulo.setAlignment(Element.ALIGN_CENTER);
            document.add(titulo);

            // Línea divisoria
            LineSeparator separator = new LineSeparator();
            document.add(new Chunk(separator));

            // Información de la cita en tabla
            PdfPTable table = new PdfPTable(2);
            table.setWidthPercentage(100);
            table.setSpacingBefore(10f);
            table.setSpacingAfter(10f);

            // Encabezado de la tabla
            PdfPCell cell;

            cell = new PdfPCell(new Phrase("Información de la Cita", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14)));
            cell.setColspan(2);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setPadding(10);
            table.addCell(cell);

            // Datos de la cita
            table.addCell("Código de Cita:");
            table.addCell(codigoCita);

            table.addCell("ID Mascota:");
            table.addCell(idMascota);

            table.addCell("ID Propietario:");
            table.addCell(idPropietario);

            table.addCell("Nombre del Doctor:");
            table.addCell(nombreDoctor);

            table.addCell("Servicio:");
            table.addCell(servicio);

            table.addCell("Fecha de la Cita:");
            table.addCell(fecha);

            document.add(table);

            // Instrucciones finales
            Paragraph instruccion = new Paragraph("Recuerde llegar 10 minutos antes de la cita. En caso de emergencia, contáctenos.\n\n", FontFactory.getFont(FontFactory.HELVETICA_OBLIQUE, 12));
            instruccion.setAlignment(Element.ALIGN_CENTER);
            document.add(instruccion);

            // Pie de página con datos de contacto de la veterinaria
            Paragraph footer = new Paragraph("Gracias por confiar en Veterinaria PetVet\n", FontFactory.getFont(FontFactory.HELVETICA, 10));
            footer.setAlignment(Element.ALIGN_CENTER);
            document.add(footer);

            document.close();
            JOptionPane.showMessageDialog(null, "PDF generado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (DocumentException | IOException e) {
            JOptionPane.showMessageDialog(null, "Error al generar el PDF: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void descargarPDFCita() {
        if (filaSeleccionada > -1) {
            // Obtener datos de la fila seleccionada
            String codigoCita = (String) c.tblDatosCita.getValueAt(filaSeleccionada, 0);
            String idMascota = (String) c.tblDatosCita.getValueAt(filaSeleccionada, 1);
            String idPropietario = (String) c.tblDatosCita.getValueAt(filaSeleccionada, 2);
            String nombreDoctor = (String) c.tblDatosCita.getValueAt(filaSeleccionada, 3);
            String servicio = (String) c.tblDatosCita.getValueAt(filaSeleccionada, 4);
            String fecha = c.tblDatosCita.getValueAt(filaSeleccionada, 5).toString();

            // Llamar al método para exportar a PDF
            exportarCitaSeleccionada(codigoCita, idMascota, idPropietario, nombreDoctor, servicio, fecha);
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione una fila para descargar su información.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }
}
