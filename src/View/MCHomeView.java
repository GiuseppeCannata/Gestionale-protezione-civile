package View;

import javax.swing.*;


public class MCHomeView extends JPanel {

    private JButton compitiButton;
    private JButton ruoliButton;
    private JButton rimuoviRuoliButton;
    private JButton rimuoviCompitiButton;
    private JPanel Intermedio0;

    /*costruttore*/

    public void MCHomeView(){

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

    public JButton getRimuoviRuoliButton() {
        return rimuoviRuoliButton;
    }

    public JButton getRimuoviCompitiButton() {
        return rimuoviCompitiButton;
    }


}

