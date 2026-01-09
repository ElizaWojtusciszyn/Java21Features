package org.learn.kem;

import javax.crypto.DecapsulateException;
import javax.crypto.KEM;
import java.security.InvalidKeyException;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class KEMApplication {

    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException, DecapsulateException {

        //X2551 -> Diffie - Hellman method of generating keys
        final var keyPairGenerator = KeyPairGenerator.getInstance("X25519");
        final var keyPair = keyPairGenerator.generateKeyPair();
        final var publicKey = keyPair.getPublic();
        final var privateKey = keyPair.getPrivate();

        // Sender side of the exchange
        final var sendersKem = KEM.getInstance("DHKEM");
        final var sender = sendersKem.newEncapsulator(publicKey);
        final var encapsulated = sender.encapsulate();
        final var secretKey = encapsulated.key();


        // Receiver side of the exchange
        final var receiverKem = KEM.getInstance("DHKEM");
        final var reciver = receiverKem.newDecapsulator(privateKey);
        final var receivedSecretKey = reciver.decapsulate(encapsulated.encapsulation());

        if (Arrays.equals(secretKey.getEncoded(), receivedSecretKey.getEncoded())) {
            System.out.println("Huray, your keys match!!");
        } else {
            System.out.println("Sorry, you failed :(");
        }
    }

}
