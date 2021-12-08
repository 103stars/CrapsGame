package juegoCraps;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame{

    private static final String MENSAJE_INICIO = "Bienvenido a Craps \n"
            + "Oprime el botón lanzar para iniciar el juego"
            + "\nSi tu tiro de salida es 7 u 11 ganas con Natural"
            + "\nSi tu tiro de salida es 2, 3 o 12 pierdes con Craps"
            + "\nSi sacas cualquier otro valor establecerás Punto"
            + "\nEstado en punto podrás seguir lanzando los dados"
            + "\npero ahora ganarás si sacas nuevamente el valor del Punto"
            + "\nsin que previamente hayas sacado 7";

    private Header headerProject;
    private JLabel dado1, dado2;
    private JButton lanzar;
    private JPanel panelDados, panelResultados;
    private ImageIcon imageDado;
    private JTextArea mensajeSalida, resultadosDados;
    private JSeparator separator;
    private Escucha escucha;
    private ModelCraps modelCraps;

    public GUI(){
        initGUI();
        this.setTitle("Juego Craps");
        //this.setSize(600, 520);
        this.pack();
        this.setResizable(true);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void initGUI() {
        // Set up JFrame container's layout
        //Create Listener Object or control Object
        escucha = new Escucha();

        modelCraps= new ModelCraps();

        //Setup JComponents

        headerProject = new Header("Header ", Color.BLACK);
        this.add(headerProject, BorderLayout.NORTH);
        imageDado = new ImageIcon(getClass().getResource("/resources/dado.png"));
        dado1 = new JLabel(imageDado);
        dado2 = new JLabel(imageDado);

        lanzar = new JButton("Lanzar");
        lanzar.addActionListener(escucha);

        panelDados = new JPanel();
        panelDados.setPreferredSize(new Dimension(300, 180));
        panelDados.setBorder(BorderFactory.createTitledBorder("Tus dados"));
        panelDados.add(dado1);
        panelDados.add(dado2);
        panelDados.add(lanzar);
        this.add(panelDados,BorderLayout.CENTER);

        mensajeSalida = new JTextArea(7,31);
        mensajeSalida.setText(MENSAJE_INICIO);
        mensajeSalida.setEditable(false);
        //mensajeSalida.setBorder(BorderFactory.createTitledBorder("Que debes hacer"));
        JScrollPane scroll = new JScrollPane(mensajeSalida);

        panelResultados= new JPanel();
        panelResultados.setBorder(BorderFactory.createTitledBorder("Que debes hacer"));
        panelResultados.add(scroll);
        panelResultados.setPreferredSize(new Dimension(370, 180));

        this.add(panelResultados,BorderLayout.EAST);

        resultadosDados = new JTextArea(4,31);
        separator= new JSeparator();
        separator.setPreferredSize(new Dimension(320, 7));
        separator.setBackground(Color.BLUE);




    }
    public static void main(String[] args){
        EventQueue.invokeLater(()-> {
            GUI miProjectGUI = new GUI();
        });
    }

    private class Escucha implements ActionListener {


        @Override
        public void actionPerformed(ActionEvent e) {

            modelCraps.calcularTiro();
            int[] caras = modelCraps.getCaras();

            imageDado = new ImageIcon(getClass().getResource("/resources/"+caras[0]+".png"));
            dado1.setIcon(imageDado);

            imageDado = new ImageIcon(getClass().getResource("/resources/"+caras[1]+".png"));
            dado2.setIcon(imageDado);

            modelCraps.determinarJuego();

            panelResultados.removeAll();
            panelResultados.setBorder(BorderFactory.createTitledBorder("Resultados"));

            panelResultados.add(resultadosDados);
            panelResultados.add(separator);
            panelResultados.add(mensajeSalida);

            resultadosDados.setText(modelCraps.getEstadoToString()[0]);
            mensajeSalida.setRows(4);
            mensajeSalida.setText(modelCraps.getEstadoToString()[1]);

            revalidate();
            repaint();



        }
    }

}
