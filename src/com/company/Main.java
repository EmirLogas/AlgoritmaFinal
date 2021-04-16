package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        Dosyayi_Oku();
        Arama_Yap();
    }

    public static void Dosyayi_Oku() throws IOException {
        String Okunan;

        int Dizi1Boyut = 0;
        int Dizi2Boyut = 0;

        File Diziler = new File("Diziler.txt");
        Scanner Okuyucu = new Scanner(Diziler);
        Scanner OkuyucuVeYazici = new Scanner(Diziler);
        while (Okuyucu.hasNextLine()) {
            Okunan = Okuyucu.nextLine();
            if (Okunan.equals("Dizi1")) {
                Okunan = Okuyucu.nextLine();
                Dizi1Boyut++;
                while (!(Okunan.equals("Dizi2"))) {
                    Okunan = Okuyucu.nextLine();
                    if (!(Okunan.equals("Dizi2"))) {
                        Dizi1Boyut++;
                    }
                }
            }
            if (Okunan.equals("Dizi2")) {
                Okunan = Okuyucu.nextLine();
                Dizi2Boyut++;
                while (Okunan != null && Okuyucu.hasNextLine()) {
                    Okunan = Okuyucu.nextLine();
                    if (Okunan != null) {
                        Dizi2Boyut++;
                    }
                }
            }
        }
        int[] Dizi1 = new int[Dizi1Boyut];
        int[] Dizi2 = new int[Dizi2Boyut];
        int i = 1;
        int k = 1;

        while (OkuyucuVeYazici.hasNextLine()) {
            Okunan = OkuyucuVeYazici.nextLine();
            if (Okunan.equals("Dizi1")) {
                Okunan = OkuyucuVeYazici.nextLine();
                Dizi1[0] = Integer.parseInt(Okunan);
                while (!(Okunan.equals("Dizi2"))) {
                    Okunan = OkuyucuVeYazici.nextLine();
                    if (!(Okunan.equals("Dizi2"))) {
                        Dizi1[i] = Integer.parseInt(Okunan);
                        i++;
                    }
                }
            }
            if (Okunan.equals("Dizi2")) {
                Okunan = OkuyucuVeYazici.nextLine();
                Dizi2[0] = Integer.parseInt(Okunan);
                while (Okunan != null && OkuyucuVeYazici.hasNextLine()) {
                    Okunan = OkuyucuVeYazici.nextLine();
                    if (Okunan != null) {
                        Dizi2[k] = Integer.parseInt(Okunan);
                        k++;
                    }
                }
            }
        }
        Siralama_Yap(Dizi1, Dizi2);
    }

    public static void Siralama_Yap(int[] Dizi1, int Dizi2[]) throws IOException {

        for (int i = 0; i < Dizi1.length; i++) {
            int tempi = i;
            for (int j = i + 1; j < Dizi1.length; j++) {
                if (Dizi1[tempi] > Dizi1[j]) {
                    tempi = j;
                }
            }
            int eks = Dizi1[tempi];
            Dizi1[tempi] = Dizi1[i];
            Dizi1[i] = eks;
        }

        for (int i = 0; i < Dizi2.length; i++) {
            int tempi = i;
            for (int j = i + 1; j < Dizi2.length; j++) {
                if (Dizi2[tempi] > Dizi2[j]) {
                    tempi = j;
                }
            }
            int eks = Dizi2[tempi];
            Dizi2[tempi] = Dizi2[i];
            Dizi2[i] = eks;
        }
        Dizileri_Birlestir(Dizi1, Dizi2);
    }

    public static void Dizileri_Birlestir(int Dizi1[], int Dizi2[]) throws IOException {

        int[] Dizi3 = new int[Dizi1.length + Dizi2.length];
        int indisd1 = 0;
        int indisd2 = 0;
        for (int i = 0; i < Dizi3.length; i++) {
            if (indisd2 == Dizi2.length) {
                Dizi3[i] = Dizi1[indisd1];
                indisd1++;
            } else if (indisd1 == Dizi1.length) {
                Dizi3[i] = Dizi2[indisd2];
                indisd2++;
            } else {
                if (Dizi1[indisd1] > Dizi2[indisd2]) {
                    Dizi3[i] = Dizi2[indisd2];
                    if (indisd2 < Dizi2.length) {
                        indisd2++;
                    }
                } else if (Dizi1[indisd1] < Dizi2[indisd2]) {
                    Dizi3[i] = Dizi1[indisd1];
                    if (indisd1 < Dizi1.length) {
                        indisd1++;
                    }
                } else {
                    Dizi3[i] = Dizi1[indisd1];
                    i++;
                    Dizi3[i] = Dizi2[indisd2];
                    if (indisd1 < Dizi1.length) {
                        indisd1++;
                    }
                    if (indisd2 < Dizi2.length) {
                        indisd2++;
                    }
                }
            }


        }

        for (int i = 0; i < Dizi3.length; i++) {
            System.out.println(Dizi3[i]);
        }
        Dosyayi_Yaz(Dizi3);
    }

    public static void Dosyayi_Yaz(int Dizi3[]) throws IOException {
        File SiralanmisDiziler = new File("SiralanmisDiziler.txt");
        String Yazilacak;
        SiralanmisDiziler.createNewFile();
        FileWriter Yazici = new FileWriter(SiralanmisDiziler);
        for (int i = 0; i < Dizi3.length; i++) {
            Yazilacak = String.valueOf(Dizi3[i]);
            Yazici.write(Yazilacak + "\n");
        }
        Yazici.close();
    }

    public static void Arama_Yap() throws FileNotFoundException {
        File SiralanmisDiziler = new File("SiralanmisDiziler.txt");
        String Okunan;
        Scanner scan = new Scanner(System.in);
        while (true) {
            Scanner Okuyucu = new Scanner(SiralanmisDiziler);
            int aranansayi = 0;
            int aranansayi_Satir = 1;
            boolean SayiVar = false;
            System.out.println("Aradığınız Sayıyı giriniz");
            try {
                aranansayi = scan.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Program Sonlandırıldı");
                break;
            }
            while (Okuyucu.hasNextLine()) {
                Okunan = Okuyucu.nextLine();
                if (aranansayi == Integer.parseInt(Okunan)) {
                    System.out.println("Aranan " + aranansayi + " Sayısı " + aranansayi_Satir + ". Satırdadır");
                    SayiVar = true;
                    aranansayi_Satir++;
                } else {
                    aranansayi_Satir++;
                }
            }
            if (SayiVar == false) {
                System.out.println("Aranan " + aranansayi + " Sayısı Bulunamadı");
            }
        }
    }
}