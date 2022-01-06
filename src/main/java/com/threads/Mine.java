package com.threads;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Mine {
    public String data;
    public String hash;
    public String previousHash = "0";
    public long timeStamp;
    public int nonce;
    public String hashRate;
    private MessageDigest digest = MessageDigest.getInstance("SHA-384");
    public Mine() throws NoSuchAlgorithmException, UnsupportedEncodingException {
        this.data = "0123456789";
        this.previousHash = "0";
        this.timeStamp = System.currentTimeMillis() / 1000L;
        this.hash = calculateBlockHash();
    }

    private String calculateBlockHash() throws UnsupportedEncodingException {
        String dataToHash = this.previousHash + Long.toString(this.timeStamp) + Integer.toString(this.nonce) + this.data;
        byte[] hash = this.digest.digest(dataToHash.getBytes("UTF-8"));
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }
    public String mineBlock(int prefix) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        String prefixString = new String(new char[prefix]).replace('\0', '0');
        while (!this.hash.substring(0, prefix).equals(prefixString)) {
            this.nonce++;
            this.hash = calculateBlockHash();
        }
        return this.hash;
    }
}
