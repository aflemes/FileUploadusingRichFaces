package com.java.fileupload;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.Logger;

import org.richfaces.event.FileUploadEvent;
import org.richfaces.model.UploadedFile;
import com.java.fileupload.FileUpload;
/**
 *
 * @author skyli
 */
public class FileUploadBean implements Serializable {
    private final static Logger logger = Logger.getLogger(FileUploadBean.class.getName());
    private ArrayList<FileUpload> files = new ArrayList<FileUpload>();

    public void listener(FileUploadEvent event) throws Exception {
        UploadedFile itemTemp = event.getUploadedFile();
        FileUpload item = new FileUpload();
        
        item.setName(itemTemp.getName());
        item.setLength(itemTemp.getSize());
        item.setData(itemTemp.getData());
        
        files.add(item);
    }

    public String clearUploadData() {
        files.clear();
        return null;
    }

    public int getSize() {
        if (getFiles().size() > 0) {
            return getFiles().size();
        } else {
            return 0;
        }
    }

    public long getTimeStamp() {
        return System.currentTimeMillis();
    }

    public ArrayList<FileUpload> getFiles() {
        return files;
    }

    public void setFiles(ArrayList<FileUpload> files) {
        this.files = files;
    }
    
    public void setInitialFiles(){
        FileUpload item = new FileUpload();
        item.setName("Default file");
        item.setLength(564000);
        item.setData(null);
        files.add(item);
        
        logger.info("rola");
    }
}
