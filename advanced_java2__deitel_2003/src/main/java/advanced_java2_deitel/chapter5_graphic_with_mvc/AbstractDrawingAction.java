// AbstractDrawingAction.java
// AbstractDrawingAction is an Action implementation that
// provides set and get methods for common Action properties.
package advanced_java2_deitel.chapter5_graphic_with_mvc;

import java.awt.event.*;
import javax.swing.*;

public abstract class AbstractDrawingAction extends AbstractAction {

    // construct AbstractDrawingAction with given name,
    // icon description and mnemonic key
    public AbstractDrawingAction(String name, Icon icon,
                                 String description, Integer mnemonic) {
        setName(name);
        setSmallIcon(icon);
        setShortDescription(description);
        setMnemonic(mnemonic);
    }

    // set Action name
    public void setName(String name) {
        putValue(Action.NAME, name);
    }

    // set Action Icon
    public void setSmallIcon(Icon icon) {
        putValue(Action.SMALL_ICON, icon);
    }

    // set Action short description
    public void setShortDescription(String description) {
        putValue(Action.SHORT_DESCRIPTION, description);
    }

    // set Action mnemonic key
    public void setMnemonic(Integer mnemonic) {
        putValue(Action.MNEMONIC_KEY, mnemonic);
    }

    // abstract actionPerformed method to be implemented
    // by concrete subclasses
    public abstract void actionPerformed(ActionEvent event);
}
