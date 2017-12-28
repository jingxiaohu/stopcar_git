package com.park.pda.action.v1.fingerprintcollection.param;

/**
 * app上传指纹信息对象
 * Created by zzy on 2017/8/24.
 */
public class UserFingerEvnoInfo {
    private String vein;   //静脉信息
    private String fingerprint;   //指纹信息
    private String vein_hash; //  静脉hashcode
    private String fingerprint_hash;  //指纹hashcode

    public String getVein() {
        return vein;
    }

    public void setVein(String vein) {
        this.vein = vein;
    }

    public String getFingerprint() {
        return fingerprint;
    }

    public void setFingerprint(String fingerprint) {
        this.fingerprint = fingerprint;
    }

    public String getVein_hash() {
        return vein_hash;
    }

    public void setVein_hash(String vein_hash) {
        this.vein_hash = vein_hash;
    }

    public String getFingerprint_hash() {
        return fingerprint_hash;
    }

    public void setFingerprint_hash(String fingerprint_hash) {
        this.fingerprint_hash = fingerprint_hash;
    }
}
