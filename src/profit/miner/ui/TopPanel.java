/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package profit.miner.ui;

import profit.TradeManager;

/**
 *
 * @author USER
 */
public class TopPanel extends javax.swing.JPanel {
    TradeManager tradeMgr;
    /**
     * Creates new form PricePanel
     */
    public TopPanel(TradeManager mgr) {
        tradeMgr = mgr;
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar1 = new javax.swing.JToolBar();
        jComboBox2 = new javax.swing.JComboBox<>();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        jComboBox1 = new javax.swing.JComboBox<>();
        cmdRunStrategy = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        cmdSettings = new javax.swing.JButton();
        txtSymbolPrice = new javax.swing.JLabel();

        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.Y_AXIS));

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "EUR/USD", "GBP/USD", "AUD/USD", "NZD/USD", "ZAR/USD", "INR/USD", "USD/JPY", "USD/CHF", "USD/CAD", "USD/HKD", "USD/SGD", "EUR/CHF", "EUR/JPY", "EUR/GBP", "EUR/AUD", "EUR/CAD", "EUR/NZD", "GBP/JPY", "GBP/CHF", "GBP/AUD", "GBP/NZD", "GBP/CAD", "AUD/CAD", "AUD/JPY", "AUD/CHF", "NZD/JPY", "NZD/CHF", "ZAR/EUR", "ZAR/GBP", "USD/TRY", "USD/SEK", "USD/RUB", "USD/DKK", "GBP/ZAR", "GBP/SEK", "GBP/PLN", "GBP/DKK", "EUR/ZAR", "EUR/TRY", "CAD/CHF", "CAD/JPY", "GBP/TRY", "CHF/JPY", "TRY/JPY", "XAU/USD", "XBR/USD", "XAG/USD" }));
        jToolBar1.add(jComboBox2);
        jToolBar1.add(jSeparator2);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "<SELECT STRATEGY>", "Buy Stop & Sell Stop Catcher" }));
        jToolBar1.add(jComboBox1);

        cmdRunStrategy.setText("Run Strategy");
        cmdRunStrategy.setFocusable(false);
        cmdRunStrategy.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        cmdRunStrategy.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        cmdRunStrategy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdRunStrategyActionPerformed(evt);
            }
        });
        jToolBar1.add(cmdRunStrategy);
        jToolBar1.add(jSeparator1);

        cmdSettings.setText("Settings");
        cmdSettings.setFocusable(false);
        cmdSettings.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        cmdSettings.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(cmdSettings);

        add(jToolBar1);

        txtSymbolPrice.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtSymbolPrice.setText("3.9349");
        add(txtSymbolPrice);
    }// </editor-fold>//GEN-END:initComponents

    private void cmdRunStrategyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdRunStrategyActionPerformed
        
    }//GEN-LAST:event_cmdRunStrategyActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cmdRunStrategy;
    private javax.swing.JButton cmdSettings;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel txtSymbolPrice;
    // End of variables declaration//GEN-END:variables
}