package Billboard;

import java.io.Serializable;

//TODO: CHANGE UNIT TEST FOR BILLBOARD
public class Billboard implements Serializable {
    private String name;
    private String creator;
    private String backgroundColor;
    private String message;
    private String messageColor;
    private String picture;
    private String information;
    private String informationColor;
    public Billboard(String billboardName,String creator,String backgroundColor,String message,
                     String messageColor,String picture,String information,String informationColor){
        this.name = billboardName;
        this.creator = creator;
        this.backgroundColor = backgroundColor;
        this.message = message;
        this.messageColor = messageColor;
        this.picture = picture;
        this.information = information;
        this.informationColor = informationColor;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreatorId(String creator) {
        this.creator = creator;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessageColor() {
        return messageColor;
    }

    public void setMessageColor(String messageColor) {
        this.messageColor = messageColor;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public String getInformationColor() {
        return informationColor;
    }

    public void setInformationColor(String informationColor) {
        this.informationColor = informationColor;
    }

    @Override
    public String toString() {
        return "Billboard{" +
                "name='" + name + '\'' +
                ", creator='" + creator + '\'' +
                ", backgroundColor='" + backgroundColor + '\'' +
                ", message='" + message + '\'' +
                ", messageColor='" + messageColor + '\'' +
                ", picture='" + picture + '\'' +
                ", information='" + information + '\'' +
                ", informationColor='" + informationColor + '\'' +
                '}';
    }

    //TODO: XMLFile.create(Billboard billboard)
    public void exportXML() {
        XMLFile.create(this);
    }
}
