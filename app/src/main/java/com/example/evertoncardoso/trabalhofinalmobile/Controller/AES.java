package com.example.evertoncardoso.trabalhofinalmobile.dao;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class AES {
    protected static String chaveSimetrica;
    protected static SecretKey key;
    //public static byte[] mensagemEncriptada;
    //public static byte[] mensagemDescriptada;

    public AES(){
        criarChave();
    }

    private void criarChave(){
        this.chaveSimetrica = "24";
        this.key = new SecretKeySpec(chaveSimetrica.getBytes(), "AES");
    }

    public String CriptografaMensagem(String msg){
        try{
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, this.key);
            String mensagemEncriptada = new String(cipher.doFinal(msg.getBytes()));
            return mensagemEncriptada;
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

    public static String criptografaSenha(String senha)
    {
        int k=10;
        int cripto;
        String resposta = "";
        String msgOriginal = senha;
        for (int i=0; i < msgOriginal.length(); i++)
        {
            char c = msgOriginal.charAt(i);
            int j = (int) c;
            cripto = j + k;
            resposta += (char)cripto;
        }
        return resposta;
    }

    public static String descriptografaSenha(String senha)
    {
        int k=10;
        int cripto;
        String resposta = "";
        String msgOriginal = senha;
        for (int i=0; i < msgOriginal.length(); i++)
        {
            char c = msgOriginal.charAt(i);
            int j = (int) c;
            cripto = j - k;
            resposta += (char)cripto;
        }
        return resposta;
    }
}
