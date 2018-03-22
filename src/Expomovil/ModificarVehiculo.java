/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Expomovil;

import DataStructures.LinkedList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;

/**
 *
 * @author allanvz
 */
public class ModificarVehiculo extends javax.swing.JFrame {
    
    LinkedList<Agencia> agencias;
    Vehiculo vehiculo;
    DefaultComboBoxModel listaAgenciasS;
    DefaultListModel listaColoresS;
    DefaultListModel listaExtrasS;
    Boolean value;

    /**
     * Creates new form ModificarVehiculo
     */
    public ModificarVehiculo(LinkedList agencias, Vehiculo vehiculo) {
        initComponents();
        this.agencias = agencias;
        this.vehiculo = vehiculo;
        this.listaAgenciasS = new DefaultComboBoxModel();
        this.agenciaVehiculo.setModel(listaAgenciasS);
        this.listaColoresS = new DefaultListModel();
        this.listaColores.setModel(listaColoresS);
        this.listaExtrasS = new DefaultListModel();
        this.listaExtras.setModel(listaExtrasS);
        this.agenciaVehiculo.setSelectedItem(vehiculo.getAgencia().getNombre());
        this.cantidadVehiculo.setText(Integer.toString(vehiculo.getCantidad()));
        this.cilindradaVehiculo.setText(Integer.toString(vehiculo.getCilindrada()));
        this.combustibleVehiculo.setText(vehiculo.getCombustible());
        this.descripcionVehiculo.setText(vehiculo.getDescripcion());
        this.fotoVehiculo.setText(vehiculo.getFoto());
        this.marcaVehiculo.setText(vehiculo.getMarca());
        this.modeloVehiculo.setText(vehiculo.getModelo());
        this.precioVehiculo.setText(Double.toString(vehiculo.getPrecio()));
        this.tipoVehiculo.setText(vehiculo.getTipo());
        this.transmisionVehiculo.setText(vehiculo.getTransmision());
        this.value = false;
        agregarExtras(vehiculo.getExtras());
        agregarColores(vehiculo.getColores());
    }
    
    public void agregarListaAgenciasS(String elemento) {
        this.listaAgenciasS.addElement(elemento);
    }
    
    public void eliminarListaAgenciasS() {
        this.listaAgenciasS.removeAllElements();
        this.agencias.goToStart();
        while (this.agencias.getElement() != null) {
            agregarListaAgenciasS(this.agencias.getElement().getNombre());
            this.agencias.next();
        }
    }
    
    public String[] eliminarExtra(String extra, String[] extras){
        String[] arrNuevo = new String[extras.length-2];
        int pos = 0;
        for(int x = 0; x != extras.length-1; x++) {
            if(!extra.equals(extras[x])) {
                arrNuevo[pos] = extras[x];
                pos++;
            }
        }
        return arrNuevo;
    }
    
    public void agregarExtras(String[] extras) {
        this.listaExtrasS.clear();
        for(int x = 0; x != extras.length; x++) {
            this.listaExtrasS.addElement(extras[x]);
        }
    }
    
    public void agregarColores(String[] colores) {
        this.listaColoresS.clear();
        for(int x = 0; x < colores.length; x++) {
            this.listaColoresS.addElement(colores[x]);
        }
    }
    
    public String[] agregarArreglo(String[] array, String add) {
        String[] arrayTemp = new String[array.length+1];
        for(int x = 0; x < array.length; x++) {
            arrayTemp[x] = array[x];
        }
        arrayTemp[-1] = add;
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
    
    public Vehiculo getElement() {
        return this.vehiculo;
    }
    
    public Boolean getBoolean() {
        return this.value;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

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
        modificarVehiculo = new javax.swing.JButton();
        agregarColor = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        listaColores = new javax.swing.JList<>();
        eliminarColor = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        listaExtras = new javax.swing.JList<>();
        eliminarExtra = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

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

        modificarVehiculo.setText("Modificar");
        modificarVehiculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modificarVehiculoActionPerformed(evt);
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
                        .addComponent(modificarVehiculo)))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(jLabel10)
                        .addComponent(marcaVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(colorVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addComponent(modificarVehiculo)
                .addContainerGap(43, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(listaColores);

        eliminarColor.setText("Eliminar");
        eliminarColor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarColorActionPerformed(evt);
            }
        });

        jScrollPane2.setViewportView(listaExtras);

        eliminarExtra.setText("Eliminar");
        eliminarExtra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarExtraActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 174, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(37, 37, 37)
                                .addComponent(eliminarColor))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 14, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(eliminarExtra)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(eliminarColor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(eliminarExtra)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void marcaVehiculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_marcaVehiculoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_marcaVehiculoActionPerformed

    private void agregarColorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarColorActionPerformed
        // TODO add your handling code here:
        String[] newArray = agregarArreglo(this.vehiculo.getColores(), this.colorVehiculo.getText());
        this.vehiculo.setColores(newArray);
        agregarColores(vehiculo.getColores());
    }//GEN-LAST:event_agregarColorActionPerformed

    private void agregarExtraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarExtraActionPerformed
        // TODO add your handling code here:
        String[] newArray = agregarArreglo(this.vehiculo.getExtras(), this.extrasVehiculo.getText());
        this.vehiculo.setExtras(newArray);
        agregarExtras(vehiculo.getExtras());
    }//GEN-LAST:event_agregarExtraActionPerformed

    private void eliminarColorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarColorActionPerformed
        // TODO add your handling code here:
        String[] newArray = eliminarArreglo(this.vehiculo.getColores(), this.vehiculo.getColores()[this.listaColores.getSelectedIndex()]);
        this.vehiculo.setColores(newArray);
        agregarColores(vehiculo.getColores());
    }//GEN-LAST:event_eliminarColorActionPerformed

    private void eliminarExtraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarExtraActionPerformed
        // TODO add your handling code here:
        String[] newArray = eliminarArreglo(this.vehiculo.getExtras(), this.vehiculo.getExtras()[this.listaExtras.getSelectedIndex()]);
        this.vehiculo.setExtras(newArray);
        agregarExtras(vehiculo.getExtras());
    }//GEN-LAST:event_eliminarExtraActionPerformed

    private void modificarVehiculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modificarVehiculoActionPerformed
        // TODO add your handling code here:
        String marca = this.marcaVehiculo.getText();
        String modelo = this.modeloVehiculo.getText();
        String tipo = this.tipoVehiculo.getText();
        String descripcion = this.descripcionVehiculo.getText();
        int cilindrada = Integer.valueOf(this.cilindradaVehiculo.getText());
        String combustible = this.combustibleVehiculo.getText();
        String transmision = this.transmisionVehiculo.getText();
        String foto = this.fotoVehiculo.getText();
        double precio = Double.valueOf(this.precioVehiculo.getText());
        int cantidad = Integer.valueOf(this.cantidadVehiculo.getText());
        this.agencias.goToPos(this.agenciaVehiculo.getSelectedIndex());
        Agencia agenciaPertenencia = this.agencias.getElement();
        this.vehiculo = new Vehiculo(marca, modelo, tipo, descripcion, cilindrada, transmision, combustible, this.vehiculo.getColores(), this.vehiculo.getExtras(), foto, precio, cantidad, agenciaPertenencia);
        this.setVisible(false);
    }//GEN-LAST:event_modificarVehiculoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> agenciaVehiculo;
    private javax.swing.JButton agregarColor;
    private javax.swing.JButton agregarExtra;
    private javax.swing.JButton agregarFoto;
    private javax.swing.JTextField cantidadVehiculo;
    private javax.swing.JTextField cilindradaVehiculo;
    private javax.swing.JTextField colorVehiculo;
    private javax.swing.JTextField combustibleVehiculo;
    private javax.swing.JTextField descripcionVehiculo;
    private javax.swing.JButton eliminarColor;
    private javax.swing.JButton eliminarExtra;
    private javax.swing.JTextField extrasVehiculo;
    private javax.swing.JTextField fotoVehiculo;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList<String> listaColores;
    private javax.swing.JList<String> listaExtras;
    private javax.swing.JTextField marcaVehiculo;
    private javax.swing.JTextField modeloVehiculo;
    private javax.swing.JButton modificarVehiculo;
    private javax.swing.JTextField precioVehiculo;
    private javax.swing.JTextField tipoVehiculo;
    private javax.swing.JTextField transmisionVehiculo;
    // End of variables declaration//GEN-END:variables
}
