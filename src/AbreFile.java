
import java.io.File;
import javax.swing.JFileChooser;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author igorr
 */
public class AbreFile {
    String ruta;
    static String rutaAuxiliar;
    File file;

    public void setFile(File file) {
        this.file = file;
    }

    public File getFile() {
        return file;
    }
    

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public String getRutaAuxiliar() {
        return rutaAuxiliar;
    }

    public int dameFile() {

        try {
            JFileChooser miJFC = new JFileChooser();
            int seleccion = miJFC.showOpenDialog(null);
            if (seleccion == JFileChooser.APPROVE_OPTION) {
                file = miJFC.getSelectedFile();
                ruta=file.getAbsolutePath();
                rutaAuxiliar=ruta;
                return 1;
            }
            if (seleccion == JFileChooser.CANCEL_OPTION) {
                miJFC.cancelSelection();
            }

        } catch (Exception e) {
            System.out.println("El error producido en la apertura de file" + e.getMessage() + "||||" + e.getLocalizedMessage());

        }
        return -1;
    }

}
