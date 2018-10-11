/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SwModel;

/**
 * @author Adriano Valt <adrianovalt@gmail.com>
 */
public class Equipamento {
    
    private String id;
    private String ip;
    private String netMask;

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**@return the ip
     */
    public String getIp() {
        return ip;
    }

    /**
     * @param ip the ip to set
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * @return the netMask
     */
    public String getNetMask() {
        return netMask;
    }

    /**
     * @param netMask the netMask to set
     */
    public void setNetMask(String netMask) {
        this.netMask = netMask;
    }
}
