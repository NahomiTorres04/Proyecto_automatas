/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import dk.brics.automaton.Automaton;
import dk.brics.automaton.RegExp;
import java.awt.Color;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import rojerusan.RSPanelsSlider;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.ImageIcon;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import rojerusan.RSNotifyFade;


/**
 *
 * @author Nahomi
 */
public class GUI extends javax.swing.JFrame {
    public String  grafo="";
    private FileWriter fotoa = null;
    private PrintWriter pw = null;
    private String rutaDot = "C:\\Program Files (x86)\\Graphviz2.38\\bin\\dot.exe";
    String textoCamino = "C:\\Automatas\\grafo1.txt"; //La expresión regular convertida a automata
    String imagen = "C:\\Automatas\\grafo1.jpg"; //ruta de la imágen del autómata
    String parametroT = "-Tjpg"; //parámetro para el tipo de imagen que se va a devolver
    String parametroO = "-o"; //Parámetro para el programa objeto4
    
    /**
     * Creates new form GUI
     */
    public GUI() {
        initComponents();
        //this.setIconImage(new ImageIcon(new ImageIcon(getClass().getResource("/iconos/iconof.ico")).getImage()));
        this.setLocationRelativeTo(null);
        transparencia();
    }
    public void transparencia()  //proceso para quitar fondos de componentes del jframe
    {
        btncerrar.setOpaque(false);
        btncerrar.setBorderPainted(false);
        btncerrar.setContentAreaFilled(false);
        btnminimizar.setOpaque(false);
        btnminimizar.setBorderPainted(false);
        btnminimizar.setContentAreaFilled(false);
        btncerrar1.setOpaque(false);
        btncerrar1.setBorderPainted(false);
        btncerrar1.setContentAreaFilled(false);
        btnminimizar1.setOpaque(false);
        btnminimizar1.setBorderPainted(false);
        btnminimizar1.setContentAreaFilled(false);
        btnverf.setOpaque(false);
        btnverf.setBorderPainted(false);
        btnverf.setContentAreaFilled(false);
        btndiagrama.setOpaque(false);
        btndiagrama.setBorderPainted(false);
        btndiagrama.setContentAreaFilled(false);
        btnveriftext.setOpaque(false);
        btnveriftext.setBorderPainted(false);
        btnveriftext.setContentAreaFilled(false);
        btnback.setOpaque(false);
        btnback.setBorderPainted(false);
        btnback.setContentAreaFilled(false);
        btnlimpiar.setOpaque(false);
        btnlimpiar.setBorderPainted(false);
        btnlimpiar.setContentAreaFilled(false);
    }
    private void Verificacion()
    {
        Pattern p = Pattern.compile(txtexpr.getText()); //se declara el objeto de tipo pattern
        Matcher m = p.matcher(txtapalabras.getText());   // se declara el objeto de tipo matcher
         String[]   pnvalidas = p.split(txtapalabras.getText());  //declare un arreglo de tipo string para guardar las palabras no aceptadas con la funcion split
           for(String s : pnvalidas) //cree este ciclo para mostrar las palabras no aceptadas en el textarea
           {
                  txtpa.setLineWrap(true); //se cargan en el jtextarea
                  txtpa.append(s  + "\n");
           }
          if (txtexpr.getText().length() >= 1)  //Esta condicion es para que entre a un proceso que subraya las palabras que coiciden con el patron
          {
            DefaultHighlighter.DefaultHighlightPainter highlightPainter = new DefaultHighlighter.DefaultHighlightPainter(Color.YELLOW); //aca se declara el objeto que nos sirve para subrayar
            Highlighter h = txtapalabras.getHighlighter(); //se le asigna la direccion del componente 
            h.removeAllHighlights(); //se elimina cualquier subrayado
            while (m.find())  // se crea el ciclo que busca las coincidencias con la funcion find
            {
                try {
                    h.addHighlight(m.start(), m.end(), highlightPainter); //se subraya la palabra
                } catch (BadLocationException ex) {
                    Logger.getLogger(Color.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
          }
        if(m.matches()) // esta condicion se utiliza para saber si dentro del texto hay palabras que son aceptadas o no aceptadas por el automata
        {
           new rojerusan.RSNotifyFade("¡ACEPTADA!", "Todas las Palabras son Válidas", Color.WHITE, Color.BLACK, Color.BLACK, SOMEBITS, RSNotifyFade.PositionNotify.BottomRight, RSNotifyFade.TypeNotify.SUCCESS).setVisible(true);

        }
        else
            new rojerusan.RSNotifyFade("¡ERROR!", "No todas las palabras son válida", Color.white, Color.black, Color.black, SOMEBITS, RSNotifyFade.PositionNotify.BottomRight, RSNotifyFade.TypeNotify.ERROR).setVisible(true);
    }
       
    private void VerificarExpresion(String er){
        for(int i = 0; i < er.length(); i++)
        {
            char caracter = er.charAt(i);
            if(((int) caracter == 42) && (i < er.length() - 1))
            {
                if((int) er.charAt(i + 1) == 42)
                {
                           new rojerusan.RSNotifyFade("¡ERROR!", "Expresión no válida, no se permite **", Color.white, Color.black, Color.black, SOMEBITS, RSNotifyFade.PositionNotify.BottomRight, RSNotifyFade.TypeNotify.ERROR).setVisible(true);
                           break;
                } 
            }
            else if(((int) caracter == 43) && (i < er.length() - 1))
            {
                if((int) er.charAt(i + 1) == 43)
                {
                          new rojerusan.RSNotifyFade("¡ERROR!", "Expresión no válida, no se permite ++", Color.white, Color.black, Color.black, SOMEBITS, RSNotifyFade.PositionNotify.BottomRight, RSNotifyFade.TypeNotify.ERROR).setVisible(true);
                          break;
                 }
            } 
            else if(((int) caracter == 123) && (i < er.length() - 1))
            {
                if((int) er.charAt(i + 1) == 125)
                {
                          new rojerusan.RSNotifyFade("¡ERROR!", "Expresión no válida, no se permite {}", Color.white, Color.black, Color.black, SOMEBITS, RSNotifyFade.PositionNotify.BottomRight, RSNotifyFade.TypeNotify.ERROR).setVisible(true);
                          break;
                 }
            }
            else if(((int) caracter == 91) && (i < er.length() - 1))
            {
                if((int) er.charAt(i + 1) == 93)
                {
                          new rojerusan.RSNotifyFade("¡ERROR!", "Expresión no válida, no se permite []", Color.white, Color.black, Color.black, SOMEBITS, RSNotifyFade.PositionNotify.BottomRight, RSNotifyFade.TypeNotify.ERROR).setVisible(true);
                          break;
                 }
            }
            else if(((int) caracter == 40) && (i < er.length() - 1))
            {
                if((int) er.charAt(i + 1) == 41)
                {
                          new rojerusan.RSNotifyFade("¡ERROR!", "Expresión no válida, no se permite ()", Color.white, Color.black, Color.black, SOMEBITS, RSNotifyFade.PositionNotify.BottomRight, RSNotifyFade.TypeNotify.ERROR).setVisible(true);
                          break;
                 }
            }
            else if(((int) caracter == 60) && (i < er.length() - 1))
            {
                          new rojerusan.RSNotifyFade("¡ERROR!", "Expresión no válida, no se permite <", Color.white, Color.black, Color.black, SOMEBITS, RSNotifyFade.PositionNotify.BottomRight, RSNotifyFade.TypeNotify.ERROR).setVisible(true);
                          break;
            } 
            else if(((int) caracter == 62) && (i < er.length() - 1))
            {
                          new rojerusan.RSNotifyFade("¡ERROR!", "Expresión no válida, no se permite >", Color.white, Color.black, Color.black, SOMEBITS, RSNotifyFade.PositionNotify.BottomRight, RSNotifyFade.TypeNotify.ERROR).setVisible(true);
                          break;
            } 
            else if(((int) caracter == 124 && (i < er.length() - 1)))
               {
                        if((int) er.charAt(i + 1) == 124)
                        {
                             new rojerusan.RSNotifyFade("¡ERROR!", "Expresión no válida, no se permite |", Color.white, Color.black, Color.black, SOMEBITS, RSNotifyFade.PositionNotify.BottomRight, RSNotifyFade.TypeNotify.ERROR).setVisible(true);
                             break;
                        } 
                } 
            else if(((int) caracter == 92 && (i < er.length() - 1)))
               {
                           new rojerusan.RSNotifyFade("¡ERROR!", "Expresión no válida, no se permite '\'", Color.white, Color.black, Color.black, SOMEBITS, RSNotifyFade.PositionNotify.BottomRight, RSNotifyFade.TypeNotify.ERROR).setVisible(true); 
                           break;
                } 
            else if(((int) caracter == 126 && (i < er.length() - 1)))
               {
                           new rojerusan.RSNotifyFade("¡ERROR!", "Expresión no válida, no se permite ~", Color.white, Color.black, Color.black, SOMEBITS, RSNotifyFade.PositionNotify.BottomRight, RSNotifyFade.TypeNotify.ERROR).setVisible(true); 
                           break;
                }
            else if(((int) caracter == 35 && (i < er.length() - 1)))
               {
                           new rojerusan.RSNotifyFade("¡ERROR!", "Expresión no válida, no se permite #", Color.white, Color.black, Color.black, SOMEBITS, RSNotifyFade.PositionNotify.BottomRight, RSNotifyFade.TypeNotify.ERROR).setVisible(true);
                           break;
                }
            else if(((int) caracter == 34 && (i < er.length() - 1)))
               {
                           new rojerusan.RSNotifyFade("¡ERROR!", "Expresión no válida", Color.white, Color.black, Color.black, SOMEBITS, RSNotifyFade.PositionNotify.BottomRight, RSNotifyFade.TypeNotify.ERROR).setVisible(true);
                           break;
                }
            else
            {
                new rojerusan.RSNotifyFade("¡ACEPTADA!", "Expresión Válida", Color.WHITE, Color.BLACK, Color.BLACK, SOMEBITS, RSNotifyFade.PositionNotify.BottomRight, RSNotifyFade.TypeNotify.SUCCESS).setVisible(true);
            }
           }
      }

    private void grafico(){
        
        RegExp exp = new RegExp(txtexpr.getText()); //por medio de la libreria automaton.RegExp se guarda la expresion regular ingresada por el usuario en un objeto de tipo RegExp
        Automaton a = exp.toAutomaton();   //se convierte la expreion regular en un automata y se guarda en un objeto de tipo automata (a)
        grafo = a.toDot();   //Instrucciones para generar el gráfico
        //txtpa.setText(grafo); // aca se muestra el codigo que se utilizo para la grafica del automata
        creaTexto(grafo);
    }
    
    public void creaTexto(String g){
        try{
                fotoa = new FileWriter(textoCamino);//se indica la ruta en donde se creara el archivo de texto
                pw = new PrintWriter(fotoa); //se indica en donde se escribiran las instrucciones para generar el gráfico
                g = g.replace("u0020","Ɛ"); //cambia el espacio " " por el caracter Ɛ
                pw.write(g); //se escriben las instrucciones en el archivo de texto
                fotoa.close(); // se cierra el archivo de texto
                g=""; //se inicializa la variable
        }
        catch(IOException e){
            System.err.println("ERROR en archivo: "+ e.toString()); //se indica si ha ocurrido un error
        }
    }
    
    public void creaImagen()
    {
        try{
            String[] cmd = new String[5]; //se crea un arreglo cpara crear la imagen con todos sus parametros
            cmd[0] = rutaDot;                        //se envía la ruta donde se encuentra el programa que nos sirve para graficar en este caso GraphViz
            cmd[1] = parametroT;                 //se le indica que tipo de imagen se creará
            cmd[2] = textoCamino;               //instrucciones de la expresion regular que se graficará
            cmd[3] = parametroO;                 //parametro de tipo objeto
            cmd[4] = imagen;                          //ruta donde se guardara la imagen
            
            Runtime rt = Runtime.getRuntime();  //se crea la variable de tipo RunTime

            rt.exec(cmd);                               //se crea la imagen
        }
        catch(Exception ex){
            ex.printStackTrace();             
        }
    }
    
    public void borraArchivo(String path){
        File fotoa = new File(path);          // se crea un nuevo objeto de tipo File con la direccion que se quiere eliminar
        fotoa.delete();                                 //se elimina el archivo
    }
    
    public void reinicio(){
        /**
         * se inician de nuevo las variables
         * para su uso en una nueva expresión regular
         */
        grafo= "";
        fotoa = null;
        pw=null;
    }
        private void cargarimagen(ImageIcon diagrama)
    {
   
         foto.repaint();                                    //funcion para refrescar el label
         diagrama.getImage().flush();       //se borra de la chaché la información de la imagen anterior si hubiera una
        foto.setIcon(diagrama);                   //se coloca la imagen en el label
   
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        p1 = new javax.swing.JPanel();
        rSPanelsSlider1 = new rojerusan.RSPanelsSlider();
        panelprincipal = new javax.swing.JPanel();
        btnminimizar = new javax.swing.JButton();
        btncerrar = new javax.swing.JButton();
        txtexpr = new javax.swing.JTextField();
        lbingtext = new javax.swing.JLabel();
        btnverf = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtapalabras = new javax.swing.JTextArea();
        lbexpr = new javax.swing.JLabel();
        btndiagrama = new javax.swing.JButton();
        btnveriftext = new javax.swing.JButton();
        btnlimpiar = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtpa = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        pdiagrama = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        foto = new javax.swing.JLabel();
        btnminimizar1 = new javax.swing.JButton();
        btncerrar1 = new javax.swing.JButton();
        expresion = new javax.swing.JLabel();
        btnback = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(1307, 798));
        getContentPane().setLayout(new java.awt.CardLayout());

        p1.setLayout(new java.awt.CardLayout());

        rSPanelsSlider1.setPreferredSize(new java.awt.Dimension(1308, 800));

        panelprincipal.setBackground(new java.awt.Color(244, 202, 204));
        panelprincipal.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        panelprincipal.setName("panelprincipal"); // NOI18N
        panelprincipal.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnminimizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/icons8_Compress_35px.png"))); // NOI18N
        btnminimizar.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/icons8_Compress_45px.png"))); // NOI18N
        btnminimizar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnminimizarMouseClicked(evt);
            }
        });
        panelprincipal.add(btnminimizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1187, 0, 50, 50));

        btncerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/icons8_Cancel_35px.png"))); // NOI18N
        btncerrar.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/icons8_Cancel_45px.png"))); // NOI18N
        btncerrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btncerrarMouseClicked(evt);
            }
        });
        panelprincipal.add(btncerrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1240, 0, 60, 50));

        txtexpr.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 18)); // NOI18N
        txtexpr.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        panelprincipal.add(txtexpr, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 123, 490, 50));

        lbingtext.setFont(new java.awt.Font("Yu Gothic UI", 0, 16)); // NOI18N
        lbingtext.setText("Ingrese Texto");
        panelprincipal.add(lbingtext, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 210, 210, -1));

        btnverf.setBackground(new java.awt.Color(253, 242, 243));
        btnverf.setFont(new java.awt.Font("Yu Gothic UI", 0, 13)); // NOI18N
        btnverf.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/icons8_Checked_Radio_Button_55px.png"))); // NOI18N
        btnverf.setText("Verificar Expresión");
        btnverf.setBorder(null);
        btnverf.setPreferredSize(new java.awt.Dimension(203, 60));
        btnverf.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/icons8_Checked_Radio_Button_70px.png"))); // NOI18N
        btnverf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnverfActionPerformed(evt);
            }
        });
        panelprincipal.add(btnverf, new org.netbeans.lib.awtextra.AbsoluteConstraints(557, 120, 220, 60));

        txtapalabras.setColumns(20);
        txtapalabras.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 18)); // NOI18N
        txtapalabras.setRows(5);
        txtapalabras.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jScrollPane1.setViewportView(txtapalabras);

        panelprincipal.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 240, 720, 490));

        lbexpr.setFont(new java.awt.Font("Yu Gothic UI", 0, 16)); // NOI18N
        lbexpr.setText("Expresión Regular");
        panelprincipal.add(lbexpr, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 90, 210, -1));

        btndiagrama.setBackground(new java.awt.Color(253, 242, 243));
        btndiagrama.setFont(new java.awt.Font("Yu Gothic UI", 0, 13)); // NOI18N
        btndiagrama.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/icons8_Mind_Map_55px_1.png"))); // NOI18N
        btndiagrama.setText("Generar Diagrama");
        btndiagrama.setBorder(null);
        btndiagrama.setPreferredSize(new java.awt.Dimension(203, 60));
        btndiagrama.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/icons8_Mind_Map_70px_1.png"))); // NOI18N
        btndiagrama.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btndiagramaMouseClicked(evt);
            }
        });
        panelprincipal.add(btndiagrama, new org.netbeans.lib.awtextra.AbsoluteConstraints(936, 519, 220, 80));

        btnveriftext.setBackground(new java.awt.Color(253, 242, 243));
        btnveriftext.setFont(new java.awt.Font("Yu Gothic UI", 0, 13)); // NOI18N
        btnveriftext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/icons8_Check_55px.png"))); // NOI18N
        btnveriftext.setText("Verificar Texto    ");
        btnveriftext.setActionCommand("");
        btnveriftext.setBorder(null);
        btnveriftext.setPreferredSize(new java.awt.Dimension(203, 60));
        btnveriftext.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/icons8_Check_70px.png"))); // NOI18N
        btnveriftext.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnveriftextMouseClicked(evt);
            }
        });
        panelprincipal.add(btnveriftext, new org.netbeans.lib.awtextra.AbsoluteConstraints(936, 420, 220, 80));

        btnlimpiar.setBackground(new java.awt.Color(253, 242, 243));
        btnlimpiar.setFont(new java.awt.Font("Yu Gothic UI", 0, 13)); // NOI18N
        btnlimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/icons8_Broom_55px.png"))); // NOI18N
        btnlimpiar.setText("Limpiar");
        btnlimpiar.setBorder(null);
        btnlimpiar.setPreferredSize(new java.awt.Dimension(203, 60));
        btnlimpiar.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/icons8_Broom_70px.png"))); // NOI18N
        btnlimpiar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnlimpiarMouseClicked(evt);
            }
        });
        btnlimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnlimpiarActionPerformed(evt);
            }
        });
        panelprincipal.add(btnlimpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(936, 620, 220, 80));

        jLabel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        panelprincipal.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 390, 310, 340));

        txtpa.setEditable(false);
        txtpa.setColumns(20);
        txtpa.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 18)); // NOI18N
        txtpa.setRows(5);
        txtpa.setDisabledTextColor(new java.awt.Color(153, 153, 153));
        jScrollPane2.setViewportView(txtpa);

        panelprincipal.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 130, 420, 240));

        jLabel4.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 16)); // NOI18N
        jLabel4.setText("Palabras NO aceptadas");
        panelprincipal.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 90, 210, 30));

        jLabel2.setBackground(new java.awt.Color(253, 242, 243));
        jLabel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jLabel2.setOpaque(true);
        jLabel2.setPreferredSize(new java.awt.Dimension(1280, 700));
        panelprincipal.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(13, 48, 1280, 730));

        jLabel6.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel6.setText("Automáta Finito no Determinista");
        panelprincipal.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 10, 450, 30));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/logo.jpg"))); // NOI18N
        panelprincipal.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(13, 0, 50, -1));

        rSPanelsSlider1.add(panelprincipal, "card2");

        pdiagrama.setBackground(new java.awt.Color(244, 202, 204));
        pdiagrama.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        pdiagrama.setName("pdiagrama"); // NOI18N
        pdiagrama.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Yu Gothic UI", 2, 36)); // NOI18N
        jLabel1.setText("   Diagrama");
        jLabel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 3));
        pdiagrama.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(34, 70, 234, 85));

        foto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 3));
        pdiagrama.add(foto, new org.netbeans.lib.awtextra.AbsoluteConstraints(34, 179, 1240, 570));

        btnminimizar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/icons8_Compress_35px.png"))); // NOI18N
        btnminimizar1.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/icons8_Compress_45px.png"))); // NOI18N
        btnminimizar1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnminimizar1MouseClicked(evt);
            }
        });
        pdiagrama.add(btnminimizar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1190, 0, 50, 50));

        btncerrar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/icons8_Cancel_35px.png"))); // NOI18N
        btncerrar1.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/icons8_Cancel_45px.png"))); // NOI18N
        btncerrar1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btncerrar1MouseClicked(evt);
            }
        });
        btncerrar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncerrar1ActionPerformed(evt);
            }
        });
        pdiagrama.add(btncerrar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1240, 0, 60, 50));

        expresion.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 24)); // NOI18N
        expresion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 3));
        pdiagrama.add(expresion, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 70, 980, 85));

        btnback.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/icons8_Back_Arrow_35px.png"))); // NOI18N
        btnback.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/icons8_Back_Arrow_45px.png"))); // NOI18N
        btnback.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnbackMouseClicked(evt);
            }
        });
        pdiagrama.add(btnback, new org.netbeans.lib.awtextra.AbsoluteConstraints(1140, 0, 50, 50));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/logo.jpg"))); // NOI18N
        pdiagrama.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(13, 0, 50, -1));

        jLabel9.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel9.setText("Automáta Finito no Determinista");
        pdiagrama.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 10, 450, 30));

        jLabel3.setBackground(new java.awt.Color(253, 242, 243));
        jLabel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jLabel3.setOpaque(true);
        jLabel3.setPreferredSize(new java.awt.Dimension(1280, 700));
        pdiagrama.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(13, 48, 1280, 730));

        rSPanelsSlider1.add(pdiagrama, "card3");

        p1.add(rSPanelsSlider1, "card2");

        getContentPane().add(p1, "card2");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btncerrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btncerrarMouseClicked
        this.dispose(); //funcion para cerrar el frame
    }//GEN-LAST:event_btncerrarMouseClicked

    private void btnminimizarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnminimizarMouseClicked
        this.setExtendedState(ICONIFIED); //funcion para minimizar
    }//GEN-LAST:event_btnminimizarMouseClicked

    private void btnminimizar1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnminimizar1MouseClicked
        this.setExtendedState(ICONIFIED); //funcion para minimizar
    }//GEN-LAST:event_btnminimizar1MouseClicked

    private void btncerrar1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btncerrar1MouseClicked
        this.dispose(); // funcion para cerrar
        borraArchivo(imagen); //se borra la ruta de la imagen
        reinicio(); //se llama a la funcion reinicio
        borraArchivo(textoCamino); //se borram las instrucciones para graficar
    }//GEN-LAST:event_btncerrar1MouseClicked

    private void btndiagramaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btndiagramaMouseClicked
        rSPanelsSlider1.setPanelSlider(pdiagrama, RSPanelsSlider.DIRECT.LEFT); //se llama al panel donde se ecuentra el diagrama
               expresion.setText(txtexpr.getText()); //se coloca la expresion en el label
            ImageIcon img = new ImageIcon(imagen); // se crea el imageicon 
            cargarimagen(img);                                          //llama a la funcion cargar imagen

        
    }//GEN-LAST:event_btndiagramaMouseClicked

    private void btnverfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnverfActionPerformed
        reinicio(); // se reicia por si habia algo anteriormente
        borraArchivo(textoCamino);  //se borran las instrucciones para graficar
        borraArchivo(imagen);            //se elimina la ruta de la imagen y la imagen
        VerificarExpresion(txtexpr.getText());           //se verifica la expresion
        grafico();                                   //se generan las instrucciones para el grpafico
        creaImagen();                             //se crea la imagen
    }//GEN-LAST:event_btnverfActionPerformed

    private void btnbackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnbackMouseClicked
        rSPanelsSlider1.setPanelSlider(panelprincipal, RSPanelsSlider.DIRECT.RIGHT); //se llama al panel principal para regresar
        borraArchivo(imagen); //se borra la ruta de la imagen y la imagen
        reinicio();                       //se reinician las variables
        borraArchivo(textoCamino);  //se borran las instrucciones para la imagen
    }//GEN-LAST:event_btnbackMouseClicked

    private void btncerrar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncerrar1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btncerrar1ActionPerformed

    private void btnveriftextMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnveriftextMouseClicked
         txtpa.setText("");
        Verificacion(); //se llama a la función verificación
    }//GEN-LAST:event_btnveriftextMouseClicked

    private void btnlimpiarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnlimpiarMouseClicked
        txtexpr.setText("");
        txtapalabras.setText("");
        txtpa.setText("");
        borraArchivo(imagen); //se borra la ruta de la imagen y la imagen
        reinicio();                       //se reinician las variables
        borraArchivo(textoCamino);  //se borran las instrucciones para la imagen
    }//GEN-LAST:event_btnlimpiarMouseClicked

    private void btnlimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnlimpiarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnlimpiarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnback;
    private javax.swing.JButton btncerrar;
    private javax.swing.JButton btncerrar1;
    private javax.swing.JButton btndiagrama;
    private javax.swing.JButton btnlimpiar;
    private javax.swing.JButton btnminimizar;
    private javax.swing.JButton btnminimizar1;
    private javax.swing.JButton btnverf;
    private javax.swing.JButton btnveriftext;
    private javax.swing.JLabel expresion;
    private javax.swing.JLabel foto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbexpr;
    private javax.swing.JLabel lbingtext;
    private javax.swing.JPanel p1;
    private javax.swing.JPanel panelprincipal;
    private javax.swing.JPanel pdiagrama;
    private rojerusan.RSPanelsSlider rSPanelsSlider1;
    private javax.swing.JTextArea txtapalabras;
    private javax.swing.JTextField txtexpr;
    private javax.swing.JTextArea txtpa;
    // End of variables declaration//GEN-END:variables

    private static class RegExpImpl extends RegExp {

        public RegExpImpl(String expresionr2) {
            super(expresionr2);
        }
    }
}
