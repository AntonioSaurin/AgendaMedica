/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package agendamedica.view;

import agendamedica.DAO.ConsultaDAO;
import agendamedica.DAO.MedicoDAO;
import agendamedica.DAO.PacienteDAO;
import agendamedica.DAO.ProntuarioDAO;
import agendamedica.controller.ConsultaController;
import agendamedica.controller.PacienteController;
import agendamedica.model.Consulta;
import agendamedica.model.Medico;
import agendamedica.model.Paciente;
import agendamedica.model.Prontuario;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.jdatepicker.impl.*;
import java.util.Properties;
import java.util.Calendar;
import java.util.List;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import javax.swing.table.DefaultTableModel;

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
    JPanel painelAgenda = new JPanel(new BorderLayout());
    painelAgenda.setBackground(Color.LIGHT_GRAY);

    JLabel titulo = new JLabel("Agenda");
    titulo.setFont(new Font("SansSerif", Font.BOLD, 28));
    titulo.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 0));

    JComboBox<String> comboMedicos = new JComboBox<>();
    JButton botaoVerAgenda = new JButton("Ver agenda");
    
     try {
        MedicoDAO medicoDAO = new MedicoDAO();
        List<Medico> medicos = medicoDAO.listarTodos();
        for (Medico m : medicos) {
            comboMedicos.addItem(m.getNome());
        }
    } catch (Exception ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(painelAgenda, "Erro ao carregar médicos: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
    }
     
    JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    topPanel.setBackground(Color.LIGHT_GRAY);
    topPanel.add(titulo);
    topPanel.add(Box.createHorizontalStrut(30));
    topPanel.add(new JLabel("Médico:"));
    topPanel.add(comboMedicos);
    topPanel.add(botaoVerAgenda);
    
    String[] colunas = {"Data", "Hora", "Tipo", "Status"};
    DefaultTableModel tableModel = new DefaultTableModel(colunas, 0);
    JTable tabela = new JTable(tableModel);
    JScrollPane scrollPane = new JScrollPane(tabela);
    
    painelAgenda.add(topPanel, BorderLayout.NORTH);
    painelAgenda.add(scrollPane, BorderLayout.CENTER);
    
     botaoVerAgenda.addActionListener(e -> {
        String medicoSelecionado = (String) comboMedicos.getSelectedItem();
        if (medicoSelecionado == null || medicoSelecionado.isEmpty()) {
            JOptionPane.showMessageDialog(painelAgenda, "Selecione um médico.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            ConsultaDAO consultaDAO = new ConsultaDAO();
            int IDMedico = MedicoDAO.buscarIdPorNome(medicoSelecionado);
            List<Consulta> consultas = consultaDAO.buscarConsultasPorMedico(IDMedico);

            tableModel.setRowCount(0);
            for (Consulta c : consultas) {
                tableModel.addRow(new Object[]{
                    c.getDtConsulta(),
                    c.getHrConsulta(),
                    c.getTipoConsulta(),
                    c.getStatus()
                });
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(painelAgenda, "Erro ao buscar agenda: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    });
     
    return painelAgenda;
    }

    private JPanel criarPainelProntuario() {
    JPanel painelProntuario = new JPanel(new BorderLayout());
    painelProntuario.setBackground(Color.LIGHT_GRAY);
    painelProntuario.setBorder(BorderFactory.createEmptyBorder(20, 30, 30, 30));

    JPanel painelPesquisa = new JPanel(new FlowLayout(FlowLayout.LEFT));
    painelPesquisa.setBackground(Color.LIGHT_GRAY);

    JTextField campoCpf = new JTextField(15);
    JButton botaoPesquisar = new JButton("Pesquisar");

    painelPesquisa.add(new JLabel("CPF do paciente:"));
    painelPesquisa.add(campoCpf);
    painelPesquisa.add(botaoPesquisar);

    JPanel infoPanel = new JPanel();
    infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
    infoPanel.setBackground(Color.LIGHT_GRAY);

    JLabel nome = new JLabel("Nome do paciente");
    nome.setFont(new Font("SansSerif", Font.BOLD, 28));
    nome.setAlignmentX(Component.LEFT_ALIGNMENT);
    JLabel CPF = new JLabel("CPF");

    Font infoFont = new Font("SansSerif", Font.PLAIN, 18);
    CPF.setFont(infoFont);

    JLabel tituloProntuario = new JLabel("Prontuário");
    tituloProntuario.setFont(new Font("SansSerif", Font.BOLD, 24));
    tituloProntuario.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));

    JLabel subTitulo = new JLabel("Relatório");
    subTitulo.setFont(new Font("SansSerif", Font.PLAIN, 18));

    JTextArea relatorio = new JTextArea();
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
    painelTexto.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));
    painelTexto.add(relatorio, BorderLayout.CENTER);

    infoPanel.add(nome);
    infoPanel.add(Box.createVerticalStrut(10));
    infoPanel.add(CPF);
    infoPanel.add(tituloProntuario);
    infoPanel.add(subTitulo);
    infoPanel.add(Box.createVerticalStrut(10));

    painelProntuario.add(painelPesquisa, BorderLayout.NORTH);
    painelProntuario.add(infoPanel, BorderLayout.CENTER);
    painelProntuario.add(painelTexto, BorderLayout.SOUTH);

    botaoPesquisar.addActionListener(e -> {
        String cpfBusca = campoCpf.getText().replaceAll("[^0-9]", "");
        if (cpfBusca.isEmpty()) {
            JOptionPane.showMessageDialog(painelProntuario, "Digite um CPF válido.");
            return;
        }

        try {
            Prontuario prontuario = ProntuarioDAO.buscarProntuarioPorCpf(cpfBusca);

            if (prontuario != null) {
                nome.setText(prontuario.getNomePaciente());
                CPF.setText("CPF: " + cpfBusca);
                relatorio.setText(prontuario.getRelatorio());
            } else {
                JOptionPane.showMessageDialog(painelProntuario, "Paciente não encontrado.");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(painelProntuario, "Erro ao buscar prontuário.");
        }
    });

    return painelProntuario;
}

    private JPanel criarPainelNovaConsulta() {
        JPanel painelNovaConsulta = new JPanel(new FlowLayout(FlowLayout.LEFT, 30, 20));
        painelNovaConsulta.setBackground(Color.LIGHT_GRAY);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBackground(Color.LIGHT_GRAY);
        
        Font labelFont = new Font("SansSerif", Font.PLAIN, 20);

        JTextField cpfField;
        JComboBox<String> tipoConsultaComboBox;
        JComboBox<String> medicoComboBox;

        JLabel cpfLabel = new JLabel("CPF:");
        cpfLabel.setFont(labelFont);
        formPanel.add(cpfLabel);
        formPanel.add(Box.createVerticalStrut(5));
        cpfField = criarCampoArredondado();
        formPanel.add(cpfField);
        formPanel.add(Box.createVerticalStrut(15));

        JLabel tipoLabel = new JLabel("Tipo de consulta:");
        tipoLabel.setFont(labelFont);
        formPanel.add(tipoLabel);
        formPanel.add(Box.createVerticalStrut(5));
        tipoConsultaComboBox = new JComboBox<>(new String[]{"Virtual", "Presencial"});
        formPanel.add(tipoConsultaComboBox);
        formPanel.add(Box.createVerticalStrut(15));

        JLabel medicoLabel = new JLabel("Médicos disponíveis:");
        medicoLabel.setFont(labelFont);
        formPanel.add(medicoLabel);
        formPanel.add(Box.createVerticalStrut(5));
        medicoComboBox = new JComboBox<>(new String[]{"medico1", "medico2"});
        formPanel.add(medicoComboBox);
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

        salvarButton.addActionListener(e -> {
    try {
        String cpf = cpfField.getText();
        String tipoConsulta = (String) tipoConsultaComboBox.getSelectedItem();
        String nomeMedico = (String) medicoComboBox.getSelectedItem();
        java.util.Date selectedDate = (java.util.Date) datePicker.getModel().getValue();

        if (cpf.isEmpty() || selectedDate == null || tipoConsulta == null || nomeMedico == null) {
            JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos.");
            return;
        }

        int idPaciente = PacienteDAO.buscarIdPorCpf(cpf);
        int idMedico = MedicoDAO.buscarIdPorNome(nomeMedico);

        LocalDate dataConsulta = selectedDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalTime horaConsulta = LocalTime.of(9, 0);
        
        Consulta consulta = new Consulta(0, dataConsulta, horaConsulta, "Agendada", tipoConsulta);
        ConsultaController controller = new ConsultaController(0, dataConsulta, horaConsulta, "Agendada", tipoConsulta);
        boolean sucesso = controller.marcarConsulta(consulta, idMedico, idPaciente);

        if (sucesso) {
            JOptionPane.showMessageDialog(null, "Consulta marcada com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Erro ao marcar consulta.");
        }

    } catch (Exception ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(null, "Erro: " + ex.getMessage());
    } 
});
        
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
        
        cadastrarButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        String nome = nomeField.getText();
        String cpf = cpfField.getText();
        String dtNascimento = nascimentoField.getText();
        String sexo = sexoField.getText();
        String senha = senhaField.getText();
        String confirmaSenha = confirmaSenhaField.getText();
        
        DateTimeFormatter entrada = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter saida = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        String dataFormatada = null;
        try {
            LocalDate data = LocalDate.parse(dtNascimento, entrada);
            dataFormatada = data.format(saida);
        } catch (DateTimeParseException d) {
            JOptionPane.showMessageDialog(null, "Data inválida. Use o formato dd/MM/yyyy.");
            return;
        }

        if (!senha.equals(confirmaSenha)) {
            JOptionPane.showMessageDialog(null, "As senhas não coincidem.");
            return;
        }
        
        if (!sexo.equals("Masculino") && !sexo.equals("Feminino")) {
            JOptionPane.showMessageDialog(null, "Sexo invalido.");
            return;
        }
        
        if (cpf.isEmpty() || nome.isEmpty() || dtNascimento.isEmpty() || sexo.isEmpty() || senha.isEmpty() || confirmaSenha.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos.");
            return;
        }

        Paciente paciente = new Paciente(0, 0, nome, senha, cpf, dataFormatada, sexo);
        PacienteController controller = new PacienteController(0, 0, nome, senha, cpf, dataFormatada, sexo);
        controller.cadastrarPaciente(paciente);

        JOptionPane.showMessageDialog(null, "Paciente cadastrado com sucesso!");
    }
});

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

