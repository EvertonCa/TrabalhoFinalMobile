package com.example.evertoncardoso.trabalhofinalmobile.View;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class AES {
    protected static String chaveSimetrica;
    protected static SecretKey key;
    public static String mensagem;
    //public static byte[] mensagemEncriptada;
    //public static byte[] mensagemDescriptada;

    public AES(String mensagem){
        criarChave();
        setMensagem(mensagem);
    }

    private void setMensagem(String mensagem){
        this.mensagem = mensagem;
    }

    private void criarChave(){
        this.chaveSimetrica = "24";
        this.key = new SecretKeySpec(chaveSimetrica.getBytes(), "AES");
    }

    public byte[] CriptografaMensagem(String msg){
        try{
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, this.key);
            //byte[] mensagemEncriptada = cipher.doFinal(msg.getBytes());
            return cipher.doFinal(msg.getBytes());
        }
        catch(Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    public String DescriptografarMensagem(byte[] msgCriptografada){
        try{
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, this.key);
            byte[]mensagemDescriptada = cipher.doFinal(msgCriptografada);
            //String mensagemDescriptografada = new String(mensagemDescriptada);
            return mensagemDescriptada.toString();

        }
        catch (Exception ex){
            ex.printStackTrace();
            return null;
        }

    }
}
