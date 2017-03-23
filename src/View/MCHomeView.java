package View;

import javax.swing.*;


public class MCHomeView extends JPanel {

    private JButton daiCompitiButton;
    private JButton daiRuoliButton;
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

    public JButton getDaiCompitiButton() {
        return daiCompitiButton;
    }

    public JButton getDaiRuoliButton() {
        return daiRuoliButton;
    }

    public JButton getRimuoviRuoliButton() {
        return rimuoviRuoliButton;
    }

    public JButton getRimuoviCompitiButton() {
        return rimuoviCompitiButton;
    }


}

