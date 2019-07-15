/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vjzone.model;

import com.sun.javaws.Main;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


/**
 *
 * @author Victor
 */
public class Analyzer implements Runnable {
    
    private String path;
    private static Analyzer itself;
    
    private Clip clip;
    private AudioInputStream inputStream;
    
    
    @Override
    public void run() {
        System.out.println("");
        try {
            this.clip = AudioSystem.getClip();
            this.inputStream = AudioSystem.getAudioInputStream(Main.class.getResourceAsStream(path));
            this.clip.open();
            this.clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    public void stop(){
        this.clip.stop();
    }
    
    public static Analyzer create(){
        
        if(itself == null){
            itself = new Analyzer();
        }
        return itself;
    }
    
    public void setPath(String path){
        
        if(this.clip != null){
            if(this.clip.isRunning()){
                this.clip.stop();
            }
        }
        this.path = path;
        System.out.println(path);

    }
    
    public String getPath(){
        
        return this.path;
    }

    
    
}
