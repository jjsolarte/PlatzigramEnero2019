package co.jeisonsolarte.aplicandomaterialdesign.model;

public class CardviewPicturePOJO {

    private String img;
    private String username;
    private String time;
    private String likeNumbre = "0 días";

    public CardviewPicturePOJO(String img, String username, String time, String likeNumbre) {
        this.img = img;
        this.username = username;
        this.time = time;
        this.likeNumbre = likeNumbre;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLikeNumbre() {
        return likeNumbre;
    }

    public void setLikeNumbre(String likeNumbre) {
        this.likeNumbre = likeNumbre;
    }

}
