package org.learn.KEM;

import org.junit.jupiter.api.Test;
import org.learn.kem.Receiver;
import org.learn.kem.Sender;

import javax.crypto.DecapsulateException;
import javax.crypto.SecretKey;
import java.security.InvalidKeyException;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class KEMTest {

    @Test
    void testGetKey() throws NoSuchAlgorithmException, InvalidKeyException, DecapsulateException {

        final var keyPairGenerator = KeyPairGenerator.getInstance("X25519");
        final var keyPair = keyPairGenerator.generateKeyPair();
        final var publicKey = keyPair.getPublic();
        final var privateKey = keyPair.getPrivate();

        final var encapsulated = new Sender().generateEncapsulated(publicKey);
        SecretKey senderKey = encapsulated.key();
        final var receiverKey = new Receiver().getKey(privateKey, encapsulated);
        // Assert
        assertNotNull(senderKey, "Sender key must not be null");
        assertNotNull(receiverKey, "Receiver key must not be null");

        assertArrayEquals(encapsulated.key().getEncoded(), receiverKey,  "Shared secret mismatch");
    }

}
