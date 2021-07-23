package com.zimonishim.GUI;

/**
 * Contains methods used by other threads to add elements to the GUI without throwing an IllegalStateException.
 * <p>
 * This is because JavaFX does not allow threads (other than the FX application thread) to modify UI elements. The message
 * passed along with the IllegalStateException is as follows: "Not on FX application thread".
 * @see IllegalStateException
 */
public interface IGUICallback {
    void addSortResultsToGUI(String result);
    void addAddAllResultsToGUI(String result);
}