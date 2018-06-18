package tranvuongquyenphong.com.model;

import java.util.Date;

public class News {
    String idtin;
    String tieudetin;
    String keyword;
    String solanxem;
    String ngaydang;
    String idtheloai;
    String noidung;
    String urlhinh;

    public String getIdtin() {
        return idtin;
    }

    public void setIdtin(String idtin) {
        this.idtin = idtin;
    }

    public String getTieudetin() {
        return tieudetin;
    }

    public void setTieudetin(String tieudetin) {
        this.tieudetin = tieudetin;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getSolanxem() {
        return solanxem;
    }

    public void setSolanxem(String solanxem) {
        this.solanxem = solanxem;
    }

    public String getNgaydang() {
        return ngaydang;
    }

    public void setNgaydang(String ngaydang) {
        this.ngaydang = ngaydang;
    }

    public String getIdtheloai() {
        return idtheloai;
    }

    public void setIdtheloai(String idtheloai) {
        this.idtheloai = idtheloai;
    }

    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }

    public String getUrlhinh() {
        return urlhinh;
    }

    public void setUrlhinh(String urlhinh) {
        this.urlhinh = urlhinh;
    }

    public News() {
    }

    public News(String idtin, String tieudetin, String keyword, String solanxem, String ngaydang, String idtheloai, String noidung, String urlhinh) {
        this.idtin = idtin;
        this.tieudetin = tieudetin;
        this.keyword = keyword;
        this.solanxem = solanxem;
        this.ngaydang = ngaydang;
        this.idtheloai = idtheloai;
        this.noidung = noidung;
        this.urlhinh = urlhinh;
    }
}
