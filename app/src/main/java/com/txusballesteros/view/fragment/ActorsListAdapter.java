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
package com.txusballesteros.view.fragment;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.txusballesteros.R;
import com.txusballesteros.domain.model.Actor;
import java.util.ArrayList;
import java.util.List;

public class ActorsListAdapter extends RecyclerView.Adapter<ActorsListAdapterViewHolder> {
  private final List<Actor> dataSet;

  public ActorsListAdapter() {
    dataSet = new ArrayList<>();
  }

  public void clear() {
    dataSet.clear();
  }

  public void addAll(List<Actor> actors) {
    dataSet.addAll(actors);
  }

  @Override
  public ActorsListAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_actors_list, parent, false);
    ActorsListAdapterViewHolder result = new ActorsListAdapterViewHolder(itemView);
    return result;
  }

  @Override
  public void onBindViewHolder(ActorsListAdapterViewHolder viewHolder, int position) {
    Actor actor = dataSet.get(position);
    viewHolder.renderName(actor.getName());
  }

  @Override
  public int getItemCount() {
    return dataSet.size();
  }
}
