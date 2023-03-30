package org.elte.model;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public abstract class MouseHandler implements MouseListener {

    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();

    }

}
