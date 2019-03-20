/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Desktop;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Hoang Pham
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try {
            openPicFolder();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean checkDob(String date) {
        boolean valid = false;
        try {
            DateFormat format2 = new SimpleDateFormat("yyyy-M-d");
            DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-M-d");
            format2.setLenient(false);
            LocalDate birthday2 = LocalDate.parse(date, formatter2);
            format2.parse(date);
            int age2 = Period.between(birthday2, LocalDate.now()).getYears();
            System.out.println(birthday2);
            System.out.println(LocalDate.now());
            if (age2 >= 18) {
                valid = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return valid;
    }

    public static void openPicFolder() throws Exception {
        File f = new File("web\\picture\\T1-cover.jpg");
        Desktop.getDesktop().open(f);
    }

}
