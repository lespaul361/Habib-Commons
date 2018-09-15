package com.github.lespaul361.commons.commonroutines.boxbuilding;

import java.awt.Component;
import java.awt.Dimension;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Helper class for some common actions needed with <code>Box</code> components
 * 
 * @author Joseph
 */
public class BoxBuildRoutines {

    /**
     * Constant to align to the left
     */
    public static final int ALIGN_LEFT = 0;

    /**
     * Constant to align to the right
     */
    public static final int ALIGN_CENTER = 1;

    /**
     * Constant to align to the right
     */
    public static final int ALIGN_RIGHT = 2;

    /**
     * Constant for no alignment set
     */
    public static final int ALIGN_NONE = 3;

    /**
     * Creates a box with a label and another component to the right of the
     * label
     * 
     * @param leftSpace
     *            Empty space to the left of the label
     * @param labelText
     *            The text for the label
     * @param betweenSpace
     *            Space between the label and the next component
     * @param txt
     *            The component label is for
     * @param afterSpace
     *            Space of the component
     * @param txtSize
     *            size of the component
     * @param Alignment
     *            The alignment of the box
     * @return A Box with the needed components
     */
    public static Box createLabelBox(int leftSpace, String labelText, int betweenSpace, Component txt, int afterSpace,
	    Dimension txtSize, int Alignment) {
	Box box = Box.createHorizontalBox();
	txt.setPreferredSize(txtSize);
	txt.setMaximumSize(txtSize);
	txt.setMinimumSize(txtSize);

	if (Alignment == ALIGN_RIGHT || Alignment == ALIGN_CENTER) {
	    box.add(Box.createHorizontalGlue());
	}
	if (leftSpace > 0) {
	    box.add(Box.createHorizontalStrut(leftSpace));
	}
	box.add(new JLabel(labelText));
	if (betweenSpace > 0) {
	    box.add(Box.createHorizontalStrut(betweenSpace));
	}
	box.add(txt);
	if (afterSpace > 0) {
	    box.add(Box.createHorizontalStrut(afterSpace));
	}
	if (Alignment == ALIGN_LEFT || Alignment == ALIGN_CENTER) {
	    box.add(Box.createHorizontalGlue());
	}

	return box;
    }

    /**
     * Creates a box with a label and another component to the right of the
     * label. Aligned to the left
     * 
     * @param leftSpace
     *            Empty space to the left of the label
     * @param labelText
     *            The text for the label
     * @param betweenSpace
     *            Space between the label and the next component
     * @param txt
     *            The component label is for
     * @param afterSpace
     *            Space of the component
     * @param txtSize
     *            size of the component
     * @return A Box with the needed components aligned to the left
     */
    public static Box createLabelBox(int leftSpace, String labelText, int betweenSpace, Component txt, int afterSpace,
	    Dimension txtSize) {
	return createLabelBox(leftSpace, labelText, betweenSpace, txt, afterSpace, txtSize, ALIGN_LEFT);
    }

    /**
     * Creates two label boxes next to each other
     * 
     * @param leftSpace
     *            Empty space to the left of the label
     * @param labelText
     *            The text for the label
     * @param betweenSpace
     *            Space between the label and the next component
     * @param txt
     *            The component label is for
     * @param afterSpace
     *            Space of the component
     * @param txtSize
     *            size of the component
     * @return A box with needed component and no alignment
     */
    public static Box createLabelBoxNoGlue(int leftSpace, String labelText, int betweenSpace, Component txt,
	    int afterSpace, Dimension txtSize) {
	Box box = Box.createHorizontalBox();
	txt.setPreferredSize(txtSize);
	txt.setMaximumSize(txtSize);
	txt.setMinimumSize(txtSize);

	if (leftSpace > 0) {
	    box.add(Box.createHorizontalStrut(leftSpace));
	}
	box.add(new JLabel(labelText));
	if (betweenSpace > 0) {
	    box.add(Box.createHorizontalStrut(betweenSpace));
	}
	box.add(txt);
	if (afterSpace > 0) {
	    box.add(Box.createHorizontalStrut(afterSpace));
	}
	return box;
    }

    /**
     * Add a component to a vertical Box
     * 
     * @param boxV
     *            The vertical box to add the component to
     * @param component
     *            The component to add the vertical box
     * @param topGap
     *            Space above the component being added
     */
    public static void addToVertBox(Box boxV, Component component, int topGap) {
	if (topGap > 0) {
	    boxV.add(Box.createVerticalStrut(topGap));
	}
	boxV.add(component);
    }

    /**
     * 
     * @param leftSpace
     *            Empty space to the left of the label
     * @param labelText1
     *            The text for the first label
     * @param betweenSpaceLabel
     *            Space between the first label and the next component
     * @param txt1
     *            The component first label is for
     * @param betweenBoxSpace
     *            Space between the label boxes
     * @param labelText2
     *            The text for the second label
     * @param txt2
     *            The component second label is for
     * @param afterSpace
     *            Space of the component
     * @param txtSize
     *            size of the components
     * @return
     */
    private Box createDualLabelBoxNoGlue(int leftSpace, String labelText1, int betweenSpaceLabel, Component txt1,
	    int betweenBoxSpace, String labelText2, Component txt2, int afterSpace, Dimension txtSize) {
	Box ret = Box.createHorizontalBox();
	Box box1 = createLabelBoxNoGlue(0, labelText1, betweenSpaceLabel, txt1, 0, txtSize);
	Box box2 = createLabelBoxNoGlue(0, labelText2, betweenSpaceLabel, txt2, 0, txtSize);
	ret.add(Box.createHorizontalStrut(leftSpace));
	ret.add(box1);
	ret.add(Box.createHorizontalStrut(betweenBoxSpace));
	ret.add(box2);
	ret.add(Box.createHorizontalStrut(afterSpace));
	return ret;
    }

    /**
     * Creates a Box with two boxes next to each other
     * 
     * @param box1
     *            First Box
     * @param spaceBetween
     *            Space between the Boxes
     * @param box2
     *            Second Box
     * @param Alignment
     *            The alignment of the boxes within the containing box
     * @return <code>Box</code>
     */
    public static Box createSideBySideBox(Box box1, int spaceBetween, Box box2, int Alignment) {
	Box box = Box.createHorizontalBox();
	if (Alignment == ALIGN_CENTER || Alignment == ALIGN_RIGHT) {
	    box.add(Box.createHorizontalGlue());
	}
	box.add(box1);
	box.add(Box.createHorizontalStrut(spaceBetween));
	box.add(box2);
	if (Alignment == ALIGN_CENTER || Alignment == ALIGN_LEFT) {
	    box.add(Box.createHorizontalGlue());
	}
	return box;

    }

    /**
     * Sets the preferred size of the components
     * 
     * @param component
     *            The component to set the size on
     * @param size
     *            Preferred size
     */
    public static void setComponentSize(Component component, Dimension size) {
	component.setMinimumSize(size);
	component.setPreferredSize(size);
	component.setMaximumSize(size);
    }

    /**
     * Creates a box for an component to be centered
     * 
     * @param component
     *            component to be centered
     * @param sideGap
     *            the gap on each side
     * @return <code>Box</code>
     */
    public static Box createCenteredBox(Component component, int sideGap) {
	Box boxH = Box.createHorizontalBox();
	boxH.add(Box.createHorizontalGlue());
	boxH.add(Box.createHorizontalStrut(sideGap));
	boxH.add(component);
	boxH.add(Box.createHorizontalStrut(sideGap));
	boxH.add(Box.createHorizontalGlue());
	return boxH;
    }

    /**
     * Creates a panel with 2 buttons at the bottom
     * 
     * @param btn1
     *            Button on the left
     * @param btn2
     *            Button on the right
     * @param szButton
     *            The side of the buttons
     * @return <code>JPanel</code>
     */
    public static JPanel getBottomButtonPanel(JButton btn1, JButton btn2, Dimension szButton) {
	Box boxH = Box.createHorizontalBox();
	Box boxH2 = Box.createHorizontalBox();
	btn2.setPreferredSize(szButton);
	btn2.setMaximumSize(szButton);
	btn2.setMinimumSize(szButton);
	btn1.setPreferredSize(szButton);
	btn1.setMaximumSize(szButton);
	btn1.setMinimumSize(szButton);

	boxH.add(btn1);
	boxH.add(Box.createHorizontalStrut(10));
	boxH.add(btn2);
	boxH.add(Box.createRigidArea(new Dimension(20, 50)));
	boxH2.add(Box.createHorizontalGlue());
	boxH2.add(boxH);
	JPanel pnl = new JPanel();
	pnl.setLayout(new BoxLayout(pnl, BoxLayout.LINE_AXIS));
	pnl.add(boxH2);
	return pnl;
    }
}
