package org.learn.kem;

import javax.crypto.DecapsulateException;
import javax.crypto.KEM;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;

public class Receiver {

    public byte[] getKey(PrivateKey privateKey,  KEM.Encapsulated encapsulated ) throws NoSuchAlgorithmException, InvalidKeyException, DecapsulateException {
        final var receiverKem = KEM.getInstance("DHKEM");
        final var reciver = receiverKem.newDecapsulator(privateKey);
        return reciver.decapsulate(encapsulated.encapsulation()).getEncoded();
    }

}
