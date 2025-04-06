/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package agendamedica.view;

import javax.swing.*;
import java.awt.*;

public class frmPrincipal extends JFrame{
    public frmPrincipal() {
        setTitle("Agenda Médica");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);

        // Criação do painel de abas
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Agenda", criarPainelAgenda());
        tabbedPane.addTab("Prontuário", criarPainelProntuario());
        tabbedPane.addTab("Nova Consulta", criarPainelNovaConsulta());
        tabbedPane.addTab("Cadastrar Paciente", new JPanel()); // Placeholder

        add(tabbedPane);
        setVisible(true);
    }

    private JPanel criarPainelAgenda() {
        JPanel painelAgenda = new JPanel();
        painelAgenda.setLayout(new BorderLayout());
        painelAgenda.setBackground(Color.LIGHT_GRAY);

        JLabel titulo = new JLabel("Agenda");
        titulo.setFont(new Font("SansSerif", Font.BOLD, 28));
        titulo.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 0));

        JLabel subTitulo = new JLabel("Segunda");
        subTitulo.setFont(new Font("SansSerif", Font.BOLD, 22));
        subTitulo.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 0));

        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        headerPanel.setBackground(Color.LIGHT_GRAY);
        headerPanel.add(titulo);
        headerPanel.add(subTitulo);

        JPanel blocosPanel = new JPanel(new GridLayout(1, 7, 20, 0));
        blocosPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));
        blocosPanel.setBackground(Color.LIGHT_GRAY);

        for (int i = 0; i < 7; i++) {
            JPanel bloco = new JPanel();
            bloco.setPreferredSize(new Dimension(80, 200));
            bloco.setBackground(Color.DARK_GRAY);
            bloco.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            blocosPanel.add(bloco);
        }

        painelAgenda.add(headerPanel, BorderLayout.NORTH);
        painelAgenda.add(blocosPanel, BorderLayout.CENTER);

        return painelAgenda;
    }

    private JPanel criarPainelProntuario() {
        JPanel painelProntuario = new JPanel(new BorderLayout());
        painelProntuario.setBackground(Color.LIGHT_GRAY);
        painelProntuario.setBorder(BorderFactory.createEmptyBorder(20, 30, 30, 30));

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBackground(Color.LIGHT_GRAY);

        JLabel nome = new JLabel("JUBILEU DA SILVA");
        nome.setFont(new Font("SansSerif", Font.BOLD, 28));
        nome.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel dataNascimento = new JLabel("12/02/2004");
        JLabel sexo = new JLabel("Sexo: Masculino");
        JLabel cpf = new JLabel("CPF: 123.456.324-01");

        Font infoFont = new Font("SansSerif", Font.PLAIN, 18);
        dataNascimento.setFont(infoFont);
        sexo.setFont(infoFont);
        cpf.setFont(infoFont);

        JLabel tituloProntuario = new JLabel("Prontuário");
        tituloProntuario.setFont(new Font("SansSerif", Font.BOLD, 24));
        tituloProntuario.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));

        JLabel subTitulo = new JLabel("Relatório");
        subTitulo.setFont(new Font("SansSerif", Font.PLAIN, 18));

        JTextArea relatorio = new JTextArea(
            "Observação:\nPaciente possui diabete, pressão alta e depressão\n\n" +
            "Medicamentos:\nFluoxetina, Insulina, Captopril"
        );
        relatorio.setFont(new Font("SansSerif", Font.PLAIN, 16));
        relatorio.setLineWrap(true);
        relatorio.setWrapStyleWord(true);
        relatorio.setEditable(false);
        relatorio.setBackground(Color.WHITE);
        relatorio.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JPanel painelTexto = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
            }
        };
        painelTexto.setBackground(Color.WHITE);
        painelTexto.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        painelTexto.add(relatorio);

        infoPanel.add(nome);
        infoPanel.add(Box.createVerticalStrut(10));
        infoPanel.add(dataNascimento);
        infoPanel.add(sexo);
        infoPanel.add(cpf);
        infoPanel.add(tituloProntuario);
        infoPanel.add(subTitulo);
        infoPanel.add(Box.createVerticalStrut(10));

        painelProntuario.add(infoPanel, BorderLayout.NORTH);
        painelProntuario.add(painelTexto, BorderLayout.CENTER);

        return painelProntuario;
    }

    private JPanel criarPainelNovaConsulta() {
        JPanel painelNovaConsulta = new JPanel();
        painelNovaConsulta.setLayout(new GridBagLayout());
        painelNovaConsulta.setBackground(Color.LIGHT_GRAY);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 20, 10, 20);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;

        Font labelFont = new Font("SansSerif", Font.PLAIN, 20);

        JLabel cpfLabel = new JLabel("CPF:");
        cpfLabel.setFont(labelFont);
        painelNovaConsulta.add(cpfLabel, gbc);

        gbc.gridy++;
        JTextField cpfField = criarCampoArredondado();
        painelNovaConsulta.add(cpfField, gbc);

        gbc.gridy++;
        JLabel tipoLabel = new JLabel("Tipo de consulta:");
        tipoLabel.setFont(labelFont);
        painelNovaConsulta.add(tipoLabel, gbc);

        gbc.gridy++;
        JTextField tipoField = criarCampoArredondado();
        painelNovaConsulta.add(tipoField, gbc);

        gbc.gridy++;
        JLabel medicoLabel = new JLabel("Médicos disponíveis:");
        medicoLabel.setFont(labelFont);
        painelNovaConsulta.add(medicoLabel, gbc);

        gbc.gridy++;
        JTextField medicoField = criarCampoArredondado();
        painelNovaConsulta.add(medicoField, gbc);

        gbc.gridy++;
        JLabel dataLabel = new JLabel("Datas disponíveis:");
        dataLabel.setFont(labelFont);
        painelNovaConsulta.add(dataLabel, gbc);

        gbc.gridy++;
        JTextField dataField = criarCampoArredondado();
        painelNovaConsulta.add(dataField, gbc);

        return painelNovaConsulta;
    }

    private JTextField criarCampoArredondado() {
        JTextField campo = new JTextField(20) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
                super.paintComponent(g);
                g2.dispose();
            }

            @Override
            protected void paintBorder(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setColor(Color.MAGENTA);
                g2.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 30, 30);
                g2.dispose();
            }
        };
        campo.setOpaque(false);
        campo.setFont(new Font("SansSerif", Font.PLAIN, 18));
        campo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        return campo;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(frmPrincipal::new);
    }
}
