/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lespaul361.commons.commonroutines.textfieldcontextmenu;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.JTextComponent;
import javax.swing.undo.UndoManager;

/**
 * A class that adds a pop up menu with options for undo, redo, cut, copy,
 * paste, delete, and select all.
 *
 * @author Charles Hamilton
 */
@SuppressWarnings("serial")
public class DefaultContextMenu extends JPopupMenu {

    private Clipboard clipboard;

    private UndoManager undoManager;

    private JMenuItem undo;
    private JMenuItem redo;
    private JMenuItem cut;
    private JMenuItem copy;
    private JMenuItem paste;
    private JMenuItem delete;
    private JMenuItem selectAll;
    private ImageIcon CopyIcon;

    private JTextComponent jTextComponent;

    /**
     * Creates a new instance of <code>DevaultContextMenu</code>
     */
    public DefaultContextMenu() {
        undoManager = new UndoManager();
        clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

        undo = new JMenuItem("Undo");
        undo.setEnabled(false);
        undo.setAccelerator(KeyStroke.getKeyStroke("control Z"));
        addIcon("Undo-icon.png", undo);
        undo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                undoManager.undo();
            }
        });

        add(undo);

        redo = new JMenuItem("Redo");
        redo.setEnabled(false);
        redo.setAccelerator(KeyStroke.getKeyStroke("control Y"));
        addIcon("Redo-icon.png", redo);
        redo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                undoManager.redo();
            }
        });

        add(redo);

        add(new JSeparator());

        cut = new JMenuItem("Cut");
        cut.setEnabled(false);
        cut.setAccelerator(KeyStroke.getKeyStroke("control X"));
        addIcon("Actions-edit-cut-icon.png", cut);
        cut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                jTextComponent.cut();
            }
        });

        add(cut);

        copy = new JMenuItem("Copy");
        copy.setEnabled(false);
        copy.setAccelerator(KeyStroke.getKeyStroke("control C"));
        addIcon("File-Copy-icon.png", copy);
        copy.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                jTextComponent.copy();
            }
        });

        add(copy);

        paste = new JMenuItem("Paste");
        paste.setEnabled(false);
        paste.setAccelerator(KeyStroke.getKeyStroke("control V"));
        addIcon("paste-icon.png", paste);
        paste.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                jTextComponent.paste();
            }
        });

        add(paste);

        delete = new JMenuItem("Delete");
        delete.setEnabled(false);
        delete.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0));
        addIcon("Close-icon.png", delete);
        delete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                jTextComponent.replaceSelection("");
            }
        });

        add(delete);

        add(new JSeparator());

        selectAll = new JMenuItem("Select All");
        selectAll.setEnabled(false);
        selectAll.setAccelerator(KeyStroke.getKeyStroke("control A"));
        selectAll.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                jTextComponent.selectAll();
            }
        });

        add(selectAll);
    }

    private void addIcon(String imageName, JMenuItem menu) {
        Image img = com.github.lespaul361.commons.commonroutines.utilities.CreateImageFromResource.getImage("/com/habibsweb/commonroutines/TextFieldContextMenu/" + imageName, this.getClass());
        Image newImg = img.getScaledInstance(16, 16, java.awt.Image.SCALE_SMOOTH);
        menu.setIcon(new ImageIcon(newImg));
    }

    private void processClickTextComponent(MouseEvent event) {
        jTextComponent = (JTextComponent) event.getSource();
        jTextComponent.requestFocus();

        boolean enableUndo = undoManager.canUndo();
        boolean enableRedo = undoManager.canRedo();
        boolean enableCut = false;
        boolean enableCopy = false;
        boolean enablePaste = false;
        boolean enableDelete = false;
        boolean enableSelectAll = false;

        String selectedText = jTextComponent.getSelectedText();
        String text = jTextComponent.getText();

        if (text != null) {
            if (text.length() > 0) {
                enableSelectAll = true;
            }
        }

        if (selectedText != null) {
            if (selectedText.length() > 0) {
                enableCut = true;
                enableCopy = true;
                enableDelete = true;
            }
        }

        try {
            if (clipboard.isDataFlavorAvailable(DataFlavor.stringFlavor)) {
                enablePaste = true;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        undo.setEnabled(enableUndo);
        redo.setEnabled(enableRedo);
        cut.setEnabled(enableCut);
        copy.setEnabled(enableCopy);
        paste.setEnabled(enablePaste);
        delete.setEnabled(enableDelete);
        selectAll.setEnabled(enableSelectAll);

        show(jTextComponent, event.getX(), event.getY());
    }

    /**
     * Adds a <code>JTextComponent</code> the class. This lets the pop up menu
     * be added to the component
     *
     * @param jTextComponent text component to add the context menu to
     */
    public void add(JTextComponent jTextComponent) {
        jTextComponent.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent pressedEvent) {
                if ((pressedEvent.getKeyCode() == KeyEvent.VK_Z)
                        && ((pressedEvent.getModifiers() & KeyEvent.CTRL_MASK) != 0)) {
                    if (undoManager.canUndo()) {
                        undoManager.undo();
                    }
                }

                if ((pressedEvent.getKeyCode() == KeyEvent.VK_Y)
                        && ((pressedEvent.getModifiers() & KeyEvent.CTRL_MASK) != 0)) {
                    if (undoManager.canRedo()) {
                        undoManager.redo();
                    }
                }
            }
        });

        jTextComponent.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent releasedEvent) {
                if (releasedEvent.getButton() == MouseEvent.BUTTON3) {
                    processClickTextComponent(releasedEvent);
                }
            }
        });

        jTextComponent.getDocument().addUndoableEditListener(
                new UndoableEditListener() {
            @Override
            public void undoableEditHappened(UndoableEditEvent event) {
                undoManager.addEdit(event.getEdit());
            }
        });
    }

}
