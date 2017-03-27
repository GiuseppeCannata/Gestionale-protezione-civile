package View;

import javax.swing.*;

/**
 * View per la home di masterchief
 */

public class MCHomeView extends JPanel {

    private JButton compitiButton;
    private JButton ruoliButton;
    private JPanel Intermedio0;

    private JButton resetMCButton;
    private JButton resetCompitiButton;
    private JButton resetRuoliButton;

    /*costruttore*/

    public MCHomeView(){

        resetCompitiButton.setVisible(false);
        resetRuoliButton.setVisible(false);
        resetMCButton.setVisible(false);

        setVisible(true);

    }


    /*getter*/
    public JPanel getIntermedio0() {

        return Intermedio0;

    }

    public JButton getCompitiButton() {

        return compitiButton;

    }

    public JButton getRuoliButton() {

        return ruoliButton;

    }

    public JButton getResetMCButton() {

        return resetMCButton;

    }

    public JButton getResetCompitiButton() {
        return resetCompitiButton;
    }

    public JButton getResetRuoliButton() {
        return resetRuoliButton;
    }

    public void VisibilitaResetButton(boolean visibilita){

        resetMCButton.setVisible(visibilita);
    }

    public void VisibilitaResetCompitiButton(boolean visibilita){

        resetCompitiButton.setVisible(visibilita);
    }

    public void VisibilitaResetRuoliButton(boolean visibilita){

        resetRuoliButton.setVisible(visibilita);
    }



    @Override
    public String toString() {
        return "MCHomeView{}";
    }
}

