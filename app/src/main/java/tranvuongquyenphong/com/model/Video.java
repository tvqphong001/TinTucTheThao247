package tranvuongquyenphong.com.model;

public class Video {


    String id,linkvideo,mota,idtin;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLinkvideo() {
        return linkvideo;
    }

    public void setLinkvideo(String linkvideo) {
        this.linkvideo = linkvideo;
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

    public Video() {
    }

    public Video(String id, String linkvideo, String mota, String idtin) {
        this.id = id;
        this.linkvideo = linkvideo;
        this.mota = mota;
        this.idtin = idtin;
    }
}
