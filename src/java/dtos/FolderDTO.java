/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import java.awt.Desktop;
import java.io.File;

/**
 *
 * @author Hoang Pham
 */
public class FolderDTO {

    File f;

    public FolderDTO() {
    }

    public File getF() {
        return f;
    }

    public void setF(File f) {
        this.f = f;
    }

    public void openPicFolder() throws Exception {
        f = new File("web\\picture");
        Desktop.getDesktop().open(f);
    }
}
