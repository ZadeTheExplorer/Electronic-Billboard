package ElectronicBillboardObject;

import java.io.Serializable;

/**
 * The type Billboard.
 */
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

    /**
     * Instantiates a new Billboard.
     *
     * @param billboardName    the billboard name
     * @param creator          the creator
     * @param backgroundColor  the background color
     * @param messageColor     the message color
     * @param informationColor the information color
     * @param picture          the picture
     * @param message          the message
     * @param information      the information
     */
    public Billboard(String billboardName,String creator,String backgroundColor,String messageColor,
                     String informationColor,String picture,String message,String information){
        this.name = billboardName;
        this.creator = creator;
        this.backgroundColor = backgroundColor;
        this.message = message;
        this.messageColor = messageColor;
        this.picture = picture;
        this.information = information;
        this.informationColor = informationColor;
    }


    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets creator.
     *
     * @return the creator
     */
    public String getCreator() {
        return creator;
    }

    /**
     * Sets creator.
     *
     * @param creator the creator
     */
    public void setCreator(String creator) {
        this.creator = creator;
    }

    /**
     * Gets background color.
     *
     * @return the background color
     */
    public String getBackgroundColor() {
        return backgroundColor;
    }

    /**
     * Sets background color.
     *
     * @param backgroundColor the background color
     */
    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    /**
     * Gets message.
     *
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets message.
     *
     * @param message the message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Gets message color.
     *
     * @return the message color
     */
    public String getMessageColor() {
        return messageColor;
    }

    /**
     * Sets message color.
     *
     * @param messageColor the message color
     */
    public void setMessageColor(String messageColor) {
        this.messageColor = messageColor;
    }

    /**
     * Gets picture.
     *
     * @return the picture
     */
    public String getPicture() {
        return picture;
    }

    /**
     * Sets picture.
     *
     * @param picture the picture
     */
    public void setPicture(String picture) {
        this.picture = picture;
    }

    /**
     * Gets information.
     *
     * @return the information
     */
    public String getInformation() {
        return information;
    }

    /**
     * Sets information.
     *
     * @param information the information
     */
    public void setInformation(String information) {
        this.information = information;
    }

    /**
     * Gets information color.
     *
     * @return the information color
     */
    public String getInformationColor() {
        return informationColor;
    }

    /**
     * Sets information color.
     *
     * @param informationColor the information color
     */
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

    /**
     * Export xml.
     */
//TODO: XMLFile.create(Billboard billboard)
    public void exportXML() {
        XMLFile.create(this);
    }
}
