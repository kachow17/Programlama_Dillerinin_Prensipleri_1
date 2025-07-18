/**
*
* @author Mert ÇALIŞKAN ve mert.caliskan3@ogr.sakarya.edu.tr
* @since 23.04.2023
* <p>
* Main sınıfı ve bütün kaynak kodlar burada
* </p>
*/

package Main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws Exception {
        String dosyaYolu = args[0];
        BufferedReader bufferedReader = new BufferedReader(new FileReader(dosyaYolu));
        String satir;
        boolean fonksiyonIci = false;
        Pattern fonksiyonDeseni = Pattern.compile("\\s*(public|private|protected)?\\s*\\w+\\s+\\w+\\s*\\([^\\)]*\\)\\s*\\{");
        Pattern tekliYorumDeseni = Pattern.compile("//.*");
        Pattern cokluYorumDeseni = Pattern.compile("/\\*.*?\\*/", Pattern.DOTALL);
        ArrayList<String> tekliYorumlar = new ArrayList<String>();
        ArrayList<String> cokluYorumlar = new ArrayList<String>();
        String fonksiyonAdi = "";
        while ((satir = bufferedReader.readLine()) != null) {
            Matcher fonksiyonMatcher = fonksiyonDeseni.matcher(satir);
            int sayac = 0;
            if (fonksiyonMatcher.matches()) {
                if (fonksiyonIci) {
                   
                    System.out.println("Fonksiyon: " + fonksiyonAdi.trim());
                    System.out.println("Tekli Yorumlar:");
                    for (String yorum : tekliYorumlar) {
                        sayac++;
                        System.out.println(yorum.trim());
                    }
                    System.out.println("***Tekli Yorum Sayisi : " + sayac + "***");
                    sayac = 0;
                    System.out.println("");
                    System.out.println("Çoklu Yorumlar:");
                    for (String yorum : cokluYorumlar) {
                        System.out.println(yorum.trim());
                        sayac++;
                        System.out.println("***Coklu Yorum Sayisi : " + sayac + "***");
                    }
                    sayac = 0;
                    System.out.println("------------------------------------" +
                            "" +
                            "");
                    
                    tekliYorumlar.clear();
                    cokluYorumlar.clear();
                }
                
                fonksiyonAdi = satir.trim();
                fonksiyonIci = true;
            } else if (fonksiyonIci) {
                Matcher tekliYorumMatcher = tekliYorumDeseni.matcher(satir);
                Matcher cokluYorumMatcher = cokluYorumDeseni.matcher(satir);
                while (cokluYorumMatcher.find()) {
                    cokluYorumlar.add(cokluYorumMatcher.group());
                }
                while (tekliYorumMatcher.find()) {
                    tekliYorumlar.add(tekliYorumMatcher.group());
                }
            }
        }
        
        System.out.println("Fonksiyon: " + fonksiyonAdi.trim());
        System.out.println("Tekli Yorumlar:");
        for (String yorum : tekliYorumlar) {
            System.out.println(yorum.trim());
        }
        System.out.println("Çoklu Yorumlar:");
        for (String yorum : cokluYorumlar) {
            System.out.println(yorum.trim());
        }
        bufferedReader.close();
        }
}