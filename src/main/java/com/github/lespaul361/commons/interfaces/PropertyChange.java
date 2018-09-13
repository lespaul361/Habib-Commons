/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lespaul361.commons.interfaces;

import java.beans.PropertyChangeListener;

/**
 *
 * @author David Hamilton
 */
public interface PropertyChange {

    /**
     * Add a PropertyChangeListener to the listener list. The listener is
     * registered for all properties. The same listener object may be added more
     * than once, and will be called as many times as it is added. If listener
     * is null, no exception is thrown and no action is taken.
     *
     * @param listener The PropertyChangeListener to be added
     * @see PropertyChangeListener
     * @see PropertyChangeSupport
     */
    void addPropertyChangeListener(PropertyChangeListener listener);

    /**
     * Add a PropertyChangeListener for a specific property. The listener will
     * be invoked only when a call on firePropertyChange names that specific
     * property. The same listener object may be added more than once. For each
     * property, the listener will be invoked the number of times it was added
     * for that property. If propertyName or listener is null, no exception is
     * thrown and no action is taken.
     *
     * @param propertyName The name of the property to listen on.
     * @param listener The PropertyChangeListener to be added
     * @see PropertyChangeListener
     * @see PropertyChangeSupport
     */
    void addPropertyChangeListener(String propertyName,
            PropertyChangeListener listener);

    /**
     * Remove a PropertyChangeListener from the listener list. This removes a
     * PropertyChangeListener that was registered for all properties. If
     * listener was added more than once to the same event source, it will be
     * notified one less time after being removed. If listener is null, or was
     * never added, no exception is thrown and no action is taken.
     *
     * @param listener The PropertyChangeListener to be removed
     * @see PropertyChangeListener
     * @see PropertyChangeSupport
     */
    void removePropertyChangeListener(PropertyChangeListener listener);

    /**
     * Remove a PropertyChangeListener for a specific property. If listener was
     * added more than once to the same event source for the specified property,
     * it will be notified one less time after being removed. If propertyName is
     * null, no exception is thrown and no action is taken. If listener is null,
     * or was never added for the specified property, no exception is thrown and
     * no action is taken.
     *
     * @param propertyName The name of the property that was listened on.
     * @param listener The PropertyChangeListener to be removed
     * @see PropertyChangeListener
     * @see PropertyChangeSupport
     */
    void removePropertyChangeListener(String propertyName,
            PropertyChangeListener listener);
}
