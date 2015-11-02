/* Licensed under the Apache License, Version 2.0 (the "License");
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

package org.camunda.bpm.dmn.engine.impl.handler;

import org.camunda.bpm.dmn.engine.handler.DmnElementHandlerContext;
import org.camunda.bpm.dmn.engine.impl.DmnInputEntryImpl;
import org.camunda.bpm.model.dmn.instance.InputEntry;

public class DmnInputEntryHandler extends AbstractDmnUnaryTestsHandler<InputEntry, DmnInputEntryImpl> {

  protected DmnInputEntryImpl createElement(DmnElementHandlerContext context, InputEntry unaryTests) {
    return new DmnInputEntryImpl();
  }

}
