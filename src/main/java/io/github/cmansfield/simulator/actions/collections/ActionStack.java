package io.github.cmansfield.simulator.actions.collections;

import io.github.cmansfield.simulator.gamemanager.Game;
import io.github.cmansfield.simulator.actions.Action;

import java.util.stream.Collectors;
import java.util.LinkedList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Deque;


public class ActionStack implements Collection<Action> {
  private Deque<Action> stack;

  public ActionStack() {
    this.stack = new LinkedList<>();
  }

  public ActionStack(ActionStack mtgStack, Game game) {
    this.stack = mtgStack.stack.stream()
            .map(action -> action.copy(game))
            .collect(Collectors.toCollection(LinkedList::new));
  }

  public void resolveStack() {
    Action action;

    while(!stack.isEmpty()) {
      action = stack.pollLast();
      action.execute();
    }
  }

  @Override
  public int size() {
    return stack.size();
  }

  @Override
  public boolean isEmpty() {
    return stack.isEmpty();
  }

  @Override
  public boolean contains(Object o) {
    return stack.contains(o);
  }

  @Override
  public Iterator<Action> iterator() {
    return stack.iterator();
  }

  @Override
  public Object[] toArray() {
    return stack.toArray();
  }

  @Override
  public <T> T[] toArray(T[] a) {
    return stack.toArray(a);
  }

  @Override
  public boolean add(Action action) {
    return stack.add(action);
  }

  @Override
  public boolean remove(Object o) {
    return stack.remove(o);
  }

  @Override
  public boolean containsAll(Collection<?> c) {
    return stack.containsAll(c);
  }

  @Override
  public boolean addAll(Collection<? extends Action> c) {
    return stack.addAll(c);
  }

  @Override
  public boolean removeAll(Collection<?> c) {
    return stack.removeAll(c);
  }

  @Override
  public boolean retainAll(Collection<?> c) {
    return stack.retainAll(c);
  }

  @Override
  public void clear() {
    stack.clear();
  }
}
