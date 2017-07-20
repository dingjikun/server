package model;

/**
 * Created by pc on 2017/5/28.
 */
public class OssToken {
    public String getSignature() {
        return signature;
    }

    private final String signature;

    public OssToken(String sig) {
        signature = sig;
    }
}
