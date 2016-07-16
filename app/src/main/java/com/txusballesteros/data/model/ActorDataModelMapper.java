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
package com.txusballesteros.data.model;

import com.txusballesteros.domain.model.Actor;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ActorDataModelMapper {
  @Inject
  public ActorDataModelMapper() { }

  public List<Actor> map(List<ActorDataModel> source) {
    List<Actor> result = new ArrayList<>(source.size());
    for(ActorDataModel actorDataModel : source) {
      result.add(map(actorDataModel));
    }
    return result;
  }

  public Actor map(ActorDataModel source) {
    return new Actor.Builder(source.getId(), source.getName(), source.getPictureUrl()).build();
  }
}
