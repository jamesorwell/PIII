/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ku.piii.model;

import ku.piii.model.MusicMedia;

/**
 *
 * @author James
 */
public class MusicMediaColumnInfo {

    public String getHeading() {
        return heading;
    }

    public MusicMediaColumnInfo setHeading(String heading) {
        this.heading = heading;
        return this;
    }

    public int getMinWidth() {
        return minWidth;
    }

    public MusicMediaColumnInfo setMinWidth(int minWidth) {
        this.minWidth = minWidth;
        return this;
    }

    public String getProperty() {
        return property;
    }

    public MusicMediaColumnInfo setProperty(String property) {
        this.property = property;
        return this;
    }

    
    private String heading;
    private int minWidth;
    private String property;
    
 
    
    
}
