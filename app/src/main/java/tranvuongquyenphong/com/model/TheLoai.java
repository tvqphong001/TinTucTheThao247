package tranvuongquyenphong.com.model;

public class TheLoai{
    String id, tenLoai, link, nguontin;

    public TheLoai(String id, String tenLoai, String link, String nguontin) {
        this.id = id;
        this.tenLoai = tenLoai;
        this.link = link;
        this.nguontin = nguontin;
    }

    public TheLoai() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTenLoai() {
        return tenLoai;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getNguontin() {
        return nguontin;
    }

    public void setNguontin(String nguontin) {
        this.nguontin = nguontin;
    }
}
