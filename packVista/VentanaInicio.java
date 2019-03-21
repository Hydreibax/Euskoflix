package packVista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import org.eclipse.wb.swing.FocusTraversalOnArray;

import packModelo.ModeloEtiquetas;
import packModelo.ModeloTitulos;
import packModelo.ModeloValoracion;

import java.awt.Component;

public class VentanaInicio extends JFrame {

	private static VentanaInicio miVentanaInicio = new VentanaInicio();
	private JLabel panelGeneral;
	private JPanel panelFlow;
	private JPanel panelAbsolute;	
	// Para anniadir una JScrollBar es, al parecer, necesario que el layout no sea null (o sea, absolute),
	// asi que generamos un segundo panel con otro layout diferente.
	private JLabel logo;
	private JLabel fondo;
	private JButton btnCargarTitulos;
	private JButton btnCargarEtiquetas;
	private JButton btnCargarValoraciones;
	private JButton btnVisualizarTitulos;
	private JButton btnVisualizarEtiquetas;
	private JButton btnVisualizarValoraciones;
	private JTextArea visualizador;
	
	// Tal y como se esta programando, para que los elementos salgan en pantalla tienen que ser
	// anniadidos a su panel correspondiente, pero en vez de hacer contentPane.add(logo), hacemos
	// contentPane.add(getLogo()) porque asi podemos editar cada elemento en su propio metodo de get
	
	private VentanaInicio() {} // Al hacerla Singleton quitamos todo de la constructora
	
	public void arrancar(){ // Y ponemos todo aqui
		setTitle("Euskoflix");
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaInicio.class.getResource("/packImagenes/E.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 750);
		panelGeneral = new JLabel();
		panelGeneral.setBorder(new EmptyBorder(0, 0, 0, 0));
		Image img = new ImageIcon(this.getClass().getResource("/packImagenes/Fondo.png")).getImage();
		panelGeneral.setIcon(new ImageIcon(img));
		setContentPane(panelGeneral);
		panelGeneral.setLayout(new BorderLayout(0, 0));
		panelGeneral.add(getPanelFlow(), BorderLayout.NORTH);
		panelFlow.setPreferredSize(new Dimension(1000, 275)); // Al anniadirlo en la linea anterior se quedaba pequennio, aqui se agranda
		getPanelFlow().add(getPanelAbsolute());
		panelAbsolute.setPreferredSize(new Dimension(1000, 275)); // Same as above
		panelAbsolute.add(getLogo());
		panelAbsolute.add(getBtnCargarTitulos());
		panelAbsolute.add(getBtnVisualizarTitulos());
		panelAbsolute.add(getBtnCargarEtiquetas());
		panelAbsolute.add(getBtnVisualizarEtiquetas());
		panelAbsolute.add(getBtnCargarValoraciones());
		panelAbsolute.add(getBtnVisualizarValoraciones());		
		panelGeneral.add(getVisualizador(), BorderLayout.CENTER); // Anniadimos el elemento en el centro debido a que al
															// ponerlo ahi se extiende a todo el panel si no hay mas elementos
		JScrollPane scrollBar = new JScrollPane(visualizador, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		panelGeneral.add(scrollBar);
		//setUndecorated(true); // Si quitamos el comentario, pasaria a no tener la barra superior de la ventana
		setVisible(true); // Necesario, siendo Singleton, sin esto, no se veria la ventana
	}
	
	public static VentanaInicio getVentanaInicio()
	{
		return miVentanaInicio;
	}
	
	private JLabel getLogo()
	{
		if (logo == null)
		{
			logo = new JLabel(""); // Nombre que saldra al ejecutar
			logo.setBounds(352, 10, 296, 86); // setBounds(posicionX, posicionY, tamannioX, tamannioY)
			Image img = new ImageIcon(this.getClass().getResource("/packImagenes/Euskoflix.png")).getImage(); // Asi se hace que una JLabel tenga imagen
			logo.setIcon(new ImageIcon(img)); // Aqui se pone la imagen
		}
		return logo;
	}
	
	private JButton getBtnCargarTitulos() {
		if (btnCargarTitulos == null) {
			btnCargarTitulos = new JButton("Cargar titulos de peliculas");
			btnCargarTitulos.addActionListener(new ActionListener() { // Todos los botones tendran listeners asi
				public void actionPerformed(ActionEvent e) { // Accion a ejecutar cuando el boton sea pulsado
					ModeloTitulos.getModeloTitulos().cargaTitles();
					visualizador.setText("");
				}
			});
			btnCargarTitulos.setBounds(50, 125, 250, 25);
		}
		return btnCargarTitulos;
	}
	private JButton getBtnVisualizarTitulos() {
		if (btnVisualizarTitulos == null) {
			btnVisualizarTitulos = new JButton("Visualizar titulos de peliculas");
			btnVisualizarTitulos.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					ModeloTitulos.getModeloTitulos().imprimirDatos();
				}
			});
			btnVisualizarTitulos.setBounds(50, 200, 250, 25);
		}
		return btnVisualizarTitulos;
	}

	private JButton getBtnCargarValoraciones() {
		if (btnCargarValoraciones == null) {
			btnCargarValoraciones = new JButton("Cargar valoraciones de peliculas");
			btnCargarValoraciones.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ModeloValoracion.getModeloValoracion().cargaRatings();
					visualizador.setText("");
				}
			});
			btnCargarValoraciones.setBounds(375, 125, 250, 25);
		}
		return btnCargarValoraciones;
	}

	private JButton getBtnVisualizarValoraciones() {
		if (btnVisualizarValoraciones == null) {
			btnVisualizarValoraciones = new JButton("Visualizar valoraciones de peliculas");
			btnVisualizarValoraciones.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ModeloValoracion.getModeloValoracion().imprimirDatos();
				}
			});
			btnVisualizarValoraciones.setBounds(375, 200, 250, 25);
		}
		return btnVisualizarValoraciones;
	}

	private JButton getBtnCargarEtiquetas() {
		if (btnCargarEtiquetas == null) {
			btnCargarEtiquetas = new JButton("Cargar etiquetas de peliculas");
			btnCargarEtiquetas.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ModeloEtiquetas.getModeloEtiquetas().cargarTags();
					visualizador.setText("");
				}
			});
			btnCargarEtiquetas.setBounds(700, 125, 250, 25);
		}
		return btnCargarEtiquetas;
	}
	private JButton getBtnVisualizarEtiquetas() {
		if (btnVisualizarEtiquetas == null) {
			btnVisualizarEtiquetas = new JButton("Visualizar etiquetas de peliculas");
			btnVisualizarEtiquetas.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ModeloEtiquetas.getModeloEtiquetas().imprimirDatos();
				}
			});
			btnVisualizarEtiquetas.setBounds(700, 200, 250, 25);
		}
		return btnVisualizarEtiquetas;
	}
	private JTextArea getVisualizador() {
		if (visualizador == null) {
			visualizador = new JTextArea();
			visualizador.setFont(new Font("Arial", Font.PLAIN, 13));
			visualizador.setBounds(50, 275, 900, 400);
			visualizador.setEditable(false); // Prohibimos la edicion del JTextPane
			visualizador.setText("");
		}
		return visualizador;
	}
	
	public void unirTexto(String pTexto)
	{
		visualizador.append(pTexto);
	}

	private JPanel getPanelFlow()
	{
		if (panelFlow == null)
		{
			panelFlow = new JPanel();
			panelFlow.setBounds(0, 0, 1000, 275);
			panelFlow.setOpaque(false);
		}
		return panelFlow;
	}
	
	private JPanel getPanelAbsolute()
	{
		if (panelAbsolute == null)
		{
			panelAbsolute = new JPanel();
			panelAbsolute.setBounds(0, 0, 1000, 275);
			panelAbsolute.setLayout(null);
			panelAbsolute.setOpaque(false);
		}
		return panelAbsolute;
	}
}