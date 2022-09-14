
package conversiones;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Conversor extends JFrame {

    JPanel panel = new JPanel();
    JTextArea textBox1 = new JTextArea();
    JTextArea textBox2 = new JTextArea();
    JTextArea textBox3 = new JTextArea();
    String numerator, denominator, entireNumber, unidad;
    int j = 0, a = 0, fraction2, fraction3;

    public Conversor() {
        this.setSize(700, 270);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setTitle("Conversor de unidad internacional a inglesa");
        InsertarComponentes();
    }

    public void InsertarComponentes() {
        panel.setLayout(null);
        panel.setBackground(Color.gray);
        this.getContentPane().add(panel);
        setLabel();
        setTextBox();
        setButton();
    }

    public void setLabel() {
        JLabel titulo = new JLabel("CONVERSOR DE UNIDADES");
        titulo.setBounds(210, 20, 350, 20);
        titulo.setForeground(Color.white);
        titulo.setFont(new Font("arial", 1, 25));
        panel.add(titulo);

        JLabel milimetros = new JLabel("Milimetros: ");
        milimetros.setBounds(20, 60, 350, 20);
        milimetros.setForeground(Color.black);
        milimetros.setFont(new Font("arial", 1, 25));
        panel.add(milimetros);

        JLabel milesimas = new JLabel("Pulgadas: ");
        milesimas.setBounds(20, 100, 350, 35);
        milesimas.setForeground(Color.black);
        milesimas.setFont(new Font("arial", 1, 25));
        panel.add(milesimas);

        JLabel fraction = new JLabel("Fracción de pulgada: ");
        fraction.setBounds(20, 150, 350, 35);
        fraction.setForeground(Color.black);
        fraction.setFont(new Font("arial", 1, 25));
        panel.add(fraction);
    }

    public void setTextBox() {
        textBox1.setBounds(160, 60, 100, 20);
        textBox2.setBounds(160, 110, 100, 20);
        textBox3.setBounds(280, 160, 100, 20);
        panel.add(textBox1);
        panel.add(textBox2);
        panel.add(textBox3);
    }

    public void setButton() {
        JButton button1 = new JButton("CONVERTIR");
        JButton boton2 = new JButton("LIMPIAR");
        button1.setBounds(500, 100, 100, 30);
        boton2.setBounds(500, 150, 100, 30);
        panel.add(button1);
        panel.add(boton2);
        
        //Clear button
        ActionListener firstListener = (ActionEvent ae) -> {
            textBox1.setText("");
            textBox2.setText("");
            textBox3.setText("");
            fraction2 = 0;
            fraction3 = 0;
            j = 0;
            a = 0;
        };
        boton2.addActionListener(firstListener); //Add action to the button
        //Continue button

        ActionListener secondListener = (ActionEvent ae) -> {
            String empty = "";
            // ALL ARE EMPTIES
            if (textBox2.getText().equalsIgnoreCase(empty) == true && textBox3.getText().equalsIgnoreCase(empty) == true && textBox1.getText().equalsIgnoreCase(empty) == true) {
                textBox1.setText(empty);
                textBox2.setText(empty);
                textBox3.setText(empty);
            } //FIRST TEXT BOX
            else if (textBox2.getText().equalsIgnoreCase(empty) == true && textBox3.getText().equalsIgnoreCase(empty)) {
                String milimetro = textBox1.getText();
                Float mm = Float.parseFloat(milimetro);
                float milesima_de_pulgada = (float) (mm / 25.4f);
                String valor_textBox2 = String.valueOf(milesima_de_pulgada);
                textBox2.setText(valor_textBox2);
                ///////////////////////
                float fraction = (float) (milesima_de_pulgada / (1 / 128f));
                fraction2 = (int) (fraction);
                int x1 = 128;
                fraction3 = fraction2;
                if (fraction2 % 128 == 0) {
                    a = fraction2 / 128;
                    String a2 = String.valueOf(a);
                    textBox3.setText(a2);
                    fraction2 = 0;
                } else {
                    while (fraction2 >= 128) {
                        fraction2 -= 128;
                    }
                    if (fraction3 >= 128) {
                        while (fraction3 >= 128) {
                            j++;
                            entireNumber = String.valueOf(j);
                            fraction3 -= 128;
                        }
                        textBox3.setText(entireNumber + "  ");
                    }
                    if (fraction2 % 2 == 0) {
                        while (fraction2 % 2 == 0) {
                            fraction2 /= 2;
                            x1 /= 2;
                        }
                    }
                    String valor_textBox3 = String.valueOf(fraction2);
                    textBox3.append(valor_textBox3 + "/" + x1);
                }
            } //SECOND TEXT BOX
            else if (textBox1.getText().equalsIgnoreCase(empty) == true && textBox3.getText().equalsIgnoreCase(empty)) {
                String milesima = textBox2.getText();
                Float milesima_inch = Float.parseFloat(milesima);
                float milimetro = (float) (milesima_inch * (25.4f));
                String mm = String.valueOf(milimetro);
                textBox1.setText(mm);
                ////////////////////////////
                float fraction = (float) (milesima_inch / (1 / 128f));
                fraction2 = (int) (fraction);
                int x2 = 128;
                fraction3 = fraction2;
                if (fraction2 % 128 == 0) {
                    a = fraction2 / 128;
                    String a2 = String.valueOf(a);
                    textBox3.setText(a2);
                    fraction2 = 0;
                } else {
                    while (fraction2 >= 128) {
                        fraction2 -= 128;
                    }
                    if (fraction3 >= 128) {
                        while (fraction3 >= 128) {
                            j++;
                            entireNumber = String.valueOf(j);
                            fraction3 -= 128;
                        }
                        textBox3.setText(entireNumber + "  ");
                    }
                    if (fraction2 % 2 == 0) {
                        while (fraction2 % 2 == 0) {
                            fraction2 /= 2;
                            x2 /= 2;
                        }
                    }
                    String valor_textBox3 = String.valueOf(fraction2);
                    textBox3.append(valor_textBox3 + "/" + x2);
                }
            } //THIRD TEXT BOX
            else if (textBox1.getText().equalsIgnoreCase(empty) == true && textBox2.getText().equalsIgnoreCase(empty)) {
                String fraction = textBox3.getText();
                Pattern p = Pattern.compile("\\d+");
                Matcher m = p.matcher(fraction);
                int i = 0;
                while (m.find()) {
                    if (i == 0) {
                        numerator = m.group();
                        float num = Float.parseFloat(numerator);
                        float mm = (float) (num * 25.4);
                        String valor_textBox1 = String.valueOf(mm);
                        textBox1.setText(valor_textBox1);
                        float mm_inch = (float) (num * 1);
                        String valor_textBox2 = String.valueOf(mm_inch);
                        textBox2.setText(valor_textBox2);
                    }
                    if (i == 1) {
                        denominator = m.group();
                        float num = Float.parseFloat(numerator);
                        float den = Float.parseFloat(denominator);
                        float mm = (float) ((num / den) * 25.4);
                        String valor_textBox1 = String.valueOf(mm);
                        textBox1.setText(valor_textBox1);
                        float mm_inch = (float) (num / den);
                        String valor_textBox2 = String.valueOf(mm_inch);
                        textBox2.setText(valor_textBox2);
                    }
                    if (i == 2) {
                        unidad = m.group();
                        float num = Float.parseFloat(numerator);
                        float den = Float.parseFloat(denominator);
                        float uno = Float.parseFloat(unidad);
                        float mm = (float) (num * 25.4);
                        mm += (den / uno) * 25.4;
                        String valor_textBox1 = String.valueOf(mm);
                        textBox1.setText(valor_textBox1);
                        float mm_inch = (float) (num + (den / uno));
                        String valor_textBox2 = String.valueOf(mm_inch);
                        textBox2.setText(valor_textBox2);
                    }
                    i++;
                }
            }
        };
        button1.addActionListener(secondListener); //Add action to the button
    }

    public static void main(String[] args) {
        Conversor c1 = new Conversor();
        c1.setVisible(true);
    }
}
