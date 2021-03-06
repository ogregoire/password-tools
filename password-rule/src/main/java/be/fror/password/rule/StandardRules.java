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

import static be.fror.password.rule.RuleResult.failed;
import static be.fror.password.rule.RuleResult.ok;
import static com.google.common.base.Preconditions.checkNotNull;

import com.google.common.base.CharMatcher;

/**
 *
 * @author Olivier Grégoire &lt;fror@users.noreply.github.com&gt;
 */
class StandardRules {

  static final Rule NO_WHITESPACE = new Rule() {
    @Override
    public RuleResult validate(final Password password) {
      checkNotNull(password, "password must not be null");
      if (CharMatcher.WHITESPACE.matchesNoneOf(password.getPassword())) {
        return ok();
      } else {
        return failed("noWhitespace");
      }
    }

    @Override
    public String toString() {
      return "noWhitespace()";
    }
  };

}
