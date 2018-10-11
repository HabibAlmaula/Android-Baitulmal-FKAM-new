package com.baitulmalfkam.baitulmalfkam.POJO;

public class Program {
    public String namaProgram;
    public int gambarProgram;
    public String detailprogram;

    public Program(String namaProgram, int gambarProgram, String detailprogram) {
        this.namaProgram = namaProgram;
        this.gambarProgram = gambarProgram;
        this.detailprogram = detailprogram;
    }

    public String getNamaProgram() {
        return namaProgram;
    }

    public void setNamaProgram(String namaProgram) {
        this.namaProgram = namaProgram;
    }

    public int getGambarProgram() {
        return gambarProgram;
    }

    public void setGambarProgram(int gambarProgram) {
        this.gambarProgram = gambarProgram;
    }

    public String getDetailprogram() {
        return detailprogram;
    }

    public void setDetailprogram(String detailprogram) {
        this.detailprogram = detailprogram;
    }
}
