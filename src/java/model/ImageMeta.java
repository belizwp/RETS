package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.awt.Image;
@JsonIgnoreProperties({"img"})
public class ImageMeta {

    private String fileName;
    private String fileSize;
    private String fileType;

    private Image img;

    @Override
    public String toString() {
        return "FileMeta [fileName=" + getFileName() + ", fileSize=" + getFileSize()
                + ", fileType=" + getFileType() + "]";
    }

    /**
     * @return the fileName
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * @param fileName the fileName to set
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * @return the fileSize
     */
    public String getFileSize() {
        return fileSize;
    }

    /**
     * @param fileSize the fileSize to set
     */
    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    /**
     * @return the fileType
     */
    public String getFileType() {
        return fileType;
    }

    /**
     * @param fileType the fileType to set
     */
    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    /**
     * @return the img
     */
    public Image getImg() {
        return img;
    }

    /**
     * @param img the img to set
     */
    public void setImg(Image img) {
        this.img = img;
    }

}
