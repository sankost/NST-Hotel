/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package report;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import domen.Boravak;
import domen.Uplata;;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import sb.boravak.SBboravakLocal;


/**
 *
 * @author Sanja
 */

@ManagedBean
@RequestScoped
public class Report implements Serializable {

    Document document;
    private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 25, Font.BOLD);
    private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.NORMAL);

    private List<Boravak> listaBoravaka;
    private Boravak boravak;

    @EJB
    private SBboravakLocal sbBoravak;

    public Report() {
        document = new Document();
        boravak = null;
    }

    @PostConstruct
    public void inicijalizujPodatke() {
        listaBoravaka = sbBoravak.vratiListuBoravaka();
    }

    public List<Boravak> getListaBoravaka() {
        return listaBoravaka;
    }

    public void setListaBoravaka(List<Boravak> listaBoravaka) {
        this.listaBoravaka = listaBoravaka;
    }

    public Boravak getBoravak() {
        return boravak;
    }

    public void napraviPDF() throws DocumentException, FileNotFoundException, IOException {
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd.mm.yyyy.");
        List<Boravak> lista = sbBoravak.vratiListuBoravaka();
        FileOutputStream fos = new FileOutputStream("C:\\Users\\Sanja\\Desktop\\report\\boravak_"+sdf1.format(new Date())+"_"+(int)(Math.random() * 100000)+".pdf");
        PdfWriter.getInstance(document, fos);
        document.open();
        document.add(new Paragraph("Izvestaj o evidentiranim boravcima u hotelu: ", catFont));
        addEmptyLine(4);
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy.");
            Paragraph datum = new Paragraph("Datum izvestaja:" + sdf.format(new Date()), subFont);
            datum.setAlignment(Element.ALIGN_LEFT);
            document.add(datum);
            addEmptyLine(2);
        Paragraph subCatPart = new Paragraph("");
        dodajTabeluBoravaka(subCatPart, lista);
        document.add(subCatPart);
        addEmptyLine(3);
        Paragraph p = new Paragraph(18, "Ukupan iznos:", subFont);
        p.setAlignment(Element.ALIGN_RIGHT);
        document.add(p);
        Paragraph p1 = new Paragraph(18, ukupanIznos(lista)+"", subFont);
        p1.setAlignment(Element.ALIGN_RIGHT);
        document.add(p1);
        document.close();
        fos.close();
    }

    private void addEmptyLine(int number) {
        Paragraph paragraph = new Paragraph();
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
        try {
            document.add(paragraph);
        } catch (DocumentException ex) {
            Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void dodajTabeluBoravaka(Paragraph subCatPart, List<Boravak> lista)
            throws BadElementException {
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy.");

        PdfPTable table = new PdfPTable(6);

        PdfPCell c1 = new PdfPCell(new Phrase("Sifra boravka"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Pocetak:"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Kraj boravka"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Gost"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Cena"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
        
        c1 = new PdfPCell(new Phrase("Placeno"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        for (Boravak bor : lista) {
            table.addCell("" + bor.getBoravakID());
            table.addCell(sdf.format(bor.getDatumOd()));
            table.addCell(sdf.format(bor.getDatumDo()));
            table.addCell(bor.getGostID().getIme() + " " + bor.getGostID().getPrezime());
            table.addCell(bor.getCena() + "");
            table.addCell(ukupnoPlaceno(bor.getUplataList())+"");
        }
        subCatPart.add(table);
    }


    public double ukupanIznos(List<Boravak> lista) {
        double sum = 0;
        for (Boravak bor : lista) {
            sum += bor.getCena();
        }
        return sum;
    }
    
    public static double ukupnoPlaceno (List<Uplata> list){
         double sum = 0;
        for (Uplata uplata : list) {
            sum += uplata.getIznos();
        }
        return sum;
    }

}
