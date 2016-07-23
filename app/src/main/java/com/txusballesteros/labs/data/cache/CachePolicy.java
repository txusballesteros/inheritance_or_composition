package com.txusballesteros.labs.data.cache;

public interface CachePolicy {
  boolean hasExpired();
  void refresh();
  void invalidate();
}
