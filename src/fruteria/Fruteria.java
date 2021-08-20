package fruteria;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.text.NumberFormat;

public class Fruteria extends JFrame implements ActionListener, ItemListener {

    JLabel[] lblNomeFruta = new JLabel[4];
    String frutas[] = {"Morango", "Maçã", "Laranja", "Banana"};
    JLabel[] lblPrecoFrutasAte5 = new JLabel[4];
    double precoFrutasAte5[] = {2.50, 1.80, 0.99, 2.30};
    JLabel[] lblPrecoFrutasMaisDe5 = new JLabel[4];
    double precoFrutasMaisDe5[] = {2.20, 1.50, 0.95, 2.00};
    JLabel[] lblPrecoUnitario = new JLabel[4];
    JTextField[] txtQtd = new JTextField[4];
    JButton calc, limp;
    NumberFormat NF1;
    JLabel lblNomeFrutaTop, lblPrecoAte5Top, lblPrecoMaisDe5Top, lblQntdTop, lblPrecoQntd, lblTotal, lblPrecoFinal, lblFormaPagamento;
    JRadioButton RBDinheiro, RBDebito, RBCredito;
    ButtonGroup RB;
    JPanel P1, P2;

    public static void main(String[] args) {
        JFrame janela = new Fruteria();
        janela.setUndecorated(true);
        janela.getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setVisible(true);
    }

    public Fruteria() {

        setTitle("Compre uma fruta");
        setBounds(200, 100, 550, 480);
        getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER));

        NF1 = NumberFormat.getNumberInstance();
        NF1.setMinimumFractionDigits(2);

        P1 = new JPanel();
        P2 = new JPanel();
        P1.setLayout(new GridLayout(6, 5));
        P2.setLayout(new BoxLayout(P2, BoxLayout.Y_AXIS));

        lblNomeFrutaTop = new JLabel("Fruta", javax.swing.SwingConstants.CENTER);
        lblNomeFrutaTop.setFont(new Font("", Font.BOLD, 16));
        lblPrecoAte5Top = new JLabel("Preço até 5 kg", javax.swing.SwingConstants.CENTER);
        lblPrecoAte5Top.setFont(new Font("", Font.BOLD, 16));
        lblPrecoMaisDe5Top = new JLabel("Preço por mais de 5 kg", javax.swing.SwingConstants.CENTER);
        lblPrecoMaisDe5Top.setFont(new Font("", Font.BOLD, 16));
        lblQntdTop = new JLabel("Quantidade", javax.swing.SwingConstants.CENTER);
        lblQntdTop.setFont(new Font("", Font.BOLD, 16));
        lblPrecoQntd = new JLabel("Preço", javax.swing.SwingConstants.CENTER);
        lblPrecoQntd.setFont(new Font("", Font.BOLD, 16));

        for (int i = 0; i < 4; i++) {
            lblNomeFruta[i] = new JLabel(" " + frutas[i], javax.swing.SwingConstants.CENTER);
            lblNomeFruta[i].setFont(new Font("", Font.PLAIN, 14));
        }

        for (int i = 0; i < 4; i++) {
            lblPrecoFrutasAte5[i] = new JLabel("R$ " + NF1.format(precoFrutasAte5[i]), javax.swing.SwingConstants.CENTER);
            lblPrecoFrutasAte5[i].setFont(new Font("", Font.PLAIN, 14));
        }

        for (int i = 0; i < 4; i++) {
            lblPrecoFrutasMaisDe5[i] = new JLabel("R$ " + NF1.format(precoFrutasMaisDe5[i]), javax.swing.SwingConstants.CENTER);
            lblPrecoFrutasMaisDe5[i].setFont(new Font("", Font.PLAIN, 14));
        }

        for (int i = 0; i < 4; i++) {
            txtQtd[i] = new JTextField();
            txtQtd[i].setFont(new Font("", Font.PLAIN, 14));
        }

        for (int i = 0; i < 4; i++) {
            lblPrecoUnitario[i] = new JLabel("", javax.swing.SwingConstants.CENTER);
            lblPrecoUnitario[i].setFont(new Font("", Font.PLAIN, 14));
        }

        P1.add(lblNomeFrutaTop);
        P1.add(lblPrecoAte5Top);
        P1.add(lblPrecoMaisDe5Top);
        P1.add(lblQntdTop);
        P1.add(lblPrecoQntd);

        for (int i = 0; i < 4; i++) {
            P1.add(lblNomeFruta[i]);
            P1.add(lblPrecoFrutasAte5[i]);
            P1.add(lblPrecoFrutasMaisDe5[i]);
            P1.add(txtQtd[i]);
            P1.add(lblPrecoUnitario[i]);
        }

        lblFormaPagamento = new JLabel("Forma de pagamento", javax.swing.SwingConstants.CENTER);
        lblFormaPagamento.setFont(new Font("", Font.BOLD, 16));
        P2.add(lblFormaPagamento);

        RBDinheiro = new JRadioButton("Dinheiro");
        RBDinheiro.setFont(new Font("", Font.PLAIN, 14));
        RBDebito = new JRadioButton("Débito");
        RBDebito.setFont(new Font("", Font.PLAIN, 14));
        RBCredito = new JRadioButton("Crédito");
        RBCredito.setFont(new Font("", Font.PLAIN, 14));

        RB = new ButtonGroup();
        RB.add(RBDinheiro);
        RBDinheiro.addItemListener(this);
        RB.add(RBDebito);
        RBDebito.addItemListener(this);
        RB.add(RBCredito);
        RBCredito.addItemListener(this);

        P2.add(RBDinheiro);
        P2.add(RBDebito);
        P2.add(RBCredito);

        lblTotal = new JLabel("Total:", javax.swing.SwingConstants.CENTER);
        lblTotal.setFont(new Font("", Font.BOLD, 16));
        P2.add(lblTotal);

        lblPrecoFinal = new JLabel("", javax.swing.SwingConstants.CENTER);
        lblPrecoFinal.setFont(new Font("", Font.PLAIN, 16));
        P2.add(lblPrecoFinal);

        limp = new JButton("Limpar");
        limp.addActionListener(this);
        P2.add(limp);

        getContentPane().add(P1);
        getContentPane().add(P2);
    }

    double[] quant = new double[4];
    double[] valor = new double[4];
    double total;
    int somaqnt;

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == limp) {
            for (int i = 0; i < 4; i++) {
                txtQtd[i].setText("");
                lblPrecoUnitario[i].setText("");
                lblPrecoFinal.setText("");
                total = 0;
                somaqnt = 0;
                RB.clearSelection();
            }
        }
    }

    public void itemStateChanged(ItemEvent e) {
        try {
            total = 0;
            somaqnt = 0;           
            for (int i = 0; i < 4; i++) {

                if (txtQtd[i].getText().length() != 0) { //se a quantidade inserida não for vazia

                    quant[i] = Double.parseDouble(txtQtd[i].getText());

                    if (quant[i] < 5) { //se for menor que 5

                        valor[i] = quant[i] * precoFrutasAte5[i];
                        lblPrecoUnitario[i].setText("R$ " + NF1.format(valor[i]));

                    } else { //se for maior que 5

                        valor[i] = quant[i] * precoFrutasMaisDe5[i];
                        lblPrecoUnitario[i].setText("R$ " + NF1.format(valor[i]));
                    }

                    somaqnt += quant[i]; //soma o valor das quantidades para desconto
                    total += valor[i]; //soma os valores unitários
                    
                    if ((somaqnt > 8 || total > 25) && RBDinheiro.isSelected()) {
                        total -= (10*total/100);
                    }
                    else if(RBCredito.isSelected()){
                        total += (total*2/100);
                    }
                    
                    lblPrecoFinal.setText("R$ " + NF1.format(total));
                }
            }
        } catch (NumberFormatException erro) {
            JOptionPane.showMessageDialog(null, "Caro usuário, digite somente números",
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

}
