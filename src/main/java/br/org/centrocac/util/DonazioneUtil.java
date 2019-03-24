/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.centrocac.util;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.Normalizer;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;

/**
 *
 * @author fabio
 */
public class DonazioneUtil {

    private static final String CHAVE = "smspde";
    private static final String ALGORITMO = "DES";



    public static String extrairExtensao(String arquivo) {
        if (arquivo == null) {
            return null;
        } else {
            String resposta = "";
            for (int i = arquivo.length() - 1; i >= 0; i--) {
                if (arquivo.charAt(i) == '.') {
                    break;
                } else {
                    resposta = arquivo.charAt(i) + resposta;
                }
            }
            return resposta;
        }

    }

    public static boolean excluirArquivo(String arquivo) {
        File file = new File(arquivo);
        if (file.exists()) {
            return file.delete();
        } else {
            return false;
        }
    }

    public static boolean salvarStream(InputStream in, String arquivoDestino) {
        BufferedInputStream bin = null;
        BufferedOutputStream bout = null;
        try {
            bin = new BufferedInputStream(in);
            bout = new BufferedOutputStream(new FileOutputStream(new File(arquivoDestino)));
            while (bin.available() != 0) {
                bout.write(bin.read());
            }
            bin.close();
            bout.flush();
            bout.close();
            return true;
        } catch (Exception e) {
            System.err.println(e);
            return false;
        } finally {
            if (bin != null) {
                try {
                    bin.close();
                } catch (IOException ex) {
                    System.err.println(ex);
                }
            }
            if (bout != null) {
                try {
                    bout.close();
                } catch (IOException ex) {
                    System.err.println(ex);
                }
            }
        }
    }

    public static String encriptarSHA256(String senha) {
        ShaPasswordEncoder sha = new ShaPasswordEncoder(256);
        String senhaCripto = sha.encodePassword(senha, null);
        return senhaCripto;
    }

   

    /**
     * Algoritmo insertsort de ordenação
     *
     * @param <T>
     * @param lista que será ordenada
     * @param comparador que será utilizado durante a ordenação
     */
    public static <T> void ordenar(List<T> lista, Comparator<T> comparador) {
        if (lista != null
                && !lista.isEmpty()
                && comparador != null) {
            T item = null;
            int anterior = 0;
            for (int i = 1; i < lista.size(); i++) {
                item = lista.get(i);
                anterior = i - 1;
                for (int j = anterior; j >= 0; j--) {
                    if (comparador.compare(item, lista.get(j)) < 0) {
                        lista.set(j + 1, lista.get(j));
                        lista.set(j, item);
                    } else {
                        break;
                    }
                }
            }
        }
    }

    public static String gerarSenhaAscii(int caracteres) {
        return RandomStringUtils.randomAlphanumeric(caracteres).toUpperCase();
    }

    public static String criptografarPrivado(String senha) {
        try {
            SecretKey key = new SecretKeySpec(CHAVE.getBytes("UTF-8"), ALGORITMO);
            Cipher cipher = Cipher.getInstance(ALGORITMO);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] resposta = cipher.doFinal(senha.getBytes("UTF-8"));
            return asHex(resposta);
        } catch (Exception ex) {
            return null;
        }
    }

    public static String descriptografarPrivado(String senha) {
        try {
            SecretKey key = new SecretKeySpec(CHAVE.getBytes("UTF-8"), ALGORITMO);
            Cipher cipher = Cipher.getInstance(ALGORITMO);
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] resposta = cipher.doFinal(fromHexString(senha));
            return new String(resposta);
        } catch (Exception ex) {
            return null;
        }
    }

    private static byte[] fromHexString(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }

    public static String asHex(byte buf[]) {
        StringBuilder strbuf = new StringBuilder(buf.length * 2);
        int i;

        for (i = 0; i < buf.length; i++) {
            if (((int) buf[i] & 0xff) < 0x10) {
                strbuf.append("0");
            }

            strbuf.append(Long.toString((int) buf[i] & 0xff, 16));
        }

        return strbuf.toString();
    }

    public static String retiraAcento(String texto) {
        String txt = Normalizer.normalize(texto, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        final String resposta = pattern.matcher(txt).replaceAll("");
        return resposta;
    }

}
