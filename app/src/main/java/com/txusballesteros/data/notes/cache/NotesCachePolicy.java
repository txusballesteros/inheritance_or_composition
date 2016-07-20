/*
 * Copyright Txus Ballesteros 2016 (@txusballesteros)
 *
 * This file is part of some open source application.
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 *
 * Contact: Txus Ballesteros <txus.ballesteros@gmail.com>
 */
package com.txusballesteros.data.notes.cache;

import java.util.GregorianCalendar;
import javax.inject.Inject;

public class NotesCachePolicy {
  private final long EXPIRATION_TIME_IN_MS = (15 * 1000);
  private final long INITIAL_EXPIRATION_TIME = -1;
  private long lastCacheTime = INITIAL_EXPIRATION_TIME;

  @Inject
  public NotesCachePolicy() { }

  public void invalidate() {
    lastCacheTime = INITIAL_EXPIRATION_TIME;
  }

  public void refresh() {
    lastCacheTime = GregorianCalendar.getInstance().getTimeInMillis();
  }

  public boolean hasExpired() {
    boolean result = true;
    if (lastCacheTime != INITIAL_EXPIRATION_TIME) {
      long currentTime = GregorianCalendar.getInstance().getTimeInMillis();
      long periodDifference = currentTime - lastCacheTime;
      result = periodDifference > EXPIRATION_TIME_IN_MS;
    }
    return result;
  }
}
