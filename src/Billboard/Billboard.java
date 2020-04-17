package Billboard;

public class Billboard {
    private String description;
    private String img_url;
    public Billboard(String description, String img_url){
        this.description = description;
        this.img_url = img_url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }
}
