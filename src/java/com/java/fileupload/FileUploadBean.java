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
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
/**
 *
 * @author skyli
 */
public class FileUploadBean implements Serializable {
    private final static Logger logger = Logger.getLogger(FileUploadBean.class.getName());
    private ArrayList<FileUpload> files = new ArrayList<FileUpload>();
    private Boolean enableUpload;
    private Boolean showMessage;
    
    public void listener(FileUploadEvent event) throws Exception {
        UploadedFile itemTemp = event.getUploadedFile();
        FileUpload item = new FileUpload();
        
        item.setName(itemTemp.getName());
        item.setLength(itemTemp.getSize());
        item.setData(itemTemp.getData());
        
        files.add(item);
        logger.info("listener");
    }

    public String clearUploadData() {
        files.clear();        
        setEnableUpload(false);
        
        logger.info("clear -> getEnableUpload -> " + getEnableUpload());
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
        /*FileUpload item = new FileUpload();
        
        //remove elementos do cache
        clearUploadData();
        
        item.setName("Default file");
        item.setLength(564000);
        item.setData(null);
        
        if (files.size() == 0){
            files.add(item);
            setEnableUpload(true);
        }
        */
        logger.info(" setInitialFiles ");        
    }

    public Boolean getEnableUpload() {
        return enableUpload;
    }

    public void setEnableUpload(Boolean enableUpload) {
        this.enableUpload = enableUpload;
    }
    
    public void changeStatus(){
        if (getEnableUpload())
            setEnableUpload(false);
        else
            setEnableUpload(true);
    }

    public Boolean getShowMessage() {
        return showMessage;
    }

    public void setShowMessage(Boolean showMessage) {
        this.showMessage = showMessage;
    } 
    
    public void trocaMessage(){
        if (getShowMessage())
            setShowMessage(false);
        else
            setShowMessage(true);
    }
    
    public void sizeRejected(AjaxBehaviorEvent event){
        logger.info("errorHandle");
        
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Nao foi possivel anexar o arquivo informado!",null));
    }
    
    public void uploadComplete(AjaxBehaviorEvent event){
        logger.info("uploadComplete");
        if (getSize() > 0)
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "",null));
    }
    
    public void fileSubmit(){
        logger.info("fileSubmit");
    }
}
