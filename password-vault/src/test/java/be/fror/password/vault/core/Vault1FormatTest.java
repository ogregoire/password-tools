/*
 * Copyright 2015 Olivier Grégoire.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package be.fror.password.vault.core;

import be.fror.common.io.ByteSource;
import be.fror.password.vault.model.Vault;

import org.junit.Test;

import java.io.IOException;

/**
 *
 * @author Olivier Grégoire
 */
public class Vault1FormatTest {

  @Test
  public void testRead() throws IOException {
    ByteSource source = ByteSource.wrap(new byte[]{ 
      'V', 'L', 'T', '1', // Tag ("VLT1")
      1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, // Salt
      0, 0, 4, 0, // Iter (1024)
    });

    Vault vault = Vault1Format.INSTANCE.read(source.openStream());

  }

}
