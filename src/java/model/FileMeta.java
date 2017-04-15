package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.InputStream;

@JsonIgnoreProperties({"content"})
public class FileMeta {

    private String fileName;
    private String fileSize;
    private String fileType;

    private InputStream content;

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
     * @return the content
     */
    public InputStream getContent() {
        return content;
    }

    /**
     * @param content the content to set
     */
    public void setContent(InputStream content) {
        this.content = content;
    }

}
