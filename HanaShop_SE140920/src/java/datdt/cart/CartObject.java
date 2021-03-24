/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datdt.cart;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import org.apache.tomcat.util.codec.binary.Base64;

/**
 *
 * @author TRONG DAT
 */
public class CartObject implements Serializable{
    private HashMap<String,Integer> proList;
    
    public CartObject(){
        proList=new HashMap<String,Integer>();
    }

    public HashMap<String, Integer> getProList() {
        return proList;
    }

    public void setProList(HashMap<String, Integer> proList) {
        this.proList = proList;
    }

   
    
    public String writeObjectToString() throws IOException{
        ByteArrayOutputStream baos=null;
        ObjectOutputStream oos=null;
        String objStr=null;
        try{
            baos=new ByteArrayOutputStream();
            oos=new ObjectOutputStream(baos);
            oos.writeObject(this);
            oos.flush();   
            
            byte[]arr=baos.toByteArray();
            objStr=Base64.encodeBase64String(arr);
        }finally{
            if(oos!=null){
                oos.close();
            }
            if(baos!=null){
                baos.close();
            }
            return objStr;
        }
       
    }
    public void writeStringToObject(String strObj) throws IOException, ClassNotFoundException{
        ByteArrayInputStream bais=null;
        ObjectInputStream ois=null;
        try{
            byte[] arr=Base64.decodeBase64(strObj);
            bais=new ByteArrayInputStream(arr);
            ois=new ObjectInputStream(bais);
            CartObject obj=(CartObject)ois.readObject();
            this.setProList(obj.getProList());
        }finally{
            if(bais!=null){
                bais.close();
            }
            if(ois!=null){
                ois.close();
            }
        }
    }
    public void addToCart(String proID,int quantity){
        int totalQuantity=0;
        if(this.proList==null){
            this.proList=new HashMap<String,Integer>();
        }
        if(this.proList.containsKey(proID)){
            totalQuantity=this.proList.get(proID);
        }
        totalQuantity+=quantity;
        this.proList.put(proID, totalQuantity);
    }
    public void updateToCart(String proID,int quantity){
        if(this.proList!=null && this.proList.containsKey(proID)){
            if(quantity==0){
                this.proList.remove(proID);
            }else{
                this.proList.put(proID, quantity);
            }            
        }
    }
    public void deleteProduct(String proID){
        if(this.proList!=null && this.proList.containsKey(proID)){
                this.proList.remove(proID);
                if(this.proList.isEmpty()){
                    this.proList=null;
                }
        }
    }
}
