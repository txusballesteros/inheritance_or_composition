package com.txusballesteros.data.cache;

public interface CachePolicy {
  boolean hasExpired();
  void refresh();
  void invalidate();
}
