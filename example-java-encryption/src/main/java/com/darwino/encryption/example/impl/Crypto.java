/**
 * Copyright © 2018 Darwino, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.darwino.encryption.example.impl;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.ShortBufferException;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

public class Crypto {
	public static final String CIPHER_ALG = "Blowfish"; //$NON-NLS-1$
	
	private final String key;
	
	public Crypto(String key) {
		this.key = key;
	}
	
	public String encryptString(String input) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, ShortBufferException, IllegalBlockSizeException, BadPaddingException {
		byte[] in = input.getBytes();
		
		SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), CIPHER_ALG);
		Cipher cipher = Cipher.getInstance(CIPHER_ALG);
		
		cipher.init(Cipher.ENCRYPT_MODE, keySpec);
		byte[] enc = cipher.doFinal(in);
		
		return DatatypeConverter.printBase64Binary(enc);
	}
	
	public String decryptString(String input) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, ShortBufferException, IllegalBlockSizeException, BadPaddingException {
		byte[] in = DatatypeConverter.parseBase64Binary(input);

		SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), CIPHER_ALG);
		Cipher cipher = Cipher.getInstance(CIPHER_ALG);
		
		cipher.init(Cipher.DECRYPT_MODE, keySpec);
		byte[] dec = cipher.doFinal(in);
		
		return new String(dec);
	}
}
