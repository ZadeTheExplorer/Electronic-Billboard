package Billboard;

public class Billboard {
    private String topText;
    private String bottomDescription;
    private String img_url;
    public Billboard(String text, String description, String img_url){
        this.bottomDescription = description;
        this.img_url = img_url;
        this.topText = text;
    }

    public String getDescription() {
        return bottomDescription;
    }

    public void setDescription(String description) {
        this.bottomDescription = description;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }
}
