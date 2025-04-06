/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package agendamedica.view;

import javax.swing.*;
import java.awt.*;
import org.jdatepicker.impl.*;
import java.util.Properties;
import java.util.Calendar;
import java.text.SimpleDateFormat;

public class frmPrincipal extends JFrame {

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
        tabbedPane.addTab("Cadastrar Paciente", criarPainelCadastrarPaciente());

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

        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        headerPanel.setBackground(Color.LIGHT_GRAY);
        headerPanel.add(titulo);

        JPanel blocosPanel = new JPanel(new GridLayout(1, 7, 20, 0));
        blocosPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));
        blocosPanel.setBackground(Color.LIGHT_GRAY);

        String[] dias = {"Domingo", "Segunda", "Terça", "Quarta", "Quinta", "Sexta", "Sábado"};

        for (int i = 0; i < 7; i++) {
            JPanel bloco = new JPanel();
            bloco.setLayout(new BorderLayout());
            bloco.setPreferredSize(new Dimension(80, 200));
            bloco.setBackground(Color.DARK_GRAY);
            bloco.setBorder(BorderFactory.createLineBorder(Color.GRAY));

            JLabel diaLabel = new JLabel(dias[i], SwingConstants.CENTER);
            diaLabel.setForeground(Color.WHITE);
            diaLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
            bloco.add(diaLabel, BorderLayout.NORTH);

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
        painelTexto.add(relatorio, BorderLayout.CENTER);

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
        JPanel painelNovaConsulta = new JPanel(new FlowLayout(FlowLayout.LEFT, 30, 20));
        painelNovaConsulta.setBackground(Color.LIGHT_GRAY);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBackground(Color.LIGHT_GRAY);

        Font labelFont = new Font("SansSerif", Font.PLAIN, 20);

        JLabel cpfLabel = new JLabel("CPF:");
        cpfLabel.setFont(labelFont);
        formPanel.add(cpfLabel);
        formPanel.add(Box.createVerticalStrut(5));
        formPanel.add(criarCampoArredondado());
        formPanel.add(Box.createVerticalStrut(15));

        JLabel tipoLabel = new JLabel("Tipo de consulta:");
        tipoLabel.setFont(labelFont);
        formPanel.add(tipoLabel);
        formPanel.add(Box.createVerticalStrut(5));
        formPanel.add(criarCampoArredondado());
        formPanel.add(Box.createVerticalStrut(15));

        JLabel medicoLabel = new JLabel("Médicos disponíveis:");
        medicoLabel.setFont(labelFont);
        formPanel.add(medicoLabel);
        formPanel.add(Box.createVerticalStrut(5));
        formPanel.add(criarCampoArredondado());
        formPanel.add(Box.createVerticalStrut(15));

        JLabel dataLabel = new JLabel("Datas disponíveis:");
        dataLabel.setFont(labelFont);
        formPanel.add(dataLabel);
        formPanel.add(Box.createVerticalStrut(5));

        UtilDateModel model = new UtilDateModel();
        model.setSelected(true);

        Properties p = new Properties();
        p.put("text.today", "Hoje");
        p.put("text.month", "Mês");
        p.put("text.year", "Ano");

        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
        JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
        datePicker.setBackground(Color.WHITE);
        formPanel.add(datePicker);

        formPanel.add(Box.createVerticalStrut(25));

        JButton salvarButton = new JButton("Salvar");
        salvarButton.setFont(new Font("SansSerif", Font.BOLD, 18));
        salvarButton.setBackground(Color.BLACK);
        salvarButton.setForeground(Color.WHITE);
        salvarButton.setFocusPainted(false);
        salvarButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        formPanel.add(salvarButton);

        painelNovaConsulta.add(formPanel);
        return painelNovaConsulta;
    }

    private JPanel criarPainelCadastrarPaciente() {
        JPanel painel = new JPanel(new GridLayout(4, 2, 40, 20));
        painel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));
        painel.setBackground(Color.LIGHT_GRAY);

        Font labelFont = new Font("SansSerif", Font.PLAIN, 20);
        JTextField nomeField = criarCampoArredondado();
        JTextField cpfField = criarCampoArredondado();
        JTextField nascimentoField = criarCampoArredondado();
        JTextField sexoField = criarCampoArredondado();
        JTextField senhaField = criarCampoArredondado();
        JTextField confirmaSenhaField = criarCampoArredondado();

        painel.add(criarCampoComLabel("Nome:", nomeField, labelFont));
        painel.add(criarCampoComLabel("Crie uma senha:", senhaField, labelFont));
        painel.add(criarCampoComLabel("CPF:", cpfField, labelFont));
        painel.add(criarCampoComLabel("Confirme a senha:", confirmaSenhaField, labelFont));
        painel.add(criarCampoComLabel("Data de nascimento:", nascimentoField, labelFont));
        painel.add(criarCampoComLabel("Sexo:", sexoField, labelFont));

        JButton cadastrarButton = new JButton("Cadastrar");
        cadastrarButton.setFont(new Font("SansSerif", Font.PLAIN, 18));
        cadastrarButton.setBackground(Color.WHITE);
        cadastrarButton.setFocusPainted(false);
        JPanel botaoPanel = new JPanel();
        botaoPanel.setBackground(Color.LIGHT_GRAY);
        botaoPanel.add(cadastrarButton);

        JPanel container = new JPanel(new BorderLayout());
        container.setBackground(Color.LIGHT_GRAY);
        container.add(painel, BorderLayout.CENTER);
        container.add(botaoPanel, BorderLayout.SOUTH);

        return container;
    }

    private JPanel criarCampoComLabel(String texto, JComponent campo, Font font) {
        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painel.setBackground(Color.LIGHT_GRAY);

        JLabel label = new JLabel(texto);
        label.setFont(font);

        painel.add(label);
        painel.add(Box.createVerticalStrut(5));
        painel.add(campo);

        return painel;
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
                g2.setColor(Color.BLACK);
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

    public class DateLabelFormatter extends JFormattedTextField.AbstractFormatter {
        private final String datePattern = "dd/MM/yyyy";
        private final SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

        @Override
        public Object stringToValue(String text) throws java.text.ParseException {
            return dateFormatter.parseObject(text);
        }

        @Override
        public String valueToString(Object value) {
            if (value != null) {
                Calendar cal = (Calendar) value;
                return dateFormatter.format(cal.getTime());
            }
            return "";
        }
    }
}

