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

package org.camunda.bpm.dmn.engine.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.camunda.bpm.dmn.engine.DmnDecisionOutput;
import org.camunda.bpm.engine.variable.value.TypedValue;

public class DmnDecisionOutputImpl implements DmnDecisionOutput {

  private static final long serialVersionUID = 1L;

  public static final DmnEngineLogger LOG = DmnLogger.ENGINE_LOGGER;

  protected final Map<String, TypedValue> outputValues = new LinkedHashMap<String, TypedValue>();

  public void putValue(String name, TypedValue value) {
    outputValues.put(name, value);
  }

  @SuppressWarnings("unchecked")
  @Override
  public <T extends TypedValue> T getValueTyped(String name) {
    return (T) outputValues.get(name);
  }

  @SuppressWarnings("unchecked")
  @Override
  public <T extends TypedValue> T getFirstValueTyped() {
    if (!outputValues.isEmpty()) {
      return (T) outputValues.values().iterator().next();
    } else {
      return null;
    }
  }

  @Override
  public <T extends TypedValue> T getSingleValueTyped() {
    if (outputValues.size() > 1) {
      throw LOG.decisionOutputHasMoreThanOneValue(this);
    } else {
      return getFirstValueTyped();
    }
  }

  @SuppressWarnings("unchecked")
  public <T> T getFirstValue() {
    if (!outputValues.isEmpty()) {
      return (T) getFirstValueTyped().getValue();
    } else {
      return null;
    }
  }

  @SuppressWarnings("unchecked")
  public <T> T getSingleValue() {
    if (!outputValues.isEmpty()) {
      return (T) getSingleValueTyped().getValue();
    } else {
      return null;
    }
  }

  @Override
  public Map<String, Object> getValueMap() {
    Map<String, Object> valueMap = new HashMap<String, Object>();

    for (String key : outputValues.keySet()) {
      valueMap.put(key, get(key));
    }

    return valueMap;
  }

  @Override
  public int size() {
    return outputValues.size();
  }

  @Override
  public boolean isEmpty() {
    return outputValues.isEmpty();
  }

  @Override
  public boolean containsKey(Object key) {
    return outputValues.containsKey(key);
  }

  @Override
  public Set<String> keySet() {
    return outputValues.keySet();
  }

  @Override
  public Collection<Object> values() {
    List<Object> values = new ArrayList<Object>();

    for (TypedValue typedValue : outputValues.values()) {
      values.add(typedValue.getValue());
    }

    return values;
  }

  @Override
  public Collection<TypedValue> valuesTyped() {
    return outputValues.values();
  }

  @Override
  public String toString() {
    return outputValues.toString();
  }

  @Override
  public boolean containsValue(Object value) {
    return values().contains(value);
  }

  @Override
  public Object get(Object key) {
    TypedValue typedValue = outputValues.get(key);
    if (typedValue != null) {
      return typedValue.getValue();
    } else {
      return null;
    }
  }

  @Override
  public Object put(String key, Object value) {
    throw new UnsupportedOperationException("decision output is immutable");
  }

  @Override
  public Object remove(Object key) {
    throw new UnsupportedOperationException("decision output is immutable");
  }

  @Override
  public void putAll(Map<? extends String, ? extends Object> m) {
    throw new UnsupportedOperationException("decision output is immutable");
  }

  @Override
  public void clear() {
    throw new UnsupportedOperationException("decision output is immutable");
  }

  @Override
  public Set<Entry<String, Object>> entrySet() {
    Set<Entry<String, Object>> entrySet = new HashSet<Map.Entry<String, Object>>();

    for (Entry<String, TypedValue> typedEntry : outputValues.entrySet()) {
      DmnDecisionOutputValueEntry entry = new DmnDecisionOutputValueEntry(typedEntry.getKey(), typedEntry.getValue());
      entrySet.add(entry);
    }

    return entrySet;
  }

  protected class DmnDecisionOutputValueEntry implements Entry<String, Object> {

    protected final String key;
    protected final TypedValue typedValue;

    public DmnDecisionOutputValueEntry(String key, TypedValue typedValue) {
      this.key = key;
      this.typedValue = typedValue;
    }

    @Override
    public String getKey() {
      return key;
    }

    @Override
    public Object getValue() {
      if (typedValue != null) {
        return typedValue.getValue();
      } else {
        return null;
      }
    }

    @Override
    public Object setValue(Object value) {
      throw new UnsupportedOperationException("decision output entry is immutable");
    }

  }

}
