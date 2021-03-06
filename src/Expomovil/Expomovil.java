/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Expomovil;

import DataStructures.LinkedList;
import DataStructures.Queue;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Window;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.MatteBorder;

/**
 *
 * @author allanvz
 */
public class Expomovil extends javax.swing.JFrame {
    
    //atributos
    DefaultListModel listaAgencias;
    DefaultListModel listaVehiculos;
    DefaultListModel listaEsperaVisualizar;
    DefaultListModel listaEsperaNoDisponibleVisualizar;
    DefaultListModel listaBusqueda;
    DefaultComboBoxModel listaElegirAgencia;
    DefaultComboBoxModel listaAgenciasS;
    DefaultComboBoxModel listaVehiculosS;
    LinkedList<Agencia> listaDeAgencias;
    LinkedList<Vehiculo> listaDeVehiculos;
    Queue<Cliente> listaDeClientes;
    Queue<Cliente> listaDeClientes2;
    String[] colores;
    String[] extras;

    /**
     * Creates new form interfazGrafica
     */
    public Expomovil() {
        initComponents();
        this.listaAgencias = new DefaultListModel();
        this.listaAgenciaEliminar.setModel(this.listaAgencias);
        this.listaAgenciaConsultar.setModel(this.listaAgencias);
        this.listaVehiculos = new DefaultListModel();
        this.listaVehiculoConsultar.setModel(this.listaVehiculos);
        this.listaVehiculoEliminar.setModel(this.listaVehiculos);
        this.listaVehiculoModificar.setModel(this.listaVehiculos);
        this.listaBusqueda = new DefaultListModel();
        this.listaEsperaVisualizar = new DefaultListModel();
        this.listaAtenderEspera.setModel(this.listaEsperaVisualizar);
        this.listaEsperaNoDisponibleVisualizar = new DefaultListModel();
        this.listaEsperaNoDisponible.setModel(this.listaEsperaNoDisponibleVisualizar);
        this.listaAgenciasS = new DefaultComboBoxModel();
        this.agenciaVehiculo.setModel(this.listaAgenciasS);
        this.listaElegirAgencia = new DefaultComboBoxModel();
        this.agenciaBusqueda.setModel(this.listaElegirAgencia);
        this.elegirAgencia.setModel(this.listaElegirAgencia);
        this.listaVehiculosS = new DefaultComboBoxModel();
        this.vehiculoSolicitar.setModel(this.listaVehiculosS);
        this.listaDeAgencias = new LinkedList();
        this.listaDeVehiculos = new LinkedList();
        this.listaDeClientes = new Queue();
        this.listaDeClientes2 = new Queue();
        this.colores = new String[0];
        this.extras = new String[0];
        agregarListaElegirAgencia("All");
    }
    
    public void agregarAgencia(String cedJuridica, String nombre) {
        Agencia agenciaTemp = new Agencia(cedJuridica, nombre);
        this.listaDeAgencias.append(agenciaTemp);
        agregarListaAgencias(agenciaTemp.getNombre());
        agregarListaAgenciasS(agenciaTemp.getNombre());
        agregarListaElegirAgencia(agenciaTemp.getNombre());
    }
    
    public void agregarVehiculo(String marca, String modelo, String tipo, 
            String descripcion, int cilindrada, String combustible, 
            String transmision, String[] colores, String[] extras, 
            String foto, double precio, int cantidad, Agencia agencia) {
        Vehiculo vehiculoTemp = new Vehiculo(marca, modelo, tipo, 
            descripcion, cilindrada, combustible, 
            transmision, colores, extras, 
            foto, precio, cantidad, agencia);
        this.listaDeVehiculos.append(vehiculoTemp);
        agregarListaVehiculos(vehiculoTemp.getMarca()+" "+vehiculoTemp.getModelo());
        if((String)this.elegirAgencia.getSelectedItem() == "All") {
            agregarTodos();
        }else {
            agregarPorAgencia((String)this.elegirAgencia.getSelectedItem());
        }
        agregarListaVehiculosS(vehiculoTemp.getMarca()+" "+vehiculoTemp.getModelo());
    }
    
    public void agregarVehiculo(Vehiculo vehiculoTemp) {
        this.listaDeVehiculos.append(vehiculoTemp);
        agregarListaVehiculos(vehiculoTemp.getMarca()+" "+vehiculoTemp.getModelo());
        if((String)this.elegirAgencia.getSelectedItem() == "All") {
            agregarTodos();
        }else {
            agregarPorAgencia((String)this.elegirAgencia.getSelectedItem());
        }
        agregarListaVehiculosS(vehiculoTemp.getMarca()+" "+vehiculoTemp.getModelo());
    }
    
    public void agregarCliente(String cedula, String nombre, String direccion, String numero, String correo, Vehiculo vehiculo) {
        Cliente clienteTemp = new Cliente(cedula, nombre, direccion, numero, correo, vehiculo);
        this.listaDeClientes.enqueue(clienteTemp);
        agregarListaEsperaVisualizar(nombre);
    }
    
    public void agregarNoDisponible(Cliente cliente) {
        this.listaDeClientes2.enqueue(cliente);
        agregarListaEsperaNoDisponibleVisualizar(cliente.getNombre());
    }
    
    public void eliminarAgencia() {
        eliminarListaAgencias();
        eliminarListaAgenciasS();
    }
    
    public void eliminarVehiculo() {
        eliminarListaVehiculos();
        eliminarListaVehiculosS();
        if((String)this.elegirAgencia.getSelectedItem() == "All") {
            agregarTodos();
        }else {
            agregarPorAgencia((String)this.elegirAgencia.getSelectedItem());
        }
    }
    
    public void eliminarCliente() {
        Cliente atendiendo = this.listaDeClientes.dequeue();
        this.labelCedula.setText(atendiendo.getCedula());
        this.labelCorreo.setText(atendiendo.getCorreo());
        this.labelDireccion.setText(atendiendo.getDireccion());
        this.labelNombre.setText(atendiendo.getNombre());
        this.labelNumero.setText(atendiendo.getNumero());
        this.labelVehiculo.setText(atendiendo.getVehiculo().getMarca()+atendiendo.getVehiculo().getModelo());
        eliminarListaEsperaVisualizar();
    }
    
    public void eliminarNoDisponible() {
        Cliente atendiendo = this.listaDeClientes2.dequeue();
        this.labelCedula.setText(atendiendo.getCedula());
        this.labelCorreo.setText(atendiendo.getCorreo());
        this.labelDireccion.setText(atendiendo.getDireccion());
        this.labelNombre.setText(atendiendo.getNombre());
        this.labelNumero.setText(atendiendo.getNumero());
        this.labelVehiculo.setText(atendiendo.getVehiculo().getMarca()+atendiendo.getVehiculo().getModelo());
        eliminarListaEsperaNoDisponibleVisualizar();
    }
    
    public void agregarListaAgencias(String elemento) {
        this.listaAgencias.addElement(elemento);
    }
    
    public void agregarListaVehiculos(String elemento) {
        this.listaVehiculos.addElement(elemento);
    }
    
    public void agregarListaEsperaVisualizar(String elemento) {
        this.listaEsperaVisualizar.addElement(elemento);
    }
    
    public void agregarListaEsperaNoDisponibleVisualizar(String elemento) {
        this.listaEsperaNoDisponibleVisualizar.addElement(elemento);
    }
    
    public void agregarListaAgenciasS(String elemento) {
        this.listaAgenciasS.addElement(elemento);
    }
    
    public void agregarListaElegirAgencia(String element) {
        this.listaElegirAgencia.addElement(element);
    }
    
    public void agregarListaVehiculosS(String elemento) {
        this.listaVehiculosS.addElement(elemento);
    }
    
    public void eliminarListaAgencias() {
        this.listaAgencias.clear();
        this.listaDeAgencias.goToStart();
        while (this.listaDeAgencias.next()) {
            agregarListaAgencias(this.listaDeAgencias.getElement().getNombre());
        }
    }
    
    public void eliminarListaElegirAgencia() {
        this.listaElegirAgencia.removeAllElements();
        agregarListaElegirAgencia("All");
        this.listaDeAgencias.goToStart();
        while(this.listaDeAgencias.next()) {
            agregarListaElegirAgencia(this.listaDeAgencias.getElement().getNombre());
        }
    }
    
    public void eliminarListaVehiculos() {
        this.listaVehiculos.clear();
        this.listaDeVehiculos.goToStart();
        while(this.listaDeVehiculos.next()) {
            agregarListaVehiculos(this.listaDeVehiculos.getElement().getMarca()+" "+this.listaDeVehiculos.getElement().getModelo());
        }
    }
    
    public void eliminarListaEsperaVisualizar() {
        this.listaEsperaVisualizar.removeElementAt(0);
    }
    
    public void eliminarListaEsperaNoDisponibleVisualizar() {
        this.listaEsperaNoDisponibleVisualizar.removeElementAt(0);
    }
    
    public void eliminarListaAgenciasS() {
        this.listaAgenciasS.removeAllElements();
        agregarListaAgenciasS("All");
        this.listaDeAgencias.goToStart();
        while (this.listaDeAgencias.next()) {
            agregarListaAgenciasS(this.listaDeAgencias.getElement().getNombre());
        }
    }
    
    public void eliminarListaVehiculosS() {
        this.listaVehiculosS.removeAllElements();
        this.listaDeVehiculos.goToStart();
        while(this.listaDeVehiculos.next()) {
            agregarListaVehiculosS(this.listaDeVehiculos.getElement().getMarca()+" "+this.listaDeVehiculos.getElement().getModelo());
        }
    }
    
    public String[] agregarArreglo(String[] array, String add) {
        String[] arrayTemp = new String[array.length+1];
        for(int x = 0; x < array.length; x++) {
            arrayTemp[x] = array[x];
        }
        arrayTemp[arrayTemp.length-1] = add;
        return arrayTemp;
    }
    
    public String[] eliminarArreglo(String[] array, String remove) {
        String[] arrayTemp = new String[array.length-1];
        int newElement = 0;
        for(int x = 0; x < arrayTemp.length; x++) {
            if(array[x] != remove) {
                arrayTemp[newElement] = array[x];
                newElement ++;
            }
        }
        return arrayTemp;
    }
    
    public String agregarFoto(Window parent){
        // TODO Auto-generated catch block
        String path = "";
        JFileChooser chooser = new JFileChooser();
        int returnVal = chooser.showOpenDialog(parent);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            path = chooser.getSelectedFile().getPath();
        }
        return path;
    }
    
    public void infoCliente(Cliente info) {
        JOptionPane.showMessageDialog(null, "Nombre: "+ info.getNombre()
                +"\nCedula: "+ info.getCedula()
                +"\nCorreo: "+ info.getCorreo()
                +"\nNumero: "+ info.getNumero()
                +"\nDireccion: "+ info.getDireccion()
                +"\nVehiculo: "+ info.getVehiculo().getMarca()+" "+ info.getVehiculo().getModelo(), "Info Cliente", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void enviarCorreo(String to, String message, String subject){
        SendEmail correo = new SendEmail(to, message, subject);
        correo.send();
    }
    
    public void busquedaAvanzada(String agencia, String marca, String modelo, String precio){
        String agenciaTemp = agencia;
        String marcaTemp = marca;
        String modeloTemp = modelo;
        this.listaDeVehiculos.goToStart();
        LinkedList<Vehiculo> listaBusquedaTemp = new LinkedList<>();
        Vehiculo vehiculoActual;
        while (this.listaDeVehiculos.next()){
            vehiculoActual = this.listaDeVehiculos.getElement();
            if (vehiculoActual.getAgencia().getNombre().equals(agenciaTemp) || agenciaTemp.equals("All")){
                if(vehiculoActual.getMarca().equals(marcaTemp) || marcaTemp.equals("")){
                    if (vehiculoActual.getModelo().equals(modeloTemp) || modeloTemp.equals("")){
                        if(precio.equals("") || vehiculoActual.getPrecio()==Double.parseDouble(precio)){
                            listaBusquedaTemp.append(vehiculoActual);
                        }
                    }
                }
            }
        }
        BusquedaAvanzada busqueda = new BusquedaAvanzada(listaBusquedaTemp);
        busqueda.setVisible(true);
    }
        
    public void agregarPanelVehiculo(Vehiculo vehiculo) {
        DefaultListModel listaColoresS;
        DefaultListModel listaExtrasS;
        javax.swing.JPanel VentanaVehiculo;
        javax.swing.JPanel jPanel2;
        javax.swing.JScrollPane jScrollPane1;
        javax.swing.JScrollPane jScrollPane2;
        javax.swing.JLabel labelAgencia;
        javax.swing.JLabel labelCantidad;
        javax.swing.JLabel labelCilindrada;
        javax.swing.JLabel labelCombustible;
        javax.swing.JLabel labelDescripcion;
        javax.swing.JLabel labelFoto;
        javax.swing.JLabel labelMarca;
        javax.swing.JLabel labelModelo;
        javax.swing.JLabel labelPrecio;
        javax.swing.JLabel labelTipo;
        javax.swing.JLabel labelTransmision;
        javax.swing.JList<String> listaColor;
        javax.swing.JList<String> listaExtras;
        javax.swing.JPanel panelFoto;
        
        VentanaVehiculo = new javax.swing.JPanel();
        panelFoto = new javax.swing.JPanel();
        labelFoto = new javax.swing.JLabel();
        labelMarca = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listaColor = new javax.swing.JList<>();
        labelModelo = new javax.swing.JLabel();
        labelTipo = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        labelDescripcion = new javax.swing.JLabel();
        labelCilindrada = new javax.swing.JLabel();
        labelCombustible = new javax.swing.JLabel();
        labelTransmision = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        listaExtras = new javax.swing.JList<>();
        labelPrecio = new javax.swing.JLabel();
        labelAgencia = new javax.swing.JLabel();
        labelCantidad = new javax.swing.JLabel();
        
        listaColoresS = new DefaultListModel();
        listaColor.setModel(listaColoresS);
        listaExtrasS = new DefaultListModel();
        listaExtras.setModel(listaExtrasS);
        labelAgencia.setText(vehiculo.getAgencia().getNombre());
        labelCantidad.setText(Integer.toString(vehiculo.getCantidad()));
        labelCilindrada.setText(Integer.toString(vehiculo.getCilindrada()));
        labelCombustible.setText(vehiculo.getCombustible());
        labelDescripcion.setText(vehiculo.getDescripcion());
        labelMarca.setText(vehiculo.getMarca());
        labelModelo.setText(vehiculo.getModelo());
        labelPrecio.setText(Double.toString(vehiculo.getPrecio()));
        labelTipo.setText(vehiculo.getTipo());
        labelTransmision.setText(vehiculo.getTransmision());
        
        VentanaVehiculo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        
        ImageIcon image = null;
        image = new ImageIcon(vehiculo.getFoto());
        
        labelFoto.setIcon(image);
        
        listaExtrasS.clear();
        for(int x = 0; x != vehiculo.getExtras().length; x++) {
            listaExtrasS.addElement(vehiculo.getExtras()[x]);
        }
        
        listaColoresS.clear();
        for(int x = 0; x != vehiculo.getColores().length; x++) {
            listaColoresS.addElement(vehiculo.getColores()[x]);
        }
        
        javax.swing.GroupLayout panelFotoLayout = new javax.swing.GroupLayout(panelFoto);
        panelFoto.setLayout(panelFotoLayout);
        panelFotoLayout.setHorizontalGroup(
            panelFotoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelFoto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
        );
        panelFotoLayout.setVerticalGroup(
            panelFotoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelFoto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        
        jScrollPane1.setViewportView(listaColor);
        
        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(labelDescripcion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelDescripcion, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
        );
        
        jScrollPane2.setViewportView(listaExtras);
        
        javax.swing.GroupLayout VentanaVehiculoLayout = new javax.swing.GroupLayout(VentanaVehiculo);
        VentanaVehiculo.setLayout(VentanaVehiculoLayout);
        VentanaVehiculoLayout.setHorizontalGroup(
            VentanaVehiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(VentanaVehiculoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelFoto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(VentanaVehiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(VentanaVehiculoLayout.createSequentialGroup()
                        .addGroup(VentanaVehiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(VentanaVehiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING))
                            .addGroup(VentanaVehiculoLayout.createSequentialGroup()
                                .addComponent(labelMarca)
                                .addGap(18, 18, 18)
                                .addComponent(labelModelo)
                                .addGap(18, 18, 18)
                                .addComponent(labelTipo)))
                        .addGap(18, 18, 18)
                        .addGroup(VentanaVehiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelPrecio)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(VentanaVehiculoLayout.createSequentialGroup()
                        .addGroup(VentanaVehiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(VentanaVehiculoLayout.createSequentialGroup()
                                .addComponent(labelAgencia)
                                .addGap(81, 81, 81)
                                .addComponent(labelCantidad))
                            .addGroup(VentanaVehiculoLayout.createSequentialGroup()
                                .addComponent(labelCilindrada)
                                .addGap(18, 18, 18)
                                .addComponent(labelCombustible)
                                .addGap(18, 18, 18)
                                .addComponent(labelTransmision)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(98, 98, 98)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        VentanaVehiculoLayout.setVerticalGroup(
            VentanaVehiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(VentanaVehiculoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelFoto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(VentanaVehiculoLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(VentanaVehiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelMarca)
                    .addComponent(labelModelo)
                    .addComponent(labelTipo)
                    .addComponent(labelPrecio))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(VentanaVehiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(VentanaVehiculoLayout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(VentanaVehiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelCilindrada)
                    .addComponent(labelCombustible)
                    .addComponent(labelTransmision))
                .addGap(7, 7, 7)
                .addGroup(VentanaVehiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(VentanaVehiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(labelAgencia)
                        .addComponent(labelCantidad)))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        
        this.jPanel11.add(VentanaVehiculo);
        this.jPanel11.updateUI();
    }
    
    public void agregarPorAgencia(String agencia) {
        this.listaDeVehiculos.goToStart();
        this.jPanel11.removeAll();
        while(this.listaDeVehiculos.next()) {
            if(this.listaDeVehiculos.getElement().getAgencia().getNombre() == agencia) {
                agregarPanelVehiculo(this.listaDeVehiculos.getElement());
            }
        }
    }
    
    public void agregarTodos() {
        this.listaDeAgencias.goToStart();
        this.jPanel11.removeAll();
        while(this.listaDeAgencias.next()) {
            this.listaDeVehiculos.goToStart();
            String agencia = this.listaDeAgencias.getElement().getNombre();
            while(this.listaDeVehiculos.next()) {
                if(this.listaDeVehiculos.getElement().getAgencia().getNombre() == agencia) {
                    agregarPanelVehiculo(this.listaDeVehiculos.getElement());
                }
            }
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        contenedorPrincipal = new javax.swing.JTabbedPane();
        home = new javax.swing.JPanel();
        intro1 = new javax.swing.JLabel();
        intro2 = new javax.swing.JLabel();
        intro3 = new javax.swing.JLabel();
        agencia = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        nombreAgencia = new javax.swing.JTextField();
        cedulaAgencia = new javax.swing.JTextField();
        botonCrearAgencia = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listaAgenciaEliminar = new javax.swing.JList<>();
        botonEliminarAgencia = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        listaAgenciaConsultar = new javax.swing.JList<>();
        botonConsultarAgencia = new javax.swing.JButton();
        vehiculo = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        modeloVehiculo = new javax.swing.JTextField();
        tipoVehiculo = new javax.swing.JTextField();
        marcaVehiculo = new javax.swing.JTextField();
        descripcionVehiculo = new javax.swing.JTextField();
        cilindradaVehiculo = new javax.swing.JTextField();
        combustibleVehiculo = new javax.swing.JTextField();
        transmisionVehiculo = new javax.swing.JTextField();
        colorVehiculo = new javax.swing.JTextField();
        extrasVehiculo = new javax.swing.JTextField();
        fotoVehiculo = new javax.swing.JTextField();
        agregarExtra = new javax.swing.JButton();
        agregarFoto = new javax.swing.JButton();
        precioVehiculo = new javax.swing.JTextField();
        cantidadVehiculo = new javax.swing.JTextField();
        agenciaVehiculo = new javax.swing.JComboBox<>();
        crearVehiculo = new javax.swing.JButton();
        agregarColor = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        listaVehiculoEliminar = new javax.swing.JList<>();
        eliminarVehiculo = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        listaVehiculoModificar = new javax.swing.JList<>();
        modificarVehiculo = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        listaVehiculoConsultar = new javax.swing.JList<>();
        consultarVehiculo = new javax.swing.JButton();
        visualizar = new javax.swing.JPanel();
        elegirAgencia = new javax.swing.JComboBox<>();
        jScrollPane9 = new javax.swing.JScrollPane();
        jPanel11 = new javax.swing.JPanel();
        listaEspera = new javax.swing.JTabbedPane();
        solicitarVehiculo = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        cedulaSolicitar = new javax.swing.JTextField();
        nombreSolicitar = new javax.swing.JTextField();
        direccionSolicitar = new javax.swing.JTextField();
        telefonoSolicitar = new javax.swing.JTextField();
        correoSolicitar = new javax.swing.JTextField();
        vehiculoSolicitar = new javax.swing.JComboBox<>();
        jLabel21 = new javax.swing.JLabel();
        solicitar = new javax.swing.JButton();
        visualizarLista = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        labelNombre = new javax.swing.JLabel();
        labelCedula = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        labelNumero = new javax.swing.JLabel();
        labelCorreo = new javax.swing.JLabel();
        labelVehiculo = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        labelDireccion = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        listaAtenderEspera = new javax.swing.JList<>();
        atenderEspera = new javax.swing.JButton();
        infoEspera = new javax.swing.JButton();
        jScrollPane8 = new javax.swing.JScrollPane();
        listaEsperaNoDisponible = new javax.swing.JList<>();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        atenderEsperaND = new javax.swing.JButton();
        infoEsperaND = new javax.swing.JButton();
        busquedaAvanzada = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        agenciaBusqueda = new javax.swing.JComboBox<>();
        marcaBusqueda = new javax.swing.JTextField();
        modeloBusqueda = new javax.swing.JTextField();
        precioBusqueda = new javax.swing.JTextField();
        realizarBusqueda = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        home.setBackground(new java.awt.Color(255, 255, 255));

        intro1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        intro1.setText("Bienvenido");

        intro2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        intro2.setText("a la");

        intro3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        intro3.setText("Expomovil");

        javax.swing.GroupLayout homeLayout = new javax.swing.GroupLayout(home);
        home.setLayout(homeLayout);
        homeLayout.setHorizontalGroup(
            homeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(homeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(homeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(intro1, javax.swing.GroupLayout.DEFAULT_SIZE, 652, Short.MAX_VALUE)
                    .addComponent(intro2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(intro3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 652, Short.MAX_VALUE))
                .addContainerGap())
        );
        homeLayout.setVerticalGroup(
            homeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(homeLayout.createSequentialGroup()
                .addGap(166, 166, 166)
                .addComponent(intro1)
                .addGap(18, 18, 18)
                .addComponent(intro2)
                .addGap(18, 18, 18)
                .addComponent(intro3)
                .addContainerGap(194, Short.MAX_VALUE))
        );

        contenedorPrincipal.addTab("Home", home);

        jLabel1.setText("Nombre:");

        jLabel2.setText("Cedula Juridica:");

        botonCrearAgencia.setText("Crear");
        botonCrearAgencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCrearAgenciaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(203, 203, 203)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(botonCrearAgencia)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(nombreAgencia)
                        .addComponent(cedulaAgencia, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)))
                .addContainerGap(183, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(nombreAgencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cedulaAgencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(botonCrearAgencia)
                .addContainerGap(209, Short.MAX_VALUE))
        );

        agencia.addTab("Crear", jPanel1);

        jScrollPane1.setViewportView(listaAgenciaEliminar);

        botonEliminarAgencia.setText("Eliminar");
        botonEliminarAgencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEliminarAgenciaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(154, 154, 154)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(254, 254, 254)
                        .addComponent(botonEliminarAgencia)))
                .addContainerGap(189, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(botonEliminarAgencia)
                .addContainerGap(165, Short.MAX_VALUE))
        );

        agencia.addTab("Eliminar", jPanel2);

        jScrollPane3.setViewportView(listaAgenciaConsultar);

        botonConsultarAgencia.setText("Consultar");
        botonConsultarAgencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonConsultarAgenciaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(154, 154, 154)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(253, 253, 253)
                        .addComponent(botonConsultarAgencia)))
                .addContainerGap(189, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(botonConsultarAgencia)
                .addContainerGap(165, Short.MAX_VALUE))
        );

        agencia.addTab("Consultar", jPanel3);

        contenedorPrincipal.addTab("Agencia", agencia);

        jLabel3.setText("Marca:");

        jLabel4.setText("Modelo:");

        jLabel5.setText("Tipo:");

        jLabel6.setText("Descripcion:");

        jLabel7.setText("Cilindrada:");

        jLabel8.setText("Combustible:");

        jLabel9.setText("Transmision:");

        jLabel10.setText("Color:");

        jLabel11.setText("Extras:");

        jLabel12.setText("Foto:");

        jLabel13.setText("Precio:");

        jLabel14.setText("Cantidad:");

        jLabel15.setText("Agencia:");

        marcaVehiculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                marcaVehiculoActionPerformed(evt);
            }
        });

        agregarExtra.setText("+");
        agregarExtra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarExtraActionPerformed(evt);
            }
        });

        agregarFoto.setText("⚲");
        agregarFoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarFotoActionPerformed(evt);
            }
        });

        crearVehiculo.setText("Crear");
        crearVehiculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crearVehiculoActionPerformed(evt);
            }
        });

        agregarColor.setText("+");
        agregarColor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarColorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(modeloVehiculo)
                            .addComponent(tipoVehiculo)
                            .addComponent(marcaVehiculo)
                            .addComponent(descripcionVehiculo)
                            .addComponent(cilindradaVehiculo)
                            .addComponent(combustibleVehiculo)
                            .addComponent(transmisionVehiculo, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(extrasVehiculo))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(colorVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(precioVehiculo))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cantidadVehiculo))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(agenciaVehiculo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(fotoVehiculo)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(agregarExtra, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(agregarFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                            .addComponent(agregarColor, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(265, 265, 265)
                        .addComponent(crearVehiculo)))
                .addContainerGap(44, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel10)
                    .addComponent(marcaVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(colorVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(agregarColor))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel11)
                    .addComponent(modeloVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(extrasVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(agregarExtra))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel12)
                    .addComponent(tipoVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fotoVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(agregarFoto))
                .addGap(22, 22, 22)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel13)
                    .addComponent(descripcionVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(precioVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel14)
                    .addComponent(cilindradaVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cantidadVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel15)
                    .addComponent(combustibleVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(agenciaVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(transmisionVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(crearVehiculo)
                .addContainerGap(40, Short.MAX_VALUE))
        );

        vehiculo.addTab("Crear", jPanel4);

        jScrollPane4.setViewportView(listaVehiculoEliminar);

        eliminarVehiculo.setText("Eliminar");
        eliminarVehiculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarVehiculoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(156, 156, 156)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(255, 255, 255)
                        .addComponent(eliminarVehiculo)))
                .addContainerGap(193, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(eliminarVehiculo)
                .addContainerGap(145, Short.MAX_VALUE))
        );

        vehiculo.addTab("Eliminar", jPanel5);

        jScrollPane5.setViewportView(listaVehiculoModificar);

        modificarVehiculo.setText("Modificar");
        modificarVehiculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modificarVehiculoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(254, 254, 254)
                        .addComponent(modificarVehiculo))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(157, 157, 157)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(193, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(modificarVehiculo)
                .addContainerGap(145, Short.MAX_VALUE))
        );

        vehiculo.addTab("Modificar", jPanel6);

        jScrollPane6.setViewportView(listaVehiculoConsultar);

        consultarVehiculo.setText("Consultar");
        consultarVehiculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                consultarVehiculoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(156, 156, 156)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(254, 254, 254)
                        .addComponent(consultarVehiculo)))
                .addContainerGap(193, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(consultarVehiculo)
                .addContainerGap(145, Short.MAX_VALUE))
        );

        vehiculo.addTab("Consultar", jPanel7);

        contenedorPrincipal.addTab("Vehiculo", vehiculo);

        elegirAgencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                elegirAgenciaActionPerformed(evt);
            }
        });

        jPanel11.setLayout(new javax.swing.BoxLayout(jPanel11, javax.swing.BoxLayout.Y_AXIS));
        jScrollPane9.setViewportView(jPanel11);

        javax.swing.GroupLayout visualizarLayout = new javax.swing.GroupLayout(visualizar);
        visualizar.setLayout(visualizarLayout);
        visualizarLayout.setHorizontalGroup(
            visualizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(visualizarLayout.createSequentialGroup()
                .addGap(203, 203, 203)
                .addComponent(elegirAgencia, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 664, Short.MAX_VALUE)
        );
        visualizarLayout.setVerticalGroup(
            visualizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(visualizarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(elegirAgencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 405, Short.MAX_VALUE))
        );

        contenedorPrincipal.addTab("Visualizar", visualizar);

        jLabel16.setText("Cedula:");

        jLabel17.setText("Nombre:");

        jLabel18.setText("Direccion:");

        jLabel19.setText("Telefono:");

        jLabel20.setText("Correo:");

        jLabel21.setText("Vehiculo:");

        solicitar.setText("Solicitar");
        solicitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                solicitarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout solicitarVehiculoLayout = new javax.swing.GroupLayout(solicitarVehiculo);
        solicitarVehiculo.setLayout(solicitarVehiculoLayout);
        solicitarVehiculoLayout.setHorizontalGroup(
            solicitarVehiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(solicitarVehiculoLayout.createSequentialGroup()
                .addGap(130, 130, 130)
                .addGroup(solicitarVehiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(solicitarVehiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(solicitarVehiculoLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(solicitarVehiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cedulaSolicitar)
                            .addComponent(nombreSolicitar)
                            .addComponent(direccionSolicitar)
                            .addComponent(telefonoSolicitar, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)))
                    .addGroup(solicitarVehiculoLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(solicitarVehiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(correoSolicitar)
                            .addComponent(vehiculoSolicitar, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(solicitarVehiculoLayout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addComponent(solicitar)
                                .addGap(0, 130, Short.MAX_VALUE)))))
                .addGap(197, 197, 197))
        );
        solicitarVehiculoLayout.setVerticalGroup(
            solicitarVehiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(solicitarVehiculoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(solicitarVehiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(cedulaSolicitar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(solicitarVehiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(nombreSolicitar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(solicitarVehiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(direccionSolicitar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(solicitarVehiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(telefonoSolicitar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(solicitarVehiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(correoSolicitar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(solicitarVehiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(vehiculoSolicitar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21))
                .addGap(18, 18, 18)
                .addComponent(solicitar)
                .addContainerGap(98, Short.MAX_VALUE))
        );

        listaEspera.addTab("Solicitar Vehiculo", solicitarVehiculo);

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        jLabel22.setText("Nombre:");

        jLabel23.setText("Cedula:");

        jLabel26.setText("Telefono:");

        jLabel27.setText("Correo:");

        jLabel29.setText("Vehiculo:");

        jLabel28.setText("Direccion:");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelDireccion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelDireccion, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel22)
                        .addGap(18, 18, 18)
                        .addComponent(labelNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel27)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelCorreo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel26)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelNumero, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel23)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelCedula, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel28)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel29)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelVehiculo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelNombre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelCedula, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelNumero, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelCorreo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(labelVehiculo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jLabel28)
                .addGap(28, 28, 28)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );

        jScrollPane2.setViewportView(listaAtenderEspera);

        atenderEspera.setText("Atender");
        atenderEspera.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                atenderEsperaActionPerformed(evt);
            }
        });

        infoEspera.setText("Info.");
        infoEspera.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                infoEsperaActionPerformed(evt);
            }
        });

        jScrollPane8.setViewportView(listaEsperaNoDisponible);

        jLabel24.setText("Lista Espera Inicial");

        jLabel25.setText("Lista Espera N.D");

        atenderEsperaND.setText("Atender");
        atenderEsperaND.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                atenderEsperaNDActionPerformed(evt);
            }
        });

        infoEsperaND.setText("Info.");
        infoEsperaND.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                infoEsperaNDActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout visualizarListaLayout = new javax.swing.GroupLayout(visualizarLista);
        visualizarLista.setLayout(visualizarListaLayout);
        visualizarListaLayout.setHorizontalGroup(
            visualizarListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(visualizarListaLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(visualizarListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24)
                    .addGroup(visualizarListaLayout.createSequentialGroup()
                        .addComponent(atenderEspera, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(infoEspera, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(visualizarListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25)
                    .addGroup(visualizarListaLayout.createSequentialGroup()
                        .addComponent(atenderEsperaND, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(infoEsperaND, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        visualizarListaLayout.setVerticalGroup(
            visualizarListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(visualizarListaLayout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(visualizarListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(jLabel25))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(visualizarListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(visualizarListaLayout.createSequentialGroup()
                        .addGroup(visualizarListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
                            .addComponent(jScrollPane8))
                        .addGap(18, 18, 18)
                        .addGroup(visualizarListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(atenderEspera)
                            .addComponent(infoEspera)
                            .addComponent(atenderEsperaND)
                            .addComponent(infoEsperaND)))
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(32, Short.MAX_VALUE))
        );

        listaEspera.addTab("Atencion Lista de Espera", visualizarLista);

        contenedorPrincipal.addTab("Lista de Espera", listaEspera);

        jLabel34.setText("Agencia:");

        jLabel35.setText("Marca:");

        jLabel36.setText("Modelo:");

        jLabel37.setText("Precio:");

        realizarBusqueda.setText("Realizar");
        realizarBusqueda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                realizarBusquedaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout busquedaAvanzadaLayout = new javax.swing.GroupLayout(busquedaAvanzada);
        busquedaAvanzada.setLayout(busquedaAvanzadaLayout);
        busquedaAvanzadaLayout.setHorizontalGroup(
            busquedaAvanzadaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(busquedaAvanzadaLayout.createSequentialGroup()
                .addGroup(busquedaAvanzadaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(busquedaAvanzadaLayout.createSequentialGroup()
                        .addGap(101, 101, 101)
                        .addGroup(busquedaAvanzadaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel36, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(busquedaAvanzadaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(agenciaBusqueda, 0, 200, Short.MAX_VALUE)
                            .addComponent(marcaBusqueda)
                            .addComponent(modeloBusqueda)
                            .addComponent(precioBusqueda)))
                    .addGroup(busquedaAvanzadaLayout.createSequentialGroup()
                        .addGap(214, 214, 214)
                        .addComponent(realizarBusqueda)))
                .addContainerGap(297, Short.MAX_VALUE))
        );
        busquedaAvanzadaLayout.setVerticalGroup(
            busquedaAvanzadaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(busquedaAvanzadaLayout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(busquedaAvanzadaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel34)
                    .addComponent(agenciaBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(busquedaAvanzadaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel35)
                    .addComponent(marcaBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(busquedaAvanzadaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel36)
                    .addComponent(modeloBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(busquedaAvanzadaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel37)
                    .addComponent(precioBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(realizarBusqueda)
                .addContainerGap(192, Short.MAX_VALUE))
        );

        contenedorPrincipal.addTab("Busqueda Avanzada", busquedaAvanzada);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(contenedorPrincipal)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(contenedorPrincipal)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void marcaVehiculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_marcaVehiculoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_marcaVehiculoActionPerformed

    private void botonCrearAgenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCrearAgenciaActionPerformed
        // TODO add your handling code here:
        String nomTemp = this.nombreAgencia.getText();
        String cedTemp = this.cedulaAgencia.getText();
        if(nomTemp.equals("")) {
            JOptionPane.showMessageDialog(null, "Ingrese el nombre de la agencia", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }else if(cedTemp.equals("")) {
            JOptionPane.showMessageDialog(null, "Ingrese la cedula de la agencia", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        agregarAgencia(cedTemp, nomTemp);
        this.nombreAgencia.setText(null);
        this.cedulaAgencia.setText(null);
    }//GEN-LAST:event_botonCrearAgenciaActionPerformed

    private void botonEliminarAgenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEliminarAgenciaActionPerformed
        // TODO add your handling code here:
        if(this.listaAgenciaEliminar.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione la agencia que desea eliminar", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        this.listaDeAgencias.goToPos(this.listaAgenciaEliminar.getSelectedIndex());
        this.listaDeVehiculos.goToStart();
        while(this.listaDeVehiculos.next()) {
            if(this.listaDeVehiculos.getElement().getAgencia().getNombre() == this.listaDeAgencias.getElement().getNombre()) {
                this.listaDeVehiculos.remove();
                this.listaDeVehiculos.goToStart();
            }
        }
        this.listaDeAgencias.remove();
        eliminarAgencia();
        eliminarVehiculo();
    }//GEN-LAST:event_botonEliminarAgenciaActionPerformed

    private void botonConsultarAgenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonConsultarAgenciaActionPerformed
        // TODO add your handling code here:
        if(this.listaAgenciaConsultar.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione la agencia que desea consultar", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        this.listaDeAgencias.goToPos(this.listaAgenciaConsultar.getSelectedIndex());
        JOptionPane.showMessageDialog(null, "Nombre: "+this.listaDeAgencias.getElement().getNombre()+"\nCedula Juridica: "+this.listaDeAgencias.getElement().getCedJuridica(), "Informacion de Agencia", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_botonConsultarAgenciaActionPerformed

    private void agregarColorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarColorActionPerformed
        // TODO add your handling code here:
        this.colores = agregarArreglo(this.colores, this.colorVehiculo.getText());
        this.colorVehiculo.setText(null);
    }//GEN-LAST:event_agregarColorActionPerformed

    private void agregarExtraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarExtraActionPerformed
        // TODO add your handling code here:
        this.extras = agregarArreglo(this.extras, this.extrasVehiculo.getText());
        this.extrasVehiculo.setText(null);
    }//GEN-LAST:event_agregarExtraActionPerformed

    private void crearVehiculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_crearVehiculoActionPerformed
        // TODO add your handling code here:
        String marca = this.marcaVehiculo.getText();
        String modelo = this.modeloVehiculo.getText();
        String tipo = this.tipoVehiculo.getText();
        String descripcion = this.descripcionVehiculo.getText();
        String combustible = this.combustibleVehiculo.getText();
        String transmision = this.transmisionVehiculo.getText();
        String foto = this.fotoVehiculo.getText();
        if(marca.equals("")) {
            JOptionPane.showMessageDialog(null, "Ingrese la marca", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }else if(modelo.equals("")) {
            JOptionPane.showMessageDialog(null, "Ingrese el modelo", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }else if(tipo.equals("")) {
            JOptionPane.showMessageDialog(null, "Ingrese el tipo", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }else if(descripcion.equals("")) {
            JOptionPane.showMessageDialog(null, "Ingrese la descripcion", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }else if(combustible.equals("")) {
            JOptionPane.showMessageDialog(null, "Ingrese el tipo de combustible", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }else if(transmision.equals("")) {
            JOptionPane.showMessageDialog(null, "Ingrese la transmision", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }else if(foto.equals("")) {
            JOptionPane.showMessageDialog(null, "Seleccione la foto", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }else if(this.cilindradaVehiculo.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Ingrese la cilindrada", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }else if(this.precioVehiculo.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Ingrese el precio", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }else if(this.cantidadVehiculo.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Ingrese la cantidad", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int cilindrada = Integer.valueOf(this.cilindradaVehiculo.getText());
        double precio = Double.valueOf(this.precioVehiculo.getText());
        int cantidad = Integer.valueOf(this.cantidadVehiculo.getText());
        this.listaDeAgencias.goToPos(this.agenciaVehiculo.getSelectedIndex());
        Agencia agenciaPertenencia = this.listaDeAgencias.getElement();
        agregarVehiculo(marca, modelo, tipo, descripcion, cilindrada, transmision, combustible, this.colores, this.extras, foto, precio, cantidad, agenciaPertenencia);
        this.marcaVehiculo.setText(null);
        this.modeloVehiculo.setText(null);
        this.tipoVehiculo.setText(null);
        this.descripcionVehiculo.setText(null);
        this.cilindradaVehiculo.setText(null);
        this.combustibleVehiculo.setText(null);
        this.transmisionVehiculo.setText(null);
        this.fotoVehiculo.setText(null);
        this.precioVehiculo.setText(null);
        this.cantidadVehiculo.setText(null);
        this.colores = new String[0];
        this.extras = new String[0];
    }//GEN-LAST:event_crearVehiculoActionPerformed

    private void eliminarVehiculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarVehiculoActionPerformed
        // TODO add your handling code here:
        if(this.listaVehiculoEliminar.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione lel vehiculo que desea eliminar", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        this.listaDeVehiculos.goToPos(this.listaVehiculoEliminar.getSelectedIndex());
        this.listaDeVehiculos.remove();
        eliminarVehiculo();
    }//GEN-LAST:event_eliminarVehiculoActionPerformed

    private void modificarVehiculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modificarVehiculoActionPerformed
        // TODO add your handling code here:
        if(this.listaVehiculoModificar.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione el vehiculo que desea modificar", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        this.listaDeVehiculos.goToPos(this.listaVehiculoModificar.getSelectedIndex());
        ModificarVehiculo ventanaTemp = new ModificarVehiculo(this.listaDeAgencias, this.listaDeVehiculos.getElement(), this);
        ventanaTemp.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_modificarVehiculoActionPerformed

    private void consultarVehiculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_consultarVehiculoActionPerformed
        // TODO add your handling code here:
        if(this.listaVehiculoConsultar.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione el vehiculo que desea consultar", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        this.listaDeVehiculos.goToPos(this.listaVehiculoConsultar.getSelectedIndex());
        ConsultarVehiculo ventanaTemp = new ConsultarVehiculo(this.listaDeVehiculos.getElement());
        ventanaTemp.setVisible(true);
    }//GEN-LAST:event_consultarVehiculoActionPerformed

    private void solicitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_solicitarActionPerformed
        // TODO add your handling code here:
        String cedula = this.cedulaSolicitar.getText();
        String nombre = this.nombreSolicitar.getText();
        String direccion = this.direccionSolicitar.getText();
        String numero = this.telefonoSolicitar.getText();
        String correo = this.correoSolicitar.getText();
        if(cedula.equals("")) {
            JOptionPane.showMessageDialog(null, "Ingrese la cedula", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }else if(nombre.equals("")) {
            JOptionPane.showMessageDialog(null, "Ingrese su nombre", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }else if(direccion.equals("")) {
            JOptionPane.showMessageDialog(null, "Ingrese la direccion", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }else if(numero.equals("")) {
            JOptionPane.showMessageDialog(null, "Ingrese su numero", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }else if(correo.equals("")) {
            JOptionPane.showMessageDialog(null, "Ingrese el correo", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        this.listaDeVehiculos.goToPos(this.vehiculoSolicitar.getSelectedIndex());
        Vehiculo vehiculoTemp = this.listaDeVehiculos.getElement();
        agregarCliente(cedula, nombre, direccion, numero, correo, vehiculoTemp);
        this.cedulaSolicitar.setText(null);
        this.nombreSolicitar.setText(null);
        this.direccionSolicitar.setText(null);
        this.telefonoSolicitar.setText(null);
        this.correoSolicitar.setText(null);
    }//GEN-LAST:event_solicitarActionPerformed

    private void infoEsperaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_infoEsperaActionPerformed
        // TODO add your handling code here:
        if(this.listaAtenderEspera.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione lel cliente que desea consultar", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        infoCliente(this.listaDeClientes.getPos(this.listaAtenderEspera.getSelectedIndex()));
    }//GEN-LAST:event_infoEsperaActionPerformed

    private void agregarFotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarFotoActionPerformed
        // TODO add your handling code here:
        this.fotoVehiculo.setText(agregarFoto(this.getOwner()));
    }//GEN-LAST:event_agregarFotoActionPerformed

    private void atenderEsperaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_atenderEsperaActionPerformed
        // TODO add your handling code here:
        Cliente atendiendo = this.listaDeClientes.dequeue();
        if(atendiendo.getVehiculo().getCantidad() != 0) {
            this.labelCedula.setText(atendiendo.getCedula());
            this.labelCorreo.setText(atendiendo.getCorreo());
            this.labelDireccion.setText(atendiendo.getDireccion());
            this.labelNombre.setText(atendiendo.getNombre());
            this.labelNumero.setText(atendiendo.getNumero());
            this.labelVehiculo.setText(atendiendo.getVehiculo().getMarca()+" "+atendiendo.getVehiculo().getModelo());
            atendiendo.getVehiculo().setCantidad(atendiendo.getVehiculo().getCantidad() - 1);
            enviarCorreo(atendiendo.getCorreo(), "Su vehiculo está disponible y será entregado mañana.", "Vehiculo Disponible");
        }else {
            this.listaDeClientes2.enqueue(atendiendo);
            agregarListaEsperaNoDisponibleVisualizar(atendiendo.getNombre());
            enviarCorreo(atendiendo.getCorreo(), "Su vehiculo no está disponible y usted será enviado a otra lista de espera.", "Vehiculo NO Disponible");
        }
        eliminarListaEsperaVisualizar();
    }//GEN-LAST:event_atenderEsperaActionPerformed

    private void atenderEsperaNDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_atenderEsperaNDActionPerformed
        // TODO add your handling code here:
        Cliente atendiendo = this.listaDeClientes2.dequeue();
        if(atendiendo.getVehiculo().getCantidad() != 0) {
            this.labelCedula.setText(atendiendo.getCedula());
            this.labelCorreo.setText(atendiendo.getCorreo());
            this.labelDireccion.setText(atendiendo.getDireccion());
            this.labelNombre.setText(atendiendo.getNombre());
            this.labelNumero.setText(atendiendo.getNumero());
            this.labelVehiculo.setText(atendiendo.getVehiculo().getMarca()+" "+atendiendo.getVehiculo().getModelo());
            atendiendo.getVehiculo().setCantidad(atendiendo.getVehiculo().getCantidad() - 1);
            enviarCorreo(atendiendo.getCorreo(), "Su vehiculo está disponible y será entregado mañana.", "Vehiculo Disponible");
        }else {
            this.listaDeClientes2.enqueue(atendiendo);
            agregarListaEsperaNoDisponibleVisualizar(atendiendo.getNombre());
            enviarCorreo(atendiendo.getCorreo(), "Su vehiculo no está disponible y usted será enviado a otra lista de espera.", "Vehiculo NO Disponible");
        }
        eliminarListaEsperaNoDisponibleVisualizar();
    }//GEN-LAST:event_atenderEsperaNDActionPerformed

    private void infoEsperaNDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_infoEsperaNDActionPerformed
        // TODO add your handling code here:
        if(this.listaEsperaNoDisponible.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione lel cliente que desea consultar", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        infoCliente(this.listaDeClientes2.getPos(this.listaEsperaNoDisponible.getSelectedIndex()));
    }//GEN-LAST:event_infoEsperaNDActionPerformed

    private void elegirAgenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_elegirAgenciaActionPerformed
        // TODO add your handling code here:
        String element = (String)this.elegirAgencia.getSelectedItem();
        if(element == "All") {
            agregarTodos();
        }else {
            agregarPorAgencia(element);
        }
    }//GEN-LAST:event_elegirAgenciaActionPerformed

    private void realizarBusquedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_realizarBusquedaActionPerformed
        String agenciaTemp = (String)this.agenciaBusqueda.getSelectedItem();
        String marca = this.marcaBusqueda.getText();
        String modelo = this.modeloBusqueda.getText();
        String precio = this.precioBusqueda.getText();
        busquedaAvanzada (agenciaTemp, marca, modelo, precio);
        this.marcaBusqueda.setText(null);
        this.modeloBusqueda.setText(null);
        this.precioBusqueda.setText(null);
    }//GEN-LAST:event_realizarBusquedaActionPerformed

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
            java.util.logging.Logger.getLogger(Expomovil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Expomovil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Expomovil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Expomovil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Expomovil().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane agencia;
    private javax.swing.JComboBox<String> agenciaBusqueda;
    private javax.swing.JComboBox<String> agenciaVehiculo;
    private javax.swing.JButton agregarColor;
    private javax.swing.JButton agregarExtra;
    private javax.swing.JButton agregarFoto;
    private javax.swing.JButton atenderEspera;
    private javax.swing.JButton atenderEsperaND;
    private javax.swing.JButton botonConsultarAgencia;
    private javax.swing.JButton botonCrearAgencia;
    private javax.swing.JButton botonEliminarAgencia;
    private javax.swing.JPanel busquedaAvanzada;
    private javax.swing.JTextField cantidadVehiculo;
    private javax.swing.JTextField cedulaAgencia;
    private javax.swing.JTextField cedulaSolicitar;
    private javax.swing.JTextField cilindradaVehiculo;
    private javax.swing.JTextField colorVehiculo;
    private javax.swing.JTextField combustibleVehiculo;
    private javax.swing.JButton consultarVehiculo;
    private javax.swing.JTabbedPane contenedorPrincipal;
    private javax.swing.JTextField correoSolicitar;
    private javax.swing.JButton crearVehiculo;
    private javax.swing.JTextField descripcionVehiculo;
    private javax.swing.JTextField direccionSolicitar;
    private javax.swing.JComboBox<String> elegirAgencia;
    private javax.swing.JButton eliminarVehiculo;
    private javax.swing.JTextField extrasVehiculo;
    private javax.swing.JTextField fotoVehiculo;
    private javax.swing.JPanel home;
    private javax.swing.JButton infoEspera;
    private javax.swing.JButton infoEsperaND;
    private javax.swing.JLabel intro1;
    private javax.swing.JLabel intro2;
    private javax.swing.JLabel intro3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JLabel labelCedula;
    private javax.swing.JLabel labelCorreo;
    private javax.swing.JLabel labelDireccion;
    private javax.swing.JLabel labelNombre;
    private javax.swing.JLabel labelNumero;
    private javax.swing.JLabel labelVehiculo;
    private javax.swing.JList<String> listaAgenciaConsultar;
    private javax.swing.JList<String> listaAgenciaEliminar;
    private javax.swing.JList<String> listaAtenderEspera;
    private javax.swing.JTabbedPane listaEspera;
    private javax.swing.JList<String> listaEsperaNoDisponible;
    private javax.swing.JList<String> listaVehiculoConsultar;
    private javax.swing.JList<String> listaVehiculoEliminar;
    private javax.swing.JList<String> listaVehiculoModificar;
    private javax.swing.JTextField marcaBusqueda;
    private javax.swing.JTextField marcaVehiculo;
    private javax.swing.JTextField modeloBusqueda;
    private javax.swing.JTextField modeloVehiculo;
    private javax.swing.JButton modificarVehiculo;
    private javax.swing.JTextField nombreAgencia;
    private javax.swing.JTextField nombreSolicitar;
    private javax.swing.JTextField precioBusqueda;
    private javax.swing.JTextField precioVehiculo;
    private javax.swing.JButton realizarBusqueda;
    private javax.swing.JButton solicitar;
    private javax.swing.JPanel solicitarVehiculo;
    private javax.swing.JTextField telefonoSolicitar;
    private javax.swing.JTextField tipoVehiculo;
    private javax.swing.JTextField transmisionVehiculo;
    private javax.swing.JTabbedPane vehiculo;
    private javax.swing.JComboBox<String> vehiculoSolicitar;
    private javax.swing.JPanel visualizar;
    private javax.swing.JPanel visualizarLista;
    // End of variables declaration//GEN-END:variables
}
