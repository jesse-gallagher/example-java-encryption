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
package com.darwino.encryption.example.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.darwino.encryption.example.impl.Crypto;

@SuppressWarnings("nls")
public class AppTest {
	@Test
	public void testRoundTrip() throws Exception {
		Crypto crypto = new Crypto("TESTING KEY");
		
		String in = "hello there";
		String out = crypto.encryptString(in);
		String processed = crypto.decryptString(out);
		
		
		assertEquals("Input and round-tripped value should match", in, processed);
	}
}
