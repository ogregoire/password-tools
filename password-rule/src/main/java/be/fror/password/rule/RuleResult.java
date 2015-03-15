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

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Olivier Grégoire &lt;fror@users.noreply.github.com&gt;
 */
public abstract class RuleResult {

  private RuleResult() {
  }

  /**
   *
   * @return <tt>true</tt> if the validation passed, <tt>false</tt> otherwise.
   */
  public abstract boolean isValid();

  /**
   *
   * @return an empty list if <tt>isValid()</tt> returns <tt>true</tt>, but a list of all the
   * <tt>Failure</tt>s otherwise.
   */
  public abstract ImmutableList<Failure> getFailures();

  /**
   *
   *
   * @return a <tt>RuleResult</tt> always returning <tt>true</tt> to <tt>isValid()</tt> and an empty
   * <tt>List</tt> to <tt>getFailures()</tt>
   */
  public static RuleResult ok() {
    return Ok.INSTANCE;
  }

  public static FailedResult failed() {
    return new FailedResult();
  }

  public static FailedResult failed(final String reason) {
    return new FailedResult().addFailure(reason);
  }

  public static FailedResult failed(final String reason, final String key, final Object value) {
    return new FailedResult().addFailure(reason, key, value);
  }

  public static FailedResult failed(final String reason, final String key1, final Object value1,
      final String key2, final Object value2) {
    return new FailedResult().addFailure(reason, key1, value1, key2, value2);
  }

  public static FailedResult failed(final String reason, final String key1, final Object value1,
      final String key2, final Object value2, final String key3, final Object value3) {
    return new FailedResult().addFailure(reason, key1, value1, key2, value2, key3, value3);
  }

  public static FailedResult failed(final String reason, final Map<String, Object> parameters) {
    return new FailedResult().addFailure(reason, parameters);
  }

  private static final class Ok extends RuleResult {

    private static final RuleResult INSTANCE = new Ok();

    private static final ImmutableList<Failure> EMPTY_FAILURES = ImmutableList.of();

    private Ok() {
    }

    @Override
    public boolean isValid() {
      return true;
    }

    @Override
    public ImmutableList<Failure> getFailures() {
      return EMPTY_FAILURES;
    }

  }

  public static final class FailedResult extends RuleResult {

    private final List<Failure> failures;

    FailedResult() {
      this.failures = new ArrayList<>();
    }

    @Override
    public boolean isValid() {
      return false;
    }

    @Override
    public ImmutableList<Failure> getFailures() {
      return ImmutableList.copyOf(failures);
    }

    public FailedResult addFailure(final String reason) {
      return this.addFailure(reason, ImmutableMap.of());
    }

    public FailedResult addFailure(final String reason, final String key, final Object value) {
      return this.addFailure(reason, ImmutableMap.of(key, value));
    }

    public FailedResult addFailure(final String reason, final String key1, final Object value1,
        final String key2, final Object value2) {
      return this.addFailure(reason, ImmutableMap.of(key1, value1, key2, value2));
    }

    public FailedResult addFailure(final String reason, final String key1, final Object value1,
        final String key2, final Object value2, final String key3, final Object value3) {
      return this.addFailure(reason, ImmutableMap.of(key1, value1, key2, value2, key3, value3));
    }

    public FailedResult addFailure(final String reason, final Map<String, Object> parameters) {
      this.failures.add(new Failure(reason, ImmutableMap.copyOf(parameters)));
      return this;
    }

    void addFailures(final ImmutableList<Failure> failures) {
      this.failures.addAll(failures);
    }

  }
}