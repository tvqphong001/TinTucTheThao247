package tranvuongquyenphong.com.model;

public class TheLoai{
    String id,ten,urlHinh;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getUrlHinh() {
        return urlHinh;
    }

    public void setUrlHinh(String urlHinh) {
        this.urlHinh = urlHinh;
    }

    public TheLoai() {
    }

    public TheLoai(String id, String ten, String urlHinh) {
        this.id = id;
        this.ten = ten;
        this.urlHinh = urlHinh;
    }
}
