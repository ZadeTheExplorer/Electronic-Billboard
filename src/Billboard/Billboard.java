package Billboard;

public class Billboard {
    private String message;
    private String information;
    private String img_url;
    public Billboard(String message, String information, String img_url){
        this.information = information;
        this.img_url = img_url;
        this.message = message;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public void delete(int id){

    }

    //TODO: XMLFile.create(Billboard billboard)
    public void exportXML() {
        XMLFile.create(this);
    }
}
