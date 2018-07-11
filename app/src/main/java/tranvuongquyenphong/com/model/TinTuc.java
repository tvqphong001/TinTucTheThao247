package tranvuongquyenphong.com.model;

import java.util.Date;

public class TinTuc {
    public String title,link,hinhanh,nguonbao,luotxem,theloai;
    public Date ngaydang;

    public TinTuc() {
    }

    public TinTuc(String title, String link, String hinhanh, String nguonbao, Date ngaydang) {
        this.title = title;
        this.link = link;
        this.hinhanh = hinhanh;
        this.nguonbao = nguonbao;
        this.ngaydang = ngaydang;
    }

    public TinTuc(String title, String link, String hinhanh, String nguonbao, String luotxem, String theloai, Date ngaydang) {
        this.title = title;
        this.link = link;
        this.hinhanh = hinhanh;
        this.nguonbao = nguonbao;
        this.luotxem = luotxem;
        this.theloai = theloai;
        this.ngaydang = ngaydang;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(String hinhanh) {
        this.hinhanh = hinhanh;
    }

    public String getNguonbao() {
        return nguonbao;
    }

    public void setNguonbao(String nguonbao) {
        this.nguonbao = nguonbao;
    }

    public String getLuotxem() {
        return luotxem;
    }

    public void setLuotxem(String luotxem) {
        this.luotxem = luotxem;
    }

    public String getTheloai() {
        return theloai;
    }

    public void setTheloai(String theloai) {
        this.theloai = theloai;
    }

    public Date getNgaydang() {
        return ngaydang;
    }

    public void setNgaydang(Date ngaydang) {
        this.ngaydang = ngaydang;
    }
}
