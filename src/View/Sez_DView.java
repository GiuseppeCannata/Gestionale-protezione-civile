package View;

import javax.swing.*;


public class Sez_DView extends JPanel {

    private JPanel Intermedio0;
    private JTextField GStext;
    private JTextField TagliaTestatext;
    private JTextField TagliaBustotext;
    private JTextField TagliaManotext;
    private JTextField TagliaPantalonitext;
    private JTextField TagliaScarpetext;
    private JButton salvaButton;
    private JTextField Abilitatext;


    public Sez_DView() {

        setVisible(true);

    }

    public JPanel getIntermedio0() {

        return Intermedio0;

    }

    public String getAbilitatext() {

        return Abilitatext.getText();

    }

    public String getGStext() {

        return GStext.getText();

    }

    public String getTagliaTestatext() {

        return TagliaTestatext.getText();
    }

    public String getTagliaBustotext() {

        return TagliaBustotext.getText();

    }

    public String getTagliaManotext() {

        return TagliaManotext.getText();

    }

    public String getTagliaPantalonitext() {

        return TagliaPantalonitext.getText();

    }

    public String getTagliaScarpetext() {

        return TagliaScarpetext.getText();

    }

    public JButton getSalvaButton() {

        return salvaButton;

    }

    public void setGStext(String gStext) {

        GStext.setText(gStext);

    }

    public void setAbilitatext(String abilitatext) {

        Abilitatext.setText(abilitatext);

    }

    public void setTagliaTestatext(String tagliaTestatext) {

        TagliaTestatext.setText(tagliaTestatext);

    }

    public void setTagliaBustotext(String tagliaBustotext) {

        TagliaBustotext.setText(tagliaBustotext);

    }

    public void setTagliaManotext(String tagliaManotext) {

        TagliaManotext.setText(tagliaManotext);

    }

    public void setTagliaPantalonitext(String tagliaPantalonitext) {

        TagliaPantalonitext.setText(tagliaPantalonitext);

    }

    public void setTagliaScarpetext(String tagliaScarpetext) {

        TagliaScarpetext.setText(tagliaScarpetext);

    }

    public void Abilita_Disabilita_Campi(boolean editabile){

        GStext.setEditable(editabile);
        TagliaTestatext.setEditable(editabile);
        TagliaBustotext.setEditable(editabile);
        TagliaManotext.setEditable(editabile);
        TagliaPantalonitext.setEditable(editabile);
        TagliaScarpetext.setEditable(editabile);
        Abilitatext.setEditable(editabile);

    }

    public void VisibilitaSalvaButton(boolean visibilita){

        salvaButton.setVisible(visibilita);

    }
}
