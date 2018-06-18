package tranvuongquyenphong.com.model;

public class HinhAnh {
    String id,urlhinh,mota,idtin;

    public HinhAnh(String id, String urlhinh, String mota, String idtin) {
        this.id = id;
        this.urlhinh = urlhinh;
        this.mota = mota;
        this.idtin = idtin;
    }

    public HinhAnh() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrlhinh() {
        return urlhinh;
    }

    public void setUrlhinh(String urlhinh) {
        this.urlhinh = urlhinh;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public String getIdtin() {
        return idtin;
    }

    public void setIdtin(String idtin) {
        this.idtin = idtin;
    }
}
