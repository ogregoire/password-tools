/*
 * Copyright 2015 Olivier Grégoire <fror@users.noreply.github.com>.
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
package be.fror.password.rule;

import static com.google.common.base.Preconditions.checkArgument;

/**
 *
 * @author Olivier Grégoire &lt;fror@users.noreply.github.com&gt;
 */
public interface Rule {

  public RuleResult validate(final Password password);

  /**
   * <p>
   * If not validated, this <tt>Rule</tt> can generate the following two reasons:
   *
   * <ul>
   * <li><tt>"length.tooShort"</tt> with the parameter <tt>"minimumLength"</tt>;</li>
   * <li><tt>"length.tooLong"</tt> with the parameter <tt>"maximumLength"</tt>.</li>
   * </ul>
   *
   * @param length the exact length for passwords to match.
   * @return a <tt>Rule</tt> for passwords, based on their length.
   * @throws IllegalArgumentException if <tt>minimumLength &lt; 0</tt>
   */
  public static Rule lengthIs(final int length) {
    checkArgument(0 <= length);
    return new LengthRule(length, length);
  }

  /**
   * Both values are inclusive.
   *
   * <p>
   * If not validated, this <tt>Rule</tt> can generate the two following reasons:
   *
   * <ul>
   * <li><tt>"length.tooShort"</tt> with the parameter <tt>"minimumLength"</tt>;</li>
   * <li><tt>"length.tooLong"</tt> with the parameter <tt>"maximumLength"</tt>.</li>
   * </ul>
   *
   * @param minimumLength the minimum password length accepted by this <tt>Rule</tt>
   * @param maximumLength the maximum password length accepted by this <tt>Rule</tt>
   * @return a <tt>Rule</tt> for passwords, based on their length.
   * @throws IllegalArgumentException if <tt>minimumLength &lt; 0</tt> or if <tt>minimumLength &gt;=
   * maximumLength</tt>
   */
  public static Rule lengthIsBetween(final int minimumLength, final int maximumLength) {
    checkArgument(0 <= minimumLength && minimumLength < maximumLength);
    return new LengthRule(minimumLength, maximumLength);
  }

  /**
   *
   * <p>
   * If not validated, this <tt>Rule</tt> can generate the following reason:
   *
   * <ul>
   * <li><tt>"length.tooShort"</tt> with the parameter <tt>"minimumLength"</tt>.</li>
   * </ul>
   *
   * @param minimumLength the minimum password length accepted by this <tt>Rule</tt>
   * @return a <tt>Rule</tt> for passwords, based on their length.
   * @throws IllegalArgumentException if <tt>minimumLength &lt; 0</tt>
   */
  public static Rule lengthIsGreaterThan(final int minimumLength) {
    checkArgument(0 <= minimumLength);
    return new LengthRule(minimumLength, Integer.MAX_VALUE);
  }

  /**
   *
   *
   * <p>
   * If not validated, this <tt>Rule</tt> can generate the following reason:
   *
   * <ul>
   * <li><tt>"noWhitespace"</tt> without any parameters.</li>
   * </ul>
   *
   * @return a <tt>Rule</tt> for passwords matching no whitespaces.
   */
  public static Rule noWhitespace() {
    return StandardRules.NO_WHITESPACE;
  }

}
